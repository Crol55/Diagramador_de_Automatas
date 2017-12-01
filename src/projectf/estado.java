
package projectf;

import java.util.ArrayList;
import java.util.LinkedList;


 public class estado { /*Con esta clase se crean todos los estados del AFN*/
    Object simbolo;
    int  id;
    Object sig_e; // 
    Object ant_e;
    estado inicial;
    estado fin;
    
    LinkedList<estado> ListaFinal ;
      ArrayList<String>  transiciones=new ArrayList();
   
         
//   
//    public void updateTransiciones( ArrayList<Integer>  anterior, ArrayList<Integer>  nuevo){// se ejecuta para cada estado del automata
//        for (int i = 0; i < transiciones.size(); i++) { //obtener todos las transiciones por estado.
//            String x[]=transiciones.get(i).split("-");// lo partimos el cual su tamaño maximo sera [3]
//            System.out.println("awebo levuelto   "+anterior.get(0)+" "+nuevo.get(0));
//            int id2=Integer.parseInt(x[0]);
//            int id3=Integer.parseInt(x[2]);
//            id2=id2+1;
//            id3=id3+1;
//           // System.out.println("el valor aqui es "+id3);
//           String cadena =String.valueOf(id2);
//           String cadena2 =String.valueOf(id3);
//            transiciones.set(i,cadena+"-"+x[1]+"-"+cadena2);
//        }
//    }
        public void updateTransiciones( ArrayList<Integer>  anterior, ArrayList<Integer>  nuevo){// se ejecuta para cada estado del automata
        for (int i = 0; i < transiciones.size(); i++) { //obtener todos las transiciones por estado.
        //  System.out.println("#transiciones por estado "+transiciones.size());
            String x[]=transiciones.get(i).split("-");// lo partimos el cual su tamaño maximo sera [0-2]
          //  int id2=Integer.parseInt(x[0]);
            for (int j = 0; j < anterior.size(); j++) {
                
                if(anterior.get(j).toString().equals((nuevo.get(j).toString()))){// no deberia mover ninguna transicion
               //  System.out.println("son iguales");
                }else{
                  if(x[2].equals(anterior.get(j).toString())){//fin==anterior-->fin=nuevo
                   int id3=nuevo.get(j);
                  
                
                String cadena =String.valueOf(getid());
                String cadena2 =String.valueOf(id3);
               
                 transiciones.set(i,cadena+"-"+x[1]+"-"+cadena2);
                 //System.out.println("existio coincidencia");
              }else{
              // System.out.println("no existio coincidencia");
              }
                }
                 
            }
          
            
        }
    }
    
    public void actualizarTransiciones(int conta){
      for (int i = 0; i < transiciones.size(); i++) { 
          int idd=conta+1;
          transiciones.set(i,inicial.getid()+"-"+simbolo+"-"+fin.getid());
      }
    }
    
    public estado update(estado est,int anterior,int actual){// algoritmo: actualizar todos los estados que antes dependian del valor ingresado como parametro
    
        boolean encontrada=true;
        // System.out.println("encontre coincidencia"+est.fin.getid());
        while(encontrada==false){
                
              if(est.fin.getid()==anterior){
                  est.fin.setid(actual);
                  encontrada=true;
                 // System.out.println("encontre coincidencia");
              }
        
        }
        
        return est;
    }
    
//    public estado(LinkedList<estado> est){
//         this.ListaFinal=est;
//       // estado aux=ListaFinal.get(0);
//    System.out.println("estoy imprimiendo desde la clase estatica ");
//    }
    public estado(int id){
    this.id=id;
    }
    public int getid(){
        return id;
    }
    public void setid(int dato){
    this.id=dato;
    }
    
    public void addTransiciones(estado inicial,estado fin,Object simbolo){
    this.inicial=inicial;
    this.fin=fin;
    this.simbolo=simbolo;
    transiciones.add(toString());
    
//    String aux= transiciones.get(0);
//    System.out.println(""+transiciones.size()+""+aux);
  //   System.out.println(toString());
    }
   
 
//    
    @Override
   public String toString() {
//        return "("+inicial.getid() +"-" + simbolo+"-" + fin.getid()+")";
      return inicial.getid() +"-" + simbolo+"-" + fin.getid();
    }
//    
   
    
    
   
    public Object getsimbolo(){
    return simbolo;
    }
    
    public Object getSigE(){
    return sig_e;
    }
    
    public Object getAntE(){
    return ant_e;
    }
    public void setEstadoSig(int dato){
    this.id=dato;
    }
    
    public void setfin(estado est){
    this.fin=est;
    }
   
}
