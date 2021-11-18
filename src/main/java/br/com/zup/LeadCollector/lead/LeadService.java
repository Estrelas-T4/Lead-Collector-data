package br.com.zup.LeadCollector.lead;

import br.com.zup.LeadCollector.produto.Produto;
import br.com.zup.LeadCollector.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public Lead salvarLead(Lead lead) {
        List<Produto> produtos = buscarProdutos(lead.getProdutosDeInteresse());

        lead.setProdutosDeInteresse(produtos);
        return leadRepository.save(lead);
    }

    private List<Produto> buscarProdutos(List<Produto> produtos) {
        List<Produto> listaAtualizada = new ArrayList<>();

        for (Produto produto : produtos) {
            if (produtoRepository.existsByNome(produto.getNome())) {
                listaAtualizada.add(produtoRepository.findByNome(produto.getNome()));
            }else {
                listaAtualizada.add(produto);
            }
        }

        return listaAtualizada;
    }

}
