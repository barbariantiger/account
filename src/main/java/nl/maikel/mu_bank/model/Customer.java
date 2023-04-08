package nl.maikel.mu_bank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

import static nl.maikel.mu_bank.constants.AccountConstants.CUSTOMER;
import static nl.maikel.mu_bank.constants.AccountConstants.CUSTOMER_TABLE;

@Data
@Entity(name = CUSTOMER_TABLE)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToMany(mappedBy = CUSTOMER, fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(CUSTOMER)
    private List<Account> accounts;
    @NotNull
    private String name;
    @NotNull
    private String surname;
}
