package co.edu.uco.HumanSolution.business.assembler.dto;

import java.util.List;
import java.util.stream.Collectors;

public interface DTOAssembler<D, T> {

    D toDomain(T dto);

    T toDTO(D domain);

    default List<D> toDomainList(List<T> dtoList) {
        return dtoList.stream().map(this::toDomain).collect(Collectors.toList());
    }

    default List<T> toDTOList(List<D> domainList) {
        return domainList.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
