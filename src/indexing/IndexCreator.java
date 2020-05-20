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
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.lucene.search.highlight.TokenSources;
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
		
		document.add(new TextField("link", article.getLink(), Store.YES));
		document.add(new TextField("title", article.getTitle(), Store.YES));
		document.add(new TextField("text", article.getText(), Store.YES));
		
		//  Test this
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
	 * 	TODO
	 *  Change method so it isn't main
	 * 
	 */
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
		
		/*
		 * deleteAll() until there is no main in IndexCreator
		 */
		iWriter.deleteAll();
		
		for (int index = 0; index < documentsList.size(); index++){
			iWriter.addDocument(documentsList.get(index));
		}
		iWriter.close();
		
		DirectoryReader iReader = DirectoryReader.open(directory);
	
		System.out.println("numDocs: " + iReader.numDocs());
		//System.out.println(iReader.document(10));
		System.out.println("--------------------------");
		
		IndexSearcher iSearcher = new IndexSearcher(iReader);
		/*
		 * PHRASE QUERY
		 */
		PhraseQuery.Builder builder = new PhraseQuery.Builder();
		builder.add(new Term("text", "comput"));
		//builder.add(new Term("text", "stalin"));
		PhraseQuery phraseQuery = builder.build();
		System.out.println("phraseQuery: " + phraseQuery.toString());
		///////////////////////
		
		/*
		 * BOOLEAN with AND, OR,  WILDCARD and TERM
		 */
		QueryParser parserText = new QueryParser("text", analyzer);
		QueryParser parserTitle = new QueryParser("title", analyzer);

		Query queryText = parserText.parse("computer science");
		Query queryTitle = parserTitle.parse("science");
		System.out.println(parserText.getAnalyzer());
		System.out.println(queryText.toString());
		///////////////////////
		TopDocs topDocs = iSearcher.search(queryText, 10);
		ScoreDoc[] hits = topDocs.scoreDocs;//iSearcher.search(query, 6000).scoreDocs;
		
		/* Highlight*/
		
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<mark>", "</mark>");
	    Highlighter highlighter = new Highlighter(formatter, new QueryScorer(queryText));
		
	    /* Highlight*/
	    
		for(int i = 0; i < hits.length; i++){
			Document hitDoc = iSearcher.doc(hits[i].doc);
			/* Highlight*/
			String text = hitDoc.get("text");
			TokenStream tokenStream = TokenSources.getAnyTokenStream(iSearcher.getIndexReader(), 
					hits[i].doc, "text", analyzer);
		     TextFragment[] frag = highlighter.getBestTextFragments(tokenStream,
		    		 text, false, 10);//highlighter.getBestFragments(tokenStream, text, 3, "...");
		     for (int j = 0; j < frag.length; j++) {
		         if ((frag[j] != null) && (frag[j].getScore() > 0)) {
		           System.out.println((frag[j].toString()));
		         }
		       }
		     //<mark>hahah</mark> vagelis
		     System.out.println();
		     /* Highlight*/			
		    
		     //System.out.println(hitDoc.getField("text"));
		}
		System.out.println("Found: " + hits.length);
		iReader.close();
		directory.close();
	}
}
