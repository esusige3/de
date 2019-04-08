package kr.hs.dgsw.de.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity//데이터베이스에 테이블로 생성
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String email;

    private String profilePathName;




    @CreationTimestamp
    private LocalDateTime joined;
    @UpdateTimestamp
    private LocalDateTime modified;


    public User(String username, String email){
        this.username = username;
        this.email = email;
    }
}
