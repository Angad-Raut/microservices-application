package com.projectx.basepackage.service;

import com.projectx.basepackage.exceptions.InvalidDataException;
import com.projectx.basepackage.exceptions.ResourceNotFoundException;
import com.projectx.basepackage.models.StudentDetails;
import com.projectx.basepackage.payloads.EntityIdDto;
import com.projectx.basepackage.payloads.StudentDto;
import com.projectx.basepackage.payloads.StudentListDto;

import java.util.List;

public interface StudentService {
    String insertOrUpdate(StudentDto dto)throws InvalidDataException;
    StudentDetails getById(EntityIdDto dto)throws ResourceNotFoundException;
    List<StudentListDto> getAllStudents();
    String deleteById(EntityIdDto dto);
}
