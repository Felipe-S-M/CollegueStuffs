import com.sun.nio.file.ExtendedWatchEventModifier;

import java.util.Date;

public class Proprietario {
    private String nome;
    private Integer cpf;
    private Integer rg;
    private Date dataNascimento;
    public Proprietario(){
        this.nome = "";
        this.cpf = 0;
        this.rg = 0;
        this.dataNascimento = null;
    }
    public StringBuilder gerador(Endereco endereco){
        StringBuilder dados = new StringBuilder();
        dados.append("Nome: "+this.nome).append("\n CPF: "+this.cpf);
        dados.append("\nRG: "+this.rg).append("\nData de Nascimento: "+this.dataNascimento);
        dados.append("\nRua: "+endereco.getRua()).append("\nBairro: "+endereco.getBairro());
        dados.append("\nCidade: "+endereco.getCidade()).append("\nEstado: "+endereco.getEstado());
        dados.append("\nCEP: "+endereco.getCep()).append("\nComplemento: "+endereco.getComplemento());
        return dados;
    }
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public Integer getCpf(){
        return this.cpf;
    }
    public void setCpf(int cpf){
        this.cpf = cpf;
    }
    public Integer getRg(){
        return this.rg;
    }
    public void setRg(int rg){
        this.rg = rg;
    }
    public Date getDataNascimento(){
        return this.dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }
}
