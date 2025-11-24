import java.util.List;
import java.util.Scanner;

import model.Usuarios;
import repository.UsuariosRepository;
public class Main{

    public static void main(String[] args) {
    
        Scanner scanner = new Scanner(System.in);
        UsuariosRepository usuariosRepository = new UsuariosRepository();
    
        int opcion;
    
        do {
            System.out.println("\n MENU");
            System.out.println("1. insertar usuario");
            System.out.println("2. listar usuarios");
            System.out.println("3. buscar usuario por id");
            System.out.println("4. actualizar usuario");
            System.out.println("5. eliminar usuario");
            System.out.println("6. salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcion) {
    
                case 1:
                    System.out.println("\nInsertar Usuario ");
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Edad: ");
                    Long edad = scanner.nextLong();
    
                    usuariosRepository.insertarUsuario(new Usuarios(nombre, edad));
                    System.out.println("Usuario insertado correctamente.");
                    break;
    
    
                case 2:
                    System.out.println("\nLista de Usuarios");
                    List<Usuarios> lista = usuariosRepository.listarUsuarios();
    
                    for (Usuarios u : lista) {
                        System.out.println("id: " + u.getId());
                        System.out.println("Nombre: " + u.getNombre());
                        System.out.println("Edad: " + u.getEdad());
                    }
                    break;
    
    
                case 3:
                    System.out.println("\n buscar usuario por id");
                    System.out.print("Ingrese id: ");
                    Long idBuscar = scanner.nextLong();
    
                    Usuarios encontrado = usuariosRepository.BusquedaId(idBuscar);
    
                    if (encontrado != null) {
                        System.out.println("id: " + encontrado.getId());
                        System.out.println("Nombre: " + encontrado.getNombre());
                        System.out.println("Edad: " + encontrado.getEdad());
                    } else {
                        System.out.println("No existe ese id");
                    }
                    break;
    
    
                case 4:
                    System.out.println("\nActualizar Usuario");
                    System.out.print("Ingrese id del usuario: ");
                    Long idActualizar = scanner.nextLong();
                    scanner.nextLine();
    
                    Usuarios usuarioActualizado = usuariosRepository.BusquedaId(idActualizar);
    
                    if (usuarioActualizado != null) {
                        System.out.println("Nombre actual: " + usuarioActualizado.getNombre());
                        System.out.println("Edad actual: " + usuarioActualizado.getEdad());
    
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nueva edad: ");
                        Long nuevaEdad = scanner.nextLong();
    
                        boolean actualizado = usuariosRepository.actualizarUsuario(
                                idActualizar,
                                nuevoNombre,
                                nuevaEdad);
    
                        if (actualizado) {
                            System.out.println("Usuario actualizado");
                        } else {
                            System.out.println("error");
                        }
                    } else {
                        System.out.println("No se encontró ese id");
                    }
                    break;
    
    
                case 5:
                    System.out.println("\nEliminar Usuario");
                    System.out.print("Ingrese ID del usuario: ");
                    Long idEliminar = scanner.nextLong();
    
                    Usuarios usuarioEliminar = usuariosRepository.BusquedaId(idEliminar);
    
                    if (usuarioEliminar != null) {
                        boolean eliminado = usuariosRepository.EliminarUsuario(idEliminar);
    
                        if (eliminado) {
                            System.out.println("Usuario eliminado correctamente.");
                        } else {
                            System.out.println("Error al eliminar.");
                        }
                    } else {
                        System.out.println("No existe ese id");
                    }
                    break;
    
    
                case 6:
                    System.out.println("Saliendo");
                    break;
    
                default:
                    System.out.println("esta opcion no existe");
            }
    
        } while (opcion != 6);
    
        scanner.close();
    }
}