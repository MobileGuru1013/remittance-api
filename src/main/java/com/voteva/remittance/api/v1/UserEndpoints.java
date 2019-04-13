package com.voteva.remittance.api.v1;

import com.voteva.remittance.api.v1.dto.AccountDto;
import com.voteva.remittance.api.v1.dto.UserDto;
import com.voteva.remittance.api.v1.mapper.AccountMapper;
import com.voteva.remittance.api.v1.mapper.UserMapper;
import com.voteva.remittance.api.v1.response.RestResponseBuilder;
import com.voteva.remittance.entity.Account;
import com.voteva.remittance.entity.User;
import com.voteva.remittance.service.AccountOperation;
import com.voteva.remittance.service.UserOperation;
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

@Path("/v1/users")
@Produces(APPLICATION_JSON + ";charset=utf-8")
@Singleton
public class UserEndpoints {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionEndpoints.class);
    private AccountOperation accountOperation;
    private UserOperation userOperation;

    @Inject
    public UserEndpoints(UserOperation userOperation,
                         AccountOperation accountOperation) {
        this.userOperation = userOperation;
        this.accountOperation = accountOperation;
    }

    /**
     * Returns user info by id
     *
     * @param id user id
     * @return user info by id
     */
    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") String id) {
        LOGGER.debug("Getting user info with id={}", id);

        User user = userOperation.getUser(id);
        UserDto userDto = UserMapper.from(user);

        return RestResponseBuilder.build(userDto);
    }

    /**
     * Returns user accounts by specified user id
     *
     * @param id user id
     * @return user accounts by specified user id
     */
    @GET
    @Path("/{id}/accounts")
    public Response getAccounts(@PathParam("id") String id) {
        LOGGER.debug("Getting accounts for user with id={}", id);

        List<Account> accounts = accountOperation.getAll(id);
        List<AccountDto> accountDtoList = AccountMapper.from(accounts);

        return RestResponseBuilder.build(accountDtoList);
    }
}
