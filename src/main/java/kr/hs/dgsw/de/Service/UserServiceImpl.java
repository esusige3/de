package kr.hs.dgsw.de.Service;

import kr.hs.dgsw.de.Domain.User;
import kr.hs.dgsw.de.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        //this.userRepository.findByEmail(user.getEmail()).map(found->null).orElse(this.userRepository.save(user))

        Optional<User> found = this.userRepository.findByEmail(user.getEmail());
        if(found.isPresent())
            return null;
        return this.userRepository.save(user);
    }

    @Override
    public List<User> listAllUsers() {
        List<User> UserList = this.userRepository.findAll();
        return UserList;
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<User> found = this.userRepository.findById(id);
        if(found.isPresent()){
            this.userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public String modifiUser(User user) {
        this.userRepository.findByEmail(user.getEmail()).map(found->{
                found.setUsername(user.getUsername());
                found.setEmail(user.getEmail());
                return "변경완료";
            }
        ).orElse("실패");
        return "변경실패";
    }

    @Override
    public User loginUser(User user) {
        /*if((this.userRepository.findByUsername(user.getUsername()).isPresent()&&this.userRepository.findByEmail(user.getEmail()).isPresent())) {

            return true;
        }*/
        Optional<User> found = this.userRepository.findByUserId(user.getUserId());
        if(found.isPresent()){
            User find = found.get();
            if(find.getPassword().equals(user.getPassword())){
                return find;
            }
        }
        return null;
    }
}
