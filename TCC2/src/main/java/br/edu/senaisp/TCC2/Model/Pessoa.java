package br.edu.senaisp.TCC2.Model;

import jakarta.persistence.*;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeResponsavel;
    private String enderecoResponsavel;
    private String telefoneResponsavel;
    private String emailResponsavel;
    private String nomePessoa;
    private String patologiaPessoa;
    private String medicacaoPessoa;
    private String tipoSanguineoPessoa;
    private byte[] fotoPessoa;
    private String observacaoPessoa;

    @OneToOne
    @JoinColumn(name = "qrcode_id")
    private QRCode qrcode;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getEnderecoResponsavel() {
        return enderecoResponsavel;
    }

    public void setEnderecoResponsavel(String enderecoResponsavel) {
        this.enderecoResponsavel = enderecoResponsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public void setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getPatologiaPessoa() {
        return patologiaPessoa;
    }

    public void setPatologiaPessoa(String patologiaPessoa) {
        this.patologiaPessoa = patologiaPessoa;
    }

    public String getMedicacaoPessoa() {
        return medicacaoPessoa;
    }

    public void setMedicacaoPessoa(String medicacaoPessoa) {
        this.medicacaoPessoa = medicacaoPessoa;
    }

    public String getTipoSanguineoPessoa() {
        return tipoSanguineoPessoa;
    }

    public void setTipoSanguineoPessoa(String tipoSanguineoPessoa) {
        this.tipoSanguineoPessoa = tipoSanguineoPessoa;
    }

    public byte[] getFotoPessoa() {
        return fotoPessoa;
    }

    public void setFotoPessoa(byte[] fotoPessoa) {
        this.fotoPessoa = fotoPessoa;
    }

    public String getObservacaoPessoa() {
        return observacaoPessoa;
    }

    public void setObservacaoPessoa(String observacaoPessoa) {
        this.observacaoPessoa = observacaoPessoa;
    }

    public QRCode getQrcode() {
        return qrcode;
    }

    public void setQrcode(QRCode qrcode) {
        this.qrcode = qrcode;
    }
}
