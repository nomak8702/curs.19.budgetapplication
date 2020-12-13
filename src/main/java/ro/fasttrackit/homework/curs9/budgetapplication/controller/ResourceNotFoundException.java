package ro.fasttrackit.homework.curs9.budgetapplication.controller;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
