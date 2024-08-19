package br.edu.senaisp.TCC2.Controller;

import br.edu.senaisp.TCC2.Model.Usuario;
import br.edu.senaisp.TCC2.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
        // Verifica se o email e senha estão corretos
        Usuario usuarioAutenticado = usuarioService.autenticarUsuario(usuario.getEmail(), usuario.getSenha());
        if (usuarioAutenticado != null) {
            return ResponseEntity.ok(usuarioAutenticado); // Retorna o usuário autenticado
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorna erro 401 se falhar
        }
    }
}
