package uid.contact.manager.dao.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class ContactInfo {
	
	@NotNull
	@Pattern(regexp="[0-9]+")
	@Size(min=4, max=21)
	private String phoneNo;
	
	@NotNull
	@Pattern(regexp="[a-zA-Z0-9!'\\s-_@#./:,|]+")
	@Size(min=1, max=21)
	private String addressLine1;
	
	@Pattern(regexp="^$|[a-zA-Z0-9!'\\s-_@#./:,|]+")
	@Size(min=0, max=21)
	private String addressLine2;
	
	@NotNull
	@Pattern(regexp="[0-9]+")
	@Size(min=1, max=9)
	private String zipCode;
	
	@NotNull
	@Pattern(regexp="[a-zA-Z-\\s.]+")
	@Size(min=1, max=25)
	private String city;
	
	@NotNull
	@Pattern(regexp="[a-zA-Z][a-zA-Z]")
	@Size(min=2, max=2)
	private String state;
	
	@NotNull
	@Pattern(regexp="[a-zA-Z-\\s]+")
	@Size(min=1, max=30)
	private String country;
	
	@NotNull
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	@Size(min=1, max=100)
	private String email;
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	



}
