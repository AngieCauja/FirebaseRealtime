package com.example.firebasecloudmessaging;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Demo1Activity extends AppCompatActivity{
    private FirebaseDatabase database;
    private DatabaseReference reference;

    private EditText text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);

        database =FirebaseDatabase.getInstance();

        text = findViewById(R.id.nombre_edit_text);


        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = database.getReference("persona/nombre");
                String nombre = text.getText().toString();
                reference.setValue(nombre);
            }
        });
    }
}

