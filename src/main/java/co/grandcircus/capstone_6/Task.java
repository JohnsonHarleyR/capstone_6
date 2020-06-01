package co.grandcircus.capstone_6;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User user;
	private String description;
	private LocalDate date;
	private int complete = 0;
	
	public Task() {
		
	}
	
	public Task(User user, String description, LocalDate date) {
		this.user = user;
		this.description = description;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getComplete() {
		return complete;
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}

	@Override
	public String toString() {
		
		//output true or false instead of 1 or 0 for complete
		Boolean isComplete;
		if (complete == 1) {
			isComplete = true;
		} else {
			isComplete = false;
		}
		
		return "User [id=" + id + ", userId=" + user.getId() + ", description=" + description +
				", complete=" + isComplete + "]";
	}
	
	
}
