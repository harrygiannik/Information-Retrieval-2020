package controller;

import org.apache.lucene.search.ScoreDoc;

import searching.Sorter;

public class SorterController {
	private Sorter sorter;
	private String results;
	
	public SorterController() {
		super();
		this.sorter = new Sorter();
	}
	
	public SorterController(Sorter sorter, String results) {
		super();
		this.sorter = sorter;
		this.results = results;
	}
	
	
}
