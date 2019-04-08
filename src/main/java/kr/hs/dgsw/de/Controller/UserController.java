package kr.hs.dgsw.de.Controller;

import kr.hs.dgsw.de.Domain.User;
import kr.hs.dgsw.de.Service.CommentService;
import kr.hs.dgsw.de.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/user/init")
    public User initUser(@RequestBody User user){//RequestBody는 객체를 받아올 수 있다.
        return this.userService.addUser(user);
    }

    @GetMapping("/user/view")
    public List<User> viewUsers(){
        return this.userService.listAllUsers();
    }

    @DeleteMapping("/user/delete/{id}")
    public boolean deleteUser(@PathVariable  Long id){return this.userService.deleteUser(id);}

    @PutMapping("/user/modi")
    public String modifiUser(@RequestBody User user){
        return this.userService.modifiUser(user) ;
    }

    @PostMapping("/user/login")
    public boolean loginUser(@RequestBody User user){return this.userService.loginUser(user);}

}
