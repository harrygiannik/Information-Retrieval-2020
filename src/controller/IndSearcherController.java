package controller;

import java.io.IOException;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;

import searching.IndOpener;
import searching.IndSearcher;

public class IndSearcherController {
	private IndOpener indOpener = new IndOpener();
	private IndSearcher indSearcher;
	
	public IndSearcherController(IndOpener indOpener, IndSearcher indSearcher) {
		super();
		this.indOpener = indOpener;
		this.indSearcher = indSearcher;
	}

	public IndSearcherController() {
		super();
	}

	public IndOpener getIndOpener() {
		return indOpener;
	}

	public void setIndOpener(IndOpener indOpener) {
		this.indOpener = indOpener;
	}

	public IndSearcher getIndSearcher() {
		return indSearcher;
	}

	public void setIndSearcher(IndSearcher indSearcher) {
		this.indSearcher = indSearcher;
	}
	
	public void openIndex() {
		indOpener.openDirectory();
		indOpener.openIndex(indOpener.getDirectory());
	}
	
	public void initSearcher() {
		IndexSearcher indexSearcher = new IndexSearcher(getIndOpener().getiReader());
		this.indSearcher = new IndSearcher(indexSearcher, null);
	}
	
	public ScoreDoc[] querySearchControl() throws IOException {
		return this.indSearcher.querySearch();
	}
	
	
	
}
