package com.unicuritiba.barbearia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unicuritiba.barbearia.model.Agendamento;
import com.unicuritiba.barbearia.model.Funcionario;
import com.unicuritiba.barbearia.model.Servico;
import com.unicuritiba.barbearia.repository.AgendamentoRepository;
import com.unicuritiba.barbearia.repository.FuncionarioRepository;
import com.unicuritiba.barbearia.repository.ServicoRepository;

@Controller
public class HomeController {

		@Autowired
		private FuncionarioRepository funcionarioRepository;
		
		@Autowired
		private AgendamentoRepository agendamentoRepository;
	
		@Autowired
		private ServicoRepository servicoRepository;
		
		@GetMapping("/")
		public ModelAndView home () {
			ModelAndView pagina = new ModelAndView("home");
			
			
			return pagina;
		}
		
		@GetMapping("/index")
		public ModelAndView index () {
			
			List<Funcionario> funcionarios = funcionarioRepository.findAll();
			List<Servico> servicos = servicoRepository.findAll();
			
			ModelAndView pagina = new ModelAndView("index");
			pagina.addObject("agendamento", new Agendamento());
			pagina.addObject("funcionarios", funcionarios);
			pagina.addObject("servicos", servicos);
			return pagina;
		}
		
		@PostMapping("/agendamento-cliente")
		public ModelAndView agendamentoCliente (@ModelAttribute Agendamento agendamento) {
			
			agendamentoRepository.save(agendamento);
						
			ModelAndView sucessoAgendamento = new ModelAndView("redirect:/sucesso-agendamento");
			
			return sucessoAgendamento;
		}
		
		@GetMapping("/sucesso-agendamento")
		public ModelAndView boaAgendamento() {
			
			ModelAndView pagina = new ModelAndView("sucessoAgendamento");
			return pagina;
			
		}
		
		@GetMapping("/cadastro")
		public ModelAndView cadastro () {
			ModelAndView pagina = new ModelAndView("cadastro");
			
			pagina.addObject("funcionario", new Funcionario());			
			
			return pagina;
		}
		
		@PostMapping("/cadastro-funcionario")
		public ModelAndView cadastroFuncionario(@ModelAttribute Funcionario funcionario) {
			
			funcionarioRepository.save(funcionario);
			ModelAndView sucessoCadastro = new ModelAndView("redirect:/sucesso-cadastro");
						
			return sucessoCadastro;
		}
		
		@GetMapping("/sucesso-cadastro")
		public ModelAndView bemVindoFuncionario() {
						
			ModelAndView pagina = new ModelAndView("sucessoCadastro");			
			return pagina;
			
		}
		
		@GetMapping("/orderAdmin")
		public ModelAndView adminBarber() {
			
			List<Agendamento> agendamentos = agendamentoRepository.findAll();
			
			ModelAndView pagina = new ModelAndView("orderAdmin");
			pagina.addObject("agendamentos", agendamentos);
			
			return pagina;
			
		}
		
		@PostMapping("/deleta-agendamento")
		public ModelAndView deleteAgendamento (@RequestParam(value = "id", required = false) Integer id
) {
			
			agendamentoRepository.deleteById(Long.valueOf(id));
						
			ModelAndView deleteAgendamento = new ModelAndView("redirect:/orderAdmin");
			return deleteAgendamento;
		}
		
		
		@GetMapping("/agenda")
		public ModelAndView agendA () {
		
		List<Funcionario> funcionarios = funcionarioRepository.findAll();

		List<Agendamento> agendamentos = agendamentoRepository.findAll();
			
		ModelAndView pagina = new ModelAndView("agenda");
		pagina.addObject("agendamentos", agendamentos);
		pagina.addObject("funcionarios", funcionarios);

		return pagina;
	}

		@GetMapping("/agenda/{funcionario}")
			public ModelAndView agenda (@PathVariable("funcionario") String profissional) {
			
			List<Agendamento> agendamentos = agendamentoRepository.findByProfissional(profissional);
			
			ModelAndView pagina = new ModelAndView("agenda");
			pagina.addObject("agendamento", agendamentos);
			return pagina;
		}
		
		@GetMapping("/login")
		public ModelAndView login () {
			
			ModelAndView pagina = new ModelAndView("login");
			
			return pagina;
		}
		
		@PostMapping("/valida-login")
		public ModelAndView validaLogin (@ModelAttribute Funcionario funcionario) {
				
			System.out.println(funcionario.getCpf());
			System.out.println(funcionario.getSenha());

			ModelAndView sucessoLogin = new ModelAndView("redirect:/orderAdmin");
			
			return sucessoLogin;
		}
		
		@GetMapping("/funcionarios")
		public ModelAndView Func () {
			
			List<Funcionario> funcionarios = funcionarioRepository.findAll();
			
			ModelAndView pagina = new ModelAndView("funcionarios");
			pagina.addObject("funcionarios", funcionarios);
			
			return pagina;
		}
		
		@PostMapping("/deleta-funcionario")
		public ModelAndView deleteFuncionario (@RequestParam(value = "id", required = false) Integer id
) {
			
			funcionarioRepository.deleteById(Long.valueOf(id));
						
			ModelAndView deleteFuncionario = new ModelAndView("redirect:/funcionarios");
			return deleteFuncionario;
		}
		
		@GetMapping("/servicos")
		public ModelAndView Serv () {
			
			List<Servico> servicos = servicoRepository.findAll();
			
			ModelAndView pagina = new ModelAndView("servicos");
			pagina.addObject("servicos", servicos);
			
			return pagina;
		}
		
		@GetMapping("/cadastro-servico")
		public ModelAndView cadastroServ () {
			ModelAndView pagina = new ModelAndView("cadastro-servico");
			
			pagina.addObject("servico", new Servico());			
			
			return pagina;
		}
		
		@PostMapping("/cadastroServ")
		public ModelAndView cadastroServicp (@ModelAttribute Servico servico) {
			
			servicoRepository.save(servico);
			ModelAndView sucessoCadastro = new ModelAndView("redirect:/servicos");
						
			return sucessoCadastro;
		}
		
		@PostMapping("/deleta-servico")
		public ModelAndView deleteServico (@RequestParam(value = "id", required = false) Integer id
) {
			
			servicoRepository.deleteById(Long.valueOf(id));
						
			ModelAndView deleteServico = new ModelAndView("redirect:/servicos");
			return deleteServico;
		}
		
		
}