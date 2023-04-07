package nl.maikel.mu_bank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.maikel.mu_bank.event.TransactionType;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static nl.maikel.mu_bank.constants.AccountConstants.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity(name = TRANSACTION_TABLE)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnoreProperties(TRANSACTIONS)
    @NotNull(message = ACCOUNT_MUST_NOT_BE_NULL)
    private Account account;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal updatedBalance;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdOn;
}
