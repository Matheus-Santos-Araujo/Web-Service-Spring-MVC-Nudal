package appice.nudal.Controllers;

import appice.nudal.DAO.BDprincipal;
import appice.nudal.Email.EnviarEmail;
import appice.nudal.Models.POJO_AlteracaoModalidade;
import appice.nudal.Models.POJO_Inscricao;
import appice.nudal.Models.POJO_Login;
import appice.nudal.Models.POJO_Modalidade;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerNudal {
    
     BDprincipal banco = new BDprincipal();
    
      @RequestMapping("/")
    public String inicio(){
        return "index";
    }
     
     @RequestMapping("/login")
    public String Login (){
       return "login";
    }
    
     @RequestMapping("/index")
    public String Index (){
       return "index";
    }
    
     @RequestMapping("/LoginIniciado")
    public String LoginIniciado (POJO_Login login, HttpSession session, Model model){
           
              if(banco.efetuarLogin(login.email, login.senha).equals("ADMINISTRADOR"))) {
               session.setAttribute("username", login.email); 
            } else { model.addAttribute("errorMessage", "Dados Inválidos"); return "login"; }
           return "index";   
    }
    
      @RequestMapping("/Logout")
    public String Logout (HttpSession session){
        session.removeAttribute("username");
        session.invalidate();
        return "index";   
    }
     
     
    @RequestMapping("/inserirUsuario")
    public String inserirUsuario(){
        return "inserirUsuario";
    }
    
      @RequestMapping("/inserirModalidade")
    public String inserirModalidade(){
        return "inserirModalidade";
    }
    
    @RequestMapping("/AdicionarUsuario")
    public String AdicionarUsuario (POJO_Login usuario, Model model){
       if (banco.inserirLogin(usuario)) { model.addAttribute("statusinserir", "ok"); } else { model.addAttribute("statusinserir", "erro"); }
       return "exibirUsuarios";
    }
       
    @RequestMapping("exibirUsuarios")
    public String ExibirUsuarios (Model model){
      model.addAttribute("usuarios", banco.getUsuarios());
      return "exibirUsuarios";
    }
    
    @RequestMapping("RemoverUsuario")
         public String RemoverUsuario (POJO_Login usuario, Model model){
          if(banco.deletarLogin(usuario.email)) { model.addAttribute("status", "ok"); } else { model.addAttribute("status", "erro"); }
            return "exibirUsuarios";
         }
         
    @RequestMapping("exibirModalidades")
    public String ExibirModalidades(Model model){
      model.addAttribute("modalidades", banco.getModalidades());
      return "exibirModalidades";
    }    
    
     @RequestMapping("RemoverModalidade")
         public String RemoverModalidade (POJO_Modalidade modalidade, Model model){
          if (banco.deletarModalidade(modalidade.id)) { model.addAttribute("status", "ok"); } else {  model.addAttribute("status", "erro"); }
            return "exibirModalidades";
         }
         
    @RequestMapping("exibirInscricoes")
    public String ExibirInscricoes (Model model){
      model.addAttribute("usuarios", banco.getInscricoes());
      return "exibirInscricoes";
    }
    
      @RequestMapping("RemoverInscricao")
         public String RemoverInscricao (POJO_Inscricao inscricao, Model model){
          if (banco.deletarInscricao(inscricao.id)) { model.addAttribute("status", "ok"); } else {model.addAttribute("status", "erro"); }
          return "exibirInscricoes";
         }
         
      @RequestMapping("exibirPedidosAlteracao")
       public String exibirPedidosAlteracao(){
        return "exibirPedidosAlteracao";
    }
         
       @RequestMapping("aceitarpedido")
         public String AceitarPedido(POJO_AlteracaoModalidade alteracao){
        EnviarEmail email = new EnviarEmail();
        POJO_AlteracaoModalidade pedido = banco.pegarPedidoAlteracao(alteracao.id);
        email.enviar(pedido.professor, "Pedido de Alteração", "Seu pedido de alteração em "+ pedido.nome +" foi aceito pelo administrador!");
        banco.aceitarPedido(alteracao.id);
        return "exibirPedidosAlteracao";
         }
         
           @RequestMapping("negarpedido")
         public String NegarPedido(POJO_AlteracaoModalidade alteracao){
        EnviarEmail email = new EnviarEmail();
        POJO_AlteracaoModalidade pedido = banco.pegarPedidoAlteracao(alteracao.id);
        email.enviar(pedido.professor, "Pedido de Alteração", "Seu pedido de alteração em "+ pedido.nome +" foi negado pelo administrador!");
        banco.negarPedido(alteracao.id,"");
         return "exibirPedidosAlteracao";
         }
}
