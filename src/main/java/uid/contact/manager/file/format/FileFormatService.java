package uid.contact.manager.file.format;

public interface FileFormatService {
	
	public FileRecordFormat getPersonTypeRecordFormat();
	
	public FileRecordFormat getSmallOrganizationTypeRecordFormat();
	
	public FileRecordFormat getLargeOrganizationTypeRecordFormat();
	
}
