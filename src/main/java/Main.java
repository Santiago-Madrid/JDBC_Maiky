import java.util.Scanner;

import model.Usuarios;
import repository.UsuariosRepository;

public class Main {
    public static void main(String[] args) {
        /* Creamos el scanner */
        Scanner scanner = new Scanner(System.in);

        /* Instanciamos nuestra clase */
        UsuariosRepository usuariosRepository = new UsuariosRepository();

        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la edad del usuario: ");
        Long edad = scanner.nextLong();

        /* Creamos un usuario nuevo con los datos solicitados previamente */
        Usuarios usuario = new Usuarios(nombre, edad);

        /* LLamamos nuestro metodo insertar usuario pasandole el usuario nuevo creado */
        usuariosRepository.insertarUsuario(usuario);

        /* -------------------------------------------------------------- */

        System.out.println("Lista de usuarios");
        
        
        System.out.print("Ingrese el id del usuario: ");
        Long IdEncontrado = scanner.nextLong();
        Usuarios nombreEncontrado = usuariosRepository.BusquedaId(IdEncontrado);

     if (nombreEncontrado != null) {
         System.out.println("Usuario encontrado: id: " + nombreEncontrado.getId()
                 + ", nombre: " + nombreEncontrado.getNombre()
                 + ", edad: " + nombreEncontrado.getEdad());
     }
     scanner.close();
    }

}