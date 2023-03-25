package com.mycompany.estructura;

import static com.mycompany.estructura.CodificacionInstrucciones.tamanodireccion;

public class Instruccion {
    
    private InstruccionesEnum instruccion;
    private String primerOperando;
    private String segundoOperando;
    private String etiqueta;
    private String direccionMemoria;
    private String comentario;
    private String[] codificacionOperando ;
    private String flag;
    
    
    public Instruccion() {
        
    }

    public Instruccion(InstruccionesEnum instruccion) {
        this.instruccion = instruccion;
        codificacionOperando =new String[tamanodireccion];
    }

    
    public Instruccion(InstruccionesEnum instruccion, String primerOperando) {
        this.instruccion = instruccion;
        this.primerOperando = primerOperando;
        codificacionOperando =new String[tamanodireccion];
    }

    
    public Instruccion(InstruccionesEnum instruccion, String primerOperando, String segundoOperando) {
        this.instruccion = instruccion;
        this.primerOperando = primerOperando;
        this.segundoOperando = segundoOperando;
        codificacionOperando =new String[tamanodireccion];
    }

    public Instruccion(InstruccionesEnum instruccion, String primerOperando, String segundoOperando, String etiqueta) {
        this.instruccion = instruccion;
        this.primerOperando = primerOperando;
        this.segundoOperando = segundoOperando;
        this.etiqueta = etiqueta;
        codificacionOperando =new String[tamanodireccion];;
    }
    
    

    
    public Instruccion(InstruccionesEnum instruccion, String primerOperando, String segundoOperando, String etiqueta, String direccionMemoria, String comentario, String[] codificacionOperando) {
        this.instruccion = instruccion;
        this.primerOperando = primerOperando;
        this.segundoOperando = segundoOperando;
        this.etiqueta = etiqueta;
        this.direccionMemoria = direccionMemoria;
        this.comentario = comentario;
        this.codificacionOperando = codificacionOperando;
    }

    public InstruccionesEnum getInstruccion() {
        return this.instruccion;
    }

    public void setInstruccion(InstruccionesEnum instruccion) {
        this.instruccion = instruccion;
    }

    public String getPrimerOperando() {
        return this.primerOperando;
    }

    public void setPrimerOperando(String primerOperando) {
        this.primerOperando = primerOperando;
    }

    public String getSegundoOperando() {
        return this.segundoOperando;
    }

    public void setSegundoOperando(String segundoOperando) {
        this.segundoOperando = segundoOperando;
    }

    public String getEtiqueta() {
        return this.etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getDireccionMemoria() {
        return this.direccionMemoria;
    }

    public void setDireccionMemoria(String direccionMemoria) {
        this.direccionMemoria = direccionMemoria;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String[] getCodificacionOperando() {
        return this.codificacionOperando;
    }

    public void setCodificacionOperando(String[] codificacionOperando) {
        this.codificacionOperando = codificacionOperando;
    }
    
    
    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Instruccion{" + "instruccion=" + instruccion + ", primerOperando=" + primerOperando + ", segundoOperando=" + segundoOperando + ", etiqueta=" + etiqueta + ", direccionMemoria=" + direccionMemoria + ", comentario=" + comentario + ", codificacionOperando=" + codificacionOperando + ", flag=" + flag + '}';
    }
    
}
