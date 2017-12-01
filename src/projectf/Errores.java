
package projectf;

import java.io.FileWriter;
import java.io.IOException;


public class Errores {
     public void GenerarHtml(String simbolo){
    if(simbolo.equals("l")){
         if(AnalizadorLexico.ErroresLexicos.size()>0){
              try{
            
FileWriter fw = new FileWriter("C:\\Users\\carlo\\OneDrive\\Documentos\\NetBeansProjects\\ProjectF\\ErroresLexicos.html");
            fw.write("<meta http-equiv=\"Content-Type\"  content=\"text/html; charset=UTF-8\" />" + "\n"  + "\n");
            fw.write("<HTML><HEAD><TITLE>Listado de Errores</TITLE></HEAD>" + "\n" + "\n");
            fw.write("<HR>" + "\n" + "\n");
            fw.write("<BR><CENTER><TABLE BORDER=1>\n");
            fw.write("	<TR>\n");
            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>Error Lexico</B></FONT></TD>\n");
            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>#_fila</B></FONT></TD>\n");
            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>#_caracter</B></FONT></TD>\n");
           // fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>Fila</B></FONT></TD>\n");
          //  fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>Columna</B></FONT></TD>\n");
           // fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>Palabra Reservada</B></FONT></TD>\n");
             fw.write("</TR>\n");
                    
                    for(int numero=0; numero<AnalizadorLexico.ErroresLexicos.size();numero++){
                        String []aux=AnalizadorLexico.ErroresLexicos.get(numero).split(" ");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" +aux[0]+ "</FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" +aux[1]+ "</FONT></TD>\n");
                    fw.write("<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" +aux[2]+ "</FONT></TD>\n");
                    fw.write("	</TR>\n");
                        }
                    fw.write("</TABLE>" + "\n");
                    fw.flush();
                fw.close();

            }catch(IOException er){
                
                System.out.println(er);
            }
        }
        
    
    }else if(simbolo.equals("s")){
        if(parser.tablaerrorSintactico.size()>0){
            
         try{
FileWriter fw = new FileWriter("C:\\Users\\carlo\\OneDrive\\Documentos\\NetBeansProjects\\ProjectF\\ErroresSintacticos.html");
            fw.write("<meta http-equiv=\"Content-Type\"  content=\"text/html; charset=UTF-8\" />" + "\n"  + "\n");
            fw.write("<HTML><HEAD><TITLE>Listado de Errores</TITLE></HEAD>" + "\n" + "\n");
            fw.write("<HR>" + "\n" + "\n");
            fw.write("<BR><CENTER><TABLE BORDER=1>\n");
            fw.write("	<TR>\n");
            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>Error Sintactico</B></FONT></TD>\n");
            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>#_fila</B></FONT></TD>\n");
            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>#_columna</B></FONT></TD>\n");
             fw.write("</TR>\n");
                    
                    for(int numero=0; numero<parser.tablaerrorSintactico.size();numero++){
                        String []aux=parser.tablaerrorSintactico.get(numero).split(" ");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" +aux[0]+ "</FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" +aux[1]+ "</FONT></TD>\n");
                    fw.write("<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" +aux[2]+ "</FONT></TD>\n");                                    
                    fw.write("	</TR>\n");
                        }
                    fw.write("</TABLE>" + "\n");
                    fw.flush();
                fw.close();

            }catch(IOException er){
                
                System.out.println(er);
            }
        
        }
         
     
        
        
    }
  
    
  
    
    
}

}




    
     

