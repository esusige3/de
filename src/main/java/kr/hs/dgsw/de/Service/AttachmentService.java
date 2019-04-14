package kr.hs.dgsw.de.Service;

import kr.hs.dgsw.de.Domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    boolean uploadProfile(Long userId, MultipartFile profile);
    boolean uploadCommentImage(Long id, MultipartFile image);
}
