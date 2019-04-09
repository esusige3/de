package kr.hs.dgsw.de.Service;

import kr.hs.dgsw.de.Domain.Comment;
import kr.hs.dgsw.de.Domain.User;
import kr.hs.dgsw.de.Domain.WriteForm;
import kr.hs.dgsw.de.Protocol.CommentUsernameProtocol;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommentService {
    List<CommentUsernameProtocol> listAllComments();


    boolean deleteComent(Long userId);
    CommentUsernameProtocol writeComment(WriteForm form);
    boolean modiComent(String comment, Long id);
}
