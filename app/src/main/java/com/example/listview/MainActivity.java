package com.example.listview;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    // Model: Record (intents=puntuació, nom)
    class Record {
        public int intents;
        public String nom;
        public Drawable imagencita;

        public Record(int _intents, String _nom ,Drawable _img) {
            intents = _intents;
            nom = _nom;
            imagencita=_img;
        }
        public int getIntents(){
            return intents;
        }
    }
    // Model = Taula de records: utilitzem ArrayList
    ArrayList<Record> records;




    // ArrayAdapter serà l'intermediari amb la ListView
    ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> nombres=new ArrayList<String>();
        nombres.add("Isma");
        nombres.add("Irene");
        nombres.add("Ivan");
        nombres.add("Albert");
        nombres.add("Enric");
        nombres.add("Pablo");
        nombres.add("Rafa");
        nombres.add("Sergio");
        nombres.add("Jordi");
        nombres.add("Borja");
        nombres.add("Alejandro");
        nombres.add("Lluis");
        nombres.add("Marc");
        nombres.add("Erik");
        nombres.add("Josep");


        //R.drawable.
        ArrayList<Drawable> images=new ArrayList<Drawable>();
        images.add(getResources().getDrawable(R.drawable.perfil1));
        images.add(getResources().getDrawable(R.drawable.perfil2));
        images.add(getResources().getDrawable(R.drawable.perfil3));
        images.add(getResources().getDrawable(R.drawable.perfil4));
        images.add(getResources().getDrawable(R.drawable.perfil5));
        images.add(getResources().getDrawable(R.drawable.perfil6));
        images.add(getResources().getDrawable(R.drawable.perfil7));


        // Inicialitzem model
        records = new ArrayList<Record>();
        // Afegim alguns exemples
        records.add( new Record(33,"Manolo",getResources().getDrawable(R.drawable.perfil7)) );
        records.add( new Record(12,"Pepe",getResources().getDrawable(R.drawable.perfil6)) );
        records.add( new Record(42,"Laura",getResources().getDrawable(R.drawable.perfil5)) );

        // Inicialitzem l'ArrayAdapter amb el layout pertinent
        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                // "Pintem" valors (també quan es refresca)
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                ((ImageView) convertView.findViewById(R.id.imagencita)).setImageDrawable(getItem(pos).imagencita);
                return convertView;
            }

        };

        // busquem la ListView i li endollem el ArrayAdapter
        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);

        // botó per afegir entrades a la ListView
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<3;i++) {
                    int rand1= (int) (Math.random()*50)+1;
                    int rand2= (int) (Math.random()*15);
                    int rand3= (int) (Math.random()*7);
                    records.add(new Record(rand1, nombres.get(rand2),images.get(rand3)));
                }
                // notificar l'adapter dels canvis al model
                adapter.notifyDataSetChanged();
            }
        });

        Button bu = (Button) findViewById(R.id.ordena);
        bu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Collections.sort(records, Comparator.comparing(Record::getIntents).thenComparing(Record::getIntents));
                // notificar l'adapter dels canvis al model
                adapter.notifyDataSetChanged();
            }
        });



    }
}