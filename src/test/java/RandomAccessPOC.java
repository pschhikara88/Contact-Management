import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import uid.contact.manager.constant.Constants;
import uid.contact.manager.execution.AppData;


public class RandomAccessPOC {

		    static final String FILEPATH = "C:/Users/SRPOP/Desktop/test11.txt";

		    public static void main(String[] args) 
		    {
		    	try {
		         
		           // writeToFile(FILEPATH, "JavaCodeGeeks Rocks!", 22);
		          // readFile(FILEPATH);
		    	//addString(FILEPATH,"\r\ndfdsfdfdfsdfsd");
		    		RandomAccessFile file = new RandomAccessFile(FILEPATH, "rw");
		    		delete(file,7);
		    	// System.out.println(new String(readFromFile(FILEPATH, 150, 23)));
		    	} catch (IOException e) {
		            e.printStackTrace();
		        }
		    }

		    private static byte[] readFromFile(String filePath, int position, int size)
		            throws IOException 
		    {
		        /*
		        file.seek(position);
		        byte[] bytes = new byte[size];
		        file.read(bytes);
		        file.close();*/
		    	RandomAccessFile file = new RandomAccessFile(filePath, "rw");
		    	 byte[] bytes = new byte[size];
		    	 long seek = 0;
		    	 long deleteSeek=0;
		    	 long length = file.length();
		    	 int i=0;
		    	 String s=null;
		    	 while(seek < length) { 
		    		 System.out.println(seek);
		    		 if(i==1)
		    			 deleteSeek =seek;
		    	   s = file.readLine();
		    	   seek = file.getFilePointer();
		    	   
		    	   i++;
		          }
		    	 System.out.println("length " + file.length());
		    	 
		    	// writeToFile(FILEPATH,s , deleteSeek);
		    	 //file.setLength(length-16);
		    	 
		    	 System.out.println("length " + file.length());
		        return bytes;
		    }
		    private static void writeToFile(String filePath, String data, long position)
		            throws IOException {
		        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
		        file.seek(position);
		        file.write(data.getBytes());
		        file.close();
		    }
		    
		    private static List<String> readFile(String filePath)
            throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        ArrayList<String> stringList = new  ArrayList<String>();
       int seek=0;
       file.seek(seek);
        String s=null;
        	while((s=file.readLine())!=null)
        	{
        		stringList.add(s);
        		
        	}
        System.out.println(stringList);
        file.close();
        return null;
    }
		    
		    private static List<String> addString(String filePath,String insertString)
            throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
       long seek=file.length();
       file.seek(seek);
       file.writeBytes(insertString);
       System.out.println(file.length() + insertString.getBytes().length);
        //System.out.println(stringList);
        file.close();
        return null;
    }
		    
		    
		    public static void delete(RandomAccessFile file, long recordSeek) throws IOException {
		    	System.out.println("intial length"+ file.length());
		    	if(file.length()-5<=0)
		    	{	file.setLength(0);
		    	 return;
		    	}
				long lastSeek=file.length()-5;
				System.out.println("last seek"+ lastSeek);
				if((recordSeek+5)==file.length())
				{	System.out.println("full");
					 file.setLength(0);
					return;
				}
				if(lastSeek==recordSeek)
				{
					System.out.println("last");
					file.setLength(file.length()-5-2);
					return;
				}
				file.seek(lastSeek);
				String lastRecord = file.readLine();
				file.seek(recordSeek);
		        file.write(lastRecord.getBytes());
		        
		        file.setLength(file.length()-7);
		        System.out.println(file.length());
		        file.close();
			}
	}
