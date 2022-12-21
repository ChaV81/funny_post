package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Entite.Post;

public class Derby {
	private String url = "jdbc:derby:funnyPost;create=true";
	private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private String user = "cha";
	private String pwd = "123";
	private Connection cn;
	
	// Constructeur
	public Derby() {
		try {
			Class.forName(driver);
			this.cn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void ConnectBDD() {
		System.out.println("Connection à la bdd ok");
		Statement st = null;
		try {
			st = this.cn.createStatement();
			//st.execute("drop table posts");
			//st.execute("create table posts (id int, user_id int, title varchar(254), body varchar(254))");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//st.execute("create table posts (id int, user_id int, title varchar(254), body varchar(254))");
		
	}
	
	public void insert(int id, int user_id, String title, String body) {
		try {
			String query = "insert into posts values (?, ?, ?, ?)";
			PreparedStatement st = this.cn.prepareStatement(query);
			st.setInt(1, id);
			st.setInt(2, user_id);
			st.setString(3, title);
			st.setString(4, body);
			st.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	// pas de void mais une liste de post
	//  créer une liste de posts
	
	public List<Post> selectAll() {
	    List<Post> postsList = new ArrayList<Post>();
		try {
			Statement st = this.cn.createStatement();
			ResultSet rs = st.executeQuery("select * from posts"); 
			// a chaque next créer un post et l'inclure dans ma liste
			while(rs.next()) {
				int id_post = rs.getInt("id");
				int id_user = rs.getInt("user_id");
				String title = rs.getString("title");
				String body = rs.getString("body");
				Post post = new Post(id_post, id_user, title, body);
				postsList.add(post);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return postsList;
	}
	
	public void insertFromWS(JSONArray json_array) {
		//Bouclé sur le json array
		//pour chaque entré faire un insert dans posts
		for (int i = 0; i < json_array.length(); i++) {
		  JSONObject obj;
		try {
			obj = json_array.getJSONObject(i);
			int id = obj.getInt("id");
			int user_id = obj.getInt("userId");
			String title = obj.getString("title");
			String body = obj.getString("body");
			this.insert(id, user_id, title, body);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
}
