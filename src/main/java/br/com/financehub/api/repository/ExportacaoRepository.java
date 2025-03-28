package br.com.financehub.api.repository;

import br.com.financehub.api.model.Exportacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportacaoRepository extends JpaRepository<Exportacao, Long> {
}
