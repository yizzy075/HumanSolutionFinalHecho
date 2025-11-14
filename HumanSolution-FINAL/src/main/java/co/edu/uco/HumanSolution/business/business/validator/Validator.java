package co.edu.uco.HumanSolution.business.business.validator;

public interface Validator<T> {
    
    void validate(T data);
}

