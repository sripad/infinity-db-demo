/**
 * 
 */
package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * @author Sripad
 *
 */
public abstract class InfinityDBModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	Long id;

	@Id
	Long eventId;

	@CreatedDate
	private Date createdDateTime;

	@LastModifiedDate
	private Date lastModifiedDateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

}
