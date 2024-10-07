/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

/**
 *
 * @author philippe Giovanelli
 */
public class Motor {

    private int qtdPist;
    private int potencia;

// construtor Default_______________________________
    
    public Motor() {
        qtdPist = 0;
        potencia = 0;
    }
    
    // construtor sobrecarregado_______________________________  
    
        public Motor(int qtdPist, int potencia) {
        this.qtdPist = qtdPist;
        this.potencia = potencia;
        }
        
        
// Acessores e modificadores_______________________ 

    public int getQtdPist() {return this.qtdPist;}
    public final void setQtdPist(int qtdPist) {this.qtdPist = qtdPist;}
    
    public int getPotencia() {return this.potencia;}
    public final void setPotencia(int potencia) {this.potencia = potencia;}
}
