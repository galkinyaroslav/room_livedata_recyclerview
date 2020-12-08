package com.example.room_livedata_recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class AddBarcodesActivity extends AppCompatActivity {
    private EditText editTextBarcodenum;
    private EditText editTextName;
    private EditText editTextDocname;
    private EditText editTextDocdate;
    private EditText editTextDocnum;
    private EditText editTextDate;
    private EditText editTextInvnum;
    private EditText editTextFactnum;
    private EditText editTextPassportnum;
    private EditText editTextFnquantity;
    private EditText editTextFncost;
    private EditText editTextBuquantity;
    private EditText editTextBucost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barcodes);

        editTextBarcodenum = findViewById(R.id.edit_text_barcodenum);
        editTextName = findViewById(R.id.edit_text_name);
        editTextDocname = findViewById(R.id.edit_text_docname);
        editTextDocdate = findViewById(R.id.edit_text_docdate);
        editTextDocnum = findViewById(R.id.edit_text_docnum);
        editTextDate = findViewById(R.id.edit_text_date);
        editTextInvnum = findViewById(R.id.edit_text_invnum);
        editTextFactnum = findViewById(R.id.edit_text_factnum);
        editTextPassportnum = findViewById(R.id.edit_text_passportnum);
        editTextFnquantity = findViewById(R.id.edit_text_fnquantity);
        editTextFncost = findViewById(R.id.edit_text_fncost);
        editTextBuquantity = findViewById(R.id.edit_text_buquantity);
        editTextBucost = findViewById(R.id.edit_text_bucost);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Добавить штрихкод");
    }
    private void saveBarcode(){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_barcode_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_barcode:
                saveBarcode();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}