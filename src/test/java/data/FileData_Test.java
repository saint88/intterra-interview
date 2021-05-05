package data;

import org.junit.Test;
import src.com.data.FileData;
import src.com.data.IData;
import src.com.user.User;

import java.io.IOException;
import java.util.List;

public class FileData_Test {

  private IData fileData = new FileData("data.txt");

  @Test
  public void getDataFromResourceFile() throws IOException {
    IData fileData = new FileData("data.txt");

    List<User> actualUserData = fileData.getData();
    assert !actualUserData.isEmpty(): "Error: Data from resource file is empty";
  }

  @Test
  public void getDataFromNotExistResourceFile() throws IOException {
    IData fileData = new FileData("test");
    List<User> actualUserData = fileData.getData();

    assert actualUserData.isEmpty(): "Error: Data from resource file doesn't empty";
  }

}
