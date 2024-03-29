/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadorLexico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author oziel
 */
public class Analizador {
    int controlCadena = 0;
    int controlChar = -1;
    boolean bandera = false;
    String texto, aux;
    LinkedList<String> lista = new LinkedList();
    public Analizador(){
        
    }
    
    public char leer() throws IOException {
        
        if(bandera == false){
        try{
            bandera = true;
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(file);
            File abre = file.getSelectedFile();
            
            //RECORRER EL ARCHIVO Y LEERLO
            if (abre != null){
                FileReader archivos = new FileReader (abre);
                BufferedReader lee = new BufferedReader (archivos);
                
                while ((aux = lee.readLine()) != null){
                   texto = aux;
                   lista.add(texto);
                }
                lee.close();
                System.out.println(lista);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
            return '.';
        }
        }
        if(controlChar==lista.get(controlCadena).length()-1){
            controlCadena++;
            controlChar = -1;
            if(controlCadena  >= lista.size()) return ' ';
        }
        controlChar++;
        return lista.get(controlCadena).charAt(controlChar);
    }
    
    public String cuartoMetodo() throws IOException{
        char x;
        int ascii;
        String cadena = "";
        x = leer();
        ascii = x;
        if(x == '@'){
            cadena += x;
            x = leer();
            ascii = x;
            if(ascii >= 97 && ascii <= 122){
                cadena += x;
                x = leer();
                ascii = x;
            }
            while(ascii >= 97 && ascii <= 122){
                cadena += x;
                x=leer();
                ascii = x;
            }
            System.out.println("Se validó el identificador");
            return cadena;
        }else if(ascii >= 48 && ascii <= 57){
                cadena += x;
                x = leer();
                ascii = x;
            while(ascii >= 48 && ascii <= 57){
                cadena += x;
                x = leer();
                ascii = x;
            }
            if(x == '.'){
                x = leer();
                ascii = x;
                if(ascii >= 48 && ascii <= 57){
                    cadena += '.';
                    cadena += x;
                    x = leer();
                    ascii = x;
                }
                while(ascii >= 48 && ascii <= 57){
                cadena += x;
                x = leer();
                ascii = x;
                }
            }
            if(cadena.length()!= 0) System.out.println("Se validó el número");
            return cadena;
        }
        else{
            if(x == '='){
                cadena += x;
                x = leer();
                if (x == '='){
                    cadena += x;
                    return cadena;
                }
            }
            if(x == '<'){
                cadena += x;
                x = leer();
                if (x == '='){
                    cadena += x;
                    return cadena;
                }
            }
            if(x == '>'){
                cadena += x;
                x = leer();
                if (x == '='){
                    cadena += x;
                    return cadena;
                }
            }
            if(x == '¡'){ 
                x = leer();
                if(x == '!'){
                    cadena += "¡!";
                    return cadena;
                }
            }
            return cadena;
        }
    }
}
