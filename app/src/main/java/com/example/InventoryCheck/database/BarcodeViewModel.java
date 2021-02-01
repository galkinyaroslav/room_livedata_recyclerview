package com.example.InventoryCheck.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BarcodeViewModel extends AndroidViewModel {
    private final BarcodeRepository repository;
    private final LiveData<List<Barcode>> allBarcodes;
    public BarcodeViewModel(@NonNull Application application) {
        super(application);
        repository=new BarcodeRepository(application);
        allBarcodes=repository.getAllBarcodes();
    }
    public void insert(Barcode barcode){
        repository.insert(barcode);
    }
    public void update(Barcode barcode){
        repository.update(barcode);
    }
    public void delete(Barcode barcode){
        repository.delete(barcode);
    }
    public void deleteAllBarcodes(){
        repository.deleteAllBarcodes();
    }
    public LiveData<List<Barcode>> getAllBarcodes(){
        return allBarcodes;
    }
    public Barcode getTargetBarcode(String string) throws ExecutionException, InterruptedException {return repository.getTargetBarcode(string); }

}
