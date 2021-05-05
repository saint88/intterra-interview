package src.com.data;

import src.com.user.User;

import java.io.IOException;
import java.util.List;

public interface IData {
  List<User> getData() throws IOException;
}
