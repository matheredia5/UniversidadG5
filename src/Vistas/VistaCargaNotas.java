
package Vistas;

import Control.AlumnoData;
import Control.InscripcionData;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Alumno;
import modelo.Inscripcion;
import modelo.Materia;

public class VistaCargaNotas extends javax.swing.JInternalFrame {
    private InscripcionData inscData=new InscripcionData();
    private AlumnoData aData=new AlumnoData();
    private DefaultTableModel modelo=new DefaultTableModel();

    public VistaCargaNotas() {
        initComponents();
        cargarAlumnos();
        armarCabecera();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelCargaDeNotas = new javax.swing.JLabel();
        jLabelAlumno = new javax.swing.JLabel();
        jcbAlumnos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtNotas = new javax.swing.JTable();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        jLabelCargaDeNotas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelCargaDeNotas.setText("CARGA DE NOTAS");

        jLabelAlumno.setText("ALUMNO:");

        jcbAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAlumnosActionPerformed(evt);
            }
        });

        jtNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtNotas);

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jLabelCargaDeNotas))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabelAlumno)
                        .addGap(18, 18, 18)
                        .addComponent(jcbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelCargaDeNotas)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAlumno)
                    .addComponent(jcbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardar)
                    .addComponent(jButtonCancelar))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAlumnosActionPerformed
        // TODO add your handling code here:
        llenarTabla();
    }//GEN-LAST:event_jcbAlumnosActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada=jtNotas.getSelectedRow();
        
        if(filaSeleccionada!=-1){
            Alumno alumSelec=(Alumno)jcbAlumnos.getSelectedItem();
            int idMateria=(Integer)modelo.getValueAt(filaSeleccionada, 0);
            String nombredemateria=(String)modelo.getValueAt(filaSeleccionada, 1);
            
            int idAlumno=alumSelec.getIdAlumno();        

            
            Inscripcion insc=new Inscripcion();
            insc.setIdAlumno(idAlumno);
            insc.setIdMateria(idMateria);
            insc.setNota(WIDTH);
            
            inscData.actualizarNota(WIDTH, idAlumno, idMateria);
             
            borrarFilas();
          
        }else{
        
            JOptionPane.showMessageDialog(this,"Usted debe seleccionar una materia");
            
        }
    
        
        
        
    }//GEN-LAST:event_jButtonGuardarActionPerformed
    
    private void cargarAlumnos() {
        List<Alumno> alumnos=aData.listarAlumnos();
        for(Alumno alu:alumnos){
            jcbAlumnos.addItem(alu);
        }
        
    }
    
    private void armarCabecera() {
        ArrayList<Object> titulos=new ArrayList<>();
        titulos.add("Codigo");
        titulos.add("Nombre");
        titulos.add("Nota");
        for(Object tit:titulos){
        
            modelo.addColumn(tit);
        }
        jtNotas.setModel(modelo);
        
    }
    
    private void llenarTabla() {
    // Obtener el alumno seleccionado del JComboBox
    Alumno alumnoSeleccionado=(Alumno)jcbAlumnos.getSelectedItem();{
        Inscripcion insc = new Inscripcion();
        insc.getNota();
            
    // Limpiar el modelo de la tabla
    modelo.setRowCount(0);

        List<Materia> lista=inscData.obtenerMateriasCursadas(alumnoSeleccionado.getIdAlumno());
            for(Materia i:lista){
            
                modelo.addRow(new Object[]{i.getIdMateria(),i.getNombre(),insc.getNota()});

//        List<Inscripcion> lista=inscData.obtenerMateriasCursadas(alumnoSeleccionado.getIdAlumno());
//            for(Inscripcion i:lista){
//            
//                modelo.addRow(new Object[]{i.getIdMateria(),i.getMateria(),i.getNota()});
        }
    }
}

    private void borrarFilas(){
    
        int filas=modelo.getRowCount()-1;
        for(int i=filas;i >=0;i--){
        
            modelo.removeRow(i);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabelAlumno;
    private javax.swing.JLabel jLabelCargaDeNotas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<Alumno> jcbAlumnos;
    private javax.swing.JTable jtNotas;
    // End of variables declaration//GEN-END:variables
}
