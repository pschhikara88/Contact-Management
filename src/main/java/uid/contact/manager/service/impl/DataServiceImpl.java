package uid.contact.manager.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import uid.contact.manager.constant.Constants;
import uid.contact.manager.converter.ObjectRecordStringConvertor;
import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.execution.AppData;
import uid.contact.manager.factory.ApplicationSingeltonFactory;
import uid.contact.manager.io.service.FileIOService;
import uid.contact.manager.io.service.impl.RandomAccessFileIOServiceImpl;
import uid.contact.manager.service.DataService;
import uid.contact.manager.utility.Utils;

public class DataServiceImpl implements DataService{
	
	private FileIOService<RandomAccessFile> fileIOService;
	private ObjectRecordStringConvertor objectStringMapper;
	
	public DataServiceImpl() throws InstantiationException, IllegalAccessException
	{
		fileIOService = (FileIOService)ApplicationSingeltonFactory.getInstance(RandomAccessFileIOServiceImpl.class);
		objectStringMapper = (ObjectRecordStringConvertor)ApplicationSingeltonFactory.getInstance(ObjectRecordStringConvertor.class);
	}
	
	public  Map<String,Person> getAllContactsForSeekMainData() throws IOException
	{
		//List<Person> personList = new ArrayList<Person>();
		Map<String,Person> personMap = new TreeMap<String,Person>();
		RandomAccessFile file = new RandomAccessFile((File)AppData.configDataMap.get(Constants.CONTACT_FILE_OBJECT),"r");
		Map<Long, String> personRecordWithSeekMap = fileIOService.getAllRecords(file);
		Person person = null;
		if(personRecordWithSeekMap!=null && personRecordWithSeekMap.size()>0)
		for(Map.Entry<Long, String> entry : personRecordWithSeekMap.entrySet()){
		    //System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
			person = objectStringMapper.convertStringToPerson(entry.getValue());
			person.setSeek(entry.getKey());
			personMap.put(String.valueOf(entry.getKey()), person);
		}
		return personMap;
	}
	
	public  Map<String,Person> getAllContactsWithNameAsKey() throws IOException
	{
		Map<String,Person> personMap = new TreeMap<String,Person>();
		//List<Person> personList = new ArrayList<Person>();
		Map<String, Person> personRecordWithSeekMap = AppData.mainDataMap.get(Constants.MAIN_DATA_SEEK_KEY);
		Person person = null;
		if(personRecordWithSeekMap!=null && personRecordWithSeekMap.size()>0)
		{
			for(Map.Entry<String, Person> entry : personRecordWithSeekMap.entrySet()){
		    //System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
			//person = objectStringMapper.convertStringToPerson(entry.getValue());
		//	person.setSeek(Long.valueentry.getKey());
			person = entry.getValue();
			personMap.put(person.getFirstName().toLowerCase()+"%%"+(person.getMiddleName()!=null?person.getMiddleName().toLowerCase():"")+
					"%%"+person.getLastName().toLowerCase(), entry.getValue());
			}
		}
		return personMap;
	}
	
	public  Map<String,Person> getAllRecentContacts() throws IOException
	{
		Map<String,Person> personMap = new TreeMap<String,Person>(Collections.reverseOrder());
		//List<Person> personList = new ArrayList<Person>();
		Map<String, Person> personRecordWithSeekMap = AppData.mainDataMap.get(Constants.MAIN_DATA_SEEK_KEY);
		Person person = null;
		int i=1;
		if(personRecordWithSeekMap!=null && personRecordWithSeekMap.size()>0)
		{
			for(Map.Entry<String, Person> entry : personRecordWithSeekMap.entrySet()){
		    //System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
			//person = objectStringMapper.convertStringToPerson(entry.getValue());
		//	person.setSeek(Long.valueentry.getKey());
				person = entry.getValue();
				if(i>20)
					return personMap;
				personMap.put(String.valueOf(person.getUpdatedDate().getTime()),person);
				i++;
			}
			
		}
		return personMap;
	}
	
	public  Map<String,Person> getAllContactsByPhone() throws IOException
	{
		Map<String,Person> personMap = new TreeMap<String,Person>();
		//List<Person> personList = new ArrayList<Person>();
		Map<String, Person> personRecordWithSeekMap = AppData.mainDataMap.get(Constants.MAIN_DATA_SEEK_KEY);
		Person person = null;
		if(personRecordWithSeekMap!=null && personRecordWithSeekMap.size()>0)
		{
			for(Map.Entry<String, Person> entry : personRecordWithSeekMap.entrySet()){
		    //System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
			//person = objectStringMapper.convertStringToPerson(entry.getValue());
		//	person.setSeek(Long.valueentry.getKey());
				person = entry.getValue();
				personMap.put(person.getContactInfo().getPhoneNo(),person);
			}
			
		}
		return personMap;
	}

	@Override
	public Map<String,Person>  getAllContactsWithPersonIdentifierAsKey() {
		Long maxPersonIdentifer = 0L;
		Map<String,Person> personMap = new HashMap<String,Person>();
		//List<Person> personList = new ArrayList<Person>();
		Map<String, Person> personRecordWithPersonIdentifierMap = AppData.mainDataMap.get(Constants.MAIN_DATA_SEEK_KEY);
		Person person = null;
		if(personRecordWithPersonIdentifierMap!=null && personRecordWithPersonIdentifierMap.size()>0)
		{
			for(Map.Entry<String, Person> entry : personRecordWithPersonIdentifierMap.entrySet()){
			person = entry.getValue();
			if(maxPersonIdentifer<person.getPersonIdentifier())
				maxPersonIdentifer=person.getPersonIdentifier();
			personMap.put(String.valueOf(person.getPersonIdentifier()), entry.getValue());
			}
		}
		AppData.configDataMap.put(Constants.MAX_PERSON_IDENTIFIER,maxPersonIdentifer);
		return personMap;
	}

	@Override
	public Person getContactByPersonIdentifier(Long personIdentifier) {
		// TODO Auto-generated method stub
		return AppData.mainDataMap.get(Constants.MAIN_DATA_ID_KEY).get(String.valueOf(personIdentifier));
	}

}
