package com.example.room_livedata_recyclerview;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_livedata_recyclerview.database.Barcode;
import com.example.room_livedata_recyclerview.database.BarcodeAdapter;
import com.example.room_livedata_recyclerview.database.BarcodeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
private BarcodeViewModel barcodeViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}