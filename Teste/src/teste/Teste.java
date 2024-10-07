/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teste;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author philippe Giovanelli
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    private static Leitura leia = new Leitura();

    public static void main(String[] args) throws VelocException {

        boolean continuar = true;
        int opcao = 0;
        // Menu Principal---------------------------------------------------------------------------------

        while (continuar) {
            System.out.println("""
                =============================================================
                \tSistema de Gestão de Veículos - Menu Inicial     
                =============================================================

                \t1. Cadastrar Veiculo de Passeio
                \t2. Cadastrar Veiculo de Carga
                \t3. Imprimir Todos os Veiculos de Passeio
                \t4. Imprimir Todos os Veiculos de Carga
                \t5. Imprimir Veiculo de Passeio pela Placa
                \t6. Imprimir Veiculo de Carga pela Placa
                \t7. Excluir Veículo de Passeio pela Placa
                \t8. Excluir Veículo de Carga pela Placa
                \t9. Sair do Sistema

                =============================================================
                """);
            try {
                opcao = Integer.parseInt(leia.entDados("\tDigite uma das opções acima:"));
            } catch (NumberFormatException nfe) {
                System.out.println("OPÇÃO INVÁLIDA DIGITE UMA OPÇÃO DE 1 A 9 - PRESSIONE ENTER");
                leia.entDados("");
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarVeiculoPasseioOption();

                    break;

                case 2:
                    cadastrarVeiculoCargaOption();
                    break;

                case 3:
                    System.out.println("=============================================================");
                    System.out.println("\tImpressão dos Veiculos de PASSEIO cadastrados!");
                    System.out.println("=============================================================");
                    for (int i = 0; i < BDVeiculos.getVeiculos().size(); i++) {
                        if (BDVeiculos.getVeiculos().get(i) != null) {
                            BDVeiculos.imprimirPasseio(BDVeiculos.getVeiculos().get(i), i);
                        } else {
                            leia.entDados("\n Todos Veiculos Foram imopressos! Pressione ENTER");
                        }
                        System.out.println("=============================================================");

                    }
                    break;
                case 4:
                    System.out.println("=============================================================");
                    System.out.println("\tImpressão dos Veiculos de CARGA cadastrados!");
                    System.out.println("=============================================================");
                    for (int i = 0; i < BDVeiculos.getVeiculosC().size(); i++) {
                        if (BDVeiculos.getVeiculosC().get(i) != null) {
                            BDVeiculos.imprimirCarga(BDVeiculos.getVeiculosC().get(i), i);
                        } else {
                            leia.entDados("\n Todos Veiculos Foram impressos! Pressione ENTER");
                        }
                    }
                    System.out.println("=============================================================");
                    break;

                case 5:
                    System.out.println("=============================================================");
                    System.out.println("\tConsulta do Veiculo de PASSEIO pela Placa");
                    System.out.println("=============================================================");
                    Passeio p = new Passeio();
                    boolean verifPlaca = false;
                    String placaPasseio = leia.entDados("\n Informe a Placa: ");
                    p.setPlaca(placaPasseio);
                    for (int i = 0; i < BDVeiculos.getVeiculos().size(); i++) {
                        if (BDVeiculos.getVeiculos().get(i).getPlaca().equalsIgnoreCase(p.getPlaca())) {
                            BDVeiculos.imprimirPasseio(BDVeiculos.getVeiculos().get(i), i);
                            verifPlaca = true;
                        }
                    }
                    if (!verifPlaca) {
                        leia.entDados("\n\tVeiculo de PASSEIO não consta com esta PLACA Pressione ENTER");
                    }
                    break;

                case 6:
                    System.out.println("=============================================================");
                    System.out.println("\tConsulta do Veiculo de CARGA pela Placa");
                    System.out.println("=============================================================");
                    Carga c = new Carga();
                    boolean verifPlacaC = false;
                    String placaCarga = leia.entDados("\n Informe a Placa: ");
                    c.setPlaca(placaCarga);
                    for (int i = 0; i < BDVeiculos.getVeiculosC().size(); i++) {
                        if (BDVeiculos.getVeiculosC().get(i).getPlaca().equalsIgnoreCase(c.getPlaca())) {
                            BDVeiculos.imprimirCarga(BDVeiculos.getVeiculosC().get(i), i);
                            verifPlacaC = true;
                        }
                    }
                    if (!verifPlacaC) {
                        leia.entDados("\n\tVeiculo de CARGA não consta com esta PLACA Pressione ENTER");
                    }
                    break;

                case 7:
                    System.out.println("=============================================================");
                    System.out.println("\tExcluir Veiculo de PASSEIO pela Placa");
                    System.out.println("=============================================================");
                    Passeio pe = new Passeio();
                    verifPlaca = false;
                    placaPasseio = leia.entDados("\n Informe a Placa: ");
                    pe.setPlaca(placaPasseio);
                    for (int i = 0; i < BDVeiculos.getVeiculos().size(); i++) {
                        if (BDVeiculos.getVeiculos().get(i).getPlaca().equalsIgnoreCase(pe.getPlaca())) {
                            BDVeiculos.getVeiculos().remove(i);
                            leia.entDados("\n\tVeiculo de Passeio Removido com sucesso! Pressione ENTER");
                            
                            verifPlaca = true;
                        }
                    }
                    if (!verifPlaca) {
                        leia.entDados("\n\tVeiculo de PASSSEIO não consta com esta PLACA Pressione ENTER");
                    }
                    break;
                case 8:
                    System.out.println("=============================================================");
                    System.out.println("\tExcluir Veiculo de CARGA pela Placa");
                    System.out.println("=============================================================");
                    Carga ce = new Carga();
                    verifPlacaC = false;
                    placaCarga = leia.entDados("\n Informe a Placa: ");
                    ce.setPlaca(placaCarga);
                    for (int i = 0; i < BDVeiculos.getVeiculosC().size(); i++) {
                        if (BDVeiculos.getVeiculosC().get(i).getPlaca().equalsIgnoreCase(ce.getPlaca())) {
                            BDVeiculos.getVeiculosC().remove(i);
                            leia.entDados("\n\tVeiculo de Carga Removido com sucesso! Pressione ENTER");
                            
                            verifPlacaC = true;
                        }
                    }
                    if (!verifPlacaC) {
                        leia.entDados("\n\tVeiculo de CARGA não consta com esta PLACA Pressione ENTER");
                    }
                    break;
                case 9:
                    continuar = false;
                    break;
                default:
                    leia.entDados("OPÇÃO INVÁLIDA DIGITE UMA OPÇÃO DE 1 A 7 - PRESSIONE ENTER");
                    break;
            }
        }

    }//Fim do main

    //métodos-------------------------------------------------------------------------------------------
    public static Carga cadastroCarga(Carga veicCarga) throws VelocException {
        System.out.println("=============================================================");
        System.out.println("\tCadastro do Veiculo de carga:");
        System.out.println("=============================================================");
        veicCarga.setPlaca(leia.entDados("Placa: "));
        try {
            BDVeiculos.verificarPlacaExistenteCarga(veicCarga.getPlaca());
        } catch (VeicExistException e) {
            return null;

        }

        veicCarga.setMarca(leia.entDados("Marca: "));
        veicCarga.setModelo(leia.entDados("Modelo: "));
        veicCarga.setCor(leia.entDados("Cor: "));
        try{veicCarga.setVelocMax(Integer.parseInt(leia.entDados("Velocidade Máxima: ")));}
        catch(VelocException e){veicCarga.setVelocMax(90);}
        veicCarga.setQtdRodas(Integer.parseInt(leia.entDados("Quantidade de Rodas: ")));
        veicCarga.getMotor().setQtdPist(Integer.parseInt(leia.entDados("Quantidade Pistões: ")));
        veicCarga.getMotor().setPotencia(Integer.parseInt(leia.entDados("Potência: ")));
        veicCarga.setCargaMax(Integer.parseInt(leia.entDados("Carga Máxima: ")));
        veicCarga.setTara(Integer.parseInt(leia.entDados("Tara: ")));
        return veicCarga;
    }

    public static Passeio cadastroPasseio(Passeio veicPasseio) throws VelocException {
        System.out.println("=============================================================");
        System.out.println("\tCadastro do Veiculo de passeio:");
        System.out.println("=============================================================");
        veicPasseio.setPlaca(leia.entDados("Placa: "));
        try {
            BDVeiculos.verificarPlacaExistentePasseio(veicPasseio.getPlaca());
        } catch (VeicExistException e) {
            return null;
        }
        veicPasseio.setMarca(leia.entDados("Marca: "));
        veicPasseio.setModelo(leia.entDados("Modelo: "));
        veicPasseio.setCor(leia.entDados("Cor: "));
        try {veicPasseio.setVelocMax(Integer.parseInt(leia.entDados("Velocidade Maxima ")));}
        catch(VelocException e){
        veicPasseio.setVelocMax(100);
        }
        veicPasseio.setQtdRodas(Integer.parseInt(leia.entDados("Quantidade de Rodas: ")));
        veicPasseio.getMotor().setQtdPist(Integer.parseInt(leia.entDados("Quantidade de Pistões: ")));
        veicPasseio.getMotor().setPotencia(Integer.parseInt(leia.entDados("Potência: ")));
        veicPasseio.setQtdPassageiros(Integer.parseInt(leia.entDados("Quantidade de Passageiros: ")));
        return veicPasseio;
    }

    public static void cadastrarVeiculoCargaOption() throws VelocException {
       
        Carga veicCarga = new Carga();
        veicCarga = cadastroCarga(veicCarga);
        if (veicCarga == null) {
            return;
        }
        BDVeiculos bd = new BDVeiculos();
        bd.adicionarCarga(veicCarga);
        System.out.println("=============================================================");
        System.out.println("Veiculo de Carga cadastrado com SUCESSO! Pressione ENTER");
        System.out.println("=============================================================");
        leia.entDados("");
        System.out.println("Deseja Cadastrar outro VEICULO DE CARGA?");
        System.out.println("=============================================================");
        String respCarga = leia.entDados("Digite Sim = S ou Não= N ");
        System.out.println("=============================================================");
        if (respCarga.equalsIgnoreCase("s")) {
            cadastrarVeiculoCargaOption();
        }
    }

    public static void cadastrarVeiculoPasseioOption() throws VelocException {
        
        Passeio veicPasseio = new Passeio();
        veicPasseio = cadastroPasseio(veicPasseio);
        if (veicPasseio == null) {
            return;
        }
        BDVeiculos bd = new BDVeiculos();
        bd.adicionarPasseio(veicPasseio);
        System.out.println("=============================================================");
        System.out.println("Veiculo de Passeio cadastrado com SUCESSO! Pressione ENTER");
        System.out.println("=============================================================");
        leia.entDados("");
        System.out.println("Deseja Cadastrar outro VEICULO DE PASSEIO?");
        System.out.println("=============================================================");
        String respPasseio = leia.entDados("Digite Sim = S ou Não= N ");
        System.out.println("=============================================================");
        if (respPasseio.equalsIgnoreCase("s")) {
            cadastrarVeiculoPasseioOption();
        }
    }

}

/*
// Veiculos de Passeio---------------------------------------------------------------------
        Passeio veicPasseio1 = new Passeio();
        Passeio veicPasseio2 = new Passeio();
        Passeio veicPasseio3 = new Passeio();
        Passeio veicPasseio4 = new Passeio();

        Passeio veicPasseio5 = new Passeio();
        veicPasseio5.setPlaca("cvx-2323");
        veicPasseio5.setMarca("Chevrolet");
        veicPasseio5.setModelo("Gol GIV");
        veicPasseio5.setCor("Preto");
        veicPasseio5.setVelocMax(180);
        veicPasseio5.setQtdRodas(4);
        veicPasseio5.getMotor().setQtdPist(8);
        veicPasseio5.getMotor().setPotencia(280);
        veicPasseio5.setQtdPassageiros(4);

        Passeio veiculos[] = new Passeio[5];
        veiculos[0] = veicPasseio1;
        veiculos[1] = veicPasseio2;
        veiculos[2] = veicPasseio3;
        veiculos[3] = veicPasseio4;
        veiculos[4] = veicPasseio5;

        for (int qtdVeic = 0; qtdVeic <= 4.; qtdVeic++) {
            System.out.println("=============================================================");
            System.out.println("\tVeiculo de passeio:" + (qtdVeic + 1));
            System.out.println("=============================================================");
            System.out.println("\tPlaca: " + veiculos[qtdVeic].getPlaca());
            System.out.println("\tMarca: " + veiculos[qtdVeic].getMarca());
            System.out.println("\tModelo: " + veiculos[qtdVeic].getModelo());
            System.out.println("\tCor: " + veiculos[qtdVeic].getCor());
            System.out.println("\tVelocida Máxima: " + veiculos[qtdVeic].calcVelox() + " M/H");// MÉTODO CALCULAR VELOCIDADE!
            System.out.println("\tQuantidade de rodas: " + veiculos[qtdVeic].getQtdRodas());
            System.out.println("\tQuantidade de Passageiros: " + veiculos[qtdVeic].getqtdPassageiros());
            System.out.println("\tQuantidaed de Pistões: " + veiculos[qtdVeic].getMotor().getQtdPist());
            System.out.println("\tPôtencia: " + veiculos[qtdVeic].getMotor().getPotencia() + "\n");

        }

        // Veiculos de Cargas---------------------------------------------------------------------
        Carga veicCarga1 = new Carga();
        Carga veicCarga2 = new Carga();
        Carga veicCarga3 = new Carga();
        Carga veicCarga4 = new Carga();

        Carga veicCarga5 = new Carga();
        veicCarga5.setPlaca("aaa-2222");
        veicCarga5.setMarca("Fiat");
        veicCarga5.setModelo("Strada");
        veicCarga5.setCor("Verde");
        veicCarga5.setVelocMax(150);
        veicCarga5.setQtdRodas(4);
        veicCarga5.setCargaMax(850);
        veicCarga5.setTara(2580);
        veicCarga5.getMotor().setQtdPist(16);
        veicCarga5.getMotor().setPotencia(280);

        Carga veiculosC[] = new Carga[5];
        veiculosC[0] = veicCarga1;
        veiculosC[1] = veicCarga2;
        veiculosC[2] = veicCarga3;
        veiculosC[3] = veicCarga4;
        veiculosC[4] = veicCarga5;

        for (int qtdVeic = 0; qtdVeic <= 4.; qtdVeic++) {
            System.out.println("=============================================================");
            System.out.println("\tVeiculo de Carga:" + (qtdVeic + 1));
            System.out.println("=============================================================");
            System.out.println("\tPlaca: " + veiculosC[qtdVeic].getPlaca());
            System.out.println("\tMarca: " + veiculosC[qtdVeic].getMarca());
            System.out.println("\tModelo: " + veiculosC[qtdVeic].getModelo());
            System.out.println("\tCor: " + veiculosC[qtdVeic].getCor());
            System.out.printf("\tVelocida Máxima: %.2f C/H \n", veiculosC[qtdVeic].calcVelox());// MÉTODO CALCULAR VELOCIDADE!
            System.out.println("\tQuantidade de rodas: " + veiculosC[qtdVeic].getQtdRodas());
            System.out.println("\tCarga Máxima: " + veiculosC[qtdVeic].getCargaMax());
            System.out.println("\tTara: " + veiculosC[qtdVeic].getTara());
            System.out.println("\tQuantidaed de Pistões: " + veiculosC[qtdVeic].getMotor().getQtdPist());
            System.out.println("\tPôtencia: " + veiculosC[qtdVeic].getMotor().getPotencia() + "\n");

        }*/
