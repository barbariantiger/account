package nl.maikel.mu_bank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

import static nl.maikel.mu_bank.constants.AccountConstants.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = ACCOUNT_TABLE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnoreProperties(ACCOUNTS)
    private Customer customer;
    @OneToMany(mappedBy = ACCOUNT, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnoreProperties(ACCOUNT)
    private List<Transaction> transactions;
    private BigDecimal balance;
}
