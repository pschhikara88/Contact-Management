package uid.contact.manager.file.format;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class FileRecordFormat {
	
	private SortedSet<FieldFormat> fieldFormatSet;
	
	private int recordSize;

	public SortedSet<FieldFormat> getFieldFormatSet() {
		return fieldFormatSet;
	}

	public void setFieldFormatSet(SortedSet<FieldFormat> fieldFormatSet) {
		this.fieldFormatSet = fieldFormatSet;
	}

	public int getRecordSize() {
		int recordSize =0;
		for(FieldFormat fieldFormat : fieldFormatSet)
		{
			recordSize = recordSize+fieldFormat.getLength();
		}
		return recordSize;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}	

}
