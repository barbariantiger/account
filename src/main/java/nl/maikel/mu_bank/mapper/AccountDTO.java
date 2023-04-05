package nl.maikel.mu_bank.mapper;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AccountDTO {
    @NotEmpty(message = "Customer ID must not be null")
    private String customerId;
    private BigDecimal initialCredit;
}
