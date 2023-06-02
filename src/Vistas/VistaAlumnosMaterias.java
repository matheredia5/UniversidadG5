
package Vistas;

import Control.InscripcionData;
import Control.MateriaData;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Alumno;
import modelo.Materia;


public class VistaAlumnosMaterias extends javax.swing.JInternalFrame {
    
    private InscripcionData inscData=new InscripcionData();
    private MateriaData mData=new MateriaData();
    private DefaultTableModel modelo=new DefaultTableModel();
    
    public VistaAlumnosMaterias() {
        initComponents();
//        cargarMaterias();
        armarCabecera();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtMaterias = new javax.swing.JTable();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabelCargaDeNotas = new javax.swing.JLabel();
        jLabelMaterias = new javax.swing.JLabel();
        jcbMaterias = new javax.swing.JComboBox<>();

        jtMaterias.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtMaterias);

        jButtonGuardar.setText("Guardar");

        jButtonCancelar.setText("Cancelar");

        jLabelCargaDeNotas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelCargaDeNotas.setText("LISTADO DE ALUMNOS POR MATERIA");

        jLabelMaterias.setText("MATERIA:");

        jcbMaterias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMateriasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonGuardar)
                        .addGap(45, 45, 45)
                        .addComponent(jButtonCancelar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabelMaterias)
                        .addGap(18, 18, 18)
                        .addComponent(jcbMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabelCargaDeNotas)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelCargaDeNotas)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMaterias)
                    .addComponent(jcbMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardar)
                    .addComponent(jButtonCancelar))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMateriasActionPerformed
        // TODO add your handling code here:
        borrarFilas();
        
    }//GEN-LAST:event_jcbMateriasActionPerformed

        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabelCargaDeNotas;
    private javax.swing.JLabel jLabelMaterias;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<Materia> jcbMaterias;
    private javax.swing.JTable jtMaterias;
    // End of variables declaration//GEN-END:variables
    
    private void cargarMaterias() {
        List<Materia> materias=mData.listarMaterias();
        for(Materia mat:materias){
            jcbMaterias.addItem(mat);
        }
        
    }
    
    private void armarCabecera() {
        ArrayList<Object> titulos=new ArrayList<>();
        titulos.add("ID");
        titulos.add("DNI");
        titulos.add("Apellido");
        titulos.add("Nombre");
        for(Object tit:titulos){
        
            modelo.addColumn(tit);
        }
        jtMaterias.setModel(modelo);
        
    }
    
    private void  llenarTabla(){
       Materia materiaSeleccionada=(Materia)jcbMaterias.getSelectedItem();
        
        
            List<Alumno> lista=inscData.obtenerAlumnosXMateria(materiaSeleccionada.getIdMateria());
            for(Alumno alu:lista){
            
                modelo.addRow(new Object[]{alu.getIdAlumno(),alu.getDni(),alu.getApellido(),alu.getNombre()});
            }
        
    }
    
    private void borrarFilas(){
    
        int filas=modelo.getRowCount()-1;
        for(int i=filas;i >=0;i--){
        
            modelo.removeRow(i);
        }
    }


}
