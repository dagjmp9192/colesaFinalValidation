package util;
import java.io.*;
import models.User;
import org.springframework.web.multipart.MultipartFile;
public class FileManager {

	
	private static  String path;
	static 
	{
		String folder=".."+File.separator+".."
	+File.separator+".."+File.separator+"uploads";
		System.out.println("obtaining path of "+folder);
		try
		{
			Class c=FileManager.class;
			String fpath=c.getResource(folder).toString();
			//removing file: from the path
			path=fpath.substring(5);
			System.out.println(path);
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static String save(
			MultipartFile file,User user)
	{
		String ext=file.getOriginalFilename();
		ext=ext.substring(ext.indexOf('.'));
		String fname=user.getId()+"_avatar"+ext;
		String fpath=path+fname;
		System.out.println("saving file "+fpath);
		try
		{
			File f=new File(fpath);
			FileOutputStream fos=new FileOutputStream(f);
			fos.write(file.getBytes());
			fos.close();
			System.out.println("File saved.");
			return fname;
		}catch(Exception e)
		{
			System.out.println(e);
			return "";
		}
	}
}
