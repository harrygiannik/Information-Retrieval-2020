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
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchEngine {

	private JFrame frmWikipediaSearchEngine;
	private JTextField userInput;
	private JTextPane txtpnSupportedSearchingmethodstntcontent;
	private JTextPane resultsArea;
	private JTextPane historyArea;
	private JButton contentSearchBtn;
	private JButton titleSearchBtn;
	private JButton historyBtn;
	private JButton scoreSortBtn;
	private JButton sizeSortBtn;
	private JButton abSortBtn;
	private JButton searchBtn;

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
		frmWikipediaSearchEngine.getContentPane().setBackground(new Color(255, 255, 224));
		frmWikipediaSearchEngine.setTitle("Wikipedia Search Engine");
		frmWikipediaSearchEngine.setBounds(100, 100, 1080, 720);
		frmWikipediaSearchEngine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWikipediaSearchEngine.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(128, 128, 128));
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(202, 103, 679, 477);
		frmWikipediaSearchEngine.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBorder(new LineBorder(Color.gray));
		panel.setOpaque(true);
		panel.setVisible(false);
		
		JButton closeHelpBtn = new JButton("Close");
		closeHelpBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(false);
				txtpnSupportedSearchingmethodstntcontent.setVisible(false);
				closeHelpBtn.setVisible(false);
				contentSearchBtn.setEnabled(true);
				titleSearchBtn.setEnabled(true);
				historyBtn.setEnabled(true);
				scoreSortBtn.setEnabled(true);
				sizeSortBtn.setEnabled(true);
				abSortBtn.setEnabled(true);
				searchBtn.setEnabled(true);
				resultsArea.setEnabled(true);
				historyArea.setEnabled(true);
			}
		});
		closeHelpBtn.setBackground(new Color(255, 255, 255));
		closeHelpBtn.setBounds(550, 437, 117, 25);
		panel.add(closeHelpBtn);
		closeHelpBtn.setVisible(false);
		
		
		JTextPane txtpnSupportedSearchingmethodstntcontent = new JTextPane();
		txtpnSupportedSearchingmethodstntcontent.setForeground(new Color(0, 0, 128));
		txtpnSupportedSearchingmethodstntcontent.setBackground(new Color(220, 220, 220));
		txtpnSupportedSearchingmethodstntcontent.setFont(new Font("Chandas", Font.PLAIN, 5));
		txtpnSupportedSearchingmethodstntcontent.setContentType("text/html");
		txtpnSupportedSearchingmethodstntcontent.setText("Supported searching methods:<br><br>&emsp;Content Search: Searches the article's main body content.<br><br>&emsp;Title Search: Searches in article's title.<br><br><br>Supported sorting methods:<br><br>&emsp;Sort by Score: Results are sorted by their relevance to the search query.<br><br>&emsp;Sort by Size: Results are sorted based on the article size.<br><br>&emsp;Alphabetical Sort: Results are sorted alphabetically by title.<br><br><br>Query history:<br><br>&emsp;Show history: Retrieves queries from past searches.<br><br><br>Supported queries:<br><br>&emsp;The engine supports keyword, boolean, wildcard and field based queries.<br><br>&emsp;The acceptable fields are title and text.");
		txtpnSupportedSearchingmethodstntcontent.setBounds(72, 12, 528, 450);
		panel.add(txtpnSupportedSearchingmethodstntcontent);
		txtpnSupportedSearchingmethodstntcontent.setVisible(false);
		txtpnSupportedSearchingmethodstntcontent.setEditable(false);
		this.txtpnSupportedSearchingmethodstntcontent = txtpnSupportedSearchingmethodstntcontent;
		
		userInput = new JTextField();
		userInput.setBounds(100, 62, 881, 29);
		userInput.setBorder(new LineBorder(Color.gray));
		frmWikipediaSearchEngine.getContentPane().add(userInput);
		userInput.setColumns(10);
		
		JButton contentSearchBtn = new JButton("Content Search");
		contentSearchBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentSearchBtn.setBackground(new Color(255, 255, 255));
		contentSearchBtn.setBounds(100, 105, 110, 29);
		this.contentSearchBtn = contentSearchBtn;
		frmWikipediaSearchEngine.getContentPane().add(contentSearchBtn);
		
		JButton titleSearchBtn = new JButton("Title Search");
		titleSearchBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		titleSearchBtn.setBackground(new Color(255, 255, 255));
		titleSearchBtn.setBounds(248, 105, 110, 29);
		this.titleSearchBtn = titleSearchBtn;
		frmWikipediaSearchEngine.getContentPane().add(titleSearchBtn);
		
		JButton historyBtn = new JButton("Show History");
		historyBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		historyBtn.setBackground(new Color(255, 255, 255));
		historyBtn.setBounds(389, 105, 110, 29);
		this.historyBtn = historyBtn;
		frmWikipediaSearchEngine.getContentPane().add(historyBtn);
		
		JButton scoreSortBtn = new JButton("Sort by Score");
		scoreSortBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scoreSortBtn.setBackground(new Color(255, 255, 255));
		scoreSortBtn.setBounds(576, 105, 110, 29);
		this.scoreSortBtn = scoreSortBtn;
		frmWikipediaSearchEngine.getContentPane().add(scoreSortBtn);
		
		JButton sizeSortBtn = new JButton("Sort by Size");
		sizeSortBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sizeSortBtn.setBackground(new Color(255, 255, 255));
		sizeSortBtn.setBounds(728, 105, 110, 29);
		this.sizeSortBtn = sizeSortBtn;
		frmWikipediaSearchEngine.getContentPane().add(sizeSortBtn);
		
		JButton abSortBtn = new JButton("Alphabetical Sort");
		abSortBtn.setFont(new Font("Tahoma", Font.PLAIN, 9));
		abSortBtn.setBackground(new Color(255, 255, 255));
		abSortBtn.setBounds(871, 105, 110, 29);
		this.abSortBtn = abSortBtn;
		frmWikipediaSearchEngine.getContentPane().add(abSortBtn);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.setBackground(new Color(255, 255, 255));
		searchBtn.setBounds(492, 158, 93, 29);
		this.searchBtn = searchBtn;
		frmWikipediaSearchEngine.getContentPane().add(searchBtn);
		
		JTextPane resultsArea = new JTextPane();
		resultsArea.setContentType("text/html");
		resultsArea.setBounds(100, 206, 672, 374);
		resultsArea.setBorder(new LineBorder(Color.gray));
		resultsArea.setEditable(false);
		this.resultsArea = resultsArea;
		frmWikipediaSearchEngine.getContentPane().add(resultsArea);
		
		JTextPane historyArea = new JTextPane();
		historyArea.setBounds(781, 206, 200, 374);
		historyArea.setBorder(new LineBorder(Color.gray));
		historyArea.setEditable(false);
		this.historyArea = historyArea;
		frmWikipediaSearchEngine.getContentPane().add(historyArea);
		
		JLabel lblNewLabel = new JLabel("Results");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBackground(new Color(204, 255, 255));
		lblNewLabel.setBounds(100, 185, 87, 16);
		frmWikipediaSearchEngine.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("History");
		lblNewLabel_1.setForeground(new Color(128, 128, 128));
		lblNewLabel_1.setBackground(new Color(204, 255, 255));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_1.setBounds(781, 186, 128, 16);
		frmWikipediaSearchEngine.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Created as a course project for the Information Retrieval course in UoI");
		lblNewLabel_2.setBackground(new Color(204, 255, 255));
		lblNewLabel_2.setForeground(new Color(128, 128, 128));
		lblNewLabel_2.setBounds(638, 644, 412, 16);
		frmWikipediaSearchEngine.getContentPane().add(lblNewLabel_2);
		
		JButton helpBtn = new JButton("Help");
		helpBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel.setVisible(true);
				txtpnSupportedSearchingmethodstntcontent.setVisible(true);
				closeHelpBtn.setVisible(true);
				contentSearchBtn.setEnabled(false);
				titleSearchBtn.setEnabled(false);
				historyBtn.setEnabled(false);
				scoreSortBtn.setEnabled(false);
				sizeSortBtn.setEnabled(false);
				abSortBtn.setEnabled(false);
				searchBtn.setEnabled(false);
				historyArea.setEnabled(false);
				resultsArea.setEnabled(false);
				
			}
		});
		helpBtn.setBackground(new Color(255, 255, 255));
		helpBtn.setBounds(932, 606, 97, 25);
		frmWikipediaSearchEngine.getContentPane().add(helpBtn);
		/*
		helpBtn.addActionListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					//TODO open pop up
				}
			}
		});
		*/
		
		JLabel lblNewLabel_3 = new JLabel("Please insert your query");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_3.setBackground(new Color(204, 255, 255));
		lblNewLabel_3.setForeground(new Color(128, 128, 128));
		lblNewLabel_3.setBounds(100, 41, 460, 16);
		frmWikipediaSearchEngine.getContentPane().add(lblNewLabel_3);
	}
}
