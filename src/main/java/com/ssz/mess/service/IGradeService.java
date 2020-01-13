package com.ssz.mess.service;

import com.ssz.mess.pojo.Grade;

import java.util.List;

public interface IGradeService {

    boolean saveGrade(Grade grade);

    Grade getGradeById(Integer id);

    List<Grade> getGrades();

    boolean updateGrade(Integer id, Grade grade);

    void deleteGrade(Integer id);

}
