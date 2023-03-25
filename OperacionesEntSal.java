package com.mycompany.estructura;

public class OperacionesEntSal {
    
     /* RECORDAR
        Submúltiplos de la unidad de    tiempo (segundos):
        10^(-3): mili (m)
        10^(-6): micro (m)
        10^(-9): nano (n)
        10^(-12): pico (p)
        Múltiplos de las unidades de datos (bits o bytes).
        2^(10): kilo (K)
        2^(20): mega (M)
        2^(30): giga (G)
        2^(40): tera (T)
                */
    
    static final float MILISEGUNDO = (float) Math.pow(10, -3);          // ms
    static final float MICROSEGUNDO = (float) Math.pow(10, -6);         // µs
    static final float NANOSEGUNDO = (float) Math.pow(10, -9);          // ns
    static final float PICOSEGUNDO = (float) Math.pow(10, -12);         // ps
    static final float KILOBYTE = (float) Math.pow(10, 3);              // KILOBYTE (KB) 2^10 kibi(Ki)
    static final float MEGABYTE = (float) Math.pow(10, 6);              // MEGABYTE (MB) 2^20 mebi(Mi)
    static final float GIGABYTE = (float) Math.pow(10, 9);              // GIGABYTE (GB) 2^30 gibi (Gi)
    static final float TERABYTE = (float) Math.pow(10, 12);             // TERABYTE (TB) 2^40 tebi (Ti)

    /* Ejemplos:
    10 ^3 bytes = 1000 bytes = 1 KiloByte
    2^10 bytes = 1024 bytes = 1 KiB(kibibyte)
    */
    static final float MEGAHERTZIOS = (float) Math.pow(10, 6);          // MHz
    static final float GIGAHERTZIOS= (float) Math.pow(10, 9);          // GHz
     
    public static void main(String[] args) {
        
        
        /* Características del disco  (EJERCICIO 4.1)
           ========================================== */
        
        float tiempoLatencia= 6*MILISEGUNDO;//tlatencia expresado en sg.
        
        float velocidadGiroMinuto = 12000; // revoluciones por minuto (12.000 vueltas por 60 segundo )
        float Una_pista_PorSegundo = 60/ velocidadGiroMinuto; // 1 pista tarda 0,005 seg
        
        float Mbloque= 500000; // Bytes 500.000 bytes
        float Mdato = 4; // en Cisca los datos diempre son de 4 bytes
        float Ndatos = Mbloque /Mdato ; 
        float tDato = Una_pista_PorSegundo/Ndatos; 
        float Vtransf = (Mdato/tDato); 
         
        // Característica de transferencia
        
        /*  Ndatos = Mbloque / Mdato
            
            Mdato: tamaño de un dato expresado en bytes. Entendemos dato como la unidad básica de transferencia. (4 bytes)
            Mbloque: tamaño del bloque de datos que queremos transferir entre el computador y el periférico expresado en bytes. (500 000 bytes)
            Ndatos: número de datos que forman el bloque que queremos transferir. Nos indica también el número de transferencias que se tienen que hacer. (500 000 / 4 = 125 000 bytes)
        
        
            PERIFERICO 
            ==========
            Vtransf: velocidad de transferencia media del periférico expresada en bytes por segundo.
            tDato: tiempo de transferencia de un dato (entre el periférico y el módulo de E/S) expresado en segundos.
            
            tDato = Mdato / Vtransf ==>  Vtransf = Mdato / tDato
        
            tLatencia: tiempo de latencia, tiempo que necesita el periférico para iniciar la primera transferencia expresado en segundos. En algunos periféricos este tiempo puede ser significativo.
            tBloque: tiempo de transferencia de los datos de todo el bloque (entre el periférico y el módulo de E/S) expresado en segundos.
            tBloque = tlatencia + (Ndatos * tdato) */
       
       

            System.out.println("Mdato = "+Mdato+" bytes");
            System.out.println("Mbloque = "+Mbloque+" bytes");
            System.out.println("Ndatos = Mbloque / Mdato = "+Ndatos+" bytes");
            System.out.println("tDato = "+tDato+" sg");     
            System.out.println("4.1.a VELOCIDAD DE TRANSFERENCIA DEL DISCO Vtransf = Mdato/tdato = "+Vtransf+" bytes/sg");
            System.out.println("4.1.a VELOCIDAD DE TRANSFERENCIA DEL DISCO Vtransf = Mdato/tdato = "+Vtransf/MEGABYTE+" MBytes/sg");
            System.out.println("*********************************************************************************");      

        /* Ndatos = Mbloque / Mdato 
            Mdato = 4 bytes 
            Mbloque = 256 Kbyes = 256 * 1024 bytes = 262.144 bytes
        */
        
            Mbloque = 256*KILOBYTE; // bytes consecutivos, para simplificar se considera 1000 bytes en lugar de 1024
            Ndatos = Mbloque/Mdato;
            float tBloque = Ndatos*tDato;
            System.out.println("4.1.b Tiempo de transferencia del bloc por parte del periférico (Ndatos * tdato) sin latencia = "+ tBloque +" sg");
            System.out.println("4.1.b Tiempo de transferencia del bloc por parte del periférico (Ndatos * tdato) sin latencia = "+ tBloque/MILISEGUNDO +" milisegundos");
            tBloque = tiempoLatencia+tBloque;
            System.out.println("4.1.b Tiempo de transferencia del bloc por parte del periférico (tlatencia + Ndatos * tdato) con latencia = "+tBloque +" sg"); 
            System.out.println("4.1.b Tiempo de transferencia del bloc por parte del periférico (tlatencia + Ndatos * tdato) con latencia = "+tBloque/MILISEGUNDO +" milisegundos"); 
            
            System.out.println("*********************************************************************************");  
            Mbloque = 4*KILOBYTE; // bytes consecutivos, para simplificar se considera 1000 bytes en lugar de 1024
            Ndatos = Mbloque/Mdato;
            tBloque = Ndatos*tDato;
            System.out.println("4.1.c Tiempo de transferencia del bloc por parte del periférico (Ndatos * tdato) sin latencia = "+tBloque +" sg");
            System.out.println("4.1.c Tiempo de transferencia del bloc por parte del periférico (Ndatos * tdato) sin latencia = "+tBloque /MICROSEGUNDO+" microsegundos");
            tBloque = tiempoLatencia+tBloque;
            System.out.println("4.1.c Tiempo de transferencia del bloc por parte del periférico (tlatencia + Ndatos * tdato) con latencia = "+tBloque +" seg");
            System.out.println("4.1.c Tiempo de transferencia del bloc por parte del periférico (Ndatos * tdato) sin latencia = "+tBloque /MILISEGUNDO+" ms");
            System.out.println("      Se puede concluir que si leemos un bloque de tamaño pequeñ0, la mayor parte del tiempo de respuesta ");
            System.out.println("      del disco es debido a la latencia y no al tiempo de transferencia.");
            System.out.println("      Por tanto, con un moderado incremento de tiempo (de 6,04 ms a 8,56 ms el incremento ");
            System.out.println("      es de 8,56/6,04 = 1,417, es decir, de cerca del 42%) podemos leer un bloque mucho más grande");
            System.out.println("      (el bloque de 256KBytes es 64 veces mayor que el bloque de 4KBytes)");
            System.out.println("*********************************************************************************");  

        /* PROCESADOR
            ==========
            La frecuencia nos indica el número de ciclos de reloj por segundo. Calculando la inversa de la frecuencia obtenemos el tiempo de un ciclo de reloj del procesador.
            tciclo = 1/frecuencia
            
            frecuencia del reloj del procesador = 2 GHz
            número de instrucciones por ciclo de reloj = 4
            tCiclo: tiempo de un ciclo de reloj (1 / 2 GHz) = 0,5 ns
            tInst: tiempo medio de ejecución de una instrucción expresado en segundos. tiempo de ejecución de las instrucciones (tCiclo / 4) = 0,125 ns
        
            BUS DEL SISTEMA Y MEMORIA
            =========================
            tcesión: tiempo necesario para que el procesador haga la cesión del bus expresado en segundos. (Usar ns)
            trecup: tiempo necesario para que el procesador haga la recuperación del bus expresado en segundos. (Usar ns)
            tmem: tiempo medio para hacer una lectura o una escritura en la memoria principal mediante el bus del sistema expresado en segundos.(Usar ns)
        
            TRANSFERENCIA DE ENTRADA Y SALIDA
            =================================
            tProg : tiempo necesario para hacer la programación de la transferencia de E/S expresado en segundos.
            tFinal : tiempo necesario para hacer la finalización de la transferencia de E/S expresado en segundos.
            tTransf_Dato : tiempo, expresado en segundos, que el procesador está ocupado durante la transferencia de un dato con el módulo de E/S o parado mientras el controlador de DMA hace la transferencia de un dato.
            ttransf_bloque: tiempo, expresado en segundos, que el procesador está ocupado durante la transferencia de datos de todo el bloque con el módulo de E/S o parado mientras el DMA hace la transferencia de datos de todo el bloque. */
                
         /* E/S PROGRAMADA (EJERCICIO 4.2)
            ==============================
            
            tDato = Mdato / Vtransf
            tTransf_dato = tDato
            tTransf_Bloque = tLatencia + (Ndatos * tTransf_Dato) = tBloque
            tCpu = tProg + tTransf_Bloque + tFinal
        
            Nprog = programar la transferencia  
            Nfinal = finalizar la transferencia 
        
            tProg = Nprog * tInst 
            tFinal = Nfinal * tInst
            tTransf_Dato = tDato 
            tTransf_Bloque = tBloque 
            tCpu = tProg + tTransf_Bloque + tFinal */
     
            float tE_S=500*NANOSEGUNDO; // En segundos tProg + tFinal
            
            // Nfinal + Nfinal

            System.out.println("4.2.a SE TRATA DE UNA TRANSFERENCIA DE DATOS DE ENTRADA DESDE DISCO.");
            System.out.println("      La instrucción 7 lee desde el registro de datos del controlador de disco, puerto 204");
            System.out.println("      , y la instrucción 8 copia el dato leído al vector DATA, en memoria.");
            Mdato = 4; 
            System.out.println("4.2.a Tamaño de un dato expresado en bytes. ADD R2,4 es de 4 bytes Mdatos= "+Mdato+" bytes");
            System.out.println("       La instrucción 7 copia un dato del puerto 204 y lo guarda en el registro R0");
            System.out.println("       Como CISCA utiliza un tamaño de palabra de memoria de 4 bytes, se transfieren 4 bytes cada vez.");
            System.out.println("       Además, la instrucción 9 suma 4 al registre R2 que se utiliza como índice para apuntar");
            System.out.println("       al siguiente elemento del vector donde se guarda el nuevo dato.");
            System.out.println("*********************************************************************************"); 
            Mbloque=500000;
            Ndatos = Mbloque*Mdato;
            System.out.println("4.2.b ¿Cuántos datos se transfieren en total entre la memoria y el disco, Ndatos= "+Ndatos+" bytes");
            System.out.println("       Las instrucciones 3, 10 y 11 controlan el bucle para que se repita 50.000 veces, ");
            System.out.println("       en cada iteración del bucle se transfieren 4 bytes");
            System.out.println("*********************************************************************************"); 
            System.out.println("4.2.c ¿Qué bit del registro de estado del controlador de disco se utiliza para verificar ");
            System.out.println("       que se puede realizar la transferencia de un dato? El bit 1 del registro R0 (AND R0,2)");
            System.out.println("       La instrucción 4 lee el contenido del registro de estado, puerto con dirección 200, ");
            System.out.println("       la instrucción 5 hace una máscara (instrucción AND) del valor leído con el bit 1,");
            System.out.println("       el segundo bit menos significativo (valor decimal 2), que tiene que ser 1 para permitir");
            System.out.println("       salir del bucle de las instrucciones 4, 5 y 6, y realizar la transferencia de un dato de 4 bytes.");
            System.out.println("4.2.c ¿Qué valor de este bit indica que la transferencia se puede realizar? CUANDO ESTÁ A UNO ");
            System.out.println("       La operación AND bit a bit permite seleccionar los bits de un cierto valor V. ");
            System.out.println("       Por ejemplo, el resultado de V AND 1 será diferente de cero si el bit menos significativo ");
            System.out.println("       de V (el bit cero) es 1. AND 8 será diferente de cero si el 4º bit menos significativo (el bit 3) es 1.");
            System.out.println("*********************************************************************************"); 
            System.out.println("¿Cuál es el tiempo total que dedica la CPU a la tarea de Entrada/Salida ?"+tE_S+" sg");
            tDato = Mdato / Vtransf;
            Ndatos=50000; // ????????
            float tTransf_Bloque=tiempoLatencia+(tDato*Ndatos);
            System.out.println("4.2.d ¿Cuál es el tiempo total que dedica la CPU a la tarea de transferencia del bloque ? "+tTransf_Bloque+" sg");
            System.out.println("4.2.d ¿Cuál es el tiempo total que dedica la CPU a la tarea de transferencia del bloque ? "+tTransf_Bloque/MILISEGUNDO+" ms");
            System.out.println("       Durante los 6 ms de espera hasta que el disco se posicione en el sector adecuado se ejecutan");
            System.out.println("       las instrucciones 4, 5 y 6. Después, durante los 2 ms que dura la transferencia de datos, ");
            System.out.println("       se alterna entre la ejecución de las instrucciones 4-6, del bucle de espera activa de sincronización,");
            System.out.println("       y la ejecución de las instrucciones 7-8, que hacen el intercambio de cada dato.");
            System.out.println("*********************************************************************************"); 

            float frecuencia_reloj_rocesador = (float) 2.5 ; // Ghz
            frecuencia_reloj_rocesador=frecuencia_reloj_rocesador*GIGAHERTZIOS; //Hz
            float tCiclo= 1/frecuencia_reloj_rocesador; //

            float numero_instrucciones_por_ciclo_reloj = 4; 

            // tInst: tiempo medio de ejecución de una instrucción expresado en segundos. tiempo de ejecución de las instrucciones (tCiclo / 4) = 0,125 ns 
            float tInst=tCiclo/numero_instrucciones_por_ciclo_reloj;
  
            //  Ninstrucciones = 8, El mínimo número de instrucciones que tiene que ejecutar el programa para cada dato transferido es 8 instrucciones (4-11). 
            // La ejecución repetida de les instrucciones 4, 5 y 6 sólo se realiza cuando el dispositivo periférico es más lento que el procesador.
            // El procesador puede ejecutar 4 instrucciones por ciclo.
            // Por tanto, el tiempo mínimo para transferir un dato (4 Bytes) será el tiempo necesario para ejecutar 8 instrucciones:
            // 8 instrucciones / 4 instr/ciclo = 2 ciclos, es decir, 2 * 0,4 ns = 0,8 ns
            float nInstrucciones = 8;
            float tMin= (nInstrucciones/numero_instrucciones_por_ciclo_reloj)*tCiclo; 
            //Por tanto, se pueden transferir como mucho 4 Bytes cada 0,8 ns, es decir:
            //4 Bytes / 0,8*10-9 s = 5 GBytes/s, como máximo (1 Gbyte = 109 Bytes = 1000000000 Bytes)

            float tCpu = 4/tMin ; 
            System.out.println("4.2.e ¿Cuál es la máxima tasa o velocidad de transferencia del nuevo disco que se podría soportar sin que se perdiesen datos? "+tCpu+ " Bytes/sg");
            System.out.println("4.2.e ¿Cuál es la máxima tasa o velocidad de transferencia del nuevo disco que se podría soportar sin que se perdiesen datos? "+tCpu/GIGABYTE+ " GBytes/sg");
            System.out.println("*********************************************************************************");  
       
        /* E/S POR INTERRUPCIONES (EJERCICIO 4.3)
           ======================================
         
            La señal INT la activa el módulo de E/S y la recibe el procesador. Es una señal activa a la baja.
            (INTA o INTACK,es una señal activa a la baja)
            
            trec_int: tiempo que pasa desde que el procesador reconoce que hay una petición de interrupción hasta que se puede iniciar la ejecución de la RSI
                    expresado en segundos. Este tiempo incluye el reconocimiento de la interrupción y la salvaguarda del estado del procesador.
            trsi: tiempo de ejecución de la RSI expresado en segundos. Este tiempo incluye inhibir las interrupciones si es necesario, guardar en la pila 
                    los registros que se pueden modificar, acceder al módulo de E/S para hacer la transferencia del dato, restaurar de la pila los registros, 
                    volver a habilitar las interrupciones y hacer el retorno de interrupción.
            Nrsi: número de instrucciones de la RSI.
         
            trsi = Nrsi * tinst
            ttransf_dato = trec_int + trsi
            ttransf_bloque = Ndatos * ttransf_dato
            tcpu = tprog + ttransf_bloque + tfinal
            ttransf_dato es menor que tdato. */
            
            System.out.println("4.3.a ¿Por qué razón es necesario ejecutar las instrucciones 1, 2, 3, 10, 11, 12 y 13 ? ");
            System.out.println("       Hay que deshabilitar las interrupciones (1) para que el mismo disco no vuelva a interrumpir la propia RSI,");
            System.out.println("       y hay que volver a habilitarlas (12) para permitir nuevas interrupciones una vez acabe la RSI.");
            System.out.println("       Hay que guardar (2 y 3) el contenido de los registros que se tienen que modificar en la RSI para poder recuperar su valor (10 y 11) al final de la RSI,");
            System.out.println("       y así asegurar que el programa que se ha interrumpido no ve modificado su estado. La instrucción IRET (13) devuelve el control al programa interrumpido.");
            System.out.println("*********************************************************************************");  
            System.out.println("4.3.b Las instrucciones 4 y 5 leen el contenido de dos registros (puertos) de E/S. El valor leído del puerto 200 no se utiliza,");
            System.out.println("      pero ¡ES NECESARIO hacer esta lectura para que la RSI funcione! Revisad el uso que se hacía de este registro de ESTADO en la versión de E/S programada del ejercicio 4.2");
            System.out.println("      y proponed una hipótesis respecto a por qué puede ser necesario ejecutar la instrucción 4.");
            System.out.println("      Muchos dispositivos periféricos requieren que se haga una lectura al registro de estado para borrar el bit de Estado que indica que hay una petición de interrupción pendiente.");
            System.out.println("      De esta forma el programador indica al dispositivo periférico que se ha atendido la interrupción y que la petición ya se puede borrar.");
            System.out.println("      Cuando leemos o escribimos de/a un registro de E/S se pueden producir \"efectos colaterales\".");
            System.out.println("      Por ejemplo, es posible (1) leer un registro de Estado dos veces seguidas y obtener valores diferentes,");
            System.out.println("      (2) escribir un valor y después leer un valor diferente al que se ha escrito,");
            System.out.println("      (3) escribir un valor y provocar un cambio en el valor leído de otro registro");
            System.out.println("      Por lo tanto, no podemos descartar la instrucción 4 por el hecho que parezca que no tiene sentido.");
            System.out.println("      Cuando accedemos a registros de E/S tenemos que contar con los \"efectos colaterales\".");
            System.out.println("*********************************************************************************");  
        
            frecuencia_reloj_rocesador = (float) 2.5 ; // Ghz
            frecuencia_reloj_rocesador=frecuencia_reloj_rocesador*GIGAHERTZIOS; //Hz
            tCiclo= 1/frecuencia_reloj_rocesador; //
            numero_instrucciones_por_ciclo_reloj = 4;       
            float tRec_Int = tCiclo; // Tiempo para atender la interrupción
            tE_S=500*NANOSEGUNDO; // En segundos tProg + tFinal
            float Nrsi = 13; // Número total de instrucciones
            float tRsi = Nrsi * tInst; // Tiempo de ejecución RSI
     
            float tTransf_Dato = tRec_Int + tRsi; // Tiempo consumido por la CPU en cada interrupción
            /* Número de interrupciones producidas (o número total de datos, Ndatos ): 
              200.000 Bytes / 4 Bytes por interrupción = 50.000 interrupciones.
            */
            Ndatos=50000;
            tBloque=tTransf_Bloque;// Viene del ejercicio 4,2
            tTransf_Bloque = Ndatos * tTransf_Dato;
            tCpu= ( tE_S ) + tTransf_Bloque;
            System.out.println("4.3.c ¿Cuál es el tiempo total que dedica la CPU a la tarea de Entrada/Salida, tcpu? "+tCpu+" sg"); 
            System.out.println("4.3.c ¿Cuál es el tiempo total que dedica la CPU a la tarea de Entrada/Salida, tcpu? "+tCpu/MICROSEGUNDO+" µsg"); 
            /* De los 8 ms de tiempo total que tarda el periférico para realizar la transferencia (tbloque = 8 ms = 8000 us), este tiempo se calculó en el ejercicio 4.2. d, 
               la CPU está dedicada a la tarea de E/S el porcentaje siguiente:*/
            float per_Ocupación = tTransf_Bloque * 100 / tBloque;
            System.out.println("4.3.c ¿Qué porcentaje de ocupación representa %ocupación? " +per_Ocupación+"% del tiempo");
            System.out.println("*********************************************************************************");  
        
           /*
            En la fase de transferencia de datos, el disco genera 50.000 interrupciones durante 2 ms (este dato se calculó en el ejercicio 4.2.d):*/
            tTransf_Bloque=  Ndatos * tDato;
            tTransf_Dato =  tTransf_Bloque/Ndatos;
           /* Es decir, si 50.000 tarda 2ms ==> 1 interrupción tarda X tenemos  2.000 us / 50.000 = 0,04 us = 40 ns. 
            Este es el tiempo máximo que tendría que tardar la gestión de la interrupción, 
            incluyendo el tiempo adicional para transferir el control a la RSI, el tiempo que puede consumir la CPU en una interrupción tTransf_Dato
            Nrsi = 13;
            tInst = tCiclo / 4
            
            tTransf_Dato = tRec_Int + tRsi ;
            tTransf_Dato = tRec_Int + (Nrsi * tInst) ;
            tTransf_Dato = tCiclo + (13 * tInst);
            tTransf_Dato = tCiclo + (13 * tCiclo / 4);
            tTransf_Dato = (1+(13/4))*tCiclo
            tTransf_Dato = 4,25*tCiclo
            tCiclo = Transf_Dato / 4,25
           */
           
            tCiclo = tTransf_Dato/(1+(Nrsi/4));
            System.out.println("tCiclo "+tCiclo);
            /*La frecuencia será la inversa del tiempo de ciclo:*/
            float frec= 1/tCiclo;
            
            System.out.println("4.3.d ¿hasta qué frecuencia podríamos hacerlo sin que se perdiesen datos en la transferencia? "+frec+" Ghz");
            System.out.println("4.3.d ¿hasta qué frecuencia podríamos hacerlo sin que se perdiesen datos en la transferencia? "+(frec*GIGAHERTZIOS)+" Hhz");
            System.out.println("4.3.d ¿hasta qué frecuencia podríamos hacerlo sin que se perdiesen datos en la transferencia? "+(frec*GIGAHERTZIOS)/MEGAHERTZIOS+" MHhz");
            System.out.println("*********************************************************************************"); 
            
        /* E/S ENTRADA/SALIDA POR DMA (EJERCICIO 4.4)
           ==========================================

            tTransf_Dato = tCesión + tMem + tRecup
            tTransf_Bloque = Ndatos * tTransf_Dato
            tCpu = tProg + tTransf_Bloque + tFinal*/
        
            tTransf_Dato=2; // en nanosegundos
            float tMem= 1;  // añadir 1nanosegndo por cada ráfaga
            Ndatos=50000;   
            tTransf_Bloque=Ndatos*tTransf_Dato;
            
            System.out.println("4.4.a ¿Tiempo total de ocupación del bus por parte del controlador de disco/DMA? "+tTransf_Bloque+" ns");
            System.out.println("4.4.a ¿Tiempo total de ocupación del bus por parte del controlador de disco/DMA? "+tTransf_Bloque*NANOSEGUNDO/MICROSEGUNDO+" µs");
            System.out.println("*********************************************************************************"); 
            
            Mdato=4;        // Bytes por cada transfeencia, por lo tanto hay tanta peticiones como datos que se transfierem
           
            /* POR RÁFAGA  
            
            nRafaga = mBuffer / Mdato
            tTransf_Rafaga = tCesión + (nRafaga * tMem) + tRecup
            tTransf_Bloque = (Ndatos / nRafaga) * tTransf_Rafaga
            tCpu = tProg + tTransf_Bloque + tFinal
            %ocupación = (tTransf_Bloque * 100) / tBloque
            tdisp = (100% - %ocupación)
            
            */
            float nRafaga =4;
            float tTransf_Rafaga = tTransf_Dato+3;
            tTransf_Bloque = (Ndatos / nRafaga) * tTransf_Rafaga;
            
            System.out.println("4.4.b ¿Contestar a la pregunta anterior suponiendo que el controlador de disco/DMA ");
            System.out.println("       almacena 4 datos temporalmente y que la transferencia de estos 4 datos se hace en modo ráfaga? "+tTransf_Bloque+" ns");
            System.out.println("4.4.b ¿Contestar a la pregunta anterior suponiendo que el controlador de disco/DMA ");
            System.out.println("       almacena 4 datos temporalmente y que la transferencia de estos 4 datos se hace en modo ráfaga? "+tTransf_Bloque*NANOSEGUNDO/MICROSEGUNDO+" µs");
            System.out.println("*********************************************************************************"); 
            
            /* Recordemos del apartado 4.2 que tbloque = 8000 us */
            /* SIN RÁFAGAS */
            tBloque= 8000; //us
            tBloque= tBloque*MICROSEGUNDO; // en segundos
       
            tTransf_Dato=2; // en nanosegundos
            Ndatos=50000;   
            tTransf_Bloque=Ndatos*tTransf_Dato; // en nanosegundos
            tTransf_Bloque=tTransf_Bloque*NANOSEGUNDO; // en segundos
            float PerOcupación = tTransf_Bloque * 100 / tBloque;
            float tDisp= 100- PerOcupación;
                      
            System.out.println("4.4.c ¿qué porcentaje de tiempo tiene disponible la CPU para ejecutar código efectivo");
            System.out.println("       de otros programas durante la transferencia sin ráfaga ? "+tDisp+" %");
            System.out.println("*********************************************************************************"); 
            
            /* CON RÁFAGAS */
            tBloque= 8000; //us
            tBloque= tBloque*MICROSEGUNDO; // en segundos
       
            tTransf_Dato=2; // en nanosegundos
            Ndatos=50000;   
            
            nRafaga =4;
            tTransf_Rafaga = tTransf_Dato+3;
            tTransf_Bloque = (Ndatos / nRafaga) * tTransf_Rafaga;
            tTransf_Bloque=tTransf_Bloque*NANOSEGUNDO; // en segundos 
            
            PerOcupación = tTransf_Bloque * 100 / tBloque;
            tDisp= 100- PerOcupación;
                      
            System.out.println("4.4.c ¿qué porcentaje de tiempo tiene disponible la CPU para ejecutar código efectivo");
            System.out.println("       de otros programas durante la transferencia con ráfaga ? "+tDisp+" %");
            System.out.println("*********************************************************************************"); 
            
             
            System.out.println(" ================================================ PEC 2 ================================================ ");
            
            /* PEC 2 Apartado 2.1 
              =================== */
        
            tiempoLatencia= 0;//tlatencia expresado en sg.
            Ndatos = 4000000; // Bytes
            Mdato = 4; // Bytes en Cisca los datos diempre son de 4 bytes

            Vtransf = 2*MEGABYTE; // Bytes
            tDato = Mdato / Vtransf;         
            tBloque = tiempoLatencia + (Ndatos * tDato);
            System.out.println("2.1.b ¿ Cuánto tiempo dura la transferencia ttransf_bloque? "+tBloque+ " s");
            System.out.println("      ¿ Qué porcentaje de este tiempo dedica la CPU a la transferencia? ");
            System.out.println("        La CPU dedica el 100% del tiempo y, por lo tanto, el tiempo coincide ");
            System.out.println("        con el tiempo dedicado por el periférico tbloc. ");
            System.out.println("*********************************************************************************"); 

            frecuencia_reloj_rocesador = (float) 4 ; // Ghz
            frecuencia_reloj_rocesador=frecuencia_reloj_rocesador*GIGAHERTZIOS; //Hz
            tCiclo= 1/frecuencia_reloj_rocesador; //
            float una_instruccion_cada_4_ciclos  = 4; 
            tInst=tCiclo*una_instruccion_cada_4_ciclos;
            nInstrucciones = 8;
            tMin= (nInstrucciones/tInst);
            
            System.out.println("2.1.c ¿Cuál es la máxima tasa o velocidad de transferencia del nuevo dispositivo ");
            System.out.println("       que se podría soportar sin que el dispositivo se tuviera que esperar?");
            System.out.println("       tCiclo = "+tCiclo+ " s");
            System.out.println("       tCiclo = "+tCiclo/NANOSEGUNDO+ " ns");
            System.out.println("       El mínimo número de instrucciones que ha de ejecutar el programa para cada dato");
            System.out.println("       transferido son las 8 instrucciones: 3, 4, 5, 6, 7, 8, 9 y 10. Ejecutar las "+nInstrucciones+" instrucciones");
            System.out.println("       tInst = "+tInst+" s");
            System.out.println("       tInst = "+tInst/NANOSEGUNDO+ " ns");
            System.out.println("       Se pueden transferir 4 bytes cada "+tMin+" , es decir: "+(4/tMin)+ " Bytes/s");
            System.out.println("       Se pueden transferir 4 bytes cada "+tMin+" , es decir: "+(4/tMin)*GIGABYTE+ " GBytes/s");
            System.out.println("*********************************************************************************"); 
            
            /* PEC 2 Apartado 2.2 
              =================== */

            frecuencia_reloj_rocesador = 4 ; // Ghz
            frecuencia_reloj_rocesador=frecuencia_reloj_rocesador*GIGAHERTZIOS; //Hz
            tCiclo= 1/frecuencia_reloj_rocesador; //
            una_instruccion_cada_4_ciclos  = 4; 
            
            tInst=tCiclo*una_instruccion_cada_4_ciclos;
            
            tE_S=1000*NANOSEGUNDO; // En segundos tProg + tFinal
            tRec_Int = 16*tCiclo; // Tiempo para atender la interrupción
            Nrsi = 15; // Número total de instrucciones
            tRsi = Nrsi * tInst; // Tiempo de ejecución RSI
            tTransf_Dato = tRec_Int + tRsi; // Tiempo consumido por la CPU en cada interrupción
            Ndatos = 4000000; // Número de interrupciones producidas Bytes
            tTransf_Bloque = tTransf_Dato*Ndatos; // Tiempo consumido en total en TODAS las interrupciones
            tCpu= ( tE_S ) + tTransf_Bloque; // El tiempo final de ocupación de la CPU debe incluir el tiempo de programación y finalización
            per_Ocupación = tCpu * 100 / tBloque; 

            
            System.out.println("2.2.b ¿ Cuál es el tiempo total que dedica la CPU a la tarea de Entrada/Salida, tcpu ");
            System.out.println("         tCiclo = "+tCiclo+ " s");
            System.out.println("         tCiclo = "+tCiclo/NANOSEGUNDO+ " ns");
            System.out.println("         tRec_Int = "+tRec_Int+ "s");
            System.out.println("         tRec_Int = "+tRec_Int/NANOSEGUNDO+ "ns");
            System.out.println("         tInst = "+tInst+ "s");
            System.out.println("         tInst = "+tInst/NANOSEGUNDO+ "ns");    
            System.out.println("         Número total de instrucciones Nrsi = "+Nrsi+ " instrucciones");
            System.out.println("         Tiempo de ejecución RSI tRsi = "+tRsi+ " s");
            System.out.println("         Tiempo de ejecución RSI tRsi = "+tRsi/NANOSEGUNDO+ "ns");
            System.out.println("         Tiempo consumido por la CPU en cada interrupción, ttransf_dato = "+tTransf_Dato+" s");
            System.out.println("         Tiempo consumido por la CPU en cada interrupción, ttransf_dato = "+tTransf_Dato/NANOSEGUNDO+ "ns");
            System.out.println("         Número de interrupciones producidas Ndatos = "+Ndatos+" bytes");
            System.out.println("         Tiempo consumido en total en TODAS las interrupciones tTransf_Bloque = "+tTransf_Bloque+" s");
            System.out.println("         Tiempo consumido en total en TODAS las interrupciones tTransf_Bloque = "+tTransf_Bloque/MILISEGUNDO+" ms");
            System.out.println("         Etiempo de programación y finalización de la transferencia = "+tE_S+" s");
            System.out.println("         Etiempo de programación y finalización de la transferencia = "+tE_S/NANOSEGUNDO+ "ns");
            System.out.println("         El tiempo final de ocupación de la CPU = "+tCpu+" s");
            System.out.println("         El tiempo final de ocupación de la CPU = "+tCpu/MILISEGUNDO+" ms");
            System.out.println("         Etiempo de programación y finalización de la transferencia = "+tE_S+ " ns");
            System.out.println("         Etiempo de programación y finalización de la transferencia = "+tE_S/NANOSEGUNDO+ "ns");
            System.out.println("      ¿ Cuál es el porcentaje que representa el tiempo de transferencia del bloque ");
            System.out.println("        ttransf_bloque con respecto al tiempo de transferencia del bloque por parte ");
            System.out.println("        del periférico tbloque? " + per_Ocupación+ " %");
            System.out.println("*********************************************************************************"); 
            
            Ndatos = 4000000; // Número de interrupciones producidas
            
             
           /* Es decir,tenemos una interrupción cada 8.000.000 us / 4.000.000 = 4 us (microsegundos).
              Este es el tiempo máximo que debería tardar la gestión de la interrupción (ttransf_dato),
              incluyendo el tiempo adicional para transferir el control a la RSI.*/

            Nrsi = 15;
            
            /*
            En el enunciado se define trec_int = 16 ciclos de reloj = 16 * tciclo y por lo tanto:
            tTransf_Dato = tRec_Int + tRsi = = tRec_Int + (Nrsi × tInst) = 16 * tCiclo + (15 × tInst)
            Tal como hemos visto en el apartado anterior y como dice el enunciado, el tiempo de una
            instrucción es: tinstr = 4 * tInst
            Por lo tanto:
            tTransf_Dato = 16 * tCiclo + (15 × tInst) = 16 * tCiclo + (15 × 4*  tCiclo ) 
            tTransf_Dato = 16 * tCiclo + 60 *  tCiclo = 76 * tCiclo

            Queremos encontrar el tiempo de ciclo tal que el tiempo de transferencia de un dato sea 2 us:

            
            tTransf_Dato = 16*tCiclo + (Nrsi*4*tCiclo)
            tTransf_Dato = (16+(Nrsi*4))*tCiclo
            tCiclo = tTransf_Dato / (16+(Nrsi*4))
            */
            tTransf_Dato=tBloque/Ndatos;
            tCiclo = (tTransf_Dato / (16+(Nrsi*4)))/MICROSEGUNDO;
            
            /*La frecuencia será la inversa del tiempo de ciclo:*/
            frec= 1/tCiclo;
            
            System.out.println("2.2.c ¿ Cuál es el tiempo total que dedica la CPU a la tarea de Entrada/Salida, tcpu ");
            System.out.println("        En la fase de transferencia de datos, el controlador de E/S genera "+Ndatos+ " interrupciones");
            System.out.println("        durante "+tBloque+" segundos. (datos calculados en el apartado 2.1)");
            System.out.println("        Ndatos * tDato = "+tBloque+" segundos");
            System.out.println("        Ndatos * tDato = "+tBloque/MILISEGUNDO+ " ms");
            System.out.println("        Ndatos * tDato = "+tBloque/MICROSEGUNDO+ " µs");
            System.out.println("        Es decir,tenemos una interrupción cada "+tBloque +" s / "+Ndatos+" = "+tTransf_Dato+" s");
            System.out.println("        Es decir,tenemos una interrupción cada "+tBloque +" s / "+Ndatos+" = "+tTransf_Dato/MICROSEGUNDO+" µs");
            System.out.println("        En el enunciado se define trec_int = 16 ciclos de reloj y por lo tanto:");
            System.out.println("        tTransf_Dato = tRec_Int + tRsi = tRec_Int + (Nrsi × tInst) ");
            System.out.println("        tTransf_Dato = 16 * tCiclo + (15 × tInst)");
            System.out.println("        Tal como hemos visto en el apartado anterior y como dice el enunciado, el tiempo de una");
            System.out.println("        instrucción es: tInst = 4*  tCiclo");
            System.out.println("        Por lo tanto:");
            System.out.println("        tTransf_Dato = 16 * tCiclo + (15 × tInst)");
            System.out.println("        tTransf_Dato = 16 * tCiclo + (15 × 4*  tCiclo)");
            System.out.println("        tTransf_Dato = 16 * tCiclo + 60 *  tCiclo = 76 * tCiclo");
            System.out.println("        Queremos encontrar el tiempo de ciclo tal que el tiempo de transferencia de un dato sea 2 µs ");
            System.out.println("        tCiclo = "+tCiclo+" µs");
            System.out.println("        tCiclo = "+tCiclo*MICROSEGUNDO+ " s");
            System.out.println("        La frecuencia será la inversa del tiempo de ciclo = "+frec+ " MHz");
            System.out.println("*********************************************************************************"); 
            
            /* PEC 2 Apartado 2.3 
              =================== */
            
            float tC_R=10*NANOSEGUNDO; // tCesión + tRecup en segundos
            tMem  =  1*NANOSEGUNDO; // en segundos
            float mBuffer =  4000;  // En bytes
            Mdato =  4;             // En bytes
            nRafaga = mBuffer / Mdato ;
            tTransf_Rafaga = tC_R + (nRafaga * tMem);
            float NumPeticionesBus = Ndatos/nRafaga;
            tTransf_Bloque = tTransf_Rafaga * NumPeticionesBus;
            
            System.out.println("2.3.a ¿ Tiempo total de ocupación del bus por parte del controlador de DMA ");
            System.out.println("        para llevar a cabo la transferencia que venimos analizando ? "+tTransf_Bloque/MILISEGUNDO+ " ms");
            System.out.println("        Nráfaga  = "+nRafaga);
            System.out.println("        tTransf_Rafaga  = "+tTransf_Rafaga+" s");
            System.out.println("        tTransf_Rafaga  = "+tTransf_Rafaga/MICROSEGUNDO+ " µs");
            System.out.println("        Número de peticiones del Bus  NumPeticionesBus = "+NumPeticionesBus);
            System.out.println("        Tiempo total de ocupación del Bus tTransf_Bloque = "+tTransf_Bloque+" a"); 
            System.out.println("        Tiempo total de ocupación del Bus tTransf_Bloque = "+tTransf_Bloque/MILISEGUNDO+ " ms");
            System.out.println("*********************************************************************************"); 
            
            PerOcupación = tTransf_Bloque * 100 / tBloque;
            
            System.out.println("2.3.b ¿ Cuál es el porcentaje de tiempo que tiene disponible la CPU para ejecutar código");
            System.out.println("        efectivo de otros programas durante la transferencia ? "+tTransf_Bloque/MILISEGUNDO+ " ms");
            System.out.println("        %ocupación   PerOcupación = "+PerOcupación+" %");
            System.out.println("        Porcentaje de tiempo disponible = "+ (100-PerOcupación)+" %");
            System.out.println("*********************************************************************************"); 
    }
    
}
