package kr.hs.dgsw.de.Repository;

import kr.hs.dgsw.de.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
