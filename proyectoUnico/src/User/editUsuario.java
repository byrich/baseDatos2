/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Controlador.Cliente_sql;
import Entidad.Cliente;
import UI.Principal_ui;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Byrich
 */
public class editUsuario extends javax.swing.JPanel {
    ArrayList<Cliente> cli;
    public Principal_ui papa;
    Cliente_sql api;
    /**
     * Creates new form editUsuario
     */
    public editUsuario() {
        initComponents();
        //update();
    }

    public void update(){
        Object resutl[]= api.getClientes();
        cli = (ArrayList<Cliente>) resutl[0];
        this.jTable1.setModel((DefaultTableModel) resutl[1]);
        /*
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
        }*/
    }
    
    public void cargarApi(Cliente_sql papa){
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

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

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton2)
                .addGap(28, 28, 28)
                .addComponent(jButton3)
                .addContainerGap(254, Short.MAX_VALUE))
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
            panel.cargarApi(this.api);
            papa.remove(papa.actual);
            papa.repaint();
            papa.actual = panel;
            papa.add(panel);
            panel.updateUI();
        }
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int actual = this.jTable1.getSelectedRow();
        if (actual == -1){
            update();
            JOptionPane.showMessageDialog(null, "Seleccione un registro!");
        }
        else{
            Long dpi = cli.get(actual).dpi;
            int ret = api.delCliente(dpi+"");
            if (ret == 1){
                JOptionPane.showMessageDialog(null, "Registro Eliminado!");
            }
            else if (ret == -1){
                JOptionPane.showMessageDialog(null, "El usuario: " +dpi+ " ya no existe");
            }
            else{
                JOptionPane.showMessageDialog(null, "Intente mas tarde");
            }
            update();
        }

    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
