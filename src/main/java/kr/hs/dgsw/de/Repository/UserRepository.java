package kr.hs.dgsw.de.Repository;

import kr.hs.dgsw.de.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String name);

    //Optional<User> findByName(String name);

}
