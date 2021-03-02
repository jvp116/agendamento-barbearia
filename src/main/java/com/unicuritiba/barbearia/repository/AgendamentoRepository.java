package com.unicuritiba.barbearia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicuritiba.barbearia.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento,Long>{

	public List<Agendamento> findByProfissional(String profissional);
	public List<Agendamento> findById(String id);

}
