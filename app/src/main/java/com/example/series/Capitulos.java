package com.example.series;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Capitulos extends AppCompatActivity {
  //  TextView  res;
  ImageView imageView;
    int id;

    ArrayList<model_Capitulos> seriesArrayList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitulos);
          id = getIntent().getExtras().getInt("id");
       // res = findViewById(R.id.resultado);
        //res.setText("id: "+id);
        recyclerView = findViewById(R.id.Capitulosrecycler);
        recyclerView.setHasFixedSize(true);
        seriesArrayList = new ArrayList<>();
        imageView = (ImageView) findViewById(R.id.fondo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        seriesDisponibles(id);


    }

    public void seriesDisponibles(int id){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando Datos");
        progressDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://seriesyoutube.000webhostapp.com/webService/webservice.php?id="+id, new AsyncHttpResponseHandler() {
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
            model_Capitulos ci = new model_Capitulos();
            JSONObject jsonObject = null;
            try{
                jsonObject = jsonArray.getJSONObject(i);
                ci.setIdCapitulo(jsonObject.getInt("id"));
                ci.setNombreCapitulo(jsonObject.getString("nombre"));
                ci.setImagenCapitulo(jsonObject.getString("imagenSerie"));
                ci.setCapitulo(jsonObject.getString("capitulo"));
                ci.setNumeroCapitulo(jsonObject.getString("numeroCapitulo"));
              //  Picasso.with(this).load(ci.getImagenCapitulo()).transform(new CropSquareTransformation()).resize(50,50).centerCrop().into(imageView);

            }catch(JSONException e){
                e.printStackTrace();
            }

            seriesArrayList.add(ci);
            CapitulosSeleccion series = new CapitulosSeleccion(this,seriesArrayList);

            recyclerView.setAdapter(series);
        }
    }


}


