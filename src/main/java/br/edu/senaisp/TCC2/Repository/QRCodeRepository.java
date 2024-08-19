package br.edu.senaisp.TCC2.Repository;

import br.edu.senaisp.TCC2.Model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QRCodeRepository extends JpaRepository<QRCode, String> {

    // Busca um QRCode pelo status
    @Query("SELECT q FROM QRCode q WHERE q.status = :status")
    Optional<QRCode> findByStatus(@Param("status") int status);

    // Verifica se o QRCode está associado a um usuário
    @Query("SELECT CASE WHEN COUNT(q) > 0 THEN true ELSE false END FROM QRCode q WHERE q.id = :id AND q.usuario IS NOT NULL")
    boolean existsByQRCodeAndUsuarioIsNotNull(@Param("id") String id);
}
