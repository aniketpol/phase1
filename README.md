# phase1

<b>Description:</b>

Phase I impelments the phonebuzz implementation using twilio TwiML <br>
I have written code for x-twilio-signature validation which is commented.<br>


<b>Steps to Deploy</b><br>
[1] Clone or Download the project from the repository<br>
[2] Run a maven "Clean install" command on the above project which will generate "phase1.war" in target folder<br>
[3] Deploy phase1.war on tomcate<br>
[4] Download ngrok "https://ngrok.com/download" and follow installation steps "https://ngrok.com/docs" and make it tunnel to   your http 8080 port<br>
[5] Copy the new public url from ngrok terminal <br>
[6] Login to the your twilio account and set the "https://xxxxxxx/phase1/twiml" as your voice url where xxxx is the generated ngrok url<br>
[7] You are all set to test phonebuzz and make a call to your twilio account phone number<br><br>
<b>Technology Used:</b> Java Servlet,Maven,ngrok server,apcahe tomcate 7 server



