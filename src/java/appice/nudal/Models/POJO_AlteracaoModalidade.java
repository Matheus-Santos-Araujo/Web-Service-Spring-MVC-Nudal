package appice.nudal.Models;
import org.json.simple.JSONObject;

/**
 * @author Marcos Junior
 */
public class POJO_AlteracaoModalidade {
    public static final String PedidoAberto = "aberto";
    public static final String PedidoNegado = "negado";
    public static final String PedidoAceito = "aceito";
    public int id;
    public int id_modalidade;
    public String tipo;
    public String nome;
    public String professor;
    public String horario;
    public String informacoes;
    public String foto64;
    public String situacao;
    public String detalhe;

    //CONSTANTES-----------------------------------------------------------
    public static final String LUTA = "luta";
    public static final String DANCA = "danca";
    //CONSTRUTORES--------------------------------------------------------------
    public POJO_AlteracaoModalidade() {
        this.tipo = "";
        this.nome = "";
        this.professor = "";
        this.horario = "";
        this.informacoes = "";
        this.foto64 = "";
        this.situacao = "";
        this.detalhe = "";
    }
    public POJO_AlteracaoModalidade(JSONObject json) {
        this.id = Integer.valueOf((String) json.get("id"));
        this.id_modalidade = Integer.valueOf((String) json.get("id_modalidade"));
        this.tipo = (String) json.get("tipo");
        this.nome = (String) json.get("nome");
        this.professor = (String) json.get("professor");
        this.horario = (String) json.get("horario");
        this.informacoes = (String) json.get("informacoes");
        this.foto64 = (String) json.get("foto64");
        this.situacao = (String) json.get("situacao");
        this.detalhe = (String) json.get("detalhe");
    }
    //MÉTODOS-------------------------------------------------------------------
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("id", String.valueOf(this.id));
        json.put("id_modalidade", String.valueOf(this.id_modalidade));
        json.put("tipo", this.tipo);
        json.put("nome", this.nome);
        json.put("professor", this.professor);
        json.put("horario", this.horario);
        json.put("informacoes", this.informacoes);
        json.put("foto64", this.foto64);      
        json.put("situacao", this.situacao);
        json.put("detalhe", this.detalhe); 
        return json;
    }
    public POJO_Modalidade pegaModalidade(){
        POJO_Modalidade modalidade =  new POJO_Modalidade();
        modalidade.id = this.id_modalidade;
        modalidade.tipo = this.tipo;
        modalidade.nome = this.nome;
        modalidade.professor = this.professor;
        modalidade.horario = this.horario;
        modalidade.informacoes = this.informacoes;
        modalidade.foto64 = this.foto64;
        return modalidade;
    }
    
}