/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.combinacionDAO;
import DAO.enunciadoDAO;
import DAO.opcionesDAO;
import DAO.preguntaDAO;
import DAO.preguntaTemaDAO;
import DAO.rutaDAO;
import DAO.temaDAO;
import DTO.Enunciado;
import DTO.Opcion;
import DTO.Pregunta;
import DTO.Pregunta_Tema;
import DTO.Tema;
import DTO.archivos;
import DTO.combinacion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class latex {

    private String codInicial;
    private String codFinal;
    private archivos adminArch = new archivos();
    private preguntaDAO pDAO = new preguntaDAO();

    public int crearEnunciado(Enunciado en) throws IOException, InterruptedException {
        //Para crear un enunciado, primero obtiene la ruta donde se está almacenando la información LaTeX
        rutaDAO url = new rutaDAO();
        //Creamos la carpeta que contendrá toda la información del enunciado a  crear

        String nombre = "Enunciado" + en.getCodigo();
        String ruta = url.getRuta() + "\\" + nombre;
        Process p = Runtime.getRuntime().exec("cmd /c " + "md " + ruta + "\\" + nombre);
        en.setUrl(url.getRutaSQL() + "\\\\" + nombre);

        Thread.sleep(500);
        /*Creamos el archivo que contendrá los paquetes e insertamos los paquetes requeridos
         File arc=adminArch.crearTxt(ruta,"packages.txt");
         adminArch.insertarTxt(arc, en.getPaquetes());
         //Creamos el archivo que contendrá el enunciado e insertamos los paquetes requeridos
         arc = adminArch.crearTxt(ruta,"Enunciado.txt");
         adminArch.insertarTxt(arc,en.getEnunciado());*/

        //Definimos el codigo incial y final de LaTex
        String paq = "";
        String desc = "";
        if (en.getPaquetes() != null) {
            paq = en.getPaquetes();
        }
        if (en.getEnunciado() != null) {
            desc = en.getEnunciado();
        }
        codInicial = "\\documentclass[a4paper,12pt]{article}"
                + "\\usepackage[spanish]{babel}"
                + "\\usepackage{times}"
                + "\n" + paq
                + "\n"
                + "\\title{Vista preliminar de Enunciado}"
                + "\n"
                + "\\usepackage{graphicx}"
                + "\n"
                + "\\begin{document}"
                + "\\twocolumn \n"
                + "\n"
                + "\\maketitle"
                + "\n";
        codFinal = "\\end{document}";
        //Unimos todas las partes para crear el documento completo de LaTeX para el enunciado
        String codigo = codInicial + "\n" + desc + "\n" + codFinal;
        //Creamos el archivo .Tex con el codigo creado anteriormente en la ruta especificada
        String nom = "Enunciado";
        String ext = ".tex";
        File archivo = new File(ruta, nom + ext);
        PrintWriter a = null;
        try {
            a = new PrintWriter(archivo);

        } catch (IOException ex) {
            Logger.getLogger(latex.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.println(codigo);
        a.close();
        //Creamos el archivo .BAT para compilar LaTeX
        adminArch.crearBAT(ruta, nom + ext);
        //Ejecutamos el .BAT y verificamos si hay errores
        // NO crear carpeta cuando hay error en el cmdq
        try {
            Process q = Runtime.getRuntime().exec("cmd /c start /wait " + "exec.bat");
            boolean band = false;
            q.waitFor();
            File archiv = new File(ruta + "\\" + nom + ".log");
            String hilera = adminArch.leerTxt(archiv);
            int i = hilera.length();
            for (int j = 0; j < i; j++) {
                if (hilera.charAt(j) == '?') {
                    JOptionPane.showMessageDialog(null, "Error");
                    band = true;
                }
            }
            if (!band) {
                Process q1 = Runtime.getRuntime().exec("cmd /c start " + ruta + "\\" + nom + ".pdf \n exit");
                //Todo se ejecutó perfectamente, entonces realizamos el registro en la base de datos
                return 1;

            } else {
                Process q1 = Runtime.getRuntime().exec("cmd /c start /b /wait \n RD /S /Q " + ruta + " \n exit");
                /* int k=Integer.parseInt(en.getCodigo());
                 k--;
                 n=Integer.toString(k);*/
                return (0);
            }
        } catch (IOException ex) {
            Process q1 = Runtime.getRuntime().exec("cmd /c start /b /wait \n RD /S /Q " + ruta + " \n exit");
            /* int k=Integer.parseInt(n);
             k--;
             n= Integer.toString(k);*/
            return (0);
        }
    }

    public void borrarEnunciado(Enunciado en) throws IOException {
        enunciadoDAO enDAO = new enunciadoDAO();
        enDAO.cargarEnunciado(en);
        Process q1 = Runtime.getRuntime().exec("cmd /c start /b /wait \n RD /S /Q " + en.getUrl() + " \n exit");
        if (enDAO.borrarEnunciado(en.getCodigo()) == 1) {
            return;
        }
    }

    /*
     public int crearOpcion(Opcion op) throws IOException, InterruptedException{
     //Para crear un enunciado, primero obtiene la ruta donde se está almacenando la información LaTeX
     rutaDAO url = new rutaDAO();
     //Creamos la carpeta que contendrá toda la información del enunciado a  crear
     String nombre = "Opcion"+op.getCodigo();
     //             String ruta = pAux.getUrl()+"\\"+nombre;
     String ruta = url.getRuta()+"\\"+nombre;
     Process p1 = Runtime.getRuntime().exec("cmd /c "+"md "+ruta+"\\"+nombre);
     op.setUrl(url.getRutaSQL()+"\\\\"+nombre);
     Pregunta pAux= new Pregunta();
     pAux.setCodigo( op.getPregunta());
     preguntaDAO pdao=new preguntaDAO();
     pdao.cargarPregunta(pAux);
           
           
            
     Thread.sleep(500);
        
    
     //Definimos el codigo incial y final de LaTex
     codInicial="\\documentclass[a4paper,12pt]{article}"+
     "\\usepackage[spanish]{babel}"+
     "\\usepackage{times}"+
     "\n"+
     "\\title{Vista preliminar de Enunciado}"+
     "\n"+
     "\\usepackage{graphicx}"+
     "\n"+
     "\\begin{document}"+
     "\\twocolumn \n"+  
     "\n" +
     "\\maketitle"+
     "\n";
     codFinal=   "\\end{document}";
     //Unimos todas las partes para crear el documento completo de LaTeX para el enunciado
     String codigo = codInicial+"\n"+op.getDescripcionOpcion()+"\n \\newline \n"+codFinal;
     //Creamos el archivo .Tex con el codigo creado anteriormente en la ruta especificada
     String nom="Opcion";
     String ext=".tex";
     File archivo = new File(ruta,nom+ext);
     PrintWriter a = null;
     try {
     a = new PrintWriter(archivo);

     } catch (IOException ex) {
     Logger.getLogger(latex.class.getName()).log(Level.SEVERE, null, ex);
     }
     a.println(codigo);
     a.close();
     //Creamos el archivo .BAT para compilar LaTeX
     adminArch.crearBAT(ruta, nom+ext);
     return adminArch.ejecutarBAT(ruta,nom);
                    
           
     }
     */
    public int crearOpcion(Opcion op) throws IOException, InterruptedException {
        //Para crear un enunciado, primero obtiene la ruta donde se está almacenando la información LaTeX
        rutaDAO url = new rutaDAO();
        //Creamos la carpeta que contendrá toda la información del enunciado a  crear
        String nombre = "Opcion" + op.getCodigo();
//             String ruta = pAux.getUrl()+"\\"+nombre;
        Pregunta pAux = new Pregunta();
        pAux.setCodigo(op.getPregunta().getCodigo());
        preguntaDAO pdao = new preguntaDAO();
        pdao.cargarInformacionPregunta(pAux);
        String ruta = pAux.getUrl() + "\\" + nombre;
        Process p1 = Runtime.getRuntime().exec("cmd /c " + "md " + ruta + "\\" + nombre);
        op.setUrl(url.getRutaSQL() + "\\\\" + nombre);

        Thread.sleep(500);

        /*Creamos el archivo que contendrá el enunciado e insertamos los paquetes requeridos
         File arc = adminArch.crearTxt(ruta,"opcion.txt");
         adminArch.insertarTxt(arc,op.getDescripcionOpcion());
         */
        //Definimos el codigo incial y final de LaTex
        codInicial = "\\documentclass[a4paper,12pt]{article}"
                + "\\usepackage[spanish]{babel}"
                + "\\usepackage{times}"
                + "\n"
                + "\\title{Vista preliminar de Enunciado}"
                + "\n"
                + "\\usepackage{graphicx}"
                + "\n"
                + "\\begin{document}"
                + "\\twocolumn \n"
                + "\n"
                + "\\maketitle"
                + "\n";
        codFinal = "\\end{document}";
        //Unimos todas las partes para crear el documento completo de LaTeX para el enunciado
        String codigo = codInicial + "\n" + op.getDescripcionOpcion() + "\n" + codFinal;
        //Creamos el archivo .Tex con el codigo creado anteriormente en la ruta especificada
        String nom = "Opcion";
        String ext = ".tex";
        File archivo = new File(ruta, nom + ext);
        PrintWriter a = null;
        try {
            a = new PrintWriter(archivo);

        } catch (IOException ex) {
            Logger.getLogger(latex.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.println(codigo);
        a.close();
        //Creamos el archivo .BAT para compilar LaTeX
        adminArch.crearBAT(ruta, nom + ext);
        return adminArch.ejecutarBAT(ruta, nom);

    }

    public void nuevoTema(Tema tema, String año, String sem) throws IOException {
        String rutaCom = "C:\\Users\\Daniel\\Desktop\\Pruebas\\examen" + tema.getCodigo();
        File folder = new File(rutaCom);
        if (folder.exists()) {
            Process q = Runtime.getRuntime().exec("cmd /c start " + rutaCom + "\\examen.pdf");
            return;
        }
        Enunciado e = new Enunciado();
        enunciadoDAO eD = new enunciadoDAO();
        int cont = 1;
        ArrayList codigosEnunciados = new ArrayList();
        Iterator it = tema.getPreguntas().iterator();
        int temp = -1;
        String body = "";
        String paquetes = "";
        while (it.hasNext()) {
            Pregunta_Tema pregunta = (Pregunta_Tema) it.next();
            Pregunta Paux = pregunta.getPregunta();
            pDAO.cargarPregunta(Paux);
            Enunciado en = Paux.getEnunciado();//Arreglar enunciado DTO

            if (en.getCodigo() != temp) {
                temp = en.getCodigo();
                if (en.getPaquetes() != null) {
                    paquetes = paquetes + en.getPaquetes() + " \n ";
                }
                if (en.getEnunciado() != null) {
                    body = body + en.getEnunciado() + " \n ";
                }
            }
            Pregunta p = pregunta.getPregunta();
            combinacionDAO cDAO = new combinacionDAO();
            cDAO.cargarCombinacion(pregunta.getCombinacion());

            if (p.getDescripcionPregunta() != null) {
                body = body + pregunta.getNroPregunta() + ". " + p.getDescripcionPregunta() + "\\newline \n \\newline \n  ";
            }
            ArrayList<Opcion> oo = (ArrayList) p.getOpciones();
            combinacion combinacion = pregunta.getCombinacion();
            if (oo.size() < 4) {
                continue;
            }
            String nom = "";
            if (oo.get(combinacion.getA() - 1) != null) {
                nom = nom + "A. " + oo.get(combinacion.getA() - 1).getDescripcionOpcion() + "\\newline \n ";
            }
            if (oo.get(combinacion.getB() - 1) != null) {
                nom = nom + "B. " + oo.get(combinacion.getB() - 1).getDescripcionOpcion() + "\\newline \n ";
            }
            if (oo.get(combinacion.getC() - 1) != null) {
                nom = nom + "C. " + oo.get(combinacion.getC() - 1).getDescripcionOpcion() + "\\newline \n ";
            }
            if (oo.get(combinacion.getD() - 1) != null) {
                nom = nom + "D. " + oo.get(combinacion.getD() - 1).getDescripcionOpcion() + "\\newline \n ";
            }
            /*    Opcion opA = (Opcion) oo.get(combinacion.getA()-1);
             Opcion opB = (Opcion) oo.get(combinacion.getB()-1);
             Opcion opC = (Opcion) oo.get(combinacion.getC()-1);
             Opcion opD = (Opcion) oo.get(combinacion.getD()-1);
            
             if (opAuxi.getDescripcionOpcion() != null) {
             String letra = "";
                    
             if (1== combinacion.getA()) {
             letra = "A";
             } else if (2 == combinacion.getB()) {
             letra = "B";
             } else if (3== combinacion.getC()) {
             letra = "C";
             } else if (4 == combinacion.getA()) {
             letra = "D";
             }
                
             body = body +"A. " + opA.getDescripcionOpcion() + "\\newline \n ";
             body = body +"B. " + opB.getDescripcionOpcion() + "\\newline \n ";
             body = body +"C. " + opC.getDescripcionOpcion() + "\\newline \n ";
             body = body +"D. " + opD.getDescripcionOpcion() + "\\newline \n ";
             //}*/
            body = body + nom + "\\newline \n";
        }

        cont++;

        codInicial = "\\documentclass[a4paper,12pt]{article}\n"
                + "\\usepackage[left=1cm,top=3cm,right=1cm,bottom=3cm]{geometry} \n"
                + "\\usepackage[utf8]{inputenc} \n"
                + "\\usepackage[spanish]{babel}\n"
                + "\\usepackage{times}\n"
                + "\\usepackage{graphicx}\n"
                + paquetes
                + "\n"
                + "\n"
                + "\\title{ \n"
                + "\\begin{minipage}{12cm} \n"
                + "\\centerline {\\includegraphics{C:/Users/Daniel/Desktop/latex/escudo.jpg}} \n"
                + "\\begin{center}"
                + "Vicerector\\'ia de Docencia"
                + "\\end{center}"
                + "Departamento de Admisiones y Registro"
                + "\\newline"
                + "\\newline"
                + "\\newline"
                + "\\newline"
                + "\\centerline {Examen de Admisi\\'on}"
                + "\\newline"
                + "\\newline"
                + "\\centerline {" + año + " - " + sem + "}"
                + "\\author{Universidad de Antioquia}"
                + "\\date{Jornada 1:  " + tema.getJornada() + "}"
                + "\\end{minipage}"
                + "}"
                + "\\begin{document}\n"
                + "\n"
                + "\\maketitle\n"
                + "\\twocolumn \n"
                + "\\newpage \n";

        codFinal = "\\end{document}";

        this.crearExamen(2014, codInicial, body, codFinal, tema);
    }

    public void nuevoExamen(Tema tema, String año, String sem) throws IOException {
        String rutaCom = "C:\\Users\\Daniel\\Desktop\\Pruebas\\examen" + tema.getCodigo();
        File folder = new File(rutaCom);
        if (folder.exists()) {
            /*  PrintWriter a = null;
             File bat = new File("exec.bat");
             a = null;
             try {
             a = new PrintWriter(bat);

             } catch (FileNotFoundException ex) {
             Logger.getLogger(latex.class.getName()).log(Level.SEVERE, null, ex);
             }

             String comando = "cd " + rutaCom + "\\\\\" \n pdflatex examen.tex \n start " + rutaCom + "\\examen.pdf \n exit";
             a.print(comando);
             a.close();*/
            Process q = Runtime.getRuntime().exec("cmd /c start " + rutaCom + "\\examen.pdf");
            return;
        }
   //a tema meterle fecha (sacarladelsistema) y  
        //crear instancia de temaDAO y ustiliza emtodo crearTema y envio el tema que acabod e crear

        //p= this.juntarPreguntas(preg);
        //lsitarenunciado--Array, recorro saco paquetes y lo meto en string 
        Enunciado e = new Enunciado();
        enunciadoDAO eD = new enunciadoDAO();
        ArrayList codigosEnunciados = new ArrayList();
        int cont = 1;
        codigosEnunciados = eD.listarCodigos();
        String paquetes = "", body = "";
        Iterator i = codigosEnunciados.iterator();
        while (i.hasNext()) {
            int eCod = (int) i.next();
            e.setCodigo(eCod);
            eD.cargarEnunciado(e);
            if (e.getPaquetes() != null) {
                paquetes = paquetes + e.getPaquetes() + " \n ";
            }

            if (body != null) {
                body = body + e.getEnunciado() + " \n  ";
            }

            Pregunta p = new Pregunta();
            preguntaDAO pD = new preguntaDAO();
            ArrayList codigosPreguntas = new ArrayList();
            codigosPreguntas = pD.listarCodigosPreguntasdeEnunciado(eCod);
            Iterator j = codigosPreguntas.iterator();

            while (j.hasNext()) {
                int pCod = (int) j.next();
                Pregunta_Tema pt = new Pregunta_Tema();
                //          Tema tt = new Tema();
                //         tt.setCodigo(0);
                combinacion comb = new combinacion();
                comb.setCodigo(1);
                Pregunta pp = new Pregunta();
                pp.setCodigo(pCod);
                pt.setPregunta(pp);
                pt.setRespuesta(0);
                pt.setTema(tema);
                pt.setCombinacion(comb);
                pt.setNroPregunta(cont);
                preguntaTemaDAO ptd = new preguntaTemaDAO();
                ptd.ingresarPreguntaTema(pt);

                p.setCodigo(pCod);
                pD.cargarInformacionPregunta(p);
                if (body != null) {
                    body = body + cont + ". " + p.getDescripcionPregunta() + "\\newline \\newline  \n ";
                }

                Opcion op = new Opcion();
                opcionesDAO opD = new opcionesDAO();
                Collection<Opcion> codigosOpciones = new ArrayList();
                codigosOpciones = opD.listarCodigosOpcionesdePregunta(pCod);
                Iterator k = codigosOpciones.iterator();
                while (k.hasNext()) {
                    Opcion opAuxi = (Opcion) k.next();
                    int opCod = opAuxi.getCodigo();
                    op.setCodigo(opCod);
                    Pregunta preg = new Pregunta();
                    preg.setCodigo(opAuxi.getPregunta().getCodigo());
                    op.setPregunta(preg);
                    opD.cargarOpcion(op);
                    if (op.getDescripcionOpcion() != null) {
                        String letra = "";
                        if (op.getCodigo() == 1) {
                            letra = "A";
                        } else if (op.getCodigo() == 2) {
                            letra = "B";
                        } else if (op.getCodigo() == 3) {
                            letra = "C";
                        } else if (op.getCodigo() == 4) {
                            letra = "D";
                        }
                        body = body + letra + ". " + op.getDescripcionOpcion() + "\\newline \n ";
                    }
                }
                body = body + "\\newline \n";
                cont++;
            }
        }

        codInicial = "\\documentclass[a4paper,12pt]{article}\n"
                + "\\usepackage[utf8]{inputenc} \n"
                + "\\usepackage[left=1cm,top=3cm,right=1cm,bottom=3cm]{geometry} \n"
                + "\\usepackage[spanish]{babel}\n"
                + "\\usepackage{times}\n"
                + "\\usepackage{graphicx}\n"
                + paquetes
                + "\n"
                + "\n"
                + "\\title{ \n"
                + "\\begin{minipage}{12cm} \n"
                + "\\centerline {\\includegraphics{C:/Users/Daniel/Desktop/examenes/escudo.jpg}} \n"
                + "\\begin{center}"
                + "Vicerector\\'ia de Docencia"
                + "\\end{center}"
                + "Departamento de Admisiones y Registro"
                + "\\newline"
                + "\\newline"
                + "\\newline"
                + "\\newline"
                + "\\centerline {Examen de Admisi\\'on}"
                + "\\newline"
                + "\\newline"
                + "\\centerline {" + año + " - " + sem + "}"
                + "\\author{Universidad de Antioquia}"
                + "\\date{Jornada 1:  " + tema.getJornada() + "}"
                + "\\end{minipage}"
                + "}"
                + "\\begin{document}\n"
                + "\n"
                + "\\maketitle\n"
                + "\\twocolumn \n"
                + "\\newpage ";

        codFinal = "\\end{document}";

        this.crearExamen(2014, codInicial, body, codFinal, tema);
    }

    public void crearExamen(int n, String inicio, String preguntas, String fin, Tema tema) {
        String nombre = "examen";
        String ext = ".tex";
        rutaDAO ruta = new rutaDAO();
        try {
            Process p = Runtime.getRuntime().exec("cmd /c " + "md " + ruta.getRuta() + "\\examen" + tema.getCodigo());
            //Process p = Runtime.getRuntime().exec("cmd /c "+"md "+ruta+"\\"+nombre);
            Thread.sleep(500);
            File archivo = new File(ruta.getRuta() + "\\examen" + tema.getCodigo() + "\\" + nombre + ext);
            PrintWriter a = null;

            try {
                a = new PrintWriter(archivo);

            } catch (IOException ex) {
                Logger.getLogger(latex.class.getName()).log(Level.SEVERE, null, ex);
            }

            String cm = inicio + preguntas + fin;
            a.println(cm);
            a.close();

            File bat = new File("exec.bat");
            a = null;
            try {
                a = new PrintWriter(bat);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(latex.class.getName()).log(Level.SEVERE, null, ex);
            }
            String comando = "cd " + ruta.getRuta() + "\\examen" + tema.getCodigo() + "\\\\\" \n pdflatex " + nombre + ext + " \n start " + ruta.getRuta() + "\\examen" + tema.getCodigo() + "\\" + nombre + ".pdf \n exit";
            a.print(comando);
            a.close();

            try {
                Process q = Runtime.getRuntime().exec("cmd /c start " + "exec.bat");
                //   temaDAO td=new temaDAO();
                //   td.ingresarTema(tema);

            } catch (IOException ex) {
                Logger.getLogger(latex.class.getName()).log(Level.SEVERE, null, ex);
                //   Process q = Runtime.getRuntime().exec("cmd /c start"+"RD C:\\Users\\Daniel\\Desktop\\latex\\examen"+n);
                // n--;
            }
        } catch (IOException ex) {
            Logger.getLogger(latex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(latex.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int borrarPregunta(Pregunta p) throws IOException {
        preguntaDAO pDAO = new preguntaDAO();
        pDAO.cargarInformacionPregunta(p);
        Process q1 = Runtime.getRuntime().exec("cmd /c start /b /wait \n RD /S /Q " + p.getUrl() + " \n exit");
        if (pDAO.borrarPregunta(p.getCodigo()) == 1) {
            return 1;
        }
        return 0;
    }

    public void vaciarOpcion(Opcion p) throws IOException {
        opcionesDAO pDAO = new opcionesDAO();
       // pDAO.cargarOpcion(p);
        Process q1 = Runtime.getRuntime().exec("cmd /c start /b /wait \n RD /S /Q " + p.getUrl() + " \n exit");
        if (pDAO.vaciarOpcion(p.getCodigo()) == 1) {
            return;
        }
    }

    public int crearPregunta(Pregunta p) throws IOException, InterruptedException {
        //Para crear un enunciado, primero obtiene la ruta donde se está almacenando la información LaTeX
        rutaDAO url = new rutaDAO();
        //Creamos la carpeta que contendrá toda la información del enunciado a  crear

        String nombre = "Pregunta" + p.getCodigo();
        Enunciado enAux = new Enunciado();
        enAux.setCodigo(p.getEnunciado().getCodigo());
        enunciadoDAO endao = new enunciadoDAO();
        endao.cargarEnunciado(enAux);
        String ruta = enAux.getUrl() + "\\" + nombre;
        Process p1 = Runtime.getRuntime().exec("cmd /c " + "md " + ruta + "\\" + nombre);
        p.setUrl(url.getRutaSQL() + "\\\\" + nombre);
        Thread.sleep(500);

        //  Creamos el archivo que contendrá el enunciado e insertamos los paquetes requeridos
        //   File arc = adminArch.crearTxt(ruta,"Pregunta.txt");
        //    adminArch.insertarTxt(arc,p.getDescripcionPregunta());
        //Definimos el codigo incial y final de LaTex
        codInicial = "\\documentclass[a4paper,12pt]{article}"
                + "\\usepackage[spanish]{babel}"
                + "\\usepackage{times}"
                + "\n"
                + "\\title{Vista preliminar de Enunciado}"
                + "\n"
                + "\\usepackage{graphicx}"
                + "\n"
                + "\\begin{document}"
                + "\\twocolumn \n"
                + "\n"
                + "\\maketitle"
                + "\n";
        codFinal = "\\end{document}";
        //Unimos todas las partes para crear el documento completo de LaTeX para el enunciado
        String codigo = codInicial + "\n" + p.getDescripcionPregunta() + "\n" + codFinal;
        //Creamos el archivo .Tex con el codigo creado anteriormente en la ruta especificada
        String nom = "Pregunta";
        String ext = ".tex";
        File archivo = new File(ruta, nom + ext);
        PrintWriter a = null;
        try {
            a = new PrintWriter(archivo);

        } catch (IOException ex) {
            Logger.getLogger(latex.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.println(codigo);
        a.close();
        //Creamos el archivo .BAT para compilar LaTeX
        adminArch.crearBAT(ruta, nom + ext);
        return adminArch.ejecutarBAT(ruta, nom);

    }

}
