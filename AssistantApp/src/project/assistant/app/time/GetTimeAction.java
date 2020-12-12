package project.assistant.app.time;
import java.time.LocalTime;
import project.assistant.app.Action;
import project.assistant.Assistant;
import project.assistant.app.Response;


public class GetTimeAction extends Action{
	public void doCommand(String command) {
		Assistant assistant = Assistant.getInstance();
		
		String result = LocalTime.now().toString();
		
		assistant.displayItem(new Response("Local time: "+result));
	}
	String[] keywords = { "time", "what", "is", "it", "now", "right" };
	double[] scores =   { 3,          0.1,    0.1,  0.1, 0.1, 0.1 };
	
	public double getLikelihood(String command) {
		double score = 0;
		for (int i = 0; i < keywords.length; i++) {
			String keyword = keywords[i];
			if (command.contains(keyword)) {
				score += scores[i];
			}
		}
		return score;
	}
}
