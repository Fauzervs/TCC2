package br.edu.senaisp.TCC2.Controller;

import br.edu.senaisp.TCC2.Model.QRCode;
import br.edu.senaisp.TCC2.Model.Animal;
import br.edu.senaisp.TCC2.Model.Pessoa;
import br.edu.senaisp.TCC2.Model.Objeto;
import br.edu.senaisp.TCC2.Service.QRCodeService;
import br.edu.senaisp.TCC2.Service.AnimalService;
import br.edu.senaisp.TCC2.Service.PessoaService;
import br.edu.senaisp.TCC2.Service.ObjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/qrcodes")
public class QRCodeController {

    @Autowired
    private QRCodeService qrcodeService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ObjetoService objetoService;

    @GetMapping("/{qrcodeId}")
    public ResponseEntity<Void> verificarQRCode(@PathVariable String qrcodeId) {
        // Verifica se o QRCode existe no banco de dados
        QRCode qrcode = qrcodeService.getQRCodeById(qrcodeId)
                .orElseThrow(() -> new RuntimeException("QRCode não encontrado"));

        // Verifica se o status do QRCode é 0 (plaqueta virgem)
        if (qrcode.getStatus() == 0) {
            // Redireciona para a tela de cadastro de usuário
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/cadastroUsuario.html?qrcodeId=" + qrcodeId)
                    .build();
        }

        // Caso o QRCode esteja cadastrado (status != 0), verifica o tipo de associação
        Animal animal = animalService.getAnimalByQRCodeId(qrcodeId).orElse(null);
        if (animal != null) {
            // Redireciona para o relatório de animal
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/relatorioAnimal.html?qrcodeId=" + qrcodeId)
                    .build();
        }

        Pessoa pessoa = pessoaService.getPessoaByQRCodeId(qrcodeId).orElse(null);
        if (pessoa != null) {
            // Redireciona para o relatório de pessoa
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/relatorioPessoa.html?qrcodeId=" + qrcodeId)
                    .build();
        }

        Objeto objeto = objetoService.getObjetoByQRCodeId(qrcodeId).orElse(null);
        if (objeto != null) {
            // Redireciona para o relatório de objeto
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/relatorioObjeto.html?qrcodeId=" + qrcodeId)
                    .build();
        }

        // Se o QRCode não estiver associado a nenhum perfil, trata como plaqueta preenchida genérica
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/plaquetaPreenchida.html?qrcodeId=" + qrcodeId)
                .build();
    }
}
