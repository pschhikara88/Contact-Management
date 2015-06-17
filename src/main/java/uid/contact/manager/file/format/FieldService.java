package uid.contact.manager.file.format;

public interface FieldService {

	public String getFieldValueForFile(FieldFormat fieldFormat, String fieldValueStoredInObject);
	
	public String getFieldValueForFile(int fieldLength, String fieldValueStoredInObject);
	
	public String getFieldValueForObject(String fieldValueStoredInFile);
	
	public String getFieldValueFromFileRecordString(int offset,FieldFormat fieldFormat, String recordString);
	
	public String getFieldValueFromFileRecordString(int offset, int length,
			String recordString);
	
	public String getFieldValueFromFileRecordString(FieldFormat fieldFormat, String recordString);
}
