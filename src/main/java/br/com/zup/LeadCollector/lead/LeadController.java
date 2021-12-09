package br.com.zup.LeadCollector.lead;

import br.com.zup.LeadCollector.lead.Lead;
import br.com.zup.LeadCollector.lead.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/leads")
@RestControllerAdvice
public class LeadController {
    @Autowired
    private LeadService leadService;

    @PutMapping
    public Lead cadastrarLead(@RequestBody @Valid Lead lead){
        return leadService.salvarLead(lead);
    }

    @GetMapping()
    public List<Lead> buscarProdutos(@RequestParam String nomeProduto){
        return leadService.buscarTodosPeloNomeProduto(nomeProduto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public void manipularValidacao(MethodArgumentNotValidException exception){

    }

}
