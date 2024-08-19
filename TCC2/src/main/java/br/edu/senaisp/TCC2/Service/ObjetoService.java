package br.edu.senaisp.TCC2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.senaisp.TCC2.Exception.ResourceNotFoundException;
import br.edu.senaisp.TCC2.Model.Objeto;
import br.edu.senaisp.TCC2.Repository.ObjetoRepository;

@Service
public class ObjetoService {

    @Autowired
    private ObjetoRepository objetoRepository;

    // Método para salvar um Objeto no banco de dados
    public Objeto saveObjeto(Objeto objeto) {
        return objetoRepository.save(objeto);
    }

    // Método para buscar um Objeto pelo ID
    public Objeto getObjetoById(Long id) {
        return objetoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado com o ID: " + id));
    }

    // Método para deletar um Objeto pelo ID
    public void deleteObjeto(Long id) {
        Objeto objeto = getObjetoById(id);  // Verifica se o objeto existe antes de deletar
        objetoRepository.delete(objeto);
    }

    // Método para buscar um Objeto pelo ID do QR Code
    public Objeto getObjetoByQRCodeId(String qrcodeId) {
        return objetoRepository.findByQRCodeId(qrcodeId)
            .orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado com o QRCode ID: " + qrcodeId));
    }
}
