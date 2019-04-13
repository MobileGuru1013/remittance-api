package com.voteva.remittance.api.v1;

import com.voteva.remittance.api.v1.dto.AccountDto;
import com.voteva.remittance.api.v1.dto.UserDto;
import com.voteva.remittance.api.v1.response.RestResponse;
import com.voteva.remittance.entity.Account;
import com.voteva.remittance.entity.User;
import com.voteva.remittance.exception.ServiceExecutionException;
import com.voteva.remittance.service.AccountOperation;
import com.voteva.remittance.service.UserOperation;
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

public class UserEndpointsTest {

    @InjectMocks
    private UserEndpoints userEndpoints;
    @Mock
    private UserOperation userOperation;
    @Mock
    private AccountOperation accountOperation;

    private static final String TEST_USER_ID = "1";

    @BeforeClass
    public void init() {
        initMocks(this);
    }

    @Test
    public void testGetUser_Success() {
        User user = new User();
        user.setId(TEST_USER_ID);
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");

        when(userOperation.getUser(any(String.class))).thenReturn(user);

        Response response = userEndpoints.getUser(TEST_USER_ID);
        assertNotNull(response);
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        assertTrue(response.getEntity() instanceof RestResponse);

        RestResponse restResponse = (RestResponse) response.getEntity();
        assertTrue(restResponse.isSuccess());
        assertNull(restResponse.getError());
        assertTrue(restResponse.getBody() instanceof UserDto);
        assertEquals(((UserDto) restResponse.getBody()).getId(), TEST_USER_ID);
    }

    @Test(expectedExceptions = ServiceExecutionException.class)
    public void testGetUser_Error() {
        when(userOperation.getUser(any(String.class))).thenThrow(new ServiceExecutionException("Message"));

        userEndpoints.getUser(TEST_USER_ID);
    }

    @Test
    public void testGetAccounts_Success() {
        Account account = new Account();
        account.setId("123");
        account.setOwnerId(TEST_USER_ID);
        account.setBalance(new BigDecimal(1000));
        List<Account> accounts = Collections.singletonList(account);

        when(accountOperation.getAll(any(String.class))).thenReturn(accounts);

        Response response = userEndpoints.getAccounts(TEST_USER_ID);
        assertNotNull(response);
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        assertTrue(response.getEntity() instanceof RestResponse);

        RestResponse restResponse = (RestResponse) response.getEntity();
        assertTrue(restResponse.isSuccess());
        assertNull(restResponse.getError());
        assertTrue(restResponse.getBody() instanceof List);

        List<?> responseList = (List<?>) restResponse.getBody();
        assertTrue(!responseList.isEmpty());
        assertEquals(((AccountDto) responseList.get(0)).getOwnerId(), TEST_USER_ID);
    }

    @Test(expectedExceptions = ServiceExecutionException.class)
    public void testGetAccounts_Error() {
        when(accountOperation.getAll(any(String.class))).thenThrow(new ServiceExecutionException("Message"));

        userEndpoints.getAccounts(TEST_USER_ID);
    }
}