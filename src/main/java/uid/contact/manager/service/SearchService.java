package uid.contact.manager.service;

import java.util.List;

import uid.contact.manager.dao.domain.Person;

public interface SearchService {
	
	public List<Person> searchContactsByName(String searchKey);
	public List<Person> searchContactsByPhone(String searchKey);
	public List<Person> searchContactsByNameWithStartingChar(String searchKey);
	public List<Person> searchContactsByPhoneWithStartingChar(String searchKey);
	public List<Person> searchContactsByBothName(String firstNameKey, String lastNameKey);
	public List<Person> searchContactsByAllName(String firstName, String lastName,String middleName);

}
