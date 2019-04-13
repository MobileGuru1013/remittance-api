package com.voteva.remittance.api.v1;

import com.voteva.remittance.api.v1.dto.TransactionDto;
import com.voteva.remittance.api.v1.response.RestResponse;
import com.voteva.remittance.entity.Transaction;
import com.voteva.remittance.exception.ServiceExecutionException;
import com.voteva.remittance.service.TransactionOperation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.*;

public class AccountEndpointsTest {

    @InjectMocks
    private AccountEndpoints accountEndpoints;
    @Mock
    private TransactionOperation transactionOperation;

    @BeforeClass
    public void init() {
        initMocks(this);
    }

    @Test
    public void testGetTransactions_Success() {
        Transaction transaction = new Transaction();
        transaction.setId("123");
        transaction.setSenderId("1");
        transaction.setRecipientId("2");
        transaction.setAmount(new BigDecimal(300));
        List<Transaction> transactions = Collections.singletonList(transaction);

        when(transactionOperation.getAll(any(String.class))).thenReturn(transactions);

        Response response = accountEndpoints.getTransactions("1");
        assertNotNull(response);
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        assertTrue(response.getEntity() instanceof RestResponse);

        RestResponse restResponse = (RestResponse) response.getEntity();
        assertTrue(restResponse.isSuccess());
        assertNull(restResponse.getError());
        assertTrue(restResponse.getBody() instanceof List);

        List<?> responseList = (List<?>) restResponse.getBody();
        assertTrue(!responseList.isEmpty());
        assertTrue(responseList.get(0) instanceof TransactionDto);
    }

    @Test(expectedExceptions = ServiceExecutionException.class)
    public void testGetTransactions_Fail() {
        when(transactionOperation.getAll(any(String.class))).thenThrow(new ServiceExecutionException("Message"));

        accountEndpoints.getTransactions("1");
    }
}