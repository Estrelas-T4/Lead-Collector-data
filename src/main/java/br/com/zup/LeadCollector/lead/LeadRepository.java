package br.com.zup.LeadCollector.lead;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeadRepository extends CrudRepository<Lead, String> {
    List<Lead> findAllByProdutosDeInteresseNome(String nomeDoProduto);

}
