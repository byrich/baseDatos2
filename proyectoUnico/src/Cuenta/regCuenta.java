/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cuenta;

import User.modUsuario;
import Controlador.conexion;
import Entidad.Cliente;
import UI.Principal_ui;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Byrich
 */
public class regCuenta extends javax.swing.JPanel {
    ArrayList<Cliente> cli;
    public Principal_ui papa;
    private boolean existe;
    /**
     * Creates new form editUsuario
     */
    public regCuenta() {
        initComponents();
        existe = false;
        this.jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String actual = jTextField1.getText();
                if (actual.isEmpty()){
                    System.out.println("na");
                    update();
                }
                else{
                    System.out.println("sd");
                    ArrayList<Cliente> tempo = new ArrayList();
                    int x = 0;
                    int fin = cli.size();
                    while (x < fin){
                        if (actual.equals(cli.get(x).dpi+"")){
                            System.out.println("iguales");
                            tempo.add(cli.get(x));
                        }
                        x++;
                    }
                    update2(tempo);
                }
            }
        });
        update();
    }

    public void update(){
        cli = new ArrayList();
        conexion global = conexion.getInstance();
        ResultSet rs = global.getClientes();
        String[] columnNames = {"Nombre","fecha nacimiento"};
        Object[][] data={};
        DefaultTableModel dtm= new DefaultTableModel(data, columnNames);
        this.jTable1.setModel(dtm);
        try {
            while(rs.next()){
                Cliente clie = new Cliente(rs.getLong(1),rs.getString(2),rs.getBinaryStream(3),rs.getBinaryStream(4),rs.getDate(5).toString());
                cli.add(clie);
                System.out.println(clie);
            }
            int x = 0;
            int fin = cli.size();
            Object[][] datos = new Object[cli.size()][2];
            while (x < fin){
                Object temp[] = {cli.get(x).nombre,cli.get(x).fec_nac};
                datos[x]= temp;
                x++;
            }
            dtm= new DefaultTableModel(datos, columnNames);
            this.jTable1.setModel(dtm);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void update2(ArrayList<Cliente> cli){
        conexion global = conexion.getInstance();
        ResultSet rs = global.getClientes();
        String[] columnNames = {"Nombre","fecha nacimiento"};
        Object[][] data={};
        DefaultTableModel dtm= new DefaultTableModel(data, columnNames);
        //this.jTable1.setModel(dtm);
        try {
            while(rs.next()){
                Cliente clie = new Cliente(rs.getLong(1),rs.getString(2),rs.getBinaryStream(3),rs.getBinaryStream(4),rs.getDate(5).toString());
                cli.add(clie);
            }
            int x = 0;
            int fin = cli.size();
            Object[][] datos = new Object[cli.size()][2];
            while (x < fin){
                Object temp[] = {cli.get(x).nombre,cli.get(x).fec_nac};
                datos[x]= temp;
                x++;
            }
            dtm= new DefaultTableModel(datos, columnNames);
            this.jTable1.setModel(dtm);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Crear Cuenta");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("dpi");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addContainerGap(209, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        //System.out.println("sdf");
        //update();

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int actual = this.jTable1.getSelectedRow();
        if (actual == -1){
            update();
            JOptionPane.showMessageDialog(null, "Seleccione un registro!");
        }
        else{
            modUsuario panel = new modUsuario();
            panel.setBounds(0,0, 606, 351);
            //panel.Cargar(cli.get(actual).dpi+"", cli.get(actual).nombre, cli.get(actual).fec_nac);
            panel.Cargar(cli.get(actual));
            papa.remove(papa.actual);
            papa.repaint();
            papa.actual = panel;
            papa.add(panel);
            panel.updateUI();
        }
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        System.out.println("");
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String actual = jTextField1.getText();
        if (actual.isEmpty()){
            existe = false;
            update();
        }
        else{
            ArrayList<Cliente> tempo = new ArrayList();
            int x = 0;
            int fin = cli.size();
            while (x < fin){
                System.out.println(cli.get(x).dpi);
                if (actual.equals(cli.get(x).dpi+"")){
                    System.out.println("iguales");
                    tempo.add(cli.get(x));
                }
                x++;
            }
            update2(tempo);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}