package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author lis
 */
public class combinacionDAO {
     private BD bd;
     private Connection con;
     
     public combinacionDAO(){
          bd= new BD();
          con = bd.conexion();
     }
     
        public DTO.combinacion cargarCombinacion(DTO.combinacion p){
     
        try{
              if(con.isClosed()){
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM combinacionOpcion WHERE idcombinacion = '"+p.getCodigo()+"'"; // cambiar q.getCodigo() por el combobox de la pantalla seleccionarEnunciado
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if(rs.next()){
                p.setA(rs.getInt("A"));
                p.setB(rs.getInt("B"));
                p.setC(rs.getInt("C"));
                p.setD(rs.getInt("D"));
                
                return p;
            }
       }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Problemas en la consulta de la combinacion! Error: "+ ex);
        }finally {  
    if (con != null) try { con.close(); } catch (SQLException ignore) {}  
} 
        return null;
    }
    
}
