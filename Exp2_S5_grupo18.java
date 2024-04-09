package exp2_s5_grupo18;

import java.util.Scanner;
import java.util.Random;

import java.util.Random;
import java.util.Scanner;

public class Exp2_S5_grupo18 {
    static String[] tipoEntradas = new String[3];
    static String[] precioEntradas = new String[3];
    
    static int descuentoEstudiante = 10;
    static int descuentoTerceraEdad = 15;
    static int edadTerceraEdad = 60;
    
    public static void limpiaPantalla(){
        for(int i=0; i<20; i++){
            System.out.println("");
        }
    }
    
    public static void seleccionaAsiento(int opcion, String zonaTeatro, int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB, String asientoSeleccionado, boolean asientoNoValido, boolean asientoOcupado){
        Scanner teclado = new Scanner(System.in);

        String[] letrasColumnas = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"};
        String filaLetras = "  | ";
        String filaNumeros = "";
        
        int cantFilas = 2;
        int cantColumnas = 10;
        String separador = "-------------------------------------------";
        if(opcion == 2){
            cantFilas = 5;
            cantColumnas = 12;
            separador = "---------------------------------------------------";
        }else if(opcion == 3){
            cantFilas = 3;
            cantColumnas = 8;
            separador = "-----------------------------------";
        }
        
        for(int i=0; i<cantColumnas; i++){
            filaLetras += letrasColumnas[i]+" | ";
        }
        
        System.out.println("-- "+zonaTeatro+" --");
        System.out.println("");
        System.out.println(filaLetras);
        System.out.println(separador);
        
        for(int i=0; i<cantFilas; i++){
            filaNumeros = (i + 1)+" |";
            for(int j=0; j<cantColumnas; j++){
                if(opcion == 1){
                    if(asientosPalco[i][j] == 1) filaNumeros += " * |";
                    else filaNumeros += "   |";
                }else if(opcion == 2){
                    if(asientosPA[i][j] == 1) filaNumeros += " * |";
                    else filaNumeros += "   |";
                }else if(opcion == 3){
                    if(asientosPB[i][j] == 1) filaNumeros += " * |";
                    else filaNumeros += "   |";
                }
            }
            System.out.println(filaNumeros);
            System.out.println(separador);
        }
        System.out.println("(*) ASIENTOS YA VENDIDOS");
        if(asientoNoValido){
            System.out.println("-- EL ASIENTO "+asientoSeleccionado.toUpperCase()+" NO ES VÁLIDO --");
        }
        if(asientoOcupado){
            System.out.println("-- EL ASIENTO "+asientoSeleccionado.toUpperCase()+" YA ESTÁ VENDIDO --");
        }
        System.out.println("SELECCIONE EL ASIENTO QUE QUIERE COMPRAR");
        System.out.println("POR EJEMPLO: D2");
        asientoSeleccionado = teclado.nextLine();
        asientoNoValido = false;
        asientoOcupado = false;
        
        String columnaAux = asientoSeleccionado.substring(0,1);
        String filaAux = asientoSeleccionado.substring(1,2);
        int filaAsiento = Integer.parseInt(filaAux);
        filaAsiento -= 1;
        int columnaAsiento = buscaColumnaDesdeLetra(columnaAux, letrasColumnas);
        
        if(columnaAsiento < 0 || columnaAsiento > cantColumnas) asientoNoValido = true;
        else{
            if(opcion == 1){
                if(asientosPalco[filaAsiento][columnaAsiento] == 1) asientoOcupado = true;
                else asientosPalco[filaAsiento][columnaAsiento] = 1;
            }else if(opcion == 2){
                if(asientosPA[filaAsiento][columnaAsiento] == 1) asientoOcupado = true;
                else asientosPalco[filaAsiento][columnaAsiento] = 1;
            }else if(opcion == 3){
                if(asientosPB[filaAsiento][columnaAsiento] == 1) asientoOcupado = true;
                else asientosPalco[filaAsiento][columnaAsiento] = 1;
            }
        }
        
        if(asientoNoValido || asientoOcupado){
            limpiaPantalla();
            seleccionaAsiento(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB, asientoSeleccionado, asientoNoValido, asientoOcupado);
        }else{
            limpiaPantalla();
            otrosDatosCompra(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB, asientoSeleccionado);
        }
        
    }
    
    public static void otrosDatosCompra(int opcion, String zonaTeatro, int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB, String asientoSeleccionado){
        Scanner teclado = new Scanner(System.in);
        int edad;
        int precioEntrada = 15000;
        int descuento = 0;
        
        if(opcion == 2) precioEntrada = 10000;
        else if(opcion == 3) precioEntrada = 7000;
        edad = 0;
        
        try{
            do{
                System.out.println("UBICACIÓN: "+ zonaTeatro);
                System.out.println("ASIENTO: "+asientoSeleccionado.toUpperCase());
                System.out.println("");
                System.out.println("INGRESE SU EDAD:");
                edad = teclado.nextInt();
                if(edad < 1){
                    limpiaPantalla();
                    System.out.println("-- LA EDAD NO PUEDE SER MENOR A UNO (1) --");
                    System.out.println("");
                }
            }while(opcion < 1);
        }catch(Exception e){
            limpiaPantalla();
            System.out.println("ERROR: LA OPCIÓN INGRESADA NO ES UN NÚMERO");
            System.out.println("");
            otrosDatosCompra(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB, asientoSeleccionado);
        }
        
        int precioFinal = precioEntrada;
        if(edad < 24){
            descuento = 10;
            precioFinal = precioEntrada - (precioEntrada * descuento / 100);
        }
        if(edad > 60){
            descuento = 15;
            precioFinal = precioEntrada - (precioEntrada * descuento / 100);
        }
        
        limpiaPantalla();
        resumenCompra(opcion, zonaTeatro, asientosPalco, asientosPA, asientosPB, asientoSeleccionado, precioEntrada, descuento, precioFinal);
    }
    
    public static void resumenCompra(int opcion, String zonaTeatro, int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB, String asientoSeleccionado, int precioEntrada, int descuento, int precioFinal){
        Scanner teclado = new Scanner(System.in);
        String otraCompra;
        otraCompra = "";
        do{
            System.out.println("-- RESUMEN DE COMPRA --");
            System.out.println("UBICACIÓN: "+ zonaTeatro);
            System.out.println("ASIENTO: "+asientoSeleccionado.toUpperCase());
            System.out.println("PRECIO ENTRADA: "+precioEntrada);
            String txtDescuento = (descuento == 0)? "SIN DESCUENTO" : descuento+"%";
            System.out.println("DESCUENTO: "+txtDescuento);
            System.out.println("PRECIO FINAL: "+precioFinal);

            System.out.println("");
            System.out.println("DESEA COMPRAR UNA NUEVA ENTRADA (S/N)");
            otraCompra = teclado.nextLine();
            if(!("N".equals(otraCompra.toUpperCase()) || "S".equals(otraCompra.toUpperCase()))){
                limpiaPantalla();
                System.out.println("-- OPCIÓN NO VÁLIDA. SÓLO SE PERMITE 'S' O 'N' --");
                System.out.println("");
            }
        }while(!("N".equals(otraCompra.toUpperCase()) || "S".equals(otraCompra.toUpperCase())));
        
        if("N".equals(otraCompra.toUpperCase())){
            limpiaPantalla();
            System.out.println("GRACIAS POR COMPRAR ENTRADAS EN TEATRO MORO");
        }
        
        if("S".equals(otraCompra.toUpperCase())){
            limpiaPantalla();
            menuPrincipal(asientosPalco, asientosPA, asientosPB);
        }
    }
    
    public static int buscaColumnaDesdeLetra(String letra, String[] letrasColumnas){
        int pos = -1;
        for(int i=0; i<letrasColumnas.length; i++){
            if(letra.toUpperCase().equals(letrasColumnas[i])){
                pos = i;
                break;
            }
        }
        return pos;
    }
    
    public static void tipoAsiento(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        int opcion;
        String[] itemsMenu = new String[3];
        String zonaTeatro = "";
        
        opcion = 0;
        itemsMenu[0] = "ZONA A - PALCO";
        itemsMenu[1] = "ZONA B - TRIBUNA BAJA";
        itemsMenu[2] = "ZONA C - TRIBUNA ALTA";
        
        try{
            do{
                System.out.println("-- SELECCIONE EL TIPO DE ENTRADA --");
                System.out.println("");
                System.out.println("SELECCIONE UNA OPCIÓN:");
                for(int i=0; i<itemsMenu.length; i++){
                    String txtOpcion = (i + 1) + ".- " + itemsMenu[i];
                    if(i == 0) txtOpcion += " ($15.000)";
                    if(i == 1) txtOpcion += " ($10.000)";
                    if(i == 2) txtOpcion += " ($7.000)";
                    System.out.println(txtOpcion);
                }
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > itemsMenu.length){
                    limpiaPantalla();
                    System.out.println("-- LA OPCIÓN ("+opcion+") NO ES VÁLIDA --");
                    System.out.println("");
                }
            }while(opcion < 1 || opcion > itemsMenu.length);
        }catch(Exception e){
            limpiaPantalla();
            System.out.println("ERROR: LA OPCIÓN INGRESADA NO ES UN NÚMERO");
            System.out.println("");
            tipoAsiento(asientosPalco, asientosPA, asientosPB);
        }
        
        if(opcion >=1 && opcion <= itemsMenu.length){
            zonaTeatro = itemsMenu[(opcion - 1)];
            limpiaPantalla();
            seleccionaAsiento(opcion, zonaTeatro,asientosPalco, asientosPA, asientosPB, "", false, false);
        }
    }
    
    public static void menuPrincipal(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        int opcion;
        String[] itemsMenu = new String[3];
        
        opcion = 0;
        itemsMenu[0] = "COMPRAR ENTRADA";
        itemsMenu[1] = "VER PROMOCIONES";
        itemsMenu[2] = "SALIR";
        
        try{
            do{
                System.out.println("BIENVENIDO A LA ADMINISTRACIÓN DEL TEATRO MORO");
                System.out.println("-- VENTA DE ENTRADAS --");
                System.out.println("");
                System.out.println("SELECCIONE UNA OPCIÓN");
                for(int i=0; i<itemsMenu.length; i++){
                    System.out.println((i + 1) + ".- " + itemsMenu[i]);
                }
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > itemsMenu.length){
                    limpiaPantalla();
                    System.out.println("-- LA OPCIÓN ("+opcion+") NO ES VÁLIDA --");
                    System.out.println("");
                }
            }while(opcion < 1 || opcion > itemsMenu.length);
        }catch(Exception e){
            limpiaPantalla();
            System.out.println("ERROR: LA OPCIÓN INGRESADA NO ES UN NÚMERO");
            System.out.println("");
            menuPrincipal(asientosPalco, asientosPA, asientosPB);
        }
        
        if(opcion == 1){
            limpiaPantalla();
            tipoAsiento(asientosPalco, asientosPA, asientosPB);
        }
        
        if(opcion == 2){
            limpiaPantalla();
            muestraPromociones(asientosPalco, asientosPA, asientosPB);
        }
    }
    
    public static void muestraPromociones(int[][] asientosPalco, int[][] asientosPA, int[][] asientosPB){
        Scanner teclado = new Scanner(System.in);
        
        String correccionEspacios;
        
        tipoEntradas[0] = "ZONA A - PALCO";
        tipoEntradas[1] = "ZONA B - TRIBUNA BAJA";
        tipoEntradas[2] = "ZONA C - TRIBUNA ALTA";
        
        precioEntradas[0] = "$15.000";
        precioEntradas[1] = "$10.000";
        precioEntradas[2] = "$ 7.000";
        
        limpiaPantalla();
        System.out.println("-- PRECIOS DE ENTRADAS --");
        
        for(int i=0; i<tipoEntradas.length; i++){
            correccionEspacios = (i == 0)? ":        " : ": ";
            System.out.println(tipoEntradas[i]+correccionEspacios+precioEntradas[i]);
        }
        
        System.out.println("");
        System.out.println("-- PROMOCIONES DISPONIBLES --");
        
        System.out.println("DESCUENTO ESTUDIANTE:   "+descuentoEstudiante+"%");
        System.out.println("DESCUENTO TERCERA EDAD: "+descuentoTerceraEdad+"%");
        System.out.println("* Para acceder al descuento de 3ra edad, debe ser mayor de 60 años");
        
        System.out.println("");
        
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();
       
        limpiaPantalla();
        menuPrincipal(asientosPalco, asientosPA, asientosPB);
    }
    
    public static void main(String[] args) {
        int[][] asientosPalco = new int[2][10];
        int[][] asientosPA = new int[5][12];
        int[][] asientosPB = new int[3][8];

        Random random = new Random();
        for (int i=0; i<5; i++){
            int filaPalco = random.nextInt(2);
            int columnaPalco = random.nextInt(10);
            asientosPalco[filaPalco][columnaPalco] = 1;
        }
        
        for (int i=0; i<15; i++){
            int filaPA = random.nextInt(5);
            int columnaPA = random.nextInt(12);
            asientosPA[filaPA][columnaPA] = 1;
        }
        
        for (int i=0; i<10; i++){
            int filaPB = random.nextInt(3);
            int columnaPB = random.nextInt(8);
            asientosPB[filaPB][columnaPB] = 1;
        }
        
        limpiaPantalla();
        menuPrincipal(asientosPalco, asientosPA, asientosPB);
    }
    
}
