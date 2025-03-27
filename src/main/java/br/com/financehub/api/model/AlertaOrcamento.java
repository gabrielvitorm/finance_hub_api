package br.com.financehub.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alerta_orcamento")
public class AlertaOrcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta_orcamento", nullable = false)
    private Long idAlertaOrcamento;

    @Column(name = "valor_atingido", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorAtingido;

    @Column(name = "percentual_atingido", precision = 10, scale = 2, nullable = false)
    private BigDecimal percentualAtingido;

    @Column(name = "data_alerta", nullable = false)
    private LocalDateTime dataAlerta;

    @ManyToOne
    @JoinColumn(name = "ORCAMENTO_id_orcamento", nullable = false)
    private Orcamento orcamento;
}
