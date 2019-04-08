package kr.hs.dgsw.de.Controller;

import kr.hs.dgsw.de.Domain.User;
import kr.hs.dgsw.de.Protocol.AttachmentProtocol;
import kr.hs.dgsw.de.Repository.UserRepository;
import kr.hs.dgsw.de.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/attachment")
    public AttachmentProtocol upload(@RequestPart MultipartFile file){

        /*String destFilename = "D://SpringDOC/uploaded"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/mm/dd/"))
                + UUID.randomUUID().toString()
                +"_" +file.getOriginalFilename();
        try{
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            file.transferTo(destFile);
            return new AttachmentProtocol(destFilename,file.getOriginalFilename());
        }catch (Exception ex){
            return null;
        }*/
        return null;

    }

    @PostMapping("/attachment/appendProfile/{id}")
    public boolean append(@PathVariable Long id, @RequestPart MultipartFile file){
        return this.attachmentService.uploadProfile(id, file);
    }

    @GetMapping("/attachment/download/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response,@PathVariable Long id){
        try {

            Optional<User> user  = this.userRepository.findById(id);

            User target =  user.get();

            //String filepath = "D://SpringDOC/uploaded2019/56/04/49fad13b-951d-44b4-a329-53184e294434_P.png";
            String filepath = target.getProfilePathName();
            //String filename = "49fad13b-951d-44b4-a329-53184e294434_P.png";
            String filename = target.getProfileFileName();

            File file = new File(filepath);
            if (file.exists() == false) return;

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) mimeType = "application/octet-stream";
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
            response.setContentLength((int) file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is,response.getOutputStream());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
