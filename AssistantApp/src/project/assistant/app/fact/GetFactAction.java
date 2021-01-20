package project.assistant.app.fact;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import project.assistant.Assistant;
import project.assistant.app.Action;
import project.assistant.app.Response;

public class GetFactAction extends Action {
	String[] keywords = { "fact", "date", "about", "random" };
	double[] scores =   {3, 0.4, 0.2, 0.2};
	
	@Override
	public void doCommand(String command) {
		Assistant assistant = Assistant.getInstance();
		URL url = null;
		try {
			url = new URL("http://numbersapi.com/random/date");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	    Scanner sc = null;
		try {
			sc = new Scanner(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		
		while (sc.hasNext()) {
			sb.append(sc.next());
			sb.append(" ");
		}
		
		String out = "Here's your random date fact: "+sb;
		
		if (out.length() > 20) {
			assistant.displayItem(new Response(out.substring(0, out.length()/2)));
			assistant.displayItem(new Response(out.substring(out.length()/2, out.length())));
		}
		else {
			assistant.displayItem(new Response(out));
		}
		
		
	}
	
	
	
	@Override
	public double getLikelihood(String command) {
		double score = 0;
		for (int i = 0; i<keywords.length; i++) {
			String keyword = keywords[i];
			if (command.contains(keyword)) {
				score += scores[i];
			}
		}
		return score;
	}
}