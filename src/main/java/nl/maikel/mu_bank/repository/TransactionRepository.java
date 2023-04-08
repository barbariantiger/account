package nl.maikel.mu_bank.repository;

import nl.maikel.mu_bank.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> { }
