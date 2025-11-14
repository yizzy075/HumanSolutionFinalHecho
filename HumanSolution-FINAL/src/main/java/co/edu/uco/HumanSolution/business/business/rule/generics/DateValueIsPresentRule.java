package co.edu.uco.HumanSolution.business.business.rule.generics;

import co.edu.uco.HumanSolution.business.business.rule.Rule;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import co.edu.uco.HumanSolution.crosscutting.helper.StringHelper;

import java.time.LocalDate;

public class DateValueIsPresentRule implements Rule<LocalDate> {

    private String fieldName;
    private String errorMessage;

    public DateValueIsPresentRule(String fieldName, String errorMessage) {
        setFieldName(fieldName);
        setErrorMessage(errorMessage);
    }

    @Override
    public void validate(LocalDate data) {
        if (data == null) {
            throw new BusinessException(
                    String.format("El campo %s es obligatorio", fieldName),
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
