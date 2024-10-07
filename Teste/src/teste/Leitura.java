/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author phili
 */
public class Leitura {
    
    public String entDados(String rotulo){
        
        System.out.println(rotulo);
        
        InputStreamReader teclado = new InputStreamReader(System.in);
        BufferedReader buff = new BufferedReader(teclado);
        
        String ret="";
        
        try {ret= buff.readLine();}
        catch (IOException ioe) {System.out.println("\n Erro");}
        
        return ret;
            
    }   
        
   
    
}
