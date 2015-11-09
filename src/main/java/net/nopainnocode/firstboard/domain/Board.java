package net.nopainnocode.firstboard.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "board")
public class Board implements Serializable{

	@Id @GeneratedValue
	private long boardId;
	
	@Column(nullable = false, length = 50)
	private String title;
	
	@Lob
	@Column(nullable = false)
	private String content;
	
	private int readCount;
	
	@Column(nullable = false)
	private Date createDate;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comment> comments;

	public Board(String title, String content, User user) {

		// todo : is board empty

		setTitle(title);
		setContent(content);
		setReadCount(0);
		this.createDate = new Date();
		this.user = user;
		setComments(new ArrayList<Comment>());
	}

	public long getBoardId() {
		return boardId;
	}

	public void setBoardId(long boardId) {
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

	public User getUser() {
		return user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Board updateBoard(Board board){

		this.setTitle(board.getTitle());
		this.setContent(board.getContent());

		return this;
	}
	
}