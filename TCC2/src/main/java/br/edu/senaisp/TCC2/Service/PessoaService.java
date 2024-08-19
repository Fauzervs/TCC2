package br.edu.senaisp.TCC2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.senaisp.TCC2.Exception.ResourceNotFoundException;
import br.edu.senaisp.TCC2.Model.Pessoa;
import br.edu.senaisp.TCC2.Repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // Método para salvar uma Pessoa no banco de dados
    public Pessoa savePessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    // Método para buscar uma Pessoa pelo ID
    public Pessoa getPessoaById(Long id) {
        return pessoaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o ID: " + id));
    }

    // Método para deletar uma Pessoa pelo ID
    public void deletePessoa(Long id) {
        Pessoa pessoa = getPessoaById(id);  // Verifica se a pessoa existe antes de deletar
        pessoaRepository.delete(pessoa);
    }

    // Método para buscar uma Pessoa pelo ID do QR Code
    public Pessoa getPessoaByQRCodeId(String qrcodeId) {
        return pessoaRepository.findByQRCodeId(qrcodeId)
            .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o QRCode ID: " + qrcodeId));
    }
}
