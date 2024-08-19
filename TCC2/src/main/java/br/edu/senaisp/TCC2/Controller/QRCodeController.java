package br.edu.senaisp.TCC2.Controller;

import br.edu.senaisp.TCC2.Model.QRCode;
import br.edu.senaisp.TCC2.Service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/qrcodes")
public class QRCodeController {

    @Autowired
    private QRCodeService qrcodeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> verificarQRCode(@PathVariable("id") String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("ID do QR Code inválido.");
        }

        Optional<QRCode> qrcodeOptional = qrcodeService.getQRCodeById(id);

        if (qrcodeOptional.isEmpty()) {
            // Se o QR Code não existir, crie um novo registro
            QRCode newQRCode = new QRCode();
            newQRCode.setId(id);
            newQRCode.setStatus(0);  // Status 0 até o cadastro ser finalizado
            newQRCode.setApelido(null);  // Campo apelido vazio
            newQRCode.setUsuario(null);  // Campo usuario_id vazio

            qrcodeService.saveQRCode(newQRCode);

            // Redirecionar para a tela de cadastro de usuário com o QRCode ID na URL
            return ResponseEntity.status(302).header("Location", "/cadastroUsuario.html?qrcodeId=" + id).build();
        } else {
            QRCode qrCodeExistente = qrcodeOptional.get();
            if (qrCodeExistente.getStatus() == 0) {
                // Redirecionar para a tela de cadastro de usuário com o QRCode ID na URL
                return ResponseEntity.status(302).header("Location", "/cadastroUsuario.html?qrcodeId=" + id).build();
            } else if (qrCodeExistente.getStatus() == 1) {
                return ResponseEntity.ok("Plaqueta preenchida.");
            }
        }

        return ResponseEntity.status(400).body("Requisição inválida.");
    }

    @PostMapping
    public ResponseEntity<QRCode> cadastrarQRCode(@RequestBody QRCode qrcode) {
        try {
            QRCode novoQRCode = qrcodeService.saveQRCode(qrcode);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoQRCode);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
