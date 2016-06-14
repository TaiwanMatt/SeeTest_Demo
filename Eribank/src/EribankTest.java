import static org.junit.Assert.*;

import org.junit.Test;

//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
*
*/
public class EribankTest {
  private String host = "localhost";
  private int port = 8889;
  //private String projectBaseDirectory = "C:\\Users\\SeeTest\\workspace\\project2";
  protected Client client = null;

  @Before
  public void setUp(){
      client = new Client(host, port, true);
      //client.setProjectBaseDirectory(projectBaseDirectory);
      client.setReporter("xml", "reports", "Untitled");
  }

  @Test
  public void testUntitled(){
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
