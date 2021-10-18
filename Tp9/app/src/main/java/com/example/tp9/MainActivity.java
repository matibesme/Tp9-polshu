package com.example.tp9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button boton;
    ImageView botonGaleria;
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObtenerReferencias();

        SetearListners();
    }

    private void SetearListners() {
        boton.setOnClickListener(boton_click);
        botonGaleria.setOnClickListener(botonGaleria_click);
    }

    View.OnClickListener boton_click= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
            }
        }
    };

    View.OnClickListener botonGaleria_click= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    };





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



            if (resultCode == RESULT_OK) {
                if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
                    Uri contactUri = data.getData();
                    String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
                    String[] projection2 = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
                    Cursor cursor = getContentResolver().query(contactUri, projection,
                            null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        int NombreIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                        String number = cursor.getString(numberIndex);
                        String nombre = cursor.getString(NombreIndex);
                        TextView textView3 = (TextView) findViewById(R.id.textView2);
                        TextView textView = (TextView) findViewById(R.id.textView3);
                        textView3.setText(nombre);
                        textView.setText(number);
                        textView.setVisibility(View.VISIBLE);
                        textView3.setVisibility(View.VISIBLE);
                    }



            }

                if (requestCode == CAMERA_REQUEST ){
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    botonGaleria.setImageBitmap(photo);
                    botonGaleria.setMinimumHeight(120);

                }
        }
    }

    public void ObtenerReferencias(){
        boton= (Button) findViewById(R.id.button);
        botonGaleria= (ImageView) findViewById(R.id.botonGaleria);
    }
}