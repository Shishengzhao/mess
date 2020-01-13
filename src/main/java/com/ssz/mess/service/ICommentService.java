package com.ssz.mess.service;

import com.ssz.mess.pojo.Comment;

import java.util.List;

public interface ICommentService {

    boolean saveComment(Comment comment);

    Comment getCommentById(Integer id);

    List<Comment> getComments();

    boolean updateComment(Integer id, Comment comment);

    void deleteComment(Integer id);

    /**
     * 根据storeId查询评论
     * @param id
     * @return
     */
    List<Comment> getCommentsByStoreId(Integer id);
}
