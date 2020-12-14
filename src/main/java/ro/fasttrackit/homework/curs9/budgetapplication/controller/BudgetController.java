package ro.fasttrackit.homework.curs9.budgetapplication.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.homework.curs9.budgetapplication.model.Transaction;
import ro.fasttrackit.homework.curs9.budgetapplication.model.Type;
import ro.fasttrackit.homework.curs9.budgetapplication.service.TransactionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class BudgetController {

    private final TransactionService transactionService;

    public BudgetController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/all")
    List<Transaction> getTransactions() {
        return transactionService.getAllTransactions();
    }


    @GetMapping
    public List<Transaction> getTransactionFilter(@RequestParam(required = false) Type type,
                                                  @RequestParam(required = false) Double minAmount, @RequestParam(required = false) Double maxAmount) {

        return transactionService.getTransactionFiltred(type, minAmount, maxAmount);
    }

    @GetMapping("{idTransaction}")
    public Transaction getTransactionById(@PathVariable int idTransaction) {
        return transactionService.getTransactionById(idTransaction)
                .orElseThrow(() -> new ResourceNotFoundException("Not find Id!!! " + idTransaction));
    }

    @PutMapping("{idTransaction}")
    Transaction replaceTransaction(@PathVariable int idTransaction, @RequestBody Transaction transaction) {
        return transactionService.replaceTransaction(idTransaction, transaction);
    }

    @PatchMapping("{idTransaction}")
    Transaction patchTransaction(@PathVariable int idTransaction, @RequestBody Transaction transaction) {
        return transactionService.patchTransaction(idTransaction, transaction);
    }

    @DeleteMapping("{idTransaction}")
    Transaction deletTransaction(@PathVariable int idTransaction) {
        return transactionService.deleteTransaction(idTransaction);
    }

    @GetMapping("/reports/type")
    Map<Type, Double> getTypeSumAmount() {
        return transactionService.mapTypeAmount();
    }

    @GetMapping("/reports/product")
    Map<String, Double> getProductSumAmount() {
        return transactionService.mapProductAmount();
    }


    @PostMapping
    Transaction addNewTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

}
