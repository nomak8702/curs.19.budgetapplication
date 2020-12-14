package ro.fasttrackit.homework.curs9.budgetapplication.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.homework.curs9.budgetapplication.controller.ResourceNotFoundException;
import ro.fasttrackit.homework.curs9.budgetapplication.model.Transaction;
import ro.fasttrackit.homework.curs9.budgetapplication.model.Type;

import java.util.*;
import java.util.stream.Collectors;

import static ro.fasttrackit.homework.curs9.budgetapplication.model.Type.*;

@Service
public class TransactionService {

    List<Transaction> list = List.of(new Transaction(1, "XRP", BUY, 365.89),
            new Transaction(2, "Stellar", BUY, 658.78),
            new Transaction(3, "BTC", BUY, 489.89),
            new Transaction(4, "ETH", SELL, 999.9));

    List<Transaction> transactionList;

    public TransactionService(Collection<Transaction> transactionList) {
        this.transactionList = new ArrayList<>();
        this.transactionList.addAll(list);
        this.transactionList.addAll(transactionList);
    }

    public Transaction addTransaction(Transaction transaction) {
        transaction.setId(calculatedId());
        transactionList.add(transaction);
        return transaction;
    }

    private int calculatedId() {
        return transactionList.stream()
                .mapToInt(Transaction::getId)
                .max()
                .orElse(0) + 1;
    }

    public List<Transaction> getAllTransactions() {
        return transactionList;
    }

    public List<Transaction> getTransactionFiltred(Type type,
                                                   Double minAmount, Double maxAmount) {
        return transactionList.stream()
                .filter(t -> type == null || t.getType().equals(type))
                .filter(t -> minAmount == null || t.getAmount() >= minAmount)
                .filter(t -> maxAmount == null || t.getAmount() <= maxAmount)
                .collect(Collectors.<Transaction>toList());
    }

    public Optional<Transaction> getTransactionById(int idTransaction) {
        return transactionList.stream()
                .filter(t -> t.getId() == idTransaction)
                .findFirst();
    }

    public Transaction replaceTransaction(int idTransaction, Transaction transaction) {
        transaction.setId(idTransaction);
        deleteTransaction(idTransaction);
        addTransaction(transaction);
        return transaction;

    }

    public Transaction deleteTransaction(int idTransaction) {

        Transaction transaction = getOrThrow(idTransaction);
        transactionList.remove(transaction);
        return transaction;
    }

    private Transaction getOrThrow(int idTransaction) {
        return getTransactionById(idTransaction)
                .orElseThrow(() -> new ResourceNotFoundException("Not find id !! " + idTransaction));
    }

    public Transaction patchTransaction(int idTransaction, Transaction transaction) {
        Transaction oldTransaction = getOrThrow(idTransaction);
        String product = oldTransaction.getProduct();
        double amount = oldTransaction.getAmount();

        if (transaction.getProduct() != null) {
            product = transaction.getProduct();
        }
        if (transaction.getAmount() != 0) {
            amount = transaction.getAmount();
        }

        return replaceTransaction(idTransaction,
                new Transaction(oldTransaction.getId(), product,
                        oldTransaction.getType(),
                        amount));
    }

    public Map<Type, Double> mapTypeAmount() {
        Map<Type, Double> result = new HashMap<>();

        double resultSell = 0;
        double resultBuy = 0;

        for (Transaction trans : transactionList) {
            if (trans.getType().equals(SELL)) {
                resultSell += trans.getAmount();
                result.put(trans.getType(), resultSell);
            } else {
                resultBuy += trans.getAmount();
                result.put(trans.getType(), resultBuy);
            }
        }
        return result;
    }

    public Map<String, Double> mapProductAmount() {
        Map<String, Double> result = new HashMap<>();

        double resultSum = 0;
        for (Transaction trans : transactionList) {
            resultSum += trans.getAmount();
            result.put(trans.getProduct(),
                    resultSum);

        }
        return result;
    }
}
