/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Agencias.editAgencia;
import Agencias.regAgencia;
import Auditoria.Auditoria;
import Cheques.IngresoCheque;
import Controlador.Agencia_sql;
import Controlador.Cliente_sql;
import Controlador.Cuenta_sql;
import Controlador.Operador_sql;
import Cuenta.editCuenta;
import Cuenta.regCuenta;
import Operador.editOperador;
import Operador.regOperador;
import User.darAlta;
import User.editUsuario;
import User.regUsuario;
import java.awt.Component;

/**
 *
 * @author Byrich
 */
public class Principal_ui extends javax.swing.JFrame {
    public Component actual;
    public Cliente_sql apiCliente;
    public Operador_sql apiOperador;
    public Agencia_sql apiAgencia;
    public Cuenta_sql apiCuenta;
    /**
     * Creates new form Principal_ui
     */
    public Principal_ui() {
        initComponents();
        apiCliente = new Cliente_sql();
        apiOperador = new Operador_sql();
        apiAgencia = new Agencia_sql();
        apiCuenta = new Cuenta_sql();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu12 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();

        jMenu12.setText("jMenu12");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 351, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Cliente");
        jMenu1.setEnabled(false);

        jMenu3.setText("Administrar");

        jMenuItem1.setText("Registrar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Editar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem12.setText("Dar de alta");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenu1.add(jMenu3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Cuenta");
        jMenu2.setEnabled(false);

        jMenu9.setText("Administrar");

        jMenuItem9.setText("Registrar");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem9);

        jMenuItem10.setText("editar");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem10);

        jMenu2.add(jMenu9);

        jMenu10.setText("Depositar");

        jMenuItem13.setText("cheque");
        jMenu10.add(jMenuItem13);

        jMenuItem14.setText("transferencia");
        jMenu10.add(jMenuItem14);

        jMenuItem15.setText("efectivo");
        jMenu10.add(jMenuItem15);

        jMenu2.add(jMenu10);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Operador");
        jMenu5.setEnabled(false);

        jMenu6.setText("Administrar");

        jMenuItem5.setText("Registrar");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem5);

        jMenuItem6.setText("Editar");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem6);

        jMenu5.add(jMenu6);

        jMenuBar1.add(jMenu5);

        jMenu7.setText("Agencia");
        jMenu7.setEnabled(false);

        jMenu8.setText("Administrar");

        jMenuItem7.setText("Registrar");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem7);

        jMenuItem8.setText("Editar");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem8);

        jMenu7.add(jMenu8);

        jMenuBar1.add(jMenu7);

        jMenu4.setText("Sesion");

        jMenuItem3.setText("Iniciar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuItem4.setText("Salir");
        jMenuItem4.setEnabled(false);
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuBar1.add(jMenu4);

        jMenu11.setText("Cheques");

        jMenuItem11.setText("Ingreso");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem11);

        jMenuItem16.setText("Lectura archivo");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem16);

        jMenuBar1.add(jMenu11);

        jMenu13.setText("Auditoria");

        jMenuItem17.setText("Mostrar auditoria");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem17);

        jMenuBar1.add(jMenu13);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:.
        this.remove(actual);
        this.repaint();
        regUsuario panel = new regUsuario();
        panel.cargarApi(apiCliente);
        actual = panel;
        this.add(panel);
        panel.setBounds(0,0, 606, 351);
        panel.updateUI();
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.remove(actual);
        this.repaint();
        editUsuario panel = new editUsuario();
        actual = panel;
        panel.papa = this;
        panel.cargarApi(apiCliente);
        this.add(panel);
        panel.setBounds(0,0, 606, 351);
        panel.updateUI();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        actual = this.jPanel1;
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        Logins lg = new Logins(this,true);
        lg.cargarApi(apiOperador);
        lg.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        Inicio niu = new Inicio();
        niu.setBounds(0,0, 606, 351);
        this.remove(actual);
        this.actual = niu;
        this.add(niu);
        niu.updateUI();
        this.repaint();
        this.activar();
        this.jMenu5.setEnabled(false);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        
        this.remove(actual);
        this.repaint();
        regOperador panel = new regOperador();
        actual = panel;
        this.add(panel);
        panel.cargarApi(this.apiOperador);
        panel.setBounds(0,0, 606, 351);
        panel.updateUI();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        this.remove(actual);
        this.repaint();
        editOperador panel = new editOperador();
        actual = panel;
        panel.papa = this;
        panel.cargarApi(apiOperador);
        this.add(panel);
        panel.setBounds(0,0, 606, 351);
        panel.updateUI();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        this.remove(actual);
        this.repaint();
        regAgencia panel = new regAgencia();
        actual = panel;
        this.add(panel);
        panel.cargarApi(apiAgencia);
        panel.setBounds(0,0, 606, 351);
        panel.updateUI();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        this.remove(actual);
        this.repaint();
        regCuenta panel = new regCuenta();
        actual = panel;
        panel.papa = this;
        this.add(panel);
        panel.cargarApi(apiCuenta);
        panel.setBounds(0,0, 606, 351);
        panel.updateUI();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        this.remove(actual);
        this.repaint();
        editAgencia panel = new editAgencia();
        actual = panel;
        panel.papa = this;
        this.add(panel);
        panel.cargarApi(apiAgencia);
        panel.setBounds(0,0, 606, 351);
        panel.updateUI();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        this.remove(actual);
        this.repaint();
        darAlta panel = new darAlta();
        actual = panel;
        panel.papa = this;
        panel.cargarApi(apiCliente);
        this.add(panel);
        panel.setBounds(0,0, 606, 351);
        panel.updateUI();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        this.remove(actual);
        this.repaint();
        editCuenta panel = new editCuenta();
        actual = panel;
        panel.papa = this;
        panel.cargarApi(apiCuenta);
        this.add(panel);
        panel.setBounds(0,0, 606, 351);
        panel.updateUI();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        this.remove(actual);
        this.repaint();
        IngresoCheque panel = new IngresoCheque();
        actual = panel;
        this.add(panel, java.awt.BorderLayout.CENTER);
        panel.setBounds(0,0, 606, 351);
        panel.updateUI();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        this.remove(actual);
        this.repaint();
        Auditoria panel = new Auditoria();
        actual = panel;
        this.add(panel, java.awt.BorderLayout.CENTER);
        panel.setBounds(0,0, 606, 351);
        panel.updateUI();
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        //escribir archivo
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    public void activar(){
        this.jMenu1.setEnabled(!this.jMenu1.isEnabled());
        this.jMenu2.setEnabled(!this.jMenu2.isEnabled());
        this.jMenuItem3.setEnabled(!this.jMenuItem3.isEnabled());
        this.jMenuItem4.setEnabled(!this.jMenuItem4.isEnabled());
    }
    
    public void activarAdmin(){
        activar();
        this.jMenu5.setEnabled(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal_ui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
