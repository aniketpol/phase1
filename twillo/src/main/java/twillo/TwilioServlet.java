package twillo;



import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

import java.io.IOException;


public class TwilioServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	 VoiceResponse voiceResponse = new VoiceResponse.Builder()
                 .say(new Say.Builder("Hello People ,I am developing this app for the getting internship into Lendup Company").build())
                 .build();

         response.setContentType("application/xml");
         try {
             response.getWriter().print(voiceResponse.toXml());
         } catch (TwiMLException e) {
             e.printStackTrace();
         }
    }
}