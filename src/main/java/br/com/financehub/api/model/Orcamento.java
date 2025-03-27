package br.com.financehub.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orcamento")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orcamento")
    private Long idOrcamento;

    @Column(name = "limite_orcamento", precision = 10, scale = 2, nullable = false)
    private BigDecimal limiteOrcamento;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "mes_referencia", nullable = false)
    private Date mesReferencia;

    @ManyToOne
    @JoinColumn(name = "USUARIOS_id_usuario", nullable = false)
    private Usuario usuario;
}
