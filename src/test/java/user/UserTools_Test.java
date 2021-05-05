package user;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import org.junit.Test;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import src.com.user.IUserTools;
import src.com.user.User;
import src.com.user.UserTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTools_Test {

  @Test
  public void convertUserDataList() {
    IUserTools userTools = new UserTools();

    List<User> testUsers = new ArrayList<>();

    testUsers.add(new User("Test-1", new ArrayList<String>(){{add("test1@gmail.com"); add("test2@gmail.com");}}));
    testUsers.add(new User("Test-2", new ArrayList<String>(){{add("test4@gmail.com");}}));
    testUsers.add(new User("Test-3", new ArrayList<String>(){{add("test2@gmail.com"); add("test7@gmail.com");}}));

    Map<String, User> actualData = userTools.convertUsersDataList(testUsers);

    Map<String, User> expectedData = new HashMap();
    expectedData.put("test1@gmail.com", new User("Test-1", new ArrayList<String>(){{add("test2@gmail.com");}}));
    expectedData.put("test2@gmail.com", new User("Test-3", new ArrayList<>()));
    expectedData.put("test4@gmail.com", new User("Test-2", new ArrayList<>()));
    expectedData.put("test7@gmail.com", new User("Test-3", new ArrayList<>()));

    for(Map.Entry<String, User> entry : actualData.entrySet()) {
      assertReflectionEquals(entry.getValue(), expectedData.get(entry.getKey()), ReflectionComparatorMode.LENIENT_ORDER);
    }
  }

  @Test
  public void convertUserDataListWithEmptyUsersList() {
    IUserTools userTools = new UserTools();
    List<User> testUsers = new ArrayList<>();

    Map<String, User> actualData = userTools.convertUsersDataList(testUsers);

    assert actualData.isEmpty(): "User data list doesn't empty";
  }

  @Test
  public void convertUserDataListWithEmptyEmails() {
    IUserTools userTools = new UserTools();
    List<User> testUsers = new ArrayList<>();

    testUsers.add(new User("Test-1", new ArrayList<>()));
    testUsers.add(new User("Test-2", new ArrayList<>()));
    testUsers.add(new User("Test-3", new ArrayList<>()));

    Map<String, User> actualData = userTools.convertUsersDataList(testUsers);

    Map<String, User> expectedData = new HashMap();
    expectedData.put("test1@gmail.com", new User("Test-1", new ArrayList<>()));
    expectedData.put("test2@gmail.com", new User("Test-3", new ArrayList<>()));
    expectedData.put("test4@gmail.com", new User("Test-2", new ArrayList<>()));
    expectedData.put("test7@gmail.com", new User("Test-3", new ArrayList<>()));

    for(Map.Entry<String, User> entry : actualData.entrySet()) {
      assertReflectionEquals(entry.getValue(), expectedData.get(entry.getKey()), ReflectionComparatorMode.LENIENT_ORDER);
    }
  }

  @Test
  public void getUsersInfo() {
    IUserTools userTools = new UserTools();

    Map<String, User> testData = new HashMap();
    testData.put("test1@gmail.com", new User("Test-1", new ArrayList<String>(){{add("test2@gmail.com");}}));
    testData.put("test2@gmail.com", new User("Test-3", new ArrayList<>()));
    testData.put("test4@gmail.com", new User("Test-2", new ArrayList<>()));
    testData.put("test7@gmail.com", new User("Test-3", new ArrayList<>()));

    Map<String, User> actualData = userTools.getUsersInfo(testData);

    Map<String, User> expectedData = new HashMap();
    expectedData.put("Test-3", new User("Test-3", new ArrayList<String>(){{add("test1@gmail.com"); add("test7@gmail.com"); add("test2@gmail.com");}}));
    expectedData.put("Test-2", new User("Test-2", new ArrayList<String>(){{add("test4@gmail.com");}}));

    for(Map.Entry<String, User> entry : actualData.entrySet()) {
      assertReflectionEquals(entry.getValue(), expectedData.get(entry.getKey()), ReflectionComparatorMode.LENIENT_ORDER);
    }

  }

  @Test
  public void getUsersInfoWithEmptyUsersData() {
    IUserTools userTools = new UserTools();

    Map<String, User> testData = new HashMap();
    Map<String, User> actualData = userTools.getUsersInfo(testData);

    assert actualData.isEmpty(): "User data list doesn't empty";
  }
}
