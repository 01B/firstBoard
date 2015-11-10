package net.nopainnocode.firstboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import net.nopainnocode.firstboard.FirstBoardApplication;
import net.nopainnocode.firstboard.domain.Board;
import net.nopainnocode.firstboard.domain.User;
import net.nopainnocode.firstboard.service.BoardService;
import net.nopainnocode.firstboard.service.UserService;
import net.nopainnocode.firstboard.testhelper.entitybuilder.BoardBuilder;
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
 * Created by no_pain_no_code on 2015. 11. 6..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FirstBoardApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port = 8888")
@Transactional
public class BoardControllerTest extends TestCase {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

    private MockMvc mockMvc;
    private User baseUser;
    private Board baseBoard;

    @Before
    public void setUp() throws Exception {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.baseUser = userService.addNewUser(UserBuilder.createDefault());
        this.baseBoard = boardService.addNewBoard(BoardBuilder.createDefault(this.baseUser));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddNewBoard() throws Exception {

        // given
        Board newBoard = BoardBuilder.createDefault(baseUser);
        newBoard.setTitle("addNewBoardTitle");
        newBoard.setContent("addNewBoardContent");

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/boards")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(newBoard));
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.handler().handlerType(BoardController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("addNewBoard"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", org.hamcrest.Matchers.is(newBoard.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", org.hamcrest.Matchers.is(newBoard.getContent())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testFindBoard() throws Exception {

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/boards/" + baseBoard.getBoardId());
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().handlerType(BoardController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("findBoard"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.boardId", org.hamcrest.Matchers.is(baseBoard.getBoardId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", org.hamcrest.Matchers.is(baseBoard.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", org.hamcrest.Matchers.is(baseBoard.getContent())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.username", org.hamcrest.Matchers.is(baseBoard.getUser().getUsername())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateBoard() throws Exception {

        // given
        Board enteredBoard = BoardBuilder.createDefault(baseUser);
        enteredBoard.setBoardId(baseBoard.getBoardId());
        enteredBoard.setTitle("updateTestTitle");
        enteredBoard.setContent("updateTestContent");

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/boards")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(enteredBoard));
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.handler().handlerType(BoardController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("updateBoard"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.boardId", org.hamcrest.Matchers.is(enteredBoard.getBoardId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", org.hamcrest.Matchers.is(enteredBoard.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", org.hamcrest.Matchers.is(enteredBoard.getContent())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.username", org.hamcrest.Matchers.is(enteredBoard.getUser().getUsername())))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void testDeleteBoard() throws Exception {

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/boards/" + this.baseBoard.getBoardId());
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.handler().handlerType(BoardController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("deleteBoard"))
                .andDo(MockMvcResultHandlers.print());

    }
}