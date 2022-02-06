package com.tj.projet_my_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class AddpieceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpiece);
    }

    //Fonction -> View pièces
    public void toPiece(View view){
        //Création de l'Intent explicite
        Intent nextActivity = new Intent(
                this, //Contexte de départ
                PieceActivity.class //Activité cible
        );
        // Démarrage de la nouvelle activité
        startActivity(nextActivity);
    }
}