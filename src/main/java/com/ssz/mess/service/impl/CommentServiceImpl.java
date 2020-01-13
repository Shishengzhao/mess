package com.ssz.mess.service.impl;

import com.ssz.mess.mapper.CommentMapper;
import com.ssz.mess.pojo.Comment;
import com.ssz.mess.pojo.CommentExample;
import com.ssz.mess.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    @Transactional
    public boolean saveComment(Comment comment) {
        if(commentMapper.getCommentById(comment.getId())!=null){
            //已经存在
            return false;
        }
        return commentMapper.insert(comment) > 0;
    }

    @Override
    @Transactional
    public Comment getCommentById(Integer id) {
        return commentMapper.getCommentById(id);
    }

    @Override
    @Transactional
    public List<Comment> getComments() {
        return commentMapper.selectByExample(null);
    }

    @Override
    @Transactional
    public boolean updateComment(Integer id, Comment comment) {
        Comment c = commentMapper.getCommentById(id);
        if(c == null){
            //说明不存在
            throw new RuntimeException("id不存在");
        }
        BeanUtils.copyProperties(comment,c);
        return commentMapper.updateByPrimaryKey(c) > 0;
    }

    @Override
    @Transactional
    public void deleteComment(Integer id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public List<Comment> getCommentsByStoreId(Integer id) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andStoreIdEqualTo(id);
        return commentMapper.selectByExample(commentExample);
    }
}
