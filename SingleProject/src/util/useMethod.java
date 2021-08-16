package util;

import java.io.File;

public class useMethod {
	public static String getFileSize(String filepath, String filename) {
		String size="";
		File mfile = new File(filepath, filename);
		if(mfile.exists()) {
			long FileSize = mfile.length();
			size = Long.toString(FileSize);
		}else
			size = "File not exist";
		return size;
	}
}
