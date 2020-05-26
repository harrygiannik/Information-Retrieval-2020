package controller;

import java.util.Stack;

import org.apache.lucene.analysis.tokenattributes.FlagsAttribute;

public class Controller {
	private String userInput;
	private boolean defaultSearch;
	private boolean titleSearch;
	private boolean enableHistory;
	private boolean scoreSort;
	private boolean sizeSort;
	private boolean alphabeticalSort;
	private String results;
	private Stack<String> history;
	private HistoryController historyController;
	private IndSearcherController indSearcherController;
	private QueryCreatorController queryCreatorController;
	private SorterController sorterController;
	
	public Controller() {
		super();
	}

	public String getUserInput() {
		return userInput;
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}

	public boolean isDefaultSearch() {
		return defaultSearch;
	}

	public void setDefaultSearch(boolean defaultSearch) {
		this.defaultSearch = defaultSearch;
	}

	public boolean isTitleSearch() {
		return titleSearch;
	}

	public void setTitleSearch(boolean titleSearch) {
		this.titleSearch = titleSearch;
	}

	public boolean isEnableHistory() {
		return enableHistory;
	}

	public void setEnableHistory(boolean enableHistory) {
		this.enableHistory = enableHistory;
	}

	public boolean isScoreSort() {
		return scoreSort;
	}

	public void setScoreSort(boolean scoreSort) {
		this.scoreSort = scoreSort;
	}

	public boolean isSizeSort() {
		return sizeSort;
	}

	public void setSizeSort(boolean sizeSort) {
		this.sizeSort = sizeSort;
	}

	public boolean isAlphabeticalSort() {
		return alphabeticalSort;
	}

	public void setAlphabeticalSort(boolean alphabeticalSort) {
		this.alphabeticalSort = alphabeticalSort;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public Stack<String> getHistory() {
		return history;
	}

	public void setHistory(Stack<String> history) {
		this.history = history;
	}

	public HistoryController getHistoryController() {
		return historyController;
	}

	public void setHistoryController(HistoryController historyController) {
		this.historyController = historyController;
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

	public SorterController getSorterController() {
		return sorterController;
	}

	public void setSorterController(SorterController sorterController) {
		this.sorterController = sorterController;
	}

	public void initParams() {
		/*
		 * Setting Index
		 */
		setIndSearcherController(new IndSearcherController());
		indSearcherController.openIndex();
		indSearcherController.initSearcher();
		
		/*
		 * Set history
		 */
		setHistoryController(new HistoryController());
		historyController.getHistory().initHistory();
		setHistory(historyController.getHistory().getQueryHistory());
		
		/*
		 * Set default sorter
		 */
		setSorterController(new SorterController());
		sorterController.setType("score");
		sorterController.setIndSearcherController(getIndSearcherController());
		sorterController.getSorter().setiSearcher(getIndSearcherController().getIndSearcher().getiSearcher());
		setScoreSort(true);
		setAlphabeticalSort(false);
		setSizeSort(false);
		
		/*
		 * Set query creator, analyzer
		 */
		setQueryCreatorController(new QueryCreatorController());
		queryCreatorController.getQueryCreator().setAnalyzer(queryCreatorController.getAnalyzer());
		setDefaultSearch(true);
		setTitleSearch(false);
	}
	
}










