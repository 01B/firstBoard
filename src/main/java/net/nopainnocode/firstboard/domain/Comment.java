package net.nopainnocode.firstboard.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "comment")
public class Comment implements Serializable{


	@Id @GeneratedValue
	private Long commentId;

	@Column(nullable = false, length = 100)
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	private User user;

	public Comment(String content, User user) {

		setContent(content);
		setUser(user);
	}

	public Comment(){

	}

	public Comment update(Comment enteredComment) {

		setContent(enteredComment.getContent());

		return this;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) { this.commentId = commentId; }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
