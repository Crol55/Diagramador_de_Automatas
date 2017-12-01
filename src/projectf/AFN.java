/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectf;

import java.util.ArrayList;

public class AFN {
  
     ArrayList<Automata> listaAutomatas=new ArrayList();
     ArrayList<Integer> indic= new ArrayList(); 
        ArrayList<Integer>  listaanterior=new ArrayList();
        ArrayList<Integer>  listaactual=new ArrayList();
      
    public void construir(String tipo){// para (.,|,+,*,?)
    
        switch(tipo){
          case "*":{ 
                 Automata AFNk= new Automata();
                 int conta=0;
                 int valor= listaAutomatas.size()-1;
                  // System.out.println("ae"+valor);
               
                 estado nuevoinicio= new estado(0);
                 //insertarlo en el nuevo automata y colocarlo como estado inicial
                 AFNk.addestados(nuevoinicio);
                 AFNk.setEstadoInicial(nuevoinicio);
                 // mover los estados intermedios al nuevo automata
                   Automata aux=listaAutomatas.get(valor);// obtenemos el ultimo automata de la lista 
                 for (int i = 0; i <aux.listaEstados.size(); i++) {// trasladar estados intermedios al nuevo automata
                     conta=conta+1;
                          estado au= aux.getestados(i); 
                          
//                       
                          listaanterior.add(au.getid());
                          au.setid(conta);
                          listaactual.add(au.getid());
                     
                      
                   } for (int i = 0; i <aux.listaEstados.size(); i++) {// actualizar las transiciones
                   
                          estado au= aux.getestados(i);                         
                           au.updateTransiciones(listaanterior,listaactual);
                      
                     
                         // System.out.println(au.getid());
                          AFNk.addestados(au);
                      
                   } listaanterior.clear(); listaactual.clear(); print();
                 
                 
                 
                 
                 // isertamos el estado ultimo del automata y colocarlo como estado final
                 
                 estado nuevofin= new estado(conta+1);
                 AFNk.addestados(nuevofin);
                 AFNk.setEstadoUltimo(nuevofin);
                 
                 //recuperar el estado inicial y ultimo, del ultimo automata
                   estado inicioanterior = aux.getEstadoInicial();
                   estado ultimoanterior = aux.getEstadoUltimo();
                   
                   // crear cerraduras con epsilon-->inicio
                 nuevoinicio.addTransiciones(nuevoinicio, inicioanterior, "ε");
                 nuevoinicio.addTransiciones(nuevoinicio, nuevofin, "ε");
                 ultimoanterior.addTransiciones(ultimoanterior, inicioanterior, "ε");
                 ultimoanterior.addTransiciones(ultimoanterior, nuevofin, "ε");
               
                 // -----------------------
                 
                 
                 listaAutomatas.remove(valor);
                 listaAutomatas.add(AFNk); // agregamos el nuevo automata creado a la lista de automatas
             imprimir();
            
            }break;
            
            case "+":{
                int conta=0;
                Automata afn= new Automata();
                    int valor= listaAutomatas.size()-1;
                    Automata antiguo =listaAutomatas.get(valor);
             // insertar inicial
                estado nuevoinicio= new estado(0);
                afn.addestados(nuevoinicio);
                afn.setEstadoInicial(nuevoinicio);
              // insertar estados intermedios     
                    for (int i = 0; i < antiguo.listaEstados.size(); i++) {// traer todos los estados del automata viejo y meterlos en el actual
                       conta=conta+1;
                        estado temp=antiguo.getestados(i);
                        listaanterior.add(temp.getid());
                        temp.setid(conta);
                        listaactual.add(temp.getid());
                       
                }
                     for (int i = 0; i < antiguo.listaEstados.size(); i++) {// traer todos los estados del automata viejo y meterlos en el actual
                           estado temp=antiguo.getestados(i);
                           temp.updateTransiciones(listaanterior, listaactual);
                            afn.addestados(temp);
                            
                }  listaanterior.clear(); listaactual.clear();
             // insertar el ultimo
                estado nuevofin= new estado(conta+1);
                afn.addestados(nuevofin);
                afn.setEstadoUltimo(nuevofin);
            // recuperar inicio y fin del automata anterior
                estado anteriorinicio=antiguo.getEstadoInicial();
                estado anteriorultimo= antiguo.getEstadoUltimo();
            
            
            // aplicar kleene
                nuevoinicio.addTransiciones(nuevoinicio,anteriorinicio, "ε");
                anteriorultimo.addTransiciones(anteriorultimo, anteriorinicio, "ε");
                anteriorultimo.addTransiciones(anteriorultimo, nuevofin, "ε");
                 listaAutomatas.remove(valor);
                 listaAutomatas.add(afn); // agregamos el nuevo automata creado a la lista de automatas
                 imprimir();
            
            }break;
            
            case "?":{
                   
                   // Automata afnn= new Automata();
                    int valor= listaAutomatas.size()-1;
                    Automata aux =listaAutomatas.get(valor);
                    
                    estado inicioanterior =aux.getEstadoInicial();
                 
                    estado anteriorfin= aux.getEstadoUltimo();
                    inicioanterior.addTransiciones(inicioanterior,anteriorfin, "ε");
                    imprimir();
             
            }break;
            
            case ".":{
                 
                    Automata afnn= new Automata();
                    //int valor= listaAutomatas.size()-1;
                    Automata af1 =listaAutomatas.get(1);
                    Automata af2= listaAutomatas.get(0);
                    
                    
                   
                    int conta=0;
                    for (int i = 0; i < af1.listaEstados.size(); i++) {// llenar listas anterior y actual y tambien .setid()
                                        
                        estado temp=af1.getestados(i);
                    listaanterior.add(temp.getid());
                        temp.setid(conta); //temp.actualizarTransiciones(conta);
                    listaactual.add(temp.getid());
                        //afnn.addestados(temp);
                          conta=conta+1; 
                    }print();
                     for (int i = 0; i < af1.listaEstados.size(); i++) {// modificar las transiciones de cada estado
                       
                        estado temp=af1.getestados(i);//devuelve el # de la listaEstados
                      
                        temp.updateTransiciones(listaanterior, listaactual);
                        afnn.addestados(temp);
                        
                    } listaanterior.clear(); listaactual.clear();
 //------------------------------inicia segundo automata de concat-------------------------------------    
  
                         
                         
                    for (int i = 0; i < af2.listaEstados.size(); i++) {
                      //System.out.println("el contador aca vale "+conta);
                        estado temp=af2.getestados(i);//devuelve el # de la listaEstados
                    listaanterior.add(temp.getid());
                        temp.setid(conta);// temp.actualizarTransiciones(conta);
                    listaactual.add(temp.getid());
                      conta=conta+1;
                       // afnn.addestados(temp);
                  } print(); 
                    for (int i = 0; i < af2.listaEstados.size(); i++) {
                       
                        estado temp=af2.getestados(i);
                       
                        temp.updateTransiciones(listaanterior, listaactual);
                        afnn.addestados(temp);
                    } listaanterior.clear(); listaactual.clear(); 
                    
               estado inicioprimero=af1.getEstadoInicial();
                estado finprimero=af1.getEstadoUltimo();
                     estado iniciosegundo= af2.getEstadoInicial();
                     estado finsegundo=af2.getEstadoUltimo();
                         finprimero.addTransiciones(finprimero, iniciosegundo, "ε");
                          
                afnn.setEstadoInicial(inicioprimero);
                afnn.setEstadoUltimo(finsegundo);
                 listaAutomatas.clear();
                 listaAutomatas.add(afnn); // agregamos el nuevo automata creado a la lista de automatas
                 imprimir();  
                      }break;
                      
                      
            case "|":{
               
                Automata afn= new Automata();
                estado nuevoinicio= new estado(0);//estado inicio
                    Automata af1 =listaAutomatas.get(0);// de la lista tomamos el primer automata
                    Automata af2= listaAutomatas.get(1); // de la lista tomamos el segundo automata
                    estado inicioanterior1 =af1.getEstadoInicial(); //obtenemos el inicial de cada automata 
                    estado inicioanterior2 =af2.getEstadoInicial();
                    
                    nuevoinicio.addTransiciones(nuevoinicio, inicioanterior1, "ε"); // el nuevo estado debe unirse con Epsilon al inicial de cada automata
                    nuevoinicio.addTransiciones(nuevoinicio, inicioanterior2, "ε");
                    afn.addestados(nuevoinicio);
                    afn.setEstadoInicial(nuevoinicio); // AL NUEVO AUTOMATA indicarle que nuevoinicio sera el estado inicial
//-------------------------------------------------------------------------------------------------------------------
                    int conta=1;// llenar las listas de comparacion para ir llevando referencia 
                    for (int i = 0; i < af1.listaEstados.size(); i++) {// llenar listas anterior y actual y tambien .setid()
                                  
                        estado temp=af1.getestados(i);
                    listaanterior.add(temp.getid());
                        temp.setid(conta); //temp.actualizarTransiciones(conta);
                    listaactual.add(temp.getid());
                        //afnn.addestados(temp);
                          conta=conta+1;       
                    }print();
                     for (int i = 0; i < af1.listaEstados.size(); i++) {// modificar las transiciones de cada estado
                       
                        estado temp=af1.getestados(i);//devuelve el #estado de la listaEstados
                      
                        temp.updateTransiciones(listaanterior, listaactual);// este metodo de la clase estado es la que nos da las referencias actualizadas''
                        afn.addestados(temp);
                       
                    } nuevoinicio.transiciones.set(0,nuevoinicio.getid()+"-"+nuevoinicio.getsimbolo()+"-"+inicioanterior1.getid());
                     listaanterior.clear(); listaactual.clear();                 
               //SIEMPRE LIMPIAR LAS LISTAS SINO TRUENA ESA MIERDA
                
//--------------------------------------------------------------------------------------------------------------
                    for (int i = 0; i < af2.listaEstados.size(); i++) {
                      
                        estado temp=af2.getestados(i);//devuelve el # de la listaEstados
                    listaanterior.add(temp.getid());
                        temp.setid(conta);// temp.actualizarTransiciones(conta);
                    listaactual.add(temp.getid());
                      conta=conta+1;
                       // afnn.addestados(temp);
                  } print(); 
                    for (int i = 0; i < af2.listaEstados.size(); i++) {
                       
                        estado temp=af2.getestados(i);
                       
                        temp.updateTransiciones(listaanterior, listaactual);
                        afn.addestados(temp);
                    } nuevoinicio.transiciones.set(1,nuevoinicio.getid()+"-"+nuevoinicio.getsimbolo()+"-"+inicioanterior2.getid());
                     
                    listaanterior.clear(); listaactual.clear(); 
                    estado nuevofin= new estado(conta);
                    estado finafn1=af1.getEstadoUltimo();
                    estado finafn2= af2.getEstadoUltimo();
                    
                    finafn1.addTransiciones(finafn1, nuevofin, "ε");
                    finafn2.addTransiciones(finafn2, nuevofin, "ε");
                    
                    
                    
                    afn.setEstadoUltimo(nuevofin);
                    
                    afn.addestados(nuevofin);
                 listaAutomatas.clear();
                 listaAutomatas.add(afn); // agregamos el nuevo automata creado a la lista de automatas
                 imprimir();  
            }break;
            
            default:{
            System.out.println("debi entrar aca porque es un caracter "+tipo);
            
            
        Automata afn= new Automata();
        // creamos 2 estados uno inicio y uno de aceptacion(fin)
        estado inicio=new estado(0);
        estado fin= new estado(1);
        // agregar la transicion a inicio
        inicio.addTransiciones(inicio, fin, tipo);
        // agregar todos los estados(2) al automata
        afn.addestados(inicio);
        afn.addestados(fin);
        afn.setEstadoInicial(inicio);
        afn.setEstadoUltimo(fin);
        listaAutomatas.add(afn);  
     
            
            
            }break;
            
        }// cierre switch
    
    
    }
    public void print(){
        for (int i = 0; i < listaactual.size(); i++) {
             System.out.println(listaanterior.get(i)+" "+listaactual.get(i));
        }
   
    }
    public void imprimir(){
       
    Automata aux=listaAutomatas.get(listaAutomatas.size()-1);
     System.out.println("imprimire el automata final "+listaAutomatas.size());
        for (int i = 0; i <aux.listaEstados.size() ; i++) {
            estado est=aux.getestados(i);
            for (int j = 0; j < est.transiciones.size(); j++) {
                 System.out.println(est.transiciones.get(j));
            }
           
        }
    }
    
    public Automata getFinalAutomata(){
    Automata automata= listaAutomatas.get(0);
    
    return automata;
    }
    
    public void Limpiar(){
    listaAutomatas.clear();
    }
//    public void construir(String cadena,int indice){//simbolos
//       
//       Automata afn= new Automata();
//        // creamos 2 estados uno inicio y uno de aceptacion(fin)
//        estado inicio=new estado(0);
//        estado fin= new estado(1);
//        // agregar la transicion a inicio
//        inicio.addTransiciones(inicio, fin, cadena);
//        // agregar todos los estados(2) al automata
//        afn.addestados(inicio);
//        afn.addestados(fin);
//        afn.setEstadoInicial(inicio);
//        afn.setEstadoUltimo(fin);
//        listaAutomatas.add(afn);  
//        indic.add(indice);
//      
//       }
    
     
  
   
   
   
     
      public static void main(String Args[]){
    AFN a= new AFN();
 
    a.construir("digito");  
     //a.construir("?");
//    a.construir("_");
////  
////    a.construir("|");
////    a.construir("letra",1);
////     a.construir("|");
////      a.construir("*");
////       a.construir("letra",1);
////      a.construir("*");
//  //a.construir("h",1); 
////    a.construir("*");
// a.construir(".");

   // a.construir("-");  
     //a.construir("?");
//    a.construir("_");
//  
//    a.construir("|");
//    a.construir("letra");
//     a.construir("|");
//      a.construir("*");
//       a.construir("letra");
////      a.construir("*");
//  //a.construir("h",1); 
////    a.construir("*");
// a.construir(".");

      System.out.println("cantidad de automatas en la lista "+a.listaAutomatas.size());
      
      Generar g= new Generar();
      g.GenerarDot("afn",g.estructuraDOT("afn",a.getFinalAutomata()),"AFN"); 
      g.GenerarJpg("afn");
      
     // a.construir(".");

         
   

    }
     
   
    
}
