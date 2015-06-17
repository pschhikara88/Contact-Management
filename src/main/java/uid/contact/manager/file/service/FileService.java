package uid.contact.manager.file.service;

import java.io.File;

public interface FileService {
	
	public boolean isfileExists(File file);
	/*public boolean isDefaultContactfileExists();
	public boolean createDefaultContactFile();
	public void createDefaultDirectoryAndConfigFile();*/
	public void createConfigFile(File file);
	public void createFile(File file);
	public boolean createFile(String name);
	public boolean createFile(String dir, String name);
	public boolean deleteFile(String name);
}
