package br.com.diastecnologia.for9.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

public class ImageUtil {

	private static String default_file_path = "imagens";
	
	public static String saveFile( String basePath, UploadedFile file ) throws IOException{
		
		File dir = new File(basePath + File.separator + ".." + File.separator + default_file_path);
		if( !dir.exists() ){
			dir.mkdir();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String newFileName = sdf.format(new Date() ) + "." + file.getContentType().split("/")[1];
		
		Path newFilePath = Paths.get(basePath, "..", default_file_path, newFileName);
		Files.copy(file.getFile(), newFilePath, StandardCopyOption.REPLACE_EXISTING);
		
		return "/" + default_file_path + "/" + newFileName;
	}
	
}
