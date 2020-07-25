package appice.nudal.Models;

import org.json.simple.JSONObject;
/**
 * @author Marcos Junior
 */
public class POJO_Inscricao {
    public int id;
    public int modalidade;
    public String nome;
    public String telfixo;
    public String telcelular;
    public String email;
    public String endereco_rua;
    public String endereco_numero;
    public String tiposanguineo;
    public String observacoes;
    public String atividade;
    public String foto64;
    public String sexo;
    public String peso;
    public String altura;
    public String rg_numero;
    public String rg_expedicao;
    public String rg_expedidor;
    public String cpf;
    public String nome_pai;
    public String nome_mae;
    public String ies;
    public String nome_emergencia;
    public String telefone_emergencia;
    public String nascimento;

    //CONSTRUTORES--------------------------------------------------------------
    public POJO_Inscricao() {
        int modalidade = 0;
        this.nome = "";
        this.telfixo = "";
        this.telcelular = "";
        this.email = "";
        this.endereco_rua = "";
        this.endereco_numero = "";
        this.tiposanguineo = "";
        this.observacoes = "";
        this.atividade = "";
        this.foto64 = "";
        this.sexo = "";
        this.peso = "";
        this.altura = "";
        this.rg_numero = "";
        this.rg_expedicao = "";
        this.rg_expedidor = "";
        this.cpf = "";
        this.nome_pai = "";
        this.nome_mae = "";
        this.ies = "";
        this.nome_emergencia = "";
        this.telefone_emergencia = "";
        this.nascimento = "";
    }
    public POJO_Inscricao(JSONObject json) {
        try{
            this.id = Integer.valueOf((String) json.get("id"));
            this.modalidade = Integer.valueOf((String) json.get("modalidade"));
            this.nome = (String) json.get("nome");
            this.telfixo = (String) json.get("telfixo");
            this.telcelular = (String) json.get("telcelular");
            this.email = (String) json.get("email");
            this.endereco_rua = (String) json.get("endereco_rua");
            this.endereco_numero = (String) json.get("endereco_numero");
            this.tiposanguineo = (String) json.get("tiposanguineo");
            this.observacoes = (String) json.get("observacoes");
            this.atividade = (String) json.get("atividade");
            this.foto64 = (String) json.get("foto64");
            this.sexo = (String) json.get("sexo");
            this.peso = (String) json.get("peso");
            this.altura = (String) json.get("altura");
            this.rg_numero = (String) json.get("rg_numero");
            this.rg_expedicao = (String) json.get("rg_expedicao");
            this.rg_expedidor = (String) json.get("rg_expedidor");
            this.cpf = (String) json.get("cpf");
            this.nome_pai = (String) json.get("nome_pai");
            this.nome_mae = (String) json.get("nome_mae");
            this.ies = (String) json.get("ies");
            this.nome_emergencia = (String) json.get("nome_emergencia");
            this.telefone_emergencia = (String) json.get("telefone_emergencia");
            this.nascimento = (String) json.get("nascimento");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //MÉTODOS-------------------------------------------------------------------
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("id",String.valueOf(this.id));
        json.put("modalidade",String.valueOf(this.modalidade));
        json.put("nome",this.nome);
        json.put("telfixo",this.telfixo);
        json.put("telcelular",this.telcelular);
        json.put("email",this.email);
        json.put("endereco_rua",this.endereco_rua);
        json.put("endereco_numero",this.endereco_numero);
        json.put("tiposanguineo",this.tiposanguineo);
        json.put("observacoes",this.observacoes);
        json.put("atividade",this.atividade);
        json.put("foto64",this.foto64);
        json.put("sexo",this.sexo);
        json.put("peso",this.peso);
        json.put("altura",this.altura);
        json.put("rg_numero",this.rg_numero);
        json.put("rg_expedicao",this.rg_expedicao);
        json.put("rg_expedidor",this.rg_expedidor);
        json.put("cpf",this.cpf);
        json.put("nome_pai",this.nome_pai);
        json.put("nome_mae",this.nome_mae);
        json.put("ies",this.ies);
        json.put("nome_emergencia",this.nome_emergencia);
        json.put("telefone_emergencia",this.telefone_emergencia);
        json.put("nascimento",this.nascimento);        
        return json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModalidade() {
        return modalidade;
    }

    public void setModalidade(int modalidade) {
        this.modalidade = modalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelfixo() {
        return telfixo;
    }

    public void setTelfixo(String telfixo) {
        this.telfixo = telfixo;
    }

    public String getTelcelular() {
        return telcelular;
    }

    public void setTelcelular(String telcelular) {
        this.telcelular = telcelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco_rua() {
        return endereco_rua;
    }

    public void setEndereco_rua(String endereco_rua) {
        this.endereco_rua = endereco_rua;
    }

    public String getEndereco_numero() {
        return endereco_numero;
    }

    public void setEndereco_numero(String endereco_numero) {
        this.endereco_numero = endereco_numero;
    }

    public String getTiposanguineo() {
        return tiposanguineo;
    }

    public void setTiposanguineo(String tiposanguineo) {
        this.tiposanguineo = tiposanguineo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getFoto64() {
        return foto64;
    }

    public void setFoto64(String foto64) {
        this.foto64 = foto64;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getRg_numero() {
        return rg_numero;
    }

    public void setRg_numero(String rg_numero) {
        this.rg_numero = rg_numero;
    }

    public String getRg_expedicao() {
        return rg_expedicao;
    }

    public void setRg_expedicao(String rg_expedicao) {
        this.rg_expedicao = rg_expedicao;
    }

    public String getRg_expedidor() {
        return rg_expedidor;
    }

    public void setRg_expedidor(String rg_expedidor) {
        this.rg_expedidor = rg_expedidor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome_pai() {
        return nome_pai;
    }

    public void setNome_pai(String nome_pai) {
        this.nome_pai = nome_pai;
    }

    public String getNome_mae() {
        return nome_mae;
    }

    public void setNome_mae(String nome_mae) {
        this.nome_mae = nome_mae;
    }

    public String getIes() {
        return ies;
    }

    public void setIes(String ies) {
        this.ies = ies;
    }

    public String getNome_emergencia() {
        return nome_emergencia;
    }

    public void setNome_emergencia(String nome_emergencia) {
        this.nome_emergencia = nome_emergencia;
    }

    public String getTelefone_emergencia() {
        return telefone_emergencia;
    }

    public void setTelefone_emergencia(String telefone_emergencia) {
        this.telefone_emergencia = telefone_emergencia;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
    
}