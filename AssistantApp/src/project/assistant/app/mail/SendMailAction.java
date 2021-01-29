package project.assistant.app.mail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;


import project.assistant.Assistant;
import project.assistant.app.Action;
import project.assistant.app.Response;


public class SendMailAction extends Action{
	String[] keywords = {"mail", "email"};
	double[] scores = {3, 3};
	
	@Override
	public void doCommand(String command) {
		Assistant assistant = Assistant.getInstance();
		assistant.displayItem(new Response("Processing..."));
		List<String> words = Arrays.asList(command.split(" "));
		final String reciever = words.get(1);
		final String sender = words.get(2);
		final String password = words.get(3);
		String message = "";
		for (int i = 4; i<words.size(); i++) {
			message += words.get(i);
			message += " ";
		}
		final String subject = "Virtual Assistant Message";
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});
		
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sender));
			msg.setRecipients(
					Message.RecipientType.TO,
					InternetAddress.parse(reciever)
			);
			msg.setSubject(subject);
			msg.setText(message);
			Transport.send(msg);
			
			System.out.println("Message sent with no errors");
			assistant.displayItem(new Response("Successfully sent message to: "+reciever));
		}
		catch (MessagingException e) {
			assistant.displayItem(new Response("An error occured."));
			e.printStackTrace();
		}
	}

	@Override
	public double getLikelihood(String command) {
		double score = 0;
		for(int i = 0; i<keywords.length; i++) {
			String keyword = keywords[i];
			if(command.contains(keyword)) {
				score += scores[i];
			}
		}
		return score;
	}
	
}
