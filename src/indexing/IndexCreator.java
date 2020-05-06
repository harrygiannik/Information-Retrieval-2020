package indexing;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class IndexCreator {

	private void readJson() {
		try {
		    // create Gson instance
		    Gson gson = new Gson();

		    // create a reader
		    Reader reader = Files.newBufferedReader(Paths.get("corpus.json"));

		    // convert JSON array to list of articles
		    List<Article> articles = new Gson().fromJson(reader, 
		    		new TypeToken<List<Article>>() {}.getType());

		    // print users
		    articles.forEach(System.out::println);

		    // close reader
		    reader.close();

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}

}
