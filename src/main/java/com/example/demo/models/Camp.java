package com.example.demo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Camp {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long campId;
	
	@Column(name = "campName")
	private String campName;
	
	@ManyToOne
	@JoinColumn(name="city_Id")
	private City city;
	
	@Transient /*@Column(name = "enabled")*/
	private boolean enabled;
	
	@Column(name = "adminId")
	private long adminId;
	
	@Column(name = "startDate")
	private Date startDate;
	
	@Column(name = "endDate")
	private Date endDate;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "topic")
	private String topic;

	public long getCampId() {
		return campId;
	}

	public void setCampId(long campId) {
		this.campId = campId;
	}

	public String getCampName() {
		return campName;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	
	
}
