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

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody Usuario usuario, @RequestParam String qrcodeId) {
        if (qrcodeId == null || qrcodeId.isEmpty()) {
            return ResponseEntity.badRequest().body("O parâmetro qrcodeId é obrigatório.");
        }

        try {
            // Salva o usuário
            usuario = usuarioService.saveUsuario(usuario);

            // Recupera o QRCode e associa ao usuário
            QRCode qrcode = qrcodeService.getQRCodeById(qrcodeId)
                    .orElseThrow(() -> new RuntimeException("QRCode não encontrado"));

            qrcode.setUsuario(usuario);
            qrcode.setStatus(1);  // Atualiza o status para 1 após o cadastro ser concluído
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
