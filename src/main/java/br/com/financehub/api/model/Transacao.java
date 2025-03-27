package br.com.financehub.api.model;

import br.com.financehub.api.enums.Categoria;
import br.com.financehub.api.enums.TipoTransacao;
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
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Long idTransacao;

    @Column(name = "valor_transacao", precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "tipo_transacao", nullable = false)
    private Enum<TipoTransacao> tipoTransacao;

    @Column(name = "tipo_categoria", nullable = false)
    private Enum<Categoria> tipoCategoria;

    @ManyToOne
    @JoinColumn(name = "USUARIOS_id_usuario", nullable = false)
    private Usuario usuario;
}
