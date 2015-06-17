package uid.contact.manager.enums;

public enum Gender {
	
	MALE("M"),
	FEMALE("F");
	
	private final String value;
	
	Gender(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}

}
