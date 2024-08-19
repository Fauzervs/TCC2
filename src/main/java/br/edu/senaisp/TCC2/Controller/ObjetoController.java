package br.edu.senaisp.TCC2.Controller;

import br.edu.senaisp.TCC2.Model.Objeto;
import br.edu.senaisp.TCC2.Model.QRCode;
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

    @PostMapping("/cadastrar/{qrcodeId}")
    public ResponseEntity<Void> cadastrarObjeto(@RequestBody Objeto objeto, @PathVariable String qrcodeId) {
        try {
            // Obtendo o QRCode pelo ID
            QRCode qrcode = qrcodeService.getQRCodeById(qrcodeId)
                    .orElseThrow(() -> new RuntimeException("QRCode não encontrado"));

            // Associando o QRCode ao objeto
            objeto.setQrcode(qrcode);

            // Salvando o objeto no banco de dados
            objetoService.saveObjeto(objeto);

            // Atualizando o status do QRCode para 1 após o cadastro
            qrcode.setStatus(1);
            qrcodeService.saveQRCode(qrcode);

            // Redirecionando para a página de sucesso após o cadastro
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/cadastroSucesso.html")
                    .build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Novo método para buscar dados do relatório de um objeto por QRCode
    @GetMapping("/relatorio/{qrcodeId}")
    public ResponseEntity<Objeto> getRelatorioObjeto(@PathVariable String qrcodeId) {
        Objeto objeto = objetoService.getObjetoByQRCodeId(qrcodeId)
                .orElse(null);
        if (objeto != null) {
            return ResponseEntity.ok(objeto);  // Retorna o objeto em formato JSON
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Retorna 404 se o QRCode não for encontrado
        }
    }
}
