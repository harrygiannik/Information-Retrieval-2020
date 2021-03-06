package controller;

import java.io.IOException;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.lucene.search.highlight.TokenSources;

import searching.Sorter;

public class SorterController {
	private Sorter sorter;
	private String results;
	private String type;
	private String searchField;
	private IndSearcherController indSearcherController;
	private QueryCreatorController queryCreatorController; 

	public SorterController() {
		super();
		this.sorter = new Sorter();
	}
	
	public SorterController(Sorter sorter, String results, String type) {
		super();
		this.sorter = sorter;
		this.results = results;
		this.type = type;
	}

	public Sorter getSorter() {
		return sorter;
	}

	public void setSorter(Sorter sorter) {
		this.sorter = sorter;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	
	public IndSearcherController getIndSearcherController() {
		return indSearcherController;
	}

	public void setIndSearcherController(IndSearcherController indSearcherController) {
		this.indSearcherController = indSearcherController;
	}

	public QueryCreatorController getQueryCreatorController() {
		return queryCreatorController;
	}

	public void setQueryCreatorController(QueryCreatorController queryCreatorController) {
		this.queryCreatorController = queryCreatorController;
	}

	public ScoreDoc[] selectType() throws IOException {
		switch (getType()) {
		case "size":
			return sorter.sizeSort();

		case "abSort":
			return sorter.alphabeticalSort();
			
		case "score":
			
		default:
			return sorter.scoreSort();
		}
	}
	
	public String castResults() throws IOException, InvalidTokenOffsetsException{
		ScoreDoc[] hits = selectType();
		String result = "";
		
		result = highlighter(getSearchField(), hits);
		
		return result;
	}
	
	private String highlighter(String field, ScoreDoc[] hits) throws IOException, InvalidTokenOffsetsException{
		String result = "";
		String fragResults = "";
		
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<b style = 'color: red'>", "</b>");
	    Highlighter highlighter = new Highlighter(formatter, new QueryScorer(getIndSearcherController().getIndSearcher().getQuery()));
	    
	    if (field.equals("title")) {
	    	 for(int i = 0; i < hits.length; i++){
	 			Document hitDoc = getIndSearcherController().getIndSearcher().getiSearcher().doc(hits[i].doc);
	 			fragResults = hitDoc.get(field);
	 			TokenStream tokenStream = TokenSources.getAnyTokenStream(getIndSearcherController().getIndSearcher().getiSearcher().getIndexReader(), 
	 					hits[i].doc, field, getQueryCreatorController().getAnalyzer());
	 		    TextFragment[] frag = highlighter.getBestTextFragments(tokenStream, fragResults, false, 6000);
	 		    if (!(frag[0] == null)) {
	 		    	result += frag[0].toString();
	 			}
	 		    else {
	 		    	result += frag[1].toString();
	 			}
	 		    
	 		    result += "<br>";
				result += "<a href='";
				result += hitDoc.get("link");
				result += "'>";
				result += hitDoc.get("link");
				result += "</a>";
	 		    result += "<br><br>";
	 	    }
		} else {
			 for(int i = 0; i < hits.length; i++){
					Document hitDoc = getIndSearcherController().getIndSearcher().getiSearcher().doc(hits[i].doc);
					result += "<b>";
					result += hitDoc.get("title");
					result += "</b>";
					result += "<br>";
					result += "<a href='";
					result += hitDoc.get("link");
					result += "'>";
					result += hitDoc.get("link");
					result += "</a>";
					result += "<br>";
					fragResults = hitDoc.get(field);
					TokenStream tokenStream = TokenSources.getAnyTokenStream(getIndSearcherController().getIndSearcher().getiSearcher().getIndexReader(), 
							hits[i].doc, field, getQueryCreatorController().getAnalyzer());
				    TextFragment[] frag = highlighter.getBestTextFragments(tokenStream, fragResults, false, 6000);
				    result += "&emsp;...";
				    if (!(frag[0] == null)) {
				    	result += frag[0].toString();
					}
				    else {
				    	result += frag[1].toString();
					}
				    result += " ...<br><br>";
			    }
		}
	    
		return result;
	}
}
