
package projectf;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Generar {// clase para generar .dot y jpg de los automatas 
      String [][]x;
      int [][]matriz; int col=0;
      String aceptado="";
      ArrayList<Integer> ejex=new ArrayList();
      ArrayList<Integer> ejey=new ArrayList();
   	public  void GenerarBat(String nombrearchivo){// se encarga de compilar desde consola el archivo Dot y generar el Jpg
        String fic = "graficar.bat";
        String carpeta  = "C:\\release\\bin\\dot.exe";
        String archivoDot = "C:\\Users\\carlo\\OneDrive\\Documentos\\NetBeansProjects\\ProjectF\\AFN\\"+nombrearchivo+".dot";
        String archivoJpg = "C:\\Users\\carlo\\OneDrive\\Documentos\\NetBeansProjects\\ProjectF\\AFN\\"+nombrearchivo+".jpg";
        String tParam = "-Tjpg";
        String tOParam = "-o";
        
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try{
            fichero = new FileWriter(fic);
            escritor = new PrintWriter(fichero);
            escritor.println("@echo off");
            escritor.println(carpeta + " " + tParam + " " + archivoDot + " " + tOParam + " " + archivoJpg);
            escritor.println("exit");
            escritor.close();
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    
            public void GenerarJpg(String id){// encargado de abrir el jpg creado en ese momento
        GenerarBat(id);
        Runtime aplicacion = Runtime.getRuntime(); 
        try{
           // Thread.sleep(2000);
            aplicacion.exec("graficar.bat");
            System.out.println("Ejecuto bat");
            Thread.sleep(2000);
//            try{
//                Thread.sleep(7000);
//                try {
//                    Desktop.getDesktop().open(new File(id+".jpg"));
//                    System.out.println("Genero jpg");
//                } catch (IOException ex) {
//                    System.out.println("Error " + ex.getMessage());
//                }
//            }catch(Exception e){
//                System.out.println(e);
//            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
     
            
             public void GenerarDot(String nombreArchivo,String cadena,String ubicacion){
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try{
           // fichero = new FileWriter(nombreArchivo+".dot");
            fichero= new FileWriter(new File("C:\\Users\\carlo\\OneDrive\\Documentos\\NetBeansProjects\\ProjectF\\"+ubicacion+"",nombreArchivo+".dot"));
            escritor = new PrintWriter(fichero);
            escritor.println(cadena);
            escritor.close();
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
             
  public String estructuraDOT(String nombreArchivo,Automata automata){
    
        String texto = "digraph AFN {\n";

        texto +="\trankdir=LR;"+"\n";
        
        texto += "\tgraph [label=\""+nombreArchivo+"\", labelloc=t, fontsize=20]; \n";
        texto +="\tnode [shape=doublecircle, style = filled,color = mediumseagreen];";
     // colocar estado de aceptacion
                int x=automata.listaEstados.size()-1;
            texto+="\n "+x; // 
        
        //
        texto+=";"+"\n";
        texto +="\tnode [shape=circle];"+"\n";
        texto +="\tnode [color=midnightblue,fontcolor=white];\n" +"	edge [color=red];"+"\n";
       
        texto +="\tsecret_node [style=invis];\n" + "	secret_node -> "+0+" [label=\"inicio\"];" + "\n";
               for (int i = 0; i <automata.listaEstados.size() ; i++) {
                      estado est=automata.getestados(i);
                     
                          for (int j = 0; j < est.transiciones.size(); j++) {
                               String xx[]=est.transiciones.get(j).split("-"); 
                               if (xx[1].contains("\"")){
                                 texto+="\t"+xx[0]+"->"+xx[2]+" [label="+xx[1]+"]"+"\n";
                               }else{
                                 texto+="\t"+xx[0]+"->"+xx[2]+" [label=\""+xx[1]+"\"]"+"\n";
                               }
                          
            }
           
        }
           texto+="}";
        
           return texto;     
    }
 
  
  public void trasladar(String [][]v,int [][]v2,String estadoaceptacion,int col){
    this.x=v;
    this.matriz=v2;
    this.aceptado=estadoaceptacion;
    this.col=col;
    unificar();
  }
  
  public void unificar(){
        int auu=0;
        System.out.println("imprimiendo desde la clase generar");
       while(x[auu][0]!=null){
       
           for (int i = 0; i < col; i++) {
               if(x[auu][i].contains(aceptado)){
                    ejex.add(auu); ejey.add(i); // almacenamos todas las posiciones donde se encuentran los estados de aceptacion
               }
               System.out.print(x[auu][i]+"        ");
           } System.out.print("\n");
           auu++;
       }  
      
      //System.out.println("el estado de acpetacion se encuentra en "+ejex.get(0)+""+ejey.get(0));
  }
  
  public String EstructuraTabla(){// para la tabla de transiciones
      int conta=1;
  String cadena="digraph tabla {\n";
         cadena+="\tgraph [ratio=fill];\n";
         cadena+="\tnode [label=\"\\N\", fontsize=15, shape=plaintext];\n";
         cadena+="\tgraph [bb=\"0,0,352,154\"];\n";
         cadena+="\tarset [label=<\n";
         cadena+="\t\t<TABLE ALIGN=\"LEFT\">\n";
           cadena+="<TR>\n";
           for (int i = 0; i < col; i++) {
            cadena+="<TD>"+x[0][i]+"</TD>\n";
               }
            cadena+="</TR>\n";
         while(x[conta][0]!=null){
          cadena+="<TR>\n";
            for (int i = 0; i <col ; i++) {
            cadena+="<TD>"+matriz[conta][i]+"</TD>\n";
          }
         
         cadena+="</TR>\n";
         conta++;
         }
        
         
        
         cadena+="</TABLE>\n";
          cadena+=">, ];\n";
           cadena+="}";                 
       return cadena;           
  }
  public void GenerarTablaBat(String nombrearchivo){
   String fic = "graficar.bat";
        String carpeta  = "C:\\release\\bin\\dot.exe";
        String archivoDot = "C:\\Users\\carlo\\OneDrive\\Documentos\\NetBeansProjects\\ProjectF\\AFD\\"+nombrearchivo+".dot";
        String archivoJpg = "C:\\Users\\carlo\\OneDrive\\Documentos\\NetBeansProjects\\ProjectF\\AFD\\"+nombrearchivo+".jpg";
        String tParam = "-Tjpg";
        String tOParam = "-o";
        
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try{
            fichero = new FileWriter(fic);
            escritor = new PrintWriter(fichero);
            escritor.println("@echo off");
            escritor.println(carpeta + " " + tParam + " " + archivoDot + " " + tOParam + " " + archivoJpg);
            escritor.println("exit");
            escritor.close();
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
  }
   public void GenerarTablaJpg(String id){// encargado de abrir el jpg creado en ese momento
        GenerarTablaBat(id);
        Runtime aplicacion = Runtime.getRuntime(); 
        try{
           // Thread.sleep(2000);
            aplicacion.exec("graficar.bat");
            System.out.println("Ejecuto bat");
            Thread.sleep(2000);
//            try{
//                Thread.sleep(7000);
//                try {
//                    Desktop.getDesktop().open(new File(id+".jpg"));
//                    System.out.println("Genero jpg");
//                } catch (IOException ex) {
//                    System.out.println("Error " + ex.getMessage());
//                }
//            }catch(Exception e){
//                System.out.println(e);
//            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
   
   public String estructura(String nombreArchivo){
    String texto = "digraph AFN {\n";

        texto +="\trankdir=LR;"+"\n";
        
        texto += "\tgraph [label=\""+nombreArchivo+"\", labelloc=t, fontsize=20]; \n";
        texto +="\tnode [shape=doublecircle, style = filled,color = mediumseagreen];";
     // colocar estado de aceptacion
       for (int i = 0; i < ejex.size(); i++) {
            int x=matriz[ejex.get(i)][ejey.get(i)];
             texto+="\n "+x; // 
        
        //
        texto+=";"+"\n";
       }
               
           
        texto +="\tnode [shape=circle];"+"\n";
        texto +="\tnode [color=midnightblue,fontcolor=white];\n" +"	edge [color=red];"+"\n";
       
        texto +="\tsecret_node [style=invis];\n" + "	secret_node -> "+0+" [label=\"inicio\"];" + "\n";
        int conta=0;
               while(x[conta][0]!=null){
                  for (int i = 1; i < col; i++) {
                             //  String xx[]=est.transiciones.get(j).split("-"); 
                            //  if (xx[1].contains("\"")){
                                 texto+="\t"+matriz[conta][0]+"->"+matriz[conta][i]+" [label="+matriz[conta][i]+"]"+"\n";
                           //    }else{
                                // texto+="\t"+xx[0]+"->"+xx[2]+" [label=\""+xx[1]+"\"]"+"\n";
                            //   }
                          
            }
                  conta++;
         }
                     
         
       
           texto+="}";
   return texto;
   }
   
   public void GenerarJpgAFD(String id){// encargado de abrir el jpg creado en ese momento
        GenerarBatAFD(id);
        Runtime aplicacion = Runtime.getRuntime(); 
        try{
           // Thread.sleep(2000);
            aplicacion.exec("graficar.bat");
            System.out.println("Ejecuto bat");
            Thread.sleep(2000);
//           
        }catch(Exception e){
            System.out.println(e);
        }
    }
   
  public void  GenerarBatAFD(String nombrearchivo){
      String fic = "graficar.bat";
        String carpeta  = "C:\\release\\bin\\dot.exe";
        String archivoDot = "C:\\Users\\carlo\\OneDrive\\Documentos\\NetBeansProjects\\ProjectF\\AFD\\"+nombrearchivo+".dot";
        String archivoJpg = "C:\\Users\\carlo\\OneDrive\\Documentos\\NetBeansProjects\\ProjectF\\AFD\\"+nombrearchivo+".jpg";
        String tParam = "-Tjpg";
        String tOParam = "-o";
        
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try{
            fichero = new FileWriter(fic);
            escritor = new PrintWriter(fichero);
            escritor.println("@echo off");
            escritor.println(carpeta + " " + tParam + " " + archivoDot + " " + tOParam + " " + archivoJpg);
            escritor.println("exit");
            escritor.close();
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
   }
  public void gen(String nombreArchivo,String cadena,String ubicacion){
    FileWriter fichero = null;
        PrintWriter escritor = null;
        try{
           // fichero = new FileWriter(nombreArchivo+".dot");
            fichero= new FileWriter(new File("C:\\Users\\carlo\\OneDrive\\Documentos\\NetBeansProjects\\ProjectF\\"+ubicacion+"",nombreArchivo+".dot"));
            escritor = new PrintWriter(fichero);
            escritor.println(cadena);
            escritor.close();
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
  }
}
