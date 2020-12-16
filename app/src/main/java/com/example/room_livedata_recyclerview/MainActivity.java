package com.example.room_livedata_recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_livedata_recyclerview.database.Barcode;
import com.example.room_livedata_recyclerview.database.BarcodeAdapter;
import com.example.room_livedata_recyclerview.database.BarcodeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final int ADD_BARCODE_REQUEST = 1;
    public static final int EDIT_BARCODE_REQUEST = 2;
    public static final int SCAN_BARCODE_REQUEST = 3;

    private BarcodeViewModel barcodeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddBarcode = findViewById(R.id.button_add_barcode);

        buttonAddBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditBarcodesActivity.class);
                startActivityForResult(intent, ADD_BARCODE_REQUEST);

            }
        });

        FloatingActionButton buttonScanBarcode = findViewById(R.id.button_scan_barcode);
        buttonScanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BarcodeCaptureActivity.class);
                startActivityForResult(intent, SCAN_BARCODE_REQUEST);

            }
        });


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);

        final BarcodeAdapter adapter = new BarcodeAdapter();
        recyclerView.setAdapter(adapter);

        barcodeViewModel = new ViewModelProvider(this).get(BarcodeViewModel.class);
        barcodeViewModel.getAllBarcodes().observe(this, new Observer<List<Barcode>>() {
            @Override
            public void onChanged(List<Barcode> barcodes) {//Update RecyclerView Data
                //Toast.makeText(MainActivity.this,"onChanged",Toast.LENGTH_SHORT).show();
                adapter.submitList(barcodes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                barcodeViewModel.delete(adapter.getBarcodeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Штрихкод удален", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        adapter.SetOnItemClickListener(new BarcodeAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Barcode barcode) {
                Intent intent = new Intent(MainActivity.this, AddEditBarcodesActivity.class);
                intent.putExtra(AddEditBarcodesActivity.EXTRA_ID, barcode.getId());
                intent.putExtra(AddEditBarcodesActivity.EXTRA_BARCODENUM, barcode.getBarcodenum());
                intent.putExtra(AddEditBarcodesActivity.EXTRA_NAME, barcode.getName());
                intent.putExtra(AddEditBarcodesActivity.EXTRA_DOCNAME, barcode.getDocname());
                intent.putExtra(AddEditBarcodesActivity.EXTRA_DOCDATE, barcode.getDocdate());
                intent.putExtra(AddEditBarcodesActivity.EXTRA_DOCNUM, barcode.getDocnum());
                intent.putExtra(AddEditBarcodesActivity.EXTRA_DATE, barcode.getDocdate());
                intent.putExtra(AddEditBarcodesActivity.EXTRA_INVNUM, barcode.getInvnum());
                intent.putExtra(AddEditBarcodesActivity.EXTRA_FACTNUM, barcode.getFactnum());
                intent.putExtra(AddEditBarcodesActivity.EXTRA_PASSPORTNUM, barcode.getPassportnum());
                intent.putExtra(AddEditBarcodesActivity.EXTRA_FNQUANTITY, barcode.getFnquantity());
                intent.putExtra(AddEditBarcodesActivity.EXTRA_FNCOST, barcode.getFncost());
                intent.putExtra(AddEditBarcodesActivity.EXTRA_BUQUANTITY, barcode.getBuquantity());
                intent.putExtra(AddEditBarcodesActivity.EXTRA_BUCOST, barcode.getBucost());

                startActivityForResult(intent, EDIT_BARCODE_REQUEST);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_BARCODE_REQUEST && resultCode == RESULT_OK) {
            String barcodenum = data.getStringExtra(AddEditBarcodesActivity.EXTRA_BARCODENUM);
            String name = data.getStringExtra(AddEditBarcodesActivity.EXTRA_NAME);
            String docname = data.getStringExtra(AddEditBarcodesActivity.EXTRA_DOCNAME);
            String docdate = data.getStringExtra(AddEditBarcodesActivity.EXTRA_DOCDATE);
            String docnum = data.getStringExtra(AddEditBarcodesActivity.EXTRA_DOCNUM);
            String date = data.getStringExtra(AddEditBarcodesActivity.EXTRA_DATE);
            String invnum = data.getStringExtra(AddEditBarcodesActivity.EXTRA_INVNUM);
            String factnum = data.getStringExtra(AddEditBarcodesActivity.EXTRA_FACTNUM);
            String passportnum = data.getStringExtra(AddEditBarcodesActivity.EXTRA_PASSPORTNUM);
            String fnquantity = data.getStringExtra(AddEditBarcodesActivity.EXTRA_FNQUANTITY);
            String fncost = data.getStringExtra(AddEditBarcodesActivity.EXTRA_FNCOST);
            String buquantity = data.getStringExtra(AddEditBarcodesActivity.EXTRA_BUQUANTITY);
            String bucost = data.getStringExtra(AddEditBarcodesActivity.EXTRA_BUCOST);
            Barcode barcode = new Barcode(barcodenum, name, docname, docdate, docnum, date, invnum, factnum, passportnum, fnquantity, fncost, buquantity, bucost);
            barcodeViewModel.insert(barcode);
            Toast.makeText(this, "Штрихкод сохранен", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_BARCODE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditBarcodesActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Штрихкод не может быть обновлен", Toast.LENGTH_SHORT).show();
                return;
            }
            String barcodenum = data.getStringExtra(AddEditBarcodesActivity.EXTRA_BARCODENUM);
            String name = data.getStringExtra(AddEditBarcodesActivity.EXTRA_NAME);
            String docname = data.getStringExtra(AddEditBarcodesActivity.EXTRA_DOCNAME);
            String docdate = data.getStringExtra(AddEditBarcodesActivity.EXTRA_DOCDATE);
            String docnum = data.getStringExtra(AddEditBarcodesActivity.EXTRA_DOCNUM);
            String date = data.getStringExtra(AddEditBarcodesActivity.EXTRA_DATE);
            String invnum = data.getStringExtra(AddEditBarcodesActivity.EXTRA_INVNUM);
            String factnum = data.getStringExtra(AddEditBarcodesActivity.EXTRA_FACTNUM);
            String passportnum = data.getStringExtra(AddEditBarcodesActivity.EXTRA_PASSPORTNUM);
            String fnquantity = data.getStringExtra(AddEditBarcodesActivity.EXTRA_FNQUANTITY);
            String fncost = data.getStringExtra(AddEditBarcodesActivity.EXTRA_FNCOST);
            String buquantity = data.getStringExtra(AddEditBarcodesActivity.EXTRA_BUQUANTITY);
            String bucost = data.getStringExtra(AddEditBarcodesActivity.EXTRA_BUCOST);
            Barcode barcode = new Barcode(barcodenum, name, docname, docdate, docnum, date, invnum, factnum, passportnum, fnquantity, fncost, buquantity, bucost);
            barcode.setId(id);
            barcodeViewModel.update(barcode);
            Toast.makeText(this, "Штрихкод обновлен", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Штрихкод не сохранен", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_barcodes:
                barcodeViewModel.deleteAllBarcodes();
                Toast.makeText(this, "Все записи удалены", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}