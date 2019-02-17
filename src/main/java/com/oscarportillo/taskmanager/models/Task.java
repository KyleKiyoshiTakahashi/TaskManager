package com.oscarportillo.taskmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tasks")
public class Task {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
	private Long id;

	// you will need to add validations to this.
	// @NotBlank(message="task name must not be blank")
	private String description;

	// you will need to add validations to this.
	// @NotBlank(message="task name must not be blank")
	private String priority;

	// creator is not required.  We will know who created it based off of the relationship to the user 
	private String Creator;

	// you will need to add validations to this.
	// @NotBlank(message="task name must not be blank")
	private String Assignee;

	 @Column(updatable=false)
    private Date createdAt;
	private Date updatedAt;
	
	// need to add the Pre persist and pre update
	// @PrePersist
	// protected void onCreate() {
	// 	this.createdAt = new Date();
	// }
	// @PreUpdate
	// protected void onUpdate() {
	// 	this.updatedAt = new Date();
	// }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    public Task() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getCreator() {
		return Creator;
	}

	public void setCreator(String creator) {
		Creator = creator;
	}

	public String getAssignee() {
		return Assignee;
	}

	public void setAssignee(String assignee) {
		Assignee = assignee;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


    
}
