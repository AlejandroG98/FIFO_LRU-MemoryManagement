package com.mycompany.estructura;

import static com.mycompany.estructura.EjecucionInstrucciones.decimalAHexadecimalString;
import static com.mycompany.estructura.EjecucionInstrucciones.hexadecimalADecimalLong;
import static com.mycompany.estructura.EjecucionInstrucciones.numeroDeBitsRegisto;

public final class Registro {
    
    private String nombreRegistro;
    private long valorAnteriorDecimal;
    private String valorAnteriorHexadecimal;
    private long valorPosteriorDecimal;
    private String valorPosteriorHexadecimal;


    public Registro() {
    }

    public Registro(String nombreRegistro, long valorAnteriorDecimal, String valorAnteriorHexadecimal, long valorPosteriorDecimal, String valorPosteriorHexadecimal) {
        
        this.setNombreRegistro(nombreRegistro);
        this.setValorAnteriorDecimal(valorAnteriorDecimal);
        this.setValorAnteriorHexadecimal(valorAnteriorHexadecimal);
        this.setValorPosteriorDecimal(valorPosteriorDecimal);
        this.setValorPosteriorDecimal(hexadecimalADecimalLong(valorPosteriorHexadecimal));
        
    }

    public Registro(String nombreRegistro, long valorAnteriorDecimal, long valorPosteriorDecimal) {
       
        this.setNombreRegistro(nombreRegistro);
        this.setValorAnteriorDecimal(valorAnteriorDecimal);
        this.setValorAnteriorHexadecimal(decimalAHexadecimalString(valorAnteriorDecimal,numeroDeBitsRegisto));
        this.setValorPosteriorDecimal(valorPosteriorDecimal);
        this.setValorPosteriorHexadecimal(decimalAHexadecimalString(valorPosteriorDecimal,numeroDeBitsRegisto));
        
    }

    public Registro(String nombreRegistro, String valorAnteriorHexadecimal, String valorPosteriorHexadecimal) {
        this.setNombreRegistro(nombreRegistro);
        this.setValorAnteriorHexadecimal(valorAnteriorHexadecimal);
        this.setValorAnteriorDecimal(hexadecimalADecimalLong(valorAnteriorHexadecimal));
        this.setValorPosteriorHexadecimal(valorPosteriorHexadecimal);
        this.setValorPosteriorDecimal(hexadecimalADecimalLong(valorPosteriorHexadecimal));
    }
    
     public String getNombreRegistro() {
        return nombreRegistro;
    }

    public void setNombreRegistro(String nombreRegistro) {
        this.nombreRegistro = nombreRegistro;
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
        return "Registro{" + "nombreRegistro=" + nombreRegistro + ", valorAnteriorDecimal=" + valorAnteriorDecimal + ", valorAnteriorHexadecimal=" + valorAnteriorHexadecimal + ", valorPosteriorDecimal=" + valorPosteriorDecimal + ", valorPosteriorHexadecimal=" + valorPosteriorHexadecimal + '}';
    }
    
    
}
