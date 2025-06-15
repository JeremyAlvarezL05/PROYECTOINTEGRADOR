package Ordenamientos;
import EstructurasListasCitas.Nodo;
import EstructurasListasCitas.ListaEnlazada;
import EstructurasListasCitas.Cita;
public class OrdernarListasCitas {
    
     public static ListaEnlazada AgruparPorNombreDoctor(ListaEnlazada a){
        Nodo k;
        for (Nodo i = a.getIni(); i != null; i = i.sig) {
            k = i;
            for (Nodo j = i.sig; j != null; j = j.sig) {
                if (j.cita.getNombreDoctor().compareTo(k.cita.getNombreDoctor()) < 0) {
                    k = j;
                }
            }
            Cita aux = i.cita;
            i.cita = k.cita;
            k.cita = aux;
        }
        return a;
    }
    
public static ListaEnlazada OrdenarPorNombres(ListaEnlazada lista) {
        if (lista == null || lista.getIni() == null) {
            return lista;
        }
        Nodo sorted = null; 
        Nodo current = lista.getIni(); 

        while (current != null) {
            Nodo next = current.sig; 
            sorted = sortedInsert(sorted, current); 
            current = next;
        }
        lista.setIni(sorted);
        return lista;
    }
    private static Nodo sortedInsert(Nodo sorted, Nodo newNode) {
        if (sorted == null || sorted.cita.getNombrePaciente().compareTo(newNode.cita.getNombrePaciente()) >= 0) {
            newNode.sig = sorted;
            return newNode;
        } else {
            Nodo current = sorted;
            while (current.sig != null && current.sig.cita.getNombrePaciente().compareTo(newNode.cita.getNombrePaciente()) < 0) {
                current = current.sig;
            }
            newNode.sig = current.sig;
            current.sig = newNode;
        }
        return sorted;
    }


  public static int[] Seleccion(int a[]){
      int k;
      for(int i=0; i<a.length;i++){
          k=i;
          for(int j=(i+1); j<a.length;j++){
              if(a[j]>a[k]){
                  k=j;
              }
          }//fin 2do for      
          int aux=a[i];
          a[i]=a[k];
          a[k]=aux;
      }
      return a;
  }
}