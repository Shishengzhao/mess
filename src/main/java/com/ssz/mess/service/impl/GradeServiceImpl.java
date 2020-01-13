package com.ssz.mess.service.impl;

import com.ssz.mess.mapper.GradeMapper;
import com.ssz.mess.pojo.Grade;
import com.ssz.mess.pojo.GradeExample;
import com.ssz.mess.service.IGradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GradeServiceImpl implements IGradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    @Transactional
    public boolean saveGrade(Grade grade) {
        if(gradeMapper.getGradeById(grade.getId())!=null){
            //已经存在
            return false;
        }else {
            return gradeMapper.insert(grade) > 0;
        }
    }

    @Override
    @Transactional
    public Grade getGradeById(Integer id) {
        return gradeMapper.getGradeById(id);
    }

    @Override
    @Transactional
    public List<Grade> getGrades() {
        return gradeMapper.selectByExample(null);
    }

    @Override
    @Transactional
    public boolean updateGrade(Integer id, Grade grade) {
        Grade g = gradeMapper.getGradeById(id);
        if(g == null){
            throw new RuntimeException("id已经存在");
        }
        BeanUtils.copyProperties(grade,g);

        return gradeMapper.updateByPrimaryKey(g) > 0;
    }

    @Override
    @Transactional
    public void deleteGrade(Integer id) {
        gradeMapper.deleteByPrimaryKey(id);
    }

}
