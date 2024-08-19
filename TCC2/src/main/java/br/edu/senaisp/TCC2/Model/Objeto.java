package br.edu.senaisp.TCC2.Model;

import jakarta.persistence.*;

@Entity
public class Objeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeResponsavel;
    private String enderecoResponsavel;
    private String telefoneResponsavel;
    private String emailResponsavel;
    private String nomeObjeto;
    private String descricaoObjeto;
    private byte[] fotoObjeto;
    private String observacaoObjeto;

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

    public String getNomeObjeto() {
        return nomeObjeto;
    }

    public void setNomeObjeto(String nomeObjeto) {
        this.nomeObjeto = nomeObjeto;
    }

    public String getDescricaoObjeto() {
        return descricaoObjeto;
    }

    public void setDescricaoObjeto(String descricaoObjeto) {
        this.descricaoObjeto = descricaoObjeto;
    }

    public byte[] getFotoObjeto() {
        return fotoObjeto;
    }

    public void setFotoObjeto(byte[] fotoObjeto) {
        this.fotoObjeto = fotoObjeto;
    }

    public String getObservacaoObjeto() {
        return observacaoObjeto;
    }

    public void setObservacaoObjeto(String observacaoObjeto) {
        this.observacaoObjeto = observacaoObjeto;
    }

    public QRCode getQrcode() {
        return qrcode;
    }

    public void setQrcode(QRCode qrcode) {
        this.qrcode = qrcode;
    }
}
