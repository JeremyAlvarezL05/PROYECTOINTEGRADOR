package Ordenamientos;

import EstructurasListas.Pacientes;
import EstructurasListas.*;

public class OrdenarListas {

    public static ListaEnlazada OrdenarPorNombresDescedente(ListaEnlazada a) {
        Nodo k;
        for (Nodo i = a.getIni(); i != null; i = i.getSig()) {
            k = i;
            for (Nodo j = i.getSig(); j != null; j = j.getSig()) {
                if (j.getPaciente().getNombres().compareTo(k.getPaciente().getNombres()) > 0) {
                    k = j;
                }
            }      
            Pacientes aux = i.getPaciente();
            i.setPaciente(k.getPaciente());
            k.setPaciente(aux);
        }
        return a;
    }

    public static ListaEnlazada OrdenarPorNombres(ListaEnlazada lista) {
        lista.setIni(mergeSort(lista.getIni()));
        return lista;
    }

    public static int[] Seleccion(int[] a) {
        int k;
        for (int i = 0; i < a.length; i++) {
            k = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] > a[k]) {
                    k = j;
                }
            }      
            int aux = a[i];
            a[i] = a[k];
            a[k] = aux;
        }
        return a;
    }

    private static Nodo mergeSort(Nodo head) {
        if (head == null || head.getSig() == null) {
            return head;
        }

        Nodo middle = getMiddle(head);
        Nodo nextOfMiddle = middle.getSig();
        middle.setSig(null);

        Nodo left = mergeSort(head);
        Nodo right = mergeSort(nextOfMiddle);

        return sortedMerge(left, right);
    }

    private static Nodo sortedMerge(Nodo left, Nodo right) {
        Nodo result = null;
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        if (left.getDoctor().getNombres().compareToIgnoreCase(right.getDoctor().getNombres()) <= 0) {
            result = left;
            result.setSig(sortedMerge(left.getSig(), right));
        } else {
            result = right;
            result.setSig(sortedMerge(left, right.getSig()));
        }

        return result;
    }

    private static Nodo getMiddle(Nodo head) {
        if (head == null) {
            return head;
        }

        Nodo slow = head;
        Nodo fast = head.getSig();

        while (fast != null) {
            fast = fast.getSig();
            if (fast != null) {
                slow = slow.getSig();
                fast = fast.getSig();
            }
        }

        return slow;
    }
}
