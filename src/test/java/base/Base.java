package base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	public static RequestSpecification req;
	public static String method;

	public Base() {
		prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
			prop.load(fis);
		} catch (IOException e) {}
	}

	public static WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		return driver;
	}

	public static WebDriver getDriverReference() {
		return driver;
	}

	public static String getMoneyLionURL() {
		String returnURL = "https://www.moneylion.com/";
		if(prop!=null)
			returnURL = prop.getProperty("moneylion_url");
		return returnURL;
	}

	public static String getBaseURL() {
		String returnURL = "https://restful-booker.herokuapp.com";
		if(prop!=null)
			returnURL = prop.getProperty("baseUrl");
		return returnURL;
	}


	public RequestSpecification requestSpecsification() throws IOException
	{
		if(req==null)
		{
			PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
			req=new RequestSpecBuilder().setBaseUri(prop.getProperty("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	public String getJsonPath(Response response,String key)
	{
		String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		Base.method = method;
	}

}
