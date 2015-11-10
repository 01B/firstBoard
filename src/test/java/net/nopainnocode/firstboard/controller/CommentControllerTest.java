package net.nopainnocode.firstboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import net.nopainnocode.firstboard.FirstBoardApplication;
import net.nopainnocode.firstboard.domain.Board;
import net.nopainnocode.firstboard.domain.Comment;
import net.nopainnocode.firstboard.domain.User;
import net.nopainnocode.firstboard.service.BoardService;
import net.nopainnocode.firstboard.service.CommentService;
import net.nopainnocode.firstboard.service.UserService;
import net.nopainnocode.firstboard.testhelper.entitybuilder.BoardBuilder;
import net.nopainnocode.firstboard.testhelper.entitybuilder.CommentBuilder;
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
public class CommentControllerTest extends TestCase {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentService commentService;

    private MockMvc mockMvc;
    private User baseUser;
    private Board baseBoard;
    private Comment baseComment;

    @Before
    public void setUp() throws Exception {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.baseUser = userService.addNewUser(UserBuilder.createDefault());
        this.baseBoard = boardService.addNewBoard(BoardBuilder.createDefault(baseUser));
        this.baseComment = commentService.addNewComment(baseBoard.getBoardId(), CommentBuilder.createDefault(baseUser));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddNewComment() throws Exception {

        // given
        Comment newComment = CommentBuilder.createDefault(baseUser);
        newComment.setContent("addNewContent");

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/comments/" + baseBoard.getBoardId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(newComment));
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.handler().handlerType(CommentController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("addNewComment"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", org.hamcrest.Matchers.is(newComment.getContent())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.username", org.hamcrest.Matchers.is(newComment.getUser().getUsername())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateComment() throws Exception {

        // given
        Comment enteredComment = CommentBuilder.createDefault(baseUser);
        enteredComment.setCommentId(baseComment.getCommentId());
        enteredComment.setContent("updateTestContent");

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/comments")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(enteredComment));
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.handler().handlerType(CommentController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("updateComment"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.commentId", org.hamcrest.Matchers.is(enteredComment.getCommentId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", org.hamcrest.Matchers.is(enteredComment.getContent())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.username", org.hamcrest.Matchers.is(enteredComment.getUser().getUsername())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDeleteComment() throws Exception {

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/comments/" + this.baseComment.getCommentId());
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.handler().handlerType(CommentController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("deleteComment"))
                .andDo(MockMvcResultHandlers.print());
    }
}