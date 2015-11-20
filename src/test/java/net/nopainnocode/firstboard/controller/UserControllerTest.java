package net.nopainnocode.firstboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.nopainnocode.firstboard.FirstBoardApplication;
import net.nopainnocode.firstboard.domain.User;
import net.nopainnocode.firstboard.service.UserService;
import net.nopainnocode.firstboard.testhelper.entitybuilder.UserBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


/**
 * Created by no_pain_no_code on 2015. 11. 5..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FirstBoardApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=8888")
@Transactional
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;

    private MockMvc mockMvc;
    private User baseUser;

    @Before
    public void setUp() throws Exception {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.baseUser = userService.addNewUser(UserBuilder.createDefault());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddNewUser() throws Exception {

        // give
        User newUser = UserBuilder.createDefault();
        newUser.setUsername("addUserUsername");
        newUser.setNickName("addUserNickname");

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(newUser));
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.handler().handlerType(UserController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("addNewUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", org.hamcrest.Matchers.is(newUser.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", org.hamcrest.Matchers.is(newUser.getPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", org.hamcrest.Matchers.is(newUser.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", org.hamcrest.Matchers.is(newUser.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nickname", org.hamcrest.Matchers.is(newUser.getNickname())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testFindUser() throws Exception {

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/" + baseUser.getUserId());
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().handlerType(UserController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("findUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", org.hamcrest.Matchers.is(baseUser.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", org.hamcrest.Matchers.is(baseUser.getPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", org.hamcrest.Matchers.is(baseUser.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", org.hamcrest.Matchers.is(baseUser.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nickname", org.hamcrest.Matchers.is(baseUser.getNickname())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateUser() throws Exception {

        // given
        User updateTestUser = UserBuilder.createDefault();
        updateTestUser.setUserId(baseUser.getUserId());
        updateTestUser.setPassword("updatedPassword");
        updateTestUser.setFirstName("updatedFirstName");

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(updateTestUser));
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.handler().handlerType(UserController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("updateUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", org.hamcrest.Matchers.is(updateTestUser.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", org.hamcrest.Matchers.is(updateTestUser.getPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", org.hamcrest.Matchers.is(updateTestUser.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", org.hamcrest.Matchers.is(updateTestUser.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nickname", org.hamcrest.Matchers.is(updateTestUser.getNickname())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDeleteUser() throws Exception {

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/" + baseUser.getUserId());
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.handler().handlerType(UserController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("deleteUser"))
                .andDo(MockMvcResultHandlers.print());
    }
}