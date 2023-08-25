package com.projectx.basepackage.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Long studentId;
    private String rollNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String gender;
    private String standard;
}
