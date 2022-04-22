package com.Via1.facepe.acesso;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Via1.facepe.controllers.CurriculoController;
import com.Via1.facepe.dao.CurriculoDAO;
import com.Via1.facepe.dao.UsuarioDAO;
import com.Via1.facepe.entitys.Cargos;
import com.Via1.facepe.entitys.Curriculo;
import com.Via1.facepe.entitys.Subcargos;
import com.Via1.facepe.entitys.Usuario;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Controller
public class AcessoController {

	@Autowired
	private CurriculoDAO curriculoDAO;
	private String usuarioLogadoCpf;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	
	@PostMapping("/login")
	public String efetuarLogin(String cpf, String senha,  HttpSession session, RedirectAttributes ra) {
		// Buscar se existe um usuario com o login e senha informados
		Usuario usuarioLogado = this.usuarioDAO.findByCpfAndSenha(cpf, senha);
		this.usuarioLogadoCpf=cpf;
		if (usuarioLogado == null) {
			ra.addFlashAttribute("mensagem", "cpf inválido");
			return "redirect:/login";
		} else {
			session.setAttribute("usuarioLogado", usuarioLogado);
			return "redirect:/adm/buscarCurriculos";
			
		}
	}
	
	@GetMapping("/sair")
	public String sair(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/acessoNegado")
	public String acessoNegado() {
		return "acesso_negado";
	}
	@GetMapping("/adm/alterarSenha")
	public String alterarSenha() {
		return "alterar_Senha";
	}
	@PostMapping("/salvarSenha")
	public String novaSenha(String senha, String senhaNova, String confsenha, RedirectAttributes ra) {
		//01863044477
		System.out.println("foi");
		Usuario usu = this.usuarioDAO.findByCpf(usuarioLogadoCpf);
		
		if (usu.getSenha().equals(senha)) {
			if(senhaNova.equals(confsenha)) {
				usu.setSenha(senhaNova);		
				this.usuarioDAO.save(usu);
				ra.addFlashAttribute("mensagem", "senhas alterada");
				return "redirect:/adm/home";
			
			} else {
				ra.addFlashAttribute("mensagem", "senhas diferentes");			
				return "redirect:/adm/alterarSenha";
			}
		} 
		ra.addFlashAttribute("mensagem", "senha diferentes");			
		return "redirect:/adm/alterarSenha";
			
		
	}
	
	@GetMapping("/adicionar_Todos_Usuarios")
	public String inportarCSV() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\facepe.aluno2\\Documents\\usuario2.csv"));
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).build();
		//System.out.println(csvReader);
		List<String[]> usuarios = csvReader.readAll();		
		
		for(String[] usuario: usuarios) {
			//System.out.println(usuario[0]);
			String[] items = usuario[0].split(";");
	        List<String> itemList = new ArrayList<String>();
	        for (String item : items) {
	        	itemList.add(item);	        	
	        }
	        /* 0 nome
	         * 1 cpf
	         * 2 codigo
	         * 3 descricao
	         * 4 cargo
	         * 5 empresa
	         * 6 cgc
	         * */
	        
	       //int cpf = Integer.parseInt(items[1]);
	       //System.out.println(cpf);
	        		
	        Usuario usua1 = new Usuario();
	        		
	        		usua1.setCpf(items[1]);
	        		//System.out.println(usua1.getCpf());
	        		String cpf ="01863044477";
	        		if(usua1.getCpf().equals(cpf)) {
	        			usua1.setNome(items[0]);
	        			usua1.setSenha("1234");
	        			this.usuarioDAO.save(usua1);
	        			System.out.println(usua1.getNome());
	        		}
	        		
		}
		return "login";
	}
	
}
