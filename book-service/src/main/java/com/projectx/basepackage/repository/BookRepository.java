package com.projectx.basepackage.repository;

import com.projectx.basepackage.models.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<BookDetails,Long> {
    @Query(value = "select * from book_details where id=:bookId",nativeQuery = true)
    BookDetails getById(@Param("bookId")Long bookId);

    @Transactional
    @Modifying
    @Query(value = "update book_details set book_quantity=:quantity where id=:bookId",nativeQuery = true)
    Integer updateQuantity(@Param("bookId")Long bookId,@Param("quantity")Integer quantity);
}
