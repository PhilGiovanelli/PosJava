/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

/**
 *
 * @author phili
 */
final public class Carga extends Veiculo implements Calcular {
 
    private int cargaMax;
      private int tara;
    
    
 //Construtor default _____________________________________________ 
    
    public Carga(){
        
        this.cargaMax=0;
        this.tara=0;
    }
    
    // Métodos------------------------------------

   
   @Override
    public  float calcVelox(){
        return this.getVelocMax()*100000;
    } 

    @Override
    public int calcular(){
        int somaNumero=0;
        
        somaNumero+=getMotor().getQtdPist();
        somaNumero+=getMotor().getPotencia();
        somaNumero+=getQtdRodas();
        somaNumero+=getVelocMax();
        somaNumero+=getTara();
        somaNumero+=getCargaMax();
        return somaNumero;
    }
    
    //Acessores e modificadores______________________________________________
    
    public int getCargaMax() {return cargaMax;}
   final public void setCargaMax(int cargaMax){this.cargaMax = cargaMax;}

    public int getTara() {return tara;}
    final public void setTara(int tara){this.tara = tara;}
    
    
    
    
}


/*
   public int calcular(){

        int somaN=0;

        somaN = somaN+getVelocMax());
        somaN = somaN+getQtdRodas();
        somaN = somaN+getMotor.getQtdPist();
        somaN = somaN+getMotor.getPotencia();
        somaN = somaN+getCargaMax();
        somaN = somaN+getTara();
        return somaN;} */