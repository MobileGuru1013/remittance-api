package com.voteva.remittance.api.v1;

import com.voteva.remittance.api.v1.dto.TransactionDto;
import com.voteva.remittance.entity.Transaction;
import com.voteva.remittance.exception.ServiceExecutionException;
import com.voteva.remittance.service.TransactionOperation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;

public class TransactionEndpointsTest {

    @InjectMocks
    private TransactionEndpoints transactionEndpoints;
    @Mock
    private TransactionOperation transactionOperation;

    @BeforeClass
    public void init() {
        initMocks(this);
    }

    @Test(expectedExceptions = ServiceExecutionException.class)
    public void testPerform_Error() {
        doThrow(new ServiceExecutionException("Message")).when(transactionOperation).perform(any(Transaction.class));

        TransactionDto transactionDto = new TransactionDto(null, "1", "2", "100", null);
        transactionEndpoints.perform(transactionDto);
    }
}