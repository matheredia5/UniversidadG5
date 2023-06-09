
package Control;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Alumno;
import modelo.Inscripcion;
import modelo.Materia;


public class InscripcionData {
    private Connection con;
    private MateriaData matData= new MateriaData();
    private AlumnoData aluData = new AlumnoData();
    
    public InscripcionData(){
        con= Conexion.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion insc) {
        String sql = "INSERT INTO inscripcion(nota, id_alumno, id_materia) VALUES (?,?,?)";
                try{
                    PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setDouble(1, insc.getNota());
                    ps.setInt(2, insc.getAlumno().getIdAlumno());
                    ps.setInt(3, insc.getMateria().getIdMateria());                    
                    
                    ps.executeUpdate();
                    ResultSet rs = ps.getGeneratedKeys();
                    
                    if(rs.next()){
                         insc.setIdInscripcion(rs.getInt(1));
                         JOptionPane.showMessageDialog(null, "Inscripcion Agregada.");
                    }          
                }
                catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, "Error, no se pudo acceder.");

                }
    }
       
    public List<Inscripcion> obtenerInscripciones(){
        
        List<Inscripcion> cursadas = new ArrayList<>();    
            
        try {
            String query = "SELECT * FROM inscripcion;";
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("id_inscripto"));
                insc.setNota(rs.getInt("nota"));
                Alumno a = aluData.buscarAlumno(rs.getInt("id_alumno"));
                insc.setIdAlumno(a.getIdAlumno());               
                Materia m = matData.buscarMateria(rs.getInt("id_materia"));
                insc.setIdMateria(m.getIdMateria());
                

                cursadas.add(insc);
            }      
            ps.close();
        }catch (SQLException ex) {
            JOptionPane.showInternalMessageDialog(null, "Error Inscripcion "+ex.getMessage());
        }
        return cursadas;
    }
    
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id_alumno){
        List<Inscripcion> cursadas =  new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM inscripcion WHERE id_alumno=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();
           
            
            while(rs.next()){
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("id_inscripto"));
                
                Alumno a = aluData.buscarAlumno(rs.getInt("id_alumno"));
                inscripcion.setAlumno(a);
                
                Materia m = matData.buscarMateria(rs.getInt("id_materia"));
                inscripcion.setMateria(m);
                inscripcion.setNota(rs.getDouble("nota"));

                cursadas.add(inscripcion);
            }      
            ps.close();
        }catch (SQLException ex) {
            JOptionPane.showInternalMessageDialog(null, "Error al acceder a Inscripcion "+ex.getMessage());
                   
        }
        
        return cursadas;
        
    }
    
    public void borrarInscripcionMateriaAlumno(int id_alumno,int id_materia){
        
        String sql = "DELETE FROM inscripcion WHERE id_alumno = ? AND id_materia = ? ";

        try {
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id_alumno);
        ps.setInt(2, id_materia);


        ps.executeUpdate();

        JOptionPane.showMessageDialog(null, "Se elimino correctamente");


        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al eliminar");
        }
        }
    
     public void actualizarNota(int id_alumno,int id_materia,double nota){
            String sql = "UPDATE inscripcion SET nota = ? WHERE id_alumno = ? AND id_materia=? ";

            try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, id_alumno);
            ps.setInt(3, id_materia);


            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se actualizo correctamente");


            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar");
            }
            }
     
     public List<Materia> obtenerMateriasNOCursadas(int id_materia){
        List<Materia> materias = new ArrayList<Materia>();
        
        try {
            String sql = "SELECT id_materia, nombre, año FROM materia WHERE estado = 1 AND id_materia NOT IN " +
                    "(SELECT id_materia FROM inscripcion WHERE id_alumno = ?);";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_materia);
            ResultSet resultSet = ps.executeQuery();
            Materia materia;
            
            while(resultSet.next()){
                materia = new Materia();
                materia.setIdMateria(resultSet.getInt("id_materia"));
                materia.setNombre(resultSet.getString("nombre"));
                materia.setAño(resultSet.getInt("año"));
                materias.add(materia);                               
        }
            ps.close();            
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }     
        return materias;
       
  }
     
    public List<Materia> obtenerMateriasCursadas(int id_materia){
        List<Materia> materias = new ArrayList<Materia>();
        
        try {
            String sql = "SELECT inscripcion.id_materia, nombre, año FROM inscripcion," + 
                    "materia WHERE inscripcion.id_materia = materia.id_materia\n" +
                    "AND inscripcion.id_alumno=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_materia);
            ResultSet rs = ps.executeQuery();
            Materia materia;
            
            while(rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAño(rs.getInt("año"));
                materias.add(materia);                               
        }
            ps.close();            
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener inscripciones");
        }     
        return materias;
      }
      
     public List<Alumno> obtenerAlumnosXMateria(int id_materia){
          List<Alumno> alumnos = new ArrayList<Alumno>();
          
          String sql = "SELECT inscripcion.id_alumno,dni,nombre,apellido FROM inscripcion,alumno WHERE inscripcion.id_alumno = alumno.id_alumno AND inscripcion.id_materia = ?;";
          
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_materia);
            ResultSet rs = ps.executeQuery();
            Alumno alumno;
            
            while(rs.next()){
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("id_alumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumnos.add(alumno);                               
        }
            ps.close();            
            
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener alumnos");
        }
          
        return alumnos;
          
      }
     
    public Inscripcion obtenerInscripcionPorMateriaYAlumno(int id_materia, int id_alumno) {
    Inscripcion inscripcion = null;
    
    try {
        String sql = "SELECT * FROM inscripcion WHERE id_materia = ? AND id_alumno = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id_materia);
        ps.setInt(2, id_alumno);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            inscripcion = new Inscripcion();
            inscripcion.setIdInscripcion(rs.getInt("id_inscripto"));
            inscripcion.setNota(rs.getDouble("nota"));
            
            Alumno alumno = aluData.buscarAlumno(rs.getInt("id_alumno"));
            inscripcion.setAlumno(alumno);
            
            Materia materia = matData.buscarMateria(rs.getInt("id_materia"));
            inscripcion.setMateria(materia);
        }
        
        ps.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al obtener la inscripción: " + ex.getMessage());
    }
    
    return inscripcion;
}
 
}