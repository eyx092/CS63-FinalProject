package project.assistant.app.fact;

import java.util.Arrays;
import java.util.List;

import project.assistant.app.Action;
import project.assistant.app.App;

public class FactApp extends App{

  @Override
  public List<Action> getActions() {
    return Arrays.asList(new GetFactAction());
  }
}
