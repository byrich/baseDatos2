package Cheques;
import Controlador.conexion;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author marilu
 */
public class EscribeLote {
    conexion db;
    
    public EscribeLote(){
        db = conexion.getInstance();
    }
    
    public void escribirLote() throws IOException, SQLException {
        //Formato del nombre de archivo: "monto_documentos_bancoViene_idLote"
        String[] lote = db.getLote();
        String nameFile = lote[0] + "_" + lote[1] + "_" + lote[2] + "_" + lote[3];
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(nameFile));
            writer.write(lote[4]);
            writer.close();
        }
        catch(Exception e){
            System.out.println("En algo la cague en EscribirLote");
        }
    }
}