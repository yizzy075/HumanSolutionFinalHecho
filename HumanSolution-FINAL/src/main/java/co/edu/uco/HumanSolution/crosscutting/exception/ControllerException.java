package co.edu.uco.HumanSolution.crosscutting.exception;

public class ControllerException extends HumanSolutionException {

    private static final long serialVersionUID = 1L;

    public ControllerException(String technicalMessage, String userMessage, Exception rootException) {
        super(technicalMessage, userMessage, rootException);
    }

    public ControllerException(String technicalMessage, String userMessage) {
        super(technicalMessage, userMessage);
    }

    public ControllerException(String userMessage) {
        super(userMessage);
    }
}

