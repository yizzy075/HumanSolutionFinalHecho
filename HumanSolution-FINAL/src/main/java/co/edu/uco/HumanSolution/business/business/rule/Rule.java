package co.edu.uco.HumanSolution.business.business.rule;

public interface Rule<T> {
    
    void validate(T data);
}

