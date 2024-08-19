package br.edu.senaisp.TCC2.Controller;

import br.edu.senaisp.TCC2.Model.Pessoa;
import br.edu.senaisp.TCC2.Service.PessoaService;
import br.edu.senaisp.TCC2.Service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private QRCodeService qrcodeService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Void> cadastrarPessoa(@RequestBody Pessoa pessoa, @RequestParam String qrcodeId) {
        try {
            // Associando o QRCode à pessoa
            pessoa.setQrcode(qrcodeService.getQRCodeById(qrcodeId).orElseThrow(() -> new RuntimeException("QRCode não encontrado")));
            pessoaService.savePessoa(pessoa);

            // Atualizando o status do QRCode para 1 após o cadastro ser concluído
            pessoa.getQrcode().setStatus(1);
            qrcodeService.saveQRCode(pessoa.getQrcode());

            return ResponseEntity.status(HttpStatus.FOUND)
                                 .header("Location", "/cadastroSucesso.html")
                                 .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
