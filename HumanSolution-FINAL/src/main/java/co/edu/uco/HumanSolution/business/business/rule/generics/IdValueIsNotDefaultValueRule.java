package co.edu.uco.HumanSolution.business.business.rule.generics;

import co.edu.uco.HumanSolution.business.business.rule.Rule;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;

import java.util.UUID;

public class IdValueIsNotDefaultValueRule implements Rule<UUID> {

    private String fieldName;
    private String errorMessage;

    public IdValueIsNotDefaultValueRule(String fieldName, String errorMessage) {
        setFieldName(fieldName);
        setErrorMessage(errorMessage);
    }

    @Override
    public void validate(UUID data) {
        if (UUIDHelper.isDefault(data)) {
            throw new BusinessException(
                    String.format("El campo %s no puede tener un valor por defecto", fieldName),
                    errorMessage
            );
        }
    }

    private void setFieldName(String fieldName) {
        this.fieldName = co.edu.uco.HumanSolution.crosscutting.helper.StringHelper.getDefault(fieldName);
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = co.edu.uco.HumanSolution.crosscutting.helper.StringHelper.getDefault(errorMessage);
    }
}

