package com.test.tpmobileimc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {

    BaseDeDonnee db;
    Button BDD;
    // fonction pour afficher Toast
    public void message(){
        EditText nomEdittext = findViewById (R.id.txt_Nom);
        EditText prenomEdittext = findViewById (R.id.txt_Prenom);
        String nomstring = nomEdittext.getText().toString();
        String prenomstring = prenomEdittext.getText().toString();
        Toast.makeText(getApplicationContext(), "Bienvenue " + nomstring + " " + prenomstring , Toast.LENGTH_SHORT).show();
    }

    // fonction pour afficher message Bienvenue
    public void messageResultat(){
        EditText nomEdittext = findViewById (R.id.txt_Nom);
        EditText prenomEdittext = findViewById (R.id.txt_Prenom);
        String nomstring = nomEdittext.getText().toString();
        String prenomstring = prenomEdittext.getText().toString();
        TextView view = findViewById(R.id.lbl_resultat);
        view.setText("Bienvenue " + nomstring + " " + prenomstring);
    }
/*
    public void messageIMC(){
//        EditText PoidsEditText = findViewById (R.id.txt_poids);
//        EditText TailleEditText = findViewById (R.id.txt_taille);
        int PoidsInt = Integer.parseInt(PoidsEditText.getText().toString());
        float TailleInt = Integer.parseInt(TailleEditText.getText().toString());
        TailleInt = TailleInt/100;
        float IMC = PoidsInt/(TailleInt*TailleInt);
        TextView view = findViewById(R.id.lbl_resultat2);
        view.setText("votre IMC est de " + IMC);
    }

*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_valider = findViewById (R.id.btn_valider);

        BDD = (Button) findViewById(R.id.bouton_bdd);
        db = new BaseDeDonnee(this);

        // list Poids
        final Spinner spinnerPoids = findViewById (R.id.list_poids);
        Integer[] optionsPoids = new Integer[150] ;
        for (int i=0 ; i<optionsPoids.length; i++){
            optionsPoids[i] = i;
        }
        ArrayAdapter<Integer> adapterPoids = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, optionsPoids);
        adapterPoids.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPoids.setAdapter(adapterPoids);

        // list Taille
        final Spinner spinnerTaille = findViewById (R.id.list_taille);
        Integer[] optionsTaille = new Integer[250] ;
        for (int i=0 ; i<optionsTaille.length; i++){
            optionsTaille[i] = i;
        }
        ArrayAdapter<Integer> adapterTaille = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, optionsTaille);
        adapterTaille.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTaille.setAdapter(adapterTaille);

/*
        // Bouton Toast
        btn_valider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                message();
            }
        });
*/
        BDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent homepage = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(homepage);
            }
        });
        // Bouton resultat Bienvenue + IMC
        btn_valider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //messageResultat();
                EditText nomEdittext = findViewById (R.id.txt_Nom);
                EditText prenomEdittext = findViewById (R.id.txt_Prenom);
                String nomstring = nomEdittext.getText().toString();
                String prenomstring = prenomEdittext.getText().toString();
                int Poids = Integer.parseInt(spinnerPoids.getSelectedItem().toString());
                float Taille = Float.parseFloat(spinnerTaille.getSelectedItem().toString());
                Taille = Taille/100;
                float IMC = Poids/(Taille*Taille);
                String IMCstring = Float.toString(IMC);
                db.addTache(nomstring, prenomstring, IMCstring);
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("Nom", nomstring);
                intent.putExtra("Prenom", prenomstring);
                intent.putExtra("IMC", IMC);
                startActivity(intent);
            }
        });
    }
}
