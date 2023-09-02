package by.tms.calculator.services;

import by.tms.calculator.interfaces.OperationStorage;
import by.tms.calculator.storage.operationStorage.JdbcOperationStorage;
import by.tms.calculator.models.Operation;
import by.tms.calculator.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OperationService {

    private final OperationStorage storage = new JdbcOperationStorage();

    public Operation calculate(Operation operation) {
        switch (operation.getType()) {
            case SUM:
                operation.setResult(operation.getNum1() + operation.getNum2());
                storage.save(operation);
                return operation;
            case SUB:
                operation.setResult(operation.getNum1() - operation.getNum2());
                storage.save(operation);
                return operation;
            case MUL:
                operation.setResult(operation.getNum1() * operation.getNum2());
                storage.save(operation);
                return operation;
            case DIV:
                operation.setResult(operation.getNum1() / operation.getNum2());
                storage.save(operation);
                return operation;
        }

        throw new RuntimeException();
    }

    public List<String> getHistory() {
        List<Operation> all = storage.findAll();
        List<String> result = new ArrayList<>();

        for (Operation operation : all) {
            result.add("Result = " + operation.getNum1() + " " + operation.getNum2() + " " + operation.getResult());
        }

        return result;
    }

    public List<String> getHistoryByUser(User user) {
        List<Operation> allByUser = storage.findAllByUser(user);
        List<String> result = new ArrayList<>();

        if (allByUser.isEmpty()) {
            result.add("The story is empty");
            return result;
        }

        for (Operation operation : allByUser) {
            result.add(operation.getNum1() + operation.getType().toString() + operation.getNum2() + " = " + operation.getResult());
        }

        return result;
    }

    public boolean deleteByUserId(UUID id) {
        return storage.deleteAllByUserId(id);
    }
}
