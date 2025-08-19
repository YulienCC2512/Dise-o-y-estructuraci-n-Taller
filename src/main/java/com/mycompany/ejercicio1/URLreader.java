

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio1;
import java.io.*;
import java.net.*;

/**
 *
 * @author julian.cardenas-c
 */
public class URLreader {
     public static void main(String[] args) {
        try {
            
            // Crear objeto URL (puedes cambiar la URL por cualquier otra)
            URL url = new URL("https://www.w3schools.com/java/");

            // Mostrar la información obtenida de los métodos
            System.out.println("Protocolo: " + url.getProtocol());       
            System.out.println("Autoridad: " + url.getAuthority());     
            System.out.println("Host: " + url.getHost());               
            System.out.println("Puerto: " + url.getPort());             
            System.out.println("Ruta (Path): " + url.getPath());        
            System.out.println("Consulta (Query): " + url.getQuery());  
            System.out.println("Archivo (File): " + url.getFile());     
            System.out.println("Referencia (Ref): " + url.getRef());    

        } catch (Exception e) {
            System.out.println("Ocurrió un error al procesar la URL: " + e.getMessage());
        }
        
    }
}
