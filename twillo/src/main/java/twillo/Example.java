package twillo;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallCreator;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;




public class Example {
  // Find your Account Sid and Token at twilio.com/user/account
  public static final String ACCOUNT_SID = "AC59f43ff40e03a08ab99d60b55418cf87";
  public static final String AUTH_TOKEN = "5ea119a9d762b9e9a8967fd8e3963fa6";

  public static void main(String[] args) {
    //Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    PhoneNumber to=new PhoneNumber("+14809302702");
    PhoneNumber from=new PhoneNumber("+12562026173");
    URI obj=URI.create("https://7b1fe48e.ngrok.io/twillo/twiml");
    Call call=new CallCreator(to, from, obj).create();
    
   // Message msg=Message.creator(to, from, "Hello from aniket it took two days to send this msg!!").create();
   System.out.println(call.getSid());
    
    
    
    
  }
}