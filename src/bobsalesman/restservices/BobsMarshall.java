package bobsalesman.restservices;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class BobsMarshall {

	public File getFile(String input) throws IOException {
		SingleBobTon singleton= SingleBobTon.getInstance();
		URL url = new URL(input);
		String filename = FilenameUtils.getBaseName(url.getPath());
		File file = new File(filename);
		Bucket bucket = null;
		for(Bucket buck : singleton.s3client.listBuckets()){
			if(buck.getName().contains(singleton.bucketName))
				bucket = buck;
		}
		singleton.s3client.putObject(new PutObjectRequest(bucket.getName(), filename, file));

		file.deleteOnExit();
		FileUtils.copyURLToFile(url, file);

		return file;
	}

}
