package Demo2;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Block {
	Properties prop= new Properties();
	
	@Test
	public void blockUser() throws IOException
	{
		RestAssured.baseURI="https://api.twitter.com/1.1/blocks/";
		FileInputStream fs=new FileInputStream("C:\\Users\\Online Test\\Documents\\APIP\\src\\Demo2\\Demo1.properties");
		prop.load(fs);
		Response res=given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
		queryParam("screen_name","narendramodi").
		when().post("/create.json").then().extract().response();
		
		String response=res.asString();
		System.out.println(response);
	}
}
