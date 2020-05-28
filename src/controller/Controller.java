package controller;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

public class Controller {
	private String userInput;
	private boolean defaultSearch;
	private boolean titleSearch;
	private boolean enableHistory;
	private boolean scoreSort;
	private boolean sizeSort;
	private boolean alphabeticalSort;
	private String results;
	private String historyContent;
	private HistoryController historyController;
	private IndSearcherController indSearcherController;
	private QueryCreatorController queryCreatorController;
	private SorterController sorterController;
	private String countOfResults;
	
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
	
	public String getHistoryContent() {
		return historyContent;
	}

	public void setHistoryContent(String historyContent) {
		this.historyContent = historyContent;
	}

	public String getCountOfResults() {
		return countOfResults;
	}

	public void setCountOfResults(String countOfResults) {
		this.countOfResults = countOfResults;
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
		historyController.startHistory();
		setEnableHistory(false);
		
		/*
		 * Set default sorter
		 */
		setSorterController(new SorterController());
		sorterController.setSearchField("text");
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
	/*
	 * Searching type switching
	 */
	public void handleContentSearch() {
		setDefaultSearch(true);
		setTitleSearch(false);
		sorterController.setSearchField("text");
		queryCreatorController.getQueryCreator().setEnabled(false);
	}
	
	public void handleTitleSearch() {
		setTitleSearch(true);
		setDefaultSearch(false);
		sorterController.setSearchField("title");
		queryCreatorController.getQueryCreator().setEnabled(true);
	}
	
	/*
	 * Sorting type switching
	 */
	public void handleScoreSort() {
		setScoreSort(true);
		sorterController.setType("score");
		setSizeSort(false);
		setAlphabeticalSort(false);
	}
	
	public void handleSizeSort() {
		setSizeSort(true);
		sorterController.setType("size");
		setScoreSort(false);
		setAlphabeticalSort(false);
	}
	
	public void handleAlphabeticalSort() {
		setAlphabeticalSort(true);
		sorterController.setType("abSort");
		setScoreSort(false);
		setSizeSort(false);
	}
	
	/*
	 * Pass the history Stack
	 */
	public String handleHistory() {
		setEnableHistory(true);
		setHistoryContent("");
		
		for(int i = 0; i < historyController.getHistory().getQueryHistory().size(); i++){
			historyContent += historyController.getHistory().getQueryHistory().get(i);
			historyContent += "<br>";
		}

		return historyContent;
	}
	
	public void saveHistory(){
		historyController.saveHistory();
		
	}
	
	public String handleSearch(String userInput) throws IOException, ParseException, InvalidTokenOffsetsException{
		ScoreDoc[] hits;
		setUserInput(userInput);
		
		if (titleSearch) {
			indSearcherController.getIndSearcher().setQuery(queryCreatorController.createQuery(getUserInput()));
			hits = indSearcherController.querySearchControl();
		} else {
			indSearcherController.getIndSearcher().setQuery(queryCreatorController.createQuery(getUserInput()));
			hits = indSearcherController.querySearchControl();
		}
		
		setCountOfResults("" + hits.length);
		
		if (hits.length == 0) {
			setResults("&emsp;Sorry &nbsp;&nbsp; &#128577;<br>&emsp;Nothing found");
		} else {
			sorterController.setIndSearcherController(getIndSearcherController());
			sorterController.setQueryCreatorController(getQueryCreatorController());
			sorterController.getSorter().setiSearcher((sorterController.getIndSearcherController().getIndSearcher().getiSearcher()));
			sorterController.getSorter().setHits(hits);
			setResults(sorterController.castResults());
			historyController.setNewQuery(userInput);
			historyController.appendHistory();
		}
		
		return getResults();
	}
	
	public String enact(String caller, String userInput) throws IOException, ParseException, InvalidTokenOffsetsException{
		String retVal = null;
		switch (caller) {
		case "Content Search":
			handleContentSearch();
			break;
		case "Title Search":
			handleTitleSearch();
			break;
		case "Show History":
			retVal = handleHistory();
			break;
		case "Sort by Score":
			handleScoreSort();
			break;
		case "Sort by Size":
			handleSizeSort();
			break;
		case "Alphabetical Sort":
			handleAlphabeticalSort();
			break;
		case "Search":
			retVal = handleSearch(userInput);
			break;
		case "Close":
			saveHistory();
			break;
		default:
			initParams();
			break;
		}
		return retVal;
	}
}

