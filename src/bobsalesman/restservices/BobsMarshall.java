package bobsalesman.restservices;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class BobsMarshall {

	public File getFile(String input) throws IOException {
		URL url = new URL(input);
		String filename = FilenameUtils.getBaseName(url.getPath());
		File file = new File(filename);

		FileUtils.copyURLToFile(url, file);
		
		return file;
	}

}
