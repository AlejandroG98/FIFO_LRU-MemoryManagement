package com.mycompany.estructura;

import static com.mycompany.estructura.EjecucionInstrucciones.decimalAHexadecimalString;
import static com.mycompany.estructura.EjecucionInstrucciones.hexadecimalADecimalLong;
import static com.mycompany.estructura.EjecucionInstrucciones.numeroDeBitsContenidoMemoria;

public final class Memoria {
    
    private long direccionEnDecimal;
    private String direccionEnHexadecimal;
    private long valorAnteriorDecimal;
    private String valorAnteriorHexadecimal;
    private long valorPosteriorDecimal;
    private String valorPosteriorHexadecimal;

    public Memoria() {
    }

    public Memoria(long direccionEnDecimal, String direccionEnHexadecimal, long valorAnteriorDecimal, String valorAnteriorHexadecimal, long valorPosteriorDecimal, String valorPosteriorHexadecimal) {
        
        this.setDireccionEnDecimal(direccionEnDecimal);
        this.setDireccionEnHexadecimal(direccionEnHexadecimal);
        this.setValorAnteriorDecimal(valorAnteriorDecimal);
        this.setValorAnteriorHexadecimal(valorAnteriorHexadecimal);
        this.setValorPosteriorDecimal(valorPosteriorDecimal);
        this.setValorPosteriorDecimal(hexadecimalADecimalLong(valorPosteriorHexadecimal));
        
    }

    public Memoria(long direccionEnDecimal, long valorAnteriorDecimal, long valorPosteriorDecimal) {
       
        
        this.setDireccionEnDecimal(direccionEnDecimal);
        this.setDireccionEnHexadecimal(decimalAHexadecimalString(direccionEnDecimal,numeroDeBitsContenidoMemoria));
        this.setValorAnteriorDecimal(valorAnteriorDecimal);
        this.setValorAnteriorHexadecimal(decimalAHexadecimalString(valorAnteriorDecimal,numeroDeBitsContenidoMemoria));
        this.setValorPosteriorDecimal(valorPosteriorDecimal);
        this.setValorPosteriorHexadecimal(decimalAHexadecimalString(valorPosteriorDecimal,numeroDeBitsContenidoMemoria));
        
    }

    public Memoria(String direccionEnHexadecimal, String valorAnteriorHexadecimal, String valorPosteriorHexadecimal) {
        
        this.setDireccionEnHexadecimal(direccionEnHexadecimal);
        this.setDireccionEnDecimal((int) hexadecimalADecimalLong(direccionEnHexadecimal));
        this.setValorAnteriorHexadecimal(valorAnteriorHexadecimal);
        this.setValorAnteriorDecimal(hexadecimalADecimalLong(valorAnteriorHexadecimal));
        this.setValorPosteriorHexadecimal(valorPosteriorHexadecimal);
        this.setValorPosteriorDecimal(hexadecimalADecimalLong(valorPosteriorHexadecimal));
    }

    public long getDireccionEnDecimal() {
        return this.direccionEnDecimal;
    }

    public void setDireccionEnDecimal(long direccionEnDecimal) {
        this.direccionEnDecimal = direccionEnDecimal;
    }

    public String getDireccionEnHexadecimal() {
        return this.direccionEnHexadecimal;
    }

    public void setDireccionEnHexadecimal(String direccionEnHexadecimal) {
        if (direccionEnHexadecimal.isBlank()) direccionEnHexadecimal="0";
        direccionEnHexadecimal=direccionEnHexadecimal.replaceAll("\\[", "").replaceAll("\\]","").replaceAll("h","").replaceAll(" ","").replaceAll("-","").replaceAll("/","").replaceAll("H","");
        this.direccionEnHexadecimal = direccionEnHexadecimal;
    }

    public long getValorAnteriorDecimal() {
        return this.valorAnteriorDecimal;
    }

    public void setValorAnteriorDecimal(long valorAnteriorDecimal) {
        this.valorAnteriorDecimal = valorAnteriorDecimal;
    }

    public String getValorAnteriorHexadecimal() {
        return this.valorAnteriorHexadecimal;
    }

    public void setValorAnteriorHexadecimal(String valorAnteriorHexadecimal) {
        if (valorAnteriorHexadecimal.isBlank()) valorAnteriorHexadecimal="0";
        valorAnteriorHexadecimal=valorAnteriorHexadecimal.replaceAll("\\[", "").replaceAll("\\]","").replaceAll("h","").replaceAll(" ","").replaceAll("-","").replaceAll("/","").replaceAll("H","");
        this.valorAnteriorHexadecimal = valorAnteriorHexadecimal;
    }

    public long getValorPosteriorDecimal() {
        return this.valorPosteriorDecimal;
    }

    public void setValorPosteriorDecimal(long valorPosteriorDecimal) {
        this.valorPosteriorDecimal = valorPosteriorDecimal;
    }

    public String getValorPosteriorHexadecimal() {
        return this.valorPosteriorHexadecimal;
    }

    public void setValorPosteriorHexadecimal(String valorPosteriorHexadecimal) {
        if (valorPosteriorHexadecimal.isBlank()) valorPosteriorHexadecimal="0";
        valorPosteriorHexadecimal=valorPosteriorHexadecimal.replaceAll("\\[", "").replaceAll("\\]","").replaceAll("h","").replaceAll(" ","").replaceAll("-","").replaceAll("/","").replaceAll("H","");
        this.valorPosteriorHexadecimal = valorPosteriorHexadecimal;
    }

    @Override
    public String toString() {
        return "Memoria{" + "direccionEnDecimal=" + direccionEnDecimal + ", direccionEnHexadecimal=" + direccionEnHexadecimal + ", valorAnteriorDecimal=" + valorAnteriorDecimal + ", valorAnteriorHexadecimal=" + valorAnteriorHexadecimal + ", valorPosteriorDecimal=" + valorPosteriorDecimal + ", valorPosteriorHexadecimal=" + valorPosteriorHexadecimal + '}';
    }

   
    
    
}
