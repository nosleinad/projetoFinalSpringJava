package br.com.agenda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.agenda.model.Contato;
import br.com.agenda.repository.ContatoRepository;

@Controller
public class ContatoController {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	/* Cadastrar */
	@GetMapping("/adicionarContato")
	public String addContato() {
		return "paginas/adicionarContato";
	}
	
	@PostMapping("/adicionarContato")
	public String addContato(Contato contato) {
		
		/* Grava contato no SQL */
		contatoRepository.save(contato);
		
		return "redirect:/";
	}
	
	
	/* Consultar */
	@GetMapping("/")
	public ModelAndView listaContatos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Contato> contatos = contatoRepository.findAll();
		mv.addObject("contatos", contatos);
		return mv;
	}
	
	@RequestMapping(value="/alterarContato/{idContato}", method=RequestMethod.GET)
	public ModelAndView alterarContato(@PathVariable("idContato") long idContato) {
		Contato contato = contatoRepository.findByIdContato(idContato);
		ModelAndView mv = new ModelAndView("paginas/alterarContato");
		mv.addObject("contato", contato);
		return mv;
	}
	
	@RequestMapping(value="/alterarContato/{idContato}", method=RequestMethod.POST)
	public String alterarContatoPost(@PathVariable("idContato") long idContato, Contato contato){
		
		/* Grava contato no SQL */
		contatoRepository.save(contato);
		return "redirect:/";
		}
	
	
	/* Excluir */
	@GetMapping("/deletarContato")
	public String deletarContato(long idContato) {
		Contato contato = contatoRepository.findByIdContato(idContato);
		
		/* Deleta contato no SQL */
		contatoRepository.delete(contato);
		
		return "redirect:/";
	}
	

}