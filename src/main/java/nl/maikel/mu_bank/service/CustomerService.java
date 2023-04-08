package nl.maikel.mu_bank.service;

import jakarta.persistence.EntityNotFoundException;
import nl.maikel.mu_bank.model.Customer;
import nl.maikel.mu_bank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(String id) {
        return this.customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
