/*   
    Control
*/
package control;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Este metodo es el encargado de organizar las tareas entre las diferentes clases, este es el puente entre la clase vista y modelo
 * @version 1.0
 * @author Stephanya
 * @author Camilo
 */
public class Control {
    ArrayList datosFichero; //Declaracion de variables
    ArrayList datosSalida; 
    LectoEscrituraFichero miLectura;
    ValidaFichero miValida;
    model.Modelo miModelo;    
    boolean validoL;    
     
    /**
     * Metodo constructor de la clase 
     */
    public Control(){
    datosFichero=new ArrayList(); //Creacion de objetos
    datosSalida=new ArrayList();
    miLectura=new LectoEscrituraFichero();
    miValida=new ValidaFichero();
    miModelo=new model.Modelo();    
    }   
    
/**
 * Este metodo es el encargado de enviarle la orden a la clase lectoEscritura de leer un fichero
 * @return datosFichero conjunto de datos leidos
 */   
    public ArrayList lecturaFicheroC(){    
        datosFichero=miLectura.lecturaFicheroC(); //invoca al metodo lectura fichero
        validoL=miValida.validaFicheroC(datosFichero);  //Invoca al metodo validafichero       
        while (!validoL){ // si no es valida la informacion del fichero se solicita leer un nuevo archivo 
            JOptionPane.showMessageDialog(null,"Por favor verifique los datos a cargar","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);             
            lecturaFicheroC();   //Llamada al metodo
        }        
        return datosFichero; //si es valido entonces entrega los datos
    }
    
    public boolean validaF(ArrayList datos){
    boolean val;
        val=miValida.validaFicheroC(datos);
    
        if(val){
    return true;
    }else
        return false;    
    }
    
    
    public ArrayList calculaResultado(int busqueda, ArrayList datos){
        datosSalida=new ArrayList(); 
        
        switch(busqueda){
                case 1:
                    datosSalida=miModelo.busquedaAmplitud(datos);
                    break;
                case 2:
                    datosSalida=miModelo.busquedaCosto(datos);
                    break;
                case 3:
                    datosSalida=miModelo.busquedaProfundidad(datos);
                    break;
                case 4:
                    datosSalida=miModelo.busquedaAvara(datos);
                    break;
                case 5:
                    datosSalida=miModelo.busquedaA(datos);
                    break;
                default:
                    break;
        } 
        datosSalida=organizaArray(datosSalida);
        for(int u=0;u<datosSalida.size();u++){
        System.out.println("datosSalida "+datosSalida.get(u)); //Camino solucion       
        }
        return datosSalida;
    }
    
     public ArrayList organizaArray(ArrayList datos){
     ArrayList datosOrg=new ArrayList();
         
     datosOrg.add(datos.get(0));
     datosOrg.add(datos.get(1));
     datosOrg.add(datos.get(2));
     for(int t=datos.size()-1;t>2;t--){     
         datosOrg.add(datos.get(t));         
     }         
     return datosOrg;
     }
    
    
    
}
