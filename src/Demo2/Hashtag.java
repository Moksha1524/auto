package Demo2;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Hashtag {
	Properties prop= new Properties();
	//Logger log=LogManager.getLogger()
	
	@Test
	public void getHastag()throws IOException
	{
		RestAssured.baseURI="https://api.twitter.com/1.1/trends";
		FileInputStream fs=new FileInputStream("C:\\Users\\Online Test\\Documents\\APIP\\src\\Demo2\\Demo1.properties");
		prop.load(fs);
		Response res=given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).	
		when().
		get("/available.json").then().
		extract().response();
		
		String response=res.asString();
		JsonPath js=new JsonPath(response);
		
		int count=js.get("size()");
		for(int i=0;i<count;i++)
		{
			String country=js.get("["+i+"].country").toString();
			if(country.equalsIgnoreCase("India"))
			{
				String id=js.get("["+i+"].parentid").toString();
				Response res1=given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
						param("id",id).
						when().
						get("/place.json").then().
						extract().response();
						
						String response1=res1.asString();
						
						JsonPath js1=new JsonPath(response1);
						
						int count1=js1.get("["+0+"].trends.size()");
						for(int j=0;j<count1;j++)
						{
							String Hashtag = js1.getString("["+0+"].trends["+j+"].name");
							String Hashtag1 = js1.getString("["+0+"].trends["+j+"]").toString();
							if(Hashtag.charAt(0)=='#' && j<=5) 
							{
								System.out.println(Hashtag1);	
							}
								
						}
						break;
			}
			
			
		}		
	}
}
