package ro.fasttrackit.homework.curs9.budgetapplication.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

public class Transaction {
    private int id;
    private final String product;
    private Type type;
    private final double amount;

    public Transaction(int id, String product, Type type, double amount) {
        this.id = id;
        this.product = product;
        this.type = type;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public Type getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 &&
                Objects.equals(product, that.product) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, type, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}
