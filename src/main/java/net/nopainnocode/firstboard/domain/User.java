package net.nopainnocode.firstboard.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "user")
public class User implements Serializable
{


	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Column(unique = true, nullable = false)
	@NotEmpty
	private String username;
	
	@Column(nullable = false)
	@NotEmpty
	private String password;
	
	@Column(nullable = false)
	@NotEmpty
	private String firstName;
	
	@Column(nullable = false)
	@NotEmpty
	private String lastName;
	
	@Column(unique = true, nullable = false)
	@NotEmpty
	private String nickname;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date joinDate = new Date();


	public User(String username, String password, String firstName, String lastName, String nickname) {
		
		setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setNickName(nickname);
	}
	
	public User(){
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickName(String nickName) {
		this.nickname = nickName;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	@Transient
	public User updateUser(User enteredUser){

		if(isUserEmpty(enteredUser)) {

			this.setPassword(enteredUser.getPassword());
			this.setFirstName(enteredUser.getFirstName());
			this.setLastName(enteredUser.getLastName());
			this.setNickName(enteredUser.getNickname());
		}

		return this;
	}

	private boolean isUserEmpty(User enteredUser) {

		if(StringUtils.isEmpty(enteredUser.getFirstName())
				||StringUtils.isEmpty(enteredUser.getLastName())
				||StringUtils.isEmpty(enteredUser.getNickname()))
			throw new IllegalArgumentException("argument id null");

		return true;
	}
}
