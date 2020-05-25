package searching;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;

public class Sorter {
	private ScoreDoc [] hits;
	private Document [] sortedResults;
	private IndexSearcher iSearcher;
	/*
	 * CONSTRUCTOR
	 */
	
	public Sorter(ScoreDoc[] hits, Document[] sortedResults, IndexSearcher iSearcher) {
		super();
		this.hits = hits;
		this.sortedResults = sortedResults;
		this.iSearcher = iSearcher;
	}

	public Sorter() {
		super();
	}

	/*
	 * SETTERS and GETTERS
	 */
	public ScoreDoc[] getHits() {
		return hits;
	}

	public void setHits(ScoreDoc[] hits) {
		this.hits = hits;
	}
	
	public Document[] getSortedResults() {
		return sortedResults;
	}

	public void setSortedResults(Document[] sortedResults) {
		this.sortedResults = sortedResults;
	}
	
	public IndexSearcher getiSearcher() {
		return iSearcher;
	}

	public void setiSearcher(IndexSearcher iSearcher) {
		this.iSearcher = iSearcher;
	}

	/*
	 * SORTING methods
	 */
	
	public Document[] scoreSort() throws IOException {
		for (int i = 0; i < hits.length; i++){
			Document hitDoc = iSearcher.doc(hits[i].doc);
			sortedResults[i] = hitDoc;
		}
		return sortedResults;
	}
	
	public Document[] alphabeticalSort() throws IOException {
		for (int i = 0; i < hits.length; i++) 
        {
            for (int j = i + 1; j < hits.length; j++) { 
            	if (iSearcher.doc(hits[i].doc).getField("title").toString().compareTo(iSearcher.doc(hits[j].doc).getField("title").toString())>0) 
                {
                    ScoreDoc temp = hits[i];
                    hits[i] = hits[j];
                    hits[j] = temp;
                }
            }
        }
		
		for (int i = 0; i < hits.length; i++){
			Document hitDoc = iSearcher.doc(hits[i].doc);
			sortedResults[i] = hitDoc;
		}
		return sortedResults;
	}
	
	public Document[] sizeSort() throws IOException {
		for (int i = 0; i < hits.length; i++) 
        {
            for (int j = i + 1; j < hits.length; j++) { 
            	if (iSearcher.doc(hits[i].doc).getField("text").toString().length() > 
            			iSearcher.doc(hits[j].doc).getField("text").toString().length()) 
                {
                    ScoreDoc temp = hits[i];
                    hits[i] = hits[j];
                    hits[j] = temp;
                }
            }
        }
		
		for (int i = 0; i < hits.length; i++){
			Document hitDoc = iSearcher.doc(hits[i].doc);
			sortedResults[i] = hitDoc;
		}
		return sortedResults;
	}
}
