package src.com.user;

import java.util.*;

public class UserTools implements IUserTools {

  @Override
  public Map<String, User> convertUsersDataList(List<User> users) {
    Map<String, User> usersMap = new HashMap<>();

    for(User usr: users) {
      for(String email: usr.getEmails()) {
        usersMap.put(email, usr);
      }
    }

    usersMap.forEach((key, value) -> {
      List<String> emails = value.getEmails();
      emails.remove(key);
    });

    return usersMap;
  }

  private Map<String, User> mergeUserDataList(String key, Map<String, User> users) {
    if(!users.get(key).getEmails().isEmpty()) {
      mergeUserDataList(users.get(key).getEmails().get(0), users);
    }

    List<String> userEmails = users.get(key).getEmails();
    if(!userEmails.isEmpty()) {
      User usr = users.get(userEmails.get(0));
      users.put(key, new User(usr.getName(), userEmails));
    }

    return users;
  }

  @Override
  public Map<String, User> getUsersInfo(Map<String, User> users) {
    for(String k: users.keySet()) {
      this.mergeUserDataList(k, users);
    }

    Map<String, User> info = new HashMap<>();
    for(Map.Entry<String, User> entry: users.entrySet()) {
      if(info.containsKey(entry.getValue().getName())) {
        Set<String> emails = new HashSet<>(info.get(entry.getValue().getName()).getEmails());
        emails.add(entry.getKey());
        info.put(entry.getValue().getName(), new User(entry.getValue().getName(), new ArrayList<>(emails)));
      } else {
        List<String> emails = new ArrayList<>();
        emails.add(entry.getKey());
        info.put(entry.getValue().getName(), new User(entry.getValue().getName(), emails));
      }
    }

    return info;
  }
}
