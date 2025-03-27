package br.com.financehub.api.model;

import br.com.financehub.api.enums.TipoExportacao;
import br.com.financehub.api.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exportacao")
public class Exportacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orcamento")
    private Long idOrcamento;

    @Column(name = "tipo_exportacao", nullable = false)
    private Enum<TipoExportacao> tipoExportacao;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime data_criacao;

    @ManyToOne
    @JoinColumn(name = "USUARIO_id_usuario", nullable = false)
    private Usuario usuario;
}
