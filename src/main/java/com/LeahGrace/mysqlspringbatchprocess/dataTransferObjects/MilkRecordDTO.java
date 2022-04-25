package com.LeahGrace.mysqlspringbatchprocess.dataTransferObjects;

import com.LeahGrace.mysqlspringbatchprocess.models.MilkRecord;
import com.LeahGrace.mysqlspringbatchprocess.repositories.MilkRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class MilkRecordDTO implements Serializable {

    private static final long serialVersionUID = 870148664656984925L;

    @Autowired
    public MilkRepository milkRepository;

    private String date;
    private double price;

    public MilkRecordDTO() {
    }

    public MilkRecordDTO(String date, double price) {
        this.date = date;
        this.price = price;
    }

    public MilkRecord milkRecordDTOToMilkRecord() {
        MilkRecord milkRecord = new MilkRecord();
        milkRecord.setDate(this.date);
        milkRecord.setPrice(this.price);
        return milkRecord;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
