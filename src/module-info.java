module funny_post {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires java.json;
	
	opens application to javafx.graphics, javafx.fxml;
}
