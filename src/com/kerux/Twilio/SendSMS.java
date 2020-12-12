package com.kerux.Twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SendSMS {

	  public static final String ACCOUNT_SID = "AC72e97aa58b85594605b8bf7cda7251c2";
	  public static final String AUTH_TOKEN = "80fbcced70691f305a57f61459430562";

	  public static void main(String[] args) {
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Message message = Message.creator(new PhoneNumber("+639173257134"),
	        new PhoneNumber("+12513177901"), 
	        "This is the ship that made the Kessel Run in fourteen parsecs?").create();

	    System.out.println(message.getSid());
	  }
	
	 
}
