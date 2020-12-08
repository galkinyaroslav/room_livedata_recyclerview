package com.example.room_livedata_recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddBarcodesActivity extends AppCompatActivity {
    public static   String EXTRA_BARCODENUM=
            "com.example.room_livedata_recyclerview.EXTRA_BARCODENUM";
    public static  String EXTRA_NAME=
            "com.example.room_livedata_recyclerview.EXTRA_NAME";
    public static  String EXTRA_DOCNAME=
            "com.example.room_livedata_recyclerview.EXTRA_DOCNAME";
    public static  String EXTRA_DOCDATE=
            "com.example.room_livedata_recyclerview.EXTRA_DOCDATE";
    public static  String EXTRA_DOCNUM=
            "com.example.room_livedata_recyclerview.EXTRA_DOCNUM";
    public static  String EXTRA_DATE=
            "com.example.room_livedata_recyclerview.EXTRA_DATE";
    public static  String EXTRA_INVNUM=
            "com.example.room_livedata_recyclerview.EXTRA_INVNUM";
    public static  String EXTRA_FACTNUM=
            "com.example.room_livedata_recyclerview.EXTRA_FACTNUM";
    public static  String EXTRA_PASSPORTNUM=
            "com.example.room_livedata_recyclerview.EXTRA_PASSPORTNUM";
    public static  String EXTRA_FNQUANTITY=
            "com.example.room_livedata_recyclerview.EXTRA_FNQUANTITY";
    public static  String EXTRA_FNCOST=
            "com.example.room_livedata_recyclerview.EXTRA_FNCOST";
    public static  String EXTRA_BUQUANTITY=
            "com.example.room_livedata_recyclerview.EXTRA_BUQUANTITY";
    public static  String EXTRA_BUCOST=
            "com.example.room_livedata_recyclerview.EXTRA_BUCOST";

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
        String barcodenum=editTextBarcodenum.getText().toString();
        String name=editTextName.getText().toString();
        String docname=editTextDocname.getText().toString();
        String docdate=editTextDocdate.getText().toString();
        String docnum=editTextDocnum.getText().toString();
        String date=editTextDate.getText().toString();
        String invnum=editTextInvnum.getText().toString();
        String factnum=editTextFactnum.getText().toString();
        String passportnum=editTextPassportnum.getText().toString();
        String fnquantity=editTextFnquantity.getText().toString();
        String fncost=editTextFncost.getText().toString();
        String buquantity=editTextBuquantity.getText().toString();
        String bucost=editTextBucost.getText().toString();

        if (barcodenum.trim().isEmpty()|| name.trim().isEmpty()){
            Toast.makeText(this,"Введите \"Наименование\" и \"Номер штрихкода\"", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data=new Intent();
        data.putExtra(EXTRA_BARCODENUM,barcodenum);
        data.putExtra(EXTRA_NAME,name);
        data.putExtra(EXTRA_DOCNAME,docname);
        data.putExtra(EXTRA_DOCDATE,docdate);
        data.putExtra(EXTRA_DOCNUM,docnum);
        data.putExtra(EXTRA_DATE,date);
        data.putExtra(EXTRA_INVNUM,invnum);
        data.putExtra(EXTRA_FACTNUM,factnum);
        data.putExtra(EXTRA_PASSPORTNUM,passportnum);
        data.putExtra(EXTRA_FNQUANTITY,fnquantity);
        data.putExtra(EXTRA_FNCOST,fncost);
        data.putExtra(EXTRA_BUQUANTITY,buquantity);
        data.putExtra(EXTRA_BUCOST,bucost);

        setResult(RESULT_OK,data);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_barcode_menu, menu);
        return true;
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