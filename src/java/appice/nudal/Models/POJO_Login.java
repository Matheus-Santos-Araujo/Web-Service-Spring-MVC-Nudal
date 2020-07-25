package appice.nudal.Models;

import org.json.simple.JSONObject;

/**
 * @author Marcos Junior
 */
public class POJO_Login {
    public String email;
    public String senha;
    public String novaSenha;
    public String tipo;

    //CONSTRUTORES--------------------------------------------------------------
    public POJO_Login() {
        this.email = "";
        this.senha = "";
        this.tipo = "";
        this.novaSenha = "";
    }
    public POJO_Login(JSONObject json) {
        this.email = (String) json.get("email");
        this.senha = (String) json.get("senha");
        this.tipo = (String) json.get("tipo");
        this.novaSenha = (String) json.get("novaSenha");
    }
    //MÉTODOS-------------------------------------------------------------------
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("email", this.email);
        json.put("senha", this.senha);
        json.put("tipo", this.tipo);
         json.put("novaSenha", this.novaSenha);
        return json;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }
}