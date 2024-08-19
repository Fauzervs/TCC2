package br.edu.senaisp.TCC2.Repository;

import br.edu.senaisp.TCC2.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Busca um usuário pelo email
    Optional<Usuario> findByEmail(String email);

    // Autentica um usuário pelo email e senha
    Optional<Usuario> findByEmailAndSenha(String email, String senha);
}
	