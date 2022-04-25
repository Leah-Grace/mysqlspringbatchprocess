package com.LeahGrace.mysqlspringbatchprocess.models;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "milkRecord")
@Entity(name = "MilkRecord")
public class MilkRecord implements Serializable {


    private static final long serialVersionUID = 3446639139657165749L;

    public MilkRecord() {
    }


    @Id
    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "price", nullable = false)
    private double price;

    public MilkRecord(String date, double price) {
        this.date = date;
        this.price = price;
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
