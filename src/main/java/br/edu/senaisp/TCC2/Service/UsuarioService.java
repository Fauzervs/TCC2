package br.edu.senaisp.TCC2.Service;

import br.edu.senaisp.TCC2.Model.Usuario;
import br.edu.senaisp.TCC2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Salva um novo usuário ou atualiza um existente
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Busca um usuário pelo ID
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Deleta um usuário pelo ID
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Autentica um usuário pelo email e senha
    public Usuario autenticarUsuario(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha).orElse(null);
    }

    // Busca um usuário pelo email
    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
