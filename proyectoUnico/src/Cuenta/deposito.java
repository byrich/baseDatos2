/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cuenta;


import Controlador.Cuenta_sql;
import Entidad.Cuenta;
import UI.Principal_ui;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Byrich
 */
public class deposito extends javax.swing.JPanel {
    ArrayList<Cuenta> cli;
    public Principal_ui papa;
    Cuenta_sql api;
    public String idOperador;
    /**
     * Creates new form editUsuario
     */
    public deposito() {
        initComponents();
        update();
        dpi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int key = (int)e.getKeyChar();
                if (!(key < 58 && key > 47)) {
                    dpi.setText("");
                }
            }
        });
        monto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int key = (int)e.getKeyChar();
                if (!(key < 58 && key > 47) && !(key == 46)) {
                    monto.setText("");
                }
            }
        });
        
    }

    public void update(){
    }
    
   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        dpi = new javax.swing.JTextField();
        monto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        jButton2.setText("Depositar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("No. Cuenta");

        dpi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpiActionPerformed(evt);
            }
        });

        jLabel2.setText("Monto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dpi, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jButton2)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dpi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jButton2)
                .addContainerGap(152, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String actual = dpi.getText();
        if (actual.isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingresar un No. cuenta");
        }
        else{
            int ret = api.depositar(actual,idOperador,monto.getText());
            if (ret == 1){
                JOptionPane.showMessageDialog(null, "Deposito realizado");
            }
            else if (ret == -1){
                JOptionPane.showMessageDialog(null, "La cuenta: " +actual+ " no existe");
            }
            else{
                JOptionPane.showMessageDialog(null, "Intente mas tarde");
            }
        } 
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void dpiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpiActionPerformed
        // TODO add your handling code here:
        System.out.println("");
    }//GEN-LAST:event_dpiActionPerformed

    public void cargarApi(Cuenta_sql papa){
        this.api = papa;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dpi;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField monto;
    // End of variables declaration//GEN-END:variables
}
