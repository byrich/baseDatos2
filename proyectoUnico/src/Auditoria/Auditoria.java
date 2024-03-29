package Auditoria;

import Controlador.conexion;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author marilu
 */
public class Auditoria extends javax.swing.JPanel {

    conexion db;
    /**
     * Creates new form Auditoria
     */
    public Auditoria() {
        initComponents();
        db = conexion.getInstance();
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "operacion", "tipoOperacion", "fechaHora", "saldoInicial", "Monto", "saldoFinal", "usuario", "cuenta"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void getData(){
        //se consultan los datos
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) this.jTable1.getModel();
        ResultSet rs = db.getTransaccion();
        try {
            int operacion = 0;
            String tipoOperacion = "";
            java.sql.Date fecha = null;
            float saldoInicial, monto, saldoFinal;
            String usuario, razonRechazo;
            int noAutorizacion, noRechazo, cuenta, doc;
            while (rs.next()) {
                operacion = rs.getInt("numero");
                tipoOperacion = rs.getString("tipo");
                fecha = rs.getDate("fecha");
                saldoInicial = rs.getFloat("saldo_inicial");
                monto = rs.getFloat("valor");
                saldoFinal = rs.getFloat("saldo_final");
                usuario = rs.getString("Operador_id_operador");
                cuenta = rs.getInt("id_cuenta");

                model.addRow(new Object[]{
                    operacion, tipoOperacion, fecha,
                    saldoInicial, monto, saldoFinal,
                    usuario, cuenta
                });
            }
        }
        catch(Exception e) {
            System.out.println("No se pudieron leer algunas filas");
        }
    }
    public void generarReporte() throws IOException {
        String currentDir = System.getProperty("user.dir");
        String rutaTemplate = currentDir + "\\ReportesTemplates\\reporteAuditoriaTemplate.html";
        System.out.println(rutaTemplate);
        /*  here it is my html template
            C:\Users\C45ASP4311F\Dropbox\Ingenieria\Bases2\Proyecto1\baseDatos2\reporteAuditoria.html
        */

        String html = "";
        ResultSet rs = db.getTransaccion();
        try {
            int operacion = 0;
            String tipoOperacion = "";
            java.sql.Date fecha = null;
            float saldoInicial, monto, saldoFinal;
            String usuario, razonRechazo;
            int noAutorizacion, noRechazo, cuenta, doc;
            while (rs.next()) {
                operacion = rs.getInt("numero");
                tipoOperacion = rs.getString("tipo");
                fecha = rs.getDate("fecha");
                saldoInicial = rs.getFloat("saldo_inicial");
                monto = rs.getFloat("valor");
                saldoFinal = rs.getFloat("saldo_final");
                usuario = rs.getString("Operador_id_operador");
                cuenta = rs.getInt("id_cuenta");

                html += "<tr>\n";
                html += "<td>" + operacion + "</td>\n";
                html += "<td>" + tipoOperacion + "</td>\n";
                html += "<td>" + fecha + "</td>\n";
                html += "<td>" + saldoInicial + "</td>\n";
                html += "<td>" + monto + "</td>\n";
                html += "<td>" + saldoFinal + "</td>\n";
                html += "<td>" + usuario + "</td>\n";
                html += "<td>" + cuenta + "</td>\n";
                html += "</tr>\n";
            }
        }
        catch(Exception e) {
            System.out.println("No se pudieron leer algunas filas");
        }
        
        File htmlTemplateFile = new File(rutaTemplate);
        String htmlString = FileUtils.readFileToString(htmlTemplateFile);
        System.out.println(htmlString);
        String title = "Reporte Auditoria";
        htmlString = htmlString.replace("$title", title);
        htmlString = htmlString.replace("$body", html);
        File newHtmlFile = new File(currentDir + "\\Reportes\\reporteAuditoria.html");
        FileUtils.writeStringToFile(newHtmlFile, htmlString);
        JOptionPane.showMessageDialog(null, "Reporte auditoria generado");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
