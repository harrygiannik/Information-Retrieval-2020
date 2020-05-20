package searching;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndOpener {
	private DirectoryReader iReader;
	private Directory directory;
	
	public IndOpener() {
		super();
		// Auto-generated constructor stub
	}

	public DirectoryReader getiReader() {
		return iReader;
	}

	public void setiReader(DirectoryReader iReader) {
		this.iReader = iReader;
	}

	public Directory getDirectory() {
		return directory;
	}

	public void setDirectory(Directory directory) {
		this.directory = directory;
	}
	
	public void openDirectory() {
		File file = new File(".");
		Path path = Paths.get(file.getAbsolutePath());
		
		try {
			setDirectory(FSDirectory.open(path));
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openIndex(Directory directory) {
		try {
			setiReader(DirectoryReader.open(directory));
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
}
