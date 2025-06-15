
package ModeloArreglo;

import EstructurasListas.Doctor;
import java.util.Arrays;
import java.util.Comparator;
/**
 *
 * @author danie
 */
public class ArregloDoctor {
  private final Doctor[] doctores;
    private int count;

    public ArregloDoctor (int size) {
        doctores = new Doctor[size];
        count = 0;
    }

    public void agregarDoctor(Doctor doctor) {
        if (count < doctores.length) {
            doctores[count] = doctor;
            count++;
        } else {
            System.out.println("Arreglo lleno. No se puede agregar más doctores.");
        }
    }

    public void eliminarDoctor(String dni) {
        for (int i = 0; i < count; i++) {
            if (doctores[i].getDni().equals(dni)) {
                for (int j = i; j < count - 1; j++) {
                    doctores[j] = doctores[j + 1];
                }
                doctores[count - 1] = null;
                count--;
                System.out.println("Doctor eliminado con éxito.");
                return;
            }
        }
        System.out.println("Doctor no encontrado.");
    }

    public Doctor buscarDoctorPorDNI(String dni) {
        return buscarDoctorPorDNIRecursivo(doctores, 0, count - 1, dni);
    }

    private Doctor buscarDoctorPorDNIRecursivo(Doctor[] doctores, int inicio, int fin, String dni) {
        if (inicio > fin) {
            return null;
        }

        int medio = (inicio + fin) / 2;
        int comparacion = doctores[medio].getDni().compareTo(dni);

        if (comparacion == 0) {
            return doctores[medio];
        } else if (comparacion < 0) {
            return buscarDoctorPorDNIRecursivo(doctores, medio + 1, fin, dni);
        } else {
            return buscarDoctorPorDNIRecursivo(doctores, inicio, medio - 1, dni);
        }
    } 

    public void ordenarPorNombres() {
        Arrays.sort(doctores, 0, count, Comparator.comparing(Doctor::getNombres));
    }

    public void ordenarPorDNI() {
        Arrays.sort(doctores, 0, count, Comparator.comparing(Doctor::getDni));
    }

    public void mostrarDoctores() {
        for (int i = 0; i < count; i++) {
            System.out.println(doctores[i].getDni() + " - " + doctores[i].getNombres());
        }
    }
}
