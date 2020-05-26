package searching;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

import org.apache.lucene.store.FSDirectory;

public class History {
	private boolean isEnabled;
	private Stack<String> queryHistory = new Stack<String>();
	
	/*
	 * CONSTRUCTOR
	 */
	public History(boolean isEnabled, Stack<String> queryHistory) {
		super();
		this.isEnabled = isEnabled;
		this.queryHistory = queryHistory;
	}
	public History() {
		super();
	}
	/*
	 * GETTERS and SETTERS
	 */
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Stack<String> getQueryHistory() {
		return queryHistory;
	}

	public void setQueryHistory(Stack<String> queryHistory) {
		this.queryHistory = queryHistory;
	}
	
	/*
	 * MANAGE HISTORY methods
	 */
	public void initHistory() {
		if (isEnabled()) {
			try {
				File historyFile = new File("history.txt");
				Scanner historyReader = new Scanner(historyFile);
				Stack<String> historyStack = new Stack<String>();
				while (historyReader.hasNextLine()) {
					String data = historyReader.nextLine();
					System.out.println(data);
					historyStack.push(data);
				}
				setQueryHistory(historyStack);
				historyReader.close();
			} catch (IOException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addQuery(String query) {
		if (isEnabled()) getQueryHistory().push(query);
	}
	
	public void writeHistory() {
		if (isEnabled()) {
			try {
				FileWriter myWriter = new FileWriter("history.txt");
				while (!(getQueryHistory().empty())){
					myWriter.write(getQueryHistory().pop());
				}
				myWriter.close();
			} catch (IOException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
