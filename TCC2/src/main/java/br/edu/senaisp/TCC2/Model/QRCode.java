package br.edu.senaisp.TCC2.Model;

import jakarta.persistence.*;

@Entity
public class QRCode {

    @Id
    private String id;  // ID do QR Code

    private String apelido;
    private int status;  // 0: Virgem, 1: Associado, etc.

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private PerfilTipo perfilTipo;  // Tipo de perfil associado (Animal, Objeto, Pessoa)

    private Long perfilId;  // ID do perfil associado (Animal, Objeto, Pessoa)

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PerfilTipo getPerfilTipo() {
        return perfilTipo;
    }

    public void setPerfilTipo(PerfilTipo perfilTipo) {
        this.perfilTipo = perfilTipo;
    }

    public Long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
    }
}
