package src.com.report;

import src.com.user.User;

import java.util.Map;

public class ConsoleReport implements IReport {

  private Map<String, User> usersData;

  public ConsoleReport(Map<String, User> usersData) {
    this.usersData = usersData;
  }

  @Override
  public void print() {
    for(Map.Entry<String, User> entry: usersData.entrySet()) {
      String message = String.format("%s -> %s", entry.getKey(), String.join(", ", entry.getValue().getEmails()));
      System.out.println(message);
    }
  }
}
