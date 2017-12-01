
package projectf;

import java.util.ArrayList;



public class Automata {
 private estado inicial;
 private estado ultimo;
 ArrayList<estado> listaEstados ;
    
    public Automata(){
      listaEstados=new ArrayList();
    }
    
    public void addestados(estado estad){
        listaEstados.add(estad);
     
    
    }
    
    public estado getestados(int indice){
     return listaEstados.get(indice);
    }
    
    public void setEstadoInicial(estado ini){
       this.inicial=ini;
       
    }
    
    public void setEstadoUltimo(estado u){
    this.ultimo= u;
    }
    
    public estado getEstadoInicial(){
    return inicial;
    }
    
    public estado getEstadoUltimo(){
    return ultimo;
    }
    
    
   
    
    
}
