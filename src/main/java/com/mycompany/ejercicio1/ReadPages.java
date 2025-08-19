/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio1;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author julian.cardenas-c
 */
public class ReadPages {
   public static void main(String[] args) throws Exception {
       
        // Dirección del sitio web que deseas consultar
        String site = "http://www.google.com"; // Puedes cambiar esta URL

        // Crea el objeto que representa una URL
        URL siteURL = new URL(site);

        // Crea el objeto URLConnection
        URLConnection urlConnection = siteURL.openConnection();

        // Obtiene los campos del encabezado y los almacena en una estructura Map
        Map<String, List<String>> headers = urlConnection.getHeaderFields();

        // Obtiene una vista del mapa como conjunto de pares <K,V> para poder navegarlo
        Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();

        // Recorre la lista de campos e imprime los valores
        for (Map.Entry<String, List<String>> entry : entrySet) {
            String headerName = entry.getKey();
            // Si el nombre es nulo, significa que es la línea de estado
            if (headerName != null) {
                System.out.print(headerName + ": ");
            }
            List<String> headerValues = entry.getValue();
            for (String value : headerValues) {
                System.out.print(value);
            }
            System.out.println();
        }

        System.out.println("-------message-body------");

        try{
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(urlConnection.getInputStream())
        );
        
            
       URL google = new URL("http://www.google.com/");
       
      // try (BufferedReader reader = new BufferedReader(new InputStreamReader(google.openStream()))) {
           String inputLine = null;
        while ((inputLine = reader.readLine()) != null) {   
       System.out.println(inputLine);
        }
        }catch (IOException x){
        System.err.println(x);
        }
}
}