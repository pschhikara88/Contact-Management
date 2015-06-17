package uid.contact.manager.io.service.impl;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

import uid.contact.manager.constant.Constants;
import uid.contact.manager.execution.AppData;
import uid.contact.manager.io.service.FileIOService;

public class RandomAccessFileIOServiceImpl implements FileIOService<RandomAccessFile> {

	public long insert(RandomAccessFile file, String newRecord) throws IOException {
	    long seek=file.length();
	    file.seek(seek);
	    if(seek==0)
	    	file.writeBytes(newRecord);
	    else
	    	file.writeBytes("\r\n"+newRecord);
	    
	    file.close();
	    return seek+2;
	}

	public void update(RandomAccessFile file, String updatedRecord,long seek ) throws IOException{
	    file.seek(seek);
	    file.writeBytes(updatedRecord);
	    file.close();
	}

	public void delete(RandomAccessFile file, String record) {
		// TODO Auto-generated method stub
		
	}

	public long delete(RandomAccessFile file, long recordSeek) throws IOException {
		
		AppData.mainDataMap.get(Constants.MAIN_DATA_SEEK_KEY);
		if((file.length()-(Integer)AppData.configDataMap.get(Constants.RECORD_SIZE))<=0)
    	{	
			file.setLength(0);
			return -1;
    	}
		/*if(((recordSeek+((Integer)AppData.configDataMap.get(Constants.RECORD_SIZE))))==file.length())
		{	
			 file.setLength(0);
			return;
		}*/
		long lastSeek=file.length()-((Integer)AppData.configDataMap.get(Constants.RECORD_SIZE));
		if(lastSeek==recordSeek)
		{
			file.setLength(file.length()-((Integer)AppData.configDataMap.get(Constants.RECORD_SIZE))-2);
			return -1;
		}
		file.seek(lastSeek);
		String lastRecord = file.readLine();
		file.seek(recordSeek);
        file.write(lastRecord.getBytes());      
        file.setLength(file.length()-((Integer)AppData.configDataMap.get(Constants.RECORD_SIZE))-2);
        file.close();
        return lastSeek;
	}

	public Map<Long,String> getAllRecords(RandomAccessFile file) throws IOException {
		Map<Long,String> stringMap = new  HashMap<Long,String>();
		Long currentSeek=0L;
	    file.seek(currentSeek);
	    String record=null;
	    while((record=file.readLine())!=null)
	    {    
	    	
	    	stringMap.put(currentSeek,record);
	    	currentSeek = file.getFilePointer();	
	    }
	    System.out.println(stringMap);
	    file.close();
	    return stringMap;
	}
}
