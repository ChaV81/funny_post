package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import Entite.Post;
import Service.Derby;
import Service.WebService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller implements Initializable{
	private Derby derby;
	private WebService webS;
	
	@FXML
	private ListView listContainer;
	
	@FXML 
	private Button displayBtn;
	
	@FXML 
	private Button wsBtn;
	
	@FXML 
	private Button insertBtn;
	
	@FXML
	private Label idPostLabel;
	
	@FXML
	private TextField idPostInput;
	
	@FXML
	private Label idUserLabel;
	
	@FXML
	private TextField idUserInput;
	
	@FXML
	private Label titleLabel;
	
	@FXML
	private TextField titleInput;
	
	@FXML
	private Label bodyLabel;
	
	@FXML
	private TextField bodyInput;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.derby = new Derby();
		this.derby.ConnectBDD();
	}
	
	public void insertData() {
		int id_post = Integer.parseInt(this.idPostInput.getText());
		int id_user = Integer.parseInt(this.idUserInput.getText());
		String title = this.titleInput.getText();
		String body = this.bodyInput.getText();	
		this.derby.insert(id_post, id_user, title, body);
		System.out.println("Insertions effectuée avec succès !");
		this.idPostInput.setText("");
		this.idUserInput.setText("");
		this.titleInput.setText("");
		this.bodyInput.setText("");
		}
	
	public void displayData() {
		//code
		for(Post post : derby.selectAll())
        {
			this.listContainer.getItems().add(post.getId() +". "+post.getTitle()+" - "+post.getBody());
        }
	}
	
	public void dataFromWebService() {
		this.webS = new WebService();
		try {
			this.derby.insertFromWS(this.webS.getPosts());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
