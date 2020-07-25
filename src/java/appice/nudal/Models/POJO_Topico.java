/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appice.nudal.Models;

import org.json.simple.JSONObject;

/**
 *
 * @author Marcos Junior
 */
public class POJO_Topico {
    public int id;
    public int modalidade;
    public String foto64;
    public String titulo;
    public String texto;

    //CONSTRUTORES--------------------------------------------------------------
    public POJO_Topico() {
        this.foto64 = "";
        this.titulo = "";
        this.texto = "";
        
    }
    public POJO_Topico(JSONObject json) {
        this.id = Integer.valueOf((String) json.get("id"));
        this.modalidade = Integer.valueOf((String) json.get("modalidade"));
        this.foto64 = (String) json.get("foto64");
        this.titulo = (String) json.get("titulo");
        this.texto = (String) json.get("texto");
    }
    //MÉTODOS-------------------------------------------------------------------
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("id", String.valueOf(this.id));
        json.put("modalidade", String.valueOf(this.modalidade));
        json.put("foto64", this.foto64);
        json.put("titulo", this.titulo);
        json.put("texto", this.texto);
        return json;
    }
    
}