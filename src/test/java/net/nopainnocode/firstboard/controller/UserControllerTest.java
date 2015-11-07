package net.nopainnocode.firstboard.controller;

import junit.framework.TestCase;
import net.nopainnocode.firstboard.domain.User;
import net.nopainnocode.firstboard.testhelper.entitybuilder.UserBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by no_pain_no_code on 2015. 11. 5..
 */
public class UserControllerTest extends TestCase {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddNewUser() throws Exception {

        // given
        User user = new UserBuilder().createDefault();

        // when
        //ResultActions resultActions

        // then
    }

    @Test
    public void testFindUser() throws Exception {

    }

    @Test
    public void testUpdateUser() throws Exception {

    }

    @Test
    public void testDeleteUser() throws Exception {

    }
}