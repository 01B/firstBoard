package net.nopainnocode.firstboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.StringUtils;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity(name = "board")
public class Board implements Serializable{

	@Id @GeneratedValue
	private Long boardId;
	
	@Column(nullable = false, length = 50)
	private String title;
	
	@Lob
	@Column(nullable = false)
	private String content;
	
	private int readCount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	private User user;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	public Board(String title, String content, User user) {

		// todo : is board empty

		setTitle(title);
		setContent(content);
		this.user = user;
	}

	public Board(){

	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date date) { this.createDate = date; }

	public User getUser() {
		return user;
	}

	public void setUser(User user) { this.user = user; }

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Transient
	public Board updateBoard(Board board){

		if(isBoardEmpty(board)){

			this.setBoardId(board.getBoardId());
			this.setTitle(board.getTitle());
			this.setContent(board.getContent());
		}

		return this;
	}

	private boolean isBoardEmpty(Board enteredBoard) {

		if(StringUtils.isEmpty(enteredBoard.getTitle())
				||StringUtils.isEmpty(enteredBoard.getContent()))
			throw new IllegalArgumentException("argument id null");

		return true;
	}
	
}