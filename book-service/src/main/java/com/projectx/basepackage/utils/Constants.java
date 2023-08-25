package com.projectx.basepackage.utils;

import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static final Integer INSERTED_BY=21;
    public static final Long INSERTED_BY_ID=1L;
    public static final String INSERTED_MSG="Book inserted successfully!!";
    public static final String UPDATED_MSG="Book details updated successfully!!";
    public static final String DELETED_MSG="Book deleted successfully!!";
    public static final String ERROR_MSG="Error occurred while adding / updating the book details!!";
    public static final String BOOK_NOT_FOUND_MSG="Book not present in the system!!";
    public static final String BOOK_QTY_NOT_FOUND_MSG="This book quantity not present in the system!!";
    public static final String BOOK_QTY_UPDATE_MSG="Book quantity updated in the system!!";
    public static final String BOOK_QTY_NOT_UPDATE_MSG="Book quantity not updated in the system!!";
}
