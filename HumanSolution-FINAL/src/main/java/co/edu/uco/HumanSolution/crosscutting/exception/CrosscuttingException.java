package co.edu.uco.HumanSolution.crosscutting.exception;

public class CrosscuttingException extends HumanSolutionException {

    private static final long serialVersionUID = 1L;

    public CrosscuttingException(String technicalMessage, String userMessage, Exception rootException) {
        super(technicalMessage, userMessage, rootException);
    }

    public CrosscuttingException(String technicalMessage, String userMessage) {
        super(technicalMessage, userMessage);
    }

    public CrosscuttingException(String userMessage) {
        super(userMessage);
    }
}

