package br.edu.senaisp.TCC2.Service;

import br.edu.senaisp.TCC2.Model.Usuario;
import br.edu.senaisp.TCC2.Exception.ResourceNotFoundException;
import br.edu.senaisp.TCC2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
