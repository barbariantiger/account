package nl.maikel.mu_bank.function;

import lombok.extern.slf4j.Slf4j;
import nl.maikel.mu_bank.event.TransactionProcessedEvent;
import nl.maikel.mu_bank.mapper.TransactionMapper;
import nl.maikel.mu_bank.repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

import static nl.maikel.mu_bank.constants.AccountConstants.ACCOUNT_UPDATED;
import static nl.maikel.mu_bank.event.EventType.TRANSACTION_PROCESSED;

@Slf4j
@Configuration
public class TransactionFunction {

    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;

    public TransactionFunction(final TransactionMapper transactionMapper, final AccountRepository accountRepository) {
        this.transactionMapper = transactionMapper;
        this.accountRepository = accountRepository;
    }

    @Bean
    public Consumer<TransactionProcessedEvent> consumeTransaction() {
        return event -> {
            if (event.getType().equals(TRANSACTION_PROCESSED)) {
                var account = accountRepository.findById(event.getAccountId()).orElseThrow();
                var transactions = account.getTransactions();
                transactions.add(transactionMapper.transactProcEventToTransaction(event));
                account.setTransactions(transactions);
                account = accountRepository.save(account);
                log.debug(ACCOUNT_UPDATED, account);
            }
        };
    }
}
