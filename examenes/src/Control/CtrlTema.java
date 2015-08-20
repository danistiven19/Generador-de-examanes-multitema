/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import DAO.preguntaTemaDAO;
import DAO.randomDAO;
import DAO.rutaDAO;
import DAO.temaDAO;
import DTO.Credencial_Tema;
import DTO.Pregunta_Tema;
import DTO.Tema;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Daniel
 */
public class CtrlTema {
    rutaDAO url = new rutaDAO();
    private preguntaTemaDAO ptDAO = new preguntaTemaDAO();
    private temaDAO temaDAO = new temaDAO();
    private latex lt = new latex();
    randomDAO rDAO = new randomDAO();
    rutaDAO ruta=new rutaDAO();
      String fileNameWrite = ruta.getRuta();
    private Integer year;
    private Integer semestre;
    private String jornada;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }
    
    public Tema cargarTema(int t){   
        Tema tt = new Tema();
        tt.setCodigo(t);
        return temaDAO.cargarTema(tt);
        
    }
    public Collection<Tema> listarTemas(){
        return temaDAO.listarTemas();
    }
    
    public void ingresarRespuesta(Tema t){
        temaDAO.ingresarRespuesta(t);
    }
    public void desplegarRespuestas(Tema maestro){
        temaDAO.desplegarRespuestas(maestro);
    }
     public void crearMaestro(Integer year, Integer semestre, String Jornada) throws IOException{
        Tema tema=new Tema();
           Calendar fecha = new GregorianCalendar();
        java.util.Date ju = fecha.getTime();
        java.sql.Date  date = new java.sql.Date(ju.getTime());
        tema.setFechaCreacion(date);
        tema.setCodigo(0);
        tema.setJornada(Jornada);
        temaDAO tdao = new temaDAO();
        this.reiniciarExamen();
        /*if(tdao.ingresarTema(tema) == 0){
            if(this.reiniciarExamen() == 1){
                this.reiniciarExamen();
                tdao.ingresarTema(tema);
            }
        }*/
        tdao.ingresarTema(tema);
        lt.nuevoExamen(tema, this.getYear(), this.getSemestre(), this.getJornada());
    }

     public void imprimir(Tema t ) throws IOException{
         lt.nuevoTema(t, 2014, 1, "Mañana");
        // lt.nuevoTema(t, this.getYear(), this.getSemestre(), this.getJornada());
     }
     
     public int randomPreguntas() throws SQLException{
         return rDAO.randompreguntas();
     }
     
     public int reiniciarExamen() throws IOException{
         temaDAO temadao = new temaDAO();
         preguntaTemaDAO ptdao = new preguntaTemaDAO();
         if(ptdao.borrarPreguntaTemas() == 1){
             Collection<Tema> temas = listarTemas();
             Iterator it = temas.iterator();
             while(it.hasNext()){
                 Tema tema = (Tema) it.next();
                 String ruta = url.getRuta() + "\\examen" + tema.getCodigo();
                 File f = new File(ruta);
                 if(f.exists()){
                     Runtime.getRuntime().exec("cmd /c "+url.getUnidad()+" && cd "+url.getRuta()+" && start /b /wait \n RD /S /Q examen" + tema.getCodigo() + " \n exit");
                 }
             }
             
             temadao.borrarTemas();
             return 1;
         }else{
             return 0;
         }
     }
     
     
    public File writeExcelFile(String nombreArchivo) throws IOException {
        Collection preguntaTemaArray = new ArrayList<Pregunta_Tema>();
        Tema tema = new Tema();

        /*La ruta donde se creará el archivo*/
        String rutaArchivo = fileNameWrite + "/" + nombreArchivo + ".xls";
        /*Se crea el objeto de tipo File con la ruta del archivo*/
        File archivoXLS = new File(rutaArchivo);
        /*Si el archivo existe se elimina*/
        if (archivoXLS.exists()) {
            archivoXLS.delete();
        }
        /*Se crea el archivo*/
        archivoXLS.createNewFile();

        /*Se crea el libro de excel usando el objeto de tipo Workbook*/
        Workbook libro = new HSSFWorkbook();
        /*Se inicializa el flujo de datos con el archivo xls*/
        FileOutputStream archivo = new FileOutputStream(archivoXLS);

        //Ciclo para los temas
        int j = 0;
        while (j <= 10) {
            tema = cargarTema(j);
            preguntaTemaArray = tema.getPreguntas();
            Iterator itPreguntaTemaArray = preguntaTemaArray.iterator();
            int pregunta = 0;
            String respuesta = "";
            /*Utilizamos la clase Sheet para crear una nueva hoja de trabajo dentro del libro que creamos anteriormente*/
            Sheet hoja = libro.createSheet("Tema " + j);
            //Ciclo para preguntaTema
            int f = 0;
            int a = preguntaTemaArray.size() + 1;
            while (f < a) {
                //  while (itPreguntaTemaArray.hasNext()) {

                /*Hacemos un ciclo para inicializar los valores de 10 filas de celdas*/
                /*La clase Row nos permitirá crear las filas*/
                Row fila = hoja.createRow(f);

                for (int c = 0; c < 2; c++) {
                    /*Creamos la celda a partir de la fila actual*/
                    Cell celda = fila.createCell(c);

                    /*Si la fila es la número 0, estableceremos los encabezados*/
                    if (f == 0 && c == 0) {
                        celda.setCellValue("Pregunta");
                    } else if (f == 0 && c == 1) {
                        celda.setCellValue("Respuesta");
                    } else if (f != 0 && c == 1) {
                        /*Si no es la primera fila establecemos un valor*/
                        celda.setCellValue(respuesta);
                    } else {
                        /*Si no es la primera fila establecemos un valor*/
                        celda.setCellValue(pregunta);
                    }
                }

                f = f + 1;
                if (f < a) {
                    Pregunta_Tema pt = (Pregunta_Tema) itPreguntaTemaArray.next();

                    pregunta = pt.getNroPregunta();

                    int rta = pt.getRespuesta();
                    if (rta == 1) {
                        respuesta = "A";
                    } else if (rta == 2) {
                        respuesta = "B";
                    } else if (rta == 3) {
                        respuesta = "C";
                    } else if (rta == 4) {
                        respuesta = "D";
                    }
                }

            }
            j++;
        }
        /*Escribimos en el libro*/
        libro.write(archivo);
        /*Cerramos el flujo de datos*/
        archivo.close();
        /*Y abrimos el archivo con la clase Desktop*/

        return archivoXLS;
    }

}
