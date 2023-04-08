package nl.maikel.mu_bank.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public final class Error {
    private Integer httpStatusCode;
    private String httpStatus;
    private String message;
}
