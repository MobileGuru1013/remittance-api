package com.voteva.remittance.api.v1;

import com.voteva.remittance.api.v1.dto.TransactionDto;
import com.voteva.remittance.api.v1.mapper.TransactionMapper;
import com.voteva.remittance.api.v1.response.RestResponseBuilder;
import com.voteva.remittance.entity.Transaction;
import com.voteva.remittance.service.TransactionOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/v1/accounts")
@Produces(APPLICATION_JSON + ";charset=utf-8")
@Singleton
public class AccountEndpoints {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountEndpoints.class);
    private TransactionOperation transactionOperation;

    @Inject
    public AccountEndpoints(TransactionOperation transactionOperation) {
        this.transactionOperation = transactionOperation;
    }

    /**
     * Returns all successful transaction for specified account id
     *
     * @param id account id
     * @return all successful transaction for specified account id
     */
    @GET
    @Path("/{id}/transactions")
    public Response getTransactions(@PathParam("id") String id) {
        LOGGER.debug("Getting transactions for user with id={}", id);

        List<Transaction> transactions = transactionOperation.getAll(id);
        List<TransactionDto> transactionDtoList = TransactionMapper.from(transactions);

        return RestResponseBuilder.build(transactionDtoList);
    }
}
