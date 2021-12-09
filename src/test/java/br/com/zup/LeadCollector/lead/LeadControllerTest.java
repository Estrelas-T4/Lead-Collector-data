package br.com.zup.LeadCollector.lead;

import br.com.zup.LeadCollector.produto.Produto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(LeadController.class)
public class LeadControllerTest {
    @MockBean
    private LeadService leadService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private Lead lead;
    private Produto produto;
    private List<Produto> produtos;
    private List<Lead> leads;

    @BeforeEach
    public void setup(){
        objectMapper = new ObjectMapper();
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

    @Test
    public void testarRotaParaBuscarProdutos() throws Exception {
        Mockito.when(leadService.buscarTodosPeloNomeProduto(Mockito.anyString())).thenReturn(leads);

        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.get("/leads")
                .param("nomeProduto", "Foice")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void testarRotaParaCadastrarLeadValidacoesEmail() throws Exception {
        Mockito.when(leadService.salvarLead(Mockito.any(Lead.class))).thenReturn(lead);
        lead.setEmail("emailerrado.com");
        String json = objectMapper.writeValueAsString(lead);

        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.put("/leads")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().is(422));

    }

    @Test
    public void testarRotaParaCadastrarLead() throws Exception {
        Mockito.when(leadService.salvarLead(Mockito.any(Lead.class))).thenReturn(lead);
        String json = objectMapper.writeValueAsString(lead);

        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.put("/leads")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().is(200));

        String jsonDeRespostaDaAPI = respostaDaRequisicao.andReturn().getResponse().getContentAsString();
        Lead leadDaResposta = objectMapper.readValue(jsonDeRespostaDaAPI, Lead.class);

    }

}
