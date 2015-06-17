package uid.contact.manager.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import uid.contact.manager.constant.Constants;
import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.dao.service.PersonDAO;
import uid.contact.manager.dao.service.impl.PersonDAOImpl;
import uid.contact.manager.execution.AppData;
import uid.contact.manager.factory.ApplicationSingeltonFactory;
import uid.contact.manager.service.PersonService;

public class PersonServiceImpl implements PersonService {

	private PersonDAO personDAO;
	
	public PersonServiceImpl() throws InstantiationException, IllegalAccessException
	{
		personDAO = (PersonDAO)ApplicationSingeltonFactory.getInstance(PersonDAOImpl.class);
	}
	
	public Person save(Person person) throws FileNotFoundException, IOException {
		Map<String,Person> personMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_SEEK_KEY);
		Map<String,Person> personNameMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY);
		Map<String,Person> personRecentMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_RECENT_KEY);
		Map<String,Person> personIDMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_ID_KEY);
		if(AppData.configDataMap.get(Constants.MAX_PERSON_IDENTIFIER)!=null)
			person.setPersonIdentifier((Long)AppData.configDataMap.get(Constants.MAX_PERSON_IDENTIFIER)+1);
		else
			person.setPersonIdentifier(1L);
		AppData.configDataMap.put(Constants.MAX_PERSON_IDENTIFIER,person.getPersonIdentifier());
		setAuditFieldForAdd(person);
		person = personDAO.addPerson(person);
		personMap.put(String.valueOf(person.getSeek()),person);
		personNameMap.put(person.getFirstName()+"%%"+person.getMiddleName()+"%%"+person.getLastName(), person);
		personRecentMap.put(String.valueOf(person.getUpdatedDate().getTime()), person);
		/*if(personRecentMap.size()>20)
		{
			String key = ((TreeMap<String,Person>)personRecentMap).lastKey();
			personRecentMap.remove(key);
		}*/
		personIDMap.put(String.valueOf(person.getPersonIdentifier()), person);
		return person;
	}

	public Person update(Person person) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Map<String,Person> personMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_SEEK_KEY);
		Map<String,Person> personNameMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY);
		Map<String,Person> personRecentMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_RECENT_KEY);
		Map<String,Person> personIDMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_ID_KEY);
		setAuditFieldForEdit(person);
		person = personDAO.editPerson(person);
		personMap.put(String.valueOf(person.getSeek()), person);
		replaceEditedPersonInNameKeyMap(personNameMap,person);
		replaceEditedPersonInRecentMap(personRecentMap,person);
		/*if(personRecentMap.size()>20)
		{
			String key = ((TreeMap<String,Person>)personRecentMap).lastKey();
			personRecentMap.remove(key);
		}*/
		personIDMap.put(String.valueOf(person.getPersonIdentifier()), person);
		return person;	
	}

	public void delete(Person person) throws FileNotFoundException, IOException {
		//personDAO.deletePerson(person);
		setAuditFieldForDelete(person);
		Map<String,Person> personMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_SEEK_KEY);
		Map<String,Person> personNameMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY);
		Map<String,Person> personRecentMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_RECENT_KEY);
		Map<String,Person> personIDMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_ID_KEY);
		setAuditFieldForDelete(person);
		personMap.put(String.valueOf(person.getSeek()), person);
		personNameMap.remove(person.getFirstName()+"%%"+person.getMiddleName()+"%%"+person.getLastName());
		replaceEditedPersonInRecentMap(personRecentMap,person);
		/*if(personRecentMap.size()>20)
		{
			String key = ((TreeMap<String,Person>)personRecentMap).lastKey();
			personRecentMap.remove(key);
		}*/
		personIDMap.put(String.valueOf(person.getPersonIdentifier()), person);
		
	}
	
	@Override
	public void restore(Person person) throws FileNotFoundException,
			IOException {
		// TODO Auto-generated method stub
		setAuditFieldForDelete(person);
		Map<String,Person> personMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_SEEK_KEY);
		Map<String,Person> personNameMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY);
		Map<String,Person> personRecentMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_RECENT_KEY);
		Map<String,Person> personIDMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_ID_KEY);
		setAuditFieldForRestore(person);
		personMap.put(String.valueOf(person.getSeek()), person);
		personNameMap.put(person.getFirstName()+"%%"+person.getMiddleName()+"%%"+person.getLastName(), person);
		replaceEditedPersonInRecentMap(personRecentMap,person);
		/*if(personRecentMap.size()>20)
		{
			String key = ((TreeMap<String,Person>)personRecentMap).lastKey();
			personRecentMap.remove(key);
		}*/
		personIDMap.put(String.valueOf(person.getPersonIdentifier()), person);
	}
	
	private void setAuditFieldForAdd(Person person)
	{
		person.setCreatedBy("System");
		person.setCreatedDate(new Date());
		person.setUpdatedBy("System");
		person.setUpdatedDate(new Date());
		person.setLastActionPerformed("Added");
	}
	
	private void setAuditFieldForEdit(Person person)
	{
		person.setUpdatedBy("System");
		person.setUpdatedDate(new Date());
		person.setLastActionPerformed("Edited");
	}
	
	private void setAuditFieldForDelete(Person person)
	{
		person.setUpdatedBy("System");
		person.setUpdatedDate(new Date());
		person.setLastActionPerformed("Deleted");
	}
	
	private void setAuditFieldForRestore(Person person)
	{
		person.setUpdatedBy("System");
		person.setUpdatedDate(new Date());
		person.setLastActionPerformed("Restored");
	}
	
	private void replaceEditedPersonInNameKeyMap(Map<String,Person> personMap, Person person)
	{
		Person currentPerson= null;
		String removeKey = null;
		for(Map.Entry<String, Person> entry : personMap.entrySet()){
			currentPerson = entry.getValue();
			if(person.getPersonIdentifier()==currentPerson.getPersonIdentifier())
			{
				removeKey =entry.getKey();
			}
		}
		if(removeKey!=null)
		{
			personMap.remove(removeKey);
			personMap.put(person.getFirstName()+"%%"+person.getMiddleName()+"%%"+person.getLastName(), person);
		}
			
	}
	
	private void replaceEditedPersonInRecentMap(Map<String,Person> personMap, Person person)
	{
		Person currentPerson= null;
		String removeKey = null;
		for(Map.Entry<String, Person> entry : personMap.entrySet()){
			currentPerson = entry.getValue();
			if(person.getPersonIdentifier()==currentPerson.getPersonIdentifier())
			{
				removeKey =entry.getKey();
			}
		}
		if(removeKey!=null)
		{
			personMap.remove(removeKey);
		}
		personMap.put(String.valueOf(person.getUpdatedDate().getTime()), person);	
	}

	@Override
	public void deleteRecordFromDataBase() throws FileNotFoundException,
			IOException {
		Map<String,Person> personRecentMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_RECENT_KEY);
		Person person = null;
		for(Map.Entry<String, Person> entry : personRecentMap.entrySet()){
			person = entry.getValue();
			if(person.getLastActionPerformed().equals("Deleted"))
				personDAO.deletePerson(person);
		}
	}

	

}
