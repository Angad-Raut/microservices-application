package com.projectx.basepackage.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
@Entity
@Table(name = "student_data")
public class StudentDetails extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rollNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String gender;
    private String standard;

    public StudentDetails(){
        super();
    }

    public StudentDetails(Long id, String rollNumber, String firstName, String middleName,
                          String lastName, String address, String gender, String standard,
                          Date insertedTime,Date updatedTime,Long insertedById,Long updatedById,
                          Integer insertedBy,Integer updatedBy) {
        super(insertedTime,updatedTime,insertedById,updatedById,insertedBy,updatedBy);
        this.id = id;
        this.rollNumber = rollNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.standard = standard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDetails that = (StudentDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(rollNumber, that.rollNumber) && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(gender, that.gender) && Objects.equals(standard, that.standard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rollNumber, firstName, middleName, lastName, address, gender, standard);
    }

    @Override
    public String toString() {
        return "StudentDetails{" +
                "id=" + id +
                ", rollNumber='" + rollNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", standard='" + standard + '\'' +
                '}';
    }
}
