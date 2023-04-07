package nl.maikel.mu_bank.mapper;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

import static nl.maikel.mu_bank.constants.AccountConstants.CUSTOMER_ID_MUST_NOT_BE_NULL;

@Data
@AllArgsConstructor
public class AccountDTO {
    @NotEmpty(message = CUSTOMER_ID_MUST_NOT_BE_NULL)
    private String customerId;
    private BigDecimal initialCredit;
}
