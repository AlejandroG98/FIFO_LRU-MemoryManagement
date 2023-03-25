package com.mycompany.estructura;

public class LRUssignment {
    
    public static void main(String[] args) {
        
        /* Número de líneas de la memoria caché */
        int numLineCache=4;
        /* Número de palabras de la memoria caché */
        /* Lo habitual es de 32 bytes y 128 bytes 
        , en los ejercicios normalmente se usan 8 */
        int numWords=8;
        
         /* Variables auxiliares */
        int block;              // Bloque
        int label;              // Etiqueta
        int numFailures=0;      // Número de falloos
        int wordsInCache[][] = new int[numLineCache][numWords];
        
        
       
        /* Memoria caché con valores inicales e imprimimos por pantalla los valores */
        System.out.print("┌───────┬───────────");
        for (int i = 0; i < numWords-1; i++) {
            System.out.print("─────");
        }
        System.out.println("──────┐");       
        System.out.print("│ Linea │ Estado Inicial");
        for (int i = 0; i < numWords-2; i++) {
            System.out.print("     ");
        }
        System.out.println("       │");
        System.out.print("├───────┼───────────");
        for (int i = 0; i < numWords-1; i++) {
            System.out.print("─────");
        }
        System.out.println("──────┤");      
        int contador =0 ;
        /* SI TENEMOS QUE ASIGNAR DIFERENCTES VALORES PREVIAMENTE*/
//        wordsInCache[3][0]=56;
//        wordsInCache[3][1]=57;
//        wordsInCache[3][2]=58;
//        wordsInCache[3][3]=59;
//        wordsInCache[3][4]=60;
//        wordsInCache[3][5]=61;
//        wordsInCache[3][6]=62;
//        wordsInCache[3][7]=63;
        for (int i = 0;  i< numLineCache; i++) {
            for (int j = 0; j < numWords; j++) {
                if (wordsInCache[i][j]==0){
                    wordsInCache[i][j]=contador++;   // contador++ VALOR INICIAL DE LA MEMORIA CACHÉ, EN ESTE CASO ES DESDE O HASTA 31
                                                 // SI ES VACÍA, CAMBIAR EL VALOR DE contador++ por -1
                                                 // OTROS VALORES, ASIGNARLOS MANUALMENTE wordsInCache[0][0]=0, .... 
                }
            }    
            /* Calculamos el Bloque que pertencece la dirección nueva de memoria */
            block=wordsInCache[i][0]/numWords;
            /* Calculamos la etiqueta que tiene la dirección nueva de memoria */
            label = block/numLineCache;
            if (wordsInCache[i][0]==-1){
                System.out.print("│   "+(String.valueOf(i)+"  ").subSequence(0, 2)+"  │           ");
                for (int z = 0; z < numWords-1; z++) {
                    System.out.print("     ");
                }
                System.out.println("      │");
            } else {
                System.out.print("│   "+(String.valueOf(i)+"  ").subSequence(0, 2)+"  │"+" "+(String.valueOf(block)+"  ").subSequence(0, 2)+" : "+ (String.valueOf(label)+"  ").subSequence(0, 2)+" ( ");
                 for (int j = 0; j < numWords; j++) {
                    System.out.print((String.valueOf(wordsInCache[i][j]+"   ")).subSequence(0, 3));
                    if (j<numWords-1){
                        System.out.print(", ");
                    }
                }
                System.out.println(" ) │");
            }
            if (i<numLineCache-1){
                System.out.print("├───────┼───────────");
                for (int z = 0; z < numWords-1; z++) {
                    System.out.print("─────");
                }
                System.out.println("──────┤"); 
            } 
        }
        System.out.print("└───────┴───────────");
        for (int z = 0; z < numWords-1; z++) {
            System.out.print("─────");
        }
        System.out.println("──────┘");
        System.out.println();
       
        
        
        /* Accesos a las direcciones de memorias */
       
        int directcionTocCheck[]={12, 13, 25, 26, 17, 8, 22, 3, 24, 62, 5, 63, 64, 17, 18, 19, 57, 58, 20, 25};
        
        /* En los accesos a memoria por LRU añadimos un valor para indicar la línea que más tiempo lleva sin usarse */
       
        int vectorPosiciones[] = new int[directcionTocCheck.length+numLineCache];
        for (int i = 0; i < numLineCache; i++) {
            vectorPosiciones[i]=i;
        }
        for (int i = numLineCache; i < vectorPosiciones.length; i++) {
            vectorPosiciones[i]=-1;
        }
       
        int posicion=0;
        for (int directcionTocChecks : directcionTocCheck){
            
            /* Compprobamos si existe en memoria */
            boolean success=false;
            int lineToChange=0;
            
            /* En asignación asociativa LRU la dirección a comprobar puede estar en cualquier línea de la cache */
            for (int i = 0; i < numLineCache; i++) {
                if (directcionTocChecks>=wordsInCache[i][0] && directcionTocChecks<=wordsInCache[i][numWords-1]){
                    /* Si se produce un acierto la línea pasará a ser la primera leida y el resto retrocden una posición de lectura */
                    lineToChange=i;
                    success=true;
                    
                }
            }
            
            vectorPosiciones[posicion+numLineCache]=lineToChange;
            
            
            if (success){
                /* Imprimimos el valor del acierto por pantalla */
                System.out.println("┌───────┬────┐");
                System.out.println("│ Linea │ "+(String.valueOf(directcionTocChecks)+"  ").substring(0, 2)+" │");
                System.out.println("├───────┼────┤");
                for (int i = 0;  i< numLineCache; i++) {
                    if (i==lineToChange){
                        System.out.println("│   "+(String.valueOf(i)+"  ").subSequence(0, 1)+"   │ E  │");
                    }else{
                        System.out.println("│   "+(String.valueOf(i)+"  ").subSequence(0, 1)+"   │    │");
                    }
                    if (i<numLineCache-1){
                        System.out.println("├───────┼────┤");
                    }    
                }
                System.out.println("└───────┴────┘");
                System.out.println();
            }
            
            
            if (!success){
                /* Se porduce un fallo e incrementamos en uno el núemro de fallos. */
                numFailures++;
           
                /* Calculamos el Bloque que pertencece la dirección nueva de memoria */
                block=directcionTocChecks/numWords;
                lineToChange=block%numLineCache;;
                
                int buscamosPosicion=0;
                boolean encontrado=false;
                while (buscamosPosicion< posicion+numLineCache && !encontrado){
                    if (vectorPosiciones[buscamosPosicion]!=-1){
                        lineToChange=vectorPosiciones[buscamosPosicion];
                        encontrado=true;
                    }else 
                        buscamosPosicion++;
                }
                vectorPosiciones[posicion+numLineCache]=lineToChange;
                /* Cambiamos el valor de la línea con menos accesos donde se produce el fallo */
                for (int i = 0; i < numWords; i++) {
                    wordsInCache[lineToChange][i]=block*numWords+i;
                }
               
               /* Imprimimos el valor del fallo por pantalla */
                System.out.print("┌───────┬───────────");
                for (int i = 0; i < numWords-1; i++) {
                    System.out.print("─────");
                }
                System.out.println("──────┐"); 
                System.out.print("│ Linea │ Fallo :  "+(String.valueOf(directcionTocChecks)+"  ").substring(0, 2));
                for (int i = 0; i < numWords-2; i++) {
                    System.out.print("     ");
                }
                System.out.println("          │");
                System.out.print("├───────┼───────────");
                for (int i = 0; i < numWords-1; i++) {
                    System.out.print("─────");
                }
                System.out.println("──────┤");
                for (int i = 0;  i< numLineCache; i++) {
                     /* Calculamos el Bloque que pertencece la dirección a la que accedemos */
                    block=wordsInCache[i][0]/numWords;
                    /* Calculamos la etiqueta que tiene la dirección nueva de memoria */
                    label = block/numLineCache;
                
                    if (wordsInCache[i][0]==-1){
                        System.out.print("│   "+(String.valueOf(i)+"  ").subSequence(0, 2)+"  │           ");
                        for (int z = 0; z < numWords-2; z++) {
                          System.out.print("     ");
                        }
                        System.out.println("           │");
                    } else {
                        if (i==lineToChange){
                             System.out.print("│   "+(String.valueOf(i)+"  ").subSequence(0, 2)+"* │");
                             
                        }else {
                            System.out.print("│   "+(String.valueOf(i)+"  ").subSequence(0, 2)+"  │");
                        }
                        System.out.print(" "+(String.valueOf(block)+"  ").subSequence(0, 2)+" : "+ (String.valueOf(label)+"  ").subSequence(0, 2)+" ( ");
                        for (int j = 0; j < numWords; j++) {
                            System.out.print((String.valueOf(wordsInCache[i][j]+"   ")).subSequence(0, 3));
                            if (j<numWords-1){
                                System.out.print(", ");
                            }
                        }
                        System.out.println(" ) │");
                    }
                    if (i<numLineCache-1){
                        if (i<numLineCache-1){
                            System.out.print("├───────┼───────────");
                            for (int z = 0; z < numWords-1; z++) {
                                System.out.print("─────");
                            }
                            System.out.println("──────┤");
                        }  
                    }    
                }
                System.out.print("└───────┴───────────");
                for (int z = 0; z < numWords-1; z++) {
                    System.out.print("─────");
                }
                System.out.println("──────┘");
                System.out.println();
            }
            for (int i = 0; i < posicion+numLineCache; i++) {
                if (vectorPosiciones[i]==lineToChange){
                    vectorPosiciones[i]=-1;
                }
            }
            posicion++;
        }
        
        /* Cálculo de aciertos, fallos y tasa media */
        /* Cálculo de aciertos, fallos y tasa media */
        float ta =2; // PONER EL VALOR CORRECTO
        float tf=20; // PONER EL VALOR CORRECTO
        float Tf=(float)numFailures/(float)directcionTocCheck.length;
        float Ta=(float)(directcionTocCheck.length-numFailures)/(float)directcionTocCheck.length;
        System.out.println("┌────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│ Resultados                                                             │");
        System.out.println("├────────────────────────────────────────────────────────────────────────┤");   
        System.out.println("│ Acceso a memoria = "+(String.valueOf(directcionTocCheck.length)+"          ").subSequence(0,10)+"                                          │");
        System.out.println("│ Números de aciertos = "+(String.valueOf(directcionTocCheck.length-numFailures)+"          ").subSequence(0,10)+"                                       │");
        System.out.println("│ Números de fallos = "+(String.valueOf(numFailures)+"          ").subSequence(0,10)+"                                         │");
        System.out.println("│ Tasa de aciertos Ta= (accesos a memoria - fallos)/accesos a memoria    │");
        System.out.println("│ Tasa de aciertos Ta= números de aciertos/accesos a memoria             │");
        System.out.println("│ Tasa de aciertos Ta= ( "+(String.valueOf(directcionTocCheck.length)+ " accesos - "+String.valueOf(numFailures) +" fallos )/ "+String.valueOf(directcionTocCheck.length)+" accesos"+"                                              ").substring(0, 48)+"│");
        System.out.println("│ Tasa de aciertos Ta= "+(String.valueOf(Ta)+"          ").subSequence(0,10)+"                                        │");
        System.out.println("│ Tasa de fallos   Tf= (1- Ta)                                           │");
        System.out.println("│ Tasa de fallos   Tf= (1- "+(String.valueOf(Ta)+")          ").substring(0, 10)+"                                    │");
        System.out.println("│ Tasa de fallos   Tf= "+(String.valueOf(Tf)+"          ").subSequence(0,10)+"                                        │");
        System.out.println("│                  ta= "+(String.valueOf(ta)+"          ").subSequence(0,10)+"                                        │");
        System.out.println("│                  tf= "+(String.valueOf(tf)+"          ").subSequence(0,10)+"                                        │");
        System.out.println("│ tm = Ta x ta + (1 – Ta) x tf = Ta x ta + Tf x tf                       │");
        System.out.println("│ tm = "+(String.valueOf(Ta)+" x "+String.valueOf(ta)+" + "+String.valueOf(Tf)+" x "+String.valueOf(tf)+"                            ").substring(0, 40)+"                          │");
        System.out.println("│ tiempo medio de acceso tm = "+(String.valueOf(Ta*ta+(Tf*tf))+"          ").subSequence(0,10)+"                                 │");
        System.out.println("└────────────────────────────────────────────────────────────────────────┘");
    }
}
    

