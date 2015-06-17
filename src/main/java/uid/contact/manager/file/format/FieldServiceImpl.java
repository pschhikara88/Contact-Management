package uid.contact.manager.file.format;

import java.util.SortedSet;

import uid.contact.manager.constant.Constants;
import uid.contact.manager.execution.AppData;

public class FieldServiceImpl implements FieldService {
	
	public String getFieldValueForFile(FieldFormat fieldFormat, String fieldValueStoredInObject)
	{
		int fieldValueLength=fieldValueStoredInObject!=null?fieldValueStoredInObject.length():0;
		int countSpace= fieldFormat.getLength()-fieldValueLength;
		StringBuilder fieldvalue = new StringBuilder(fieldValueStoredInObject!=null?fieldValueStoredInObject:"");
		for(int i=0; i<countSpace;i++)
			fieldvalue.append(" ");
		return fieldvalue.toString();
	}
	
	public String getFieldValueForFile(int fieldLength, String fieldValueStoredInObject)
	{
		int fieldValueLength=fieldValueStoredInObject!=null?fieldValueStoredInObject.length():0;
		int countSpace= fieldLength-fieldValueLength;
		StringBuilder fieldvalue = new StringBuilder(fieldValueStoredInObject!=null?fieldValueStoredInObject:"");
		for(int i=0; i<countSpace;i++)
			fieldvalue.append(" ");
		return fieldvalue.toString();
	}
	
	public String getFieldValueForObject(String fieldValueStoredInFile)
	{
		return fieldValueStoredInFile.trim();
	}

	public String getFieldValueFromFileRecordString(int offset, int length,
			String recordString) {
		return recordString.substring(offset, offset+length).trim();
	}

	public String getFieldValueFromFileRecordString(int offset,
			FieldFormat fieldFormat, String recordString) {
		// TODO Auto-generated method stub
		return recordString.substring(offset, offset+fieldFormat.getLength());
	}

	public String getFieldValueFromFileRecordString(FieldFormat fieldFormat,
			String recordString) {
		FileRecordFormat fileRecordFormat = (FileRecordFormat)AppData.configDataMap.get(Constants.FILE_RECORD_FORMAT_KEY);
		SortedSet<FieldFormat> fieldFormatList =fileRecordFormat.getFieldFormatSet();
		int offset=0;
		for(FieldFormat currentFieldFormat : fieldFormatList)
		{
			if(currentFieldFormat.getFieldName().equals(fieldFormat.getFieldName()))
				return recordString.substring(offset, offset+fieldFormat.getLength());
			else
				offset = offset+ fieldFormat.getLength();	
		}
		return null;
	}
	

}
