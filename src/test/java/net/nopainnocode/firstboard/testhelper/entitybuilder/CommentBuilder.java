package net.nopainnocode.firstboard.testhelper.entitybuilder;

import net.nopainnocode.firstboard.domain.Comment;
import net.nopainnocode.firstboard.domain.User;

/**
 * Created by no_pain_no_code on 2015. 11. 6..
 */
public class CommentBuilder implements Builder<Comment>{

    private String content;

    private User user;

    @Override
    public Comment build() {

        return new Comment(this.content, this.user);
    }

    public CommentBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public CommentBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public static Comment createDefault(User user){

        return new CommentBuilder()
                .setContent("defaultContent")
                .setUser(user)
                .build();
    }
}
