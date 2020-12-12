package com.kerux.Twilio;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SendNoShow {


	  public final String ACCOUNT_SID = "AC72e97aa58b85594605b8bf7cda7251c2";
	  public final String AUTH_TOKEN = "80fbcced70691f305a57f61459430562";

	  public void Send() {
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Message message = Message.creator(new PhoneNumber("+639178216313"),
	        new PhoneNumber("+12513177901"), 
	        "KERUX QUEUE UPDATE : You didn't show up to when your number was called. You were marked as No Show.").create();

	    System.out.println(message.getSid());
	  }
	

}
