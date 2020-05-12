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
	private Text txtPleaseInsertYour;
	private Text text;
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
		
		txtPleaseInsertYour = new Text(shlWikipediaSearchEngine, SWT.BORDER);
		txtPleaseInsertYour.setText("Please insert your query");
		txtPleaseInsertYour.setBounds(100, 62, 881, 29);
		
		Button btnNewButton = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Ubuntu", 11, SWT.NORMAL));
		btnNewButton.setBounds(100, 105, 110, 29);
		btnNewButton.setText("Default Search");
		
		Button btnNewButton_1 = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnNewButton_1.setBounds(248, 105, 110, 29);
		btnNewButton_1.setText("Title Search");
		
		Button btnNewButton_2 = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnNewButton_2.setBounds(389, 105, 110, 29);
		btnNewButton_2.setText("Enable History");
		
		Button btnNewButton_3 = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnNewButton_3.setFont(SWTResourceManager.getFont("Ubuntu", 9, SWT.NORMAL));
		btnNewButton_3.setBounds(871, 105, 110, 29);
		btnNewButton_3.setText("Alphabetical Sort");
		
		Button btnNewButton_4 = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnNewButton_4.setBounds(728, 105, 110, 29);
		btnNewButton_4.setText("Sort by Size");
		
		Button btnNewButton_5 = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnNewButton_5.setBounds(576, 105, 110, 29);
		btnNewButton_5.setText("Sort by Score");
		
		Button btnNewButton_6 = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnNewButton_6.setBounds(492, 158, 93, 29);
		btnNewButton_6.setText("Search");
		
		text = new Text(shlWikipediaSearchEngine, SWT.BORDER);
		text.setBounds(100, 206, 675, 374);
		
		txtHistory = new Text(shlWikipediaSearchEngine, SWT.BORDER);
		txtHistory.setFont(SWTResourceManager.getFont("Ubuntu", 11, SWT.NORMAL));
		txtHistory.setBounds(781, 206, 200, 374);
		
		Button btnNewButton_7 = new Button(shlWikipediaSearchEngine, SWT.NONE);
		btnNewButton_7.setBounds(888, 611, 93, 29);
		btnNewButton_7.setText("Help");
		
		Label lblNewLabel = new Label(shlWikipediaSearchEngine, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(0, 674, 1074, 17);
		lblNewLabel.setText("Created as a course project for the Information Retrieval course in UoI");
		
		Label lblNewLabel_1 = new Label(shlWikipediaSearchEngine, SWT.NONE);
		lblNewLabel_1.setBounds(100, 183, 200, 17);
		lblNewLabel_1.setText("Results");
		
		Label lblNewLabel_2 = new Label(shlWikipediaSearchEngine, SWT.NONE);
		lblNewLabel_2.setBounds(781, 183, 200, 17);
		lblNewLabel_2.setText("History");

	}
}
