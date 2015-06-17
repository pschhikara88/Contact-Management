package uid.contact.manager.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationProviderResolver;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import uid.contact.manager.dao.domain.Person;

public class Utils {
	
	public static List<Person> convertMapIntoArrayList(Map<String,Person> personMap)
	{
		List<Person> personList =  new ArrayList<Person>();
		if(personMap!=null && personMap.size()>0)
		{
			for(Map.Entry<String, Person> entry : personMap.entrySet()){
				personList.add(entry.getValue());
			}
			
		}
		return personList;
	}
	
	public static String convertDateToString(Date date)
	{
		DateFormat dateFormat = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
		return dateFormat.format(date);
	}
	
	public static String makeFirstLetterCapital(String line)
	{
		if(line.length()>0)
			return Character.toUpperCase(line.charAt(0)) + line.substring(1);
		else
			return "";
	}
	
	public static Date convertStringToDate(String dateString)
	{
		Date date= null;
		DateFormat dateFormat = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
		try {
			date=dateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static void main(String[] args) {
		Person p = new Person();
		p.setMiddleName("");
		//Configuration<?> config = Validation.byDefaultProvider().configure();
		/*config.messageInterpolator(new MessageInterpolator())
		    .traversableResolver( new TraversableResolver())
		    .constraintValidatorFactory(new ConstraintValidatorFactory());*/

	/*	ValidatorFactory factory = config.buildValidatorFactory();
		Validator validator = factory.getValidator();*/
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
/*		Configuration<?> configuration = Validation
		   .byDefaultProvider()
	  .providerResolver( ValidationProviderResolver )
		    .configure();
		ValidatorFactory factory = configuration.buildValidatorFactory();*/
		
		/*ValidatorFactory factory = Validation.getValidatorBuilder().build();
		//cache the factory somewhere
		Validator<Person> addressValidator = factory.getValidator(Person.class);*/

		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(p);
		
		for(ConstraintViolation<Person> constraintViolation :constraintViolations )
		{
			System.out.println(constraintViolation.getMessageTemplate());
			System.out.println(constraintViolation.getPropertyPath().toString());
		}
		String s= "Amabala%%%%Cantt";
		String [] sq = s.split("%%");
		System.out.println(sq[0]+sq[1].toString()+sq[2]);
		System.out.println("2   kjhfghfggdfg        gfbdbfdb            j          Mk,gghgdfg                          hjfghdfgfdg                        hdfgdfg                  nv65664564 76576756345          Added     System    Thu, Sep 25 2014 05:02:55     System    Thu, Sep 25 2014 05:02:55     ".length() + "  test");
		System.out.println(s.matches("[a-zA-Z-\\s.]+"));
		//System.out.println(s.matches("[a-zA-Z][a-zA-Z]"));
		//System.out.println(s.matches("[0-9]+"));
	//	System.out.println(s.matches("[a-zA-Z0-9!@\\s-@#./:,><|]+"));
		
		//System.out.println("1   esfefeafsf          sdfsdfsdf           b          Msdfsdfsdfsf                        sdfsdfsdf                          sdfsdfsdf                tt54543    45345234234          Added     System    Mon, Sep 22 2014 09:17:15     System    Mon, Sep 22 2014 09:17:15     ".length());
		//System.out.println(convertDateToString(new Date()));
		//System.out.println(convertStringToDate(convertDateToString(new Date())).toString());
	}
	
	

}
