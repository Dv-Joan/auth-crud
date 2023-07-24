package com.paucar.santana.webapp.controller;

import com.paucar.santana.webapp.model.Teacher;
import com.paucar.santana.webapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// ! El ENDPOINT para utilizar la API
@RequestMapping("api/docentes")
public class TeacherController {

    @Autowired
    private TeacherService TeacherService;

    // ? ENDPOINT para agregar un Docente
    @PostMapping("/agregar")
    public Teacher addTeacher(@RequestBody Teacher Teacher) {
        return TeacherService.addTeacher(Teacher);
    }

    // ? ENDPOINT para buscar un Docente por ID
    @GetMapping("/{TeacherId}")
    public Teacher getTeacher(@PathVariable("TeacherId") Integer TeacherId) {
        return TeacherService.getTeacher(TeacherId);
    }

    // ? ENDPOINT para listar todos los Docentes
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return TeacherService.getAllTeachers();
    }

    // ? ENDPOINT para eliminar un Docente por su ID
    @DeleteMapping("{TeacherId}")
    public void deleteTeacher(@PathVariable("TeacherId") Integer TeacherId) {
        TeacherService.deleteTeacher(TeacherId);
    }

    // ? ENDPOINT para actualizar los Docentes por su ID
    @PutMapping(path = "/{TeacherId}")
    public Teacher updateTeacher(
            @PathVariable Integer TeacherId,
            @RequestParam(required = false) String TeacherName,
            @RequestParam(required = false) String TeacherApellido,
            @RequestParam(required = false) Double salario

    ) {
        return TeacherService.updateTeacher(TeacherId, TeacherName, TeacherApellido, salario);
    }

}
