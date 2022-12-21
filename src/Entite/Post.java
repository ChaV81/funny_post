package Entite;

public class Post {
	private int id;
	private int user_id;
	private String title;
	private String body;
	
	// Constructeur
	public Post(int id, int user_id, String title, String body) {
		this.id = id;
		this.user_id = user_id;
		this.title = title;
		this.body = body;
	}
	
	// Getter and Setter
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
