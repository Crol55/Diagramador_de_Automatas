
package projectf;
 /*
    clase para Generar Thompson tabla de simbolos y apartir de la tabla de simbolos graficar 
    
    */
import java.util.ArrayList;
import java.util.*;
import javax.swing.JOptionPane;
public class AFD {

    String [][]x;
    int [][]matriz;
         private ArrayList<String> trans=new ArrayList();
         private final Queue<String> cola = new LinkedList<>();
         private final Queue<String> auxiliar = new LinkedList<>();
        HashSet<String> l= new HashSet<>();
          ArrayList<String>  marcadas=new ArrayList();
          ArrayList<String> lista_cerradura=new ArrayList();
          ArrayList<String> alfabeto=new ArrayList();
          ArrayList<Integer> sortt=new ArrayList();// almacenar datos y ordenarlos
           ArrayList<String> simb=new ArrayList();
            ArrayList<Integer> equivalente=new ArrayList();
        public String aceptacion="";
          int actual=1;
          int fila=1; 
          int columna=1;
          boolean primeravez=true;
          boolean terminar=false;
          int especial=1; // es el indice para agregar a la matriz una cerradura
    public AFD(Automata automata, ArrayList<String>alfabeto){
        actual=1; fila=1; columna=1; primeravez=true; terminar=false; especial=1; aceptacion="";
/* trasladamos todos los estados del Ultimo automata 
   y sacarle todas las transiciones ya que es lo unico que interesa para crear la tabla de transiciones
   para una nueva ArrayLista 'trans'*/
        this.alfabeto=alfabeto;//------------------ recibiendo el alfabeto que el parser envia
       x = new String[40][alfabeto.size()+1];
        for (int i = 0; i < automata.listaEstados.size(); i++) {
               estado est=automata.getestados(i);
               for (int j = 0; j < est.transiciones.size(); j++) {
                  this.trans.add(est.transiciones.get(j));
            }
          
        }
        //-----------determinando el estado de aceptacion del automata
        aceptacion=trans.get(trans.size()-1);
        String []aux=aceptacion.split("-");
        aceptacion=aux[2];
        System.out.println("el estado de aceptacion sera "+aceptacion);
        
        ///--------------------------------------------------------------------
        cola.add("0");// el inicio de la pila siempre sera "0"
        //alfabeto.add("digito"); alfabeto.add("num");
        //----------------------colocando el alfabeto---------------------------
        x[0][0]="Estado";
        int cont=1;
        for (int i = 0; i < alfabeto.size(); i++) {// colocando el alfabeto en la matriz
             x[0][cont]=alfabeto.get(i); 
            // System.out.println("alfabetooooooo"+alfabeto.get(i));
             cont++;
        }
      
    }
    
    public void MAIN(){
      //  for (int i = 0; i < 5; i++) {

     while(terminar==false){
         
       if(!auxiliar.isEmpty()){ // NO esta vacia ejecuta
          // System.out.println("el tamaño de la pila auxiliar es   "+auxiliar.size());
               while(!auxiliar.peek().equals("&")){
                   System.out.println("obtengo aux  "+auxiliar.peek());
                     if(auxiliar.peek().equals("")){ // si esta vacio el mueve no debe ejecutar nada con cerradura
                         auxiliar.poll();// simplemente se desecha el null
                     }else{
                       cola.add(auxiliar.poll().replaceAll(",",""));
                     }
                 
               }
              auxiliar.poll();
              
            }
              Cerradura();
              limpiar();
      
       if(auxiliar.isEmpty()==true){
      //  System.out.println("EL TAMAÑO ES "+auxiliar.size());
        if(x[actual][0]!=null){
        Mueve(actual);
          limpiar();
           actual++;
        }else{
            terminar=true;
        }
       
  
         // s();
       
      
       
       }
       primeravez=false;
      
       }            
    // }//for
   s();
        System.out.println("aca abajo ira ultima");
        transicionesFinales();
        ultima();
    }
    
    
    public void limpiar(){
    marcadas.clear();
    l.clear();
    sortt.clear();
    lista_cerradura.clear();
    }
    
    public void Cerradura(){
      boolean ejecutar=true;
      if(cola.isEmpty()==true){ejecutar=false;   }else{ejecutar=true;}
      
        while(!cola.isEmpty()){ // verificamos que la cola no este vacia
              String extraido=cola.poll();
                
             marcada(extraido);// marcamos todos los estados que ya hayan sido recorrido
             System.out.println("Extrayendo "+extraido);

             if(extraido.equals(aceptacion)){
                   System.out.println("Este es un estado de aceptacion");
                  
               }else{
                  
                   for (int i = 0; i < trans.size(); i++) {
                       String cadena[]=trans.get(i).split("-");
                
                if(cadena[0].equals(extraido)){// si lo extraido de la cola es igual al estado
                    
                    if(cadena[1].contains("ε")){//verifica si nos podemos mvoer con epsilon
                        
                     
                        if(verificar(cadena[2])==false){
                             
                              cola.add(cadena[2]);
                          }                       
                      }
                 }              
              }              
          }

     }
        if(ejecutar==true){
          moldear();// quitar estados repetidos con hashmap
          System.out.println("");
          fin();// ordena los estados y los agrega a lista_cerradura
       
          x[especial][0]=lista_cerradura.get(0); 
//          if(lista_cerradura.get(0).equals("")){
//          // JOptionPane.showMessageDialog(null, "alert","la lista_cerradura esta vacia", JOptionPane.ERROR_MESSAGE);
//             terminar=true;
//         }      
              especial++;
        }
       
     }
    public void Mueve(int indice){
         
        columna=1;
        String aux=x[indice][0];// traer transiciones Cerradura de la matriz
        
        String temp[]=aux.split(",");
       
        int conta=0;
        while(conta<alfabeto.size()){
          
             String sentencia="";
            for (int i = 0; i < temp.length; i++) { // por cada transicion de cerradura X[x][0]
               
               for (int j = 0; j < trans.size(); j++) {
                   
                       String cadena[]=trans.get(j).split("-");
                
                if(cadena[0].equals(temp[i])){// si lo extraido de la cola es igual al estado
                 
                    if(cadena[1].contains(alfabeto.get(conta))){//verifica si nos podemos mover con todas las del alfabeto
                   
                     sentencia+=cadena[2]+",";                     
                      
                    }
                }
             } 
         }//System.out.println("valor de fila "+fila);
            if(sentencia.equals("")){
                  x[fila][columna]="null";// colocar el mueve obtenido a la par de su cerradura
            }else{
             x[fila][columna]=sentencia;// colocar el mueve obtenido a la par de su cerradura
            }
            
                 boolean xe=false;
              if(primeravez==false){
                xe=existeRepitencia(sentencia);
              }
         //    System.out.println("REPITENCIAAAAAAAAAAAAAAAAAAAA "+xe);
             if(xe==false){// si ese conjunto obtenido con mueve ya fue transformado en una cerradura ya no debe volver a ejecutarlo
                 auxiliar.add(sentencia); auxiliar.add("&");
             }       
           // JOptionPane.showMessageDialog(null, "alert",, JOptionPane.ERROR_MESSAGE);
            
              columna++;
              conta++;
        }
        
        fila++;
    }
    public void marcada(String dato){// verificar si un estado ya fue tomado en cuenta para no volver a repetirlo
       
               marcadas.add(dato);
     
    }
    public boolean verificar(String dat){// retorna true si el estado ingresado ya existe en marcadas
        boolean respuesta=false;
        for (int i = 0; i < marcadas.size(); i++) {
            if(marcadas.get(i).equals(dat)){
                respuesta=true;
            }
        }
    return respuesta;
    }
    public void moldear(){// clase para eliminar estados repetidos
        for (int i = 0; i < marcadas.size(); i++) {
          //  System.out.print("estas estan marcadas"+marcadas.get(i)+",");
            l.add(marcadas.get(i));
        }
    }
    public void fin(){ // fin de la cerradura y debe insertar en la lista cerradura
    String concat="";
    for(String dato:l){//l=hashmap
           
               sortt.add( Integer.parseInt(dato));// lista de enteros, utilizada luego para ordenar datos           
    
        }   ordenar();
            String au=concatenar();
            System.out.println(au);
            lista_cerradura.add(au);
     
    }
    
   public void ordenar(){// ordena los datos de sortt 
   Collections.sort(sortt);  
   }
   public String concatenar(){
       String concat="";
       int cont=0;
       for (int i = 0; i < sortt.size(); i++) {
           if(cont==sortt.size()-1){ // para quitar la ultima ,
             concat+=String.valueOf(sortt.get(i));
           }else{
            concat+=String.valueOf(sortt.get(i))+",";
           }
          
           cont++;
       }
       return concat;
   }
   public boolean existeRepitencia(String transicion){// buscara si ese conjunto ya existe en los Mueves anteriores
       boolean ret = false;
       for (int i = 1; i <= fila; i++) {
           for (int j = 1; j <= columna; j++) {
               if(x[i][j].equals(transicion)){
               ret=true;
               }
               
           }
       }
       return ret;
   }
    public void s(){
         int auu=0;
       while(x[auu][0]!=null){
       
           for (int i = 0; i < columna; i++) {
               System.out.print(x[auu][i]+"    jiji ");
           } System.out.print("\n");
           auu++;
       }  
       matriz= new int [auu][columna]; // instanceamos la matriz final
//        int auxiliar=0;
//        System.out.println(x[0][0]+"   "+x[0][1]+"   "+x[0][2]);// encabezado siempre es el mismo XD
        
//        while(x[auxiliar][0]!=null){
//            System.out.println("el tamaño del vector columna  "+columna);
//             System.out.println(x[auxiliar][0]+"       "+x[auxiliar][1]+"   "+x[auxiliar][2]);
//            auxiliar++;
//        }       
//        System.out.println(x[1][0]+"   "+x[1][1]+"   "+x[1][2]);
//        System.out.println(x[2][0]+"   "+x[2][1]+"   "+x[2][2]);
//        System.out.println(x[3][0]+"   "+x[3][1]+"   "+x[3][2]);
      
    }
    public void ultima(){
      int auu=1;
       while(x[auu][0]!=null){
       
           for (int i = 0; i < columna; i++) {
               System.out.print(matriz[auu][i]+"   ");
           } System.out.print("\n");
           auu++;
       } 
    }
    
   public void transicionesFinales(){
    /* metodo para renombrar todas las transiciones como {0,1,2,3,5,6,7,9,10,11 }={A}o a un solo digito{1}*/
    int indicador=0;
    matriz[0][0]=indicador;// siempre la misma
   
 //----------------------------------------------------------   
     int auu=1;
     int spec=1;
       while(x[auu][0]!=null){
           System.out.println("obtengo  "+x[auu][0]);
           for (int i = 1; i < columna; i++) {
               String aux= x[auu][i];
               if(aux.equals("null")){
                   System.out.println("nulo");
                     matriz[auu][i]=-1;
               }else{
                   if(aux.equals(aceptacion+",")){
                       System.out.println("ENCONTRE UN ESTADO DE ACEPTACION");
                        matriz[auu][i]=indicador+1;
                   }else{
               if(verificacion(aux)==false){ // si es false significa q es nuevo, y se puede agregar
               simb.add(aux);   
                 
               indicador++;
                  spec++;
               equivalente.add(indicador);
                matriz[spec][0]=indicador;// representaria a cerradura
               matriz[auu][i]=indicador;// representria a mueve
              
              
               
               }else{// si ya existe, solo afecta al mueve
                 
               int indice=busqueda(aux);// REFERENCIA linea '328'
               matriz[auu][i]=equivalente.get(indice);//System.out.println("obtengo "+equivalente.get(indice));
               }}   
             }
           } 
           auu++;
       }  
  //---------------------------------------------------------     
       
   }
     public boolean verificacion(String cadena){// verificar si los simbolos de mueve, ya existen. retorna true si existe, y false si no
     
         boolean existe=false;
    
        if(!simb.isEmpty()){
          
            for (int i = 0; i < simb.size(); i++) {
                if(simb.get(i).equals(cadena)){ 
                                    
                   existe=true;
                }
            }
        
        }//System.out.println(" retorno  "+existe);
   return existe;
   }
   
   
   public int busqueda(String cadena){// en que indice existio la coincidencia para buscarlo luego en array<equivalente>
       boolean fin=false;
       int retorno=0;
       int contaa=0;
       while(fin==false){
           if(simb.get(contaa).equals(cadena)){ // encuentra coincidencia 
           retorno=contaa;// 
           fin=true;
           }
           contaa++;
       }
       
   return retorno;
   }
 
   
}
