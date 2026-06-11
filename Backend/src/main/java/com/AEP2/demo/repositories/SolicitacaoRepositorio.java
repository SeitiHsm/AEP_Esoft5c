package com.AEP2.demo.repositories;

import com.AEP2.demo.models.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoRepositorio extends JpaRepository<Solicitacao, Integer> {
}
