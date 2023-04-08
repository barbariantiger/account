package nl.maikel.mu_bank.constants;

public class AccountConstants {

    private AccountConstants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static final String UTILITY_CLASS = "Utility class";
    public static final String TRANSACTION_TABLE = "TRANSACTION";
    public static final String ACCOUNT_TABLE = "ACCOUNT";
    public static final String CUSTOMER_TABLE = "CUSTOMER";
    public static final String TRANSACTION_BINDING_NAME = "startTransaction-out-0";
    public static final String MAPPER_COMPONENT_MODEL = "spring";
    public static final String ACCOUNT_UPDATED = "Account updated: {}";
    public static final String TRANSACTION_EVENT_CREATED = "Transaction Event created: {}";
    public static final String ACCOUNTS_ENDPOINT = "/accounts";
    public static final String BAD_REQUEST = "Bad Request";
    public static final String NOT_FOUND = "Not found";
    public static final String CONFLICT = "Conflict";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String STATUS_CODE_500 = "500 Status Code";
    public static final String CUSTOMER_ID_MUST_NOT_BE_NULL = "Customer ID must not be null";
    public static final String TRANSACTION_ID = "TRANSACTION_ID";
    public static final String ACCOUNT = "account";
    public static final String ACCOUNTS = "accounts";
    public static final String CUSTOMER = "customer";
    public static final String TRANSACTIONS = "transactions";
    public static final String BALANCE = "balance";
    public static final String CUSTOMER_DOT_ID = "customer.id";
    public static final String CUSTOMER_ID = "customerId";
    public static final String TRANSACTION_ID_CAMEL_CASE = "transactionId";
    public static final String ID = "id";
    public static final String ACCOUNT_MUST_NOT_BE_NULL = "Account must not be null";
    public static final String CUSTOMERS_ENPOINT = "/customers/{id}";
}
