package com.example.room_livedata_recyclerview.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BarcodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Barcode barcodes);
//    @Insert()
//    void insertList(List<Barcode> barcodes);
    @Update
    void uodate(Barcode barcode);
    @Delete
    void delete(Barcode barcode);
    @Query("DELETE FROM barcode_table")
    void deleteAllBarcodes();

    @Query("SELECT*FROM barcode_table ORDER BY name ASC")
    LiveData<List<Barcode>> getALLBarcodes();

    @Query("SELECT *  FROM barcode_table WHERE barcodenum LIKE :scannedBarcodeNumb")
    LiveData<Barcode> getTargetBarcode(String scannedBarcodeNumb);

}
