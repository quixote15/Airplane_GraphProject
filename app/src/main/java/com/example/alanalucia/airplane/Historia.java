package com.example.alanalucia.airplane;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Historia extends AppCompatActivity implements View.OnClickListener{
    int contador = 1;
    TextView tutorialView;
    Button next_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotas);
        //inicializar variaveis
        next_button= (Button) findViewById(R.id.nextButton);
        tutorialView = (TextView) findViewById(R.id.textTutorial);
        //inicializar string historia
       // tutorialView.setText(getTutoText(contador++));

        ///set listener
        next_button.setOnClickListener(this);
    }



    public String getTutoText(int next){
        switch(next){
            case 1: return getResources().getString(R.string.introducao);
            case 2: return getResources().getString(R.string.hist_1);
            case 3: return getResources().getString(R.string.hist_2);
            default: return null;

        }
    }

    @Override
    public void onClick(View v) {
        if(contador < 3) {
            String text = getTutoText(contador++);
            tutorialView.setText(text);
        }
        else{
            Intent intent = new Intent(this,Rota.class);
            startActivity(intent);
        }
    }
}
