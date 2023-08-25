package com.projectx.basepackage.controller;

import com.projectx.basepackage.exceptions.InvalidDataException;
import com.projectx.basepackage.exceptions.ResourceNotFoundException;
import com.projectx.basepackage.payloads.EntityIdDto;
import com.projectx.basepackage.payloads.LibraryDto;
import com.projectx.basepackage.payloads.LibraryListDto;
import com.projectx.basepackage.payloads.ResponseDto;
import com.projectx.basepackage.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/insertOrUpdate")
    public ResponseEntity<ResponseDto<String>> insertOrUpdate(@RequestBody LibraryDto dto) {
        try {
            String data = libraryService.insertOrUpdate(dto);
            return new ResponseEntity<>(new ResponseDto<String>(data,null),HttpStatus.CREATED);
        } catch (InvalidDataException e) {
            return new ResponseEntity<>(new ResponseDto<String>(null,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getLibraryById")
    public ResponseEntity<ResponseDto<LibraryDto>> getLibraryById(@RequestBody EntityIdDto dto) {
        try {
            LibraryDto data = libraryService.getById(dto);
            return new ResponseEntity<>(new ResponseDto<LibraryDto>(data,null),HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ResponseDto<LibraryDto>(null,e.getMessage()), HttpStatus.OK);
        }
    }

    @PostMapping("/deleteById")
    public ResponseEntity<ResponseDto<String>> deleteById(@RequestBody EntityIdDto dto) {
        try {
            String data = libraryService.deleteById(dto);
            return new ResponseEntity<>(new ResponseDto<String>(data,null),HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ResponseDto<String>(null,e.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllLibraries")
    public ResponseEntity<ResponseDto<List<LibraryListDto>>> getAllLibraries() {
         try {
              List<LibraryListDto> list = libraryService.getAllLibraryList();
              return new ResponseEntity<>(new ResponseDto<List<LibraryListDto>>(list,null),HttpStatus.OK);
         } catch (Exception e) {
              return new ResponseEntity<>(new ResponseDto<List<LibraryListDto>>(null,e.getMessage()),HttpStatus.OK);
         }
    }
}
