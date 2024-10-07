/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

/**
 *
 * @author philippe Giovanelli                         indentação automatica=Alt+Shift+F
 */
public abstract class  Veiculo {

    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private float velocMax;
    private int qtdRodas;
    private Motor motor;

// construtor Default_______________________________
   
    public Veiculo() {
        placa = "";
        marca = "";
        modelo = "";//falta o this
        cor = "";
        velocMax = 0;
        qtdRodas = 0;
        motor = new Motor();
    }
    
  /*/ construtor sobrecarregado_______________________________  
    
        public Veiculo(String placa,String marca,String modelo,String cor, float velocMax, int qtdRodas, int qtdPist, int potencia) { //como colocar outro objeto 
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.velocMax = velocMax;
        this.qtdRodas = qtdRodas;
        this.motor = new Motor(qtdPist,potencia);
    }*/
    
// Métodos------------------------------------

    public abstract float calcVelox();     
        
// Acessores e modificadores_______________________
    
    public String getPlaca() {return this.placa;}
    public final void setPlaca(String placa) {this.placa = placa;}
    
    public String getMarca() {return this.marca;}
    public final void setMarca(String marca) {this.marca = marca;}
    
    public String getModelo() {return this.modelo;}
    public final void setModelo(String modelo) {this.modelo = modelo;}
    
    public String getCor() {return this.cor;}
    public final void setCor(String cor) {this.cor = cor;}
    
    public float getVelocMax() {return this.velocMax;}
    public final void setVelocMax(float velocMax)throws VelocException {
        if((velocMax<80)||(velocMax>110)){ throw new VelocException();
        
        } 
        else this.velocMax = velocMax;}
    
    public int getQtdRodas() {return this.qtdRodas;}
    public final void setQtdRodas(int qtdRodas) {this.qtdRodas = qtdRodas;}

    public Motor getMotor() {return this.motor;}
    public final void setMotor(Motor motor) {this.motor = motor;}
    
    
    
}
