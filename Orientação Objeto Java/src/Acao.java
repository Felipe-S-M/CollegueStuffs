import javax.swing.JOptionPane;

public class Acao {
    Carro carro;
    Proprietario proprietario;
    Marca marca;
    Endereco endereco;
    public Acao(Endereco endereco, Proprietario proprietario, Marca marca, Carro carro){
        this.carro = carro;
        this.proprietario = proprietario;
        this.marca = marca;
        this.endereco = endereco;
    }
    public StringBuilder gerarMensagemMenuOpcoes(){
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("1 - carro \n").append("2 - Sair \n");
        return mensagem;
    }
    public StringBuilder gerarMensagemMenuVerificar(){
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("1 - verificar \n").append("2 - Interagir com o carro \n").append("3 - Sair");
        return mensagem;
    }
    public StringBuilder gerarMensagemMenuVerificarInfo(){
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("1 - numero de marcha \n").append("2 - modelo \n").append("3 - cor \n");
        mensagem.append("4 - chassi \n").append("5 - velocidade Maxima \n").append("6 - numero de portas \n");
        mensagem.append("7 - tetor solar \n").append("8 - cambio automatico \n").append("9 - volume combustivel \n");
        mensagem.append("10 - Autonomia de viagem \n").append("11 - Informacoes do proprietario \n").append("12 - Sair");
        return mensagem;
    }
    public StringBuilder gerarMensagemMenuInteragir(){
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("1 - aumentar velocidade \n").append("2 - reduzir velocidade \n");
        mensagem.append("3 - aumentar a marcha \n").append("4 - reduzir a marcha \n");
        mensagem.append("5 - freiar \n").append("6 - sair");
        return mensagem;
    }
    public void menuOpcoes() {
        while (true) {
           StringBuilder msgEscolha = gerarMensagemMenuOpcoes();
            Integer escolha = Integer.parseInt(JOptionPane.showInputDialog("Escolha o que deseja interagir: \n" + msgEscolha));
            switch (escolha) {
                case 1:
                    menuVerificar();
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Opção inexistente");
                    break;
            }
        }
    }
    public void menuVerificar() {
        Integer escolha = 0;
        StringBuilder msg = gerarMensagemMenuVerificar();
        escolha = Integer.parseInt(JOptionPane.showInputDialog("Escolha a acao: \n" + msg));
        while(true) {
            switch (escolha) {
                case 1:
                    menuVerificarInfo();
                    break;
                case 2:
                    menuInteragir();
                    break;
                case 3:
                    menuOpcoes();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inexistente");
                    break;
            }
        }
    }
    public void menuVerificarInfo() {
        StringBuilder msg = gerarMensagemMenuVerificarInfo();
        Integer escolha = Integer.parseInt(JOptionPane.showInputDialog("Escolha: \n"+msg));
        switch (escolha) {
            case 1:
                JOptionPane.showMessageDialog(null, carro.getNumeroMarchas(),"Resultado: ",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, carro.getModelo(), "Resultado: ",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, carro.getCor(),"Resultado: ",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 4:
                JOptionPane.showMessageDialog(null,carro.getChassi(), "Resultado: ",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 5:
                JOptionPane.showMessageDialog(null, carro.getVelocidadeMaxima(),"Resultado: ",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 6:
                JOptionPane.showMessageDialog(null,carro.getNumeroPortas(), "Resultado: ",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 7:
                JOptionPane.showMessageDialog(null,carro.getTetoSolar(), "Resultado: ",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 8:
                JOptionPane.showMessageDialog(null,carro.getCambioAuto(), "Resultado: ",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 9:
                JOptionPane.showMessageDialog(null,carro.getVolumeCombustivel(), "Resultado: ",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 10:
                Integer consumoMedio = Integer.parseInt(JOptionPane.showInputDialog("Entre com o consumo medio: "));
                JOptionPane.showMessageDialog(null, carro.getAutonomiaViagem(consumoMedio),"Resultado: ",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 11:
                JOptionPane.showMessageDialog(null,proprietario.gerador(endereco), "Resultado: ",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 12:
                menuVerificar();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Op��o inexistente");
                break;
        }

    }
    public void menuInteragir() {
        StringBuilder msg = gerarMensagemMenuInteragir();
        Integer escolha = Integer.parseInt(JOptionPane.showInputDialog("Escolha com o que deseja interagir: \n" + msg));
        switch (escolha) {
            case 1:
               aumentarVelocidade();
                break;
            case 2:
                reduzirVelocidade();
                break;
            case 3:
                aumentarMarcha();
                break;
            case 4:
                reduzirMarcha();
                break;
            case 5:
                frear();
                break;
            case 6:
                menuVerificar();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Op��o desconhecida ");
                break;
        }
    }
    public void aumentarVelocidade(){
        JOptionPane.showMessageDialog(null, "Velocidade anterior: " + carro.getVelocidadeAtual());
        Boolean isVeloAtualMarcha = carro.getVelocidadeAtual()>=20 && carro.getMarcha()<3;
        if (isVeloAtualMarcha) {
            while (carro.getMarcha() < 3) {
                JOptionPane.showMessageDialog(null, "marcha incompativel" + "aumentando a marcha...");
                carro.trocarMarcha();
            }
            carro.acelerar();
        }
        JOptionPane.showMessageDialog(null,carro.getVelocidadeAtual(), "Velocidade atual: ",JOptionPane.INFORMATION_MESSAGE);
    }
    public void reduzirVelocidade(){
        JOptionPane.showMessageDialog(null,carro.getVelocidadeAtual(), "Velocidade anterior: ",JOptionPane.INFORMATION_MESSAGE);
        carro.reduzir();
        JOptionPane.showMessageDialog(null, carro.getVelocidadeAtual(), "Velocidade atual: ",JOptionPane.INFORMATION_MESSAGE);
    }
    public void aumentarMarcha(){
        JOptionPane.showMessageDialog(null,carro.getMarcha(), "marcha anterior: ",JOptionPane.INFORMATION_MESSAGE);
        carro.trocarMarcha();
        JOptionPane.showMessageDialog(null,carro.getMarcha(), "marcha atual: ",JOptionPane.INFORMATION_MESSAGE);
    }
    public void reduzirMarcha(){
        JOptionPane.showMessageDialog(null, carro.getMarcha(),"marcha anterior: ",JOptionPane.INFORMATION_MESSAGE);
        carro.reduzirMarcha();
        JOptionPane.showMessageDialog(null,carro.getMarcha(), "marcha atual: ",JOptionPane.INFORMATION_MESSAGE);
    }
    public void frear(){
        carro.frear();
        JOptionPane.showMessageDialog(null, "Carro freiado");
    }
}
