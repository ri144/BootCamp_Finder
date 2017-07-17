package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserCamp {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long usercampId;
	
	/*private long user_Id;
	*/
	/*private long camp_Id;*/
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="campId")
	private Camp camp;
	
	private String status;

	public long getUsercampId() {
		return usercampId;
	}

	public void setUsercampId(long usercampId) {
		this.usercampId = usercampId;
	}

	/*public long getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(long user_Id) {
		this.user_Id = user_Id;
	}

	public long getCamp_Id() {
		return camp_Id;
	}

	public void setCamp_Id(long camp_Id) {
		this.camp_Id = camp_Id;
	}*/

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Camp getCamp() {
		return camp;
	}

	public void setCamp(Camp camp) {
		this.camp = camp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
