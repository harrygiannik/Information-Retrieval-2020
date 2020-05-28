package indexing;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class IndexCreator {
	private List<Article> articles;
	
	public IndexCreator() {
		super();
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public void readJson() {
		try {
		    // create Gson instance
		    Gson gson = new Gson();

		    // create a reader
		    Reader reader = Files.newBufferedReader(Paths.get("corpus.json"));
		    
		    // convert JSON array to list of articles
		    List<Article> articles = gson.fromJson(reader, 
		    		new TypeToken<List<Article>>() {}.getType());
		    
		    setArticles(articles);
		    
		    // close reader
		    reader.close();
		    
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	private Document getDocument(Article article) throws IOException{
		Document document = new Document();
		
		document.add(new TextField("link", article.getLink(), Store.YES));
		document.add(new TextField("title", article.getTitle(), Store.YES));
		document.add(new TextField("text", article.getText(), Store.YES));
		
		return document;
	}
	
	public List<Document> documentCreator(List<Article> articles) {
		List<Document> documents = new ArrayList<Document>();
		/*  push document in list via getDocument method
			call the above method for each article
		*/
		for (int index = 0; index < articles.size(); index++){
			try {
				documents.add(getDocument(articles.get(index)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return documents;
	}
	
	public static void main(String[] args) throws IOException, ParseException, InvalidTokenOffsetsException {
		
		// Prepare useful object
		
		IndexCreator indexCreator = new IndexCreator();
		indexCreator.readJson();
		List<Document> documentsList = indexCreator.documentCreator(indexCreator.getArticles());
		
		// Create index
		
		Analyzer analyzer = new StandardAnalyzer();
		File file = new File(".");
		Path path = Paths.get(file.getAbsolutePath());
		
		Directory directory = FSDirectory.open(path);
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter iWriter = new IndexWriter(directory, config);
		
		iWriter.deleteAll();
		
		for (int index = 0; index < documentsList.size(); index++){
			iWriter.addDocument(documentsList.get(index));
		}
		
		iWriter.close();
		directory.close();
	}
}
