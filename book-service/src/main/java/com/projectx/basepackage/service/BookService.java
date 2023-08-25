package com.projectx.basepackage.service;

import com.projectx.basepackage.exceptions.AlreadyExistException;
import com.projectx.basepackage.exceptions.InvalidDataException;
import com.projectx.basepackage.exceptions.ResourceNotFoundException;
import com.projectx.basepackage.payloads.BookDto;
import com.projectx.basepackage.payloads.BookListDto;
import com.projectx.basepackage.payloads.EntityIdAndTypeDto;
import com.projectx.basepackage.payloads.EntityIdDto;

import java.util.List;

public interface BookService {
    String insertOrUpdate(BookDto bookDto)throws InvalidDataException, AlreadyExistException;
    BookDto getById(EntityIdDto dto)throws ResourceNotFoundException;
    String updateQuantity(EntityIdAndTypeDto dto)throws ResourceNotFoundException;
    List<BookListDto> getAllBooks();

    String deleteById(EntityIdDto dto)throws ResourceNotFoundException;
}
