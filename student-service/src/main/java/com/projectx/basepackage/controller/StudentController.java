package com.projectx.basepackage.controller;

import com.projectx.basepackage.exceptions.InvalidDataException;
import com.projectx.basepackage.exceptions.ResourceNotFoundException;
import com.projectx.basepackage.models.StudentDetails;
import com.projectx.basepackage.payloads.EntityIdDto;
import com.projectx.basepackage.payloads.ResponseDto;
import com.projectx.basepackage.payloads.StudentDto;
import com.projectx.basepackage.payloads.StudentListDto;
import com.projectx.basepackage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/insertOrUpdate")
    public ResponseEntity<ResponseDto<String>> addStudent(@RequestBody StudentDto dto) {
        try {
            String result = studentService.insertOrUpdate(dto);
            return new ResponseEntity<>(new ResponseDto<>(result,null),HttpStatus.CREATED);
        } catch (InvalidDataException e) {
            return new ResponseEntity<>(new ResponseDto<>(null,e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getById")
    public ResponseEntity<ResponseDto<StudentDetails>> getById(@RequestBody EntityIdDto dto) {
        try {
            StudentDetails result = studentService.getById(dto);
            return new ResponseEntity<>(new ResponseDto<>(result,null),HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ResponseDto<>(null,e.getMessage()), HttpStatus.OK);
        }
    }

    @PostMapping("/deleteById")
    public ResponseEntity<ResponseDto<String>> deleteById(@RequestBody EntityIdDto dto) {
        try {
            String result = studentService.deleteById(dto);
            return new ResponseEntity<>(new ResponseDto<>(result,null), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ResponseDto<>(null,e.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<ResponseDto<List<StudentListDto>>> getAllStudents() {
        try {
            List<StudentListDto> list = studentService.getAllStudents();
            return new ResponseEntity<>(new ResponseDto<>(list, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto<>(null, e.getMessage()), HttpStatus.OK);
        }
    }
}
