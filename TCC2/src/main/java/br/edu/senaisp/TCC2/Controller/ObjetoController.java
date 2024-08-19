package br.edu.senaisp.TCC2.Controller;

import br.edu.senaisp.TCC2.Model.Objeto;
import br.edu.senaisp.TCC2.Service.ObjetoService;
import br.edu.senaisp.TCC2.Service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/objetos")
public class ObjetoController {

    @Autowired
    private ObjetoService objetoService;

    @Autowired
    private QRCodeService qrcodeService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Void> cadastrarObjeto(@RequestBody Objeto objeto, @RequestParam String qrcodeId) {
        try {
            // Associando o QRCode ao objeto
            objeto.setQrcode(qrcodeService.getQRCodeById(qrcodeId).orElseThrow(() -> new RuntimeException("QRCode não encontrado")));
            objetoService.saveObjeto(objeto);

            // Atualizando o status do QRCode para 1 após o cadastro ser concluído
            objeto.getQrcode().setStatus(1);
            qrcodeService.saveQRCode(objeto.getQrcode());

            return ResponseEntity.status(HttpStatus.FOUND)
                                 .header("Location", "/cadastroSucesso.html")
                                 .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
