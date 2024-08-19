package br.edu.senaisp.TCC2.Service;

import br.edu.senaisp.TCC2.Model.QRCode;
import br.edu.senaisp.TCC2.Repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QRCodeService {

    @Autowired
    private QRCodeRepository qrcodeRepository;

    // Salva um QRCode no banco de dados
    public QRCode saveQRCode(QRCode qrcode) {
        return qrcodeRepository.save(qrcode);
    }

    // Busca um QRCode pelo ID
    public Optional<QRCode> getQRCodeById(String id) {
        return qrcodeRepository.findById(id);
    }

    // Deleta um QRCode pelo ID
    public void deleteQRCode(String id) {
        qrcodeRepository.deleteById(id);
    }

    // Atualiza o status do QRCode
    public void updateQRCodeStatus(String id, int status) {
        QRCode qrcode = qrcodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QRCode não encontrado"));
        qrcode.setStatus(status);
        qrcodeRepository.save(qrcode);
    }

    // Verifica se o QRCode está associado a um usuário
    public boolean isQRCodeAssociatedWithUser(String id) {
        return qrcodeRepository.findById(id)
                .map(qrcode -> qrcode.getUsuario() != null)
                .orElse(false);
    }
}
