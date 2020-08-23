import java.util.Date;

public class Marca {
    private String nome;
    private Integer numeroLancamento;
    private Date anoLancamento;
    private String codigoId;
    public Marca(){
        this.nome = null;
        this.numeroLancamento = null;
        this.anoLancamento = null;
        this.codigoId = null;
    }
    public String gerador(){
        String gerador = "Nome: "+this.nome+"\n"+
                         "Numero de lan�amentos: "+this.numeroLancamento+"\n"+
                         "Ano de lan�amento: "+this.anoLancamento+"\n"+
                         "Codigo de identifica��o: "+this.codigoId;
        return gerador;
    }
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public Integer getNumeroLancamento(){
        return this.numeroLancamento;
    }
    public void setNumeroLancamento(Integer numeroLancamento){
        this.numeroLancamento = numeroLancamento;
    }
    public Date getAnoLancamento(){
        return this.anoLancamento;
    }
    public void setAnoLancamento(Date anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
    public String getCodigoId(){
        return this.codigoId;
    }
    public void setCodigoId(String codigoId){
        this.codigoId = codigoId;
    }
}
