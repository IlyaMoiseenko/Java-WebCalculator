package by.tms.calculator.interfaces;

import by.tms.calculator.models.Operation;
import by.tms.calculator.models.User;

import java.util.List;

public interface OperationStorage {
    void save(Operation operation);

    List<Operation> findAll();

    List<Operation> findAllByUser(User user);
}
