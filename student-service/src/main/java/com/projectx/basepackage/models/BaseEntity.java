package com.projectx.basepackage.models;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    private Date insertedTime;
    private Date updatedTime;
    private Long insertedById;
    private Long updatedById;
    private Integer insertedBy;
    private Integer updatedBy;

    public BaseEntity(){

    }

    public BaseEntity(Date insertedTime, Date updatedTime, Long insertedById, Long updatedById, Integer insertedBy, Integer updatedBy) {
         this.insertedTime=insertedTime;
         this.updatedTime=updatedTime;
         this.insertedById=insertedById;
         this.updatedById=updatedById;
         this.insertedBy=insertedBy;
         this.updatedBy=updatedBy;
    }

    public Date getInsertedTime() {
        return insertedTime;
    }

    public void setInsertedTime(Date insertedTime) {
        this.insertedTime = insertedTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getInsertedById() {
        return insertedById;
    }

    public void setInsertedById(Long insertedById) {
        this.insertedById = insertedById;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public Integer getInsertedBy() {
        return insertedBy;
    }

    public void setInsertedBy(Integer insertedBy) {
        this.insertedBy = insertedBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
}
