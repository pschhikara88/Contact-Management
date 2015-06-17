package uid.contact.manager.dao.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import uid.contact.manager.dao.domain.Person;

public interface PersonDAO {
	
	public Person addPerson(Person person) throws IOException, FileNotFoundException;
	public Person editPerson(Person person) throws IOException, FileNotFoundException;
	public void deletePerson(Person person) throws IOException, FileNotFoundException;

}
