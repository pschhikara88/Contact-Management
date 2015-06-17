package uid.contact.manager.execution;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import uid.contact.manager.constant.Constants;
import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.enums.UserType;
import uid.contact.manager.factory.ApplicationSingeltonFactory;
import uid.contact.manager.file.format.FileFormatService;
import uid.contact.manager.file.format.FileFormatServiceImpl;
import uid.contact.manager.file.format.FileRecordFormat;
import uid.contact.manager.file.service.FileService;
import uid.contact.manager.file.service.impl.FileServiceImpl;
import uid.contact.manager.service.DataService;
import uid.contact.manager.service.impl.DataServiceImpl;

public class AppInitialization {
	
	private static final File defaultConfigFile = new File(Constants.DEFAULT_DIR, Constants.DEFAULT_CONFIG_FILE_NAME);
	
	private static final File defaultContactFile = new File(Constants.DEFAULT_DIR, Constants.DEFAULT_CONTACT_FILE_NAME);
	
	private  FileService fileService;
	private FileFormatService fileFormatService;
	//private FileIOService<RandomAccessFile> fileIOService;
	private DataService dataService;
	
	public AppInitialization() throws InstantiationException, IllegalAccessException
	{
		fileService = ApplicationSingeltonFactory.getInstance(FileServiceImpl.class);
		fileFormatService = ApplicationSingeltonFactory.getInstance(FileFormatServiceImpl.class);
		//fileIOService = ApplicationSingeltonFactory.getInstance(RandomAccessFileIOServiceImpl.class);
		dataService = ApplicationSingeltonFactory.getInstance(DataServiceImpl.class);
	}
	
	public void initApplication() throws IOException
	{
		if(!isDefaultDirectoryAndConfigfileExists())
			fileService.createConfigFile(defaultConfigFile);
		
		loadConfigurationProperties();	
		loadValidationMessageProperties();
		setFileFormatDataMapBasedOnConfiguration();
		setContactFileObjectInConfigDataMap();
		
		//can add functionality for changing location of contact file from the config file.

		if(!isDefaultContactfileExists())
			fileService.createFile(defaultContactFile);
		
		Map<String,Person> contactMap = dataService.getAllContactsForSeekMainData();
		setMainDataMapwithConatctBasedOnSeeKey(contactMap);// instantiate from some service
		Map<String,Person> contactMapWithNameKey = dataService.getAllContactsWithNameAsKey();
		setMainDataMapwithConatctBasedOnNameKey(contactMapWithNameKey);
		Map<String,Person> recentContactMap = dataService.getAllRecentContacts();
		setMainDataMapwithConatctBasedOnRecentContact(recentContactMap);
		Map<String,Person> phoneContactMap = dataService.getAllContactsByPhone();
		setMainDataMapwithConatctBasedOnPhone(phoneContactMap);
		Map<String,Person> pidContactMap = dataService.getAllContactsWithPersonIdentifierAsKey();
		setMainDataMapwithConatctBasedOnPersonalIdentifier(pidContactMap);
		
	}

	private  boolean isDefaultDirectoryAndConfigfileExists()
	{
		return fileService.isfileExists(defaultConfigFile);
	}
	
	private  boolean isDefaultContactfileExists()
	{
		return fileService.isfileExists(defaultContactFile);
	}
	
	private void loadValidationMessageProperties()
	{
	
		
		InputStream is = null;
		is = getClass().getClassLoader().getResourceAsStream("messages/validation_messages.properties");
		
		Properties props = new Properties();
		try {
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for(String propertyName : props.stringPropertyNames())
		{
			AppData.configPropertiesMap.put(propertyName,(String) props.get(propertyName));
		}
	}
	
	private void loadConfigurationProperties()
	{
		InputStream is = null;
		try {
				is = new FileInputStream(defaultConfigFile.getAbsolutePath());
		} catch (FileNotFoundException e) {
			System.out.println("Exception while instantiate input dstream for config file");
		}
		
		Properties props = new Properties();
		try {
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for(String propertyName : props.stringPropertyNames())
		{
			AppData.configPropertiesMap.put(propertyName,(String) props.get(propertyName));
		}
	}
	
	private void setFileFormatDataMapBasedOnConfiguration()
	{
		FileRecordFormat fileRecordFormat= null;
		if(AppData.configPropertiesMap.containsKey(Constants.USER_TYPE_KEY))
		{
			if(AppData.configPropertiesMap.get(Constants.USER_TYPE_KEY).equalsIgnoreCase(UserType.PERSONAL.getValue()))
			{
				fileRecordFormat = fileFormatService.getPersonTypeRecordFormat();
				AppData.configDataMap.put(Constants.FILE_RECORD_FORMAT_KEY, fileRecordFormat);
			}
			else if(AppData.configPropertiesMap.get(Constants.USER_TYPE_KEY).equalsIgnoreCase(UserType.SMALL_BUSINESS.getValue()))
			{
				fileRecordFormat = fileFormatService.getSmallOrganizationTypeRecordFormat();
				AppData.configDataMap.put(Constants.FILE_RECORD_FORMAT_KEY,fileRecordFormat );
			}
			else if(AppData.configPropertiesMap.get(Constants.USER_TYPE_KEY).equalsIgnoreCase(UserType.LARGE_BUSINESS.getValue()))
			{
				fileRecordFormat = fileFormatService.getLargeOrganizationTypeRecordFormat();
				AppData.configDataMap.put(Constants.FILE_RECORD_FORMAT_KEY,fileRecordFormat );
			}	
			AppData.configDataMap.put(Constants.RECORD_SIZE, fileRecordFormat.getRecordSize());
		}
	}
	
	private void setContactFileObjectInConfigDataMap()
	{
		AppData.configDataMap.put(Constants.CONTACT_FILE_OBJECT, defaultContactFile);
	}
	
	private void setMainDataMapwithConatctBasedOnSeeKey(Map<String,Person>  contactMap)
	{
		AppData.mainDataMap.put(Constants.MAIN_DATA_SEEK_KEY, contactMap);
	}
	
	private void setMainDataMapwithConatctBasedOnNameKey(Map<String,Person>  contactNameMap)
	{
		AppData.mainDataMap.put(Constants.MAIN_DATA_NAME_KEY, contactNameMap);
	}
	
	private void setMainDataMapwithConatctBasedOnRecentContact(Map<String,Person>  contactNameMap)
	{
		AppData.mainDataMap.put(Constants.MAIN_DATA_RECENT_KEY, contactNameMap);
	}
	
	private void setMainDataMapwithConatctBasedOnPhone(Map<String,Person>  contactNameMap)
	{
		AppData.mainDataMap.put(Constants.MAIN_DATA_PHONE_KEY, contactNameMap);
	}
	
	private void setMainDataMapwithConatctBasedOnPersonalIdentifier(Map<String,Person>  contactIDMap)
	{
		AppData.mainDataMap.put(Constants.MAIN_DATA_ID_KEY, contactIDMap);
	}
}
