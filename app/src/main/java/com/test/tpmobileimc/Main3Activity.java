package com.test.tpmobileimc;

import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    BaseDeDonnee db;
    ListView list;
    ArrayList<String> listInfo;
    ArrayAdapter adapternext;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        db = new BaseDeDonnee(this);
        listInfo = new ArrayList<>();
        home = (Button) findViewById(R.id.accueil);
        list = (ListView) findViewById(R.id.listInfo);

        viewData();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent homepage = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(homepage);
            }
        });
    }

    private void viewData() {

        Cursor cursor = db.viewData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Rien a afficher", Toast.LENGTH_SHORT).show();
        } else {

            while (cursor.moveToNext()){
                listInfo.add(cursor.getString(1)+ " " + cursor.getString(2) + " " + cursor.getString(3));
            }

            adapternext = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listInfo);
            list.setAdapter(adapternext);



        }
    }
}