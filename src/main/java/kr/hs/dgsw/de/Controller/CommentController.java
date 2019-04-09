package kr.hs.dgsw.de.Controller;

import kr.hs.dgsw.de.Domain.Comment;
import kr.hs.dgsw.de.Domain.WriteForm;
import kr.hs.dgsw.de.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.de.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comment/list")
    public List<CommentUsernameProtocol> list(){
        return this.commentService.listAllComments();
    }

    @DeleteMapping("/comment/delete/{id}")
    public boolean delete(@PathVariable Long id){
        return this.commentService.deleteComent(id);
    }

    @PostMapping("/comment/write")
    public CommentUsernameProtocol wirte(@RequestBody WriteForm comment){
        return this.commentService.writeComment(comment);
    }

    @PutMapping("/comment/modi/{id}")
    public boolean write(@RequestBody String comment,@PathVariable Long id){return this.commentService.modiComent(comment, id); }
}
