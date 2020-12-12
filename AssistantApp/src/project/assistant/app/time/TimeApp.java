package project.assistant.app.time;
import project.assistant.app.App;
import project.assistant.app.Action;

import java.util.List;
import java.util.Arrays;

public class TimeApp extends App {
	public List<Action> getActions() {
		return Arrays.asList( new GetTimeAction() );
	}
}

