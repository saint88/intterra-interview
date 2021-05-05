package src.com.data;

import src.com.user.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class FileData implements IData {

  private String fileName = "";

  public FileData(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public List<User> getData() throws IOException {
    InputStream userStream = getClass().getClassLoader().getResourceAsStream(fileName);

    List<User> users = new ArrayList<>();

    if(userStream == null) {
      return users;
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(userStream)));
    while(reader.ready()) {
      String line = reader.readLine();

      if(line.isEmpty()) break;
      String[] userInfoFromLine = line.split("->");
      List<String> emails = Arrays.stream(userInfoFromLine[1].trim().split(","))
          .map(String::trim)
          .collect(Collectors.toList());

      users.add(new User(userInfoFromLine[0].trim(), emails));
    }
    users.sort(Comparator.comparing(User::getName));

    return users;
  }
}
