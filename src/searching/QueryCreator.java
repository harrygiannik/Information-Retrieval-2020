package searching;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;

public class QueryCreator {
	private QueryParser parser;
	private Analyzer analyzer;
	private String searchField;
	private String userInput;
	private boolean isEnabled; /* Check if title based search is clicked  TODO onClick set this true*/
	
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
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
	/*
	 * QUERY CREATION if Title btn is pressed
	 */
	public Query addTitleField() throws ParseException {
		QueryParser parserTitle = new QueryParser("title", analyzer);
		Query queryTitle = parserTitle.parse(getUserInput());
		return queryTitle;
	}
	
	
}
