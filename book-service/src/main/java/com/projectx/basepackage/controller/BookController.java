package com.projectx.basepackage.controller;

import com.projectx.basepackage.exceptions.AlreadyExistException;
import com.projectx.basepackage.exceptions.InvalidDataException;
import com.projectx.basepackage.exceptions.ResourceNotFoundException;
import com.projectx.basepackage.payloads.*;
import com.projectx.basepackage.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/insertOrUpdate")
    public ResponseEntity<ResponseDto<String>> insertOrUpdate(@RequestBody BookDto dto) {
        try {
            String data = bookService.insertOrUpdate(dto);
            return new ResponseEntity<>(new ResponseDto<String>(data,null),HttpStatus.CREATED);
        } catch (InvalidDataException | AlreadyExistException e) {
            return new ResponseEntity<>(new ResponseDto<String>(null,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getBookDetailsById")
    public ResponseEntity<ResponseDto<BookDto>> getBookDetailsById(@RequestBody EntityIdDto dto) {
        try {
            BookDto data = bookService.getById(dto);
            return new ResponseEntity<>(new ResponseDto<BookDto>(data,null),HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ResponseDto<BookDto>(null,e.getMessage()), HttpStatus.OK);
        }
    }

    @PostMapping("/updateBookQtyById")
    public ResponseEntity<ResponseDto<String>> updateBookQtyById(@RequestBody EntityIdAndTypeDto dto) {
        try {
            String data = bookService.updateQuantity(dto);
            return new ResponseEntity<>(new ResponseDto<String>(data,null),HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ResponseDto<String>(null,e.getMessage()), HttpStatus.OK);
        }
    }

    @PostMapping("/deleteBookById")
    public ResponseEntity<ResponseDto<String>> deleteBookById(@RequestBody EntityIdDto dto) {
        try {
            String data = bookService.deleteById(dto);
            return new ResponseEntity<>(new ResponseDto<String>(data,null),HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ResponseDto<String>(null,e.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllBookList")
    public ResponseEntity<ResponseDto<List<BookListDto>>> getAllBooks() {
        try {
            List<BookListDto> bookList = bookService.getAllBooks();
            return new ResponseEntity<>(new ResponseDto<List<BookListDto>>(bookList,null),HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ResponseDto<List<BookListDto>>(null,e.getMessage()),HttpStatus.OK);
        }
    }
}
