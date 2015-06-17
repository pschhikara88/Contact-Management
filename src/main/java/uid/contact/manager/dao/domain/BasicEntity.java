package uid.contact.manager.dao.domain;

import java.util.Date;

public class BasicEntity {
	
	protected long seek;
	protected Date createdDate;
	protected String createdBy;
	protected String updatedBy;
	protected Date updatedDate;
	protected String lastActionPerformed;
	
	public long getSeek() {
		return seek;
	}
	public void setSeek(long seek) {
		this.seek = seek;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getLastActionPerformed() {
		return lastActionPerformed;
	}
	public void setLastActionPerformed(String lastActionPerformed) {
		this.lastActionPerformed = lastActionPerformed;
	}
	
	
	
	

}
