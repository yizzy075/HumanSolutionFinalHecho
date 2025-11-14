package co.edu.uco.HumanSolution.crosscutting.exception;

public class DataException extends HumanSolutionException {

    private static final long serialVersionUID = 1L;

    public DataException(String technicalMessage, String userMessage, Exception rootException) {
        super(technicalMessage, userMessage, rootException);
    }

    public DataException(String technicalMessage, String userMessage) {
        super(technicalMessage, userMessage);
    }

    public DataException(String userMessage) {
        super(userMessage);
    }
}

