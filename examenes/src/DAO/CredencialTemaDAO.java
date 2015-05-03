///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package DAO;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Iterator;
//import javax.swing.JOptionPane;
//
///**
// *
// * @author Daniel
// */
//public class CredencialTemaDAO {
//     	private BD bd = new BD();
//    
//	/*
//	:::
//	Credencial_Tema
//    	tema INT(Clave foranea de la tabla tema)
//    	credencial varchar(45)//numero respectivo que se le asigna al aspirante
//    
//	:::
//    
//	Metodo que me asigna los n temas que se encuentren en la tabla tema de
//	la base de datos, los cuales son asignados a las credenciales.
//	Para efectuar dicho metodo, lo que debo de hacer es hacer el respectivo
//	query para recuperar los temas que se encuentren registrados en la bd, luego
//	debo de recorrer el resultado del query y signar el tema 2 al credencial 1, tema 2 credencial 2,
//	tema n credencial m.
//	*/
//     	public void credencialTema(ArrayList credenciales){
//    	Iterator<String> i = credenciales.iterator();
//    	Connection con = bd.conexion();
//    	try{
//        	String consulta = "SELECT * FROM Tema WHERE codigo > 1";
//        	Statement sta = con.createStatement();
//        	ResultSet rs = sta.executeQuery(consulta);
//        	while(rs.next() && i.hasNext()){
//           	asignarCredencial(rs.getInt("codigo"), Integer.parseInt(i.next()) );
//        	}
//        	
//    	}catch(Exception e){
//        	JOptionPane.showMessageDialog(null,"Problemas al momento de asignar credencial-Tema! Error: "+ e);
//    	}
//	}
//
//
//
//	public int asignarCredencial(int tema, int credencial){
//    	Connection con = bd.conexion();
//    	try{
//        	String consulta = "INSERT INTO Credencial_Tema VALUES('"+tema+"','"+credencial+"')";
//        	Statement sta = con.createStatement();
//        	int rs = sta.executeUpdate(consulta);
//        	if (rs == 1 || rs == 4){
//            	return 1;
//        	}else{
//            	return 0;
//        	}
//    	}catch(Exception ex){
//        	JOptionPane.showMessageDialog(null, "Problemas al momento de insertar datos en la tabla Credencial_Tema!. Error: "+ex);
//    	}
//    	return 0;
//	}
//}
//
//
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DTO.Tema;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class CredencialTemaDAO {
     	private BD bd = new BD();
    
	/*
	:::
	Credencial_Tema
    	tema INT(Clave foranea de la tabla tema)
    	credencial varchar(45)//numero respectivo que se le asigna al aspirante
    
	:::
    
	Metodo que me asigna los n temas que se encuentren en la tabla tema de
	la base de datos, los cuales son asignados a las credenciales.
	Para efectuar dicho metodo, lo que debo de hacer es hacer el respectivo
	query para recuperar los temas que se encuentren registrados en la bd, luego
	debo de recorrer el resultado del query y signar el tema 2 al credencial 1, tema 2 credencial 2,
	tema n credencial m.
	*/
     	public HashMap credencialTema(ArrayList credenciales){
            //Collection<Tema> temas = new ArrayList<Tema>();
            ArrayList temas = new ArrayList();
    	Connection con = bd.conexion();

    	try{
            int j =1,k=0;
        	String consulta = "SELECT * FROM Tema WHERE codigo > 0";
        	Statement sta = con.createStatement();
        	ResultSet rs = sta.executeQuery(consulta);
        	while(rs.next()){
                    Tema nuevoTema = new Tema();
                    nuevoTema.setCodigo(rs.getInt("codigo"));
                    temas.add(nuevoTema);
                    
        	}
                HashMap hm = new HashMap();
                while(j<credenciales.size()){
                    Tema aux = (Tema)temas.get(k);
                    
                   asignarCredencial(aux, (Integer)Integer.parseInt(credenciales.get(j).toString()));
                   hm.put(credenciales.get(j), aux.getCodigo()); 
                
                j++;
                k++;  
                    if(k==temas.size()){//Hay mas credenciales que temas, por lo tanto se siguen asignando los temas pero nuevamente desde el inicio de temas
                        k=0;
                    }
                }
             return hm;
    	}catch(Exception e){
        	JOptionPane.showMessageDialog(null,"Problemas al momento de asignar credencial-Tema! Error: "+ e);
    	}finally {  
    if (con != null) try { con.close(); } catch (SQLException ignore) {}  
}
        return null;
	}



	public int asignarCredencial(Tema tema, int credencial){
    	Connection con = bd.conexion();
    	try{
                
        	String consulta = "INSERT INTO Credencial_Tema VALUES('"+tema.getCodigo()+"','"+credencial+"')";
        	Statement sta = con.createStatement();
        	int rs = sta.executeUpdate(consulta);
        	if (rs == 1 || rs == 4){
            	return 1;
        	}else{
            	return 0;
        	}
    	}catch(Exception ex){
        	JOptionPane.showMessageDialog(null, "Problemas al momento de insertar datos en la tabla Credencial_Tema!. Error: "+ex);
    	}finally {  
    if (con != null) try { con.close(); } catch (SQLException ignore) {}  
}
    	return 0;
	}
       
}



