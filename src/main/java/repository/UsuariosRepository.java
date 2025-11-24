package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import db.Conexion;
import model.Usuarios;

public class UsuariosRepository {

    /**
     * Metodo para insertar un usuario
     */
    public void insertarUsuario(Usuarios usuario) {
        /* Creamos consulta sql */
        String sql = "INSERT INTO USUARIOS (NOMBRE, EDAD) VALUES (?, ?)";

        /* Intentamos acceder a nuestra conexion */
        try (Connection connection = Conexion.getConnection()) {
            /* Preparamos nuestra consulta */
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            /* Preparamos nuestra consulta enviando parametros solicitados */
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setLong(2, usuario.getEdad());

            /* Ejecutamos nuestra consulta sql */
            preparedStatement.executeUpdate();

            /* Mensaje informativo */
            System.out.println("Usuario insertado correctamente");

            /* Atrapamos cualquier error y lo imprimimos en consola */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para listar todos los usuarios registrados
     */
    public List<Usuarios> listarUsuarios() {
        /* Creamos consulta sql */
        String sql = "SELECT * FROM USUARIOS";
        /* Creamos variable donde guardaremos los usuarios (Está vacía) */
        List<Usuarios> usuarios = new ArrayList<>();

        /* Intentamos acceder a nuestra conexion */
        try (Connection connection = Conexion.getConnection()) {
            /* Creamos el estado de la consulta y la ejecutamos */
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            /* Recorremos el resultado para agregar 1 por 1 los usuarios a la lista */
            while (resultSet.next()) {
                usuarios.add(new Usuarios(
                        resultSet.getLong("ID"),
                        resultSet.getString("NOMBRE"),
                        resultSet.getLong("EDAD")));
            }
            
            
            /* Atrapamos cualquier error */
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Retornamos la lista de usuarios */
        return usuarios;
    }



    public Usuarios BusquedaId(Long id) {
        
        String sql = "SELECT * FROM USUARIOS WHERE id = ?";
        
        Usuarios usuario = null;

        try (Connection connection = Conexion.getConnection()) {

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuarios(
                    resultSet.getLong("ID"),
                    resultSet.getString("NOMBRE"),
                    resultSet.getLong("EDAD"));
            } 
        }catch (Exception e) {
            e.printStackTrace();
        }
            
        return usuario;
}


    public boolean actualizarUsuario(Long id, String nuevoNombre, Long nuevaEdad) {

        String sql = "UPDATE USUARIOS SET NOMBRE = ?, EDAD = ? WHERE ID = ?";

        try (Connection connection = Conexion.getConnection();
            PreparedStatement resultSet = connection.prepareStatement(sql)) {

             resultSet.setLong(1, id);
             resultSet.setString(2, nuevoNombre);
             resultSet.setLong(3, nuevaEdad);
        return resultSet.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
}

public boolean EliminarUsuario(Long id) {

    String sql = "DELETE FROM USUARIOS WHERE ID = ?";

    try (Connection connection = Conexion.getConnection();
         PreparedStatement resultSet = connection.prepareStatement(sql)) {

        resultSet.setLong(1, id); 

        return resultSet.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}




