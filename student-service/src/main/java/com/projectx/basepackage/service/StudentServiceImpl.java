package com.projectx.basepackage.service;

import com.projectx.basepackage.Utils.Constants;
import com.projectx.basepackage.exceptions.InvalidDataException;
import com.projectx.basepackage.exceptions.ResourceNotFoundException;
import com.projectx.basepackage.models.StudentDetails;
import com.projectx.basepackage.payloads.EntityIdDto;
import com.projectx.basepackage.payloads.StudentDto;
import com.projectx.basepackage.payloads.StudentListDto;
import com.projectx.basepackage.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    @Override
    public String insertOrUpdate(StudentDto dto) throws InvalidDataException {
        StudentDetails studentDetails = null;
        Boolean flag = false;
        if (dto.getStudentId()==null) {
               studentDetails = setAddDetails(dto);
        } else {
               studentDetails = setUpdateDetails(dto);
               flag=true;
        }
        try {
             studentRepository.save(studentDetails);
             if (flag){
                 return Constants.UPDATED_STUDENT;
             } else {
                 return Constants.ADDED_STUDENT;
             }
        } catch (RuntimeException e) {
            throw new InvalidDataException(Constants.SAVE_ERROR_OCCURRED);
        }
    }

    @Override
    public StudentDetails getById(EntityIdDto dto) throws ResourceNotFoundException {
        try {
            return studentRepository.getStudentById(dto.getEntityId());
        } catch (ResourceNotFoundException e) {
             throw new ResourceNotFoundException(Constants.STUDENT_NOT_FOUND);
        }
    }

    @Override
    public List<StudentListDto> getAllStudents() {
        List<StudentDetails> list = studentRepository.findAll();
        AtomicInteger index = new AtomicInteger( 0 ) ;
        if (list!=null && !list.isEmpty()) {
            return studentRepository.findAll().stream()
                    .map(data -> StudentListDto.builder()
                            .srNo(index.incrementAndGet())
                            .studentId(data.getId())
                            .firstName(data.getFirstName())
                            .middleName(data.getMiddleName())
                            .lastName(data.getLastName())
                            .rollNumber(data.getRollNumber())
                            .gender(data.getGender())
                            .address(data.getAddress())
                            .standard(data.getStandard())
                            .build())
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<StudentListDto>();
        }
    }

    @Override
    public String deleteById(EntityIdDto dto) {
        try {
            studentRepository.deleteById(dto.getEntityId());
            return Constants.DELETED_STUDENT;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(Constants.STUDENT_NOT_FOUND);
        }
    }
    private StudentDetails setUpdateDetails(StudentDto dto) {
        StudentDetails details = studentRepository.getStudentById(dto.getStudentId());
        if (!dto.getFirstName().equals(details.getFirstName())) {
            details.setFirstName(dto.getFirstName());
        }
        if (!dto.getMiddleName().equals(details.getMiddleName())) {
            details.setMiddleName(dto.getMiddleName());
        }
        if (!dto.getLastName().equals(details.getLastName())) {
            details.setLastName(dto.getLastName());
        }
        if (!dto.getAddress().equals(details.getAddress())) {
            details.setAddress(dto.getAddress());
        }
        if (!dto.getGender().equals(details.getGender())) {
            details.setGender(dto.getGender());
        }
        if (!dto.getStandard().equals(details.getStandard())) {
            details.setStandard(dto.getStandard());
        }
        details.setUpdatedBy(Constants.INSERTED_BY);
        details.setUpdatedById(Constants.INSERTED_BY_ID);
        details.setUpdatedTime(new Date());
        return details;
    }

    private StudentDetails setAddDetails(StudentDto dto) {
        return new StudentDetails(dto.getStudentId(),setRollNo(), dto.getFirstName(),
                dto.getMiddleName(), dto.getLastName(),dto.getAddress(),dto.getGender(),
                dto.getStandard(),new Date(),new Date(),Constants.INSERTED_BY_ID,
                Constants.INSERTED_BY_ID,Constants.INSERTED_BY,Constants.INSERTED_BY);
    }
    private String setRollNo() {
        String result = null;
        String rollNo = studentRepository.getRollNumber();
        if (rollNo!=null) {
            String [] data = rollNo.split("-");
            Long converted = Long.parseLong(data[1])+1;
            result ="ST-"+converted;
            return result;
        } else {
            return "ST-10001";
        }
    }
}
