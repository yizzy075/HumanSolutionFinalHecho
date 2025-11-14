package co.edu.uco.HumanSolution.crosscutting.exception;

public class EntityException extends HumanSolutionException {

    private static final long serialVersionUID = 1L;

    public EntityException(String technicalMessage, String userMessage, Exception rootException) {
        super(technicalMessage, userMessage, rootException);
    }

    public EntityException(String technicalMessage, String userMessage) {
        super(technicalMessage, userMessage);
    }

    public EntityException(String userMessage) {
        super(userMessage);
    }
}

