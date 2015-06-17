package uid.contact.manager.converter;

import java.beans.Statement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.SortedSet;

import uid.contact.manager.constant.Constants;
import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.enums.Gender;
import uid.contact.manager.execution.AppData;
import uid.contact.manager.factory.ApplicationSingeltonFactory;
import uid.contact.manager.file.format.FieldFormat;
import uid.contact.manager.file.format.FieldService;
import uid.contact.manager.file.format.FieldServiceImpl;
import uid.contact.manager.file.format.FileRecordFormat;
import uid.contact.manager.utility.Utils;

public class ObjectRecordStringConvertor {
	
	private FieldService fieldService;
	
	public ObjectRecordStringConvertor() throws InstantiationException, IllegalAccessException
	{
		fieldService = (FieldService)ApplicationSingeltonFactory.getInstance(FieldServiceImpl.class);
	}
	
	public String  convertPersonToString(Person person)
	{
		StringBuilder record=new StringBuilder();
		FileRecordFormat fileRecordFormat = (FileRecordFormat)AppData.configDataMap.get(Constants.FILE_RECORD_FORMAT_KEY);
		SortedSet<FieldFormat> fieldFormatList =fileRecordFormat.getFieldFormatSet();
		
		Field currentField = null;
		//Method[] method = instanceClass.getMethods();
		String fiedValue=null;
		String [] fieldPart = null;
		for(FieldFormat fieldFormat : fieldFormatList)
		{
			fieldPart= fieldFormat.getFieldName().split("\\.");
			if(fieldPart.length==2)
			{
					fiedValue = getFieldValueByReflection(person.getContactInfo(),fieldPart[1]);		
			}
			else
			{
				fiedValue = getFieldValueByReflection(person,fieldFormat.getFieldName());
			}
			record.append(fieldService.getFieldValueForFile(fieldFormat , fiedValue));
		}
		return record.toString();
			
	}
	
	public Person  convertStringToPerson(String recordString)
	{
		Person person=new Person();
		
		FileRecordFormat fileRecordFormat = (FileRecordFormat)AppData.configDataMap.get(Constants.FILE_RECORD_FORMAT_KEY);
		SortedSet<FieldFormat> fieldFormatList =fileRecordFormat.getFieldFormatSet();
		
		Field currentField = null;
		//Method[] method = instanceClass.getMethods();
		String fiedValue=null;
		String [] fieldPart = null;
		int offset=0;
		String fieldValue=null;
		for(FieldFormat fieldFormat : fieldFormatList)
		{
			fieldPart= fieldFormat.getFieldName().split("\\.");
			fieldValue = fieldService.getFieldValueFromFileRecordString(offset, fieldFormat.getLength(), recordString);
			if(fieldPart.length==2)
			{
				if(fieldPart[0].equals("contact"))
					setFieldValueByReflection(person.getContactInfo(),fieldPart[1],fieldValue);
				else
				setFieldValueByReflection(person.getContactInfo(),fieldPart[1],fieldValue);
			}
			else
			{
				setFieldValueByReflection(person,fieldFormat.getFieldName(),fieldValue);
			}
			offset = offset + fieldFormat.getLength();
		}
			
		return person;
			
	}
		
		
		

		private String getFieldValueByReflection(Object obj,String fieldName)
		{
			Class instanceClass = obj.getClass();
			Field currentField=null;
			String fieldValue=null;
			try {
				currentField = instanceClass.getDeclaredField(fieldName);
				currentField.setAccessible(true);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				System.out.println("Field doesnt exist with name : "+fieldName);
				
			}
			if(currentField!=null)
			{
				try {
					if(currentField.get(obj) instanceof Gender)
						fieldValue =((Gender) currentField.get(obj)).getValue();
					else if(currentField.get(obj) instanceof Date)
					{	
						Date date = (Date) currentField.get(obj);
						fieldValue = Utils.convertDateToString(date);
						//fieldValue =((Date) currentField.get(obj)).toString();
					}
					else if(currentField.get(obj) instanceof Long)
						fieldValue =((Long) currentField.get(obj)).toString();
					else
						fieldValue = (String)currentField.get(obj);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return fieldValue;

		}
		
		private void setFieldValueByReflection(Object obj, String fieldName, String fieldValue)
		{
			/*Class instanceClass = obj.getClass();
			Method method=null;
				try {
					method = instanceClass.getMethod("set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length()));
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			
			if(method!=null)
			{
					try {
						method.invoke(obj,fieldValue);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
			}*/
			Statement stmt=null;
			Date date=null;
			if(fieldName.endsWith("Date") || fieldName.equals("dob"))
			{
				Date d= null;
				if((!fieldValue.equals("")) && fieldValue!=null )
				{
					d= Utils.convertStringToDate(fieldValue);
				
				}
				stmt=new Statement(obj, "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length()), new Object[]{d});
				
			}
			else if(fieldName.equals("gender"))
			{
				Gender gender = fieldValue.equals("M")? Gender.MALE:Gender.FEMALE;
				stmt=new Statement(obj, "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length()), new Object[]{gender});
			}
			else if(fieldName.equals("personIdentifier"))
			{
				Long longValue = Long.valueOf(fieldValue);
				stmt=new Statement(obj, "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length()), new Object[]{longValue});
			}
			else
				stmt=new Statement(obj, "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length()), new Object[]{fieldValue});
			try {
				stmt.execute();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
}
