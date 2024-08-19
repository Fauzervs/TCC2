package br.edu.senaisp.TCC2.Controller;

import br.edu.senaisp.TCC2.Model.Animal;
import br.edu.senaisp.TCC2.Service.AnimalService;
import br.edu.senaisp.TCC2.Service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private QRCodeService qrcodeService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Void> cadastrarAnimal(@RequestBody Animal animal, @RequestParam String qrcodeId) {
        try {
            // Associando o QRCode ao animal
            animal.setQrcode(qrcodeService.getQRCodeById(qrcodeId).orElseThrow(() -> new RuntimeException("QRCode não encontrado")));
            animalService.saveAnimal(animal);

            // Atualizando o status do QRCode para 1 após o cadastro ser concluído
            animal.getQrcode().setStatus(1);
            qrcodeService.saveQRCode(animal.getQrcode());

            return ResponseEntity.status(HttpStatus.FOUND)
                                 .header("Location", "/cadastroSucesso.html")
                                 .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
