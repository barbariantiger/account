package nl.maikel.mu_bank.mapper;

import nl.maikel.mu_bank.event.TransactionProcessedEvent;
import nl.maikel.mu_bank.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import static nl.maikel.mu_bank.constants.AccountConstants.*;

@Mapper(componentModel = MAPPER_COMPONENT_MODEL)
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mappings(value = {
            @Mapping(source = TRANSACTION_ID_CAMEL_CASE, target = ID)
    })
    Transaction transactProcEventToTransaction(TransactionProcessedEvent event);
}
