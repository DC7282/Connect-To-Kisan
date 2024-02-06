package com.dhiraj.fileio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import jakarta.servlet.http.Part;

public class ProductIO {

	private String result;
	private FileOutputStream fos;
	private InputStream is;
	@SuppressWarnings("finally")

	public String createFileName(Part part) {
		String image = part.getSubmittedFileName();
		String newfilename =LocalDateTime.now().toString();
		String[] split = newfilename.split(":");
		newfilename = split[0]+split[1]+split[2]+"_"+image;
		return newfilename;
	}




	public String putInFile(String newfilename, Part part) {
		try {
			File currentDirFile = new File("src\\main\\resources\\static\\MyFiles\\Products");
			String realpath = currentDirFile.getAbsolutePath();

	            Path uploadPath = Path.of(realpath);

	            // Create the upload directory if it doesn't exist
	            if (!Files.exists(uploadPath)) {
	                Files.createDirectories(uploadPath);
	            }
			realpath = uploadPath+File.separator+newfilename;
			is=part.getInputStream();
//			is = file.getInputStream();
	        byte b[]=new byte[is.available()];
	        is.read(b);

	        fos = new FileOutputStream(realpath);
//	        System.out.println(b +"  "+fos+" "+realpath+"  "+newfilename);
			fos.write(b);
			result="saved";
		} catch (Exception e) {
			result="failed";
		} finally {
			try {
//				fos.close();
//				is.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
	}
}
