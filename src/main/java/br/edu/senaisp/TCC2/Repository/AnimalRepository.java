package br.edu.senaisp.TCC2.Repository;

import br.edu.senaisp.TCC2.Model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT a FROM Animal a WHERE a.qrcode.id = :qrcodeId")
    Optional<Animal> findByQRCodeId(@Param("qrcodeId") String qrcodeId);
}
