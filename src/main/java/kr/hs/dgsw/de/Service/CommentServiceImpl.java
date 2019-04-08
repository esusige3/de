package kr.hs.dgsw.de.Service;

import kr.hs.dgsw.de.Domain.Comment;
import kr.hs.dgsw.de.Domain.User;
import kr.hs.dgsw.de.Domain.WriteForm;
import kr.hs.dgsw.de.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.de.Repository.CommentRepository;
import kr.hs.dgsw.de.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init(){
        User u = new User("abc","Dgsw@dgsw");
        this.userRepository.save(u);
        this.commentRepository.save(new Comment(u.getId(),"hi there111"));
        this.commentRepository.save(new Comment(u.getId(),"hi there222"));
        this.commentRepository.save(new Comment(u.getId(),"hi there333"));
    }



    @Override
    public List<CommentUsernameProtocol> listAllComments() {
        List<Comment> commentList = this.commentRepository.findAll();
        List<CommentUsernameProtocol> cupList = new ArrayList<>();
        commentList.forEach(comment -> {
            Optional<User> found =this.userRepository.findById(comment.getUserId());
            String username = (found.isPresent())?found.get().getUsername():null;
            cupList.add(new CommentUsernameProtocol(comment,username));//forEach는 return 이 void다
        });
        return cupList;
    }

    @Override
    public boolean deleteComent(Long Id) {
        Optional<Comment> commentList = this.commentRepository.findById(Id);
        if(commentList.isPresent()){
            this.commentRepository.deleteById(Id);
            return true;

        }
        return false;
    }

    @Override
    public CommentUsernameProtocol writeComment(WriteForm form) {
       /* Optional<User> found = this.userRepository.findByUsername(comment.getName());
        if(found.isPresent()){
            Comment send =  this.commentRepository.save(new Comment(found.get().getId(),comment.getComment()));
            return send.getUsernam;
        }
        return null;*/
       //Comment comment = new Comment();
       //comment.setUserId(this.userRepository.get);
       //comment.setContent(form.getComment());

        Optional<User> found = this.userRepository.findByUsername(form.getName());
        if(found.isPresent()){
            return new CommentUsernameProtocol(this.commentRepository.save(new Comment(found.get().getId(),form.getComment())),
            form.getName());
        }
        return null;
        /*return new CommentUsernameProtocol(
                this.commentRepository.save(new Comment(this.userRepository.findByUsername(form.getName()).get().getId(),form.getComment()),
                this.userRepository
                        .findByUsername(form.getName())
                        .map(u->u.getUsername())
                        .orElse(null)
        );*/
    }

    @Override
    public boolean modiComent(String comment, Long id) {
       Optional<Comment> found = this.commentRepository.findById(id);
       if(found.isPresent()){
           Comment find = found.get();
           find.setContent(comment);
           this.commentRepository.save(find);
           return true;
       }
       return false;

    }

}
