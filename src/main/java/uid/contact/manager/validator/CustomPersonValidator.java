package uid.contact.manager.validator;

import uid.contact.manager.dao.domain.Person;

public interface CustomPersonValidator {
	
	public boolean validateNameAsKey(Person person);
	
	public boolean validateNameAsKeyForEdit(Person person);

}
