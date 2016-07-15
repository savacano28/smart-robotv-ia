package model;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;
import java.util.LinkedList;
/** 
 * @version 1.0
 * @author Stephanya
 * @author Camilo
 * @author Aura
 */
public class Modelo {
    ArrayList datosSalida,nicho,costoHeuristica,pObjetos;    //Declaracion de variables   
    ArrayList<int[]> bNodos;
    ArrayList<double[]> bNodos1;
    Stack <int []> pila;
    Stack <double []> pila1;
    int pInicio,cObjetos,pIzq,pDer,pArr,pAba,eEvaluar,k,pPadre,kPadre,cPadre,cProteccion;       
    int [] nodo; 
    double [] nodo1;
    /**
     * Metodo constructor
     */
    public Modelo(){          
    }
    
    /*Busqueda del punto inicial de el laberinto(identificado como numero 2)*/
    public int busquedaPInicial(ArrayList datosEstructura){        
        for(int i=0;i<datosEstructura.size();i++){        
            int datoB=Integer.parseInt(datosEstructura.get(i).toString());
            if (datoB==2){
            pInicio=i;
            }
        }                
    return pInicio;
    }
    
    /*Busqueda usando Amplitud*/
    public ArrayList busquedaAmplitud(ArrayList datosEstructura){    
    datosSalida=new ArrayList();       
    nicho=new ArrayList();            
    bNodos = new ArrayList<int[]>();
    int[] l={-1,10,1,-10};
    pInicio=busquedaPInicial(datosEstructura);
    k=0;
    
    nodo=new int[3];
    nodo[0]=pInicio; //pInicio           
    nodo[1]=pInicio;       //k indice en el array           
    nodo[2]=cObjetos;//numer de objetos que lleva                     
    bNodos.add(nodo);      
    
    long time_start, time_end;
    time_start = System.currentTimeMillis();
    
    while(cObjetos!=2){                      
    if(pInicio%10==0){
        pIzq=9;
    }else{
    pIzq=Integer.parseInt(datosEstructura.get(pInicio-1).toString());     }
    
    if(pInicio>89){
        pAba=9;
    }else{
        pAba=Integer.parseInt(datosEstructura.get(pInicio+10).toString());}
    
    if((pInicio-9)%10==0){
        pDer=9;
    }else{
        pDer=Integer.parseInt(datosEstructura.get(pInicio+1).toString()); }
    
    if(pInicio<10){
        pArr=9;
    }else{
    pArr=Integer.parseInt(datosEstructura.get(pInicio-10).toString());    }
    
    nicho.add(pIzq);
    nicho.add(pAba);
    nicho.add(pDer);
    nicho.add(pArr);    
    
    for(int i=0;i<4;i++){
      eEvaluar=Integer.parseInt(nicho.get(i).toString());            
        if (eEvaluar!= 1 && eEvaluar!= 9){     
            nodo=new int[3];
            nodo[0]=pInicio+l[i]; //crece el pInicio (pos en el array)        
            nodo[1]=k;            //almacena la posicion en el arraypNodos del papa
            if (eEvaluar==6 && noPaso(k,nodo[0]) ){
            nodo[2]=cObjetos+1;
            }else{
            nodo[2]=cObjetos;}
            bNodos.add(nodo);               
        }
    }
        nicho.clear();     
        k++;         
        pInicio=bNodos.get(k)[0];               
        cObjetos=bNodos.get(k)[2];               
   }    

    time_end = System.currentTimeMillis();    
    datosSalida.add(time_end - time_start);        
    datosSalida.add(k);   
    
    while(k!=0){
        pPadre=bNodos.get(k)[1];        
        datosSalida.add(bNodos.get(k)[0]);        
        k=pPadre;
    }   
        datosSalida.add(bNodos.get(k)[1]);   
        
    datosSalida.add(2,datosSalida.size()-2);   
    
    return datosSalida; //regresa tramas completas
    }
    
    public boolean noPaso(int nActual, int nBuscar){   
    boolean paso=true;
    int cont=nActual;
    while(cont !=0){
        if(bNodos.get(cont)[0]==nBuscar){
         return false;        }
        cont=bNodos.get(cont)[1];   
    }               
    return paso;
    }
    
    public boolean noPaso1(int nActual, int nBuscar){   
    boolean paso=true; 
    int cont=nActual; 
    while(cont !=0){
        if(bNodos1.get(cont)[0] == nBuscar){
         return false;        }
        cont=(int)bNodos1.get(cont)[1]; 
    }               
    return paso;
    }
    
    public ArrayList busquedaCosto(ArrayList datosEstructura){    
    datosSalida=new ArrayList(); 
    nicho=new ArrayList();            
    bNodos = new ArrayList<int[]>();  
    pila= new Stack <int[]>();    
    pInicio=busquedaPInicial(datosEstructura);        //Obtengo Punto Inicial  
    kPadre=0;
    cObjetos=0;
    k=0;
    
    nodo=new int[6];
    nodo[0]=pInicio; //crece el pInicio           
    nodo[1]=pInicio;            
    nodo[2]=cObjetos;  
    nodo[3]=0; //suma
    nodo[4]=0; //traje 0-no, 1-si
    nodo[5]=0; //posicion actual en el array auxiliar bNodos
    
    bNodos.add(nodo);  
    long time_start, time_end;
    time_start = System.currentTimeMillis();    
    
        while(cObjetos!=2){                      
    if(pInicio%10==0){
        pIzq=9;
    }else{
    pIzq=Integer.parseInt(datosEstructura.get(pInicio-1).toString());     }
    
    if(pInicio>89){
        pAba=9;
    }else{
        pAba=Integer.parseInt(datosEstructura.get(pInicio+10).toString());}
    
    if((pInicio-9)%10==0){
        pDer=9;
    }else{
        pDer=Integer.parseInt(datosEstructura.get(pInicio+1).toString()); }
    
    if(pInicio<10){
        pArr=9;
    }else{
    pArr=Integer.parseInt(datosEstructura.get(pInicio-10).toString());    }
    
    nicho.add(pIzq);
    nicho.add(pAba);
    nicho.add(pDer);
    nicho.add(pArr);  
    int[] l={-1,10,1,-10};
    
    for(int i=0;i<4;i++){
      eEvaluar=Integer.parseInt(nicho.get(i).toString());            
        if (eEvaluar!= 1 && eEvaluar!= 9){     
            nodo=new int[6];
            nodo[0]=pInicio+l[i]; //crece el pInicio (pos en el array)        
            nodo[1]=kPadre;       //almacena la posicion en el arraypNodos del papa
            
            if ((eEvaluar==6) && noPaso(kPadre,nodo[0])) {//si no he pasado por ese nodo, evito contar 2 veces el mismo objeto
            nodo[2]=cObjetos+1;
            }else{
            nodo[2]=cObjetos;}           
            
            if(cProteccion==1||eEvaluar==3){
                nodo[3]=cPadre+1; //proteccion del padre 
                nodo[4]=1;}//si lleva el traje se pone en 1
            else if(eEvaluar==0 || eEvaluar==2 || eEvaluar==6){
                nodo[3]=cPadre+1;
                nodo[4]=cProteccion;}
            else if(eEvaluar==4){            
                nodo[3]=cPadre+3;
                nodo[4]=0;}
            else if(eEvaluar==5){
                nodo[3]=cPadre+6;
                nodo[4]=0;} 
            
            nodo[5]=bNodos.size();                 
            bNodos.add(nodo);    
            pila.push(nodo);
        }                                    
        }    
    nicho.clear();   
    Stack<int[]> tmpStack1 = new Stack<int[]>();            
        tmpStack1=ordenaPila(pila); 
        pila.clear();
        
        for(int u=tmpStack1.size()-1;u>=0;u--){ 
        pila.add(tmpStack1.get(u));
        }
             
        pInicio=pila.peek()[0];        
        cObjetos=pila.peek()[2];// cantidad de objetos
        cPadre=pila.peek()[3]; //cuenta lo que lleva el padre
        cProteccion=pila.peek()[4]; //traje
        kPadre=pila.peek()[5]; //posicion arrayfinal
        pila.pop();  
        k++;
    }        

    time_end = System.currentTimeMillis();
    datosSalida.add(time_end - time_start);        
    datosSalida.add(k);   
    
    while(kPadre!=0){
        pPadre=bNodos.get(kPadre)[1];        
        datosSalida.add(bNodos.get(kPadre)[0]);        
        kPadre=pPadre;
    }   
        datosSalida.add(bNodos.get(kPadre)[1]);               
        datosSalida.add(2,datosSalida.size()-2);
    return datosSalida; //regresa tramas completas
    }
    
    public Stack <int []> ordenaPila(Stack<int[]> input){
    Stack<int[]> tmpStack = new Stack<int[]>();            
    while(!input.isEmpty()) {
            int[] tmp = new int[6];
            tmp = input.pop(); 
            while(!tmpStack.isEmpty() && tmpStack.peek()[3] > tmp[3]) {
                input.push(tmpStack.pop());
            }
            tmpStack.push(tmp);                   
        }
        return tmpStack;
    }     
    
     public Stack <double []> ordenaPila1(Stack<double[]> input){
    Stack<double[]> tmpStack = new Stack<double[]>();            
    while(!input.isEmpty()) {
            double[] tmp = new double[5];
            tmp = input.pop();
            while(!tmpStack.isEmpty() && tmpStack.peek()[3] > tmp[3]) {
                input.push(tmpStack.pop());
            }
            tmpStack.push(tmp);                   
        }
      return tmpStack;
    }  
    
    public ArrayList busquedaProfundidad(ArrayList datosEstructura){    
    datosSalida=new ArrayList(); 
    nicho=new ArrayList();            
    bNodos = new ArrayList<int[]>();
    pila= new Stack <int[]>();        
    pInicio=busquedaPInicial(datosEstructura);        //Obtengo Punto Inicial  
    kPadre=0;
    cObjetos=0;
    k=0;
    int cProfundidad=1;
    int kNodo=0;
    int lProfundidad=20;
    
    nodo=new int[5];
    nodo[0]=pInicio; //crece el pInicio           
    nodo[1]=pInicio;     //padre  pos en bNodos     
    nodo[2]=cObjetos;     //can objetos                
    nodo[3]=cProfundidad;     //padre  pos en bNodos     
    nodo[4]=kNodo;     //posicion array actual del nodo que se guarda                
    
    bNodos.add(nodo);      
           
    long time_start, time_end;
    time_start = System.currentTimeMillis();    
    
    while(cObjetos!=2){  
    if(pInicio%10==0){
        pIzq=9;
    }else{
    pIzq=Integer.parseInt(datosEstructura.get(pInicio-1).toString());     }
    
    if(pInicio>89){
        pAba=9;
    }else{
        pAba=Integer.parseInt(datosEstructura.get(pInicio+10).toString());}
    
    if((pInicio-9)%10==0){
        pDer=9;
    }else{
        pDer=Integer.parseInt(datosEstructura.get(pInicio+1).toString()); }
    
    if(pInicio<10){
        pArr=9;
    }else{
    pArr=Integer.parseInt(datosEstructura.get(pInicio-10).toString());    }
    
    nicho.add(pArr);    
    nicho.add(pDer);    
    nicho.add(pAba);
    nicho.add(pIzq);   
    int[] l={-10,1,10,-1};
    
    for(int i=0;i<4;i++){
      eEvaluar=Integer.parseInt(nicho.get(i).toString());     
        if (eEvaluar!= 1 && eEvaluar!= 9){     
            nodo=new int[5];
            nodo[0]=pInicio+l[i]; //crece el pInicio (pos en el array)                     
            nodo[1]=kPadre; 
            if (eEvaluar==6 && noPaso(kPadre,nodo[0])){ //verifico que no este contando 2 veces el mismo objeto porque ya pase por ahi
            nodo[2]=cObjetos+1;
            }else{
            nodo[2]=cObjetos;} //cProfunidad kNodo
           
            if((cProfundidad+1)==lProfundidad && cObjetos!= 2){ //profundidad del nodo ==limi pop pila, llevo un cont para l profund         
            }else { 
            nodo[3]=cProfundidad+1;            
            pila.push(nodo);
            bNodos.add(nodo);
            nodo[4]=bNodos.size()-1;                        
          }                          
        }        
    }     
     nicho.clear();    
     if (!pila.isEmpty()){
     pInicio=pila.peek()[0];        
     kPadre=pila.peek()[4];        
     cObjetos=pila.peek()[2];
     cProfundidad=pila.peek()[3];     
     pila.pop();
     k++;
     }else{
     break;
     }
   }  

    time_end = System.currentTimeMillis();
    datosSalida.add(time_end - time_start);        
    datosSalida.add(k);   
            
    while(kPadre!=0){
        pPadre=bNodos.get(kPadre)[1];        
        datosSalida.add(bNodos.get(kPadre)[0]);        
        kPadre=pPadre;
    }   
        datosSalida.add(bNodos.get(0)[0]);    
    datosSalida.add(2,datosSalida.size()-2);   
    return datosSalida; //regresa tramas completas
    }
       
    
    public ArrayList busquedaObjeto(ArrayList datosEstructura){ 
        pObjetos=new ArrayList(); 
        for(int i=0;i<datosEstructura.size();i++){        
            int datoB=Integer.parseInt(datosEstructura.get(i).toString());
            if (datoB==6){
            pObjetos.add(i);
            }
        }                
    return pObjetos;
    }
        
    /*
    Funcion heruistica que permite calcular para cada posicion del laberinto el costo estimado
    segun la heuristica implementada.    
    */
    public double heuristicaAdmisible(int pos,int pObj1,int pObj2){
    int pIx,pIy,pO1x,pO1y,pO2x,pO2y;
    double cHeuristica,d1,d2,d3,d4;    
      
        pO1x=(pObj1/10);
        pO1y=(pObj1%10);
        pO2x=(pObj2/10);
        pO2y=(pObj2%10);
        
    //Se calcula el costo de la heuristcia para cada posicion del laberinto    
        pIx=(pos/10);
        pIy=(pos%10);
        d1=Math.abs(pO1x-pIx);
        d2=Math.abs(pO1y-pIy);
        d3=Math.abs(pO2x-pIx);
        d4=Math.abs(pO2y-pIy);
        if((d1+d2)<=(d3+d4)){
        cHeuristica=(d1+d2+Math.abs(pO2x-pO1x)+Math.abs(pO2y-pO1y))/100;
        }else{
        cHeuristica=(d3+d4+Math.abs(pO2x-pO1x)+Math.abs(pO2y-pO1y))/100;
        }        
     return cHeuristica;  
    }
    
    public ArrayList busquedaAvara(ArrayList datosEstructura){    
    int pObj1,pObj2,pObEncontrado=0;
    
    datosSalida=new ArrayList(); 
    costoHeuristica=new ArrayList();     
    nicho=new ArrayList();    
    
    bNodos1 = new ArrayList<double[]>();   
    pila1= new Stack <double[]>();    
    pInicio=busquedaPInicial(datosEstructura);        //Obtengo Punto Inicial  
    pObj1=Integer.parseInt(busquedaObjeto(datosEstructura).get(0).toString());
    pObj2=Integer.parseInt(busquedaObjeto(datosEstructura).get(1).toString());
    k=0;
    kPadre=0;

    nodo1=new double[6];
    nodo1[0]=pInicio; //crece el pInicio           
    nodo1[1]=pInicio;       //la posicion del padre en el array auxiliar     
    nodo1[2]=cObjetos;  //cantidad de objetos
    nodo1[3]=heuristicaAdmisible(pInicio,pObj1,pObj2); //costo heuristica    
    nodo1[4]=0; //posicion actual en el array auxiliar bNodos
    nodo1[5]=2; //Objeto que se encontro primero 
    bNodos1.add(nodo1);  
    long time_start, time_end;
    time_start = System.currentTimeMillis();    
    
        while(cObjetos!=2){              
    if(pInicio%10==0){
        pIzq=9;
    }else{
    pIzq=Integer.parseInt(datosEstructura.get(pInicio-1).toString());     }
    
    if(pInicio>89){
        pAba=9;
    }else{
        pAba=Integer.parseInt(datosEstructura.get(pInicio+10).toString());}
    
    if((pInicio-9)%10==0){
        pDer=9;
    }else{
        pDer=Integer.parseInt(datosEstructura.get(pInicio+1).toString()); }
    
    if(pInicio<10){
        pArr=9;
    }else{
    pArr=Integer.parseInt(datosEstructura.get(pInicio-10).toString());    }
    
    nicho.add(pIzq);
    nicho.add(pAba);
    nicho.add(pDer);
    nicho.add(pArr);  
    int[] l={-1,10,1,-10};
   
    
    for(int i=0;i<4;i++){
      eEvaluar=Integer.parseInt(nicho.get(i).toString());            
        if (eEvaluar!= 1 && eEvaluar!= 9){     
            nodo1=new double[6];
            nodo1[0]=pInicio+l[i]; //crece el pInicio (pos en el array)        
            nodo1[1]=kPadre;       //almacena la posicion en el arraypNodos del papa
            
            if ((eEvaluar==6) && noPaso1(kPadre,pInicio+l[i])) {//si no he pasado por ese nodo, evito contar 2 veces el mismo objeto
            nodo1[2]=cObjetos+1;
            nodo1[5]=nodo1[0];
            }else{
            nodo1[2]=cObjetos;
            nodo1[5]=pObEncontrado;
            }                       
            if (nodo1[2]==0){
            nodo1[3]=heuristicaAdmisible(pInicio+l[i],pObj1,pObj2); //costo heuristica  
            }else if(nodo1[2]==1 && nodo1[5]==pObj1){
            nodo1[3]=heuristicaAdmisible(pInicio+l[i],pInicio+l[i],pObj2); //costo heuristica  
            }else if(nodo1[2]==1 && nodo1[5]==pObj2){
            nodo1[3]=heuristicaAdmisible(pInicio+l[i],pObj1,pInicio+l[i]); //costo heuristica
            }
            nodo1[4]=bNodos1.size();                      
            bNodos1.add(nodo1);    
            pila1.push(nodo1);
        }          
        }    
        
    nicho.clear();   
        Stack<double[]> tmpStack1 = new Stack<double[]>();            
        tmpStack1=ordenaPila1(pila1);         
        pila1.clear();
        
        for(int u=tmpStack1.size()-1;u>=0;u--){ 
        pila1.add(tmpStack1.get(u));       
        }       
             
        pInicio=(int)pila1.peek()[0];         
        cObjetos=(int)pila1.peek()[2];// cantidad de objetos       
        kPadre=(int)pila1.peek()[4]; //posicion arrayfinal
        pObEncontrado=(int)pila1.peek()[5];
        pila1.pop();   
        k++;     
    }        

    time_end = System.currentTimeMillis();
    datosSalida.add(time_end - time_start);        
    datosSalida.add(k);    
    
    while(kPadre!=0){
        pPadre=(int)bNodos1.get(kPadre)[1];        
        datosSalida.add((int)bNodos1.get(kPadre)[0]);        
        kPadre=pPadre;
    }   
        datosSalida.add((int)bNodos1.get(kPadre)[1]);        
        datosSalida.add(2,datosSalida.size()-2);
    
    return datosSalida; //regresa tramas completas
    }
    
    public ArrayList busquedaA(ArrayList datosEstructura){    
       int pObj1,pObj2,pObEncontrado=0;
    
    datosSalida=new ArrayList(); 
    costoHeuristica=new ArrayList();     
    nicho=new ArrayList();    
    
    bNodos1 = new ArrayList<double[]>();   
    pila1= new Stack <double[]>();    
    pInicio=busquedaPInicial(datosEstructura);        //Obtengo Punto Inicial  
    pObj1=Integer.parseInt(busquedaObjeto(datosEstructura).get(0).toString());
    pObj2=Integer.parseInt(busquedaObjeto(datosEstructura).get(1).toString());
    k=0;
    kPadre=0;

    nodo1=new double[7];
    nodo1[0]=pInicio; //crece el pInicio           
    nodo1[1]=pInicio;       //la posicion del padre en el array auxiliar     
    nodo1[2]=cObjetos;  //cantidad de objetos
    nodo1[3]=heuristicaAdmisible(pInicio,pObj1,pObj2); //costo heuristica   + cPadre 
    nodo1[4]=0; //posicion actual en el array auxiliar bNodos
    nodo1[5]=2; //Objeto que se encontro primero 
    nodo1[6]=0; //Traje
    
    bNodos1.add(nodo1);  
    long time_start, time_end;
    time_start = System.currentTimeMillis();    
    
    while(cObjetos!=2){    
    if(pInicio%10==0){
        pIzq=9;
    }else{
    pIzq=Integer.parseInt(datosEstructura.get(pInicio-1).toString());     }
    
    if(pInicio>89){
        pAba=9;
    }else{
        pAba=Integer.parseInt(datosEstructura.get(pInicio+10).toString());}
    
    if((pInicio-9)%10==0){
        pDer=9;
    }else{
        pDer=Integer.parseInt(datosEstructura.get(pInicio+1).toString()); }
    
    if(pInicio<10){
        pArr=9;
    }else{
    pArr=Integer.parseInt(datosEstructura.get(pInicio-10).toString());    }
    
    nicho.add(pIzq);
    nicho.add(pAba);
    nicho.add(pDer);
    nicho.add(pArr);  
    int[] l={-1,10,1,-10};
   
    
    for(int i=0;i<4;i++){
      eEvaluar=Integer.parseInt(nicho.get(i).toString());            
        if (eEvaluar!= 1 && eEvaluar!= 9){     
            nodo1=new double[7];
            nodo1[0]=pInicio+l[i]; //crece el pInicio (pos en el array)        
            nodo1[1]=kPadre;       //almacena la posicion en el arraypNodos del papa
            
            if ((eEvaluar==6) && noPaso1(kPadre,pInicio+l[i])) {//si no he pasado por ese nodo, evito contar 2 veces el mismo objeto
            nodo1[2]=cObjetos+1;
            nodo1[5]=nodo1[0];
            }else{
            nodo1[2]=cObjetos;
            nodo1[5]=pObEncontrado;
            }      
            
             if(cProteccion==1||eEvaluar==3){
                nodo1[3]=cPadre+1; //proteccion del padre 
                nodo1[6]=1;}//si lleva el traje se pone en 1
            else if(eEvaluar==0 || eEvaluar==2 || eEvaluar==6){
                nodo1[3]=cPadre+1;
                nodo1[6]=cProteccion;}
            else if(eEvaluar==4){            
                nodo1[3]=cPadre+3;
                nodo1[6]=cProteccion;}
            else if(eEvaluar==5){
                nodo1[3]=cPadre+6;
                nodo1[6]=cProteccion;}                  
         
            if (nodo1[2]==0){
            nodo1[3]=nodo1[3]+heuristicaAdmisible(pInicio+l[i],pObj1,pObj2); 
            }else if(nodo1[2]==1 && nodo1[5]==pObj1){
            nodo1[3]=nodo1[3]+heuristicaAdmisible(pInicio+l[i],pInicio+l[i],pObj2);
      
            }else if(nodo1[2]==1 && nodo1[5]==pObj2){
            nodo1[3]=nodo1[3]+heuristicaAdmisible(pInicio+l[i],pObj1,pInicio+l[i]); 
      
            }
            nodo1[4]=bNodos1.size();                      
            bNodos1.add(nodo1);    
            pila1.push(nodo1);
        }                                  
        }
    
        nicho.clear();   
        Stack<double[]> tmpStack1 = new Stack<double[]>();            
        tmpStack1=ordenaPila1(pila1);         
        pila1.clear();        
        for(int u=tmpStack1.size()-1;u>=0;u--){ 
        pila1.add(tmpStack1.get(u));       
        }     
             
        pInicio=(int)pila1.peek()[0];         
        cObjetos=(int)pila1.peek()[2];// cantidad de objetos       
        cPadre=(int)pila1.peek()[3]; //cuenta lo que lleva el padre
        cProteccion=(int)pila1.peek()[6]; //traje
        kPadre=(int)pila1.peek()[4]; //posicion arrayfinal
        pObEncontrado=(int)pila1.peek()[5];
        pila1.pop();                             
        k++;        
    }        

    time_end = System.currentTimeMillis();
    datosSalida.add(time_end - time_start);        
    datosSalida.add(k);  
    
    while(kPadre!=0){
        pPadre=(int)bNodos1.get(kPadre)[1];        
        datosSalida.add((int)bNodos1.get(kPadre)[0]);        
        kPadre=pPadre;
    }   
        datosSalida.add((int)bNodos1.get(kPadre)[1]);        
        datosSalida.add(2,datosSalida.size());    
    return datosSalida; //regresa tramas completas
    }    
}
