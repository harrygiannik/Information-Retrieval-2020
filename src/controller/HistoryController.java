package controller;

import java.util.Stack;

import searching.History;

public class HistoryController {
	private History history;
	private String newQuery;
	
	public HistoryController(History history, String newQuery) {
		super();
		this.history = history;
		this.newQuery = newQuery;
	}

	public HistoryController() {
		super();
		setHistory(history = new History(true, null));
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public String getNewQuery() {
		return newQuery;
	}

	public void setNewQuery(String newQuery) {
		this.newQuery = newQuery;
	}
	
	public void startHistory() {
		if (history.isEnabled()){
			history.initHistory();
		}
	}
	
	public Stack<String> returnHistory() {
		return history.getQueryHistory();
	}
	
	public void appendHistory() {
		history.addQuery(newQuery);
	}
	
	public void saveHistory() {
		history.writeHistory();
	}
}
