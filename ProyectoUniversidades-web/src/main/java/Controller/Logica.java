/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.*;
import Interfaces.DiplomadoFacadeLocal;
import Interfaces.EstudianteFacadeLocal;
import Interfaces.UniversidadFacadeLocal;
import Modelo.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jonathan
 */
@Named(value = "logica")
@SessionScoped
public class Logica implements Serializable {

    @EJB
    UniversidadFacadeLocal universidadFacade;

    @EJB
    DiplomadoFacadeLocal diplomadoFacade;

    @EJB
    EstudianteFacadeLocal estudianteFacade;

    Usuario usuario = new Usuario();

    Institucion institucion = new Institucion();

    Programa programa = new Programa();

    Universidad universidad;

    Diplomado diplomado;

    Estudiante estudiante;

    String universidadNombre;

    String diplomadoNombre;

    String estudianteNombre;

    String nombreUniversidad;

    String nombreDiplomado;

    String nombreEstudiante;

    List<Diplomado> listaDiplomados;

    List<Estudiante> listaEstudiantes;

    public Logica() {
        usuario = new Usuario();
        institucion = new Institucion();
        programa = new Programa();
    }

    public void guardarUniversidad(Institucion instituto) {
        Universidad universidad = new Universidad(instituto.getNombre(), instituto.getExtension(), instituto.getEscudo());
        universidadFacade.create(universidad);
        //institucion = null;
        reset();
    }

    public void gurdarDiplomado(Programa program) {
        Diplomado diplomado = new Diplomado(program.getNombre(), programa.getDuracion());
        universidad = universidadFacade.find(0);
        diplomado.setUniversidad(universidad);
        diplomadoFacade.create(diplomado);
        //programa = null;
    }

    public void guardaEstudiante(Usuario usuario) {
        Estudiante estudiante = new Estudiante(usuario.getNombre(), usuario.getCedula());
        diplomado = diplomadoFacade.find(0);
        estudiante.setDiplomado(diplomado);
        estudianteFacade.create(estudiante);
        //usuario = null;
    }

    public List<Universidad> traerUniversidades() {
        return universidadFacade.findNombre();
    }

    public List<Diplomado> traerDiplomados() {
        return diplomadoFacade.findNombre();
    }

    public List<Estudiante> traerEstudiantes() {
        return estudianteFacade.findNombre();
    }

    public void asociarDiplomado(String uni, String diplo) {
        universidad = universidadFacade.findName(uni);
        diplomado = diplomadoFacade.findName(diplo);
        diplomado.setUniversidad(universidad);
        universidad.getDiplomados().add(diplomado);
        universidadFacade.edit(universidad);
    }

    public void asociarEstudiante(String diplo, String estu) {
        diplomado = diplomadoFacade.findName(diplo);
        estudiante = estudianteFacade.findName(estu);
        estudiante.setDiplomado(diplomado);
        diplomado.getListaEstudiantes().add(estudiante);
        diplomadoFacade.edit(diplomado);
    }

    public void cambioUniversidad(String nombreUniversidad) {
        if (nombreUniversidad != null && !nombreUniversidad.equals("")) {
            universidad = universidadFacade.findName(nombreUniversidad);
            listaDiplomados = diplomadoFacade.findAsociadas(universidad.getId());
        } else {
            listaDiplomados = new ArrayList<>();
        }
    }

    public void cambioDiplomado(String nombreDiplomado) {
        if (nombreDiplomado != null && !nombreDiplomado.equals("")) {
            diplomado = diplomadoFacade.findName(nombreDiplomado);
            listaEstudiantes = estudianteFacade.findAsociados(diplomado.getId());
        } else {
            listaEstudiantes = new ArrayList<>();
        }
    }

    public void desasociarDiplomado(String diplomadoNombre, String universidadNombre) {
        diplomado = diplomadoFacade.findName(diplomadoNombre);
        universidad = universidadFacade.find(0);
        diplomado.setUniversidad(universidad);
        diplomadoFacade.edit(diplomado);
    }

    public void desasociarEstudiante(String diplomadoNombre, String estudianteNombre) {
        estudiante = estudianteFacade.findName(estudianteNombre);
        diplomado = diplomadoFacade.find(0);
        estudiante.setDiplomado(diplomado);
        estudianteFacade.edit(estudiante);
    }

    public List<Estudiante> traerEstudiantesAsociados() {
        return listaEstudiantes;
    }

    public List<Diplomado> traerDiplomadosAsociados() {
        return listaDiplomados;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public UniversidadFacadeLocal getUniversidadFacade() {
        return universidadFacade;
    }

    public void setUniversidadFacade(UniversidadFacadeLocal universidadFacade) {
        this.universidadFacade = universidadFacade;
    }

    public DiplomadoFacadeLocal getDiplomadoFacade() {
        return diplomadoFacade;
    }

    public void setDiplomadoFacade(DiplomadoFacadeLocal diplomadoFacade) {
        this.diplomadoFacade = diplomadoFacade;
    }

    public EstudianteFacadeLocal getEstudianteFacade() {
        return estudianteFacade;
    }

    public void setEstudianteFacade(EstudianteFacadeLocal estudianteFacade) {
        this.estudianteFacade = estudianteFacade;
    }

    public String getUniversidadNombre() {
        return universidadNombre;
    }

    public void setUniversidadNombre(String universidadNombre) {
        this.universidadNombre = universidadNombre;
    }

    public String getDiplomadoNombre() {
        return diplomadoNombre;
    }

    public void setDiplomadoNombre(String diplomadoNombre) {
        this.diplomadoNombre = diplomadoNombre;
    }

    public String getEstudianteNombre() {
        return estudianteNombre;
    }

    public void setEstudianteNombre(String estudianteNombre) {
        this.estudianteNombre = estudianteNombre;
    }

    public String getNombreUniversidad() {
        return nombreUniversidad;
    }

    public void setNombreUniversidad(String nombreUniversidad) {
        this.nombreUniversidad = nombreUniversidad;
    }

    public String getNombreDiplomado() {
        return nombreDiplomado;
    }

    public void setNombreDiplomado(String nombreDiplomado) {
        this.nombreDiplomado = nombreDiplomado;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public void reset() {
        RequestContext.getCurrentInstance().reset("universidad:panel");
    }

}
