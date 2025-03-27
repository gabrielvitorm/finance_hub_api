package br.com.financehub.api.service;

import br.com.financehub.api.model.Usuario;
import br.com.financehub.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void criarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodosUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> listarUsuarioPorId(Long idUsuario){
        if (usuarioRepository.existsById(idUsuario)){
            return usuarioRepository.findById(idUsuario);
        }else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public void deletarUsuario(Long idUsuario){
        if (usuarioRepository.existsById(idUsuario)){
            usuarioRepository.deleteById(idUsuario);
        }else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public void atualizarUsuario(Long idUsuario, Usuario usuario){

        Optional<Usuario> usuarioBancoDeDados = listarUsuarioPorId(idUsuario);

        if (usuarioBancoDeDados.isEmpty()){
            throw new RuntimeException("Usuário não encontrado no Banco de dados");
        }

        Usuario usuarioEditado = usuarioBancoDeDados.get();

        usuarioEditado.setNomeUsuario(usuario.getNomeUsuario());
        usuarioEditado.setEmailUsuario(usuario.getEmailUsuario());
        usuarioEditado.setCpfUsuario(usuario.getCpfUsuario());
        usuarioEditado.setSenhaUsuario(usuario.getSenhaUsuario());

        usuarioRepository.save(usuarioEditado);
    }
}
