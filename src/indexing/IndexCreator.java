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
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
//import org.omg.CORBA.PRIVATE_MEMBER;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class IndexCreator {
	private List articles;
	
	public IndexCreator() {
		super();
	}

	public List getArticles() {
		return articles;
	}

	public void setArticles(List articles) {
		this.articles = articles;
	}
	
	public void readJson() {
		try {
		    // create Gson instance
		    Gson gson = new Gson();

		    // create a reader
		    Reader reader = Files.newBufferedReader(Paths.get("corpus.json"));
		    
		    // convert JSON array to list of articles
		    List<Article> articles = new Gson().fromJson(reader, 
		    		new TypeToken<List<Article>>() {}.getType());
		    
		    setArticles(articles);
		    
		    // print articles
		    //articles.forEach(System.out::println);
		    
		    // close reader
		    reader.close();
		    
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	private Document getDocument(Article article) throws IOException{
		Document document = new Document();
		
		FieldType linkFT = new FieldType();
		FieldType titleFT = new FieldType();
		FieldType textFT = new FieldType();
		
		linkFT.setStored(true);
		linkFT.setTokenized(false);
		
		titleFT.setStored(true);
		titleFT.setTokenized(true);
		
		textFT.setStored(true);
		textFT.setTokenized(true);
		
		Field linkField = new Field("link", article.getLink(), linkFT);
		Field titleField = new Field("title", article.getTitle(), titleFT);
		Field textField = new Field("text", article.getText(), textFT);
		
		document.add(linkField);
		document.add(titleField);
		document.add(textField);
		// TODO Test this
		//System.out.println(document.getValues(arg0));
		//TEST : System.out.println(document.getFields()); ---> DONE
		

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
	/*
	 * 
	 * TODO Change method so it isn't main
	 * 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		
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
		
		/*
		 * deleteAll() until there is no main in IndexCreator
		 */
		iWriter.deleteAll();
		
		for (int index = 0; index < documentsList.size(); index++){
			iWriter.addDocument(documentsList.get(index));
		}
		iWriter.close();
		
		DirectoryReader iReader = DirectoryReader.open(directory);
	/*
	 * 
	 * 
		System.out.println("numDocs: " + iReader.numDocs());
		System.out.println(iReader.document(10));
	 *
	 *	
	 */
		
		IndexSearcher iSearcher = new IndexSearcher(iReader);
		QueryParser parser = new QueryParser("text", analyzer);
		Query query = parser.parse("Credlin");
		TopDocs topDocs = iSearcher.search(query, 6000);
		ScoreDoc[] hits = topDocs.scoreDocs;//iSearcher.search(query, 6000).scoreDocs;
		for(int i = 0; i < hits.length; i++){
			Document hitDoc = iSearcher.doc(hits[i].doc);
			System.out.println(hitDoc.getField("title"));
		}
		System.out.println("Found: " + hits.length);
		iReader.close();
		directory.close();
	}
}
