/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DTO.Area;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class areaDAO {
    
    private BD bd;
    private Connection con;
    
    public areaDAO(){
        bd = new BD();
          con = bd.conexion();
    }
    public Collection<Area> listarAreas(){
     ArrayList a = new ArrayList();
    
     try{
           if(con.isClosed()){
                con = bd.conexion();
            }
         String consulta = "SELECT * FROM Area";
         Statement sta = con.createStatement();
         ResultSet rs = sta.executeQuery(consulta);
         
         while(rs.next()){
             Area area = new Area();
             area.setCodigo(rs.getInt("codigo"));
             area.setNombre(rs.getString("nombre"));
             a.add(area);
         }
         return a;
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "Problemas cargando la lista de areas!. Error: "+ex);
     }finally {  
    if (con != null) try { con.close(); } catch (SQLException ignore) {}  
} 
     
     return null;
    }
}
