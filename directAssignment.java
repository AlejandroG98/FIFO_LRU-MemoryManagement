package com.mycompany.estructura;

public class directAssignment {
    
    public static void main(String[] args) {
        
        /* Número de líneas de la memoria caché */
        int numLineCache=4;
        /* Número de palabras de la memoria caché */
        int numWords=8;
        
        
         /* Variables auxiliares */
        int block;              // Bloque
        int label;              // Etiqueta
        int line;               // Línea
        int numFailures=0;      // Número de fallos
        
        /* Memoria caché tendrá un número de líneas(numLineCache ) y en cada línea tendrá un número de palabras(numWords) */
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
        for (int i = 0;  i< numLineCache; i++) {
            for (int j = 0; j < numWords; j++) {
                wordsInCache[i][j]=contador++;   // VALOR INICIAL DE LA MEMORIA CACHÉ, EN ESTE CASO ES DESDE O HASTA 31
                                                 // SI ES VACÍA, CAMBIAR EL VALOR DE contador++ por -1
                                                 // OTROS VALORES, ASIGNARLOS MANUALMENTE wordsInCache[0][0]=0, .... 
            }
            
            /* Calculamos el Bloque INICIAL que pertencece la dirección de los valores de la nueva de memoria */
            /* EL BLOQUE SERÁ b=a div 2^k ==> (b=0 div numWords ==> bloque 0) (b=8 div numWords ==> bloque 1) .. es la parte entera  */
            block=wordsInCache[i][0]/numWords;
            
            /* Calculamos la etiqueta que tiene la dirección nueva de memoria */
            /* ETIQUETA ES LA PARTE ENTERA DE LA DIVISIÓN DEL BLOQUE ENTRE LA LÍNEA DE CACHÉ A LA QUE PERTENCECE. INICIALMENTE SERÁ SIEMPRE CERO O VACÍO */
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
        int directcionTocCheck[]={25, 26, 27, 22, 18, 7, 9, 2, 53, 35, 22, 23, 63, 31, 36, 23, 28, 15, 44, 38};
        
        for (int directcionTocChecks : directcionTocCheck){
        
            /* Calculamos el Bloque que pertencece la dirección a la que accedemos */
            block=directcionTocChecks/numWords;
            /* Calculamos la Línea de memoira Caché que pertenece la dirección a la que accedemos */
            line = block%numLineCache;
            
            /* Compprobamos si existe en memoria */
            boolean success=false;
            
            for (int i = 0; i < numLineCache; i++) {
                if (directcionTocChecks>=wordsInCache[i][0] && directcionTocChecks<=wordsInCache[i][numWords-1]){
                    success=true;
                }
            }
            
            if (success){
                /* Imprimimos el valor del acierto por pantalla */
                System.out.println("┌───────┬────┐");
                System.out.println("│ Linea │ "+(String.valueOf(directcionTocChecks)+"  ").substring(0, 2)+" │");
                System.out.println("├───────┼────┤");
                for (int i = 0;  i< numLineCache; i++) {
                    if (i==line){
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
           
                for (int i = 0; i < numWords; i++) {
                    wordsInCache[line][i]=block*numWords+i;
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
                        if (i==line){
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
        }
        
        /* Cálculo de aciertos, fallos y tasa media */
        float ta =6; // PONER EL VALOR CORRECTO
        float tf=26; // PONER EL VALOR CORRECTO
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
    

