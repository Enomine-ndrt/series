package com.example.series;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ArrayList<Series> seriesArrayList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        seriesArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        seriesDisponibles();

    }

    public void seriesDisponibles(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando Datos");
        progressDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://seriesyoutube.000webhostapp.com/webService/webservice.php", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        if(statusCode == 200){
                            progressDialog.dismiss();
                            try{
                                JSONArray jsonArray = new JSONArray(new String(responseBody));
                                respuestaJSON(jsonArray);

                            }catch(JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                }

        );

    }

    private void respuestaJSON(JSONArray jsonArray) {
            for(int i =0;i<jsonArray.length();i++){
                Series c = new Series();
                JSONObject jsonObject = null;
                try{
                    jsonObject = jsonArray.getJSONObject(i);
                    c.setId(jsonObject.getInt("id"));
                    c.setNombre(jsonObject.getString("nombre"));
                    c.setDescripcion(jsonObject.getString("descripcion"));
                    c.setImagen(jsonObject.getString("imagenSerie"));



                }catch(JSONException e){
                    e.printStackTrace();
                }

                seriesArrayList.add(c);
                AdapterList series = new AdapterList(this,seriesArrayList);
                series.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(MainActivity.this, "Seleccion: "+seriesArrayList.get(recyclerView.getChildAdapterPosition(view)).getId(),
                          //      Toast.LENGTH_SHORT).show();
                        //obtener la posicion del elemento
                        int id = seriesArrayList.get(recyclerView.getChildAdapterPosition(view)).getId();
                        Intent intent = new Intent(getApplicationContext(),Capitulos.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(series);
            }
    }


}
