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

    public QRCode saveQRCode(QRCode qrcode) {
        return qrcodeRepository.save(qrcode);
    }

    public Optional<QRCode> getQRCodeById(String id) {
        return qrcodeRepository.findById(id);
    }

    public void deleteQRCode(String id) {
        qrcodeRepository.deleteById(id);
    }
}
