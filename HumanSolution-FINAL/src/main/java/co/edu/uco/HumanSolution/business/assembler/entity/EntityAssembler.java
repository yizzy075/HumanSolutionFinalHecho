package co.edu.uco.HumanSolution.business.assembler.entity;

public interface EntityAssembler<D, E> {

    D toDomain(E entity);

    E toEntity(D domain);
}