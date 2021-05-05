package src.com;

import src.com.data.FileData;
import src.com.report.ConsoleReport;
import src.com.report.IReport;
import src.com.user.IUserTools;
import src.com.user.User;
import src.com.user.UserTools;

import java.io.IOException;
import java.util.*;

public class App {

  public static void main(String... args) {

    try {
      List<User> users = new FileData("data.txt").getData();
      IUserTools userTools = new UserTools();

      Map<String, User> usersData = userTools.convertUsersDataList(users);
      Map<String, User> usersInfo = userTools.getUsersInfo(usersData);

      IReport reporter = new ConsoleReport(usersInfo);
      reporter.print();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
