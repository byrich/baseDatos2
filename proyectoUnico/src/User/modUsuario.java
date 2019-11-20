/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Controlador.conexion;
import Entidad.Cliente;
import UI.ImageViewer;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Byrich
 */
public class modUsuario extends javax.swing.JPanel {
    
    public Cliente este;
    /**
     * Creates new form NewJPanel
     */
    public modUsuario() {
        initComponents();
        dpi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int key = (int)e.getKeyChar();
                if (!(key < 58 && key > 47)) {
                    dpi.setText("");
                    jLabel5.setText("* El DPI solo admite digitos");
                }
                else{
                    jLabel5.setText("");
                }
            }
        });
        dia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int key = (int)e.getKeyChar();
                if (!(key < 58 && key > 47)) {
                    dia.setText("");
                    jLabel5.setText("* El DIA solo admite digitos");
                }
                else{
                    int day = Integer.parseInt(dia.getText());
                    if (day > 31){
                        dia.setText("");
                        jLabel5.setText("* DIA rango permitido: 1 - 31");
                    }
                    else{
                        
                        jLabel5.setText("");
                    }
                    
                }
            }
        });
        mes.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int key = (int)e.getKeyChar();
                if (!(key < 58 && key > 47)) {
                    mes.setText("");
                    jLabel5.setText("* El MES solo admite digitos");
                }
                else{
                    int day = Integer.parseInt(mes.getText());
                    if (day > 12){
                        mes.setText("");
                        jLabel5.setText("* MES rango permitido: 1 - 12");
                    }
                    else{
                        jLabel5.setText("");
                    }
                    
                }
            }
        });
        anio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int key = (int)e.getKeyChar();
                if (!(key < 58 && key > 47)) {
                    anio.setText("");
                    jLabel5.setText("* El AÑO solo admite digitos");
                }
                else{
                    int day = Integer.parseInt(anio.getText());
                    if (day > 2011){
                        anio.setText("");
                        jLabel5.setText("* AÑO rango permitido:  1900 - 2011");
                    }
                    else{
                        jLabel5.setText("");
                    }
                    
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dpi = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        mes = new javax.swing.JTextField();
        anio = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        dpi.setEnabled(false);

        jLabel1.setText("DPI");

        jLabel2.setText("Nombre");

        jLabel3.setText("fecha de nacimiento (dia/mes/año)");

        jLabel4.setText("EDICION DE USUARIO");

        submit.setText("MODIFICAR");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(204, 0, 0));

        jButton1.setText("ver foto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ver firma");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(dpi, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(anio, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                        .addComponent(submit)
                        .addGap(63, 63, 63))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dpi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submit)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(116, 116, 116))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
        conexion global = conexion.getInstance();
        if (dpi.getText().isEmpty() || nombre.getText().isEmpty() || dia.getText().isEmpty() || mes.getText().isEmpty()|| anio.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ningun campo puede estar vacio!");
        }
        else
        {
            String dpI = dpi.getText();
            String nombrE = nombre.getText();
            String datE = dia.getText()+"/"+mes.getText()+"/"+anio.getText();
            if (global.evaluarCadena(nombrE) && global.evaluarFecha(datE)){
                ResultSet rs = global.editCliente(dpI, nombrE, datE);
                try {
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null, "Registro modificado!");
                        this.dpi.setText("");
                        this.nombre.setText("");
                        this.dia.setText("");
                        this.mes.setText("");
                        this.anio.setText("");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "El registro ya no esta disponible!");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Intente mas tarde");
                }
            }
        }
    }//GEN-LAST:event_submitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (este.foto != null){
            InputStream input = este.foto; 
            File theFile = new File("fotoTemporal.jpg");
            FileOutputStream output;
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                output = new FileOutputStream(theFile);
                 while (input.read(buffer) > 0) {
                    output.write(buffer);
                    baos.write(buffer);
                }
                este.foto = new ByteArrayInputStream(baos.toByteArray()); 
                input.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(modUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(modUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageViewer iv = new ImageViewer();
            iv.setVisible(true);
            iv.ola("fotoTemporal.jpg");
        }
        else{
            JOptionPane.showMessageDialog(null, "Usuario no cuenta con foto");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (este.firma != null){
            System.out.println(este.firma);
            InputStream input = este.firma; 
            File theFile = new File("fotoTemporal.jpg");
            FileOutputStream output;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            try {
                output = new FileOutputStream(theFile);
                 while (input.read(buffer) > 0) {
                    output.write(buffer);
                    baos.write(buffer);
                }
                este.firma = new ByteArrayInputStream(baos.toByteArray()); 
                input.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(modUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(modUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageViewer iv = new ImageViewer();
            iv.setVisible(true);
            iv.ola("fotoTemporal.jpg");
        }
        else{
            JOptionPane.showMessageDialog(null, "Usuario no cuenta con firma");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public void Cargar(Cliente actual){
        este = actual;
        this.dpi.setText(actual.dpi+"");
        this.nombre.setText(actual.nombre);
        String txt[]= actual.fec_nac.split("-");
        this.dia.setText(txt[2]);
        this.mes.setText(txt[1]);
        this.anio.setText(txt[0]);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anio;
    private javax.swing.JTextField dia;
    private javax.swing.JTextField dpi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField mes;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton submit;
    // End of variables declaration//GEN-END:variables
}