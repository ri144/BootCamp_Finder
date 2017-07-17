package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Testimonial {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long testimonialId;
	
	@ManyToOne
	@JoinColumn(name="campId")
	private Camp camp;
	
	@ManyToOne
	@JoinColumn(name="campId")
	private User user;
	
	@Column(name = "comment")
	private String comment;

	public long getTestimonialId() {
		return testimonialId;
	}

	public void setTestimonialId(long testimonialId) {
		this.testimonialId = testimonialId;
	}

	public Camp getCamp() {
		return camp;
	}

	public void setCamp(Camp camp) {
		this.camp = camp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
