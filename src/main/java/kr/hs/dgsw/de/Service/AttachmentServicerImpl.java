package kr.hs.dgsw.de.Service;

import kr.hs.dgsw.de.Domain.User;
import kr.hs.dgsw.de.Protocol.AttachmentProtocol;
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

    @Override
    public boolean uploadProfile(Long userId, MultipartFile profile) {//for append
        Optional<User> find = this.userRepository.findById(userId);
        if(find.isPresent())
        {
            try {
                User target = find.get();
                String destFilename = "D://SpringDOC/uploaded"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/mm/dd/"))
                        + UUID.randomUUID().toString()
                        +"_" +profile.getOriginalFilename();

                    File destFile = new File(destFilename);
                    destFile.getParentFile().mkdirs();
                    profile.transferTo(destFile);

                    target.setProfilePathName(destFilename);
                    this.userRepository.save(target);
                return true;
            }
            catch (Exception ex){
            return false;
        }

        }
        return false;
    }
}
