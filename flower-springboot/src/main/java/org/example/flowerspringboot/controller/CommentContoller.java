package org.example.flowerspringboot.controller;

import jakarta.validation.constraints.NotNull;
import org.example.flowerspringboot.pojo.Carts;
import org.example.flowerspringboot.pojo.Commentstable;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.service.CommentstableService;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
@CrossOrigin
@Validated
public class CommentContoller {
    @Autowired
    private CommentstableService commentstableService;
    @PostMapping()
    public Result<String> addComment(@RequestBody @NotNull Commentstable commentstable){
        return commentstableService.addComment(commentstable);
    }

    @GetMapping()
    public Result<PageBean<Commentstable>> getComment(Integer pageNum,Integer bid,String toSearch){
        return commentstableService.getComment(pageNum,bid,toSearch);
    }
    @DeleteMapping()
    public Result<String> commentDelete(@NotNull Integer comid){
        return commentstableService.commentDelete(comid);
    }

}
