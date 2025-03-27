package br.com.financehub.api.controller;

import br.com.financehub.api.model.Usuario;
import br.com.financehub.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarUsuario(@RequestBody Usuario usuario){
        usuarioService.criarUsuario(usuario);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> listarTodosUsuarios(){
        return usuarioService.listarTodosUsuarios();
    }

    @GetMapping("/emails/{emailUsuario}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Usuario> listarUsuarioPorEmail(@PathVariable String emailUsuario) {
        return usuarioService.listarUsuarioPorEmail(emailUsuario);
    }

    @GetMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Usuario> listaUsuarioPorId(@PathVariable Long idUsuario){
        return usuarioService.listarUsuarioPorId(idUsuario);
    }

    @DeleteMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuarioPorId(@PathVariable Long idUsuario){
        usuarioService.deletarUsuario(idUsuario);
    }

    @DeleteMapping("/emails/{emailUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuarioByEmail(@PathVariable String emailUsuario) {
        usuarioService.deletarUsuarioByEmail(emailUsuario);
    }

    @PutMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarUsuario(@PathVariable Long idUsuario, @RequestBody Usuario usuario){
        usuarioService.atualizarUsuario(idUsuario, usuario);
    }
}
