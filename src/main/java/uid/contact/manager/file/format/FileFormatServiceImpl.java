package uid.contact.manager.file.format;

import java.util.SortedSet;
import java.util.TreeSet;

public class FileFormatServiceImpl implements FileFormatService{

	public FileRecordFormat getPersonTypeRecordFormat() {
		// TODO Auto-generated method stub
		return getBasicRecordFormat();
	}

	public FileRecordFormat getSmallOrganizationTypeRecordFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	public FileRecordFormat getLargeOrganizationTypeRecordFormat() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private FileRecordFormat getBasicRecordFormat()
	{
		SortedSet<FieldFormat> fieldFormatSet = new TreeSet<FieldFormat>();
		FieldFormat fieldFormat = null;
		for(BasicRecordFormatEnum enumVal : BasicRecordFormatEnum.values())
		{
			fieldFormat = new FieldFormat(enumVal.getFieldName(),enumVal.getOrder(),enumVal.getLength());
			fieldFormatSet.add(fieldFormat);
		}
		FileRecordFormat fileRecordFormat = new FileRecordFormat();
		fileRecordFormat.setFieldFormatSet(fieldFormatSet);
		return fileRecordFormat;
	}


}
