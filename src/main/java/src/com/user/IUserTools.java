package src.com.user;

import java.util.List;
import java.util.Map;

public interface IUserTools {
  Map<String, User> convertUsersDataList(List<User> users);
  Map<String, User> getUsersInfo(Map<String, User> users);
}
