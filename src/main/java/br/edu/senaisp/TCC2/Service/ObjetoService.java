package br.edu.senaisp.TCC2.Service;

import br.edu.senaisp.TCC2.Model.Objeto;
import br.edu.senaisp.TCC2.Repository.ObjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ObjetoService {

    @Autowired
    private ObjetoRepository objetoRepository;

    public Objeto saveObjeto(Objeto objeto) {
        return objetoRepository.save(objeto);
    }

    public Optional<Objeto> getObjetoByQRCodeId(String qrcodeId) {
        return objetoRepository.findByQRCodeId(qrcodeId);
    }

    public Optional<Objeto> getObjetoById(Long id) {
        return objetoRepository.findById(id);
    }

    public void deleteObjeto(Long id) {
        objetoRepository.deleteById(id);
    }
}
