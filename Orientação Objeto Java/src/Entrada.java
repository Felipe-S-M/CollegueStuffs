import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Entrada {
    private StringBuilder proprietario;
    public void endereco(Endereco endereco){
        String rua = JOptionPane.showInputDialog("Entre com a rua do proprietário: ");
        String bairro = JOptionPane.showInputDialog("Entre com a bairro do proprietário: ");
        String cidade = JOptionPane.showInputDialog("Entre com a cidade: ");
        String estado = JOptionPane.showInputDialog("Entre com o Estado em que o proprietário mora: ");
        String cep = JOptionPane.showInputDialog("Entre com o CEP: ");
        String complemento = JOptionPane.showInputDialog("Entre com o complemento: ");
        endereco.setComplemento(complemento);
        endereco.setCep(cep);
        endereco.setEstado(estado);
        endereco.setCidade(cidade);
        endereco.setBairro(bairro);
        endereco.setRua(rua);
    }
    public void proprietario(Proprietario proprietario){
        String nome = JOptionPane.showInputDialog("Entre com o nome do proprietário: ");
        Integer cpf =  Integer.parseInt(JOptionPane.showInputDialog("Entre com o CPF do proprietário: "));
        Integer rg =  Integer.parseInt(JOptionPane.showInputDialog("Entre com o RG do proprietário: "));
        Date dataNascimento = new Date();
        Boolean validador = null;
        do {
            try {
                String entradaDat = JOptionPane.showInputDialog("Entre com a data de nascimento do proprietário: ");
                dataNascimento = new SimpleDateFormat("dd.MM.yyyy").parse(entradaDat);
                validador = true;
            } catch (ParseException e){
                validador = false;
                JOptionPane.showMessageDialog(null, "Erro na digitação");
            }
        }while(!validador);
        proprietario.setNome(nome);
        proprietario.setCpf(cpf);
        proprietario.setRg(rg);
        proprietario.setDataNascimento(dataNascimento);
    }
    public void carro(Carro carro){
        Integer escolha = null;
        String msg = "1 - Sim \n" + "2 - nao";
        Boolean tetoSolar = true;
        Boolean cambioAuto = true;
        String modelo = JOptionPane.showInputDialog("Entre com o modelo do carro: ");
        String cor = JOptionPane.showInputDialog("Entre com a cor do carro: ");
        String chassi= JOptionPane.showInputDialog("Entre com o chassi: ");
        Integer velocidadeMaxima = Integer.parseInt(JOptionPane.showInputDialog("Entre com a velocidade maxima do carro: "));
        Integer numeroPortas = Integer.parseInt(JOptionPane.showInputDialog("Entre com a quantidade de portas do carro: "));
        escolha = Integer.parseInt(JOptionPane.showInputDialog("Possui teto solar? : \n"+msg));
        if(escolha == 1) {
            tetoSolar = true;
        }else{ tetoSolar = false;
        }
        int numeroMarchas = Integer.parseInt(JOptionPane.showInputDialog("Entre com o numero de marchas maximas do carro: ")); ;
        escolha = Integer.parseInt(JOptionPane.showInputDialog("Possui cambio automatico? : \n"+msg));
        if(escolha == 1) {
            cambioAuto = true;
        }else{
            cambioAuto = false;
        }
        Integer volumeCombustivel = Integer.parseInt(JOptionPane.showInputDialog("Entre com o volume de combustível: "));;
        carro.setModelo(modelo);
        carro.setCor(cor);
        carro.setChassi(chassi);
        carro.setVelocidadeMaxima(velocidadeMaxima);
        carro.setNumeroPortas(numeroPortas);
        carro.setTetoSolar(tetoSolar);
        carro.setNumeroMarchas(numeroMarchas);
        carro.setCambioAuto(cambioAuto);
        carro.setVolumeCombustivel(volumeCombustivel);
    }
    public void marca(Marca marca){
        String nome = JOptionPane.showInputDialog("Entre com o nome da marca: ");
        Integer numeroLancamento = Integer.parseInt(JOptionPane.showInputDialog("Entre com a quantidade de lançamentos: "));
        String codigoId = JOptionPane.showInputDialog("Entre com o codigo de identificação: ");
        Date anoLancamento = new Date();
        Boolean validador = true;
        do {
            try {
                String entradaAnoDeLanca = JOptionPane.showInputDialog("Entre com o ano de lançamento: ");
                anoLancamento = new SimpleDateFormat("dd.MM.yyyy").parse(entradaAnoDeLanca);
                validador = true;
            } catch (ParseException e) {
                validador = false;
                JOptionPane.showMessageDialog(null, "Erro na digitção");
            }
        }while(!validador);
        marca.setNome(nome);
        marca.setNumeroLancamento(numeroLancamento);
        marca.setAnoLancamento(anoLancamento);
        marca.setCodigoId(codigoId);
    }
    public StringBuilder getProprietario(){
        return this.proprietario;
    }
}
