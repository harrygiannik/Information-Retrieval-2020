package searching;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.PhraseQuery;

class QueryCreator {
	private QueryParser parser;
	private Analyzer analyzer;
	private String searchField;
	private String userInput;
	
	/*
	 * CONSTRUCTOR
	 */
	public QueryCreator(String userInput) {
		super();
		this.userInput = userInput;
	}
	
	/*
	 * GETTERS and SETTERS
	 */
	public QueryParser getParser() {
		return parser;
	}
	public void setParser(QueryParser parser) {
		this.parser = parser;
	}
	public Analyzer getAnalyzer() {
		return analyzer;
	}
	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getUserInput() {
		return userInput;
	}
	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}
	
	/*
	 * QUERY CREATION TODO the method
	 */
	
}
