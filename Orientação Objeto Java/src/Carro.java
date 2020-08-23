public class Carro {
    private Integer marcha;
    private String modelo;
    private String cor;
    private String chassi;
    private String proprietario;
    private Integer autonomiaViagem;
    private Integer velocidadeMaxima;
    private Integer velocidadeAtual;
    private Integer numeroPortas;
    private Boolean tetoSolar;
    private Integer numeroMarchas;
    private Boolean cambioAuto;
    private Integer volumeCombustivel;
    public Carro(Proprietario proprietario){
        this.marcha = 0;
        this.modelo = "";
        this.cor = "";
        this.chassi = "";
        this.autonomiaViagem = 0;
        this.velocidadeMaxima = 0;
        this.velocidadeAtual = 0;
        this.numeroPortas = 0;
        this.tetoSolar = null;
        this.numeroMarchas = 0;
        this.cambioAuto = null;
        this.volumeCombustivel = 0;
    }
    public Integer getAutonomiaViagem(Integer consumoMedio){
        this.autonomiaViagem = consumoMedio*this.volumeCombustivel;
        return this.autonomiaViagem;
    }
    public String getModelo(){
        return this.modelo;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public String getCor(){
        return this.cor;
    }
    public void setCor(String cor){
        this.cor = cor;
    }
    public String getChassi(){
        return this.chassi;
    }
    public void setChassi(String chassi){
        this.chassi = chassi;
    }
    public String getProprietario(){
        return this.proprietario;
    }
    public void setProprietario(String proprietario){
        this.proprietario = proprietario;
    }
    public Integer getVelocidadeMaxima(){
        return this.velocidadeMaxima;
    }
    public void setVelocidadeMaxima(Integer velocidadeMaxima){
        this.velocidadeMaxima = velocidadeMaxima;
    }
    public Integer getVelocidadeAtual(){
        return this.velocidadeAtual;
    }
    public void setVelocidadeAtual(Integer velocidadeAtual){
        this.velocidadeAtual = velocidadeAtual;
    }
    public Integer getNumeroPortas(){
        return this.numeroPortas;
    }
    public void setNumeroPortas(Integer numeroPortas){
        this.numeroPortas = numeroPortas;
    }
    public Boolean getTetoSolar(){
        return this.tetoSolar;
    }
    public void setTetoSolar(Boolean tetoSolar){
        this.tetoSolar = tetoSolar;
    }
    public Integer getNumeroMarchas(){
        return this.numeroMarchas;
    }
    public void setNumeroMarchas(Integer numeroMarchas){
        this.numeroMarchas = numeroMarchas;
    }
    public Boolean getCambioAuto(){
        return this.cambioAuto;
    }
    public void setCambioAuto(Boolean cambioAuto){
        this.cambioAuto = cambioAuto;
    }
    public Integer getVolumeCombustivel(){
        return this.volumeCombustivel;
    }
    public void setVolumeCombustivel(Integer volumeCombustivel){
        this.volumeCombustivel = volumeCombustivel;
    }
    public void acelerar(){
        this.velocidadeAtual += 1;
    }
    public void reduzir(){
        this.velocidadeAtual -= 1;
    }
    public void frear(){
        this.velocidadeAtual = 0;
    }
    public void trocarMarcha(){
        if(marcha<numeroMarchas) {
            this.marcha += 1;
        }
    }
    public void reduzirMarcha(){
        if(marcha>1) {
            this.marcha -= 1;
        }
    }
    public Integer getMarcha(){
        return this.marcha;
    }
}
