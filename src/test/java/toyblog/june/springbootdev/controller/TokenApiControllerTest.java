package toyblog.june.springbootdev.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import toyblog.june.springbootdev.config.jwt.JwtFactory;
import toyblog.june.springbootdev.config.jwt.JwtProperties;
import toyblog.june.springbootdev.domain.RefreshToken;
import toyblog.june.springbootdev.domain.User;
import toyblog.june.springbootdev.dto.CreateAccessTokenRequest;
import toyblog.june.springbootdev.repository.RefreshTokenRepository;
import toyblog.june.springbootdev.repository.UserRepository;


import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenApiControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @BeforeEach
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("엑세스 토큰을 발급한다")
    public void createAccessToken() throws Exception {
        // given
        final String url = "/api/token";
        User testUser = userRepository.save(User.builder()
                .email("user@gmail.com")
                .password("a1234")
                .build());

        String refreshToken = JwtFactory.builder()
                .claims(Map.of("id", testUser.getId()))
                .build()
                .createToken(jwtProperties);

        refreshTokenRepository.save(new RefreshToken(testUser.getId(), refreshToken));

        // * DTO class를 이용했을 때
        CreateAccessTokenRequest request = new CreateAccessTokenRequest();
        request.setRefreshToken(refreshToken);

        // * record를 이용했을 때
        /*CreateAccessTokenRequest request = new CreateAccessTokenRequest(refreshToken);
        request.refreshToken();*/

        final String requestBody = objectMapper.writeValueAsString(request);

        // when
        ResultActions resultActions = mockMvc.perform(
                post(url)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(requestBody));

        // then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
    }
}
