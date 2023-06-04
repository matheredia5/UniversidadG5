
package modelo;

public class Materia {

    private int id_materia;
    private String nombre;
    private int año;
    private boolean estado;

    public Materia(int id_materia, String nombre, int año, boolean estado) {
        this.id_materia = id_materia;
        this.nombre = nombre;
        this.año = año;
        this.estado = estado;
    }

    public Materia(String nombre, int año, boolean estado) {
        this.nombre = nombre;
        this.año = año;
        this.estado = estado;
    }

    public Materia() {
    }

    public int getIdMateria() {
        return id_materia;
    }

    public void setIdMateria(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre + " , " + año + "." ;
    }

    

    
}