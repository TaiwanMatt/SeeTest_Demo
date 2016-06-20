import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
*
*/
//@RunWith(value = Parameterized.class)
public class EribankTest {
//	@Parameters
//	public static List<Object[]> data(){
//		Object[][] data = new Object[][] {{"Olsen"}};
//		return Arrays.asList(data);
//	}
  private String host = "localhost";
  private int port = 8889;
  private String projectBaseDirectory = "C:\\Users\\Matt\\workspace\\project2";
  protected Client client = null;
//  private String device;
//  public EribankTest(String arg){
//	  device=arg;
//  }
  @Before
  public void setUp(){
      client = new Client(host, port, true);
      client.setProjectBaseDirectory(projectBaseDirectory);
      File reports=new File("C:\\Users\\Matt\\Desktop","reports");
      reports.mkdirs();
      client.setReporter("xml", reports.getAbsolutePath(), "EribankTest");
  }

  @Test
  public void testUntitled(){
//	  Properties properties = new Properties();
//	  String configFile = "config.properties";
//	  try {
//		properties.load(new FileInputStream(configFile));
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	  String device=properties.getProperty("dev");
//	  String device=System.getProperty("device");
//	  System.out.println(device);
      client.setDevice("adb:Olsen");
      client.launch("com.experitest.ExperiBank/.LoginActivity", true, true);
      client.elementSendText("NATIVE", "xpath=//*[@hint='Username']", 0, "company");
      client.elementSendText("NATIVE", "hint=Password", 0, "company");
      client.click("NATIVE", "xpath=//*[@id='makePaymentView']", 0, 1);
      client.click("NATIVE", "text=Login", 0, 1);
      client.elementListSelect("", "text=Logout", 0, false);
      client.click("NATIVE", "text=Logout", 0, 1);
  }

  @After
  public void tearDown(){
      // Generates a report of the test case.
      // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
      client.generateReport(false);
      // Releases the client so that other clients can approach the agent in the near future. 
      client.releaseClient();
  }
}
