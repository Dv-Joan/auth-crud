package com.paucar.santana.webapp.service;

import com.paucar.santana.webapp.model.Teacher;
import com.paucar.santana.webapp.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository TeacherRepository;

    @Override
    public Teacher addTeacher(Teacher Teacher) {
        return TeacherRepository.save(Teacher);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return TeacherRepository.findAll();
    }

    @Override
    public Teacher getTeacher(Integer TeacherId) {
        return TeacherRepository.findById(TeacherId)
                .orElseThrow(() -> new IllegalArgumentException("El id del docente es invalido"));
    }

    @Override
    public void deleteTeacher(Integer TeacherId) {
        Teacher Teacher = getTeacher(TeacherId);
        TeacherRepository.delete(Teacher);
    }

    @Override
    @Transactional
    public Teacher updateTeacher(Integer TeacherId, String TeacherNombre, String TeacherApellido, Double salario) {

        Teacher Teacher = getTeacher(TeacherId);
        boolean emptyNombre = TeacherNombre == null || TeacherNombre.length() < 1;
        boolean emptyTeacherApellido = TeacherApellido == null || TeacherApellido.length() < 1;
        boolean validSalario = salario != null && (salario.compareTo((double) 0) > 0);

        if (!emptyNombre && !Teacher.getNombre().equals(TeacherNombre)) {
            Teacher.setNombre(TeacherNombre);
        }
        if (!emptyTeacherApellido && !Teacher.getApellido().equals(TeacherApellido)) {
            Teacher.setApellido(TeacherApellido);
        }
        if (validSalario) {
            Teacher.setSalario(salario);
        }

        TeacherRepository.save(Teacher);

        return Teacher;
    }
}
