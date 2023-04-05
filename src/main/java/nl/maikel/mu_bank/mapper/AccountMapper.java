package nl.maikel.mu_bank.mapper;

import nl.maikel.mu_bank.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mappings(value = {
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "initialCredit", target = "balance", defaultValue = "0.00"),
            @Mapping(source = "customerId", target = "customer.id")
    })
    Account dtoToAccount(AccountDTO accountDTO);
}
