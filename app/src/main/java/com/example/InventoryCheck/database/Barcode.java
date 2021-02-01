package com.example.InventoryCheck.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "barcode_table")
public class Barcode {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private  String barcodenum=null;
    private  String name=null;
    private  String docname=null;
    private  String docdate=null;
    private  String docnum=null;
    private  String date=null;
    private  String invnum=null;
    private  String factnum=null;
    private  String passportnum=null;
    private  String fnquantity=null;
    private  String fncost=null;
    private  String buquantity=null;
    private  String bucost=null;

    public Barcode() {
    }

    public void setBarcodenum(String barcodenum) {
        this.barcodenum = barcodenum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public void setDocdate(String docdate) {
        this.docdate = docdate;
    }

    public void setDocnum(String docnum) {
        this.docnum = docnum;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setInvnum(String invnum) {
        this.invnum = invnum;
    }

    public void setFactnum(String factnum) {
        this.factnum = factnum;
    }

    public void setPassportnum(String passportnum) {
        this.passportnum = passportnum;
    }

    public void setFnquantity(String fnquantity) {
        this.fnquantity = fnquantity;
    }

    public void setFncost(String fncost) {
        this.fncost = fncost;
    }

    public void setBuquantity(String buquantity) {
        this.buquantity = buquantity;
    }

    public void setBucost(String bucost) {
        this.bucost = bucost;
    }

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
