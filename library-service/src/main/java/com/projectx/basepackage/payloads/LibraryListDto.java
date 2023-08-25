package com.projectx.basepackage.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibraryListDto {
    private Integer srNo;
    private Long id;
    private String name;
    private String address;
    private String email;
    private Long phone;
}
