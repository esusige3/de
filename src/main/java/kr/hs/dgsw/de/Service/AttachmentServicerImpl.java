package kr.hs.dgsw.de.Service;

import kr.hs.dgsw.de.Domain.Comment;
import kr.hs.dgsw.de.Domain.User;
import kr.hs.dgsw.de.Protocol.AttachmentProtocol;
import kr.hs.dgsw.de.Repository.CommentRepository;
import kr.hs.dgsw.de.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachmentServicerImpl implements AttachmentService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;

    @Override
    public boolean uploadProfile(Long userId, MultipartFile profile) {//for append
        Optional<User> find = this.userRepository.findById(userId);
        if(find.isPresent())
        {
            try {
                User target = find.get();
                String fileName = UUID.randomUUID().toString()+"_"+profile.getOriginalFilename();
                String destFilename = "D://SpringDOC/uploaded"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/mm/dd/"))
                        + fileName;

                    File destFile = new File(destFilename);
                    destFile.getParentFile().mkdirs();
                    profile.transferTo(destFile);

                    target.setProfilePathName(destFilename);
                    target.setProfileFileName(fileName);
                    target.setOriginalFileName(profile.getOriginalFilename());
                    this.userRepository.save(target);
                return true;
            }
            catch (Exception ex){
            return false;
        }

        }
        return false;
    }

    @Override
    public boolean uploadCommentImage(Long id, MultipartFile image) {
        Optional<Comment> find = this.commentRepository.findById(id);
        if(find.isPresent())
        {
            try {
                Comment target = find.get();
                String fileName = UUID.randomUUID().toString()+"_"+image.getOriginalFilename();
                String destFilename = "D://SpringDOC/uploaded"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/mm/dd/"))
                        + fileName;

                File destFile = new File(destFilename);
                destFile.getParentFile().mkdirs();
                image.transferTo(destFile);

                target.setFilePath(destFilename);
                target.setFileName(fileName);

                this.commentRepository.save(target);
                return true;
            }
            catch (Exception ex){
                return false;
            }

        }
        return false;
    }
}
