package co.edu.uco.HumanSolution.business.business.rule.generics;

import co.edu.uco.HumanSolution.business.business.rule.Rule;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import co.edu.uco.HumanSolution.crosscutting.helper.StringHelper;

public class StringFormatValueIsValidRule implements Rule<String> {

    private String fieldName;
    private String formatRegex;
    private String errorMessage;

    public StringFormatValueIsValidRule(String fieldName, String formatRegex, String errorMessage) {
        setFieldName(fieldName);
        setFormatRegex(formatRegex);
        setErrorMessage(errorMessage);
    }

    @Override
    public void validate(String data) {
        if (!StringHelper.isEmpty(data) && !data.matches(formatRegex)) {
            throw new BusinessException(
                    String.format("El formato del campo %s no es válido", fieldName),
                    errorMessage
            );
        }
    }

    private void setFieldName(String fieldName) {
        this.fieldName = StringHelper.getDefault(fieldName);
    }

    private void setFormatRegex(String formatRegex) {
        this.formatRegex = StringHelper.getDefault(formatRegex);
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = StringHelper.getDefault(errorMessage);
    }
}

