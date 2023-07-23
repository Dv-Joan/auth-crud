package com.paucar.santana.webapp.service;

import com.paucar.santana.webapp.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    Teacher addTeacher(Teacher Teacher);

    List<Teacher> getAllTeachers();

    Teacher getTeacher(Integer TeacherId);

    void deleteTeacher(Integer TeacherId);

    Teacher updateTeacher(Integer TeacherId, String TeacherName, String TeacherApellido, Double salario);
}
