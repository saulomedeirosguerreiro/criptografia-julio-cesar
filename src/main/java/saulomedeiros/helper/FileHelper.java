package saulomedeiros.helper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHelper {
	
		public static File createOrRecover(String path) throws IOException {
			File file = new File(path);
			if (!file.exists())
				file.createNewFile();
			return file;
		}

		public static void saveToFile(String content, String path) throws IOException {
			File file = createOrRecover(path);
			PrintWriter printWriter = new PrintWriter(file);
			printWriter.print(content);
			printWriter.close();
		}

}
