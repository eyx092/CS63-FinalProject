package project.assistant.app.mail;

import java.util.Arrays;
import java.util.List;

import project.assistant.app.Action;
import project.assistant.app.App;

public class MailApp extends App{
	public List<Action> getActions() {
		return Arrays.asList(new SendMailAction());
	}
}
