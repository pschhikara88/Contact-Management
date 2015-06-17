package uid.contact.manager.dao.domain;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import uid.contact.manager.enums.Gender;


public class Person {
	
	private Long personIdentifier;
	
	@NotNull
	@Pattern(regexp="[a-zA-Z]+")
	@Size(min=1, max=20)
	private String lastName;
	
	@NotNull
	@Pattern(regexp="[a-zA-Z]+")
	@Size(min=1, max=20)
	private String firstName;
	
	@Pattern(regexp="^$|[a-zA-Z]")
	@Size(min=0, max=1)
	private String middleName;
	private Gender gender;
	
	private String dob;
	@Valid
	private ContactInfo contactInfo;
	private long seek;
	private Date createdDate;
	private String createdBy;
	private String updatedBy;
	private Date updatedDate;
	private String lastActionPerformed;
	
	private void init()
	{
		contactInfo = new ContactInfo();
	}
	
	public Person()
	{
		init();
	}
	
	public Long getPersonIdentifier() {
		return personIdentifier;
	}
	public void setPersonIdentifier(Long personIdentifier) {
		this.personIdentifier = personIdentifier;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

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
