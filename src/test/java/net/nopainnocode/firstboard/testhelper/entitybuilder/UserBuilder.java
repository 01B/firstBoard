package net.nopainnocode.firstboard.testhelper.entitybuilder;

import net.nopainnocode.firstboard.domain.User;

/**
 * Created by no_pain_no_code on 2015. 11. 6..
 */
public class UserBuilder implements Builder<User>{

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String nickname;

    @Override
    public User build() {

        return new User(this.username,
                this.password,
                this.firstName,
                this.lastName,
                this.nickname);
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public static User createDefault(){
        return new UserBuilder()
                .setUsername("defaultUsername")
                .setPassword("defaultPassword")
                .setFirstName("defaultFirstName")
                .setLastName("defaultLastName")
                .setNickname("defaultNickname")
                .build();
    }
}
