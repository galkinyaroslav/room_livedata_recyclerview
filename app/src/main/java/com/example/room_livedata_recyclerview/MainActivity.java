package com.example.room_livedata_recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_livedata_recyclerview.database.Barcode;
import com.example.room_livedata_recyclerview.database.BarcodeAdapter;
import com.example.room_livedata_recyclerview.database.BarcodeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;



public class MainActivity extends AppCompatActivity {
    public static final int ADD_BARCODE_REQUEST=1;

    private BarcodeViewModel barcodeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddBarcode=findViewById(R.id.button_add_barcode);

        buttonAddBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddBarcodesActivity.class);
                startActivityForResult(intent,ADD_BARCODE_REQUEST);

            }
        });

        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);

        final BarcodeAdapter adapter = new BarcodeAdapter();
        recyclerView.setAdapter(adapter);

        barcodeViewModel= new ViewModelProvider(this).get(BarcodeViewModel.class);
        barcodeViewModel.getAllBarcodes().observe(this, new Observer<List<Barcode>>() {
            @Override
            public void onChanged(List<Barcode> barcodes) {//Update RecyclerView Data
                //Toast.makeText(MainActivity.this,"onChanged",Toast.LENGTH_SHORT).show();
                adapter.setBarcodes(barcodes);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== ADD_BARCODE_REQUEST && resultCode==RESULT_OK){
            String barcodenum=data.getStringExtra(AddBarcodesActivity.EXTRA_BARCODENUM);
            String name=data.getStringExtra(AddBarcodesActivity.EXTRA_NAME);
            String docname=data.getStringExtra(AddBarcodesActivity.EXTRA_DOCNAME);
            String docdate=data.getStringExtra(AddBarcodesActivity.EXTRA_DOCDATE);
            String docnum=data.getStringExtra(AddBarcodesActivity.EXTRA_DOCNUM);
            String date=data.getStringExtra(AddBarcodesActivity.EXTRA_DATE);
            String invnum=data.getStringExtra(AddBarcodesActivity.EXTRA_INVNUM);
            String factnum=data.getStringExtra(AddBarcodesActivity.EXTRA_FACTNUM);
            String passportnum=data.getStringExtra(AddBarcodesActivity.EXTRA_PASSPORTNUM);
            String fnquantity=data.getStringExtra(AddBarcodesActivity.EXTRA_FNQUANTITY);
            String fncost=data.getStringExtra(AddBarcodesActivity.EXTRA_FNCOST);
            String buquantity=data.getStringExtra(AddBarcodesActivity.EXTRA_BUQUANTITY);
            String bucost=data.getStringExtra(AddBarcodesActivity.EXTRA_BUCOST);
            Barcode barcode=new Barcode(barcodenum,name,docname,docdate,docnum,date,invnum,factnum,passportnum,fnquantity,fncost,buquantity,bucost);
            barcodeViewModel.insert(barcode);
            Toast.makeText(this,"Штрихкод сохранен",Toast.LENGTH_SHORT);
        }else {
            Toast.makeText(this,"Штрихкод не сохранен",Toast.LENGTH_SHORT);
        }
    }
}