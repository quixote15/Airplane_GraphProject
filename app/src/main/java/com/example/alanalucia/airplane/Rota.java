package com.example.alanalucia.airplane;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alanalucia.airplane.data_processing.Dijkstra;
import com.example.alanalucia.airplane.data_processing.Grafo;

import java.util.LinkedList;
import java.util.Stack;

public class Rota extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Spinner destino;
    Button go_btn;
    TextView rota;
    Grafo g = new Grafo(16);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rota);

        rota = (TextView) findViewById(R.id.text_rota);
        spinner = (Spinner) findViewById(R.id.spinner);
        destino = (Spinner) findViewById(R.id.spinner2);
        go_btn = (Button) findViewById(R.id.go_button);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.cidades,R.layout.simple_spinner);
        spinner.setAdapter(adapter);
        destino.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        destino.setOnItemSelectedListener(this);
        go_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!spinner.getSelectedItem().equals(destino.getSelectedItem())){
                    String cidade = spinner.getSelectedItem().toString();
                    int vertice = spinner.getSelectedItemPosition();
                    int v_final =destino.getSelectedItemPosition();
                    g.printAdjacencyList();
                    getPath(vertice,v_final,Dijkstra.dijkstra(g,vertice));


                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(id == R.id.spinner)
            Toast.makeText(this, "Você escolheu a cidade de Origem! Agora qual o seu destino?", Toast.LENGTH_SHORT).show();
        else if(id == R.id.spinner2)
            Toast.makeText(this, "Você escolheu a cidade de Destino! Agora Vamos calcular sua rota?", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void getPath(int origem,int target,LinkedList<Integer> lista){
        Stack stack = new Stack();

        int u = target;
        Log.i("indexs ", "" + u);
        while ( u > 0 && lista.get(u) != null){
            stack.push(u);
            u = lista.get(u);
        }
        stack.push(u);


        String rota_string = "Sua Rota final: ";
        while(!stack.empty()){

            int cidade =(int) stack.pop();
            if(cidade != -1)
            rota_string +=spinner.getItemAtPosition(cidade).toString()+ " ";
            if(cidade == -1) {
                rota_string = "Rota não existe.";
                break;
            }




        }
        int distancia =  g.getDistancia()[target];
        if(distancia != -1)
        rota_string+=" distancia: " + distancia + " mil km";
        rota.setText(rota_string);
        rota.setVisibility(TextView.VISIBLE);
        stack = null;
        System.gc(); //garbage colector

    }
}
