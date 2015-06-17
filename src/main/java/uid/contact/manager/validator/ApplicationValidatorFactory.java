package uid.contact.manager.validator;

import javax.validation.Validation;
import javax.validation.Validator;

public class ApplicationValidatorFactory {

	public static final Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();
}
