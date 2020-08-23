public class Principal {
       public static void main(String[] args) {
            Entrada entrada = new Entrada();
            Endereco endereco = new  Endereco();
            Proprietario proprietario = new Proprietario();
            Marca marca = new Marca();
            entrada.endereco(endereco);
            entrada.proprietario(proprietario);
            Carro carro = new Carro(proprietario);
            entrada.carro(carro);
            entrada.marca(marca);
            Acao acao = new Acao(endereco,proprietario,marca,carro);
            acao.menuOpcoes();
       }
}
