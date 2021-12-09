package br.com.zup.LeadCollector.lead;

import br.com.zup.LeadCollector.produto.Produto;
import br.com.zup.LeadCollector.produto.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

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

    @BeforeEach
    public void setup(){
        lead = new Lead();
        lead.setEmail("arthur.dent@dontpanic.com");
        lead.setNome("Arthur Dent");

        produto = new Produto();
        produto.setNome("Chá");
        produto.setId(1);

        lead.setProdutosDeInteresse(Arrays.asList(produto));
    }

}
