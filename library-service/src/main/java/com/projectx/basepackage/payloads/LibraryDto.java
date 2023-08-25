package com.projectx.basepackage.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LibraryDto {
    private Long id;
    private String name;
    private String address;
    private String email;
    private Long phone;
}
