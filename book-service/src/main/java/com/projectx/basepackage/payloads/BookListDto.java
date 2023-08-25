package com.projectx.basepackage.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookListDto {
    private Integer srNo;
    private Long id;
    private String bookName;
    private String bookAuthor;
    private String bookPublication;
    private Double bookPrice;
    private Integer bookQuantity;
}
