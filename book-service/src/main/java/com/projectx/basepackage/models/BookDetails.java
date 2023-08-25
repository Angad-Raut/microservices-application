package com.projectx.basepackage.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "book_details")
public class BookDetails extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookName;
    private String bookAuthor;
    private String bookPublication;
    private Double bookPrice;
    private Integer bookQuantity;

    public BookDetails(){

    }

    public BookDetails(Long id, String bookName, String bookAuthor, String bookPublication, Double bookPrice, Integer bookQuantity,
                       Date insertedTime, Date updatedTime, Long insertedById, Long updatedById, Integer insertedBy, Integer updatedBy) {
        super(insertedTime, updatedTime, insertedById, updatedById, insertedBy, updatedBy);
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPublication = bookPublication;
        this.bookPrice = bookPrice;
        this.bookQuantity = bookQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublication() {
        return bookPublication;
    }

    public void setBookPublication(String bookPublication) {
        this.bookPublication = bookPublication;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Integer getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(Integer bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDetails that = (BookDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(bookName, that.bookName) && Objects.equals(bookAuthor, that.bookAuthor) && Objects.equals(bookPublication, that.bookPublication) && Objects.equals(bookPrice, that.bookPrice) && Objects.equals(bookQuantity, that.bookQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookName, bookAuthor, bookPublication, bookPrice, bookQuantity);
    }

    @Override
    public String toString() {
        return "BookDetails{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPublication='" + bookPublication + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookQuantity=" + bookQuantity +
                '}';
    }
}
