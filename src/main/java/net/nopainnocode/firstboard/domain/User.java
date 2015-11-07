package net.nopainnocode.firstboard.domain;

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
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(unique = true, nullable = false)
	private String nickname;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date joinDate;


	public User(String username, String password, String firstName, String lastName, String nickName) {
		
		setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setNickName(nickName);
		setJoinDate(new Date());
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
	public String getFullName(){
		return getLastName() + " " + getFirstName();
	}
	
	@Transient
	public User updateUser(User modifiedUser){

		if(isUserEmpty(modifiedUser)) {

			this.setFirstName(modifiedUser.getFirstName());
			this.setLastName(modifiedUser.getLastName());
			this.setNickName(modifiedUser.getNickname());
		}

		return this;
	}

	private boolean isUserEmpty(User modifiedUser) {

		if(StringUtils.isEmpty(modifiedUser.getFirstName())
				||StringUtils.isEmpty(modifiedUser.getLastName())
				||StringUtils.isEmpty(modifiedUser.getNickname()))
			throw new IllegalArgumentException("argument id null");

		return true;
	}
}
