package net.nopainnocode.firstboard.testhelper.entitybuilder;

import net.nopainnocode.firstboard.domain.Board;
import net.nopainnocode.firstboard.domain.Comment;
import net.nopainnocode.firstboard.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by no_pain_no_code on 2015. 11. 6..
 */
public class BoardBuilder implements Builder<Board>{

    private String title;

    private String content;

    private int readCount;

    private User user;

    private List<Comment> comments;

    @Override
    public Board build() {

        return new Board(this.title,
                this.content,
                this.user);
    }

    public BoardBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BoardBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public BoardBuilder setReadCount(int readCount) {
        this.readCount = readCount;
        return this;
    }

    public BoardBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public BoardBuilder setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public static Board createDefault(int commentCount){

        List<Comment> defaultComments = new ArrayList<>();
        for(int i = 0; i < commentCount ; i++)
            defaultComments.add(CommentBuilder.createDefault());

        return new BoardBuilder()
                .setTitle("defaultTitle")
                .setContent("defaultContent")
                .setReadCount(100)
                .setUser(UserBuilder.createDefault())
                .setComments(defaultComments)
                .build();
    }
}
