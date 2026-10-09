package com.techlab.articulo;
import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.ArticuloAlimenticio;
import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.model.Categoria;

public class App {
    public static void main(String[] args) throws Exception {
        var scanner = new java.util.Scanner(System.in);
        ArrayList<Articulo> articulos = new ArrayList<>();
        ArrayList<Categoria> categorias = new ArrayList<>();
        
        preCargarCategorias(categorias);
       

        do {
        System.out.println("============================================================");
        System.out.println("    SISTEMA DE ARTÍCULOS - CLASE 4 (HERENCIA Y TO_STRING)");
        System.out.println("============================================================");
        System.out.println("1 - Ingresar artículo");
        System.out.println("2 - Listar artículos");
        System.out.println("3 - Consutar Articulo");
        System.out.println("4 - Modificar Articulo");
        System.out.println("5 - Eliminar Articulo");
        System.out.println("6 - Listar Categorías");
        System.out.println("7 - Salir");
        System.out.print("============================================================");
        System.out.print("\nIngrese una opción: ");
        int opcion = scanner.nextInt();
        
        switch (opcion) {
            case 1:
                ingresarArticulo(scanner, articulos, categorias);
                break;
            case 2:
                listarArticulos(articulos);
                break;
            case 3:
                consultarArticulo(scanner, articulos);
                break;
            case 4:
                modificarArticulo(scanner, articulos, categorias);
                break;
            case 5:
                eliminarArticulo(scanner, articulos);
                break;
            case 6:
                listarCategorias(categorias);
                break;
            case 7:
                System.out.println("Saliendo del programa...");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Opción inválida. Intente nuevamente.");

        }


        }while (true);
    }

    

    public static void preCargarCategorias(ArrayList<Categoria> categorias) {
        categorias.add(new Categoria(1, "Electrónica", "Productos tecnológicos y electrónicos"));
        categorias.add(new Categoria(2, "Periféricos", "Accesorios para computadora")); 
        categorias.add(new Categoria(3, "Alimentos", "Productos alimenticios"));
        categorias.add(new Categoria(4, "Limpieza", "Artículos de limpieza del hogar"));
    }

    public static void listarCategorias(ArrayList<Categoria> categorias) {
        System.out.println("=== LISTA DE CATEGORÍAS ===");
        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
    }

    public static Articulo buscarArticuloPorCodigo(ArrayList<Articulo> articulos, int codigo) {
        for (Articulo articulo : articulos) {
            if (articulo.getCodigo() == codigo) {
                return articulo;
            }
        }
        return null; 
    }

    public static Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias, int codigo) {
        for (Categoria categoria : categorias) {
            if (categoria.getCodigo() == codigo) {
                return categoria;
            }
        }
        return null; 
    }

    public static Categoria pedirCategoriaExistente(Scanner scanner, ArrayList<Categoria> categorias) {
        while (true) {
            System.out.print("Ingrese el código de la categoría: ");
            int codigoCategoria = scanner.nextInt();
            Categoria categoria = buscarCategoriaPorCodigo(categorias, codigoCategoria);
            if (categoria != null) {
                return categoria;
            } else {
                System.out.println("Categoría no encontrada. Intente nuevamente.");
            }
        }
    }


    public static int leetEntero(Scanner scanner, String mensaje) {
        int numero;
        
        while (true) {
            System.out.print(mensaje);
          try {
                numero = Integer.parseInt(scanner.nextLine());
                break; 
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.nextLine(); 
            }
        }
        return numero;
    }


    public static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
        int numero;
        while (true) {
            numero = leetEntero(scanner, mensaje);
            if (numero >= 0) {
                break; 
            } else {
                System.out.println("El número no puede ser negativo. Intente nuevamente.");
            }
        }
        return numero;
    }

    public static double leerDoubleNoNegativo(Scanner scanner, String mensaje) {
        double numero;
        while (true) {
            System.out.print(mensaje);
            try {
                numero = Double.parseDouble(scanner.nextLine());
                if (numero >= 0) {
                    break; 
                } else {
                    System.out.println("El número no puede ser negativo. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
            }
        }
        return numero;
    }


    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        String input;
        while (true) {
            System.out.print(mensaje);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                break; 
            } else {
                System.out.println("La entrada no puede estar vacía. Intente nuevamente.");
            }
        }
        return input;
    }


    public static void ingresarArticulo(Scanner scanner, ArrayList<Articulo> articulos, ArrayList<Categoria> categorias) {
         int tipoArticulo;
         
        do {
            
        System.out.println("---INGRESAR ARTÍCULO---");
        System.out.println("1 - Artículo electrónico");
        System.out.println("2 - Artículo alimenticio");
        System.out.print("Seleccione el tipo de artículo: ");
        tipoArticulo = leerEnteroNoNegativo(scanner, "Seleccione el tipo de artículo: ") ;
        scanner.nextLine();

        }while (tipoArticulo != 1 && tipoArticulo != 2);

        Categoria categoria = pedirCategoriaExistente(scanner, categorias);
        String nombre = leerTextoNoVacio(scanner, "Ingrese el nombre del artículo: ");
        double precio = leerDoubleNoNegativo(scanner, "Ingrese el precio del artículo: ");
        listarCategorias(categorias);
        int codigo = leerEnteroNoNegativo(scanner, "Ingrese el código del artículo: ");

        if (tipoArticulo == 1) {
            int garantiaMeses = leerEnteroNoNegativo(scanner, "Ingrese la garantía en meses: ");
            ArticuloElectronico articuloElectronico = new ArticuloElectronico(codigo, nombre, precio, categoria, garantiaMeses);
            articulos.add(articuloElectronico);
            System.out.println("Se ha ingresado un artículo electrónico con éxito.");
            System.out.println(articuloElectronico);
        } else if (tipoArticulo == 2) {
            int diasParaVencimiento = leerEnteroNoNegativo(scanner, "Ingrese los días para vencimiento: ");
            ArticuloAlimenticio articuloAlimenticio = new ArticuloAlimenticio(codigo, nombre, precio, categoria, diasParaVencimiento);
            articulos.add(articuloAlimenticio);
            System.out.println("Se ha ingresado un artículo alimenticio con éxito.");
            System.out.println(articuloAlimenticio);

        }

    }


    public static void listarArticulos(ArrayList<Articulo> articulos) {
            
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos registrados.");
            return;
        }
        System.out.println("=== LISTA DE ARTÍCULOS ===");
        for (Articulo articulo : articulos) {
            System.out.println(articulo);
        }
    }

    public static void consultarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        int codigo = leerEnteroNoNegativo(scanner, "Ingrese el código del artículo a consultar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);
        if (articulo != null) {
            System.out.println(articulo);
            System.out.println("Detalle específico: " + articulo.getDetalleEspecifico());
        } else {
            System.out.println("El artículo no existe.");
        }
    }


    public static void modificarArticulo(Scanner scanner, ArrayList<Articulo> articulos, ArrayList<Categoria> categorias) {
        
        if(articulos.isEmpty()) {
            System.out.println("No hay artículos registrados para modificar.");
            return;
        }
        
        
        int codigo = leerEnteroNoNegativo(scanner, "Ingrese el código del artículo a modificar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);
        if (articulo != null) {
            System.out.println("Artículo encontrado: " + articulo);
            String nuevoNombre = leerTextoNoVacio(scanner, "Ingrese el nuevo nombre del artículo: ");
            double nuevoPrecio = leerDoubleNoNegativo(scanner, "Ingrese el nuevo precio del artículo: ");
            Categoria nuevaCategoria = pedirCategoriaExistente(scanner, categorias);
            articulo.setNombre(nuevoNombre);
            articulo.setPrecio(nuevoPrecio);
            articulo.setCategoria(nuevaCategoria);

            if (articulo instanceof ArticuloElectronico) {
                int nuevaGarantia = leerEnteroNoNegativo(scanner, "Ingrese la nueva garantía en meses: ");
                ((ArticuloElectronico) articulo).setGarantiaMeses(nuevaGarantia);
            } else if (articulo instanceof ArticuloAlimenticio) {
                int nuevosDiasParaVencimiento = leerEnteroNoNegativo(scanner, "Ingrese los nuevos días para vencimiento: ");
                ((ArticuloAlimenticio) articulo).setDiasParaVencimiento(nuevosDiasParaVencimiento);
            }

            System.out.println("Artículo modificado correctamente.");
        } else {
            System.out.println("El artículo no existe.");
        }
    }


    public static void eliminarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        if(articulos.isEmpty()) {
            System.out.println("No hay artículos registrados para eliminar.");
            return;
        }
        
        int codigo = leerEnteroNoNegativo(scanner, "Ingrese el código del artículo a eliminar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);
        if (articulo != null) {
             System.out.println("Artículo: ");
             System.out.println(articulo);
             System.out.println("eliminado correctamente");
            articulos.remove(articulo);

           
        } else {
            System.out.println("El artículo no existe.");
        }
    }


   

}