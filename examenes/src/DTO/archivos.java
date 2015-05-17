/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import Control.latex;
import DAO.rutaDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class archivos {
     public void insertarTxt(File arc, String cont){
        PrintWriter a=null;
        try {
            a = new PrintWriter(arc);
           
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error escribiendo en el archivo ! Error: "+ex);
            return;
        }
        a.println(cont);
        a.close();
        
    }
    public File crearTxt(String url, String arch){
        File arc = new File(url,arch);
        return(arc);
    }
    private rutaDAO url = new rutaDAO();
    public int crearBAT(String ruta, String nombre){
        File bat = new File("exec.bat");
        PrintWriter a=null;
        try {
            a = new PrintWriter(bat);
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(latex.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        String comando =url.getUnidad()+" \n cd "+ruta+" \n  pdflatex "+nombre+" \n exit";
        a.print(comando);
        a.close();
        return 1;
        
    }
    
    public String leerTxt(File arc) throws IOException{
        String lec="";
        try {
            FileReader read = new FileReader(arc);
            BufferedReader br = new BufferedReader(read);
            String aux = "";
            while(true){
                aux = br.readLine();
                if (aux != null){
                    lec= lec+aux+"\n";
                }else{
                    break;
                }   
            }
            br.close();
            read.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error leyendo el archivo ! Error: "+ex);
        }
        
        return(lec);
    }
      public int ejecutarBAT(String ruta, String nom) throws InterruptedException, IOException{
       //Ejecutamos el .BAT y verificamos si hay errores
                     // NO crear carpeta cuando hay error en el cmdq
             try {
                  Process q = Runtime.getRuntime().exec("cmd /c start /wait "+ "exec.bat");
                  boolean band = false;
                  q.waitFor();
                  File archiv = new File(ruta+"\\"+nom+".log");
                  String hilera = leerTxt(archiv);
                  int i = hilera.length();
                  for (int j =0; j<i; j++){
                     if (hilera.charAt(j) == '?'){
                        JOptionPane.showMessageDialog(null, "Error");
                       band= true;
                      }
                  }
                  if (!band){
                      if(!nom.equals("Opcion")){
                        Process q1 = Runtime.getRuntime().exec("cmd /c "+url.getUnidad()+" && cd "+ruta+" && start "+nom+".pdf \n exit");
                      }
                      //Todo se ejecutó perfectamente, entonces realizamos el registro en la base de datos
                      return 1;
                  }else{
                      Process q1 = Runtime.getRuntime().exec("cmd /c start /b /wait \n RD /S /Q "+ruta+" \n exit");
                       /* int k=Integer.parseInt(en.getCodigo());
                        k--;
                        n=Integer.toString(k);*/
                        return(0);
                  }
                }catch(IOException ex){
                    Process q1 = Runtime.getRuntime().exec("cmd /c start /b /wait \n RD /S /Q "+ruta+" \n exit");
                   /* int k=Integer.parseInt(n);
                    k--;
                    n= Integer.toString(k);*/
                    return(0);
                }  
     }
    
    
    
}
