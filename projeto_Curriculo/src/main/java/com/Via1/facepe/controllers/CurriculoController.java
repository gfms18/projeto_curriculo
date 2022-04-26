package com.Via1.facepe.controllers;

import java.io.IOException;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.thymeleaf.util.StringUtils;
import org.springframework.util.StringUtils;

import com.Via1.facepe.dao.CurriculoDAO;
import com.Via1.facepe.entitys.Cargos;
import com.Via1.facepe.entitys.Curriculo;
import com.Via1.facepe.entitys.FileUploadUtil;
import com.Via1.facepe.entitys.Subcargos;

@Controller
public class CurriculoController {
	
	@Autowired
	private CurriculoDAO curriculoDAO;
		
	@GetMapping("/EnviarCurriculo")
	public String enviarCurriculo() {
		return "curriculo";
	}
	
	@GetMapping("/adm/curriculo/{idCurriculo}")
	@ResponseBody
	public byte[] exibirCurriculo(@PathVariable("idCurriculo") Integer idCurriculo){
		Curriculo curriculo = this.curriculoDAO.getOne(idCurriculo);
		return curriculo.getArquivo();
	}
	
	@PostMapping("/salvar-curriculo")
	public String salvarCurriculo(Curriculo curriculo, @RequestParam("fileCurriculo") MultipartFile file, RedirectAttributes ra) {
		
		//verificar quantidade de Areas escolhidas
		List<String> cargo = curriculo.getAreaGeral();
		List<String> subcargos = curriculo.getAreaEspecifica();
		
		if(cargo.size()>3) {
			ra.addFlashAttribute("errocheckbox", "Erro ao enviar curriculo: verifique quantas opções foram marcadas e tente novamente.");
			ra.addFlashAttribute("mensagem", "Você marcou: " + cargo.size() + " opções. Selecione no maximo 3.");
			return "redirect:/EnviarCurriculo";
		}
		if(subcargos.size()>2) {
			ra.addFlashAttribute("errocheckbox", "Erro ao enviar curriculo: verifique quantas opções foram marcadas e tente novamente.");
			ra.addFlashAttribute("mensagem2", "Você marcou: " + subcargos.size() + " opções. Selecione no maximo 2.");
			return "redirect:/EnviarCurriculo";
		}
		
		
		try {
			curriculo.setArquivo(file.getBytes());
			curriculo.setNomeArquivo(file.getOriginalFilename());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
		curriculo.setDataLancamento(LocalDate.now());
		this.curriculoDAO.save(curriculo);
		return "enviado";
	}
	
	@GetMapping("/teste")
	public String paginaNova(Cargos[] cargo) {
		return "teste";
	}
	
	@GetMapping("/retornarInicio")
	public String retornarInicio() {
		return "home";
	}
	
	@ModelAttribute("enum_cargo")
	public List<String> getCargos() {
		List<String> carg = Cargos.descricoes();				
		return carg;
	}
	
	@ModelAttribute("enum_subcargos")
	public List<String> getSubCargos() {
		List<String> carg = Subcargos.descricoes();				
		return carg;
	}
	
	@PostMapping("/buscar_Cargo")
	public String buscar_Cargo(String cargo, Model model) {
		model.addAttribute("listaCurriculo", this.curriculoDAO.findByAreaGeral(cargo));
		return "buscar_curriculo";
				
	}
	
	@PostMapping("/buscar_SUbCargo")
	public String buscar_SUbCargo(String subCargo, Model model) {
		model.addAttribute("listaCurriculo", this.curriculoDAO.findByAreaEspecifica(subCargo));
		return "buscar_curriculo";
				
	}
	
	@GetMapping("/adm/buscarCurriculos")
	public String buscarCurriculo(Model model) {	
		model.addAttribute("listaCurriculo",  this.curriculoDAO.findAll(Sort.by("nome")));
		return "buscar_curriculo";
	}
	
	@PostMapping("/adm/buscar_junto")
	public String buscar_junto(String cargo, String subCargo, Model model) {
		model.addAttribute("listaCurriculo", this.curriculoDAO.findByAreaGeralAndAreaEspecifica(cargo, subCargo));
		return "buscar_curriculo";
	}
	
	@GetMapping("/adm/excluirCurriculo")//@ResponseBody@PathVariable("idCurriculo") /{idCurriculo}
	public String apagarCurriculo(Curriculo id, RedirectAttributes ra){
		System.out.println(id);
		this.curriculoDAO.delete(id);
		ra.addFlashAttribute("mensagemApagado", "Curriculo apagado com sucesso.");
		return "redirect:/adm/buscarCurriculos";
		
	}

}
