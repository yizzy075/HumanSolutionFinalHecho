package co.edu.uco.HumanSolution.crosscutting.exception;

public class BusinessException extends HumanSolutionException {

    private static final long serialVersionUID = 1L;

    public BusinessException(String technicalMessage, String userMessage, Exception rootException) {
        super(technicalMessage, userMessage, rootException);
    }

    public BusinessException(String technicalMessage, String userMessage) {
        super(technicalMessage, userMessage);
    }

    public BusinessException(String userMessage) {
        super(userMessage);
    }
}

