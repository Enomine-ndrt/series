package com.example.series;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolderDatos> implements  View.OnClickListener{
   Context context;
   ArrayList<Series> datos;
   private View.OnClickListener listener;

    public AdapterList(Context context, ArrayList<Series> datos) {
        super();
        this.context = context;
        this.datos = datos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int i) {
        Series ca = datos.get(i);
        holder.titulo.setText(ca.getNombre());
        holder.descripcion.setText(ca.getDescripcion());
        String url = ca.getImagen();
        Picasso.with(context).load(url).into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
            this.listener = listener;
    }

    @Override
    public void onClick(View view) {

        if(listener != null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView descripcion;
        ImageView imagen;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
         imagen = (ImageView)itemView.findViewById(R.id.ima);
         titulo = itemView.findViewById(R.id.titulo);
         descripcion = itemView.findViewById(R.id.descripcion);

        }
    }
}
