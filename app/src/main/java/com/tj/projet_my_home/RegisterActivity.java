package com.tj.projet_my_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;

import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AndroidNetworking.initialize(this);
    }

    public void register(View v){

        EditText editName = findViewById(R.id.RegisterName);
        String name = editName.getText().toString();

        EditText editMail = findViewById(R.id.RegisterMail);
        String mail = editMail.getText().toString();

        EditText editMdp = findViewById(R.id.RegisterMDP);
        String mdp = editMdp.getText().toString();

        AndroidNetworking.post("https://myhouse.lesmoulinsdudev.com/register")
                .addBodyParameter("name", name)
                .addBodyParameter("login", mail)
                .addBodyParameter("password", mdp)
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        switch(response.code())
                        {
                            case 200 :
                                //Création de l'Intent explicite
                                Intent nextActivity = new Intent(
                                        RegisterActivity.this, //Contexte de départ
                                        MainActivity.class //Activité cible
                                );
                                // Démarrage de la nouvelle activité
                                startActivity(nextActivity);
                                break;
                            case 500 :
                                Toast toast2 = Toast.makeText(RegisterActivity.this,"Erreur Inscription",Toast.LENGTH_SHORT);
                                toast2.show();
                                break;
                            case 400 :
                                Toast toast3 = Toast.makeText(RegisterActivity.this,"Erreur 400",Toast.LENGTH_SHORT);
                                toast3.show();
                                break;
                            default :
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast toast = Toast.makeText(RegisterActivity.this,"Erreur produite",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
    }
}