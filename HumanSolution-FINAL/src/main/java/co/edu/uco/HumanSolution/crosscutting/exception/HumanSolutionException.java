package co.edu.uco.HumanSolution.crosscutting.exception;

public class HumanSolutionException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String technicalMessage;
    private String userMessage;
    private Exception rootException;

    // Constructor con 3 parámetros: mensaje técnico, mensaje de usuario y excepción raíz
    public HumanSolutionException(String technicalMessage, String userMessage, Exception rootException) {
        super(technicalMessage);
        setTechnicalMessage(technicalMessage);
        setUserMessage(userMessage);
        setRootException(rootException);
    }

    // Constructor con 2 parámetros: mensaje técnico y mensaje de usuario
    public HumanSolutionException(String technicalMessage, String userMessage) {
        this(technicalMessage, userMessage, null);
    }

    // Constructor con 1 parámetro: solo mensaje de usuario
    public HumanSolutionException(String userMessage) {
        this(userMessage, userMessage, null);
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    private void setTechnicalMessage(String technicalMessage) {
        this.technicalMessage = technicalMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    private void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public Exception getRootException() {
        return rootException;
    }

    private void setRootException(Exception rootException) {
        this.rootException = rootException;
    }
}