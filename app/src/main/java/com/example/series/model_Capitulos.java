package com.example.series;

public class model_Capitulos {

    private int idCapitulo;
    private String nombreCapitulo;
    private String imagenCapitulo;
    private String capitulo;
    private String numeroCapitulo;

    public model_Capitulos() {
    }

    public model_Capitulos(int idCapitulo, String nombreCapitulo, String imagenCapitulo, String capitulo, String numeroCapitulo) {
        this.idCapitulo = idCapitulo;
        this.nombreCapitulo = nombreCapitulo;
        this.imagenCapitulo = imagenCapitulo;
        this.capitulo = capitulo;
        this.numeroCapitulo = numeroCapitulo;
    }

    public int getIdCapitulo() {
        return idCapitulo;
    }

    public void setIdCapitulo(int idCapitulo) {
        this.idCapitulo = idCapitulo;
    }

    public String getNombreCapitulo() {
        return nombreCapitulo;
    }

    public void setNombreCapitulo(String nombreCapitulo) {
        this.nombreCapitulo = nombreCapitulo;
    }

    public String getImagenCapitulo() {
        return imagenCapitulo;
    }

    public void setImagenCapitulo(String imagenCapitulo) {
        this.imagenCapitulo = imagenCapitulo;
    }

    public String getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }

    public String getNumeroCapitulo() {
        return numeroCapitulo;
    }

    public void setNumeroCapitulo(String numeroCapitulo) {
        this.numeroCapitulo = numeroCapitulo;
    }
}
