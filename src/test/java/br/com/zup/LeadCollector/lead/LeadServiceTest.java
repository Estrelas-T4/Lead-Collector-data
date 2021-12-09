package br.com.zup.LeadCollector.lead;

import br.com.zup.LeadCollector.produto.Produto;
import br.com.zup.LeadCollector.produto.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class LeadServiceTest {
    @MockBean
    private LeadRepository leadRepository;
    @MockBean
    private ProdutoRepository produtoRepository;

    @Autowired
    private LeadService leadService;

    private Lead lead;
    private Produto produto;
    private List<Produto> produtos;

    @BeforeEach
    public void setup(){
        lead = new Lead();
        lead.setEmail("arthur.dent@dontpanic.com");
        lead.setNome("Arthur Dent");

        produto = new Produto();
        produto.setNome("Chá");
        produto.setId(1);

        produtos = Arrays.asList(produto);
        lead.setProdutosDeInteresse(produtos);
    }

    @Test
    public void testarBuscarProdutosCadastradosCaminhoPositivo(){
        Mockito.when(produtoRepository.existsByNome(Mockito.anyString())).thenReturn(true);
        Mockito.when(produtoRepository.findByNome(Mockito.anyString())).thenReturn(produto);

        List<Produto> listaAtualizada = leadService.buscarProdutos(produtos);

        for(Produto produtoDaListaAtualizada : listaAtualizada){
            Assertions.assertEquals(produtoDaListaAtualizada, produto);
            Assertions.assertEquals(produtoDaListaAtualizada.getId(), produto.getId());
        }

        Assertions.assertTrue(listaAtualizada instanceof Iterable<?>);
    }

    @Test
    public void testarBuscarProdutosNaoCadastradosCaminhoPositivo(){
        var produtoNaoCadastrado = new Produto();
        produtoNaoCadastrado.setNome("Dinamite Pangaláctica");
        Mockito.when(produtoRepository.existsByNome(Mockito.anyString())).thenReturn(false);
        Mockito.when(produtoRepository.findByNome(Mockito.anyString())).thenReturn(produto);

        List<Produto> listaAtualizada = leadService.buscarProdutos(Arrays.asList(produtoNaoCadastrado));

        for(Produto produtoDaListaAtualizada : listaAtualizada){
            Assertions.assertNotEquals(produtoDaListaAtualizada, produto);
            Assertions.assertEquals(produtoDaListaAtualizada.getId(), 0);
        }

        Mockito.verify(produtoRepository, Mockito.times(0)).findByNome(Mockito.anyString());
        Assertions.assertTrue(listaAtualizada instanceof Iterable<?>);
    }

}
