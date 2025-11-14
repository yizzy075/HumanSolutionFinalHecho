package co.edu.uco.HumanSolution.crosscutting.messagecatalog;

public interface MessageCatalog {
    
    String getMessage(String code);
    
    String getMessage(String code, Object... params);
}

