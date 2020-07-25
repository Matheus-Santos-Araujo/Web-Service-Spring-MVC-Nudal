package appice.nudal.Upload;

import appice.nudal.Models.POJO_Modalidade;
import appice.nudal.DAO.BDprincipal;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jasper.servlet.JspCServletContext;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

@WebServlet(name = "Uploader", urlPatterns = {"/Uploader"})
public class Uploader extends HttpServlet {

    List<formulario> formularios;
    boolean deuBom = true;
    String detalhe = "";
    String foto64 = "erro";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        formularios = new ArrayList<>();
        if (!ServletFileUpload.isMultipartContent(request)) {
            out.println("Nothing to upload");
            return;
        }
        FileItemFactory itemfactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(itemfactory);

        try {
            List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldname = item.getFieldName();
                    String fieldvalue = item.getString();
                    formularios.add(new formulario(fieldname, fieldvalue));
                } else {
                    String contentType = item.getContentType();
                    if (!contentType.equals("image/jpeg")) {
                        detalhe = "Use apenas imagem no formato JPG!";
                        continue;
                    }
                    ServletContext context = request.getServletContext();
                    String realpath = context.getRealPath("/images/");
                    File uploadDir = new File(realpath);
                    File foto = File.createTempFile("img", ".jpg", uploadDir);
                    item.write(foto);
                    FileInputStream fis = new FileInputStream(foto);
                    byte[] foto_bytes = new byte[(int) foto.length()];
                    fis.read(foto_bytes);
                    foto64 = Base64.getEncoder().encodeToString(foto_bytes);
                    fis.close();
                    foto.delete();
                }
            }
            if (foto64.equals("erro")) {
                deuBom = false;
            } else {
                BDprincipal banco = new BDprincipal();
                POJO_Modalidade modalidade = new POJO_Modalidade();
                modalidade.foto64 = foto64;
                modalidade.tipo = pegaInfoFormulario("tipo");
                modalidade.nome = pegaInfoFormulario("nome");
                modalidade.professor = pegaInfoFormulario("professor");
                modalidade.horario = pegaInfoFormulario("horario");
                modalidade.informacoes = pegaInfoFormulario("informacoes");
                deuBom = (banco.inserirModalidade(modalidade));
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            detalhe = "Falha ao carregar!";
            deuBom = false;
        } catch (Exception ex) {
            ex.printStackTrace();
            detalhe = "Não foi possivel concluir o processo!";
            deuBom = false;
        } finally {
            String deuBom = this.deuBom ? "tudoShow" : "deuRuim";
            response.setHeader("statusinserir", deuBom);
            request.setAttribute("detalhe", detalhe);
            RequestDispatcher dispatcher = request.getRequestDispatcher("exibirModalidades");
            dispatcher.forward(request, response);
        }
    }

    public class formulario {

        public String chave;
        public String valor;

        public formulario(String chave, String valor) {
            this.chave = chave;
            this.valor = valor;
        }

    }

    private String pegaInfoFormulario(String chave) {
        String valor = "erro";
        for (formulario f : formularios) {
            if (f.chave.equals(chave)) {
                valor = f.valor;
                break;
            }
        }
        return valor;
    }
}
