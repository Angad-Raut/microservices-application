package com.projectx.basepackage.repository;

import com.projectx.basepackage.models.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentDetails,Long> {
    @Query(value = "select * from student_data where id=:studentId",nativeQuery = true)
    StudentDetails getStudentById(@Param("studentId")Long studentId);

    @Query(value = "select roll_number from student_data order by id desc limit 1",nativeQuery = true)
    String getRollNumber();
}
