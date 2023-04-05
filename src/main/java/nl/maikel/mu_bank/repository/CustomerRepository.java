package nl.maikel.mu_bank.repository;

import nl.maikel.mu_bank.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> { }
