package uid.contact.manager.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import uid.contact.manager.dao.domain.Person;

public interface PersonService {
	
	public Person save(Person person) throws FileNotFoundException, IOException;
	public Person update(Person person) throws FileNotFoundException, IOException;
	public void delete(Person person) throws FileNotFoundException, IOException;
	public void restore(Person person) throws FileNotFoundException, IOException;
	public void deleteRecordFromDataBase() throws FileNotFoundException, IOException;

}
