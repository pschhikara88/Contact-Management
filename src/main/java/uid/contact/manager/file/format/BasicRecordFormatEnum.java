package uid.contact.manager.file.format;

import java.util.Date;

public enum BasicRecordFormatEnum
{
	PERSON_IDENTIFIER("personIdentifier",1,4),
	LAST_NAME("lastName",2,20),
	FIRST_NAME("firstName",3,20),
	MIDDLE_INITIAL("middleName",4,1),
	DOB("dob",5,10),
	GENDER("gender",6,1),
	ADDRESS_LINE1("contact.addressLine1",7,35),
	ADDRESS_LINE2("contact.addressLine2",8,35),
	CITY("contact.city",9,25),
	STATE("contact.state",10,2),
	ZIPCODE("contact.zipCode",11,9),
	PHONE_NUMBER("contact.phoneNo",12,21),
	COUNTRY("contact.country",13,30),
	EMAIL("contact.email",14,100),
	LAST_ACTION_PERFORMED("lastActionPerformed",15,10),
	UPDATED_BY("updatedBy",16,10),
	UPDATED_DATE("updatedDate",17,30),
	CREATED_BY("createdBy",18,10),
	CREATED_DATE("createdDate",19,30);
	
	

	
	private String fieldName;
	private int order;
	private int length;
	
	private BasicRecordFormatEnum(String fieldName, int order, int length)
	{
		this.fieldName = fieldName;
		this.order = order;
		this.length = length;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	

}
