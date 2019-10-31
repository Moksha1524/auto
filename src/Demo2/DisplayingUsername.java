package Demo2;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DisplayingUsername {
	Properties prop= new Properties(); 
	String Username;
	@Test
	public void searchTweet() throws IOException
	{
		FileInputStream fs=new FileInputStream("C:\\Users\\Online Test\\Documents\\APIP\\src\\Demo2\\Demo1.properties");
		prop.load(fs);
		RestAssured.baseURI="https://api.twitter.com/1.1/search/";
		Response res=given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
	    queryParam("q","Qualitest").
		when().get("/tweets.json").
		then().assertThat().extract().response();
		
		String response=res.asString();
		JsonPath js=new JsonPath(response);
		
		
		int count=js.get("statuses.size()");
		System.out.println(count);
		for(int i=0;i<count;i++)
		{
			Username=js.get("statuses["+i+"].user.screen_name");
			System.out.println(Username);
		}

}
}
