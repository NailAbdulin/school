package service;

import exception.StudentNotFoundException;
import model.Faculty;
import model.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> studentMap = new HashMap<>();
    private long idGenerator = 1;

    public Student create(Student student) {
        student.setId(idGenerator++);
        studentMap.put(student.getId(), student);
        return student;
    }

    public Student read(long id) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        return studentMap.get(id);
    }

    public Student update(long id, Student newStudent) {
        Student oldStudent = read(id);
        oldStudent.setAge(newStudent.getAge());
        oldStudent.setName(newStudent.getName());
        studentMap.put(id, oldStudent);
        return newStudent;
    }

    public Student delete(long id) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        return studentMap.remove(id);
    }

    public Collection<Student> findByAge(int age) {
        return studentMap.values().stream().filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}
