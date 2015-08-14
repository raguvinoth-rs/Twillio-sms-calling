package twilio.java.proj;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.instance.Sms;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;
 
public class SmsSender extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	
 // Find your Account Sid and Token at twilio.com/user/account 
 public static final String ACCOUNT_SID = "AC098e1ebf7b1ba0abf81badd2d75989bc"; 
 public static final String AUTH_TOKEN = "0381804fde6edbe469a41b20ba782189"; 
 
 public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException { 
	
	 try{
             
             
	
 // Find your Account Sid and Token at twilio.com/user/account 
        //public static final 
        //        String ACCOUNT_SID = "AC098e1ebf7b1ba0abf81badd2d75989bc"; 
        //public static final 
        //        String AUTH_TOKEN = "0381804fde6edbe469a41b20ba782189"; 
	 
	 TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN); 
 
	 // Build the parameters 
	 //List<NameValuePair> params = new ArrayList<NameValuePair>();
	
	//call making
	CallFactory callFactory = client.getAccount().getCallFactory();
	 Map<String, String> params = new HashMap<String, String>();
	 /**params.add(new BasicNameValuePair("To", "+18179025865")); 
	 params.add(new BasicNameValuePair("From", "+12059286869")); 
	 params.add(new BasicNameValuePair("Body", "Hello")); */
	 params.put("To", "+18179025865"); 
	 params.put("From", "+12059286869"); 
	 params.put("Url", "http://demo.twilio.com/welcome/voice/");
	 final Call call = callFactory.create(params);
	 System.out.println(call.getSid());
 
	 //SmsFactory messageFactory = client.getAccount().getSmsFactory(); 
	 //Sms message = messageFactory.create(params); 
	 //System.out.println(message.getSid()); 
	 
	 //sms sender
	SmsFactory msgFactory = client.getAccount().getSmsFactory();
	Map<String, String> smsparams = new HashMap<String, String>();
    smsparams.put("To", "+18179025865"); 
	smsparams.put("From", "+12059286869"); 
	smsparams.put("Body", "Hello");
	Sms message = msgFactory.create(smsparams); 
    System.out.println(message.getSid());

	 }
	 catch (TwilioRestException e) {

			e.printStackTrace();
		}
	
	 
	 TwiMLResponse twiml = new TwiMLResponse();
     Say say = new Say("Hello");
     try {
         twiml.append(say);
     } catch (TwiMLException e) {
         e.printStackTrace();
     }

     response.setContentType("application/xml");
     response.getWriter().print(twiml.toXML());
	 
	 
 } 
}