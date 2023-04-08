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
            @Mapping(target = ID, expression = "java(accountDTO.getId() != null && accountDTO.getId().isEmpty() ? null : accountDTO.getId())"),
            @Mapping(target = BALANCE, ignore = true),
            @Mapping(source = CUSTOMER_ID, target = CUSTOMER_DOT_ID),
            @Mapping(target = CUSTOMER, ignore = true),
            @Mapping(target = TRANSACTIONS, ignore = true)
    })
    Account dtoToAccount(AccountDTO accountDTO);
}
