package co.edu.uco.HumanSolution.data.factory;

import co.edu.uco.HumanSolution.data.dao.*;
import co.edu.uco.HumanSolution.data.factory.postgresql.PostgreSqlDAOFactory;

public abstract class DAOFactory {

    // AGREGAR ESTE MÉTODO ESTÁTICO
    public static DAOFactory getDAOFactory() {
        return PostgreSqlDAOFactory.getInstance();
    }

    // Métodos abstractos existentes
    public abstract void initTransaction();
    public abstract void commitTransaction();
    public abstract void rollbackTransaction();
    public abstract void closeConnection();

    public abstract TipoDocumentoDAO getTipoDocumentoDAO();
    public abstract TipoPermisoDAO getTipoPermisoDAO();
    public abstract EstadoSolicitudDAO getEstadoSolicitudDAO();
    public abstract TipoHoraExtraDAO getTipoHoraExtraDAO();
    public abstract EstadoPuestoDAO getEstadoPuestoDAO();
    public abstract RolDAO getRolDAO();
    public abstract PermisoSistemaDAO getPermisoSistemaDAO();
    public abstract RolPermisoDAO getRolPermisoDAO();
    public abstract UnidadOrganizativaDAO getUnidadOrganizativaDAO();
    public abstract UsuarioDAO getUsuarioDAO();
    public abstract PuestoDAO getPuestoDAO();
    public abstract ContratoDAO getContratoDAO();
    public abstract ExperienciaLaboralDAO getExperienciaLaboralDAO();
    public abstract EvaluacionDesempenoDAO getEvaluacionDesempenoDAO();
    public abstract UsuarioDocumentoDAO getUsuarioDocumentoDAO();
    public abstract UsuarioPermisoDAO getUsuarioPermisoDAO();
    public abstract UsuarioHoraExtraDAO getUsuarioHoraExtraDAO();
}