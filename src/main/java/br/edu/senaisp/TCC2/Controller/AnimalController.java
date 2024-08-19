package br.edu.senaisp.TCC2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.senaisp.TCC2.Model.Animal;
import br.edu.senaisp.TCC2.Model.QRCode;
import br.edu.senaisp.TCC2.Service.AnimalService;
import br.edu.senaisp.TCC2.Service.QRCodeService;

import java.util.Optional;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private QRCodeService qrcodeService;

    // Método para cadastrar um novo animal
    @PostMapping("/cadastrar/{qrcodeId}")
    public ResponseEntity<Void> cadastrarAnimal(@RequestBody Animal animal, @PathVariable String qrcodeId) {
        try {
            // Obtendo o QRCode pelo ID
            QRCode qrcode = qrcodeService.getQRCodeById(qrcodeId)
                    .orElseThrow(() -> new RuntimeException("QRCode não encontrado"));

            // Associando o QRCode ao animal
            animal.setQrcode(qrcode);

            // Salvando o animal no banco de dados
            animalService.saveAnimal(animal);

            // Atualizando o status do QRCode para 1 após o cadastro
            qrcode.setStatus(1);
            qrcodeService.saveQRCode(qrcode);

            // Redirecionando para a página de sucesso após o cadastro
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/cadastroSucesso.html")
                    .build();
        } catch (RuntimeException e) {
            // Capturando exceções específicas e retornando erro 404 se o QRCode não for encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Capturando exceções gerais e retornando erro 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Novo método para buscar dados do relatório de um animal por QRCode
    @GetMapping("/relatorio/{qrcodeId}")
    public ResponseEntity<Animal> getRelatorioAnimal(@PathVariable String qrcodeId) {
        Optional<Animal> animalOptional = animalService.getAnimalByQRCodeId(qrcodeId);
        if (animalOptional.isPresent()) {
            return ResponseEntity.ok(animalOptional.get());  // Retorna o animal em formato JSON
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Retorna 404 se o QRCode não for encontrado
        }
    }
}
