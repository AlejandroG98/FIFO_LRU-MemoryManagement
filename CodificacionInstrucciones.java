package com.mycompany.estructura;

import static com.mycompany.estructura.InstruccionesEnum.*;
import java.util.ArrayList;
import java.util.List;

public class CodificacionInstrucciones {
    
    public static int bitsPorHexadecimal=4;
    
    //public static int tamanoValorInstruccion=8;// 1 bytes
    //public static int tamanoValorInstruccion=16;// 2 bytes
    public static int tamanoValorInstruccion=32;// 4 bytes
    //public static int tamanoValorInstruccion=64;// 8 bytes
    
    public static int tamanoRelativoValorInstruccion=16;// 4 bytes
    
    public static int tamanoPorInstruccion=8;// 1 bytes
    //public static int tamanoPorInstruccion=16;// 2 bytes
    //public static int tamanoPorInstruccion=32;// 4 bytes
    //public static int tamanoPorInstruccion=64;// 8 bytes
    
    //public static int tamanodireccion=10;// 1 bytes
    public static int tamanodireccion=16;// 2 bytes
    //public static int tamanodireccion=32;// 4 bytes
    //public static int tamanodireccion=64;// 8 bytes
    
    public static int numeroDeBits=8;// 2 bytes
    public static int numeroDeBitsDesplazamiento=4;// 2 bytes
    public static String modoSistema="little-endian";  // ELEGIR UNO DE LOS DOS MODOS DE SISTEMAS DE ALMACENAMIENTO.
    //public static String modoSistema="big-endian";    // ELEGIR UNO DE LOS DOS MODOS DE SISTEMAS DE ALMACENAMIENTO.
    
    public static String direccionInicial="00010000h";
    
    public static String[] codificacionOperando=new String[tamanodireccion];
    
    public static List <Instruccion> instruccionesACodificar=new ArrayList();
    
    
    
    public static void main(String[] args) {
          

   //EJEMPLO 8.
        direccionInicial="000BBB00h";
        String A="[00FFEE00h]";
        
        instruccionesACodificar.add(new Instruccion(MUL,"R1",A,"E1"));
        instruccionesACodificar.add(new Instruccion(ADD,"R1","[R1]"));
        instruccionesACodificar.add(new Instruccion(XOR,"[R1]","00FFH"));
        instruccionesACodificar.add(new Instruccion(JNE,"E1"));
        instruccionesACodificar.add(new Instruccion(RET,"","",""));
        
        
// OJO CON LOS DESPLAZAMIENTOS, TIENEN QUE SER DE 4 CIFRAS Y NO DE OCHO AL IGUAL QUE EL ÍNIDCE //



        codificar(instruccionesACodificar);
        recalcularMemoria(instruccionesACodificar);
        
        System.out.print("╔════════════════╤════════════════╤══════════════════════════════════════════╤");
                            
        for (int i = 0; i < codificacionOperando.length-1; i++) {
            System.out.print("═════╤");
        }
        System.out.println("═════╗");
        System.out.print("║ DIRECCIÓN      │ ETIQUETA       │ ENSAMBLADOR                              │");
        for (int i = 0; i < codificacionOperando.length-1; i++) {
            System.out.print(" B"+(String.valueOf(i)+"  ") .substring(0, 2)+" │");
        }
        System.out.println(" B"+(String.valueOf(tamanodireccion-1)+"  ") .substring(0, 2)+" ║");
      
        System.out.print("╠════════════════╪════════════════╪══════════════════════════════════════════╪");
        for (int i = 0; i < codificacionOperando.length-1; i++) {
            System.out.print("═════╪");
        }
        System.out.println("═════╣");
        int contador=0;
        for (Instruccion instrucciones:instruccionesACodificar) {
            System.out.print("║ ");
            
            String palabra=(instrucciones.getDireccionMemoria()!=null?(formatoHexadeximal(instrucciones.getDireccionMemoria())+"h               ").substring(0, 14)+ " │ ":"               │ ");        
            palabra=(instrucciones.getEtiqueta()!=null?palabra+(instrucciones.getEtiqueta()+"                ").substring(0, 14)+ " │ ":palabra+"               │ ");  
            
            String palabraInstruccion=(instrucciones.getInstruccion()!=null?instrucciones.getInstruccion().getNombreInstruccion().trim().toUpperCase():""); 
            palabraInstruccion=(instrucciones.getPrimerOperando()!=null?palabraInstruccion+" "+instrucciones.getPrimerOperando().trim():palabraInstruccion);
            palabraInstruccion=(instrucciones.getSegundoOperando()!=null && !instrucciones.getSegundoOperando().isEmpty()?palabraInstruccion+" , "+instrucciones.getSegundoOperando().trim():palabraInstruccion);
            palabraInstruccion=(palabraInstruccion+"                                        ").substring(0, 40);
            palabra=palabra+palabraInstruccion+"                                                                                                                 ";
            
            System.out.print(palabra.substring(0, 74)+" │");
            for (int i = 0; i < instrucciones.getCodificacionOperando().length-1; i++) {
                if(instrucciones.getCodificacionOperando()[i]==null){
                    System.out.print("     │");
                }else {
                    System.out.print(" "+(instrucciones.getCodificacionOperando()[i]+"  ").substring(0, 3)+" │");
                }    
            }
            if(instrucciones.getCodificacionOperando()[instrucciones.getCodificacionOperando().length-1]==null){
                System.out.print("     ║ ");
            }else {
                System.out.print(" "+(instrucciones.getCodificacionOperando()[instrucciones.getCodificacionOperando().length-1]+"  ").substring(0, 3)+" ║ ");
            } 
            if (instrucciones.getComentario()!=null){
                System.out.println(instrucciones.getComentario());
            }else{
                System.out.println("");
            }
            contador++;
            if (contador<instruccionesACodificar.size()){
                System.out.print("╠════════════════╪════════════════╪══════════════════════════════════════════╪");
                for (int i = 0; i < codificacionOperando.length-1; i++) {
                    System.out.print("═════╪");
                }
                System.out.println("═════╣");
            }    
            
        }
        System.out.print("╚════════════════╧════════════════╧══════════════════════════════════════════╧");
        for (int i = 0; i < codificacionOperando.length-1; i++) {
            System.out.print("═════╧");
        }
        System.out.println("═════╝");
        
    }
    
    public static void recalcularMemoria(List <Instruccion> instruccionesACodificar ){
        for (int i = 0; i < instruccionesACodificar.size(); i++) {
        
            if (instruccionesACodificar.get(i).getFlag()!=null){
                int buscarInicio=0;
                boolean encontrado=false;
                while (buscarInicio<instruccionesACodificar.size() && !encontrado){
                    if (instruccionesACodificar.get(buscarInicio).getEtiqueta()!=null && !instruccionesACodificar.get(buscarInicio).getEtiqueta().isBlank() && instruccionesACodificar.get(buscarInicio).getEtiqueta().trim().equalsIgnoreCase(instruccionesACodificar.get(i).getFlag())){
                        String nuevaDireccion=instruccionesACodificar.get(buscarInicio).getDireccionMemoria();
                        String actualDireccion=instruccionesACodificar.get(i+1).getDireccionMemoria();
                        String[] valoresCodificacionOperando=instruccionesACodificar.get(i).getCodificacionOperando();
                        if (instruccionesACodificar.get(i).getInstruccion().equals(JMP)){
                            int empezarEn=2;
                            for (int j =nuevaDireccion.length()-1;j>=0; j=j-2) {
                                valoresCodificacionOperando[empezarEn]=nuevaDireccion.substring(j-1, j+1)+"h";
                                empezarEn++;
                            }
                            instruccionesACodificar.get(i).setCodificacionOperando(valoresCodificacionOperando);
                        } else{
                            if(instruccionesACodificar.get(i).getInstruccion().equals(JE) 
                            || instruccionesACodificar.get(i).getInstruccion().equals(JNE)
                            || instruccionesACodificar.get(i).getInstruccion().equals(JL)
                            || instruccionesACodificar.get(i).getInstruccion().equals(JLE)
                            || instruccionesACodificar.get(i).getInstruccion().equals(JG)
                            || instruccionesACodificar.get(i).getInstruccion().equals(JGE)
                            ){
                                int calculo=0;
//                                if (hexadecimalADecimal(nuevaDireccion)<hexadecimalADecimal(actualDireccion)){
//                                    calculo=hexadecimalADecimal(actualDireccion)-hexadecimalADecimal(nuevaDireccion);
//                                }else{
                                     
                                    calculo=hexadecimalADecimal(nuevaDireccion)-hexadecimalADecimal(actualDireccion);
                                    
//                                }
                                
                                int empezarEn=2;
                                
                                nuevaDireccion=decimalAHexadecimalStringDesplazamiento(calculo);
                                
                                
                                if (nuevaDireccion.length()<bitsPorHexadecimal){
                                    for (int numOp = nuevaDireccion.length(); numOp < bitsPorHexadecimal; numOp++) {
                                        nuevaDireccion="0"+nuevaDireccion;
                                    }
                                }
                                int logitud=nuevaDireccion.length()*bitsPorHexadecimal;
                                if (logitud %tamanoRelativoValorInstruccion!=0){
                                    for (int numOp = 0; numOp < (logitud %tamanoRelativoValorInstruccion)-1; numOp++) {
                                        nuevaDireccion="0"+nuevaDireccion;
                                    }
                                }                       
                                for (int j =nuevaDireccion.length()-1;j>=0; j=j-2) {
                                    valoresCodificacionOperando[empezarEn]=nuevaDireccion.substring(j-1, j+1)+"h";
                                    empezarEn++;
                                }
                                instruccionesACodificar.get(i).setCodificacionOperando(valoresCodificacionOperando);
                            }
                        }
                        encontrado=true;
                    }else {
                        buscarInicio++;
                    }    
                }
                
            }
        }
    }
    
    
    public static void codificar(List <Instruccion> instruccionesACodificar ){
         
        String direccionMemoria=direccionInicial.replace("h", "").replace("H", "");
        
        for (Instruccion instrucciones:instruccionesACodificar) {
            
            String[] valoresCodificacionOperando=new String[tamanodireccion];
            valoresCodificacionOperando[0]=instrucciones.getInstruccion().getValorHexadecimal();
            int posicion=1;
            String operando="";
            
            for (int op = 0; op < 2; op++) {
                if (op==0){
                    operando=instrucciones.getPrimerOperando();
                }else{
                    if(instrucciones.getInstruccion().equals(JE) 
                            || instrucciones.getInstruccion().equals(JNE)
                            || instrucciones.getInstruccion().equals(JL)
                            || instrucciones.getInstruccion().equals(JLE)
                            || instrucciones.getInstruccion().equals(JG)
                            || instrucciones.getInstruccion().equals(JGE)
                            ){
                        instrucciones.setSegundoOperando("");
                    }
                    operando=instrucciones.getSegundoOperando();
                }
                
                if (operando!=null && !operando.isBlank()){
                   
                    // FUENTE O ETIQUETA1 es INMEDIATO ==> 12345678
                    if(!operando.startsWith("r") && !operando.startsWith("R") && !operando.startsWith("[") && !operando.contains("+")){
                        valoresCodificacionOperando[posicion]=INMEDIATO.getValorHexadecimal()+"0h";
                        posicion++;
                        String valorRegistro=operando.replace("O", "0").replace(" ", "");
                        if (valorRegistro.endsWith("h") || valorRegistro.endsWith("H")){
                            valorRegistro=valorRegistro.replace("H", "").replace("h", "").replace("-", "");
                        }else{
                            if (valorRegistro.endsWith("b")){
                                valorRegistro=valorRegistro.replace("b", "");
                                if (valorRegistro.matches("[0-1]*")){
                                   valorRegistro=decimalAHexadecimalString((int) binarioADecimal(valorRegistro));
                                }
                            } else{
                                if (valorRegistro.matches("[0-9]*") || valorRegistro.contains("-")){
                                    valorRegistro=decimalAHexadecimalString(Integer.valueOf(valorRegistro));
                                } else{
                                    if(instrucciones.getInstruccion().equals(JE) 
                                            || instrucciones.getInstruccion().equals(JNE)
                                            || instrucciones.getInstruccion().equals(JL)
                                            || instrucciones.getInstruccion().equals(JLE)
                                            || instrucciones.getInstruccion().equals(JG)
                                            || instrucciones.getInstruccion().equals(JGE)
                                            ){
                                        valorRegistro="0000";
                                    } else{
                                        valorRegistro="00000000";
                                    }
                                    instrucciones.setFlag(operando);
                                    instrucciones.setComentario(" "+operando+" <== UNO DE LOS OPERANDOS ES UNA VARIABLE O UNA ETIQUETA O UNA EXPRESIÓN. COMPRORBAR CÁLCULOS");
                                }
                            }
                        }
                        if (valorRegistro.length()<bitsPorHexadecimal){
                            for (int i = valorRegistro.length(); i < bitsPorHexadecimal; i++) {
                                valorRegistro="0"+valorRegistro;
                            }
                        }
                        int logitud=valorRegistro.length()*bitsPorHexadecimal;
                        if (logitud %tamanoRelativoValorInstruccion!=0){
                            for (int i = 0; i < (logitud %tamanoRelativoValorInstruccion)-1; i++) {
                                valorRegistro="0"+valorRegistro;
                            }
                        }                       
                        for (int i =valorRegistro.length()-1;i>=0; i=i-2) {
                            valoresCodificacionOperando[posicion]=valorRegistro.substring(i-1, i+1)+"h";
                            posicion++;
                        }
                        if(instrucciones.getInstruccion().equals(JE) 
                            || instrucciones.getInstruccion().equals(JNE)
                            || instrucciones.getInstruccion().equals(JL)
                            || instrucciones.getInstruccion().equals(JLE)
                            || instrucciones.getInstruccion().equals(JG)
                            || instrucciones.getInstruccion().equals(JGE)
                            ){
                            if (logitud<tamanoValorInstruccion){
                                for (int i = 0; i < ((tamanoRelativoValorInstruccion-logitud)/tamanoRelativoValorInstruccion); i++) {
                                    valoresCodificacionOperando[posicion]="00h";
                                    posicion++;
                                }
                            }
                        } else{
                            if (logitud<tamanoValorInstruccion){
                                for (int i = 0; i < ((tamanoValorInstruccion-logitud)/tamanoPorInstruccion); i++) {
                                    valoresCodificacionOperando[posicion]="00h";
                                    posicion++;
                                }
                            }
                        }    
                    }

                    // FUENTE O ETIQUETA1 es DIRECCIONAMIENTO DIRECTO A REGISTRO ==> R1
                    if(operando.startsWith("r") || operando.startsWith("R")){
                        String nombreRegistro=operando.replace("r", "").replace("R","");
                        valoresCodificacionOperando[posicion]=REGISTRO.getValorHexadecimal()+String.valueOf(decimalAHexadecimal(Integer.valueOf(nombreRegistro)).get(0))+"h";
                        posicion++;
                    }

                    // FUENTE O ETIQUETA1 es DIRECCIONAMIENTO DIRECTO A MEMERIA ==> [00AB 01E0 h]
                    if( !operando.startsWith("[r") && !operando.startsWith("[R") && !operando.contains("+") && operando.startsWith("[")){
                        valoresCodificacionOperando[posicion]=MEMORIA.getValorHexadecimal()+"0h";
                        posicion++;

                        String valorRegistro=operando.replace("O", "0").replace(" ", "").replace("]", "").replace("[", "");
                        if (valorRegistro.endsWith("h") || valorRegistro.endsWith("H")){
                            valorRegistro=valorRegistro.replace("H", "").replace("h", "").replace("-", "");
                        }else{

                            if (valorRegistro.endsWith("b")){
                                valorRegistro=valorRegistro.replace("b", "");
                                if (valorRegistro.matches("[0-1]*")){
                                   valorRegistro=decimalAHexadecimalString((int) binarioADecimal(valorRegistro));
                                }
                            } else{
                                if (valorRegistro.matches("[0-9]*") || valorRegistro.contains("-")){
                                    valorRegistro=decimalAHexadecimalString(Integer.valueOf(valorRegistro));
                                } else{
                                    if(instrucciones.getInstruccion().equals(JE) 
                                            || instrucciones.getInstruccion().equals(JNE)
                                            || instrucciones.getInstruccion().equals(JL)
                                            || instrucciones.getInstruccion().equals(JLE)
                                            || instrucciones.getInstruccion().equals(JG)
                                            || instrucciones.getInstruccion().equals(JGE)
                                            ){
                                        valorRegistro="0000";

                                    } else{
                                        valorRegistro="00000000";
                                    }
                                    instrucciones.setComentario(" "+operando+" <== UNO DE LOS OPERANDOS ES UNA VARIABLE O UNA ETIQUETA O UNA EXPRESIÓN. COMPROBAR CÁLCULOS");
                                    instrucciones.setFlag(operando);
                                }
                            }
                        }
                        if (valorRegistro.length()<bitsPorHexadecimal){
                            for (int i = valorRegistro.length(); i < bitsPorHexadecimal; i++) {
                                valorRegistro="0"+valorRegistro;
                            }
                        }
                        int logitud=valorRegistro.length()*bitsPorHexadecimal; 
                         if (logitud %tamanoRelativoValorInstruccion!=0){
                            for (int i = 0; i < (logitud %tamanoRelativoValorInstruccion)-1; i++) {
                                valorRegistro="0"+valorRegistro;
                            }
                        }
                        for (int i =valorRegistro.length()-1;i>=0; i=i-2) {
                            valoresCodificacionOperando[posicion]=valorRegistro.substring(i-1, i+1)+"h";
                            posicion++;
                        }
                        if(instrucciones.getInstruccion().equals(JE) 
                            || instrucciones.getInstruccion().equals(JNE)
                            || instrucciones.getInstruccion().equals(JL)
                            || instrucciones.getInstruccion().equals(JLE)
                            || instrucciones.getInstruccion().equals(JG)
                            || instrucciones.getInstruccion().equals(JGE)
                            ){
                            if (logitud<tamanoValorInstruccion){
                                for (int i = 0; i < ((tamanoRelativoValorInstruccion-logitud)/tamanoRelativoValorInstruccion); i++) {
                                    valoresCodificacionOperando[posicion]="00h";
                                    posicion++;
                                }
                            }
                        } else{
                            if (logitud<tamanoValorInstruccion){
                                for (int i = 0; i < ((tamanoValorInstruccion-logitud)/tamanoPorInstruccion); i++) {
                                    valoresCodificacionOperando[posicion]="00h";
                                    posicion++;
                                }
                            }
                        }
                    }

                    // FUENTE O ETIQUETA1 es DIRECCIONAMIENTO INDIRECTO A REGISTRO ==> [R1]
                    if((operando.startsWith("[r") || operando.startsWith("[R"))  && !operando.contains("+")){
                        String nombreRegistro=operando.replace("r", "").replace("R","").replace("r", "").replace("]","").replace("[", "").replace(" ","");
                        valoresCodificacionOperando[posicion]=INDIRECTO.getValorHexadecimal()+String.valueOf(decimalAHexadecimal(Integer.valueOf(nombreRegistro)).get(0))+"h";
                        posicion++;
                    }

                    // FUENTE O ETIQUETA1 es DIRECCIONAMIENTO RELATIVO A REGSITRO BASE ==> [REGISTRO + desplazamiento]
                    if (operando.contains("+") && operando.contains("[") ){
                        String valorRegistro=operando.replace("[", "").replace("]", "").replace("O", "0").replace(" ", "");
                        String[] parts = valorRegistro.split("\\+");
                       if (parts[0].startsWith("R") || parts[0].startsWith("r") ){
                            String nombreRegistro=parts[0].replace("r", "").replace("R","").replace("r", "").replace("]","").replace("[", "").replace(" ","");
                            valoresCodificacionOperando[posicion]=RELATIVO.getValorHexadecimal()+String.valueOf(decimalAHexadecimal(Integer.valueOf(nombreRegistro)).get(0))+"h";
                            posicion++;
                            String desplazamiento=parts[1];
                            if (desplazamiento.endsWith("h") || desplazamiento.endsWith("H")){
                                desplazamiento=desplazamiento.replace("H", "").replace("h", "").replace("-", "");
                            }else{
                                if (desplazamiento.endsWith("b")){
                                    desplazamiento=desplazamiento.replace("b", "");
                                    if (desplazamiento.matches("[0-1]*")){
                                       desplazamiento=decimalAHexadecimalStringDesplazamiento((int) binarioADecimal(desplazamiento));
                                    }
                                } else{
                                    if (desplazamiento.matches("[0-9]*") || desplazamiento.contains("-")){
                                        desplazamiento=decimalAHexadecimalStringDesplazamiento(Integer.valueOf(desplazamiento));
                                    } else{
                                        desplazamiento="0000";
                                        instrucciones.setComentario(" "+operando+" <== DESPLAZAMIENTO ES UNA VARIABLE O UNA ETIQUETA O UNA EXPRESIÓN. COMPRORBAR CÁLCULOS");
                                    }
                                }
                            }
                            if (valorRegistro.length()<bitsPorHexadecimal){
                                for (int i = valorRegistro.length(); i < bitsPorHexadecimal; i++) {
                                    valorRegistro="0"+valorRegistro;
                                }
                            }
                            int logitud=desplazamiento.length()*bitsPorHexadecimal; 
                                if (logitud %tamanoRelativoValorInstruccion!=0){
                                for (int i = 0; i < (logitud %tamanoRelativoValorInstruccion)-1; i++) {
                                    valorRegistro="0"+valorRegistro;
                                }
                            }
                            for (int i =desplazamiento.length()-1;i>=0; i=i-2) {
                                valoresCodificacionOperando[posicion]=desplazamiento.substring(i-1, i+1)+"h";
                                posicion++;
                            }
                            if (logitud<(tamanoRelativoValorInstruccion)){
                                for (int i = 0; i < ((tamanoRelativoValorInstruccion-logitud)/tamanoPorInstruccion); i++) {
                                    valoresCodificacionOperando[posicion]="00h";
                                     posicion++;
                                }
                            }
                        } else{
                           // FUENTE O ETIQUETA1 es DIRECCIONAMIENTO RELATIVO A REGISTRO INDICE ==> [DIRECCIÓN + registro]
                            if (parts[1].startsWith("R") || parts[1].startsWith("r") ){
                                String nombreRegistro=parts[1].replace("r", "").replace("R","").replace("r", "").replace("]","").replace("[", "").replace(" ","");
                                valoresCodificacionOperando[posicion]=INDEXADO.getValorHexadecimal()+String.valueOf(decimalAHexadecimal(Integer.valueOf(nombreRegistro)).get(0))+"h";
                                posicion++;
                                String desplazamiento=parts[0];
                                if (desplazamiento.endsWith("h") || desplazamiento.endsWith("H")){
                                    desplazamiento=desplazamiento.replace("H", "").replace("h", "").replace("-", "");
                                }else{

                                    if (desplazamiento.endsWith("b")){
                                        desplazamiento=desplazamiento.replace("b", "");
                                        if (desplazamiento.matches("[0-1]*")){
                                           desplazamiento=decimalAHexadecimalString((int) binarioADecimal(desplazamiento));
                                        }
                                    } else{
                                        if (desplazamiento.matches("[0-9]*") || desplazamiento.contains("-")){
                                            desplazamiento=decimalAHexadecimalString(Integer.valueOf(desplazamiento));
                                        } else{
                                            desplazamiento="00000000";
                                            instrucciones.setComentario(" "+operando+" <== INDICE ES UNA VARIABLE O UNA ETIQUETA O UNA EXPRESIÓN. COMPRORBAR CÁLCULOS");
                                        }
                                    }
                                }
                                if (valorRegistro.length()<bitsPorHexadecimal){
                                    for (int i = valorRegistro.length(); i < bitsPorHexadecimal; i++) {
                                        valorRegistro="0"+valorRegistro;
                                    }
                                }
                                int logitud=desplazamiento.length()*bitsPorHexadecimal; 
                                if (logitud %tamanoRelativoValorInstruccion!=0){
                                for (int i = 0; i < (logitud %tamanoRelativoValorInstruccion)-1; i++) {
                                    valorRegistro="0"+valorRegistro;
                                    }
                                }
                                for (int i =desplazamiento.length()-1;i>=0; i=i-2) {
                                    valoresCodificacionOperando[posicion]=desplazamiento.substring(i-1, i+1)+"h";
                                    posicion++;
                                }
                                if (logitud<(tamanoValorInstruccion)){
                                    for (int i = 0; i < ((tamanoValorInstruccion-logitud)/tamanoPorInstruccion); i++) {
                                        valoresCodificacionOperando[posicion]="00h";
                                         posicion++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // INSTRUCCIONES RELATICO A PC
            if(instrucciones.getInstruccion().equals(JE) 
                    || instrucciones.getInstruccion().equals(JNE)
                    || instrucciones.getInstruccion().equals(JL)
                    || instrucciones.getInstruccion().equals(JLE)
                    || instrucciones.getInstruccion().equals(JG)
                    || instrucciones.getInstruccion().equals(JGE)
                    ){
                valoresCodificacionOperando[1]="60h";
                
            }
           
            instrucciones.setCodificacionOperando(valoresCodificacionOperando);
            instrucciones.setDireccionMemoria(direccionMemoria);
            direccionMemoria=decimalAHexadecimalString((hexadecimalADecimal(direccionMemoria)+posicion));
        }
        
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
    
     public static int hexadecimalADecimal(String hexadecimal) {
        
        hexadecimal=hexadecimal.replaceAll("\\[", "").replaceAll("\\]","").replaceAll("h","").replaceAll(" ","").replaceAll("-","").replaceAll("/","").replaceAll("H","");
        String caracteresHexadecimales = "0123456789ABCDEF";
        hexadecimal = hexadecimal.toUpperCase();
        int decimal = 0;
        for (int i = 0; i < hexadecimal.length(); i++) {
            char caracter = hexadecimal.charAt(i);
            int posicionEnCaracteres = caracteresHexadecimales.indexOf(caracter);
            decimal = 16 * decimal + posicionEnCaracteres;
        }
        return decimal;
    }
     
    
    public static  List<Integer>  decimalABinario(int decimal) {
        
        Boolean flag = false;
        List<Integer> resultado = new ArrayList();
        if (decimal <0){
            flag = true;
            decimal = decimal*(-1);
        }
        while (decimal > 0) {
            int binario= decimal % 2;
            resultado.add(binario);
            decimal = decimal / 2;
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
    
    public static  List<Integer>  decimalABinarioDesplazamiento(int decimal) {
        
        
        Boolean flag = false;
        List<Integer> resultado = new ArrayList();
        if (decimal <0){
            flag = true;
            decimal = decimal*(-1);
        }
        while (decimal > 0) {
            int binario= decimal % 2;
            resultado.add(binario);
            decimal = decimal / 2;
        }
        if(resultado.size()<numeroDeBitsDesplazamiento*2){
            for (int i = resultado.size(); i < numeroDeBitsDesplazamiento*2; i++) {
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
    
    public static  String decimalAHexadecimalString(int decimal) {
        if (decimal<0){
            Long valor=binarioADecimal(decimalABinario(Integer.valueOf(decimal)));
            return decimalLongAHexadecimalString(valor);
        }
        int residuo;
        String hexadecimal = "";
        char[] caracteresHexadecimales = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (decimal > 0) {
            residuo = decimal % 16;
            char caracterHexadecimal = caracteresHexadecimales[residuo];
            hexadecimal = caracterHexadecimal + hexadecimal;
            decimal = decimal / 16;
        }
       if(hexadecimal.length()<numeroDeBits){
            for (int i = hexadecimal.length(); i < numeroDeBits; i++) {
                hexadecimal="0"+hexadecimal;
            }
        }
        
        return hexadecimal;
    }
    
    
    public static  String decimalLongAHexadecimalString(long decimal) {
        long residuo;
        String hexadecimal = "";
        char[] caracteresHexadecimales = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (decimal > 0) {
            residuo = decimal % 16;
            int help = (int)residuo;
            char caracterHexadecimal = caracteresHexadecimales[help];
            hexadecimal = caracterHexadecimal + hexadecimal;
            decimal = decimal / 16;
        }
       if(hexadecimal.length()<numeroDeBits){
            for (int i = hexadecimal.length(); i < numeroDeBits; i++) {
                hexadecimal="F"+hexadecimal;
            }
        }
        
        return hexadecimal;
    }
    
     public static  String decimalLongAHexadecimalStringDesplazamiento(long decimal) {
        long residuo;
        String hexadecimal = "";
        char[] caracteresHexadecimales = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (decimal > 0) {
            residuo = decimal % 16;
            int help = (int)residuo;
            char caracterHexadecimal = caracteresHexadecimales[help];
            hexadecimal = caracterHexadecimal + hexadecimal;
            decimal = decimal / 16;
        }
       if(hexadecimal.length()<numeroDeBitsDesplazamiento){
            for (int i = hexadecimal.length(); i < numeroDeBitsDesplazamiento; i++) {
                hexadecimal="F"+hexadecimal;
            }
        }
        
        return hexadecimal;
    }
    
    public static  String decimalAHexadecimalStringDesplazamiento(int decimal) {
        
        if (decimal<0){
            Long valor=binarioADecimal(decimalABinarioDesplazamiento(Integer.valueOf(decimal)));
            return decimalLongAHexadecimalStringDesplazamiento(valor);
        }
        int residuo;
        String hexadecimal = "";
        char[] caracteresHexadecimales = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (decimal > 0) {
            residuo = decimal % 16;
            char caracterHexadecimal = caracteresHexadecimales[residuo];
            hexadecimal = caracterHexadecimal + hexadecimal;
            decimal = decimal / 16;
        }
       if(hexadecimal.length()<numeroDeBitsDesplazamiento){
            for (int i = hexadecimal.length(); i < numeroDeBitsDesplazamiento; i++) {
                hexadecimal="0"+hexadecimal;
            }
        }
        return hexadecimal;
    }

    public static  List<Character> decimalAHexadecimal(int decimal) {
        int residuo;
        List<Character> resultado = new ArrayList();
        String hexadecimal = "";
        if (decimal<0){
            
        }
        char[] caracteresHexadecimales = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (decimal > 0) {
            residuo = decimal % 16;
            char caracterHexadecimal = caracteresHexadecimales[residuo];
            resultado.add(caracterHexadecimal);
            hexadecimal = caracterHexadecimal + hexadecimal;
            decimal = decimal / 16;
        }
        if(resultado.size()<numeroDeBits){
            for (int i = resultado.size(); i < numeroDeBits; i++) {
                resultado.add('0');
            }
        }
        
        return resultado;
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
