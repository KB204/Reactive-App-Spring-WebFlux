package net.reactiveapp.reactiveclient.controller;

import net.reactiveapp.reactiveclient.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;


@Controller
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping( "/")
    public String landingPage(Model model) {
        IReactiveDataDriverContextVariable reactive = new ReactiveDataDriverContextVariable(service.getAllTransaction(), 1);
        model.addAttribute("transactions",reactive);
        return "index";
    }

    @GetMapping("/{companyId}/companyTransactions")
    public String companyTransactionsPage(@PathVariable Long companyId, Model model) {
        IReactiveDataDriverContextVariable reactive = new ReactiveDataDriverContextVariable(service.getCompanyTransaction(companyId),1);
        model.addAttribute("transactions",reactive);
        return "details";
    }
}
