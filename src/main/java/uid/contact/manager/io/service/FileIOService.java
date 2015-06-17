package uid.contact.manager.io.service;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Map;

public interface FileIOService<T> {
	
	public long insert(T file, String newRecord) throws IOException;
	public void update(T file, String updatedRecord, long seek) throws IOException;
	public void delete(T file ,String record);
	public long delete(T file ,long recordSeek) throws IOException;
	public Map<Long,String> getAllRecords(T file) throws IOException ;
}
