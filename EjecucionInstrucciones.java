package com.mycompany.estructura;

import static com.mycompany.estructura.InstruccionesEnum.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EjecucionInstrucciones {
    
    //public static int numeroDeBits=8;// 2 bytes
    
    
    
    /* TAMAÑO DE LAS DIRECCIONES DE MEMORIA */
    public static int numeroDeBitsDireccionMemoria=8;// 1 bytes 
    //public static int numeroDeBitsDireccionMemoria=16;// 2 bytes
    //public static int numeroDeBitsDireccionMemoria=32;// 4 bytes
    //public static int numeroDeBitsDireccionMemoria=64;// 8 bytes
    
    /* TAMAÑO DEL VALOR DE CADA DIRECCIÓN DE MEMORIA */
    //public static int numeroDeBitsContenidoMemoria=1;// 1 bytes
    //public static int numeroDeBitsContenidoMemoria=2;// 2 bytes
    //public static int numeroDeBitsContenidoMemoria=4;// 4 bytes
    public static int numeroDeBitsContenidoMemoria=8;// 8 bytes
    
    
    /* TAMAÑO DEL VALOR DE CADA REGSITRO */
    //public static int numeroDeBitsRegisto=1;// 1 bytes
    public static int numeroDeBitsRegisto=8;// 2 bytes
    //public static int numeroDeBitsRegisto=4;// 4 bytes
    //public static int numeroDeBitsRegisto=8;// 8 bytes
    
    /* MODOS DE ALMACENAMIENTO */
    public static String modoSistema="little-endian";  // ELEGIR UNO DE LOS DOS MODOS DE SISTEMAS DE ALMACENAMIENTO.
    //public static String modoSistema="big-endian";    // ELEGIR UNO DE LOS DOS MODOS DE SISTEMAS DE ALMACENAMIENTO.
    
    public static int BitCero=0;
    public static int BitTrasnporte=0;
    public static int BitDesbordamiento=0;
    public static int BitSigno=0;
    public static int BitInterrupciones=0;
    public static String nombreInstruccionAEjecutar;
    public static String LineaSegundaAImprimir="";
    public static String LineaTerceraAImprimir="";
    public static int  numPalabra= 1;// 1 bytes
    public static List <Instruccion> instruccionesAEjecutar=new ArrayList();
    public static List <Memoria> direccionesMemoria=new ArrayList();
    public static List <Memoria> valoresMemoria=new ArrayList();
    public static List <Registro> registros=new ArrayList();
    public static List <Registro> valoresRegistro=new ArrayList();
    public static String compararFuente="";
    public static String compararDestino="";
    
    public static String inicioMemoria ="[0AB0-0100h]"; // POSICION INICIAL DE MEMORIA
    public static String finalMemoria  ="[0AB0-0300h]" ; // POSICION FINAL DE MEMORIA
    
    
    public static void main(String[] args) {
        
//        inicioMemoria ="[0000-2020h]"; // POSICION INICIAL DE MEMORIA
//        finalMemoria  ="[0000-FFFFh]" ; // POSICION FINAL DE MEMORIA
//        
        
        
        valoresRegistro.add(new Registro("R0","00000AAAh",""));  // VALORES INCIAL EN REGISTRO R0
        valoresRegistro.add(new Registro("R1","00000BBBh",""));  // VALORES INCIAL EN REGISTRO R1
        valoresRegistro.add(new Registro("R2","00000CCCh",""));  // VALORES INCIAL EN REGISTRO R2
        valoresRegistro.add(new Registro("R3","0000-0003h",""));  // VALORES INCIAL EN REGISTRO R2
        valoresRegistro.add(new Registro("R4","0000-0020h",""));  // VALORES INCIAL EN REGISTRO R2
        valoresRegistro.add(new Registro("R5","0000-000Ah",""));  // VALORES INCIAL EN REGISTRO R2
        valoresRegistro.add(new Registro("R6","0000-000Ch",""));  // VALORES INCIAL EN REGISTRO R2
        valoresRegistro.add(new Registro("R7","0000-000Eh",""));  // VALORES INCIAL EN REGISTRO R2
        valoresRegistro.add(new Registro("R8","0000-0030h",""));  // VALORES INCIAL EN REGISTRO R2
        valoresRegistro.add(new Registro("R9","0000-0024h",""));  // VALORES INCIAL EN REGISTRO R2
        valoresRegistro.add(new Registro("R10","0000-0050h",""));  // VALORES INCIAL EN REGISTRO R2
        valoresRegistro.add(new Registro("R11","0000-002Ch",""));  // VALORES INCIAL EN REGISTRO R2
        valoresRegistro.add(new Registro("R12","0000-0030h",""));  // VALORES INCIAL EN REGISTRO R2
        valoresRegistro.add(new Registro("R13","0000-0034h",""));  // VALORES INCIAL EN REGISTRO R2
        valoresRegistro.add(new Registro("R14","0000-0038h",""));  // VALORES INCIAL EN REGISTRO R2
        valoresRegistro.add(new Registro("R15","0000-0000h",""));  // VALORES INCIAL EN REGISTRO R2
        valoresRegistro.add(new Registro("PC","0000-0000h",""));  // VALORES INCIAL EN REGISTRO PC
        
       
//        
//        
        // EJEMPLO
        // Pregunta 1)
        
        valoresMemoria.add(new Memoria("[00000AAAh]","0000F00Fh","")); // VALORES INCIAL EN POSICIÓN Y VALOR
        valoresMemoria.add(new Memoria("[00000BBBh]","0000F000h","")); // VALORES INCIAL EN POSICIÓN Y VALOR
        valoresMemoria.add(new Memoria("[00000CCCh]","00000FF0h","")); // VALORES INCIAL EN POSICIÓN Y VALOR
        valoresMemoria.add(new Memoria("[00000DDDh]","00000001h","")); // VALORES INCIAL EN POSICIÓN Y VALOR
        valoresMemoria.add(new Memoria("[00000EEEh]","F000000Fh","")); // VALORES INCIAL EN POSICIÓN Y VALOR
        
          
        String A="[0000-0400h]";
        String B="[0000-0800h]";
        
        BitCero=0;
        BitTrasnporte=0;
        BitDesbordamiento=0;
        BitSigno=0;
        
        instruccionesAEjecutar.add(new Instruccion(ADD,"R0","R1"));
        instruccionesAEjecutar.add(new Instruccion(SUB,"R0","[R1]"));
        
        
        //imprimirRegistros();
        initValores();
        ejecutarInstrucciones();
            imprimirRegistros();
//        imprimirMemoria("[0000-0200H]","[0000-0200H]");
////        imprimirMemoria("[0000-0020H]","[0000-0020H]");
         
    }
    public static void ejecutarInstrucciones(){
        
        int contadorInstrucciones=0;
        while (contadorInstrucciones<instruccionesAEjecutar.size()){
            Instruccion instrucciones = instruccionesAEjecutar.get(contadorInstrucciones);
            switch (instrucciones.getInstruccion().getNombreInstruccion()) {
                case "MOV": //0
                    nombreInstruccionAEjecutar="MOV "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"")+ " ,"+(instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"",0);
                    contadorInstrucciones++;
                    break;
                case "PUSH":
                    contadorInstrucciones++;
                    break;
                case "POP":
                    contadorInstrucciones++;
                    break;
                case "ADD"://1
                    nombreInstruccionAEjecutar="ADD "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"")+ " ,"+(instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"",1);
                    contadorInstrucciones++;
                    break;
                case "SUB"://2
                    nombreInstruccionAEjecutar="SUB "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"")+ " ,"+(instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"",2);
                    contadorInstrucciones++;
                    break;
                case "MUL"://11
                    nombreInstruccionAEjecutar="MUL "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"")+ " ,"+(instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"",11);
                    contadorInstrucciones++;
                    break;
                case "DIV"://12
                    contadorInstrucciones++;
                    break;
                case "INC"://1
                    nombreInstruccionAEjecutar="INC "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"","1",1);
                    contadorInstrucciones++;
                    break;
                case "DEC"://2
                    nombreInstruccionAEjecutar="DEC "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"","1",2);
                    contadorInstrucciones++;
                    break;
                case "CMP"://10
                    nombreInstruccionAEjecutar="CMP "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"")+ " ,"+(instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"",10);
                    contadorInstrucciones++;
                    break;
                case "AND"://4
                    nombreInstruccionAEjecutar="AND "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"")+ " ,"+(instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"",4);
                    contadorInstrucciones++;
                    break;
                case "OR"://5
                    nombreInstruccionAEjecutar="OR "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"")+ " ,"+(instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"",5);
                    contadorInstrucciones++;
                    break; 
                case "XOR"://6
                    nombreInstruccionAEjecutar="XOR "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"")+ " ,"+(instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"",6);
                    contadorInstrucciones++;
                    break; 
                case "NOT"://7
                    nombreInstruccionAEjecutar="NOT "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",7);
                    contadorInstrucciones++;
                    break; 
                case "SAL":
                    nombreInstruccionAEjecutar="SAL "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"")+ " ,"+(instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"",8);
                    contadorInstrucciones++;
                    break;
                case "SAR":
                    nombreInstruccionAEjecutar="SAR "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"")+ " ,"+(instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"",9);
                    contadorInstrucciones++;
                    break;
                case "ROL":
                    nombreInstruccionAEjecutar="ROL "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"")+ " ,"+(instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"",16);
                    contadorInstrucciones++;
                    break;
                case "ROR":
                    nombreInstruccionAEjecutar="ROL "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"")+ " ,"+(instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"",17);
                    contadorInstrucciones++;
                    break;    
                case "JMP":
                    System.out.println("****************************************");
                    System.out.println("JMP , "+instrucciones.getPrimerOperando());
                    System.out.println("****************************************");
                    boolean found=false;
                    int contador=0;
                    while (contador <instruccionesAEjecutar.size() && !found){
                         if (instruccionesAEjecutar.get(contador).getEtiqueta()!=null && instruccionesAEjecutar.get(contador).getEtiqueta().equalsIgnoreCase(instrucciones.getPrimerOperando().trim())){
                            found=true;
                        }else {
                            contador++;
                         }    
                    }
                    if (found){
                        contadorInstrucciones=contador;
                    }else{
                        contadorInstrucciones++;
                    }    
                    break;
                case "JE":
                    System.out.println("*************************************************");
                    System.out.println("JE (=) , "+instrucciones.getPrimerOperando());
                    System.out.println("*************************************************");
                    if (BitCero==1 ){
                        System.out.println("CIERTO ==> BIT CERO(Z) = 1 ");
                        System.out.println("*************************************************");
                        boolean buscar=false;
                        int contadores=0;
                        while (contadores <instruccionesAEjecutar.size() && !buscar){
                            if (instruccionesAEjecutar.get(contadores).getEtiqueta()!=null && instruccionesAEjecutar.get(contadores).getEtiqueta().equalsIgnoreCase(instrucciones.getPrimerOperando().trim())){
                                buscar=true;
                            }else {
                                contadores++;
                            }    
                        }
                        if (buscar){
                            contadorInstrucciones=contadores;
                        }else{
                            contadorInstrucciones++;
                        }  
                    }else {
                        System.out.println("FALSO ==> BIT CERO(Z) = 0");
                        System.out.println("*************************************************");
                        contadorInstrucciones++;
                    }
                    break;
                case "JNE":
                    System.out.println("*************************************************");
                    System.out.println("JNE (!=) , "+instrucciones.getPrimerOperando());
                    System.out.println("*************************************************");
                    if (BitCero==0 ){
                        System.out.println("CIERTO ==> BIT CERO(Z) = 0 ");
                        System.out.println("*************************************************");
                        boolean buscar=false;
                        int contadores=0;
                        while (contadores <instruccionesAEjecutar.size() && !buscar){
                            if (instruccionesAEjecutar.get(contadores).getEtiqueta()!=null && instruccionesAEjecutar.get(contadores).getEtiqueta().equalsIgnoreCase(instrucciones.getPrimerOperando().trim())){
                                buscar=true;
                            }else {
                                contadores++;
                            }    
                        }
                        if (buscar){
                            contadorInstrucciones=contadores;
                        }else{
                            contadorInstrucciones++;
                        }  
                    }else {
                        System.out.println("FALSO ==> BIT CERO (Z) = 1 ");
                        System.out.println("*************************************************");
                        contadorInstrucciones++;
                    }
                    break;
                case "JG":
                   System.out.println("*************************************************");
                    System.out.println("JG (>), "+instrucciones.getPrimerOperando());
                    System.out.println("*************************************************");
                    if (BitCero==0 && BitSigno==0){
                        System.out.println("CIERTO ==> BIT CERO (Z) = 0 Y BIT SIGNO = 0");
                        System.out.println("*************************************************");
                        boolean buscar=false;
                        int contadores=0;
                        while (contadores <instruccionesAEjecutar.size() && !buscar){
                            if (instruccionesAEjecutar.get(contadores).getEtiqueta()!=null && instruccionesAEjecutar.get(contadores).getEtiqueta().equalsIgnoreCase(instrucciones.getPrimerOperando().trim())){
                                buscar=true;
                            }else {
                                contadores++;
                            }    
                        }
                       
                        if (buscar){
                            contadorInstrucciones=contadores;
                        }else{
                            contadorInstrucciones++;
                        }  
                    }else {
                        System.out.println("FALSO ==> BIT CERO (Z) != 0 o  = BIT SIGNO != 0");
                        System.out.println("*************************************************");
                        contadorInstrucciones++;
                    }
                    break;        
                 case "JGE":
                    System.out.println("*************************************************");
                    System.out.println("JGE (>=), "+instrucciones.getPrimerOperando());
                    System.out.println("*************************************************");
                    if (BitCero==1 && BitSigno==0){
                        System.out.println("CIERTO ==> BIT CERO (Z) = 0 o BIT SIGNO = 0");
                        System.out.println("*************************************************");
                        boolean buscar=false;
                        int contadores=0;
                        while (contadores <instruccionesAEjecutar.size() && !buscar){
                            if (instruccionesAEjecutar.get(contadores).getEtiqueta()!=null && instruccionesAEjecutar.get(contadores).getEtiqueta().equalsIgnoreCase(instrucciones.getPrimerOperando().trim())){
                                buscar=true;
                            }else {
                                contadores++;
                            }    
                        }
                       
                        if (buscar){
                            contadorInstrucciones=contadores;
                        }else{
                            contadorInstrucciones++;
                        }  
                    }else {
                        System.out.println("FALSO ==> BIT CERO (Z) != 0 y BIT SIGNO != 0");
                        System.out.println("*************************************************");
                        contadorInstrucciones++;
                    }
                    break;   
                    
                case "JL":
                    System.out.println("*************************************************");
                    System.out.println("JL(<)  , "+instrucciones.getPrimerOperando());
                    System.out.println("*************************************************");
                    if (BitSigno==1 && BitSigno==0){
                        System.out.println("CIERTO ==> BIT CERO (Z) = 0 y BIT SIGNO = 1");
                        System.out.println("*************************************************");
                        boolean buscar=false;
                        int contadores=0;
                        while (contadores <instruccionesAEjecutar.size() && !buscar){
                            if (instruccionesAEjecutar.get(contadores).getEtiqueta()!=null && instruccionesAEjecutar.get(contadores).getEtiqueta().equalsIgnoreCase(instrucciones.getPrimerOperando().trim())){
                                buscar=true;
                            }else {
                                contadores++;
                            }    
                        }
                       
                        if (buscar){
                            contadorInstrucciones=contadores;
                        }else{
                            contadorInstrucciones++;
                        }  
                    }else {
                        System.out.println("FALSO ==> BIT CERO (Z) != 0 y BIT SIGNO != 1 ");
                        System.out.println("*************************************************");
                        contadorInstrucciones++;
                    }
                    break; 
                case "JLE":
                    System.out.println("*************************************************");
                    System.out.println("JLE (<=), "+instrucciones.getPrimerOperando());
                    System.out.println("*************************************************");
                    if (BitCero==1 || BitSigno==1){
                        System.out.println("CIERTO ==> BIT CERO (Z) = 1 o BIT SIGNO = 1");
                        System.out.println("*************************************************");
                        boolean buscar=false;
                        int contadores=0;
                        while (contadores <instruccionesAEjecutar.size() && !buscar){
                            if (instruccionesAEjecutar.get(contadores).getEtiqueta()!=null && instruccionesAEjecutar.get(contadores).getEtiqueta().equalsIgnoreCase(instrucciones.getPrimerOperando().trim())){
                                buscar=true;
                            }else {
                                contadores++;
                            }    
                        }
                       
                        if (buscar){
                            contadorInstrucciones=contadores;
                        }else{
                            contadorInstrucciones++;
                        }  
                    }else {
                        System.out.println("FALSO ==> BIT CERO (Z) = 1 o BIT SIGNO = 1");
                        System.out.println("*************************************************");
                        contadorInstrucciones++;
                    }
                    break;   
                case "TEST"://15
                    nombreInstruccionAEjecutar="TEST "+(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"")+ " ,"+(instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"");
                    move(instrucciones.getPrimerOperando()!=null?instrucciones.getPrimerOperando():"",instrucciones.getSegundoOperando()!=null?instrucciones.getSegundoOperando():"",15);
                    contadorInstrucciones++;
                    break;
                default:
                    contadorInstrucciones++;
                    break;
            }   
        }
    }
    
    public static void initValores(){
        
        long memoriaDesde=hexadecimalADecimalLong(inicioMemoria);
        long memoriaHasta=hexadecimalADecimalLong(finalMemoria);
        if (memoriaDesde>memoriaHasta){
            long help=memoriaHasta;
            memoriaHasta=memoriaDesde;
            memoriaDesde=help;
        }
        String valorCeroHex="";
        for (int j = 0; j < numeroDeBitsContenidoMemoria; j++) {
            valorCeroHex="0"+valorCeroHex;
        } 
        for (int i = (int)memoriaDesde; i <(int) memoriaHasta; i++) {
            String numeroenHex=decimalAHexadecimalString(i,numeroDeBitsContenidoMemoria);
            if(numeroenHex.length()<numeroDeBitsDireccionMemoria){
                for (int j = numeroenHex.length(); j < numeroDeBitsDireccionMemoria; j++) {
                    numeroenHex="0"+numeroenHex;
                }                
            } else{
                numeroenHex=numeroenHex.substring(numeroenHex.length()-numeroDeBitsDireccionMemoria, numeroenHex.length());
            }
            Memoria memoria = new Memoria(numeroenHex,valorCeroHex,valorCeroHex);
            direccionesMemoria.add(memoria);
        }
        
        for (Memoria memorias:valoresMemoria){
            memorias.setDireccionEnHexadecimal(memorias.getDireccionEnHexadecimal().replace("-", "").replace("H", "").replace("h", "").replace(" ", ""));
            memorias.setValorAnteriorHexadecimal(memorias.getValorAnteriorHexadecimal().replace("-", "").replace("H", "").replace("h", "").replace(" ", ""));
            
            Memoria memory=direccionesMemoria.stream().filter(p->p.getDireccionEnDecimal()==memorias.getDireccionEnDecimal()).findFirst().orElse(null);
            if (memory!=null){
                memory.setValorAnteriorDecimal(hexadecimalADecimalLong(memorias.getValorAnteriorHexadecimal()));
                memory.setValorAnteriorHexadecimal(memorias.getValorAnteriorHexadecimal());
            }else{
                Memoria memoriaNew = new Memoria();

                memoriaNew.setDireccionEnHexadecimal(memorias.getDireccionEnHexadecimal());
                memoriaNew.setDireccionEnDecimal(hexadecimalADecimalLong(memoriaNew.getDireccionEnHexadecimal()));
                memoriaNew.setValorAnteriorHexadecimal(memorias.getValorAnteriorHexadecimal());
                memoriaNew.setValorAnteriorDecimal(hexadecimalADecimalLong(memorias.getValorAnteriorHexadecimal()));
                memoriaNew.setValorPosteriorHexadecimal(valorCeroHex);
                memoriaNew.setValorPosteriorDecimal(0);
                direccionesMemoria.add(memoriaNew);
            }
        }
        valorCeroHex="";
        for (int j = 0; j < numeroDeBitsRegisto; j++) {
            valorCeroHex="0"+valorCeroHex;
        } 
        for (int i = 0; i < 16; i++) {
            Registro registro= new Registro("R"+i,valorCeroHex,valorCeroHex);
            registros.add(registro);
        }
        Registro registro= new Registro("PC",valorCeroHex,valorCeroHex);
        registros.add(registro);
        for (Registro registers:valoresRegistro){
            Registro register=registros.stream().filter(p->p.getNombreRegistro().trim().equalsIgnoreCase(registers.getNombreRegistro().trim())).findFirst().orElse(null);
            if (register!=null){
                if (registers.getValorAnteriorHexadecimal().length()<numeroDeBitsRegisto){
                    for (int i = registers.getValorAnteriorHexadecimal().length(); i < numeroDeBitsRegisto; i++) {
                        registers.setValorAnteriorHexadecimal("0"+registers.getValorAnteriorHexadecimal());
                    }
                    
                }
                register.setValorAnteriorDecimal(registers.getValorAnteriorDecimal());
                register.setValorAnteriorHexadecimal(registers.getValorAnteriorHexadecimal());
            }
        }
       
    }
    
    public static void imprimirMemoria(String desde, String hasta){
       
      
        long memoriaDesde=hexadecimalADecimalLong(desde);
        long memoriaHasta=hexadecimalADecimalLong(hasta);
        if (memoriaDesde > memoriaHasta){
            long help=memoriaDesde;
            memoriaDesde=memoriaHasta;
            memoriaHasta=help;
        }
                             
        System.out.println("╔═══════════════════════════════════════════╤═══════════════════════════════════════════╤═══════════════════════════════════════════╗");
        System.out.println("║ MEMORIA                                   │ Antes                                     │ Después                                   ║");        
        System.out.println("╠═══════════════════════════════════════════╪═══════════════════════════════════════════╪═══════════════════════════════════════════╣");
        for (int i = (int)memoriaDesde; i <= (int)memoriaHasta; i++) {
            String valor=decimalAHexadecimalString(i,numeroDeBitsDireccionMemoria);
            Memoria memory = direccionesMemoria.stream().filter(p->p.getDireccionEnHexadecimal().equals(valor)).findFirst().orElse(null);
            if (memory!=null){
                System.out.println("║ "+(formatoHexadeximal(memory.getDireccionEnHexadecimal())+"h                                               ").substring(0, 42)+"│ "
                        +(formatoHexadeximal(memory.getValorAnteriorHexadecimal())+"h                                               ").substring(0, 42)+"│ "
                        +(formatoHexadeximal(memory.getValorPosteriorHexadecimal())+"h                                               ").substring(0, 42)+"║");
//                 System.out.println("║ "+(hexadecimalADecimalLong(memory.getDireccionEnHexadecimal())+"                                               ").substring(0, 42)+"│ "
//                        +(memory.getValorAnteriorDecimal()+"                                               ").substring(0, 42)+"│ "
//                        +(memory.getValorPosteriorDecimal()+"                                               ").substring(0, 42)+"║");
            }   
        }
        System.out.println("╚═══════════════════════════════════════════╧═══════════════════════════════════════════╧═══════════════════════════════════════════╝");
    }
    
//    public static void imprimirCambiosRegistros(Registro registro, String retorno){
//        System.out.println(retorno+ " ==> " +nombreInstruccionAEjecutar);
//        System.out.println(LineaSegundaAImprimir);
//        System.out.println(LineaTerceraAImprimir);
//        System.out.println("╔════════════╤═══════════════════════════════════════════╤═══════════════════════════════════════════╤═══╤═══╤═══════╗");
//        System.out.println("║ Resgistros │ Antes                                     │ Después                                   │ Z │ C │ V │ S ║");        
//        System.out.println("╠════════════╪═══════════════════════════════════════════╪═══════════════════════════════════════════╪═══╪═══╪═══╪═══╣"); 
//        System.out.println("║ "+(registro.getNombreRegistro()+"          ").substring(0, 11)+"│ "
//                        +(formatoHexadeximal(registro.getValorAnteriorHexadecimal())+"h                                               ").substring(0, 42)+"│ "
//                        +(formatoHexadeximal(registro.getValorPosteriorHexadecimal())+"h                                               ").substring(0, 42)+"│ "
//                        +(BitCero+ "  ").substring(0, 1)+ " │ "
//                        +(BitTrasnporte+ "  ").substring(0, 1)+ " │ "
//                        +(BitDesbordamiento+ "  ").substring(0, 1)+ " │ "
//                        +(BitSigno+ "  ").substring(0, 1)+ " ║");
//        System.out.println("║ "+(registro.getNombreRegistro()+"          ").substring(0, 11)+"│ "
//                        +(registro.getValorAnteriorDecimal()+"                                               ").substring(0, 42)+"│ "
//                        +(registro.getValorPosteriorDecimal()+"                                               ").substring(0, 42)+"│   │   │   │   ║");
//        System.out.println("╚════════════╧═══════════════════════════════════════════╧═══════════════════════════════════════════╧═══╧═══╧═══╧═══╝");
//        registro.setValorAnteriorDecimal(registro.getValorPosteriorDecimal());
//        registro.setValorAnteriorHexadecimal(registro.getValorPosteriorHexadecimal());
//        registro.setValorPosteriorDecimal(0);
//        String valorCeroHex="";
//        for (int j = 0; j < numeroDeBitsRegisto; j++) {
//            valorCeroHex="0"+valorCeroHex;
//        } 
//        registro.setValorPosteriorHexadecimal(valorCeroHex);
//    }
    public static void imprimirCambiosRegistros(Registro registro, String retorno){
        System.out.println(retorno+ " ==> " +nombreInstruccionAEjecutar);
        System.out.println("");
        System.out.println(LineaSegundaAImprimir);
        System.out.println(LineaTerceraAImprimir);
        System.out.println("");
        System.out.println(registro.getNombreRegistro()+" = "+ formatoHexadeximal(registro.getValorPosteriorHexadecimal())+"h");
        System.out.println(registro.getNombreRegistro()+" = "+ registro.getValorPosteriorDecimal()+" d ");
        System.out.println("");
        System.out.println("Z = "+BitCero+"  ,  S = "+BitSigno+"  ,  C = "+BitTrasnporte+"  ,  V = "+BitDesbordamiento);
        System.out.println("");
        registro.setValorAnteriorDecimal(registro.getValorPosteriorDecimal());
        registro.setValorAnteriorHexadecimal(registro.getValorPosteriorHexadecimal());
        registro.setValorPosteriorDecimal(0);
        String valorCeroHex="";
        for (int j = 0; j < numeroDeBitsRegisto; j++) {
            valorCeroHex="0"+valorCeroHex;
        } 
        registro.setValorPosteriorHexadecimal(valorCeroHex);
    }
    
    public static void imprimirCambiosMemoria(String memoryDestino, String retorno){
        
        long memoriaDesde= hexadecimalADecimalLong(memoryDestino);
        long memoriaHasta= hexadecimalADecimalLong(memoryDestino)+numPalabra;
        String valorCeroHex="";
        for (int j = 0; j < numeroDeBitsContenidoMemoria; j++) {
            valorCeroHex="0"+valorCeroHex;
        }
        System.out.println(retorno+ " ==> " +nombreInstruccionAEjecutar);
        System.out.println("");
        System.out.println(LineaSegundaAImprimir);
        System.out.println(LineaTerceraAImprimir);
        System.out.println("");
        for (int i = (int)memoriaDesde; i < (int)(memoriaHasta+1); i++) {
            String valor=decimalAHexadecimalString(i,numeroDeBitsDireccionMemoria);
            Memoria memory = direccionesMemoria.stream().filter(p->p.getDireccionEnHexadecimal().equals(valor)).findFirst().orElse(null);
            if (memory!=null){
                System.out.println("[ "+formatoHexadeximal(memory.getDireccionEnHexadecimal())+"h ] = "+formatoHexadeximal(memory.getValorPosteriorHexadecimal())+" h ");
                System.out.println("[ "+formatoHexadeximal(memory.getDireccionEnHexadecimal())+"h ] = "+memory.getValorPosteriorDecimal()+" d ");   
                memory.setValorAnteriorHexadecimal(memory.getValorPosteriorHexadecimal());
                memory.setValorAnteriorDecimal(hexadecimalADecimalLong(memory.getValorPosteriorHexadecimal()));
                memory.setValorPosteriorDecimal(0);
                memory.setValorPosteriorHexadecimal(valorCeroHex);
            }
        }
        System.out.println("");
        System.out.println("Z = "+BitCero+"  ,  S = "+BitSigno+"  ,  C = "+BitTrasnporte+"  ,  V = "+BitDesbordamiento);
        System.out.println("");
        
    }
    
    
//    public static void imprimirCambiosMemoria(String memoryDestino, String retorno){
//        
//        long memoriaDesde= hexadecimalADecimalLong(memoryDestino);
//        long memoriaHasta= hexadecimalADecimalLong(memoryDestino)+numPalabra;
//        String valorCeroHex="";
//        for (int j = 0; j < numeroDeBitsContenidoMemoria; j++) {
//            valorCeroHex="0"+valorCeroHex;
//        }
//        System.out.println(retorno+ " ==> " +nombreInstruccionAEjecutar);
//        System.out.println(LineaSegundaAImprimir);
//        System.out.println(LineaTerceraAImprimir);
//        
//        System.out.println("╔═══════════════════════════════════════════╤═══════════════════════════════════════════╤═══════════════════════════════════════════╤═══╤═══╤═══════╗");
//        System.out.println("║ MEMORIA                                   │ Antes                                     │ Después                                   │ Z │ C │ V │ S ║");         
//        System.out.println("╠═══════════════════════════════════════════╪═══════════════════════════════════════════╪═══════════════════════════════════════════╪═══╪═══╪═══╪═══╣"); 
//        for (int i = (int)memoriaDesde; i < (int)(memoriaHasta+1); i++) {
//            String valor=decimalAHexadecimalString(i,numeroDeBitsDireccionMemoria);
//            Memoria memory = direccionesMemoria.stream().filter(p->p.getDireccionEnHexadecimal().equals(valor)).findFirst().orElse(null);
//            if (memory!=null){
//                System.out.println("║ "+(formatoHexadeximal(memory.getDireccionEnHexadecimal())+"h                                               ").substring(0, 42)+"│ "
//                    +(formatoHexadeximal(memory.getValorAnteriorHexadecimal())+"h                                               ").substring(0, 42)+"│ "
//                    +(formatoHexadeximal(memory.getValorPosteriorHexadecimal())+"h                                               ").substring(0, 42)+"│ "
//                    +(BitCero+ "  ").substring(0, 1)+ " │ "
//                    +(BitTrasnporte+ "  ").substring(0, 1)+ " │ "
//                    +(BitDesbordamiento+ "  ").substring(0, 1)+ " │ "
//                    +(BitSigno+ "  ").substring(0, 1)+ " ║");
//                System.out.println("║ "+(hexadecimalADecimalLong(memory.getDireccionEnHexadecimal())+"                                               ").substring(0, 42)+"│ "
//                    +(memory.getValorAnteriorDecimal()+"                                               ").substring(0, 42)+"│ "
//                    +(memory.getValorPosteriorDecimal()+"                                               ").substring(0, 42)+"│   │   │   │   ║");
//                memory.setValorAnteriorHexadecimal(memory.getValorPosteriorHexadecimal());
//                memory.setValorAnteriorDecimal(hexadecimalADecimalLong(memory.getValorPosteriorHexadecimal()));
//                memory.setValorPosteriorDecimal(0);
//                memory.setValorPosteriorHexadecimal(valorCeroHex);
//            }
//        }
//        System.out.println("╚═══════════════════════════════════════════╧═══════════════════════════════════════════╧═══════════════════════════════════════════╧═══╧═══╧═══╧═══╝");
//            
//    }
    
    public static void imprimirRegistros(){
         
        System.out.println("╔════════════╤═══════════════════════════════════════════╤═══════════════════════════════════════════╗");
        System.out.println("║ Resgistros │ Antes                                     │ Después                                   ║");        
        System.out.println("╠════════════╪═══════════════════════════════════════════╪═══════════════════════════════════════════╣");
        for (Registro register: registros) {
             System.out.println("║ "+(register.getNombreRegistro()+"          ").substring(0, 11)+"│ "
                        +(formatoHexadeximal(register.getValorAnteriorHexadecimal())+"h                                               ").substring(0, 42)+"│ "
                        +(formatoHexadeximal(register.getValorPosteriorHexadecimal())+"h                                               ").substring(0, 42)+"║");
//             System.out.println("║ "+(register.getNombreRegistro()+"          ").substring(0, 11)+"│ "
//                        +(register.getValorAnteriorDecimal()+"                                               ").substring(0, 42)+"│ "
//                        +(register.getValorPosteriorDecimal()+"                                               ").substring(0, 42)+"║");
         }
        System.out.println("╚════════════╧═══════════════════════════════════════════╧═══════════════════════════════════════════╝");
    }
    public static void imprimirBitsResultado(String retorno){
        
        System.out.println(retorno+ " ==> " +nombreInstruccionAEjecutar);
        System.out.println(LineaSegundaAImprimir);
        System.out.println(LineaTerceraAImprimir);
        
        
        System.out.println("╔═══════════════════════════╤════════╗");
        System.out.println("║ Bits de Resultado         │ Actual ║");        
        System.out.println("╠═══════════════════════════╪════════╣");
        System.out.println("║ Bit de Cero (Z)           │ "+BitCero+"      ║");
        System.out.println("║ Bit de Transporte (C)     │ "+BitTrasnporte+"      ║");
        System.out.println("║ Bit de Desbordamiento (V) │ "+BitDesbordamiento+"      ║");
        System.out.println("║ Bit de Signo (S)          │ "+BitSigno+"      ║");
        System.out.println("║ Bit de Interrrupción (IE) │ "+BitInterrupciones+"      ║");
        System.out.println("╚═══════════════════════════╧════════╝");
        System.out.println("");
        System.out.println("Z = "+BitCero+"  ,  S = "+BitSigno+"  ,  C = "+BitTrasnporte+"  ,  V = "+BitDesbordamiento);
        System.out.println("");
    }
    
    public static void actualizaRegistros(){
        
        String valorCeroHex="";
        for (int j = 0; j < numeroDeBitsContenidoMemoria; j++) {
            valorCeroHex="0"+valorCeroHex;
        } 
        for (Memoria memorias:direccionesMemoria){
            memorias.setValorAnteriorDecimal(memorias.getValorPosteriorDecimal());
            memorias.setValorAnteriorHexadecimal(memorias.getValorPosteriorHexadecimal());
            memorias.setValorPosteriorHexadecimal(valorCeroHex);
            memorias.setValorPosteriorDecimal(0);
        }
        valorCeroHex="";
        for (int j = 0; j < numeroDeBitsRegisto; j++) {
            valorCeroHex="0"+valorCeroHex;
        } 
       for (Registro registers:registros){
            if (registers.getValorPosteriorDecimal()!=0){
                registers.setValorAnteriorDecimal(registers.getValorPosteriorDecimal());
                registers.setValorAnteriorHexadecimal(registers.getValorPosteriorHexadecimal());
                registers.setValorPosteriorHexadecimal(valorCeroHex);
                registers.setValorPosteriorDecimal(0);
            }    
        }
    }
    
    public static  String move(String destine, String origin, int tipoOpereacion ) {
       
        //tipoOpereacion==  0 ==> MOVE
        //tipoOpereacion==  1 ==> ADD
        //tipoOpereacion==  1 ==> INC
        //tipoOpereacion==  2 ==> SUB
        //tipoOpereacion==  2 ==> DEC
        //tipoOpereacion== 11 ==> MUL
        //tipoOpereacion== 12 ==> DIV
        //tipoOpereacion== 10 ==> CMP
        //tipoOpereacion==  4 ==> AND
        //tipoOpereacion==  5 ==> OR
        //tipoOpereacion==  6 ==> XOR
        //tipoOpereacion==  7 ==> NOT
        //tipoOpereacion==  8 ==> SAL
        //tipoOpereacion==  9 ==> SAR
        //tipoOpereacion== 15 ==> TEST
        //tipoOpereacion== 16 ==> ROL
        //tipoOpereacion== 17 ==> ROr
        
        if ( tipoOpereacion==1 || tipoOpereacion==2 || tipoOpereacion==11 || tipoOpereacion==12  ){
            BitCero=0;
            if  (tipoOpereacion!=11){
                BitTrasnporte=0;
            }
            BitDesbordamiento=0;
            BitSigno=0;
        }
        LineaSegundaAImprimir="";
        LineaTerceraAImprimir="";
        compararFuente="";
        compararDestino="";
        String destino=destine.replaceAll(" ","").replaceAll("/","").replaceAll("O","0");
        String fuente=origin.replaceAll(" ","").replaceAll("/","").replaceAll("O","0");
        int tipo =1;
        String retorno="";
        String memoriaDestino="";
        String registerDestino="";
        String valorDestino="";
        String encontrar="";
        String desplazamiento="";
       
        String palabra="";
                
        if((destino.contains("R") || destino.contains("r")) && !destino.contains("[") && !destino.contains("+")){
            tipo=1;
            registerDestino=destino;
        } else {
            tipo=2;
            if (destino.contains("[") && !destino.contains("+")){
                destino=destino.replace("[", "").replace("]", "");
                if (destino.contains("R") || destino.contains("r")){
                    String encontrarDestino=destino;
                    Registro encontrarValor=registros.stream().filter(p->p.getNombreRegistro().trim().equalsIgnoreCase(encontrarDestino)).findFirst().orElse(null);
                    if (encontrarValor!=null){
                        memoriaDestino=encontrarValor.getValorAnteriorHexadecimal();
                    }
                }else{
                    if (destino.contains("H") || destino.contains("h")){
                        destino=destino.replace("h", "").replace("H", "").replace("-", "");
                    } else {
                        destino=destino.replace("h", "").replace("H", "");
                        destino=decimalAHexadecimalString(Integer.valueOf(destino),numeroDeBitsRegisto);
                    }
                    memoriaDestino=destino;
                }
            }
            if (destino.contains("[") && destino.contains("+")){
                tipo=3;
                destino=destino.replace("[", "").replace("]", "");
                if (destino.substring(0, 1).equalsIgnoreCase("R")){
                    retorno="DIRECCIONAMIENTO RELATIVO A REGISTRO BASE "+modoSistema.toUpperCase();
                    encontrar=destino.substring(0, destino.indexOf("+"));
                    desplazamiento=destino.substring(destino.indexOf("+")+1);
                } else {
                    if (destino.substring(0, 1).equalsIgnoreCase("P")){
                        retorno="DIRECCIONAMIENTO RELATIVO A PC "+modoSistema.toUpperCase();
                        encontrar=destino.substring(0, destino.indexOf("+"));
                        desplazamiento=destino.substring(destino.indexOf("+")+1);
                    } else{
                        retorno="DIRECCIONAMIENTO RELATIVO A REGISTRO ÍNDICE "+modoSistema.toUpperCase();
                        desplazamiento=destino.substring(0, destino.indexOf("+"));
                        encontrar=destino.substring(destino.indexOf("+")+1);
                    }
                }    
                if (desplazamiento.contains("H") || desplazamiento.contains("h")){
                    desplazamiento=desplazamiento.replace("h", "").replace("H", "");
                } else {
                    desplazamiento=decimalAHexadecimalString(Integer.valueOf(destino),numeroDeBitsContenidoMemoria);
                }
                String encontrarBase=encontrar;
                Registro encontrarValor=registros.stream().filter(p->p.getNombreRegistro().trim().equalsIgnoreCase(encontrarBase)).findFirst().orElse(null);
                if (encontrarValor!=null){
                    memoriaDestino=encontrarValor.getValorAnteriorHexadecimal();
                }
                long calcularDireccionBases=hexadecimalADecimalLong(memoriaDestino)+hexadecimalADecimalLong(desplazamiento);
                memoriaDestino=decimalAHexadecimalString(calcularDireccionBases,numeroDeBitsContenidoMemoria); 
            }
        }
        
        /* ********************************************************* VALORES DE LA FUENTE ********************************************************* */
        if (!fuente.contains("R") && !fuente.contains("r") && !fuente.contains("[") && !fuente.contains("+") ){
            if (tipo==1){
                retorno="DIRECCIONAMIENTO INMEDIATO A REGISTRO "+modoSistema.toUpperCase();
            } else{
                if (tipo==2){
                    retorno="DIRECCIONAMIENTO INDIRECTO IMNEDIATO "+modoSistema.toUpperCase();
                }
            }
            if (fuente.contains("H") || fuente.contains("h")){
                valorDestino=fuente.replace("h", "").replace("H", "").replace("-", "");
            } else {
                fuente=fuente.replace("h", "").replace("H", "");
                long valorFuente;
                if (fuente.contains("-") ){
                   valorFuente = binarioADecimal(decimalABinario(Integer.valueOf(fuente),tipo==1?numeroDeBitsRegisto:numeroDeBitsContenidoMemoria));
                }else{
                    valorFuente=Integer.valueOf(fuente);
                }
                valorDestino=decimalAHexadecimalString(valorFuente,tipo==1?numeroDeBitsRegisto:numeroDeBitsContenidoMemoria);
            }
        } else{
            if ((fuente.contains("R") || fuente.contains("r")) && !fuente.contains("[")&& !fuente.contains("+")  ){
                if (tipo==1){
                    retorno="DIRECCIONAMIENTO DIRECTO A REGISTRO "+modoSistema.toUpperCase();
                } else {
                    if (tipo==2){
                        retorno="DIRECCIONAMIENTO INDIRECTO A REGISTRO "+modoSistema.toUpperCase();
                    }
                } 
                String registroProcedente=fuente;
                Registro encontrarValor=registros.stream().filter(p->p.getNombreRegistro().trim().equalsIgnoreCase(registroProcedente)).findFirst().orElse(null);
                if (encontrarValor!=null){
                    valorDestino=encontrarValor.getValorAnteriorHexadecimal();
                }
            } else{
                 
                if (fuente.contains("[")  && !fuente.contains("+") ){
                    if (fuente.contains("[") && !fuente.contains("R") && !fuente.contains("r")){
                        if (tipo==1) {
                            retorno="DIRECCIONAMIENTO DIRECTO A MEMORIA (INMEDIATO) "+modoSistema.toUpperCase();
                        } else { 
                            if (tipo==2){ 
                                retorno="DIRECCIONAMIENTO INDIRECTO A MEMORIA (INMEDIATO) "+modoSistema.toUpperCase();
                            }    
                         }
                        fuente=fuente.replace("[", "").replace("]", "");
                        if (fuente.contains("H") || fuente.contains("h")){
                            fuente=fuente.replace("h", "").replace("H", "").replace("-", "");;
                             if (fuente.length()<numeroDeBitsContenidoMemoria*numPalabra){
                                 for (int i = fuente.length(); i < numeroDeBitsContenidoMemoria*numPalabra; i++) {
                                     fuente="0"+fuente;
                                 }
                             }else{
                                 if (fuente.length()>numeroDeBitsContenidoMemoria*numPalabra){
                                     fuente=fuente.substring(0, numeroDeBitsContenidoMemoria*numPalabra-1);
                                 }
                             }
                        } else {
                            fuente=fuente.replace("h", "").replace("H", "");
                             Long valorLong=Long.valueOf(fuente);
                             if (fuente.contains("-") ){
                                valorLong = binarioADecimal(decimalABinario(Integer.valueOf(fuente),tipo==1?numeroDeBitsRegisto:numeroDeBitsContenidoMemoria));
                             }
                            fuente=decimalAHexadecimalString(valorLong,numeroDeBitsContenidoMemoria*numPalabra);
                        }
                        
                    } else {
                        if (fuente.contains("[") && (fuente.contains("R") || fuente.contains("r"))){
                             if (tipo==1) {
                                retorno="DIRECCIONAMIENTO DIRECTO A MEMORIA (REGISTRO) "+modoSistema.toUpperCase();
                             } else {  
                                if (tipo==2) {
                                    retorno="DIRECCIONAMIENTO INDIRECTO A MEMORIA (REGISTRO) "+modoSistema.toUpperCase();
                                }
                             }    
                            fuente=fuente.replace("[", "").replace("]", "");
                            String registroProcedente=fuente;
                            Registro encontrarValor=registros.stream().filter(p->p.getNombreRegistro().trim().equalsIgnoreCase(registroProcedente)).findFirst().orElse(null);
                            if (encontrarValor!=null){
                                fuente=encontrarValor.getValorAnteriorHexadecimal();
                            }
                        }
                    }
                    if (numPalabra==1){
                        long valor =hexadecimalADecimalLong(fuente);
                        Memoria memory= direccionesMemoria.stream().filter(p->p.getDireccionEnDecimal()==valor).findFirst().orElse(null);
                        if (memory!=null){
                            palabra=palabra+memory.getValorAnteriorHexadecimal();
                        }  
                    } else {
                        if (modoSistema.trim().equalsIgnoreCase("little-endian")){
                            for (int i = (numPalabra-1); i >= 0; i--) {
                                long valor = hexadecimalADecimalLong(fuente)+i;
                                Memoria memory= direccionesMemoria.stream().filter(p->p.getDireccionEnDecimal()==valor).findFirst().orElse(null);
                                if (memory!=null){
                                        palabra=palabra+memory.getValorAnteriorHexadecimal();
                                } 
                            }
                        } else {
                            for (int i = 0; i < numPalabra; i++) {
                                long valor = hexadecimalADecimalLong(fuente)+i;
                                Memoria memory= direccionesMemoria.stream().filter(p->p.getDireccionEnDecimal()==valor).findFirst().orElse(null);
                                if (memory!=null){
                                    palabra=palabra+memory.getValorAnteriorHexadecimal(); 
                                } 
                            }
                        }
                    }    
                    valorDestino=palabra;
                }else{
                    if (fuente.contains("[")  && fuente.contains("+") ){
                        fuente=fuente.replace("[", "").replace("]", "");
                        String[] parts = fuente.split("\\+");
                        
                        long valorRelativo=0;
                        if (parts[0].contains("R") || parts[0].contains("r") || parts[1].contains("R") || parts[1].contains("r") ){
                            if (tipo==1){
                                retorno="DIRECCIONAMIENTO DIRECTO A RELATIVO A MEMORIA A REGISTRO  "+modoSistema.toUpperCase();
                            } else {
                                if (tipo==2){
                                    retorno="DIRECCIONAMIENTO INDIRECTO RELATIVO A MEMORIA A REGISTRO "+modoSistema.toUpperCase();
                                }else{
                                    retorno="DIRECCIONAMIENTO RELATIVO RELATIVO A MEMEORIA A REGISTRO "+modoSistema.toUpperCase();
                                }
                            }
                        }
                        if (parts[0].contains("R") || parts[0].contains("r") || parts[0].contains("PC")){
                           Registro encontrarValor=registros.stream().filter(p->p.getNombreRegistro().trim().equalsIgnoreCase(parts[0])).findFirst().orElse(null);
                            if (encontrarValor!=null){
                                valorRelativo=valorRelativo+hexadecimalADecimalLong(encontrarValor.getValorAnteriorHexadecimal());
                               
                            }
                        } else{
                            if (parts[0].contains("H") || parts[0].contains("h")){
                                parts[0]=parts[0].replace("h", "").replace("H", "");
                                valorRelativo=valorRelativo+hexadecimalADecimalLong(parts[0]);
                            }else{
                                valorRelativo=valorRelativo+Integer.valueOf(parts[0]);
                            }
                        }
                        if (parts[1].contains("R") || parts[1].contains("r") || parts[1].contains("PC")){
                           Registro encontrarValor=registros.stream().filter(p->p.getNombreRegistro().trim().equalsIgnoreCase(parts[1])).findFirst().orElse(null);
                            if (encontrarValor!=null){
                                valorRelativo=valorRelativo+hexadecimalADecimalLong(encontrarValor.getValorAnteriorHexadecimal());
                            }
                        }else{
                            if (parts[1].contains("H") || parts[1].contains("h")){
                                parts[1]=parts[1].replace("h", "").replace("H", "");
                                valorRelativo=valorRelativo+hexadecimalADecimalLong(parts[1 ]);
                            }else{
                                valorRelativo=valorRelativo+Integer.valueOf(parts[1]);
                            }
                        }
                        palabra="";
                        if (numPalabra==1){
                            long valor =valorRelativo;
                            Memoria memory= direccionesMemoria.stream().filter(p->p.getDireccionEnDecimal()==valor).findFirst().orElse(null);
                            if (memory!=null){
                                palabra=palabra+memory.getValorAnteriorHexadecimal();
                            }  
                        } else {
                            if (modoSistema.trim().equalsIgnoreCase("little-endian")){
                                for (int i = (numPalabra-1); i >= 0; i--) {
                                    long valor =valorRelativo+i;
                                    Memoria memory= direccionesMemoria.stream().filter(p->p.getDireccionEnDecimal()==valor).findFirst().orElse(null);
                                    if (memory!=null){
                                        palabra=palabra+memory.getValorAnteriorHexadecimal();
                                    } 
                                }
                            } else {
                                for (int i = 0; i < numPalabra; i++) {
                                    long valor =valorRelativo+i;
                                    Memoria memory= direccionesMemoria.stream().filter(p->p.getDireccionEnDecimal()==valor).findFirst().orElse(null);
                                    if (memory!=null){
                                        palabra=palabra+memory.getValorAnteriorHexadecimal(); 
                                    } 
                                }
                            }        
                        }   
                        valorDestino=palabra;
                       
                    } else{
                        if ((fuente.contains("R") || fuente.contains("r"))  && fuente.contains("+") ){
                            String[] parts = fuente.split("\\+");
                            
                            long valorRelativo=0;
                            if (parts[0].contains("R") || !parts[0].contains("r") || parts[1].contains("R") || !parts[2].contains("r") ){
                                if (tipo==1){
                                    retorno="DIRECCIONAMIENTO DIRECTO A RELATIVO A REGISTRO "+modoSistema.toUpperCase();
                                } else {
                                    if (tipo==2){
                                        retorno="DIRECCIONAMIENTO INDIRECTO RELATIVO A REGISTRO "+modoSistema.toUpperCase();
                                    }else{
                                        retorno="DIRECCIONAMIENTO RELATIVO RELATIVO A REGISTRO "+modoSistema.toUpperCase();
                                    }
                                }
                            }
                            if (parts[0].contains("R") || parts[0].contains("r") || parts[0].contains("PC")){
                               Registro encontrarValor=registros.stream().filter(p->p.getNombreRegistro().trim().equalsIgnoreCase(parts[0])).findFirst().orElse(null);
                                if (encontrarValor!=null){
                                    valorRelativo=valorRelativo+hexadecimalADecimalLong(encontrarValor.getValorAnteriorHexadecimal());

                                }
                            } else{
                                if (parts[0].contains("H") || parts[0].contains("h")){
                                    parts[0]=parts[0].replace("h", "").replace("H", "");
                                    valorRelativo=valorRelativo+hexadecimalADecimalLong(parts[0]);
                                }else{
                                    valorRelativo=valorRelativo+Integer.valueOf(parts[0]);
                                }
                            }
                            if (parts[1].contains("R") || parts[1].contains("r") || parts[1].contains("PC")){
                               Registro encontrarValor=registros.stream().filter(p->p.getNombreRegistro().trim().equalsIgnoreCase(parts[1])).findFirst().orElse(null);
                                if (encontrarValor!=null){
                                    valorRelativo=valorRelativo+hexadecimalADecimalLong(encontrarValor.getValorAnteriorHexadecimal());
                                }
                            }else{
                                if (parts[1].contains("H") || parts[1].contains("h")){
                                    parts[1]=parts[1].replace("h", "").replace("H", "");
                                    valorRelativo=valorRelativo+hexadecimalADecimalLong(parts[1 ]);
                                }else{
                                    valorRelativo=valorRelativo+Integer.valueOf(parts[1]);
                                }
                            }
                               
                            valorDestino=decimalAHexadecimalString(valorRelativo,tipo==1?numeroDeBitsRegisto:numeroDeBitsContenidoMemoria*numPalabra);
                        }
                    }
                }
            }
        }
        valorDestino=valorDestino.replace("h", "").replace("H", "").replace("-", "").replace(" ", "");
        memoriaDestino=memoriaDestino.replace("h", "").replace("H", "").replace("-", "").replace(" ", "");
/* ******************************************************************* OPERACIONES ********************************************************************/        
        switch (tipo) {
            case 1 -> {
                String valor =registerDestino;
                Registro register=registros.stream().filter(p->p.getNombreRegistro().trim().equalsIgnoreCase(valor)).findFirst().orElse(null);
                if (register!=null){
                    if (tipoOpereacion==0 ){
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN HEXADECIMAL ==> "+formatoHexadeximal(valorDestino)+"h";
                        LineaTerceraAImprimir="EN DECIMAL     ==> "+hexadecimalADecimalLong(valorDestino)+" d ";
                    }
                    if (tipoOpereacion==1){
                        long sumamos=hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())+hexadecimalADecimalLong(valorDestino);
                        String resultadoOperacion = (decimalAHexadecimalString(sumamos,(numeroDeBitsRegisto*4))).substring(numeroDeBitsRegisto*4-numeroDeBitsRegisto-1);
                        BitTrasnporte=resultadoOperacion.length()>numeroDeBitsRegisto?(!resultadoOperacion.substring(0,1).equals("0")?1:0):0;
                        LineaSegundaAImprimir=BitTrasnporte==1?"LA SUMA GENERA TRANSPORTE \n":"";
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN HEXADECIMAL ==> "+formatoHexadeximal(register.getValorAnteriorHexadecimal())+"h + "+formatoHexadeximal(valorDestino)+"h = "+resultadoOperacion+" h";;
                        LineaTerceraAImprimir="EN DECIMAL     ==> "+hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())+ " d + "+hexadecimalADecimalLong(valorDestino)+" d = "+sumamos+" d";
                        valorDestino=decimalAHexadecimalString(register.getValorAnteriorDecimal()+hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto*4);
                        valorDestino=valorDestino.substring(valorDestino.length()-numeroDeBitsRegisto);
                        BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                        BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                        BitDesbordamiento=resultadoOperacion.length()>numeroDeBitsRegisto+1?1:0;
                    }
                    if (tipoOpereacion==2){
                        long sumamos=hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())-hexadecimalADecimalLong(valorDestino);
                        String resultadoOperacion = (decimalAHexadecimalString(sumamos,(numeroDeBitsRegisto*4))).substring(numeroDeBitsRegisto*4-numeroDeBitsRegisto-1);
                        BitTrasnporte=resultadoOperacion.length()>numeroDeBitsRegisto?(!resultadoOperacion.substring(0,1).equals("0")?1:0):0;
                        LineaSegundaAImprimir=BitTrasnporte==1?"LA RESTA GENERA TRANSPORTE \n":"";
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN HEXADECIMAL ==> "+formatoHexadeximal(register.getValorAnteriorHexadecimal())+"h - "+formatoHexadeximal(valorDestino)+"h = "+resultadoOperacion+"h";;
                        LineaTerceraAImprimir="EN DECIMAL     ==> "+hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())+" d - "+hexadecimalADecimalLong(valorDestino)+" d = "+sumamos+" d";
                        valorDestino=decimalAHexadecimalString(register.getValorAnteriorDecimal()-hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto*4);
                        valorDestino=valorDestino.substring(valorDestino.length()-numeroDeBitsRegisto);
                        BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                        BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                        BitDesbordamiento=resultadoOperacion.length()>numeroDeBitsRegisto+1?1:0;
                    }
                    if (tipoOpereacion==11 ){
                        long sumamos=hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())*hexadecimalADecimalLong(valorDestino);
                        String resultadoOperacion = (decimalAHexadecimalString(sumamos,(numeroDeBitsRegisto*4))).substring(numeroDeBitsRegisto*4-numeroDeBitsRegisto-1);
                        BitDesbordamiento=0;
                        if (resultadoOperacion.length()>numeroDeBitsRegisto+1){
                            BitDesbordamiento=1;
                        }else{
                            BitDesbordamiento=resultadoOperacion.length()>numeroDeBitsRegisto?(!resultadoOperacion.substring(0,1).equals("0")?1:0):0;
                        }
                        LineaSegundaAImprimir=BitDesbordamiento==1?"LA MULTIPLICACIÓN GENERA DESBORDAMIENTO \n":"";
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN HEXADECIMAL ==> "+formatoHexadeximal(register.getValorAnteriorHexadecimal())+"h * "+formatoHexadeximal(valorDestino)+"h = "+resultadoOperacion+"h";;
                        LineaTerceraAImprimir="EN DECIMAL     ==> "+hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())+" d * "+hexadecimalADecimalLong(valorDestino)+" d = "+sumamos+" d";
                        valorDestino=decimalAHexadecimalString(register.getValorAnteriorDecimal()*hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto*4);
                        valorDestino=valorDestino.substring(valorDestino.length()-numeroDeBitsRegisto);
                        BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                        BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                        BitDesbordamiento=resultadoOperacion.length()>numeroDeBitsRegisto+1?1:0;
                    }
                    if (tipoOpereacion==4){
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(register.getValorAnteriorHexadecimal())+" h ==> "+hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())+" d \n";
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(valorDestino)+" h ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";
                        List<Integer> binarioValorDestino=decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto);
                        List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto);
                        List<Integer> binarioValorFinal = new ArrayList();
                        for (int i = 0; i < binarioValorDestino.size(); i++) {
                            if (binarioValorDestino.get(i)==1 && binarioValorOrigen.get(i)==1){
                                binarioValorFinal.add(1);
                            } else {
                                binarioValorFinal.add(0);
                            } 
                        }
                        valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                        BitDesbordamiento=0;
                        BitTrasnporte=0;
                        BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                        BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"AND            ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"AND GENERA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                    }
                    if (tipoOpereacion==5){
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(register.getValorAnteriorHexadecimal())+" h ==> "+hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())+" d \n";
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(valorDestino)+" h ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";
                        List<Integer> binarioValorDestino=decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto);
                        List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto);
                        List<Integer> binarioValorFinal = new ArrayList();
                        for (int i = 0; i < binarioValorDestino.size(); i++) {
                            if (binarioValorDestino.get(i)==0 && binarioValorOrigen.get(i)==0){
                                binarioValorFinal.add(0);
                            } else {
                                binarioValorFinal.add(1);
                            } 
                        }
                        valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                        BitDesbordamiento=0;
                        BitTrasnporte=0;
                        BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                        BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"OR             ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"AND GENERA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                    }
                    if (tipoOpereacion==6){
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(register.getValorAnteriorHexadecimal())+" h ==> "+hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())+" d \n";
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(valorDestino)+" h ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";
                       List<Integer> binarioValorDestino=decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto);
                        List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto);
                        List<Integer> binarioValorFinal = new ArrayList();
                        for (int i = 0; i < binarioValorDestino.size(); i++) {
                            if ((binarioValorDestino.get(i)==0 && binarioValorOrigen.get(i)==0) || (binarioValorDestino.get(i)==1 && binarioValorOrigen.get(i)==1)){
                                binarioValorFinal.add(0);
                            } else {
                                
                                binarioValorFinal.add(1);
                            } 
                        }
                        valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                        BitDesbordamiento=0;
                        BitTrasnporte=0;
                        BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                        BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"XOR            ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"AND GENERA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                    }
                    if (tipoOpereacion==7){
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN HEXADECIMAL ==> "+formatoHexadeximal(valorDestino)+"h";
                        LineaTerceraAImprimir="EN DECIMAL     ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                        List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto);
                        List<Integer> binarioValorFinal = new ArrayList();
                        for (int i = 0; i < binarioValorOrigen.size(); i++) {
                            if (binarioValorOrigen.get(i)==1){
                                binarioValorFinal.add(0);
                            } else {
                                binarioValorFinal.add(1);
                            } 
                        }
                        valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"           NOT ==> "+formatoBinario(binarioValorFinal)+" b";
                        
                    }
                    if (tipoOpereacion==8){
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(register.getValorAnteriorHexadecimal())+" h ==> "+hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())+" d \n";
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"DESPLAZAR A LA DERECHA   DECIMAL ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";
                       
                        List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto);
                        int bitDeMasPeso= binarioValorOrigen.get(0);
                        List<Integer> binarioValorFinal = new ArrayList();
                        int desplazamos =(int)(hexadecimalADecimalLong(valorDestino));
                        if (desplazamos<numeroDeBitsRegisto*4) {
                            for (int i = 0; i < binarioValorOrigen.size()-(desplazamos); i++) {
                                 binarioValorFinal.add(binarioValorOrigen.get(i+desplazamos));
                            }
                            for (int i =  binarioValorOrigen.size()-desplazamos; i < binarioValorOrigen.size(); i++) {
                                 binarioValorFinal.add(0);
                            }
                        }else{
                            for (int i = 0; i < numeroDeBitsRegisto*4; i++) {
                                 binarioValorFinal.add(0);
                            }
                        }
                        valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"           SAL <== "+formatoBinario(binarioValorFinal)+" b  \n";
                        BitDesbordamiento=binarioValorOrigen.get(0)!=binarioValorFinal.get(0)?1:0;
                        BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                        BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                        LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"SAL GENERA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                    }
                    if (tipoOpereacion==9){
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(register.getValorAnteriorHexadecimal())+" h ==> "+hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())+" d \n";
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"DESPLAZAR A LA IZQUIERDA DECIMAL ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";
                       
                        List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto);
                        int bitDeMasPeso= binarioValorOrigen.get(0);
                        List<Integer> binarioValorFinal = new ArrayList();
                        int desplazamos =(int)(hexadecimalADecimalLong(valorDestino));
                        binarioValorFinal.add(bitDeMasPeso);
                        for (int i = 1; i < desplazamos; i++) {
                             binarioValorFinal.add(bitDeMasPeso);
                        }
                        for (int i = desplazamos; i < binarioValorOrigen.size(); i++) {
                             binarioValorFinal.add(binarioValorOrigen.get(i-desplazamos));
                        }
                        valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"           SAR ==> "+formatoBinario(binarioValorFinal)+" b  \n";
                        
                        BitDesbordamiento=0;
                        BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                        BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                        LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"SAR CONSERVA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                    }
                    if (tipoOpereacion==16){
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(register.getValorAnteriorHexadecimal())+" h ==> "+hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())+" d \n";
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"DESPLAZAR A LA IZQUIERDA DECIMAL ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";
                       
                        List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto);
                        int bitDeMasPeso= binarioValorOrigen.get(0);
                        List<Integer> binarioValorFinal = new ArrayList();
                        int desplazamos =(int)(hexadecimalADecimalLong(valorDestino));
                        desplazamos=+desplazamos%(numeroDeBitsRegisto*4);
                        for (int i = 0; i < binarioValorOrigen.size()-desplazamos; i++) {
                             binarioValorFinal.add(binarioValorOrigen.get(i+desplazamos));
                        }
                        for (int i = 0; i < desplazamos; i++) {
                             binarioValorFinal.add(binarioValorOrigen.get(i));
                        }
                        valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"           ROL ==> "+formatoBinario(binarioValorFinal)+" b  \n";
                        BitDesbordamiento=0;
                        BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                        LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"ROL CONSERVA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                    }
                    if (tipoOpereacion==17){
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(register.getValorAnteriorHexadecimal())+" h ==> "+hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())+" d \n";
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"DESPLAZAR A LA IZQUIERDA DECIMAL ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";
                       
                        List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto);
                        int bitDeMasPeso= binarioValorOrigen.get(0);
                        List<Integer> binarioValorFinal = new ArrayList();
                        int desplazamos =(int)(hexadecimalADecimalLong(valorDestino));
                        desplazamos=+desplazamos%(numeroDeBitsRegisto*4);
                        for (int i = desplazamos; i > 0; i--) {
                             binarioValorFinal.add(binarioValorOrigen.get(binarioValorOrigen.size()-i));
                        }
                        for (int i = 0; i < binarioValorOrigen.size()-desplazamos; i++) {
                             binarioValorFinal.add(binarioValorOrigen.get(i));
                        }
                       
                        valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"           ROR ==> "+formatoBinario(binarioValorFinal)+" b  \n";
                        BitDesbordamiento=0;
                        BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                        LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"ROL CONSERVA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                    }
                   
                    if (tipoOpereacion==15){
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(register.getValorAnteriorHexadecimal())+" h ==> "+hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())+" d \n";
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(valorDestino)+" h ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";
                        List<Integer> binarioValorDestino=decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto);
                        List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(register.getValorAnteriorHexadecimal()),numeroDeBitsRegisto);
                        List<Integer> binarioValorFinal = new ArrayList();
                        for (int i = 0; i < binarioValorDestino.size(); i++) {
                            if (binarioValorDestino.get(i)==1 && binarioValorOrigen.get(i)==1){
                                binarioValorFinal.add(1);
                            } else {
                                binarioValorFinal.add(0);
                            } 
                        }
                        valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                        BitDesbordamiento=0;
                        BitTrasnporte=0;
                        BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                        BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"TEST            ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                        LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"TEST GENERA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                        LineaTerceraAImprimir=LineaTerceraAImprimir+"\n"+"TEST NO GUARDA CAMBIOS, SOLO TESTEA LA OPERACIÓN AND Y VERIFICA BIT DE SIGNO Y BIR CERO \n";
                        System.out.println(retorno+ " ==> " +nombreInstruccionAEjecutar);
                        System.out.println("");
                        System.out.println(LineaSegundaAImprimir);
                        System.out.println(LineaTerceraAImprimir);
                        System.out.println("");
                        System.out.println(register.getNombreRegistro()+" = "+ formatoHexadeximal(register.getValorPosteriorHexadecimal())+"h");
                        System.out.println(register.getNombreRegistro()+" = "+ register.getValorPosteriorDecimal()+" d ");
                        System.out.println("");
                        System.out.println("Z = "+BitCero+"  ,  S = "+BitSigno+"  ,  C = "+BitTrasnporte+"  ,  V = "+BitDesbordamiento);
                        System.out.println("");
                    }
                     
                     
                    if (tipoOpereacion==10){
                        long sumamos=hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())-hexadecimalADecimalLong(valorDestino);
                        String resultadoOperacion = (decimalAHexadecimalString(sumamos,(numeroDeBitsRegisto*4))).substring(numeroDeBitsRegisto*4-numeroDeBitsRegisto-1);
                        BitTrasnporte=resultadoOperacion.length()>numeroDeBitsRegisto?(!resultadoOperacion.substring(0,1).equals("0")?1:0):0;
                        LineaSegundaAImprimir=BitTrasnporte==1?"LA COMPARACIÓN GENERA TRANSPORTE \n":"";
                        LineaSegundaAImprimir=LineaSegundaAImprimir+"EN HEXADECIMAL ==> "+formatoHexadeximal(register.getValorAnteriorHexadecimal())+"h - "+formatoHexadeximal(valorDestino)+"h = "+resultadoOperacion+"h";;
                        LineaTerceraAImprimir="EN DECIMAL     ==> "+hexadecimalADecimalLong(register.getValorAnteriorHexadecimal())+" d - "+hexadecimalADecimalLong(valorDestino)+" d = "+sumamos+" d \n";
                        valorDestino=decimalAHexadecimalString(register.getValorAnteriorDecimal()-hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto*4);
                        valorDestino=valorDestino.substring(valorDestino.length()-numeroDeBitsRegisto);
                        BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                        BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                        BitDesbordamiento=resultadoOperacion.length()>numeroDeBitsRegisto+1?1:0;
                        LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"LA COMPARACIÓN GENERA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                        imprimirBitsResultado(retorno);
                    } else {
                        if (tipoOpereacion!=15){
                            if (valorDestino.length()<numeroDeBitsRegisto){
                                for (int i = valorDestino.length(); i < numeroDeBitsRegisto; i++) {
                                    valorDestino="0"+valorDestino;
                                }
                            }
                            register.setValorPosteriorHexadecimal(valorDestino);
                            register.setValorPosteriorDecimal(hexadecimalADecimalLong(valorDestino));   
                            imprimirCambiosRegistros(register, retorno);
                        }    
                    }    
                }
            }
            case 2, 3 -> {
                palabra="";
                //BitTrasnporte=0;
                if (modoSistema.trim().equalsIgnoreCase("little-endian")){
                    for (int i = (numPalabra-1); i >= 0; i--) {
                        long direccionIncio= (hexadecimalADecimalLong(memoriaDestino)+i);
                        Memoria memory=direccionesMemoria.stream().filter(p->p.getDireccionEnDecimal()==direccionIncio).findFirst().orElse(null);
                        if (memory!=null){
                            palabra=palabra+memory.getValorAnteriorHexadecimal(); 
                        }
                    }
                }else{
                    for (int i = 0; i < numPalabra; i++) {
                        long direccionIncio= (hexadecimalADecimalLong(memoriaDestino)+i);
                        Memoria memory=direccionesMemoria.stream().filter(p->p.getDireccionEnDecimal()==direccionIncio).findFirst().orElse(null);
                        if (memory!=null){
                            palabra=palabra+memory.getValorAnteriorHexadecimal(); 
                        }
                    }
                }
                if (tipoOpereacion==0 ){
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN HEXADECIMAL ==> "+formatoHexadeximal(valorDestino)+"h = ";
                    LineaTerceraAImprimir="EN DECIMAL     ==> "+hexadecimalADecimalLong(valorDestino)+" d = ";
                }
                if (tipoOpereacion==1){
                    long sumamos=hexadecimalADecimalLong(palabra)+hexadecimalADecimalLong(valorDestino);
                    String resultadoOperacion = (decimalAHexadecimalString(sumamos,(numeroDeBitsRegisto*4))).substring(numeroDeBitsRegisto*4-numeroDeBitsRegisto-1);
                    BitTrasnporte=resultadoOperacion.length()>numeroDeBitsRegisto?(!resultadoOperacion.substring(0,1).equals("0")?1:0):0;
                    LineaSegundaAImprimir=BitTrasnporte==1?"LA SUMA GENERA TRANSPORTE \n":"";
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN HEXADECIMAL ==> "+formatoHexadeximal(palabra)+"h + "+formatoHexadeximal(valorDestino)+"h = "+resultadoOperacion+"h";;
                    LineaTerceraAImprimir="EN DECIMAL     ==> "+hexadecimalADecimalLong(palabra)+" d + "+hexadecimalADecimalLong(valorDestino)+" d = "+sumamos+" d";
                    valorDestino=decimalAHexadecimalString(hexadecimalADecimalLong(palabra)+hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto*4);
                    valorDestino=valorDestino.substring(valorDestino.length()-numeroDeBitsRegisto);
                    BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                    BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                    BitDesbordamiento=resultadoOperacion.length()>numeroDeBitsRegisto+1?1:0;
                        
                }
                if (tipoOpereacion==2){
                    long sumamos=hexadecimalADecimalLong(palabra)-hexadecimalADecimalLong(valorDestino);
                    String resultadoOperacion = (decimalAHexadecimalString(sumamos,(numeroDeBitsRegisto*4))).substring(numeroDeBitsRegisto*4-numeroDeBitsRegisto-1);
                    BitTrasnporte=resultadoOperacion.length()>numeroDeBitsRegisto?(!resultadoOperacion.substring(0,1).equals("0")?1:0):0;
                    LineaSegundaAImprimir=BitTrasnporte==1?"LA RESTA GENERA TRANSPORTE \n":"";
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN HEXADECIMAL ==> "+formatoHexadeximal(palabra)+"h - "+formatoHexadeximal(valorDestino)+"h = "+resultadoOperacion+"h";;
                    LineaTerceraAImprimir="EN DECIMAL     ==> "+hexadecimalADecimalLong(palabra)+" d - "+hexadecimalADecimalLong(valorDestino)+" d = "+sumamos+" d";
                    valorDestino=decimalAHexadecimalString(hexadecimalADecimalLong(palabra)-hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto*4);
                    valorDestino=valorDestino.substring(valorDestino.length()-numeroDeBitsRegisto);
                    BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                    BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                    BitDesbordamiento=resultadoOperacion.length()>numeroDeBitsRegisto+1?1:0;
                }   
                if (tipoOpereacion==11 ){
                    long sumamos=hexadecimalADecimalLong(palabra)*hexadecimalADecimalLong(valorDestino);
                    String resultadoOperacion = (decimalAHexadecimalString(sumamos,(numeroDeBitsRegisto*4))).substring(numeroDeBitsRegisto*4-numeroDeBitsRegisto-1);
                    BitDesbordamiento=0;
                    if (resultadoOperacion.length()>numeroDeBitsRegisto+1){
                        BitDesbordamiento=1;
                    }else{
                        BitDesbordamiento=resultadoOperacion.length()>numeroDeBitsRegisto?(!resultadoOperacion.substring(0,1).equals("0")?1:0):0;
                    }
                    LineaSegundaAImprimir=BitTrasnporte==1?"LA MULTIPLICACIÓN GENERA TRANSPORTE \n":"";
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN HEXADECIMAL ==> "+formatoHexadeximal(palabra)+"h * "+formatoHexadeximal(valorDestino)+"h = "+resultadoOperacion+"h";;
                    LineaTerceraAImprimir="EN DECIMAL     ==> "+hexadecimalADecimalLong(palabra)+" d * "+hexadecimalADecimalLong(valorDestino)+" d = "+sumamos+" d";
                    valorDestino=decimalAHexadecimalString(hexadecimalADecimalLong(palabra)*hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto*4);
                    valorDestino=valorDestino.substring(valorDestino.length()-numeroDeBitsRegisto);
                    BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                    BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                }
                if (tipoOpereacion==4){
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(palabra)+" h ==> "+hexadecimalADecimalLong(palabra)+" d \n";
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(valorDestino)+" h ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";
                    List<Integer> binarioValorDestino=decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto);
                    List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto);
                    List<Integer> binarioValorFinal = new ArrayList();
                    for (int i = 0; i < binarioValorDestino.size(); i++) {
                        if (binarioValorDestino.get(i)==1 && binarioValorOrigen.get(i)==1){
                            binarioValorFinal.add(1);
                        } else {
                            binarioValorFinal.add(0);
                        } 
                    }
                    valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                    BitDesbordamiento=0;
                    BitTrasnporte=0;
                    BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                    BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"AND            ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"AND GENERA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                }
                if (tipoOpereacion==5){
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(palabra)+" h ==> "+hexadecimalADecimalLong(palabra)+" d \n";
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(valorDestino)+" h ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";
                    List<Integer> binarioValorDestino=decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto);
                    List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto);
                    List<Integer> binarioValorFinal = new ArrayList();
                    for (int i = 0; i < binarioValorDestino.size(); i++) {
                        if (binarioValorDestino.get(i)==0 && binarioValorOrigen.get(i)==0){
                            binarioValorFinal.add(0);
                        } else {
                            binarioValorFinal.add(1);
                        } 
                    }
                    valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                    BitDesbordamiento=0;
                    BitTrasnporte=0;
                    BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                    BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"OR             ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"AND GENERA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                }
                if (tipoOpereacion==6){
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(palabra)+" h ==> "+hexadecimalADecimalLong(palabra)+" d \n";
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(valorDestino)+" h ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";
                    List<Integer> binarioValorDestino=decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto);
                    List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto);
                    List<Integer> binarioValorFinal = new ArrayList();
                    for (int i = 0; i < binarioValorDestino.size(); i++) {
                        if ((binarioValorDestino.get(i)==0 && binarioValorOrigen.get(i)==0) || (binarioValorDestino.get(i)==1 && binarioValorOrigen.get(i)==1)){
                            binarioValorFinal.add(0);
                        } else {
                            binarioValorFinal.add(1);
                        }
                    }
                    valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                    BitDesbordamiento=0;
                    BitTrasnporte=0;
                    BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                    BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"XOR            ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"AND GENERA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                }
                if (tipoOpereacion==7){
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN HEXADECIMAL ==> "+formatoHexadeximal(valorDestino)+"h";
                    LineaTerceraAImprimir="EN DECIMAL     ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto+4))+" b \n";     
                    List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsContenidoMemoria*numPalabra);
                    List<Integer> binarioValorFinal = new ArrayList();
                    for (int i = 0; i < binarioValorOrigen.size(); i++) {
                        if (binarioValorOrigen.get(i)==1){
                            binarioValorFinal.add(0);
                        } else {
                            binarioValorFinal.add(1);
                        }
                    }
                    valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsContenidoMemoria*numPalabra);
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"           NOT ==> "+formatoBinario(binarioValorFinal)+" b";
                }
                if (valorDestino.length()<numeroDeBitsContenidoMemoria*numPalabra){
                    for (int i = valorDestino.length(); i < numeroDeBitsContenidoMemoria*numPalabra; i++) {
                        valorDestino="0"+valorDestino;
                    }
                }
                if (tipoOpereacion==8){
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(palabra)+" h ==> "+hexadecimalADecimalLong(palabra)+" d \n";
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"DESPLAZAR A LA DERECHA   DECIMAL ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";
                       
                    List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto);
                    int bitDeMasPeso= binarioValorOrigen.get(0);
                    List<Integer> binarioValorFinal = new ArrayList();
                    int desplazamos =(int)(hexadecimalADecimalLong(valorDestino));
                    if (desplazamos<numeroDeBitsRegisto*4) {
                        for (int i = 0; i < binarioValorOrigen.size()-(desplazamos); i++) {
                             binarioValorFinal.add(binarioValorOrigen.get(i+desplazamos));
                        }
                        for (int i =  binarioValorOrigen.size()-desplazamos; i < binarioValorOrigen.size(); i++) {
                             binarioValorFinal.add(0);
                        }
                    }else{
                        for (int i = 0; i < numeroDeBitsRegisto*4; i++) {
                             binarioValorFinal.add(0);
                        }
                    }
                    valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"           SAL <== "+formatoBinario(binarioValorFinal)+" b  \n";
                    BitDesbordamiento=!Objects.equals(binarioValorOrigen.get(0), binarioValorFinal.get(0))?1:0;
                    BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                    BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                    LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"SAL GENERA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                }
                if (tipoOpereacion==9){
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(palabra)+" h ==> "+hexadecimalADecimalLong(palabra)+" d \n";
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"DESPLAZAR A LA IZQUIERDA DECIMAL ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";

                    List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto);
                    int bitDeMasPeso= binarioValorOrigen.get(0);
                    List<Integer> binarioValorFinal = new ArrayList();
                    int desplazamos =(int)(hexadecimalADecimalLong(valorDestino));
                    binarioValorFinal.add(bitDeMasPeso);
                    for (int i = 1; i < desplazamos; i++) {
                         binarioValorFinal.add(0);
                    }
                    for (int i = desplazamos; i < binarioValorOrigen.size(); i++) {
                         binarioValorFinal.add(binarioValorOrigen.get(i-desplazamos));
                    }
                    valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"           SAR ==> "+formatoBinario(binarioValorFinal)+" b  \n";
                    BitDesbordamiento=0;
                    BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                    BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                    LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"SAR CONSERVA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                }
              
                if (tipoOpereacion==15){
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(palabra)+" h ==> "+hexadecimalADecimalLong(palabra)+" d \n";
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(valorDestino)+" h ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";
                    List<Integer> binarioValorDestino=decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto);
                    List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto);
                    List<Integer> binarioValorFinal = new ArrayList();
                    for (int i = 0; i < binarioValorDestino.size(); i++) {
                        if (binarioValorDestino.get(i)==1 && binarioValorOrigen.get(i)==1){
                            binarioValorFinal.add(1);
                        } else {
                            binarioValorFinal.add(0);
                        } 
                    }
                    valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                    BitDesbordamiento=0;
                    BitTrasnporte=0;
                    BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                    BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"TEST            ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"TEST GENERA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"\n"+"TEST NO GUARDA CAMBIOS, SOLO TESTEA LA OPERACIÓN AND Y VERIFICA BIT DE SIGNO Y BIR CERO \n";
                    System.out.println(retorno+ " ==> " +nombreInstruccionAEjecutar);
                    System.out.println("");
                    System.out.println(LineaSegundaAImprimir);
                    System.out.println(LineaTerceraAImprimir);
                    System.out.println("");
                    System.out.println("Z = "+BitCero+"  ,  S = "+BitSigno+"  ,  C = "+BitTrasnporte+"  ,  V = "+BitDesbordamiento);
                    System.out.println("");
        
                }
                if (tipoOpereacion==16){
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(palabra)+" h ==> "+hexadecimalADecimalLong(palabra)+" d \n";
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"DESPLAZAR A LA IZQUIERDA DECIMAL ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";

                    List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto);
                    int bitDeMasPeso= binarioValorOrigen.get(0);
                    List<Integer> binarioValorFinal = new ArrayList();
                    int desplazamos =(int)(hexadecimalADecimalLong(valorDestino));
                    desplazamos=+desplazamos%(numeroDeBitsRegisto*4);
                    for (int i = 0; i < binarioValorOrigen.size()-desplazamos; i++) {
                         binarioValorFinal.add(binarioValorOrigen.get(i+desplazamos));
                    }
                    for (int i = 0; i < desplazamos; i++) {
                         binarioValorFinal.add(binarioValorOrigen.get(i));
                    }
                    valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"           ROL ==> "+formatoBinario(binarioValorFinal)+" b  \n";
                    BitDesbordamiento=0;
                    BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                    LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"ROL CONSERVA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                }
                if (tipoOpereacion==17){
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN DECIMAL "+formatoHexadeximal(palabra)+" h ==> "+hexadecimalADecimalLong(palabra)+" d \n";
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"DESPLAZAR A LA IZQUIERDA DECIMAL ==> "+hexadecimalADecimalLong(valorDestino)+" d \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"EN BINARIO     ==> "+formatoBinario(decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto))+" b \n";
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"                   ==================================================================================== \n";

                    List<Integer> binarioValorOrigen=decimalABinario(hexadecimalADecimalLong(palabra),numeroDeBitsRegisto);
                    int bitDeMasPeso= binarioValorOrigen.get(0);
                    List<Integer> binarioValorFinal = new ArrayList();
                    int desplazamos =(int)(hexadecimalADecimalLong(valorDestino));
                    desplazamos=+desplazamos%(numeroDeBitsRegisto*4);
                    for (int i = desplazamos; i > 0; i--) {
                         binarioValorFinal.add(binarioValorOrigen.get(binarioValorOrigen.size()-i));
                    }
                    for (int i = 0; i < binarioValorOrigen.size()-desplazamos; i++) {
                         binarioValorFinal.add(binarioValorOrigen.get(i));
                    }

                    valorDestino=decimalAHexadecimalString((int) binarioADecimal(binarioValorFinal),numeroDeBitsRegisto);
                    LineaTerceraAImprimir=LineaTerceraAImprimir+"           ROR ==> "+formatoBinario(binarioValorFinal)+" b  \n";
                    BitDesbordamiento=0;
                    BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                    LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"ROL CONSERVA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                }
                if (tipoOpereacion==10){
                    long sumamos=hexadecimalADecimalLong(palabra)-hexadecimalADecimalLong(valorDestino);
                    String resultadoOperacion = (decimalAHexadecimalString(sumamos,(numeroDeBitsRegisto*4))).substring(numeroDeBitsRegisto*4-numeroDeBitsRegisto-1);
                    BitTrasnporte=resultadoOperacion.length()>numeroDeBitsRegisto?(!resultadoOperacion.substring(0,1).equals("0")?1:0):0;
                    LineaSegundaAImprimir=BitTrasnporte==1?"LA COMPARACIÓN GENERA TRANSPORTE \n":"";
                    LineaSegundaAImprimir=LineaSegundaAImprimir+"EN HEXADECIMAL ==> "+formatoHexadeximal(palabra)+"h - "+formatoHexadeximal(valorDestino)+"h = "+resultadoOperacion+" h";;
                    LineaTerceraAImprimir="EN DECIMAL     ==> "+hexadecimalADecimalLong(palabra)+" d - "+hexadecimalADecimalLong(valorDestino)+" d = "+sumamos+" d \n";
                    valorDestino=decimalAHexadecimalString(hexadecimalADecimalLong(palabra)-hexadecimalADecimalLong(valorDestino),numeroDeBitsRegisto*4);
                    valorDestino=valorDestino.substring(valorDestino.length()-numeroDeBitsRegisto);
                    BitCero=hexadecimalADecimalLong(valorDestino)==0?1:0;
                    LineaTerceraAImprimir=BitCero==1?LineaTerceraAImprimir+"LA COMPARACIÓN GENERA BIT CERO \n":LineaTerceraAImprimir;
                    BitSigno=((decimalABinario(hexadecimalADecimalLong(valorDestino.substring(0,1)),1)).get(0)==1)?1:0;
                    BitDesbordamiento=resultadoOperacion.length()>numeroDeBitsRegisto+1?1:0;
                    LineaTerceraAImprimir=BitSigno==1?LineaTerceraAImprimir+"LA COMPARACIÓN GENERA SIGNO NEGATIVO \n":LineaTerceraAImprimir;
                    imprimirBitsResultado(retorno);
                } else {
                    if (tipoOpereacion!=15){
                        if (modoSistema.trim().equalsIgnoreCase("little-endian")){
                            int inicio=0;
                            for (int i = (numPalabra-1); i >= 0; i--) {
                                long direccionIncio= (hexadecimalADecimalLong(memoriaDestino)+i);
                                Memoria memory=direccionesMemoria.stream().filter(p->p.getDireccionEnDecimal()==direccionIncio).findFirst().orElse(null);
                                if (memory!=null){
                                    memory.setValorPosteriorHexadecimal(valorDestino.substring(inicio, inicio+numeroDeBitsContenidoMemoria));
                                    memory.setValorPosteriorDecimal(hexadecimalADecimalLong(valorDestino.substring(inicio, inicio+numeroDeBitsContenidoMemoria)));
                                    inicio=inicio+numeroDeBitsContenidoMemoria;
                                } else{
                                    String valorCeroHex="";
                                    for (int j = 0; j < numeroDeBitsContenidoMemoria; j++) {
                                        valorCeroHex="0"+valorCeroHex;
                                    }
                                    Memoria memoriaNew = new Memoria();

                                    memoriaNew.setDireccionEnHexadecimal(decimalAHexadecimalString(direccionIncio,numeroDeBitsDireccionMemoria));
                                    memoriaNew.setDireccionEnDecimal(hexadecimalADecimalLong(memoriaNew.getDireccionEnHexadecimal()));
                                    memoriaNew.setValorAnteriorHexadecimal(valorCeroHex);
                                    memoriaNew.setValorAnteriorDecimal(0);
                                    memoriaNew.setValorPosteriorHexadecimal(valorDestino.substring(inicio, inicio+numeroDeBitsContenidoMemoria));
                                    memoriaNew.setValorPosteriorDecimal(hexadecimalADecimalLong(valorDestino.substring(inicio, inicio+numeroDeBitsContenidoMemoria)));
                                    inicio=inicio+numeroDeBitsContenidoMemoria;
                                    direccionesMemoria.add(memoriaNew);
                                }
                            }
                        }else{
                            for (int i = 0; i < numPalabra; i++) {
                                long direccionIncio= (hexadecimalADecimalLong(memoriaDestino)+i);
                                Memoria memory=direccionesMemoria.stream().filter(p->p.getDireccionEnDecimal()==direccionIncio).findFirst().orElse(null);
                                if (memory!=null){
                                    memory.setValorPosteriorHexadecimal(valorDestino.substring(i*numeroDeBitsContenidoMemoria, (i*numeroDeBitsContenidoMemoria)+numeroDeBitsContenidoMemoria));
                                    memory.setValorPosteriorDecimal(hexadecimalADecimalLong(valorDestino.substring(i*numeroDeBitsContenidoMemoria, (i*numeroDeBitsContenidoMemoria)+numeroDeBitsContenidoMemoria)));
                                }else{
                                    String valorCeroHex="";
                                    for (int j = 0; j < numeroDeBitsContenidoMemoria; j++) {
                                        valorCeroHex="0"+valorCeroHex;
                                    }
                                    Memoria memoriaNew = new Memoria();
                                    memoriaNew.setDireccionEnDecimal(direccionIncio);
                                    memoriaNew.setDireccionEnHexadecimal(decimalAHexadecimalString(direccionIncio,numeroDeBitsDireccionMemoria));
                                    memoriaNew.setValorAnteriorHexadecimal(valorCeroHex);
                                    memoriaNew.setValorAnteriorDecimal(0);
                                    memoriaNew.setValorPosteriorHexadecimal(valorDestino.substring(i*numeroDeBitsContenidoMemoria, (i*numeroDeBitsContenidoMemoria)+numeroDeBitsContenidoMemoria));
                                    memoriaNew.setValorPosteriorDecimal(hexadecimalADecimalLong(valorDestino.substring(i*numeroDeBitsContenidoMemoria, (i*numeroDeBitsContenidoMemoria)+numeroDeBitsContenidoMemoria)));
                                    direccionesMemoria.add(memoriaNew);
                                }
                            }
                        }
                        imprimirCambiosMemoria(memoriaDestino, retorno);
                    }
                }    
            }
            default -> {
            }
        }        
        return retorno;

    }

    // Conversiones de otras bases a decimal
    
    public static String formatoHexadeximal(String hexadecimal){
        String formato="";
        for (int i = 0; i < hexadecimal.length(); i++) {
            formato=formato+hexadecimal.substring(i,i+1);
            if ((i+1)%4==0){
                formato=formato+" ";
            }
        }
         
         
         return formato;
    }
    
    public static String formatoBinario(List<Integer> numero){
        String formato="[ ";
        for (int i = 0; i < numero.size(); i++) {
            formato=formato+numero.get(i);
            if ((i<numero.size()-1) && (i+1)%4==0){
                formato=formato+ " - ";
            }else{
                if (i<numero.size()-1){
                    formato=formato+" " ;
                }
            }    
        }
        formato=formato+" ]";
        
         
         
         return formato;
    }
    
    public static long hexadecimalADecimalLong(String hexadecimal) {
        
        hexadecimal=hexadecimal.replaceAll("\\[", "").replaceAll("\\]","").replaceAll("h","").replaceAll(" ","").replaceAll("-","").replaceAll("/","").replaceAll("H","");
        String caracteresHexadecimales = "0123456789ABCDEF";
        hexadecimal = hexadecimal.toUpperCase();
        long decimal = 0;
        for (int i = 0; i < hexadecimal.length(); i++) {
            char caracter = hexadecimal.charAt(i);
            int posicionEnCaracteres = caracteresHexadecimales.indexOf(caracter);
            decimal = 16 * decimal + posicionEnCaracteres;
        }
        return decimal;
    }
    
    public static  int calculoBitTransporte(long decimal) {
       
        int residuo;
        String hexadecimal = "";
        char[] caracteresHexadecimales = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (decimal > 0) {
            residuo = (int) (decimal % 16);
            char caracterHexadecimal = caracteresHexadecimales[residuo];
            hexadecimal = caracterHexadecimal + hexadecimal;
            decimal = decimal / 16;
            
            
        }
        
        return hexadecimal.length();
    }
    
    public static  String decimalAHexadecimalString(long decimal, int numeroDeBits) {
       
        if (decimal<0){
            return decimalNegativoAHexadecimalString(decimal,numeroDeBits);
        }
        int residuo;
        String hexadecimal = "";
        char[] caracteresHexadecimales = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (decimal > 0) {
            residuo = (int) (decimal % 16);
            char caracterHexadecimal = caracteresHexadecimales[residuo];
            hexadecimal = caracterHexadecimal + hexadecimal;
            decimal = decimal / 16;
            
            
        }
        if(hexadecimal.length()<numeroDeBits){
            for (int i = hexadecimal.length(); i < numeroDeBits; i++) {
                    hexadecimal="0"+hexadecimal;
            }
        }
        if(hexadecimal.length()>numeroDeBits){
            hexadecimal=hexadecimal.substring((hexadecimal.length()-numeroDeBits), numeroDeBits+1);
        }
        
        return hexadecimal;
    }
    
    
    public static  String decimalNegativoAHexadecimalString(long decimal, int numeroDeBits) {
        
        String hexadecimal = "";
        char[] caracteresHexadecimales = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        List<Integer> decimalNegativoEnCa2=decimalABinario(decimal,numeroDeBits);
        
        for (int i = decimalNegativoEnCa2.size()-1; i >=0; i=i-4) {
            int temp=0;
            int potencia = 0;
            for (int j = i; j > (i-4); j--) {
                temp=(int) (temp+(decimalNegativoEnCa2.get(j) * Math.pow(2, potencia)));
                potencia++;
            }
            hexadecimal = caracteresHexadecimales[temp] + hexadecimal;  
        }
        if(hexadecimal.length()<numeroDeBits){
            for (int i = hexadecimal.length(); i < numeroDeBits; i++) {
                    hexadecimal="F"+hexadecimal;    
            }
        }
        return hexadecimal;
    }
    
    public static  List<Integer> decimalABinario(long decimal, int numeroDeBits) {
        
        Boolean flag = false;
        numeroDeBits=numeroDeBits*4;
        List<Integer> resultado = new ArrayList();
        if (decimal <0){
            flag = true;
            decimal = decimal*(-1);
        }
        while (decimal > 0) {
            long binario= decimal % 2;
            resultado.add((int)binario);
            decimal = decimal / 2;
        }
        if (numeroDeBits%4!=0){
            for (int i = 0; i < (numeroDeBits%4); i++) {
                numeroDeBits++;
            }
        }
        if(resultado.size()<numeroDeBits){
            for (int i = resultado.size(); i < numeroDeBits; i++) {
                resultado.add(0);
            }
        }
        
        if (flag){
            for (int i = 0; i < resultado.size(); i++) {
                resultado.set(i, resultado.get(i)==0?1:0);
            }
            if(resultado.get(0)==0){
                resultado.set(0, 1);
            }else{
                int i=0;
                boolean encontrado=false;               
                while(!encontrado && i<resultado.size()){
                    if (resultado.get(i)==0){
                        resultado.set(i, resultado.get(i)==0?1:0);
                        encontrado=true;
                    }else{
                        resultado.set(i, resultado.get(i)==0?1:0);
                        i++;
                    }    
                }
                
            }
        }
        List<Integer> resultadoOrdenado = new ArrayList();
        for (int i = resultado.size()-1; i >= 0; i--) {
            resultadoOrdenado.add(resultado.get(i));
        }
        return resultadoOrdenado;
       
    }
    
    public static long binarioADecimal(String binario) {
        long decimal = 0;
        
        // Ciclo infinito hasta que binario sea 0
        int potencia = 0;
        for (int i = binario.length()-1; i >=0; i--) {
            long temp = (long) (Integer.valueOf(binario.substring(i, i+1)) * Math.pow(2, potencia));
            decimal += temp;
            potencia++;
        }
        return decimal;
    }
    
    public static long binarioADecimal(List<Integer> binario) {
        long decimal = 0;
        
        // Ciclo infinito hasta que binario sea 0
        int potencia = 0;
        for (int i = binario.size()-1; i >=0; i--) {
            long temp = (long) (binario.get(i) * Math.pow(2, potencia));
            decimal += temp;
            potencia++;
        }
        return decimal;
    }
    
    public static int binarioADecimal(int binario) {
        int decimal = 0;
        int potencia = 0;
        // Ciclo infinito hasta que binario sea 0
        while (true) {
            if (binario == 0) {
                break;
            } else {
                int temp = binario % 10;
                decimal += temp * Math.pow(2, potencia);
                binario = binario / 10;
                potencia++;
            }
        }
        return decimal;
    }
    
}
