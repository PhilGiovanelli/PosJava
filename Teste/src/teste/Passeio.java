/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

/**
 *
 * @author phili
 */
final public class Passeio extends Veiculo implements Calcular {

    private int qtdPassageiros;

//Construtor default _____________________________________________    
    public Passeio() {

        this.qtdPassageiros = 0;
    }

    // Métodos------------------------------------
    @Override
    public float calcVelox() {
        return this.getVelocMax() * 1000;
    }

    @Override
    public int calcular() {
        int somaCaracteres = 0;

        somaCaracteres+=getPlaca().length();
        somaCaracteres+=getMarca().length();
        somaCaracteres+=getModelo().length(); 
        somaCaracteres+=getCor().length();
        
        return somaCaracteres;
                        
    }

//Acessores e modificadores______________________________________________
    public final void setQtdPassageiros(int qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }

    public int getqtdPassageiros() {
        return this.qtdPassageiros;
    }

}

/*public int calcular(){

        int somaC=0;

        somaC = somaC+getPlaca().length();
        somaC = somaC+getMarca().length();
        somaC = somaC+getModelo().length();
        somaC = somaC+getCor().length();
        return somaC;
    }
 */
