package com.tj.projet_my_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toRegister(View v){
        //Création de l'Intent explicite
        Intent nextActivity = new Intent(
                this, //Contexte de départ
                RegisterActivity.class //Activité cible
        );
        // Démarrage de la nouvelle activité
        startActivity(nextActivity);
    }
}