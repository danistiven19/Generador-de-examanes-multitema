/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

/**
 *
 * @author Luisa
 */

import DAO.CredencialTemaDAO;
import DAO.rutaDAO;
import DTO.Credencial_Tema;
import DTO.Tema;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author giftsam
 */
public class CtrlCredencial {
    private ArrayList credenciales = new ArrayList();
    private CredencialTemaDAO ctDAO = new CredencialTemaDAO();
    rutaDAO ruta=new rutaDAO();
    String fileNameWrite = ruta.getRuta();
    /**
     * This method is used to read the data's from an excel file.
     *
     * @param fileName - Name of the excel file.
     */
    public ArrayList readExcelFile(String fileName) {

        /**
         * Create a new instance for cellDataList
         */
        List cellDataList = new ArrayList();
        try {
            /**
             * Create a new instance for FileInputStream class
             */
            FileInputStream fileInputStream = new FileInputStream(fileName);
            /**
             * Create a new instance for POIFSFileSystem class
             */

      //POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
            /*
            
             * Create a new instance for HSSFWorkBook Class
             */
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet hssfSheet = workbook.getSheet("Pregrado - 2013-02");

            /**
             * Iterate the rows and cells of the spreadsheet to get all the
             * datas.
             */
          Iterator rowIterator = hssfSheet.rowIterator();
            while (rowIterator.hasNext()) {

                HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();
                while (iterator.hasNext()) {

                    HSSFCell hssfCell = (HSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
//                   
                }
                cellDataList.add(cellTempList);
              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * Call the printToConsole method to print the cell data in the console.
         */
        return credencialesToArray(cellDataList);
       
    }

    /**
     * This method is used to print the cell data to the console.
     *
     * @param cellDataList - List of the data's in the spreadsheet.
     */
    private ArrayList credencialesToArray(List cellDataList) {

        for (int i = 0; i < cellDataList.size(); i++) {
            List cellTempList = (List) cellDataList.get(i);
            // for (int j = 0; j < cellTempList.size(); j++) {
            // Colocar el numero de la columna
            HSSFCell hssfCell = (HSSFCell) cellTempList.get(3);
            String stringCellValue = hssfCell.toString();
            credenciales.add(stringCellValue);
        }
        Iterator<String> it = credenciales.iterator();
        while (it.hasNext()) {
            String elemento = it.next();
           
        }
        
        return credenciales;
    }
public File writeExcelFile(String nombreArchivo) throws IOException{
        ArrayList credencialTemaAr=new ArrayList();
        credencialTemaAr=ctDAO.getCredencialTema();
        Iterator itcredencialTema=credencialTemaAr.iterator();
        Credencial_Tema credencialTema=new Credencial_Tema();
        String credencial="";
        Tema temaT=new Tema();
        int tema=0;
           
        /*La ruta donde se creará el archivo*/
      //  String rutaArchivo = System.getProperty(fileNameWrite)+"/credencialTema.xls";
        String rutaArchivo = fileNameWrite+"/"+nombreArchivo+".xls";
        /*Se crea el objeto de tipo File con la ruta del archivo*/
        File archivoXLS = new File(rutaArchivo);
        /*Si el archivo existe se elimina*/
        if(archivoXLS.exists()) archivoXLS.delete();
        /*Se crea el archivo*/
        archivoXLS.createNewFile();
        
        /*Se crea el libro de excel usando el objeto de tipo Workbook*/
        Workbook libro = new HSSFWorkbook();
        /*Se inicializa el flujo de datos con el archivo xls*/
        FileOutputStream archivo = new FileOutputStream(archivoXLS);
        
        /*Utilizamos la clase Sheet para crear una nueva hoja de trabajo dentro del libro que creamos anteriormente*/
        Sheet hoja = libro.createSheet("TemaXcredencial"+nombreArchivo);
        int f=0;
        while(itcredencialTema.hasNext()){
      
           
        /*Hacemos un ciclo para inicializar los valores de 10 filas de celdas*/
           
                /*La clase Row nos permitirá crear las filas*/
        Row fila = hoja.createRow(f);
        
         for(int c=0;c<2;c++){
                /*Creamos la celda a partir de la fila actual*/
                Cell celda = fila.createCell(c);
                
                /*Si la fila es la número 0, estableceremos los encabezados*/
                if(f==0 && c==0){
                    celda.setCellValue("Tema");
                }else if(f==0 && c==1){
                    celda.setCellValue("Credencial");
                }else if(f!=0 && c==1){
                     /*Si no es la primera fila establecemos un valor*/
                     celda.setCellValue(credencial);
                }else{
                    /*Si no es la primera fila establecemos un valor*/
                    celda.setCellValue(tema);
                }
            }
       
        f=f+1;
        credencialTema=(Credencial_Tema) itcredencialTema.next();
        credencial=credencialTema.getCredencial();
        temaT=credencialTema.getTema();
        tema=temaT.getCodigo();
            
        }
        
        /*Escribimos en el libro*/
        libro.write(archivo);
        /*Cerramos el flujo de datos*/
        archivo.close();
        /*Y abrimos el archivo con la clase Desktop*/
        
        return archivoXLS;
    }
    
    public HashMap guardarCredenciales(ArrayList cred) {
        return ctDAO.credencialTema(cred);
        

    }
}
