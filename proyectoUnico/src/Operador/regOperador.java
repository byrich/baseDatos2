/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operador;

import Controlador.Operador_sql;
import Controlador.conexion;
import Entidad.Agencia;
import Entidad.Rol;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Byrich
 */
public class regOperador extends javax.swing.JPanel {
    Operador_sql api;
    
    /**
     * Creates new form NewJPanel
     */
    public regOperador() {
        initComponents();
        
    }
    
    public void update(){
        ResultSet rs = api.getAgencias();
        try {
            while(rs.next()){
                Agencia ag = new Agencia(rs.getInt(1),rs.getString(2),rs.getInt(3));
                this.jComboBox1.addItem(ag);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        rs = api.getRoles();
        try {
            while(rs.next()){
                Rol ag = new Rol(rs.getInt(1),rs.getString(2),rs.getInt(3));
                this.jComboBox2.addItem(ag);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void cargarApi(Operador_sql papa){
        this.api = papa;
        update();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        jLabel2.setText("Nombre");

        jLabel3.setText("Contaseña");

        jLabel4.setText("REGISTRO DE OPERADOR");

        submit.setText("REGISTRAR");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(204, 0, 0));

        jLabel6.setText("Agencia");

        jLabel7.setText("Rol");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(pass, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(submit)
                        .addGap(71, 71, 71))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(submit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(266, 266, 266))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
        conexion global = conexion.getInstance();
        if (nombre.getText().isEmpty() || pass.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ningun campo puede estar vacio!");
        } 
        else
        {
            String nombrE = nombre.getText();
            String pasS = pass.getText();
            Agencia tmp = (Agencia) this.jComboBox1.getSelectedItem();
            Rol tmp2 = (Rol) this.jComboBox2.getSelectedItem();
            if (global.evaluarCadena(nombrE) && global.evaluarCadena(pasS)){
                int ret = api.agregarOperador(nombrE, pasS, (int) tmp.id, 1, tmp2.id);
                if (ret == 1){
                    this.nombre.setText("");
                    this.pass.setText("");
                    JOptionPane.showMessageDialog(null, "Registro Creado!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Intente mas tarde");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Algunos campos contienen caracteres no permitidos");
            }
        }
    }//GEN-LAST:event_submitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Agencia> jComboBox1;
    private javax.swing.JComboBox<Rol> jComboBox2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField nombre;
    private javax.swing.JPasswordField pass;
    private javax.swing.JButton submit;
    // End of variables declaration//GEN-END:variables
}
