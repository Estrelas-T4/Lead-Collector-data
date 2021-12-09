package br.com.zup.LeadCollector.lead;

import br.com.zup.LeadCollector.produto.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(LeadController.class)
public class LeadControllerTest {
    @MockBean
    private LeadService leadService;

    private Lead lead;
    private Produto produto;
    private List<Produto> produtos;
    private List<Lead> leads;

    @BeforeEach
    public void setup(){
        lead = new Lead();
        lead.setNome("Billy");
        lead.setEmail("billy@puroosso.com");

        produto = new Produto();
        produto.setNome("Maça dourada");
        produto.setId(1);

        produtos = Arrays.asList(produto);
        lead.setProdutosDeInteresse(produtos);
        leads = Arrays.asList(lead);
    }
}
