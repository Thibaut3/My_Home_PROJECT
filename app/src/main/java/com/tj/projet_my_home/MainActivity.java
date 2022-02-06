package com.tj.projet_my_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Fonction -> View inscription
    public void toRegister(View v){
        //Création de l'Intent explicite
        Intent nextActivity = new Intent(
                this, //Contexte de départ
                RegisterActivity.class //Activité cible
        );
        // Démarrage de la nouvelle activité
        startActivity(nextActivity);
    }

    //Fonction connexion -> View pièce
    public void connexion(View v){

        //Récupération du mail (login)
        EditText editMail = findViewById(R.id.ConnexionMail);
        String mail = editMail.getText().toString();

        //Récupération du mot de passe
        EditText editMdp = findViewById(R.id.ConnexionMDP);
        String mdp = editMdp.getText().toString();

        //Création de la requête
        AndroidNetworking.post("https://myhouse.lesmoulinsdudev.com/auth")
                .addBodyParameter("login", mail)
                .addBodyParameter("password", mdp)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Récupération du TOKEN
                        String token = null;
                        try {
                            token = response.getString("token");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Création de l'Intent explicite
                        Intent nextActivity = new Intent(
                                MainActivity.this, //Contexte de départ
                                PieceActivity.class //Activité cible
                        );
                        //Passage du token en paramètre
                        nextActivity.putExtra("token", token);
                        // Démarrage de la nouvelle activité
                        startActivity(nextActivity);
                    }

                    @Override
                    public void onError(ANError error) {
                        //Si l'utilisateur est inconnue
                        if(error.getErrorCode() == 401){
                            Toast toast = Toast.makeText(MainActivity.this,"Utilisateur inconnue",Toast.LENGTH_SHORT);
                            toast.show();
                        }

                        //Affiche les informations des erreurs (log)
                        if (error.getErrorCode() != 0) {
                            // received error from server
                            // error.getErrorCode() - the error code from server
                            // error.getErrorBody() - the error body from server
                            // error.getErrorDetail() - just an error detail
                            Log.d("code", "onError errorCode : " + error.getErrorCode());
                            Log.d("body", "onError errorBody : " + error.getErrorBody());
                            Log.d("detail", "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d("detailElse", "onError errorDetail : " + error.getErrorDetail());
                        }
                    }
                });
    }
}