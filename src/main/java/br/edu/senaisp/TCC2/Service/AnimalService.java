package br.edu.senaisp.TCC2.Service;

import br.edu.senaisp.TCC2.Model.Animal;
import br.edu.senaisp.TCC2.Repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Optional<Animal> getAnimalByQRCodeId(String qrcodeId) {
        return animalRepository.findByQRCodeId(qrcodeId);
    }

    public Optional<Animal> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}
