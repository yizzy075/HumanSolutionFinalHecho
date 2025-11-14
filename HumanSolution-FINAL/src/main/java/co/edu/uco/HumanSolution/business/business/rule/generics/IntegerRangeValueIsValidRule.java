package co.edu.uco.HumanSolution.business.business.rule.generics;

import co.edu.uco.HumanSolution.business.business.rule.Rule;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import co.edu.uco.HumanSolution.crosscutting.helper.StringHelper;

public class IntegerRangeValueIsValidRule implements Rule<Integer> {

    private String fieldName;
    private int minValue;
    private int maxValue;
    private String errorMessage;

    public IntegerRangeValueIsValidRule(String fieldName, int minValue, int maxValue, String errorMessage) {
        setFieldName(fieldName);
        this.minValue = minValue;
        this.maxValue = maxValue;
        setErrorMessage(errorMessage);
    }

    @Override
    public void validate(Integer data) {
        if (data == null) {
            throw new BusinessException(
                    String.format("El campo %s es obligatorio", fieldName),
                    errorMessage
            );
        }

        if (data < minValue || data > maxValue) {
            throw new BusinessException(
                    String.format("El campo %s debe estar entre %d y %d. Valor proporcionado: %d",
                            fieldName, minValue, maxValue, data),
                    errorMessage
            );
        }
    }

    private void setFieldName(String fieldName) {
        this.fieldName = StringHelper.getDefault(fieldName);
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = StringHelper.getDefault(errorMessage);
    }
}
