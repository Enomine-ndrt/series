package com.example.series;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CapitulosSeleccion extends RecyclerView.Adapter<CapitulosSeleccion.ViewHolderDatos>
{
    Context context;
    ArrayList<model_Capitulos> capitulos;
    private View.OnClickListener listener;

    public CapitulosSeleccion(Context context, ArrayList<model_Capitulos> capitulos) {
        this.context = context;
        this.capitulos = capitulos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_capitulos,null,false);
       // view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos viewHolderDatos, int i) {
        model_Capitulos  cap = capitulos.get(i);
        String url = cap.getImagenCapitulo();
        String urlvideo = cap.getCapitulo();
        String youtube = "<html><body><iframe width=\"100%\" height=\"100%\" src=https://www.youtube.com/embed/"+urlvideo+" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        //String youtube = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/watch?v="+urlvideo+"frameborder=\"0\" allowfullscreen></iframe></body></html>";

        Picasso.with(context).load(url).into(viewHolderDatos.imagenPortada);
        viewHolderDatos.nombre.setText(cap.getNombreCapitulo());
        viewHolderDatos.capitulo.setText(cap.getNumeroCapitulo());
        //viewHolderDatos.webView.loadUrl(youtube);

        viewHolderDatos.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = viewHolderDatos.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        viewHolderDatos.webView.loadData(youtube, "text/html", "utf-8");

    }

    @Override
    public int getItemCount() {
        return capitulos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        ImageView imagenPortada;
        TextView nombre;
        TextView capitulo;
        WebView webView;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            imagenPortada = itemView.findViewById(R.id.imagen);
            nombre = itemView.findViewById(R.id.tituloCapitulo);
            capitulo = itemView.findViewById(R.id.capitulos);
            webView = itemView.findViewById(R.id.webview);
            //webView.getSettings().setJavaScriptEnabled(true);
             //webView.setWebChromeClient(new WebChromeClient() {
             //} );

           // webView.loadData(dataUrl, "text/html", "utf-8");
            }
    }


}
