package com.mycompany.estructura;

public enum InstruccionesEnum{    

    NOP("NOP","00h"),
    STI("STI","01h"),
    CLI("CLI","02h"),
    MOV("MOV","10h"),
    PUSH("PUSH","11h"),
    POP("POP","12h"),
    ADD("ADD","20h"),
    SUB("SUB","21h"),
    MUL("MUL","22h"),
    DIV("DIV","23h"),
    INC("INC","24h"),
    DEC("DEC","25h"),
    CMP("CMP","26h"),
    NEG("NEG","27h"),
    AND("AND","30h"),
    OR("OR","31h"),
    XOR("XOR","32h"),
    TEST("TEST","33h"),
    NOT("NOT","34h"),
    SAL("SAL","35h"),
    SAR("SAR","36h"),
    JMP("JMP","40h"),
    JE("JE","41h"),
    JNE("JNE","42h"),
    JL("JL","43h"),
    JLE("JLE","44h"),
    JG("JG","45h"),
    JGE("JGE","46h"),
    CALL("CALL","47h"),
    RET("RET","48h"),
    INT("INT","49h"),
    IRET("IRET","4Ah"),
    IN("IN","50h"),
    OUT("OUT","51h"),
    INMEDIATO("INMEDIATO","0"),
    REGISTRO("REGISTRO","1"),
    MEMORIA("MEMORIA","2"),
    INDIRECTO("INDIRECTO","3"),
    RELATIVO("RELATIVO","4"),
    INDEXADO("INDEXADO","5"),
    ENPC("ENPC","6"),
    ROL("ROL","XXh"),
    ROR("ROR","XXh");
    
    
    
    private String nombreInstruccion;
    private String valorHexadecimal;

    private InstruccionesEnum(String nombreInstruccion, String valorHexadecimal) {
        this.nombreInstruccion = nombreInstruccion;
        this.valorHexadecimal = valorHexadecimal;
    }

    public String getNombreInstruccion() {
        return this.nombreInstruccion;
    }

    public void setNombreInstruccion(String nombreInstruccion) {
        this.nombreInstruccion = nombreInstruccion;
    }

    public String getValorHexadecimal() {
        return this.valorHexadecimal;
    }

    public void setValorHexadecimal(String valorHexadecimal) {
        this.valorHexadecimal = valorHexadecimal;
    }

    @Override
    public String toString() {
        return "InstruccionesEnum{" + "nombreInstruccion=" + nombreInstruccion + ", valorHexadecimal=" + valorHexadecimal + '}';
    }
    
    
}
