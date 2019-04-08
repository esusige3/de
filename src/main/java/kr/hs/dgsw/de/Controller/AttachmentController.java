package kr.hs.dgsw.de.Controller;

import kr.hs.dgsw.de.Domain.User;
import kr.hs.dgsw.de.Protocol.AttachmentProtocol;
import kr.hs.dgsw.de.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;
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
}
