
package com.mycompany.ejercicio_tema_3;

public class Ejercicio_Tema_3 {

    public static void main(String[] args) {
      
        //Primera Parte
        int a,b,c;
        suma(a=1, b=2, c=3);
        //Segunda Parte
        Coche coche=new Coche();
        coche.agregarP();
        coche.agregarP();
        coche.agregarP();
        coche.agregarP();
        coche.agregarP();
        System.out.println("El coche tiene "+coche.numeroDePuertas);
        
    }
    
    public static void suma (int a, int b, int c) {
    int resultado; 
    resultado = a + b + c;   
    System.out.println(resultado); 
    }
    
}
