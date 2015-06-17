package uid.contact.manager.file.format;

public class FieldFormat implements Comparable<FieldFormat> {
	
	private String fieldName;
	private int order;
	private int length;
	
	public FieldFormat(){};
	
	public FieldFormat(String fieldName, int order, int length)
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

	public int compareTo(FieldFormat o) {
		// TODO Auto-generated method stub
		return this.order-o.order;
	}
	
}
