package uid.contact.manager.execution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Validation;
import javax.validation.Validator;

import uid.contact.manager.dao.domain.Person;


public class AppData {
	
	public static ArrayList<Person> contactPersonList = new ArrayList<Person>();
	
	public static final Map<String,String> configPropertiesMap = new HashMap<String,String>();
	
	public static final Map<String,Object> configDataMap = new HashMap<String,Object>();
	
	public static final Map<String,Map<String,Person>> mainDataMap= new HashMap<String,Map<String,Person>>();


	/*public static ArrayList<Person> getContactPersonList() {
		return contactPersonList;
	}

	public static void setContactPersonList(ArrayList<Person> contactPersonList) {
		AppData.contactPersonList = contactPersonList;
	}
	*/

}
