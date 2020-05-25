package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.JPanel;

public class SearchEngine {

	private JFrame frmWikipediaSearchEngine;
	private JTextField userInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchEngine window = new SearchEngine();
					window.frmWikipediaSearchEngine.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SearchEngine() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWikipediaSearchEngine = new JFrame();
		frmWikipediaSearchEngine.getContentPane().setBackground(new Color(204, 255, 255));
		frmWikipediaSearchEngine.setTitle("Wikipedia Search Engine");
		frmWikipediaSearchEngine.setBounds(100, 100, 1080, 720);
		frmWikipediaSearchEngine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWikipediaSearchEngine.getContentPane().setLayout(null);
		
		userInput = new JTextField();
		userInput.setBounds(100, 62, 881, 29);
		frmWikipediaSearchEngine.getContentPane().add(userInput);
		userInput.setColumns(10);
		
		JButton defSearchBtn = new JButton("Default Search");
		defSearchBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		defSearchBtn.setBackground(new Color(255, 255, 255));
		defSearchBtn.setBounds(100, 105, 110, 29);
		frmWikipediaSearchEngine.getContentPane().add(defSearchBtn);
		
		JButton titleSearchBtn = new JButton("Title Search");
		titleSearchBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		titleSearchBtn.setBackground(new Color(255, 255, 255));
		titleSearchBtn.setBounds(248, 105, 110, 29);
		frmWikipediaSearchEngine.getContentPane().add(titleSearchBtn);
		
		JButton historyBtn = new JButton("Show History");
		historyBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		historyBtn.setBackground(new Color(255, 255, 255));
		historyBtn.setBounds(389, 105, 110, 29);
		frmWikipediaSearchEngine.getContentPane().add(historyBtn);
		
		JButton scoreSortBtn = new JButton("Sort by Score");
		scoreSortBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scoreSortBtn.setBackground(new Color(255, 255, 255));
		scoreSortBtn.setBounds(576, 105, 110, 29);
		frmWikipediaSearchEngine.getContentPane().add(scoreSortBtn);
		
		JButton sizeSortBtn = new JButton("Sort by Size");
		sizeSortBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sizeSortBtn.setBackground(new Color(255, 255, 255));
		sizeSortBtn.setBounds(728, 105, 110, 29);
		frmWikipediaSearchEngine.getContentPane().add(sizeSortBtn);
		
		JButton abSortBtn = new JButton("Alphabetical Sort");
		abSortBtn.setFont(new Font("Tahoma", Font.PLAIN, 9));
		abSortBtn.setBackground(new Color(255, 255, 255));
		abSortBtn.setBounds(871, 105, 110, 29);
		frmWikipediaSearchEngine.getContentPane().add(abSortBtn);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.setBackground(new Color(255, 255, 255));
		searchBtn.setBounds(492, 158, 93, 29);
		frmWikipediaSearchEngine.getContentPane().add(searchBtn);
		
		JTextArea resultsArea = new JTextArea();
		resultsArea.setBounds(100, 206, 672, 374);
		frmWikipediaSearchEngine.getContentPane().add(resultsArea);
		
		JTextArea historyArea = new JTextArea();
		historyArea.setBounds(781, 206, 200, 374);
		frmWikipediaSearchEngine.getContentPane().add(historyArea);
		
		JLabel lblNewLabel = new JLabel("Results");
		lblNewLabel.setForeground(SystemColor.windowBorder);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBackground(new Color(204, 255, 255));
		lblNewLabel.setBounds(100, 185, 56, 16);
		frmWikipediaSearchEngine.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("History");
		lblNewLabel_1.setForeground(SystemColor.windowBorder);
		lblNewLabel_1.setBackground(new Color(204, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(781, 186, 56, 16);
		frmWikipediaSearchEngine.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Created as a course project for the Information Retrieval course in UoI");
		lblNewLabel_2.setBackground(new Color(204, 255, 255));
		lblNewLabel_2.setForeground(SystemColor.windowBorder);
		lblNewLabel_2.setBounds(638, 644, 412, 16);
		frmWikipediaSearchEngine.getContentPane().add(lblNewLabel_2);
		
		JButton helpBtn = new JButton("Help");
		helpBtn.setBackground(new Color(255, 255, 255));
		helpBtn.setBounds(932, 606, 97, 25);
		frmWikipediaSearchEngine.getContentPane().add(helpBtn);
		helpBtn.addActionListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					//TODO open pop up
				}
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("Please insert your query");
		lblNewLabel_3.setBackground(new Color(204, 255, 255));
		lblNewLabel_3.setForeground(SystemColor.windowBorder);
		lblNewLabel_3.setBounds(100, 41, 138, 16);
		frmWikipediaSearchEngine.getContentPane().add(lblNewLabel_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(274, 131, 480, 350);
		frmWikipediaSearchEngine.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Help");
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setBounds(156, 128, 158, 82);
		panel.add(lblNewLabel_4);
	}
}
