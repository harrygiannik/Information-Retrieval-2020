package controller;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;

import searching.QueryCreator;

public class QueryCreatorController {
	private QueryCreator queryCreator;
	private String query;
	private Analyzer analyzer;
	
	public QueryCreatorController() {
		super();
		this.queryCreator = new QueryCreator(null);
		this.analyzer = new StandardAnalyzer();
	}

	public QueryCreatorController(QueryCreator queryCreator, String query, Analyzer analyzer) {
		super();
		this.queryCreator = queryCreator;
		this.query = query;
		this.analyzer = analyzer;
	}

	public QueryCreator getQueryCreator() {
		return queryCreator;
	}


	public void setQueryCreator(QueryCreator queryCreator) {
		this.queryCreator = queryCreator;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Analyzer getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
	
	public Query createQuery(String query) throws ParseException {
		setQuery(query);
		if (queryCreator.isEnabled()){
			queryCreator.setSearchField("title");
			if (query.startsWith("title:")) {
				queryCreator.setParser(new QueryParser("title", analyzer));
				return queryCreator.getParser().parse(query.substring(6));
			}
			else {
				return queryCreator.addTitleField();
			}
		}
		else {
			queryCreator.setSearchField("text");
			queryCreator.setParser(new QueryParser("text", analyzer));
			return queryCreator.getParser().parse(getQuery());
		}
	}
}
