package uid.contact.manager.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import uid.contact.manager.file.service.FileService;

public class FileServiceImpl implements FileService {
	

	public boolean createFile(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean createFile(String dir, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteFile(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isfileExists(File file) {
		if(file.exists())
			return true;
		return false;
	}

	public void createFile(File file) {
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Ï/O exception while creating file : " + file.getName());
		}
	}

	@Override
	public void createConfigFile(File file) {
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			writer.println("user_type=personal");
			writer.close();
		} catch (IOException e) {
			System.out.println("Ï/O exception while creating file : " + file.getName());
		}
	}

}
