package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;

public class WebService {
	public WebService() {
		// Empy
	}
	
	public JSONArray getPosts() throws IOException {
		JSONArray arrayWS = null;
		URL url = new URL("https://jsonplaceholder.typicode.com/posts");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setConnectTimeout(20000);
		connection.setReadTimeout(20000);
		connection.setUseCaches(true);
		connection.setRequestMethod("GET");
		
		// Set headers
		connection.setRequestProperty("Accept", "application/xml");
		connection.setRequestProperty("Content-Type", "application/xml");
		
		int responseCode = connection.getResponseCode();
		if (responseCode == 400) {
			System.out.println("Erreur client !!");
		}else if(responseCode == 500) {
			System.out.println("Erreur serveur !!");
		}else if (responseCode == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			
			StringBuilder sb = new StringBuilder();
			String inputLine;
			while((inputLine = in.readLine()) != null) {
				sb.append(inputLine.toString());
			}
			String jsonString = sb.toString();
			
			try {
				arrayWS = new JSONArray(jsonString);
				System.out.println(arrayWS);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return arrayWS;
	}
}
