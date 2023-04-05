package nl.maikel.mu_bank.service;

import nl.maikel.mu_bank.mapper.AccountDTO;
import nl.maikel.mu_bank.mapper.AccountMapper;
import nl.maikel.mu_bank.model.Account;
import nl.maikel.mu_bank.repository.AccountRepository;
import nl.maikel.mu_bank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;
    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountMapper = accountMapper;
    }

    public Account createAccount(AccountDTO accountDTO) {
        Account account = accountMapper.dtoToAccount(accountDTO);
        customerRepository.findById(accountDTO.getCustomerId()).ifPresent(account::setCustomer);
        account = accountRepository.save(account);
        return account;
    }
}
