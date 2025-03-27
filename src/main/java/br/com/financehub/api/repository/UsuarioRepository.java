package br.com.financehub.api.repository;

import br.com.financehub.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailUsuario(String emailUsuario);

    @Override
    void delete(Usuario usuario);
}
