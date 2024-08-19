package br.edu.senaisp.TCC2.Service;

import br.edu.senaisp.TCC2.Model.Animal;
import br.edu.senaisp.TCC2.Exception.ResourceNotFoundException;
import br.edu.senaisp.TCC2.Repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    // Método para salvar um Animal no banco de dados
    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    // Método para buscar um Animal pelo ID
    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado com o ID: " + id));
    }

    // Método para deletar um Animal pelo ID
    public void deleteAnimal(Long id) {
        Animal animal = getAnimalById(id);  // Verifica se o animal existe antes de deletar
        animalRepository.delete(animal);
    }

    // Método para buscar um Animal pelo ID do QR Code
    public Animal getAnimalByQRCodeId(String qrcodeId) {
        return animalRepository.findByQRCodeId(qrcodeId)
            .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado com o QRCode ID: " + qrcodeId));
    }
}
