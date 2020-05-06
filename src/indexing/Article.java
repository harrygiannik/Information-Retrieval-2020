package indexing;

public class Article {
	private String link;
	private String title;
	private String text;
	
	public Article(String link, String title, String text) {
		super();
		this.link = link;
		this.title = title;
		this.text = text;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
