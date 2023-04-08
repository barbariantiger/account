package nl.maikel.mu_bank.service;

import lombok.extern.slf4j.Slf4j;
import nl.maikel.mu_bank.event.EventType;
import nl.maikel.mu_bank.event.TransactionEvent;
import nl.maikel.mu_bank.event.TransactionType;
import nl.maikel.mu_bank.mapper.AccountDTO;
import nl.maikel.mu_bank.mapper.AccountMapper;
import nl.maikel.mu_bank.model.Account;
import nl.maikel.mu_bank.repository.AccountRepository;
import nl.maikel.mu_bank.repository.CustomerRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static nl.maikel.mu_bank.constants.AccountConstants.TRANSACTION_BINDING_NAME;
import static nl.maikel.mu_bank.constants.AccountConstants.TRANSACTION_EVENT_CREATED;

@Slf4j
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;
    private final StreamBridge streamBridge;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository, AccountMapper accountMapper, StreamBridge streamBridge) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountMapper = accountMapper;
        this.streamBridge = streamBridge;
    }

    public void createAccount(AccountDTO accountDTO) {
        Account account = accountMapper.dtoToAccount(accountDTO);
        customerRepository.findById(accountDTO.getCustomerId()).ifPresent(account::setCustomer);
        account = accountRepository.save(account);
        createTransaction(accountDTO, account);
    }

    private void createTransaction(AccountDTO accountDTO, Account account) {
        if (accountDTO.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            var transactionEvent = new TransactionEvent(
                    EventType.CREATE_TRANSACTION,
                    TransactionType.DEPOSIT,
                    accountDTO.getInitialCredit(),
                    account.getId()
            );
            streamBridge.send(TRANSACTION_BINDING_NAME, transactionEvent);
            log.debug(TRANSACTION_EVENT_CREATED, transactionEvent);
        }
    }
}
