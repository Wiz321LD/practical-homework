package org.example.hw6.mapper;

import org.example.hw6.dto.StudentDTO;
import org.example.hw6.model.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class StudentMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public StudentMapper(ModelMapper modelMapper) {
        StudentMapper.modelMapper = modelMapper;
    }


    public static Student mapToStudent(StudentDTO studentDTO){
        return modelMapper.map(studentDTO, Student.class);
    }

    public static StudentDTO mapToStudentDTO(Student student){
        return modelMapper.map(student, StudentDTO.class);
    }

}
