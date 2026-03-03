import java.io.*;
import java.util.*;

public class Gestion {
    static class Estudiante {
        String nombre;
        int edad;
        int nota;

        public Estudiante(String nombre, int edad, int nota) {
            this.nombre = nombre;
            this.edad = edad; 
            this.nota =  nota;
        }
        @Override
        public String toString() {
            return nombre + "," + edad + "," + nota;
        } 
    }

    public static void Verificacion(String nombreArchivo) {
        File archivo = new File (nombreArchivo);

        if (!archivo.exists()) {
            System.out.println("El archivo no existe. Creandolo...");

            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                pw.println("Ana,21,89");
                pw.println("Luis,23,92");
                pw.println("Marta,20,95");
                pw.println("Carlos,22,85");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo ya existe.");
        }
    }

    public static List<Estudiante> leerArchivo(String nombreArchivo) {
        List<Estudiante> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null ) {
                System.out.println(linea);

                String[] partes = linea.split(",");
                Estudiante e = new Estudiante(partes[0].trim(), Integer.parseInt(partes[1].trim()), Integer.parseInt(partes[2].trim()));
                lista.add(e);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static void mostrarLinea(String nombreArchivo, int Numero) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            int contador = 1;

            while ((linea = br.readLine()) != null) {
                if (contador == Numero) {
                    System.out.println("Línea " + Numero + ": " + linea);
                    return;
                }
                contador++;
            }

            System.out.println("Línea no encontrada. ");
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Estudiante> mezclaDirecta(List<Estudiante> lista1, List<Estudiante> lista2) {
        List<Estudiante> resultado = new ArrayList<>();
        int i = 0, j = 0;

        while (i < lista1.size() && j < lista2.size()) {
            if (lista1.get(i).nota <= lista2.get(j).nota) {
                resultado.add(lista1.get(i));
                i++;
            } else {
                resultado.add(lista2.get(j));
                j++;
            }
        }

        while (i < lista1.size()) {
            resultado.add(lista1.get(i));
            i++;
        }
        
        while (j < lista2.size()) {
            resultado.add(lista2.get(j));
            j++;
        }
        return resultado;
    }

    public static void CorridasNaturales(List<Estudiante> lista) {
        System.out.println("Corridas naturales"); 

        for (int i = 1; i < lista.size(); i++ ) {
            if (lista.get(i).nota < lista.get(i - 1).nota) {
            System.out.println("Nueva corrida");
            }
            System.out.println(lista.get(i));
        }
    }

    public static int Recursividad(List<Estudiante> lista, int indice) {
        if (indice >= lista.size()) {
            return 0;
        }

        if (lista.get(indice).nota >= 90) {
            return 1 + Recursividad(lista, indice + 1);
        } else {
            return Recursividad(lista, indice + 1);
        }
    }

    public static void main(String[] args) {
        
        String archivo = "estudiantes.txt";

        Verificacion(archivo);
        System.out.println("\nContenido del archivo:");
        List<Estudiante> estudiantes = leerArchivo(archivo);

        System.out.println("\nMostrar linea:");
        mostrarLinea(archivo, 2);

        System.out.println("\nMezcla directa:");

        List<Estudiante> bloque1 = Arrays.asList(new Estudiante("A", 20, 70), new Estudiante("B", 21, 80));
        List<Estudiante> bloque2 = Arrays.asList(new Estudiante("C", 22, 75), new Estudiante("D", 23, 90));
        List<Estudiante> fusion = mezclaDirecta(bloque1, bloque2);

        for (Estudiante e : fusion) {
        System.out.println(e);
        }

        CorridasNaturales(estudiantes);

        int cantidad = Recursividad(estudiantes, 0);
        System.out.println("\nCantidad de estudiantes con nota >= 90: " + cantidad);

    }
}
