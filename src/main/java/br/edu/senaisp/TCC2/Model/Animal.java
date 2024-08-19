package br.edu.senaisp.TCC2.Model;

import jakarta.persistence.*;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeResponsavel;
    private String enderecoResponsavel;
    private String telefoneResponsavel;
    private String emailResponsavel;
    private String nomeAnimal;
    private String tipoAnimal;
    private String racaAnimal;
    private String descricaoAnimal;
    private String fotoAnimal;  // Alterado para String para armazenar o caminho ou link da foto
    private String observacaoAnimal;

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

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public String getRacaAnimal() {
        return racaAnimal;
    }

    public void setRacaAnimal(String racaAnimal) {
        this.racaAnimal = racaAnimal;
    }

    public String getDescricaoAnimal() {
        return descricaoAnimal;
    }

    public void setDescricaoAnimal(String descricaoAnimal) {
        this.descricaoAnimal = descricaoAnimal;
    }

    public String getFotoAnimal() {
        return fotoAnimal;
    }

    public void setFotoAnimal(String fotoAnimal) {
        this.fotoAnimal = fotoAnimal;
    }

    public String getObservacaoAnimal() {
        return observacaoAnimal;
    }

    public void setObservacaoAnimal(String observacaoAnimal) {
        this.observacaoAnimal = observacaoAnimal;
    }

    public QRCode getQrcode() {
        return qrcode;
    }

    public void setQrcode(QRCode qrcode) {
        this.qrcode = qrcode;
    }
}
