package br.edu.senaisp.TCC2.Repository;

import br.edu.senaisp.TCC2.Model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QRCodeRepository extends JpaRepository<QRCode, String> {
    // Métodos adicionais, se necessário
}
