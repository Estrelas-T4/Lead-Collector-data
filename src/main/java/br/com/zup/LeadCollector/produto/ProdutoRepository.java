package br.com.zup.LeadCollector.produto;

import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
    boolean existsByNome(String nome);

    Produto findByNome(String nome);

}
