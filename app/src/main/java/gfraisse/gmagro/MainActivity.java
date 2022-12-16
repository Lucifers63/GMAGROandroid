package gfraisse.gmagro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import gfraisse.gmagro.net.WSConnexionHTTPS;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.Connect).setOnClickListener(v ->{
            WSConnexionHTTPS ws = new WSConnexionHTTPS(){
                @Override
                protected void onPostExecute(String s){traiterConnexion(s);}
            };
            ws.execute("uc=connexion&login="+((TextView)findViewById(R.id.login)).getText().toString()+"&mdp="+((TextView)findViewById(R.id.password)).getText().toString());
        });
    }

    private void traiterConnexion(String s) {
        Log.d("TraiterConnexion",s);
        try {
            JSONObject jsono = new JSONObject(s);
            if ((Boolean) jsono.get("success")){
                Toast.makeText(this, "Authentification Réussie !", Toast.LENGTH_SHORT).show();
                jsono = jsono.getJSONObject("response");
                Intent intent = new Intent(this, MenuActivity.class) ;
                startActivity(intent);
            }else {
                Toast.makeText(this,"Login ou mot de passe non valide !!", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}