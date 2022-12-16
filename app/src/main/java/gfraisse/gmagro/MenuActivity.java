package gfraisse.gmagro;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import gfraisse.gmagro.net.WSConnexionHTTPS;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViewById(R.id.deconexion).setOnClickListener(v->{
            WSConnexionHTTPS ws = new WSConnexionHTTPS(){
                @Override
                protected void onPostExecute(String s){
                    traiterDeconnexion(s);
                }
            };
            ws.execute("uc=deconnexion");
        });
    }

    private void traiterDeconnexion(String s) {
        Log.d("TraiterDeconnexion", s);
        try {
            JSONObject jsono = new JSONObject(s);
            if ((Boolean) jsono.get("success")){
                Toast.makeText(this, "Deconnexion Réussie !", Toast.LENGTH_SHORT).show();
                finish();
            }
        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(this, "La deconnexion n'a pas pu être effectué !!", Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        WSConnexionHTTPS ws = new WSConnexionHTTPS(){
            @Override
            protected void onPostExecute(String s){
                traiterDeconnexion(s);
            }
        };
        ws.execute("uc=deconnexion");
    }
}