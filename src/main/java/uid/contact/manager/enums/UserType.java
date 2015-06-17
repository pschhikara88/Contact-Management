package uid.contact.manager.enums;

public enum UserType {
	
	PERSONAL("personal"),
	SMALL_BUSINESS("small-business"),
	LARGE_BUSINESS("large-business");
	
	private String value;
	
	private UserType(String value)
	{
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
