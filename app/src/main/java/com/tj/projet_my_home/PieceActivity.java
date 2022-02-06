package com.tj.projet_my_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class PieceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece);
        /*ArrayList<Piece> list = new ArrayList<>();
        list.add(new Piece("Cuisine",1));

        PieceAdapter pieceAdapter = new PieceAdapter(this, R.layout.piece_item, list);
        ListView listView = findViewById(R.id.listPiece);
        listView.setAdapter(pieceAdapter);*/
    }

    //Fonction -> View Ajout pièces
    public void toAddPiece(View view){
        //Création de l'Intent explicite
        Intent nextActivity = new Intent(
                this, //Contexte de départ
                AddpieceActivity.class //Activité cible
        );
        // Démarrage de la nouvelle activité
        startActivity(nextActivity);
    }
}