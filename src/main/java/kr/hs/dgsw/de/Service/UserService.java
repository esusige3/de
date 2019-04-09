package kr.hs.dgsw.de.Service;

import kr.hs.dgsw.de.Domain.User;

import java.util.List;

public interface UserService {
   User addUser(User user);
   List<User> listAllUsers();
   boolean deleteUser(Long id);
   String modifiUser(User user);
   User loginUser(User user);
}
