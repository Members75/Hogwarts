package ru.hogwarts.school.controller;

import org.apache.catalina.util.ParameterMap;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private ParameterMap<Object, Object> students;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Map<Long, Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return studentService.get(id);
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
    @GetMapping("/age/{age}")
    public Map<Long, Student> filterByAge(@PathVariable int age) {
        return students.entrySet()
                .stream()
                .filter(e -> e.getValue().getAge() == age)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}