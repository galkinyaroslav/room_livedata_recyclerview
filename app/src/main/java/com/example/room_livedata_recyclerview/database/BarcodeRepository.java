package com.example.room_livedata_recyclerview.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BarcodeRepository {
    private final BarcodeDao barcodeDao;
    private final LiveData<List<Barcode>> allBarcodes;
    public BarcodeRepository(Application application) {
        BarcodeDatabase database = BarcodeDatabase.getInstance(application);
        barcodeDao = database.barcodeDao();
        allBarcodes = barcodeDao.getALLBarcodes();
    }
    public void insert(Barcode barcode){
        new InsertBarcodeAsyncTask(barcodeDao).execute(barcode);
    }
    public void update(Barcode barcode){
        new UpdateBarcodeAsyncTask(barcodeDao).execute(barcode);
    }
    public void delete(Barcode barcode){
        new DeleteBarcodeAsyncTask(barcodeDao).execute(barcode);
    }
    public void deleteAllBarcodes(){
        new DeleteAllBarcodeAsyncTask(barcodeDao).execute();
    }
    public LiveData<List<Barcode>> getAllBarcodes(){
        return allBarcodes;
    }
    public Barcode getTargetBarcode(String string) throws ExecutionException, InterruptedException {
        GetTargetBarcodeAsyncTask AsyncBarcode =new GetTargetBarcodeAsyncTask(barcodeDao);
        AsyncBarcode.execute(string);
        return AsyncBarcode.get();
    }

    private static class InsertBarcodeAsyncTask extends AsyncTask<Barcode,Void,Void>{
        private final BarcodeDao barcodeDao;
        private InsertBarcodeAsyncTask(BarcodeDao barcodeDao){
            this.barcodeDao=barcodeDao;
        }
        @Override
        protected Void doInBackground(Barcode... barcodes){
            barcodeDao.insert(barcodes[0]);
            return null;
        }
    }

    private static class UpdateBarcodeAsyncTask extends AsyncTask<Barcode,Void,Void>{
        private final BarcodeDao barcodeDao;
        private UpdateBarcodeAsyncTask(BarcodeDao barcodeDao){
            this.barcodeDao=barcodeDao;
        }
        @Override
        protected Void doInBackground(Barcode... barcodes){
            barcodeDao.uodate(barcodes[0]);
            return null;
        }
    }

    private static class DeleteBarcodeAsyncTask extends AsyncTask<Barcode,Void,Void>{
        private final BarcodeDao barcodeDao;
        private DeleteBarcodeAsyncTask(BarcodeDao barcodeDao){
            this.barcodeDao=barcodeDao;
        }
        @Override
        protected Void doInBackground(Barcode... barcodes){
            barcodeDao.delete(barcodes[0]);
            return null;
        }
    }

    private static class DeleteAllBarcodeAsyncTask extends AsyncTask<Void,Void,Void>{
        private final BarcodeDao barcodeDao;
        private DeleteAllBarcodeAsyncTask(BarcodeDao barcodeDao){
            this.barcodeDao=barcodeDao;
        }
        @Override
        protected Void doInBackground(Void... voids){
            barcodeDao.deleteAllBarcodes();
            return null;
        }
    }

    private static class GetTargetBarcodeAsyncTask extends AsyncTask<String,Void,Barcode> {
        private final BarcodeDao barcodeDao;
        private GetTargetBarcodeAsyncTask(BarcodeDao barcodeDao) {this.barcodeDao=barcodeDao;
        }

        @Override
        protected Barcode doInBackground(String... strings) {
            return barcodeDao.getTargetBarcode(strings[0]);
        }
    }
}
