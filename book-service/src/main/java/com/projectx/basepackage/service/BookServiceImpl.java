package com.projectx.basepackage.service;

import com.projectx.basepackage.exceptions.AlreadyExistException;
import com.projectx.basepackage.exceptions.InvalidDataException;
import com.projectx.basepackage.exceptions.ResourceNotFoundException;
import com.projectx.basepackage.models.BookDetails;
import com.projectx.basepackage.payloads.BookDto;
import com.projectx.basepackage.payloads.BookListDto;
import com.projectx.basepackage.payloads.EntityIdAndTypeDto;
import com.projectx.basepackage.payloads.EntityIdDto;
import com.projectx.basepackage.repository.BookRepository;
import com.projectx.basepackage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    @Override
    public String insertOrUpdate(BookDto bookDto) throws InvalidDataException, AlreadyExistException {
        BookDetails bookDetails = null;
        Boolean flag = false;
        if (bookDto.getId()==null) {
              bookDetails = setAddBookDetails(bookDto);
        } else {
              bookDetails = setUpdateBookDetails(bookDto);
              flag = true;
        }
        try {
            BookDetails details = bookRepository.save(bookDetails);
            if (details!=null && flag) {
                return Constants.UPDATED_MSG;
            } else if (details!=null){
                return Constants.INSERTED_MSG;
            } else {
                throw new InvalidDataException(Constants.ERROR_MSG);
            }
        } catch (Exception e) {
             throw new InvalidDataException(Constants.ERROR_MSG);
        }
    }

    @Override
    public BookDto getById(EntityIdDto dto) throws ResourceNotFoundException {
        try{
            BookDetails bookDetails = bookRepository.getById(dto.getEntityId());
            if (bookDetails!=null) {
                return BookDto.builder()
                        .id(bookDetails.getId())
                        .bookName(bookDetails.getBookName())
                        .bookAuthor(bookDetails.getBookAuthor())
                        .bookPublication(bookDetails.getBookPublication())
                        .bookPrice(bookDetails.getBookPrice())
                        .bookQuantity(bookDetails.getBookQuantity())
                        .build();
            } else {
                throw new ResourceNotFoundException(Constants.BOOK_NOT_FOUND_MSG);
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(Constants.BOOK_NOT_FOUND_MSG);
        }
    }

    @Transactional
    @Override
    public String updateQuantity(EntityIdAndTypeDto dto) throws ResourceNotFoundException {
        try {
            BookDetails details = bookRepository.getById(dto.getEntityId());
            if (details!=null) {
                Integer count = bookRepository.updateQuantity(dto.getEntityId(),dto.getEntityType());
                if (count!=null && count>0) {
                    return Constants.BOOK_QTY_UPDATE_MSG;
                } else {
                    return Constants.BOOK_QTY_NOT_UPDATE_MSG;
                }
            } else {
                throw new ResourceNotFoundException(Constants.BOOK_NOT_FOUND_MSG);
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(Constants.BOOK_NOT_FOUND_MSG);
        }
    }

    @Override
    public List<BookListDto> getAllBooks() {
        try {
            List<BookDetails> bookList = bookRepository.findAll();
            if (bookList!=null && !bookList.isEmpty()) {
                AtomicInteger index = new AtomicInteger(0);
                return bookList.stream()
                        .map(data -> BookListDto.builder()
                                .srNo(index.incrementAndGet())
                                .id(data.getId())
                                .bookName(data.getBookName())
                                .bookAuthor(data.getBookAuthor())
                                .bookPublication(data.getBookPublication())
                                .bookPrice(data.getBookPrice())
                                .bookQuantity(data.getBookQuantity())
                                .build())
                        .collect(Collectors.toList());
            } else {
                return new ArrayList<BookListDto>();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String deleteById(EntityIdDto dto) throws ResourceNotFoundException {
        try {
            BookDetails details = bookRepository.getById(dto.getEntityId());
            if (details!=null) {
                bookRepository.deleteById(details.getId());
                return Constants.DELETED_MSG;
            } else {
                throw new ResourceNotFoundException(Constants.BOOK_NOT_FOUND_MSG);
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(Constants.BOOK_NOT_FOUND_MSG);
        }
    }

    private BookDetails setAddBookDetails(BookDto dto) {
        return new BookDetails(dto.getId(),dto.getBookName(),dto.getBookAuthor(),
                dto.getBookPublication(),dto.getBookPrice(),dto.getBookQuantity(),
                new Date(),new Date(),Constants.INSERTED_BY_ID,Constants.INSERTED_BY_ID,
                Constants.INSERTED_BY,Constants.INSERTED_BY);
    }
    private BookDetails setUpdateBookDetails(BookDto dto) {
        BookDetails details = bookRepository.getById(dto.getId());
        if (!dto.getBookName().equals(details.getBookName())) {
            details.setBookName(dto.getBookName());
        }
        if (!dto.getBookAuthor().equals(details.getBookAuthor())) {
            details.setBookAuthor(dto.getBookAuthor());
        }
        if (!dto.getBookPublication().equals(details.getBookPublication())) {
            details.setBookPublication(dto.getBookPublication());
        }
        if (!dto.getBookPrice().equals(details.getBookPrice())) {
            details.setBookPrice(dto.getBookPrice());
        }
        if (!dto.getBookQuantity().equals(details.getBookQuantity())) {
            details.setBookQuantity(dto.getBookQuantity());
        }
        details.setUpdatedTime(new Date());
        return details;
    }
}
