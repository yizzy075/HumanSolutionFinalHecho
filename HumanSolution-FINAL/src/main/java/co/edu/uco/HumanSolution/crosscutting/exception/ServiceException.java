package co.edu.uco.HumanSolution.crosscutting.exception;

public class ServiceException extends HumanSolutionException {

    private static final long serialVersionUID = 1L;

    public ServiceException(String technicalMessage, String userMessage, Exception rootException) {
        super(technicalMessage, userMessage, rootException);
    }

    public ServiceException(String technicalMessage, String userMessage) {
        super(technicalMessage, userMessage);
    }

    public ServiceException(String userMessage) {
        super(userMessage);
    }
}

