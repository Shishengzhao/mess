package com.ssz.mess.controller;

import com.ssz.mess.pojo.Comment;
import com.ssz.mess.pojo.Store;
import com.ssz.mess.pojo.vo.StoreVo;
import com.ssz.mess.service.ICommentService;
import com.ssz.mess.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IStoreService storeService;


    @GetMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable("id") Integer id,
                                ModelMap modelMap){
        Comment comment = commentService.getCommentById(id);
        //删评论
        commentService.deleteComment(id);

        Store s = storeService.getStoreByStoreId(comment.getStoreId());
        StoreVo store = storeService.getStoreWithOthers(s.getId());

        modelMap.addAttribute("store",store);
        return "store/details";
    }

}
