package controller;

public class Controller {
	private String userInput;
	private boolean defaultSearch;
	private boolean titleSearch;
	private boolean enableHistory;
	private boolean scoreSort;
	private boolean sizeSort;
	private boolean alphabeticalSort;
	private String results;
	private String history;
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

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
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

	
	
}
