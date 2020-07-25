package appice.nudal.Models;
import org.json.simple.JSONObject;

/**
 * @author Marcos Junior
 */
public class POJO_Modalidade {
     public int id;
     public String tipo;
     public String nome;
     public String professor;
     public String horario;
     public String informacoes;
     public String foto64;

    //CONSTANTES-----------------------------------------------------------
    public static final String LUTA = "luta";
    public static final String DANCA = "danca";
    //CONSTRUTORES--------------------------------------------------------------
    public POJO_Modalidade() {
        this.tipo = "";
        this.nome = "";
        this.professor = "";
        this.horario = "";
        this.informacoes = "";
        this.foto64 = "";
    }
    public POJO_Modalidade(JSONObject json) {
        this.id = Integer.valueOf((String) json.get("id"));
        this.tipo = (String) json.get("tipo");
        this.nome = (String) json.get("nome");
        this.professor = (String) json.get("professor");
        this.horario = (String) json.get("horario");
        this.informacoes = (String) json.get("informacoes");
        this.foto64 = (String) json.get("foto64");
    }
    //MÉTODOS-------------------------------------------------------------------
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("id", String.valueOf(this.id));
        json.put("tipo", this.tipo);
        json.put("nome", this.nome);
        json.put("professor", this.professor);
        json.put("horario", this.horario);
        json.put("informacoes", this.informacoes);
        json.put("foto64", this.foto64);        
        return json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public String getFoto64() {
        return foto64;
    }

    public void setFoto64(String foto64) {
        this.foto64 = foto64;
    }
    
}