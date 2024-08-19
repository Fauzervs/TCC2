package br.edu.senaisp.TCC2.Controller;

import br.edu.senaisp.TCC2.Model.Pessoa;
import br.edu.senaisp.TCC2.Model.QRCode;
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

    @PostMapping("/cadastrar/{qrcodeId}")
    public ResponseEntity<Void> cadastrarPessoa(@RequestBody Pessoa pessoa, @PathVariable String qrcodeId) {
        try {
            // Obtendo o QRCode pelo ID
            QRCode qrcode = qrcodeService.getQRCodeById(qrcodeId)
                    .orElseThrow(() -> new RuntimeException("QRCode não encontrado"));

            // Associando o QRCode à pessoa
            pessoa.setQrcode(qrcode);

            // Salvando a pessoa no banco de dados
            pessoaService.savePessoa(pessoa);

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

    // Novo método para buscar dados do relatório de uma pessoa por QRCode
    @GetMapping("/relatorio/{qrcodeId}")
    public ResponseEntity<Pessoa> getRelatorioPessoa(@PathVariable String qrcodeId) {
        Pessoa pessoa = pessoaService.getPessoaByQRCodeId(qrcodeId)
                .orElse(null);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);  // Retorna a pessoa em formato JSON
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Retorna 404 se o QRCode não for encontrado
        }
    }
}
