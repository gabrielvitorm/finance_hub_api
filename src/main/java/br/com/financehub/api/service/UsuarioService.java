package br.com.financehub.api.service;

import br.com.financehub.api.model.Usuario;
import br.com.financehub.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void criarUsuario(Usuario usuario) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(usuario.getSenhaUsuario());

        usuario.setSenhaUsuario(senhaCriptografada);
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

    public Optional<Usuario> listarUsuarioPorEmail(String emailUsuario){
        return usuarioRepository.findByEmailUsuario(emailUsuario);
    }

    public void deletarUsuario(Long idUsuario){
        if (usuarioRepository.existsById(idUsuario)){
            usuarioRepository.deleteById(idUsuario);
        }else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public void deletarUsuarioByEmail(String emailUsuario) {
        Optional<Usuario> usuarioDeletar = usuarioRepository.findByEmailUsuario(emailUsuario);

        if (usuarioDeletar.isPresent()) {
            usuarioRepository.delete(usuarioDeletar.get());
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public void atualizarUsuario(Long idUsuario, Usuario usuario){

        Optional<Usuario> usuarioBancoDeDados = listarUsuarioPorId(idUsuario);

        if (usuarioBancoDeDados.isEmpty()){
            throw new RuntimeException("Usuário não encontrado no Banco de dados");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(usuario.getSenhaUsuario());

        Usuario usuarioEditado = usuarioBancoDeDados.get();

        usuarioEditado.setNomeUsuario(usuario.getNomeUsuario());
        usuarioEditado.setEmailUsuario(usuario.getEmailUsuario());
        usuarioEditado.setCpfUsuario(usuario.getCpfUsuario());
        usuarioEditado.setSenhaUsuario(senhaCriptografada);

        usuarioRepository.save(usuarioEditado);
    }

    public void atualizarSenhaUsuario(String emailUsuario, Usuario usuario) {
        Optional<Usuario> usuarioBancoDeDados = listarUsuarioPorEmail(emailUsuario);

        if (usuarioBancoDeDados.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        Usuario usuarioEditado = usuarioBancoDeDados.get();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(usuario.getSenhaUsuario());

        usuarioEditado.setSenhaUsuario(senhaCriptografada);

        usuarioRepository.save(usuarioEditado);
    }

    public void atualizarEmail(String emailUsuario, Usuario usuario){
        Optional<Usuario> usuarioBancoDeDados = listarUsuarioPorEmail(emailUsuario);

        if (usuarioBancoDeDados.isEmpty()){
            throw new RuntimeException("Usuário não encontrado!");
        }

        Usuario usuarioEditado = usuarioBancoDeDados.get();

        usuarioEditado.setEmailUsuario(usuario.getEmailUsuario());

        usuarioRepository.save(usuarioEditado);
    }

    public Usuario autenticarUsuario(String emailUsuario, String senhaUsuario){
        Optional<Usuario> usuarioBancoDeDados = listarUsuarioPorEmail(emailUsuario);

        if (usuarioBancoDeDados.isEmpty()){
            throw new RuntimeException("Usuário não encontrado!");
        }

        Usuario usuarioAutenticado = usuarioBancoDeDados.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(senhaUsuario, usuarioAutenticado.getSenhaUsuario())){
            throw new RuntimeException("Senha inválida!");
        }

        return usuarioAutenticado;
    }
}
