/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phili
 */
public class BDVeiculos {

    public BDVeiculos() {
    }

    private static Carga veicCarga = new Carga();
    private static Passeio veicPasseio = new Passeio();

    private static List<Passeio> veiculos = new ArrayList<>();
    private static List<Carga> veiculosC = new ArrayList<>();

    public void adicionarPasseio(Passeio elemento) {
        veiculos.add(elemento);
    }

    public void adicionarCarga(Carga elemento) {
        veiculosC.add(elemento);
    }

    public static void verificarPlacaExistenteCarga(String Placa) throws VeicExistException {
        for (Carga carga : veiculosC) {
            if (carga.getPlaca().equalsIgnoreCase(Placa)) {
                throw new VeicExistException();
            }
        }
    }

    public static void verificarPlacaExistentePasseio(String Placa) throws VeicExistException {
        for (Passeio passeio : veiculos) {
            if (passeio.getPlaca().equalsIgnoreCase(Placa)) {
                throw new VeicExistException();
            }
        }
    }

    public static Passeio getVeicPasseio() {
        return veicPasseio;
    }

    public static void setVeicPasseio(Passeio veicPasseio) {
        BDVeiculos.veicPasseio = veicPasseio;
    }

    public static Carga getVeicCarga() {
        return veicCarga;
    }

    public static void setVeicCarga(Carga veicCarga) {
        BDVeiculos.veicCarga = veicCarga;
    }

    public static List<Passeio> getVeiculos() {
        return veiculos;
    }

    public static void setVeiculos(List<Passeio> veiculos) {
        BDVeiculos.veiculos = veiculos;
    }

    public static List<Carga> getVeiculosC() {
        return veiculosC;
    }

    public static void setVeiculosC(List<Carga> veiculosC) {
        BDVeiculos.veiculosC = veiculosC;
    }

    public static void imprimirPasseio(Passeio veicPasseio, int i) {
        System.out.println("=============================================================");
        System.out.println("\tVeiculo de passeio:" + i);
        System.out.println("=============================================================");
        System.out.println("\tPlaca: " + veicPasseio.getPlaca());
        System.out.println("\tMarca: " + veicPasseio.getMarca());
        System.out.println("\tModelo: " + veicPasseio.getModelo());
        System.out.println("\tCor: " + veicPasseio.getCor());
        System.out.println("\tVelocida Máxima: " + veicPasseio.getVelocMax() + " KM/H");
        System.out.println("\tQuantidade de rodas: " + veicPasseio.getQtdRodas());
        System.out.println("\tQuantidade de Passageiros: " + veicPasseio.getqtdPassageiros());
        System.out.println("\tQuantidaed de Pistões: " + veicPasseio.getMotor().getQtdPist());
        System.out.println("\tPôtencia: " + veicPasseio.getMotor().getPotencia());
        System.out.println("\tVelocida Máxima: " + veicPasseio.calcVelox() + " M/H");// MÉTODO CALCULAR VELOCIDADE!
        System.out.println("\tValor Calculado na Interface: " + veicPasseio.calcular());//MÉTODO CALCULAR STRING
    }

    public static void imprimirCarga(Carga veicCarga, int i) {
        System.out.println("=============================================================");
        System.out.println("\tVeiculo de Carga:" + i);
        System.out.println("=============================================================");
        System.out.println("\tPlaca: " + veicCarga.getPlaca());
        System.out.println("\tMarca: " + veicCarga.getMarca());
        System.out.println("\tModelo: " + veicCarga.getModelo());
        System.out.println("\tCor: " + veicCarga.getCor());
        System.out.println("\tVelocida Máxima: " + veicCarga.getVelocMax() + " KM/H");
        System.out.println("\tQuantidade de rodas: " + veicCarga.getQtdRodas());
        System.out.println("\tQuantidaed de Pistões: " + veicCarga.getMotor().getQtdPist());
        System.out.println("\tPôtencia: " + veicCarga.getMotor().getPotencia());
        System.out.println("\tCarga Máxima: " + veicCarga.getCargaMax());
        System.out.println("\tTara: " + veicCarga.getTara());
        System.out.printf("\tVelocida Máxima: %.2f C/H \n", veicCarga.calcVelox());// MÉTODO CALCULAR VELOCIDADE!
        System.out.println("\tValor Calculado na Interface: " + veicCarga.calcular());//MÉTODO CALCULAR INT
    }
    
    
}
