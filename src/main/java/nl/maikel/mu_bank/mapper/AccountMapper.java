package nl.maikel.mu_bank.mapper;

import nl.maikel.mu_bank.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import static nl.maikel.mu_bank.constants.AccountConstants.*;

@Mapper(componentModel = MAPPER_COMPONENT_MODEL)
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);


    @Mappings(value = {
            @Mapping(source = INITIAL_CREDIT, target = BALANCE, defaultValue = DEFAULT_BALANCE),
            @Mapping(source = CUSTOMER_ID, target = CUSTOMER_DOT_ID)
    })
    Account dtoToAccount(AccountDTO accountDTO);
}
