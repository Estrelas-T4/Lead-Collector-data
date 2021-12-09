package br.com.zup.LeadCollector.lead;

import br.com.zup.LeadCollector.produto.Produto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "leads")
public class Lead {
    @Id
    @Email
    private String email;
    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'Não Informado'")
    @Size(min = 3)
    private String nome;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Produto> produtosDeInteresse;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutosDeInteresse() {
        return produtosDeInteresse;
    }

    public void setProdutosDeInteresse(List<Produto> produtosDeInteresse) {
        this.produtosDeInteresse = produtosDeInteresse;
    }
}
