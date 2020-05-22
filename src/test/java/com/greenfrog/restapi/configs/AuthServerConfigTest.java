package com.greenfrog.restapi.configs;

import com.greenfrog.restapi.accounts.Account;
import com.greenfrog.restapi.accounts.AccountRole;
import com.greenfrog.restapi.accounts.AccountService;
import com.greenfrog.restapi.common.BaseControllerTest;
import com.greenfrog.restapi.common.TestDescription;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Test
    @TestDescription("인증 토큰을 발급 받는 테스트")
    public void getAuthToekn() throws Exception{
        // Given
        String username = "keesun@email.com";
        String password = "keesun";
        Account keesun = Account.builder()
                .email(username)
                .password(password)
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();
        this.accountService.saveAccount(keesun);

        String clientId = "myApp";
        String clientSecret = "pass";

        this.mockMvc.perform(post("/oauth/token")
                    .with(httpBasic(clientId, clientSecret))
                    .param("username", username)
                    .param("password", password)
                    .param("grant_type", "password"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("access_token").exists());
    }
}