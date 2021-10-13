package com.example.tp9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button boton;
    ImageButton botonGaleria;
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

            Intent intent = new Intent(getBaseContext(), SecondActivity.class);
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
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
                String returnString = data.getStringExtra("keyName");

                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(returnString);
                textView.setVisibility(View.VISIBLE);
            }

                if (requestCode == CAMERA_REQUEST ){
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    botonGaleria.setImageBitmap(photo);
                }
        }
    }

    public void ObtenerReferencias(){
        boton= (Button) findViewById(R.id.button);
        botonGaleria= (ImageButton) findViewById(R.id.botonGaleria);
    }
}