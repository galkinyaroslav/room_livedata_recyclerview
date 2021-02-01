package com.example.InventoryCheck.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Barcode.class}, version = 1)
public abstract class BarcodeDatabase extends RoomDatabase {
    private static BarcodeDatabase instance;
    public abstract BarcodeDao barcodeDao();

    public static synchronized BarcodeDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),BarcodeDatabase.class,"barcode_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static final RoomDatabase.Callback roomCallback= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private final BarcodeDao barcodeDao;

        private PopulateDbAsyncTask(BarcodeDatabase db) {
            barcodeDao = db.barcodeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            barcodeDao.insert(new Barcode("barcodenum1","name1","docname1","docdate1","docnum1","date1","invnum1","factnum1","passportnum1","fnquantity1","fncost1","buquantity1","bucost1"));
            barcodeDao.insert(new Barcode("barcodenum2","name2","docname2","docdate2","docnum2","date2","invnum2","factnum2","passportnum2","fnquantity2","fncost2","buquantity2","bucost2"));
            barcodeDao.insert(new Barcode("barcodenum3","name3","docname3","docdate3","docnum3","date3","invnum3","factnum3","passportnum3","fnquantity3","fncost3","buquantity3","bucost3"));
            return null;
        }
    }
}
