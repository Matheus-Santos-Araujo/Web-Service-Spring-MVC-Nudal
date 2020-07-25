package appice.nudal.DAO;

import appice.nudal.Models.POJO_AlteracaoModalidade;
import appice.nudal.Models.POJO_Inscricao;
import appice.nudal.Models.POJO_Login;
import appice.nudal.Models.POJO_Modalidade;
import appice.nudal.Models.POJO_Topico;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BDprincipal {
       
    private static Connection getConnection(){
        Connection con  = null;
        try{
            Class.forName("org.postgresql.Driver");
            URI dbUri = new URI("postgres:");
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
            con = DriverManager.getConnection(dbUrl, username, password);
        }catch(ClassNotFoundException| SQLException | URISyntaxException e){
            e.printStackTrace();
        }
        return con;
    }
    
    //BANCO-DE-DADOS------------------------------------------------------------
    public String iniciarBancoPrincipal(){
        String resposta = "<BR>";
        resposta = resposta + "Tabela Modalidades: " + criarTabelaModalidades() + "<BR>";
        resposta = resposta + "Tabela PedidosAlteração: " + criarTabelaPedidosAlteracao()+ "<BR>";
        resposta = resposta + "Tabela Topicos: " + criarTabelaTopicos() + "<BR>";
        resposta = resposta + "Tabela Login: " + criarTabelaLogin() + "<BR>";
        resposta = resposta + "Tabela Inscrições: " + criarTabelaInscricoes() + "<BR>";
        resposta = resposta + "Tabela RequestIds: " + criarTabelaRequestIds()+ "<BR>";
        return resposta;
    }
    public String zerarBancoPrincipal(){
        String resposta = "<BR>";
        resposta = resposta + "Tabela Modalidades: " + deletarTabelaModalidades() + "<BR>";
        resposta = resposta + "Tabela Pedidos Alteração: " + deletarTabelaPedidosAlteracao()+ "<BR>";
        resposta = resposta + "Tabela Topicos: " + deletarTabelaTopicos()+ "<BR>";
        resposta = resposta + "Tabela Login: " + deletarTabelaLogin() + "<BR>";
        resposta = resposta + "Tabela Inscrições: " + deletarTabelaInscricoes() + "<BR>";
        resposta = resposta + "Tabela RequestIds: " + deletarTabelaRequestIds()+ "<BR>";
        return resposta;
    }
    public String limparBancoPrincipal(){
        String resposta = "<BR>";
        resposta = resposta + "Tabela Modalidades: " + limparTabelaModalidades() + "<BR>";
        resposta = resposta + "Tabela Pedidos Alteração: " + limparTabelaPedidosAlteracao()+ "<BR>";
        resposta = resposta + "Tabela Topicos: " + limparTabelaTopicos()+ "<BR>";
        resposta = resposta + "Tabela Login: " + limparTabelaLogin() + "<BR>";
        resposta = resposta + "Tabela Inscrições: " + limparTabelaInscricoes() + "<BR>";
        resposta = resposta + "Tabela RequestIds: " + limparTabelaRequestIds()+ "<BR>";
        return resposta;
    }
    //Modalidades-------------
    public String criarTabelaModalidades(){
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            stm.execute("CREATE TABLE MODALIDADES("
                    + " ID SERIAL PRIMARY KEY,"
                    + " TIPO VARCHAR(200),"
                    + " NOME VARCHAR(200),"
                    + " PROFESSOR VARCHAR(300),"
                    + " HORARIO TEXT,"
                    + " INFORMACOES TEXT,"
                    + " FOTO64 TEXT"
                    + " );"
            );
            return "Criou Tabela Modalidades";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao criar tabela: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public String deletarTabelaModalidades() {
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DROP TABLE MODALIDADES");
            ps.execute();
            ps.close();
            return "Deletou Tabela Modalidades";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public String limparTabelaModalidades() {
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("TRUNCATE TABLE MODALIDADES");
            ps.execute();
            ps.close();
            return "Limpou Tabela Modalidades";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    //PedidosAlteração-------------
    public String criarTabelaPedidosAlteracao(){
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            stm.execute("CREATE TABLE PEDIDOSALTERACAO("
                    + " ID SERIAL PRIMARY KEY,"
                    + " ID_MODALIDADE INTEGER,"
                    + " TIPO VARCHAR(200),"
                    + " NOME VARCHAR(200),"
                    + " PROFESSOR VARCHAR(300),"
                    + " HORARIO TEXT,"
                    + " INFORMACOES TEXT,"
                    + " FOTO64 TEXT,"
                    + " SITUACAO TEXT," //aceito,negado,aberto
                    + " DETALHE TEXT" //Porque o responsável não autorizou!
                    + " );"
            );
            return "Criou Tabela Pedidos Alteração";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao criar tabela: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public String deletarTabelaPedidosAlteracao() {
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DROP TABLE PEDIDOSALTERACAO");
            ps.execute();
            ps.close();
            return "Deletou Tabela Pedidos Alteração";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public String limparTabelaPedidosAlteracao() {
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("TRUNCATE TABLE PEDIDOSALTERACAO");
            ps.execute();
            ps.close();
            return "Limpou Tabela Pedidos Alteração";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    //Topicos-----------------
    public String criarTabelaTopicos(){
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            stm.execute("CREATE TABLE TOPICOS("
                    + " ID SERIAL PRIMARY KEY,"
                    + " MODALIDADE INTEGER,"
                    + " FOTO64 TEXT,"
                    + " TITULO TEXT,"
                    + " TEXTO TEXT"
                    + " );"
            );
            return "Criou Tabela Topicos";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao criar tabela: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public String deletarTabelaTopicos() {
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DROP TABLE TOPICOS");
            ps.execute();
            ps.close();
            return "Deletou Tabela Topicos";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public String limparTabelaTopicos() {
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("TRUNCATE TABLE TOPICOS");
            ps.execute();
            ps.close();
            return "Limpou Tabela Topicos";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    //Login-------------------
    public String criarTabelaLogin(){
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            stm.execute("CREATE TABLE LOGIN("
                    + " EMAIL VARCHAR(150) UNIQUE,"
                    + " SENHA VARCHAR(30),"
                    + " TIPO VARCHAR(150)"
                    + " );"
            );
            return "Criou Tabela Login";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao criar tabela: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public String deletarTabelaLogin() {
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DROP TABLE LOGIN");
            ps.execute();
            ps.close();
            return "Deletou Tabela Login";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public String limparTabelaLogin() {
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("TRUNCATE TABLE LOGIN");
            ps.execute();
            ps.close();
            return "Limpou Tabela Login";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    //Inscricoes--------------
    public String criarTabelaInscricoes(){
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            stm.execute("CREATE TABLE INSCRICOES("
                    + " ID SERIAL PRIMARY KEY,"
                    + " MODALIDADE INTEGER,"
                    + " NOME VARCHAR(300),"
                    + " TELFIXO VARCHAR(30),"
                    + " TELCELULAR VARCHAR(30),"
                    + " EMAIL VARCHAR(100),"
                    + " ENDERECO_RUA VARCHAR(100),"
                    + " ENDERECO_NUMERO VARCHAR(10),"
                    + " TIPOSANGUINEO VARCHAR(10),"
                    + " OBSERVACOES VARCHAR(500),"
                    + " ATIVIDADE VARCHAR(100),"
                    + " FOTO64 TEXT,"
                    + " SEXO VARCHAR(10),"
                    + " PESO VARCHAR(10),"
                    + " ALTURA VARCHAR(10),"
                    + " RG_NUMERO VARCHAR(50),"
                    + " RG_EXPEDICAO VARCHAR(15),"
                    + " RG_EXPEDIDOR VARCHAR(50),"
                    + " CPF VARCHAR(50),"
                    + " NOME_PAI VARCHAR(300),"
                    + " NOME_MAE VARCHAR(300),"
                    + " IES VARCHAR(300),"
                    + " NOME_EMERGENCIA VARCHAR(300),"
                    + " TELEFONE_EMERGENCIA VARCHAR(300),"
                    + " NASCIMENTO VARCHAR(50)"
                    + " );"
            );
            return "Criou Tabela Inscricoes";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao criar tabela: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public String deletarTabelaInscricoes() {
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DROP TABLE INSCRICOES");
            ps.execute();
            ps.close();
            return "Deletou Tabela Inscrições";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public String limparTabelaInscricoes() {
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("TRUNCATE TABLE INSCRICOES");
            ps.execute();
            ps.close();
            return "Limpou Tabela Inscrições";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    //RequestIds-------------
    public String criarTabelaRequestIds(){
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            stm.execute("CREATE TABLE REQUESTIDS("
                    + " REQUESTID TEXT"
                    + " );"
            );
            return "Criou Tabela RequestIds";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao criar tabela: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public String deletarTabelaRequestIds() {
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DROP TABLE REQUESTIDS");
            ps.execute();
            ps.close();
            return "Deletou Tabela RequestIds";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public String limparTabelaRequestIds() {
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("TRUNCATE TABLE REQUESTIDS");
            ps.execute();
            ps.close();
            return "Limpou Tabela RequestIds";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    //--------------------------------------------------------------------------
    
    //MODALIDADES---------------------------------------------------------------
    //ID | TIPO |NOME | PROFESSOR | HORARIO | INFORMACOES | FOTO64
    public boolean inserirModalidade(POJO_Modalidade modalidade){
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps;
            ps = con.prepareStatement("insert into MODALIDADES"
                    + "(TIPO,NOME,PROFESSOR,HORARIO,INFORMACOES,FOTO64)"
                    + " values(?,?,?,?,?,?)");
            ps.setString(1, modalidade.tipo);
            ps.setString(2, modalidade.nome);
            ps.setString(3, modalidade.professor);
            ps.setString(4, modalidade.horario);
            ps.setString(5, modalidade.informacoes);
            ps.setString(6, modalidade.foto64);
            ps.execute();
            ps.close();
            System.out.println("Inseriu-Modalidade------------------------------");
            System.out.println(modalidade.toJSON());
            System.out.println("------------------------------------------------");
            return true;
        } catch (SQLException e){  
            e.printStackTrace();
            return false;
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public POJO_Modalidade pegarModalidade(int idModalidade) {
        Connection con = BDprincipal.getConnection();
        POJO_Modalidade modalidade = new POJO_Modalidade();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from MODALIDADES where ID = '"+idModalidade+"'");
            res.next();
            modalidade = new POJO_Modalidade();
            modalidade.id = res.getInt(1);
            modalidade.tipo = res.getString(2);
            modalidade.nome = res.getString(3);
            modalidade.professor = res.getString(4);
            modalidade.horario = res.getString(5);
            modalidade.informacoes = res.getString(6);
            modalidade.foto64 = res.getString(7);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
        return modalidade;
    }
    public List<POJO_Modalidade> getModalidades() {
        Connection con = BDprincipal.getConnection();
        List<POJO_Modalidade> modalidades = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from MODALIDADES");
            while(res.next()){
                POJO_Modalidade modalidade = new POJO_Modalidade();
                modalidade.id = res.getInt(1);
                modalidade.tipo = res.getString(2);
                modalidade.nome = res.getString(3);
                modalidade.professor = res.getString(4);
                modalidade.horario = res.getString(5);
                modalidade.informacoes = res.getString(6);
                modalidade.foto64 = res.getString(7);
                modalidades.add(modalidade);
            }           
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
        return modalidades;
    }
    public List<POJO_Modalidade> pegarModalidades(String emailProfessor) {
        Connection con = BDprincipal.getConnection();
        List<POJO_Modalidade> modalidades = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from MODALIDADES where PROFESSOR = '"+emailProfessor+"'");
            while(res.next()){
                POJO_Modalidade modalidade = new POJO_Modalidade();
                modalidade.id = res.getInt(1);
                modalidade.tipo = res.getString(2);
                modalidade.nome = res.getString(3);
                modalidade.professor = res.getString(4);
                modalidade.horario = res.getString(5);
                modalidade.informacoes = res.getString(6);
                modalidade.foto64 = res.getString(7);
                modalidades.add(modalidade);
            }           
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
        return modalidades;
    }
    public boolean alterarModalidade(POJO_Modalidade modalidade) {
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            stm.execute(
                "update MODALIDADES SET "
                + "TIPO = '"+modalidade.tipo+"' ,"        
                + "NOME = '"+modalidade.nome+"' ,"
                + "PROFESSOR = '"+modalidade.professor+"' ,"
                + "HORARIO = '"+modalidade.horario+"' ,"    
                + "INFORMACOES = '"+modalidade.informacoes+"' ,"
                + "FOTO64 = '"+modalidade.foto64 +"'"
                + " WHERE ID = '"+modalidade.id+"'"    
            );  
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public boolean deletarModalidade(int idModalidade){
        Connection con = BDprincipal.getConnection();
        try{
            int count = 0;
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM MODALIDADES WHERE ID = '"+ idModalidade +"'"); 
             while(res.next()) {
                count++;
            }
            if (count == 0) {
            return false; 
                    } else {
            stm.execute("DELETE FROM MODALIDADES WHERE ID = '"+ idModalidade +"'"); }
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    //--------------------------------------------------------------------------
    
    //PEDIDOSALTERACAO-----------------------------------------------------------
    //ID | ID_MODALIDADE | TIPO |NOME | PROFESSOR | HORARIO | INFORMACOES | FOTO64
    //SITUACAO | DETALHE
     public boolean inserirPedidoAlteracao(POJO_Modalidade modalidade){
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps;
            ps = con.prepareStatement("insert into PEDIDOSALTERACAO"
                    + "(ID_MODALIDADE,TIPO,NOME,PROFESSOR,HORARIO,INFORMACOES,FOTO64,SITUACAO,DETALHE)"
                    + " values(?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, modalidade.id);
            ps.setString(2, modalidade.tipo);
            ps.setString(3, modalidade.nome);
            ps.setString(4, modalidade.professor);
            ps.setString(5, modalidade.horario);
            ps.setString(6, modalidade.informacoes);
            ps.setString(7, modalidade.foto64);
            ps.setString(8, POJO_AlteracaoModalidade.PedidoAberto);
            ps.setString(9, "");
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e){  
            e.printStackTrace();
            return false;
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
     public POJO_AlteracaoModalidade pegarPedidoAlteracao(int idPedido) {
        Connection con = BDprincipal.getConnection();
        POJO_AlteracaoModalidade pedido = new POJO_AlteracaoModalidade();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from PEDIDOSALTERACAO where ID = '"+idPedido+"'");
            while(res.next()){
                pedido = new POJO_AlteracaoModalidade();
                pedido.id = res.getInt(1);
                pedido.id_modalidade = res.getInt(2);
                pedido.tipo = res.getString(3);
                pedido.nome = res.getString(4);
                pedido.professor = res.getString(5);
                pedido.horario = res.getString(6);
                pedido.informacoes = res.getString(7);
                pedido.foto64 = res.getString(8);
                pedido.situacao = res.getString(9);
                pedido.detalhe = res.getString(10);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
        return pedido;
    }
     public List<POJO_AlteracaoModalidade> pegarPedidosAbertos() {
        Connection con = BDprincipal.getConnection();
        List<POJO_AlteracaoModalidade> pedidos = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from PEDIDOSALTERACAO");
            while(res.next()){
            POJO_AlteracaoModalidade pedido = new POJO_AlteracaoModalidade();
            pedido.id = res.getInt(1);
            pedido.id_modalidade = res.getInt(2);
            pedido.tipo = res.getString(3);
            pedido.nome = res.getString(4);
            pedido.professor = res.getString(5);
            pedido.horario = res.getString(6);
            pedido.informacoes = res.getString(7);
            pedido.foto64 = res.getString(8);
            pedido.situacao = res.getString(9);
            pedido.detalhe = res.getString(10);
            if(pedido.situacao.equals(POJO_AlteracaoModalidade.PedidoAberto)){
                pedidos.add(pedido);
            }
            }           
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
        return pedidos;
    }
     public List<POJO_AlteracaoModalidade> pegarPedidosConcluidos() {
        Connection con = BDprincipal.getConnection();
        List<POJO_AlteracaoModalidade> pedidos = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from PEDIDOSALTERACAO");
            while(res.next()){
            POJO_AlteracaoModalidade pedido = new POJO_AlteracaoModalidade();
            pedido.id = res.getInt(1);
            pedido.id_modalidade = res.getInt(2);
            pedido.tipo = res.getString(3);
            pedido.nome = res.getString(4);
            pedido.professor = res.getString(5);
            pedido.horario = res.getString(6);
            pedido.informacoes = res.getString(7);
            pedido.foto64 = res.getString(8);
            pedido.situacao = res.getString(9);
            pedido.detalhe = res.getString(10);
            if(!pedido.situacao.equals(POJO_AlteracaoModalidade.PedidoAberto)){
                pedidos.add(pedido);
            }
            }           
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
        return pedidos;
    }
     public boolean deletarPedidoAlteracao(int idPedido){
        Connection con = BDprincipal.getConnection();
        try{
            int count = 0;
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM PEDIDOSALTERACAO WHERE ID = '"+ idPedido +"'"); 
             while(res.next()) {
                count++;
            }
            if (count == 0) {
            return false; 
                    } else {
            stm.execute("DELETE FROM PEDIDOSALTERACAO WHERE ID = '"+ idPedido +"'"); }
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
     public boolean aceitarPedido(int idPedido) {
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            stm.execute(
                "update PEDIDOSALTERACAO SET "
                + "SITUACAO = '"+POJO_AlteracaoModalidade.PedidoAceito +"' ,"
                + "DETALHE = '"+"Pedido Aceito!"+"'"
                + " WHERE ID = '"+idPedido+"'"    
            );  
            
            POJO_AlteracaoModalidade pedido = pegarPedidoAlteracao(idPedido);         
            alterarModalidade(pedido.pegaModalidade());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
     public boolean negarPedido(int idPedido,String detalhe) {
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            stm.execute(
                "update PEDIDOSALTERACAO SET "
                + "SITUACAO = '"+POJO_AlteracaoModalidade.PedidoNegado +"' ,"
                + "DETALHE = '"+detalhe+"'"
                + " WHERE ID = '"+idPedido+"'"    
            );  
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    //--------------------------------------------------------------------------
    
    //TOPICOS-------------------------------------------------------------------
    //ID | MODALIDADE | FOTO64 | TITULO | TEXTO
    public boolean inserirTopico(POJO_Topico topico){
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps;
            ps = con.prepareStatement("insert into TOPICOS"
                    + "(MODALIDADE,FOTO64,TITULO,TEXTO)"
                    + " values(?,?,?,?)");
            ps.setInt(1, topico.modalidade);
            ps.setString(2, topico.foto64);
            ps.setString(3, topico.titulo);
            ps.setString(4, topico.texto);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e){  
            e.printStackTrace();
            return false;
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public List<POJO_Topico> pegarTopicos(int idModalidade) {
        Connection con = BDprincipal.getConnection();
        List<POJO_Topico> topicos = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from TOPICOS where MODALIDADE = '"+idModalidade+"'");
            while(res.next()){
                POJO_Topico topico = new POJO_Topico();
                topico.id = res.getInt(1);
                topico.modalidade = res.getInt(2);
                topico.foto64 = res.getString(3);
//                byte[] fotoBytes = Base64.getDecoder().decode(res.getString(3));
//                topico.foto64 = Base64.getEncoder().encodeToString(redimensionaImagemMedia(fotoBytes));
                topico.titulo = res.getString(4);
                topico.texto = res.getString(5);
                topicos.add(topico);
            }           
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
        return topicos;
    }
    public boolean alterarTopico(POJO_Topico topico) {
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            stm.executeQuery(
                "update TOPICOS SET "
                + "FOTO64 = '"+topico.foto64+"' ,"
                + "TITULO = '"+topico.titulo+"' ,"
                + "TEXTO = '"+topico.texto+"' ,"
                + " WHERE ID = '"+topico.id+"'"    
            );  
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public boolean deletarTopico(int idTopico){
        Connection con = BDprincipal.getConnection();
        try{
            Statement stm = con.createStatement();
            stm.executeQuery("DELETE FROM TOPICOS WHERE ID = '"+ idTopico +"'");
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    //--------------------------------------------------------------------------
    
    //LOGIN---------------------------------------------------------------------
    //EMAIL | SENHA | TIPO
    public boolean inserirLogin(POJO_Login login){
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps;
            ps = con.prepareStatement("insert into LOGIN"
                    + "(EMAIL,SENHA,TIPO)"
                    + " values(?,?,?)");
            ps.setString(1, login.email);
            ps.setString(2, login.senha);
            ps.setString(3, login.tipo);
            ps.execute();
            ps.close();
            System.out.println("Inseriu-Usuario---------------------------------");
            System.out.println(login.toJSON());
            System.out.println("------------------------------------------------");
            return true;
        } catch (SQLException e){  
            e.printStackTrace();
            return false;
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public boolean login(String email, String senha) {
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from LOGIN where EMAIL = '"+email+"'");
            res.next();
            if(res.getString(2).equals(senha)){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public String pegaSenha(String email) {
        Connection con = BDprincipal.getConnection();
        String senha = "-1";
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from LOGIN where EMAIL = '"+email+"'");
            res.next();
            senha = res.getString(2);           
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
        return senha;
    }
    public List<POJO_Login> getUsuarios() {
        Connection con = BDprincipal.getConnection();
        List<POJO_Login> usuarios = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from LOGIN");
            while(res.next()){
                POJO_Login usuario = new POJO_Login();
                usuario.email = res.getString(1);
                usuario.senha = res.getString(2);
                usuario.tipo = res.getString(3);
                usuarios.add(usuario);
            }           
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
        return usuarios;
    }
    public String efetuarLogin(String email, String senha) {
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from LOGIN where EMAIL = '"+email+"'");
            while(res.next()){
                if(res.getString(2).equals(senha)){
                    return res.getString(3);
                }else{
                    return "negado";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "erro";
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
        return "erro";
    }
    public boolean alterarSenha(String email, String novaSenha) {
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            stm.execute(
                "update LOGIN SET "
                + "SENHA = '"+novaSenha+"' "
                + " WHERE EMAIL = '"+email+"'"    
            );  
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public boolean deletarLogin(String email){
        Connection con = BDprincipal.getConnection();
        try{
            int count = 0;
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM LOGIN WHERE EMAIL = '"+ email +"'");
            while(res.next()) {
                count++;
            }
            if (count == 0) {
            return false; } else {
            stm.execute("DELETE FROM LOGIN WHERE EMAIL = '"+ email +"'"); }
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public boolean loginVazio(){
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from LOGIN");
            int numProfessores = 0;
            while(res.next()){
                numProfessores++;
            }
            if(numProfessores == 0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e){  
            e.printStackTrace();
            return false;
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    //--------------------------------------------------------------------------
    
    //INSCRICOES----------------------------------------------------------------
    // ID | MODALIDADE | NOME | TELFIXO | TELCELULAR | EMAIL | ENDERECO_RUA | ENDERECO_NUMERO 
    // TIPOSANGUINEO | OBSERVACOES | ATIVIDADE | FOTO64 | SEXO | PESO | ALTURA 
    // RG_NUMERO | RG_EXPEDICAO | RG_EXPEDIDOR | CPF | NOME_PAI | NOME_MAE | IES 
    // AVISAR | NOME_EMERGENCIA | TELEFONE_EMERGENCIA |NASCIMENTO"
    public boolean inserirInscricao(POJO_Inscricao inscricao){
        Connection con = BDprincipal.getConnection();
        try {
            PreparedStatement ps;
            ps = con.prepareStatement("insert into INSCRICOES"
                    + "(MODALIDADE,NOME,TELFIXO,TELCELULAR,EMAIL,ENDERECO_RUA,ENDERECO_NUMERO,"
                    + "TIPOSANGUINEO,OBSERVACOES,ATIVIDADE,FOTO64,SEXO,PESO,ALTURA,"
                    + "RG_NUMERO,RG_EXPEDICAO,RG_EXPEDIDOR,CPF,NOME_PAI,NOME_MAE,IES,"
                    + "NOME_EMERGENCIA,TELEFONE_EMERGENCIA,NASCIMENTO)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, inscricao.modalidade);
            ps.setString(2, inscricao.nome);
            ps.setString(3, inscricao.telfixo);
            ps.setString(4, inscricao.telcelular);
            ps.setString(5, inscricao.email);
            ps.setString(6, inscricao.endereco_rua);
            ps.setString(7, inscricao.endereco_numero);
            ps.setString(8, inscricao.tiposanguineo);
            ps.setString(9, inscricao.observacoes);
            ps.setString(10, inscricao.atividade);
            ps.setString(11, inscricao.foto64);
            ps.setString(12, inscricao.sexo);
            ps.setString(13, inscricao.peso);
            ps.setString(14, inscricao.altura);
            ps.setString(15, inscricao.rg_numero);
            ps.setString(16, inscricao.rg_expedicao);
            ps.setString(17, inscricao.rg_expedidor);
            ps.setString(18, inscricao.cpf);
            ps.setString(19, inscricao.nome_pai);
            ps.setString(20, inscricao.nome_mae);
            ps.setString(21, inscricao.ies);
            ps.setString(22, inscricao.nome_emergencia);
            ps.setString(23, inscricao.telefone_emergencia);
            ps.setString(24, inscricao.nascimento);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e){ 
            e.printStackTrace();
            return false;
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public List<POJO_Inscricao> getInscricoes() {
        Connection con = BDprincipal.getConnection();
        List<POJO_Inscricao> inscricoes = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from INSCRICOES");
            while(res.next()){
                POJO_Inscricao inscricao = new POJO_Inscricao();
                inscricao.id = res.getInt(1);
                inscricao.modalidade = res.getInt(2);
                inscricao.nome = res.getString(3);
                inscricao.telfixo = res.getString(4);
                inscricao.telcelular = res.getString(5);
                inscricao.email = res.getString(6);
                inscricao.endereco_rua = res.getString(7);
                inscricao.endereco_numero = res.getString(8);
                inscricao.tiposanguineo = res.getString(9);
                inscricao.observacoes = res.getString(10);
                inscricao.atividade = res.getString(11);
                inscricao.foto64 = res.getString(12);
                inscricao.sexo = res.getString(13);
                inscricao.peso = res.getString(14);
                inscricao.altura = res.getString(15);
                inscricao.rg_numero = res.getString(16);
                inscricao.rg_expedicao = res.getString(17);
                inscricao.rg_expedidor = res.getString(18);
                inscricao.cpf = res.getString(19);
                inscricao.nome_pai = res.getString(20);
                inscricao.nome_mae = res.getString(21);
                inscricao.ies = res.getString(22);
                inscricao.nome_emergencia = res.getString(23);
                inscricao.telefone_emergencia = res.getString(24);
                inscricao.nascimento = res.getString(25);
                inscricoes.add(inscricao);
            }           
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
        return inscricoes;
    }
    public List<POJO_Inscricao> pegarInscricoes(int modalidade) {
        Connection con = BDprincipal.getConnection();
        List<POJO_Inscricao> inscricoes = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from INSCRICOES where MODALIDADE ='"+modalidade+"'");
            while(res.next()){
                POJO_Inscricao inscricao = new POJO_Inscricao();
                inscricao.id = res.getInt(1);
                inscricao.modalidade = res.getInt(2);
                inscricao.nome = res.getString(3);
                inscricao.telfixo = res.getString(4);
                inscricao.telcelular = res.getString(5);
                inscricao.email = res.getString(6);
                inscricao.endereco_rua = res.getString(7);
                inscricao.endereco_numero = res.getString(8);
                inscricao.tiposanguineo = res.getString(9);
                inscricao.observacoes = res.getString(10);
                inscricao.atividade = res.getString(11);
                inscricao.foto64 = res.getString(12);
                inscricao.sexo = res.getString(13);
                inscricao.peso = res.getString(14);
                inscricao.altura = res.getString(15);
                inscricao.rg_numero = res.getString(16);
                inscricao.rg_expedicao = res.getString(17);
                inscricao.rg_expedidor = res.getString(18);
                inscricao.cpf = res.getString(19);
                inscricao.nome_pai = res.getString(20);
                inscricao.nome_mae = res.getString(21);
                inscricao.ies = res.getString(22);
                inscricao.nome_emergencia = res.getString(23);
                inscricao.telefone_emergencia = res.getString(24);
                inscricao.nascimento = res.getString(25);
                inscricoes.add(inscricao);
            }           
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
        return inscricoes;
    }
    public boolean deletarInscricao(int idInscricao){
        Connection con = BDprincipal.getConnection();
        try{
            Statement stm = con.createStatement();
            stm.execute("DELETE FROM INSCRICOES WHERE ID = '"+ idInscricao +"'");
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    //--------------------------------------------------------------------------    
    
    //REQUESTIDS---------------------------------------------------------------
    //REQUESTID 
    public boolean inserirRequestId(String requestId){
        Connection con = BDprincipal.getConnection();
        String endMAC = requestId.substring(0, requestId.lastIndexOf(":"));
        String numRequisicao = requestId.substring((requestId.lastIndexOf(":")+1),requestId.length());
        int n = Integer.valueOf(numRequisicao);
        if(n>=999){
            deletarPedidos(endMAC);
        }
        try {
            PreparedStatement ps;
            ps = con.prepareStatement("insert into REQUESTIDS"
                    + "(REQUESTID)"
                    + " values(?)");
            ps.setString(1, requestId);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e){  
            e.printStackTrace();
            return false;
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    public boolean deletarPedidos(String usuario){
        Connection con = BDprincipal.getConnection();
        try{
            Statement stm = con.createStatement();
            stm.execute("DELETE FROM REQUESTIDS WHERE REQUESTID LIKE '%"+ usuario +"%'");
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    /**@return Retorna true se o pedido ja foi cadastrado e false se não*/
    public boolean pedidoJaReebido(String requestId) {
        Connection con = BDprincipal.getConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from REQUESTIDS where REQUESTID = '"+requestId+"'");
            res.next();
            String requestid = res.getString(1);
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            return false;
        }finally{
            try { con.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }
    //METODOS-------------------------------------------------------------------
    //--------------------------------
    public byte[] toByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }
    public byte[] redimensionaImagemMedia(byte[] fotoBytes) {
        byte[] fotoReduzidaBytes = null;
        //Redimensiona Imagem--------------------------------
        try {
            ImageIcon img = new ImageIcon(fotoBytes);
            img.setImage(img.getImage().getScaledInstance(
                    (img.getImage().getWidth(null) / 2),
                    (img.getImage().getHeight(null) / 2),
                    100));
            BufferedImage bufferedImage = new BufferedImage(img.getImage().getWidth(null), img.getImage().getHeight(null),
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = bufferedImage.createGraphics();
            g.drawImage(img.getImage(), 0, 0, null);
            g.dispose();
            fotoReduzidaBytes = toByteArray(bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //------------------------------------------------------
        return fotoReduzidaBytes;
    }
    //--------------------------------------------------------------------------
    public int peganumerodeusuarios(){ return getUsuarios().size(); }
    public int peganumerodemodalidades(){ return getModalidades().size(); }
    public int peganumerodeinscricoes(){ return getInscricoes().size(); }
    public int peganumerodepedidos(){ return pegarPedidosAbertos().size(); }
}
