package br.edu.senaisp.TCC2.Repository;

import br.edu.senaisp.TCC2.Model.Objeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ObjetoRepository extends JpaRepository<Objeto, Long> {

    @Query("SELECT o FROM Objeto o WHERE o.qrcode.id = :qrcodeId")
    Optional<Objeto> findByQRCodeId(@Param("qrcodeId") String qrcodeId);
}
