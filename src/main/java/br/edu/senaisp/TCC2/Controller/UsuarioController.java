package br.edu.senaisp.TCC2.Controller;

import br.edu.senaisp.TCC2.Model.QRCode;
import br.edu.senaisp.TCC2.Model.Usuario;
import br.edu.senaisp.TCC2.Service.UsuarioService;
import br.edu.senaisp.TCC2.Service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private QRCodeService qrcodeService;

    @PostMapping("/cadastrar/{qrcodeId}")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody Usuario usuario, @PathVariable String qrcodeId) {
        try {
            // Recupera o QRCode e associa ao usuário
            QRCode qrcode = qrcodeService.getQRCodeById(qrcodeId)
                    .orElseThrow(() -> new RuntimeException("QRCode não encontrado"));

            // Salva o usuário
            usuario = usuarioService.saveUsuario(usuario);

            qrcode.setUsuario(usuario);
            qrcode.setStatus(1);  // Atualiza o status para 1
            qrcodeService.saveQRCode(qrcode);

            // Redireciona para a escolha de perfil
            return ResponseEntity.status(HttpStatus.FOUND)
                                 .header("Location", "/escolhaPerfil.html?qrcodeId=" + qrcodeId)
                                 .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar usuário.");
        }
    }
}
