package com.test.tpmobileimc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;

import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView resultatBienvenue;
    TextView resultatIMC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button home = findViewById(R.id.accueilhome);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent homepage = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(homepage);
            }
        });

        resultatBienvenue = findViewById(R.id.lbl_resultatnew);
        resultatBienvenue.setText("Bienvenue " +getIntent().getStringExtra("Nom") + " "+getIntent().getStringExtra("Prenom"));
        resultatIMC = findViewById(R.id.lbl_restulatnew2);
        ImageView image_resultat = findViewById(R.id.image_new);
        float IMC = getIntent().getFloatExtra("IMC", 0);
        if(IMC < 18.5){
            resultatIMC.setText("votre IMC est de " + IMC + " et vous êtes en insuffisance pondérale");
            image_resultat.setImageResource(R.drawable.maigre);
        }
        else if (IMC >= 18.5 && IMC <25){
            resultatIMC.setText("votre IMC est de " + IMC + " et vous avez une corpulence normal");
            image_resultat.setImageResource(R.drawable.normal);
        }
        else if (IMC >=25 && IMC <30){
            resultatIMC.setText("votre IMC est de " + IMC + " et vous êtes en surpoids");
            image_resultat.setImageResource(R.drawable.surpoids);
        }
        else if (IMC >=30 && IMC<35){
            resultatIMC.setText("votre IMC est de " + IMC + " et vous êtes en obésité modérée");
            image_resultat.setImageResource(R.drawable.modere);
        }
        else if (IMC >=35 && IMC <40){
            resultatIMC.setText("votre IMC est de " + IMC + " et vous êtes en obésité sévère");
            image_resultat.setImageResource(R.drawable.severe);
        }
        else if (IMC>=40){
            resultatIMC.setText("votre IMC est de " + IMC + " et vous êtes en obésité morbide ou massive");
            image_resultat.setImageResource(R.drawable.morbide);
        }
    }
}
