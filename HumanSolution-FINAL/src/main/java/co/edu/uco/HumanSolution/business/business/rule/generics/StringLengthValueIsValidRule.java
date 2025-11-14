package co.edu.uco.HumanSolution.business.business.rule.generics;

import co.edu.uco.HumanSolution.business.business.rule.Rule;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import co.edu.uco.HumanSolution.crosscutting.helper.NumericHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.StringHelper;

public class StringLengthValueIsValidRule implements Rule<String> {

    private String fieldName;
    private int minimumLength;
    private int maximumLength;
    private String errorMessage;

    public StringLengthValueIsValidRule(String fieldName, int minimumLength, int maximumLength, String errorMessage) {
        setFieldName(fieldName);
        setMinimumLength(minimumLength);
        setMaximumLength(maximumLength);
        setErrorMessage(errorMessage);
    }

    @Override
    public void validate(String data) {
        if (!StringHelper.isEmpty(data)) {
            int length = StringHelper.length(data);
            
            if (NumericHelper.isLessThan(length, minimumLength) || 
                NumericHelper.isGreaterThan(length, maximumLength)) {
                throw new BusinessException(
                        String.format("El campo %s debe tener entre %d y %d caracteres", 
                                fieldName, minimumLength, maximumLength),
                        errorMessage
                );
            }
        }
    }

    private void setFieldName(String fieldName) {
        this.fieldName = StringHelper.getDefault(fieldName);
    }

    private void setMinimumLength(int minimumLength) {
        this.minimumLength = minimumLength;
    }

    private void setMaximumLength(int maximumLength) {
        this.maximumLength = maximumLength;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = StringHelper.getDefault(errorMessage);
    }
}

