package uid.contact.manager.dao.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

import uid.contact.manager.constant.Constants;
import uid.contact.manager.converter.ObjectRecordStringConvertor;
import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.dao.service.PersonDAO;
import uid.contact.manager.execution.AppData;
import uid.contact.manager.factory.ApplicationSingeltonFactory;
import uid.contact.manager.io.service.FileIOService;
import uid.contact.manager.io.service.impl.RandomAccessFileIOServiceImpl;

public class PersonDAOImpl implements PersonDAO {
	
	private FileIOService<RandomAccessFile> fileIOService;
	private ObjectRecordStringConvertor objectStringMapper;
	
	public PersonDAOImpl() throws InstantiationException, IllegalAccessException
	{
		fileIOService = ApplicationSingeltonFactory.getInstance(RandomAccessFileIOServiceImpl.class);
		objectStringMapper = ApplicationSingeltonFactory.getInstance(ObjectRecordStringConvertor.class);
	}

	public Person addPerson(Person person) throws IOException, FileNotFoundException {
		String recordString = objectStringMapper.convertPersonToString(person);
		RandomAccessFile file = new RandomAccessFile((File)AppData.configDataMap.get(Constants.CONTACT_FILE_OBJECT),"rw");
		long seek = fileIOService.insert(file, recordString);
		person.setSeek(seek);
		return person;
	}

	public Person editPerson(Person person) throws IOException, FileNotFoundException {
		String recordString = objectStringMapper.convertPersonToString(person);
		RandomAccessFile file = new RandomAccessFile((File)AppData.configDataMap.get(Constants.CONTACT_FILE_OBJECT),"rw");
		fileIOService.update(file, recordString,person.getSeek());
		return person;
	}

	public void deletePerson(Person person) throws IOException, FileNotFoundException {
		RandomAccessFile file = new RandomAccessFile((File)AppData.configDataMap.get(Constants.CONTACT_FILE_OBJECT),"rw");
		Map<String,Person> personMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_SEEK_KEY);
		Person deletePerson=searchByPersonalIdentiferInSeekMap(person);
		long lastSeek=-1;
		if(deletePerson!=null)
			lastSeek =fileIOService.delete(file,deletePerson.getSeek());
		if(!(lastSeek<0))
		{
			personMap.get(String.valueOf(lastSeek)).setSeek(person.getSeek());
		}
	}
	
	private Person searchByPersonalIdentiferInSeekMap(Person person)
	{
		Map<String,Person> personMap = (Map<String,Person>)AppData.mainDataMap.get(Constants.MAIN_DATA_SEEK_KEY);
		Person currentPerson = null;
		for(Map.Entry<String, Person> entry : personMap.entrySet()){
			currentPerson = entry.getValue();
			if(currentPerson.getPersonIdentifier()==person.getPersonIdentifier())
				return currentPerson;
		}
		return null;
		
	}

}
