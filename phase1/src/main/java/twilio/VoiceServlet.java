package twilio;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.security.RequestValidator;
import com.twilio.twiml.Gather;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;


/**
 * 
 * @author Aniket
 * This class is responsible for generating Say verb of Twilio which will actually read out fizzbuzz output
 */
public class VoiceServlet extends HttpServlet {

	public static final String ACCOUNT_SID = "AC59f43ff40e03a08ab99d60b55418cf87";
	public static final String AUTH_TOKEN = "5ea119a9d762b9e9a8967fd8e3963fa6";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    	
    	 String digit = request.getParameter("Digits");
    	 StringBuffer buff=new StringBuffer();
    	 //validateIncomingRequest(request,buff);
    
    	VoiceResponse.Builder builder = new VoiceResponse.Builder();

        int digitsInIntegers=0;
        if(digit!=null){
        	digitsInIntegers=Integer.parseInt(digit);
        }
        if (digitsInIntegers != 0) {
        	generateFizzBuzzString(digitsInIntegers,buff);
        	builder.say(new Say.Builder(buff.toString()).build());
        } else {
            appendGather(builder);
        }

        response.setContentType("application/xml");
        try {
            response.getWriter().print(builder.build().toXml());
        } catch (TwiMLException e) {
            throw new RuntimeException(e);
        }
    	
    
    }
    
    /**
     * Method generates fizzbuzz output until the passed digits and fills it in string buffer variable
     * @param digitsInIntegers
     * @param buff
     */
    private void generateFizzBuzzString(int digitsInIntegers, StringBuffer buff) {
    	for(int i=1;i<=digitsInIntegers;i++){          
    		if(i%3==0 && i%5==0){
    			buff.append("Fizzbuzz, ");
    		}else if(i%3==0){
    			buff.append("Fizz, ");
    		}else if(i%5==0){
    			buff.append("buzz, ");
    		}else{
    			buff.append(i+",");
    		}
    	}
	}

    /**
     * This method validates to only accept requests coming from twilio account 
     * @param request
     * @param buff
     */
	private void validateIncomingRequest(HttpServletRequest request, StringBuffer buff) {
    	
    	String expectedSignature = request.getHeader("X-Twilio-Signature");
  	    String requestURL=getUrl(request);
        HashMap<String, String> params=getAllParam(request);
        RequestValidator validator=new RequestValidator(AUTH_TOKEN);
        if(validator.validate(requestURL, params, expectedSignature)==false){
        	buff.append("Not a valid Request");
        }
		
	}

	private HashMap<String, String> getAllParam(HttpServletRequest request) {
		HashMap<String, String> map=new HashMap<String,String>();
		
		map.put("AccountSid", request.getParameter("AccountSid"));
		map.put("ApiVersion", request.getParameter("ApiVersion"));
		map.put("CallSid", request.getParameter("CallSid"));
		map.put("CallStatus", request.getParameter("CallStatus"));
		map.put("Called", request.getParameter("Called"));
		map.put("CalledCity", request.getParameter("CalledCity"));
		map.put("CalledCountry", request.getParameter("CalledCountry"));
		map.put("CalledState", request.getParameter("CalledState"));
		map.put("CalledVia", request.getParameter("CalledVia"));
		map.put("CalledZip", request.getParameter("CalledZip"));
		map.put("Caller", request.getParameter("Caller"));
		map.put("CallerCity", request.getParameter("CallerCity"));
		map.put("CallerCountry", request.getParameter("CallerCountry"));
		map.put("CallerState", request.getParameter("CallerState"));
		map.put("CallerZip", request.getParameter("CallerZip"));
		map.put("Digits", request.getParameter("Digits"));
		map.put("Direction", request.getParameter("Direction"));
		map.put("ForwardedFrom", request.getParameter("ForwardedFrom"));
		map.put("From", request.getParameter("From"));
		map.put("FromCity", request.getParameter("FromCity"));
		map.put("FromCountry", request.getParameter("FromCountry"));
		map.put("FromState", request.getParameter("FromState"));
		map.put("FromZip", request.getParameter("FromZip"));
		map.put("To", request.getParameter("To"));
		map.put("ToCity", request.getParameter("ToCity"));
		map.put("ToCountry", request.getParameter("ToCountry"));
		map.put("ToState", request.getParameter("ToState"));
		map.put("ToZip", request.getParameter("ToZip"));
		map.put("msg", request.getParameter("msg"));
		
		return map;
	}

	public static String getUrl(HttpServletRequest req) {
        String reqUrl = req.getRequestURL().toString();
        String queryString = req.getQueryString();   
        if (queryString != null) {
            reqUrl += "?"+queryString;
        }
        return reqUrl;
    }

	/**
	 * This method generates builder message that will be read as a starter 
	 * @param builder
	 */
	private void appendGather(VoiceResponse.Builder builder) {
		builder.gather(new Gather.Builder().finishOnKey("#").timeout(10)
				.say(new Say.Builder("Hello Welcome to PhoneBuzz developed by Aniket Pol as a part of coding challenge for Lend Up,Please enter digits followed by # and we will play fizzbuzz for you until that digit").build()).build()).build();
		
	}
}