package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;

class app {

	protected Shell shlWikipediaSearchEngine;
	private Text txtQuery;
	private Text txtResults;
	private Text txtHistory;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			app window = new app();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlWikipediaSearchEngine.open();
		shlWikipediaSearchEngine.layout();
		while (!shlWikipediaSearchEngine.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlWikipediaSearchEngine = new Shell();
		shlWikipediaSearchEngine.setBackground(SWTResourceManager.getColor(176, 224, 230));
		shlWikipediaSearchEngine.setSize(1080, 720);
		shlWikipediaSearchEngine.setText("Wikipedia Search Engine");
		shlWikipediaSearchEngine.setLayout(null);
		
		txtQuery = new Text(shlWikipediaSearchEngine, SWT.NONE);
		txtQuery.setBounds(100, 62, 881, 29);
		
		Button btnDefaultSearch = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnDefaultSearch.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnDefaultSearch.setBounds(100, 105, 110, 29);
		btnDefaultSearch.setText("Default Search");
		
		Button btnTitleSearch = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnTitleSearch.setBounds(248, 105, 110, 29);
		btnTitleSearch.setText("Title Search");
		
		Button btnHistory = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnHistory.setBounds(389, 105, 110, 29);
		btnHistory.setText("Enable History");
		
		Button btnABSort = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnABSort.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		btnABSort.setBounds(871, 105, 110, 29);
		btnABSort.setText("Alphabetical Sort");
		
		Button btnSizeSort = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnSizeSort.setBounds(728, 105, 110, 29);
		btnSizeSort.setText("Sort by Size");
		
		Button btnScoreSort = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnScoreSort.setBounds(576, 105, 110, 29);
		btnScoreSort.setText("Sort by Score");
		
		Button btnSearch = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnSearch.setBounds(492, 158, 93, 29);
		btnSearch.setText("Search");
		
		txtResults = new Text(shlWikipediaSearchEngine, SWT.NONE);
		txtResults.setBounds(100, 206, 675, 374);
		
		txtHistory = new Text(shlWikipediaSearchEngine, SWT.NONE);
		txtHistory.setFont(SWTResourceManager.getFont("Ubuntu", 11, SWT.NORMAL));
		txtHistory.setBounds(781, 206, 200, 374);
		
		Button btnNewButton_7 = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnNewButton_7.setBounds(888, 611, 93, 29);
		btnNewButton_7.setText("Help");
		
		Label lblNewLabel = new Label(shlWikipediaSearchEngine, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(-22, 653, 1074, 20);
		lblNewLabel.setText("Created as a course project for the Information Retrieval course in UoI");
		
		Label lblNewLabel_1 = new Label(shlWikipediaSearchEngine, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNewLabel_1.setBounds(100, 183, 200, 17);
		lblNewLabel_1.setText("Results");
		
		Label lblNewLabel_2 = new Label(shlWikipediaSearchEngine, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNewLabel_2.setBounds(781, 183, 200, 17);
		lblNewLabel_2.setText("History");
		
		Label lblPleaseInsertYour = new Label(shlWikipediaSearchEngine, SWT.NONE);
		lblPleaseInsertYour.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPleaseInsertYour.setBounds(100, 36, 172, 20);
		lblPleaseInsertYour.setText("Please insert your query");

	}
}
