package controller;

import java.io.IOException;

import javax.xml.transform.Templates;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.lucene.search.highlight.TokenSources;

import searching.Sorter;

public class SorterController {
	private Sorter sorter;
	private String results;
	private String type;
	
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
		return results;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Document[] selectType() throws IOException {
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
	
	private String castResults() throws IOException {
		Document[] hits = selectType();
		String temp = "";
		
		
		for(int i = 0; i < hits.length; i++){
			
		    
		}
	}
	
}
