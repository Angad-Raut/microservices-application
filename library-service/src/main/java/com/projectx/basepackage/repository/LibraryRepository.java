package com.projectx.basepackage.repository;

import com.projectx.basepackage.models.LibraryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryDetails,Long> {

    @Query(value = "select * from library_details where id=:id",nativeQuery = true)
    LibraryDetails getById(@Param("id")Long id);
}
