package com.packt.web.bean;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="message")
public class Message extends AbstractEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column
	@NotEmpty
	private String contents;
	
	@Column(name = "DATE", nullable=false, updatable=false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date created;
	
	@Transient
	private String date;
	
	@ManyToOne
	private Chat chat;
	
	@ManyToOne
	@NotNull
    @JoinColumn(name="author_id")
    private User author;
	
    
    public Message(){
    	
    };


	public String getContents() {
		return contents;
	}
	
	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Message{" +
				"contents='" + contents + '\'' +
				", created=" + created +
				", author=" + author +
				'}';
	}
}
