package com.example.tp9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {


    Button boton2;
    ImageView botonGaleria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ObtenerReferencias();

        SetearListners();
    }


    private void SetearListners() {
        boton2.setOnClickListener(boton2_click);

    }

    View.OnClickListener boton2_click= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            EditText editText = (EditText) findViewById(R.id.editText);
            String stringToPassBack = editText.getText().toString();

            // Put the String to pass back into an Intent and close this activity
            Intent intent = new Intent();
            intent.putExtra("keyName", stringToPassBack);
            setResult(RESULT_OK, intent);
            finish();
        }
    };

    public void ObtenerReferencias(){
        boton2= (Button) findViewById(R.id.button2);
        botonGaleria= (ImageView) findViewById(R.id.botonGaleria);
    }


}