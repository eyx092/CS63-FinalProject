package project.assistant.app.todo;

import project.assistant.Assistant;
import project.assistant.app.Action;
import project.assistant.app.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToDoAction extends Action{
	
	static ArrayList<String> toDo = new ArrayList<String>();
	
	public void doCommand(String command) {
		
		Assistant assistant = Assistant.getInstance();
		System.out.println("todo has been run");
		List<String> words = Arrays.asList(command.split(" "));
		ArrayList<String> item_arr = new ArrayList<String>();
		if (words.size() == 0) {
			assistant.displayItem(new Response("Sorry, I couldn't understand your command"));
		}
		else {
			for(int i = 2; i<words.size(); i++) {
				item_arr.add(words.get(i));
			}
			String item = "";
			for (int i = 0; i<item_arr.size(); i++) {
				item += item_arr.get(i);
				item += " ";
			}
			
			if(words.get(1).equals("add")) {
				toDo.add(item);
				assistant.displayItem(new Response("Added "+item+" to your to-do list."));
				
			}
			else if(words.get(1).equals("view")) {
				assistant.displayItem(new Response("Your to-do list:"));
				if (toDo.size() == 0) {
					assistant.displayItem(new Response("Your to-do list is empty."));
				}
				else {
					for(int i = 0; i<toDo.size(); i++) {
						assistant.displayItem(new Response("- "+toDo.get(i)));
					}
				}
			}
			else if(words.get(1).equals("remove")) {
				boolean removedItem = false;
				for(int i = 0; i<toDo.size(); i++) {
					if(item.equals(toDo.get(i))) {
						toDo.remove(i);
						removedItem = true;
						assistant.displayItem(new Response("Removed "+item+" from your to-do list."));
						break;
					}
				}
				if(removedItem == false) {
					assistant.displayItem(new Response("Couldn't find that on your to-do list."));
				}
			}
			else {
				assistant.displayItem(new Response("Sorry, I couldn't understand your command"));
			}
		}
		
	}
	
	String[] keywords = {"todo", "to-do", "list", "to"};
	double[] scores = {3, 3, 0.5, 0.2};
	
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