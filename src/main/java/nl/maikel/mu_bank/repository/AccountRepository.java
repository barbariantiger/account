package nl.maikel.mu_bank.repository;

import nl.maikel.mu_bank.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {
    @Override
    Account save(Account entity);
}
