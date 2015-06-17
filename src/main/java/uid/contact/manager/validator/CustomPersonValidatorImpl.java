package uid.contact.manager.validator;

import java.util.Map;

import uid.contact.manager.constant.Constants;
import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.execution.AppData;

public class CustomPersonValidatorImpl implements CustomPersonValidator {

	@Override
	public boolean validateNameAsKey(Person person) {
		Map<String,Person> personListWithNameAsKey = AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY);
		String key = person.getFirstName()+"%%"+person.getMiddleName()+"%%"+person.getLastName();
		if(personListWithNameAsKey.containsKey(key))
			return false;
		return true;
	}

	@Override
	public boolean validateNameAsKeyForEdit(Person person) {
		Map<String,Person> personListWithNameAsKey = AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY);
		String key = person.getFirstName()+"%%"+person.getMiddleName()+"%%"+person.getLastName();
		Person comaparePerson =null;
		if(personListWithNameAsKey.containsKey(key))
		{
			comaparePerson = personListWithNameAsKey.get(key);
			if(comaparePerson.getPersonIdentifier().equals(person.getPersonIdentifier()))
				return true;
			else
				return false;
		}
		return true;
	}

}
