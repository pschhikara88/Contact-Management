package uid.contact.manager.service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import uid.contact.manager.constant.Constants;
import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.execution.AppData;

public interface DataService {
	
	public Map<String,Person> getAllContactsForSeekMainData() throws IOException;
	public  Map<String,Person> getAllContactsWithNameAsKey() throws IOException;
	public  Map<String,Person> getAllRecentContacts() throws IOException;
	public  Map<String,Person> getAllContactsByPhone() throws IOException;
	public Map<String,Person>  getAllContactsWithPersonIdentifierAsKey();
	public Person getContactByPersonIdentifier(Long personIdentifier);
	

}
