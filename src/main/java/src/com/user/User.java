package src.com.user;

import java.util.List;

public class User {

  public User(String name, List<String> emails) {
    this.name = name;
    this.emails = emails;
  }

  private String name;
  private List<String> emails;

  public String getName() {
    return name;
  }

  public List<String> getEmails() {
    return emails;
  }
}
