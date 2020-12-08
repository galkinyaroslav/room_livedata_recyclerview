package com.example.room_livedata_recyclerview.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "barcode_table")
public class Barcode {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String barcodenum;
    private String name;
    private String docname;
    private String docdate;
    private String docnum;
    private String date;
    private String invnum;
    private String factnum;
    private String passportnum;
    private String fnquantity;
    private String fncost;
    private String buquantity;
    private String bucost;

    public Barcode(String barcodenum, String name, String docname, String docdate, String docnum, String date, String invnum, String factnum, String passportnum, String fnquantity, String fncost, String buquantity, String bucost) {
        this.barcodenum = barcodenum;
        this.name = name;
        this.docname = docname;
        this.docdate = docdate;
        this.docnum = docnum;
        this.date = date;
        this.invnum = invnum;
        this.factnum = factnum;
        this.passportnum = passportnum;
        this.fnquantity = fnquantity;
        this.fncost = fncost;
        this.buquantity = buquantity;
        this.bucost = bucost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getBarcodenum() {
        return barcodenum;
    }

    public String getName() {
        return name;
    }

    public String getDocname() {
        return docname;
    }

    public String getDocdate() {
        return docdate;
    }

    public String getDocnum() {
        return docnum;
    }

    public String getDate() {
        return date;
    }

    public String getInvnum() {
        return invnum;
    }

    public String getFactnum() {
        return factnum;
    }

    public String getPassportnum() {
        return passportnum;
    }

    public String getFnquantity() {
        return fnquantity;
    }

    public String getFncost() {
        return fncost;
    }

    public String getBuquantity() {
        return buquantity;
    }

    public String getBucost() {
        return bucost;
    }
    }
