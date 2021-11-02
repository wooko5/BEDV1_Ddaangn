package com.dev.ddaangn.user.controller;

import com.dev.ddaangn.user.repository.UserRepository;
import com.dev.ddaangn.user.service.TempService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserApiControllerTest {

    @Autowired
    private TempService tempService;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("사용자 이름으로 현재 정보 조회")
    @WithMockUser(roles = "USER")
    public void 현재유저_조회 () throws Exception {
        // Given
        String id="1";
        String url = "http://localhost:" + 8080 + "/api/v1/temperature/"+id;

        // When Then
        mvc
                .perform(get(url))
                .andDo(print())
                .andExpect(
                status().is2xxSuccessful());
    }


    @Test
    @DisplayName("전체 USER LIST 조회")
    @WithMockUser(roles = "USER")
    public void 다건조회() throws Exception {
        // Given
        String url = "http://localhost:" + 8080 + "/api/v1/temperature/list";

        // When Then
        mvc
                .perform(get(url))
                .andDo(print())
                .andExpect(
                        status().is2xxSuccessful());
    }

    @Test
    @DisplayName("이름으로 온도 수정 null->36.0")
    @WithMockUser(roles = "USER")
    public void 온도수정() throws Exception {
        // Given
        String name="DS Seo";
        String url = "http://localhost:" + 8080 + "/api/v1/temperature/"+name;

        // WHEN THEN
        mvc
                .perform(put(url))
                .andDo(print())
                .andExpect(
                        status().is2xxSuccessful());
    }



}
