package br.com.financehub.api.service;

import br.com.financehub.api.model.Transacao;
import br.com.financehub.api.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    public void criarTransacao(Transacao transacao){
        transacaoRepository.save(transacao);
    }

    public List<Transacao> listarTodasTransacoes(){
        return transacaoRepository.findAll();
    }

    public Optional<Transacao> listarTransacaoPorId(Long idTransacao){
        return transacaoRepository.findById(idTransacao);
    }

    public void deletarTransacaoById(Long idTransacao){
        transacaoRepository.deleteById(idTransacao);
    }

    public void atualizarTransacaoById(Long idTransacao, Transacao transacao){
        Optional<Transacao> transacaoBandoDeDados = listarTransacaoPorId(idTransacao);

        if (transacaoBandoDeDados.isEmpty()){
            throw new RuntimeException("Transacao não encontrada!");
        }

        Transacao transacaoEditado = transacaoBandoDeDados.get();

        transacaoEditado.setValor(transacao.getValor());
        transacaoEditado.setTipoTransacao(transacao.getTipoTransacao());
        transacaoEditado.setDescricao(transacao.getDescricao());
        transacaoEditado.setTipoCategoria(transacao.getTipoCategoria());

        transacaoRepository.save(transacaoEditado);
    }
}
