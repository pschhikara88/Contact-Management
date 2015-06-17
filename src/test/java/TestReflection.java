import java.lang.reflect.Field;

import uid.contact.manager.dao.domain.Person;


public class TestReflection {

	public static void main(String[] args) {
		Person p = new Person();
		p.setPersonIdentifier(12333L);
		Class instanceClass = p.getClass();
		Field currentField=null;
		String fieldValue=null;
		try {
			currentField = instanceClass.getField("personIdentifier");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			System.out.println("Field doesnt exist with name : "+"personIdentifier");
			e.printStackTrace();
		}
		if(currentField!=null)
		{
			try {
				fieldValue = (String)currentField.get(p);
				System.out.println(fieldValue);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		//return fieldValue;
	}
}
