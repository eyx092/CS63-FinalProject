package project.assistant.app.todo;

import java.util.Arrays;
import java.util.List;

import project.assistant.app.Action;
import project.assistant.app.App;

public class ToDoApp extends App{
	@Override
	public List<Action> getActions(){
		return Arrays.asList(new ToDoAction());
	}
}