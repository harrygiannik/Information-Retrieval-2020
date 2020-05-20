package controller;

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
	
	
	
}
