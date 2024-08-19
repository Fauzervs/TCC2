package br.edu.senaisp.TCC2.Service;

import br.edu.senaisp.TCC2.Model.Pessoa;
import br.edu.senaisp.TCC2.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa savePessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> getPessoaByQRCodeId(String qrcodeId) {
        return pessoaRepository.findByQRCodeId(qrcodeId);
    }

    public Optional<Pessoa> getPessoaById(Long id) {
        return pessoaRepository.findById(id);
    }

    public void deletePessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}
