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
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/v1/transactions")
@Consumes(APPLICATION_JSON + ";charset=UTF-8")
@Produces(APPLICATION_JSON + ";charset=UTF-8")
@Singleton
public class TransactionEndpoints {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionEndpoints.class);
    private TransactionOperation transactionOperation;

    @Inject
    public TransactionEndpoints(TransactionOperation transactionOperation) {
        this.transactionOperation = transactionOperation;
    }

    /**
     * Performs transaction
     *
     * @param transactionDto transaction info
     * @return transaction status
     */
    @POST
    @Path("/perform")
    public Response perform(TransactionDto transactionDto) {
        LOGGER.debug("Performing transaction {}", transactionDto);

        Transaction transaction = TransactionMapper.from(transactionDto);
        transactionOperation.perform(transaction);

        return RestResponseBuilder.build();
    }
}
