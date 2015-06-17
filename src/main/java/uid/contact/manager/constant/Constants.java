package uid.contact.manager.constant;

import java.io.File;

public class Constants {
	
	public static final String USER_HOME = System.getProperty("user.home");
	
	public static final String FILE_FOLDER = "Contact";
	
	public static final String DEFAULT_DIR = USER_HOME + File.separator + FILE_FOLDER ;
	
	public static final String DEFAULT_CONTACT_FILE_NAME= "contacts.txt";
	
	public static final String DEFAULT_CONFIG_FILE_NAME= "config.properties";
	
	/* start config properties key*/
	public static final String USER_CONTACT_DIR_KEY = "user_dir";
	public static final String USER_TYPE_KEY = "user_type";
	
	
	/* end config properties key*/
	
	/* start config data key*/
	public static final String FILE_RECORD_FORMAT_KEY = "file_record_format";
	public static final String CONTACT_FILE_OBJECT = "file_object";
	public static final String RECORD_SIZE = "record_size";
	public static final String MAX_PERSON_IDENTIFIER = "MAX_PERSON_IDENTIFIER";
	
	
	/* end config data key*/
	
	/* start main data app key*/
	public static final String MAIN_DATA_SEEK_KEY = "seek";
	public static final String MAIN_DATA_NAME_KEY = "name";
	public static final String MAIN_DATA_RECENT_KEY = "recent";
	public static final String MAIN_DATA_PHONE_KEY = "phone";
	public static final String MAIN_DATA_ID_KEY = "pid";

	/*end main data app key*/
	
	
	
	public static void main(String args[])
	{
		System.out.println();
	}
}
