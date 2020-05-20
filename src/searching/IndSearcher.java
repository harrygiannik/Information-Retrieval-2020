package searching;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PhraseQuery.Builder;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class IndSearcher {
	private IndexSearcher iSearcher;
	private Query query;
	
	/*
	 * Constructor for term, boolean and wildcard queries 
	 */
	public IndSearcher(IndexSearcher iSearcher, Query query) {
		super();
		this.iSearcher = iSearcher;
		this.query = query;
	}
	
	/*
	 * GETTERS and SETTERS
	 */
	public IndexSearcher getiSearcher() {
		return iSearcher;
	}
	public void setiSearcher(IndexSearcher iSearcher) {
		this.iSearcher = iSearcher;
	}
	public Query getQuery() {
		return query;
	}
	public void setQuery(Query query) {
		this.query = query;
	}
	
	/*
	 * SEARCH method
	 */
	public ScoreDoc[] querySearch() throws IOException {
		TopDocs topDocs = getiSearcher().search(getQuery(), 6000);
		return topDocs.scoreDocs;
	}
	
}
