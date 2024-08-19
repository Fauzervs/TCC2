package br.edu.senaisp.TCC2.Repository;

import br.edu.senaisp.TCC2.Model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p FROM Pessoa p WHERE p.qrcode.id = :qrcodeId")
    Optional<Pessoa> findByQRCodeId(@Param("qrcodeId") String qrcodeId);
}
