package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        // 3️⃣ Cargar driver y conectarse
        String url = "jdbc:mysql://localhost:3306/estados";
        String usuario = "root";
        String contraseña = "1234"; // tu contraseña

        try {
            // Cargar driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conexión
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("✅ Conectado a la base de datos");

            Statement stmt = conexion.createStatement();

            // ---- INSERTS en orden de dependencia ----
            // 1. Estado
            stmt.executeUpdate("INSERT INTO Estado(noEstado, nombre, ubicacion) VALUES (1, 'Guanajuato', 'Centro')");

            // 2. Municipio
            stmt.executeUpdate("INSERT INTO Municipio(noEstado, cveMuni, nombre) VALUES (1, 1, 'León')");

            // 3. TipoLocalidad
            stmt.executeUpdate("INSERT INTO TipoLocalidad(tipo, descripcion) VALUES (1, 'Urbana')");

            // 4. Localidad
            stmt.executeUpdate("INSERT INTO Localidad(noEstado, cveMuni, cveLoc, tipo, nombre) VALUES (1, 1, 1, 1, 'Localidad A')");

            // 5. Genero
            stmt.executeUpdate("INSERT INTO Genero(cveGenero, descripcion) VALUES (1, 'Masculino')");

            // 6. Personas
            stmt.executeUpdate(
                    "INSERT INTO Personas(curp, nombre, domicilio, fechaNac, cveGenero, noEstado, cveMuni, cveLoc) " +
                            "VALUES ('ABCD123456XYZ', 'Juan Pérez', 'Calle Falsa 123', '2000-01-01', 1, 1, 1, 1)"
            );

            // ---- SELECT para probar ----
            ResultSet rs = stmt.executeQuery(
                    "SELECT p.nombre AS Persona, e.nombre AS Estado, m.nombre AS Municipio, l.nombre AS Localidad " +
                            "FROM Personas p " +
                            "JOIN Estado e ON p.noEstado = e.noEstado " +
                            "JOIN Municipio m ON p.noEstado = m.noEstado AND p.cveMuni = m.cveMuni " +
                            "JOIN Localidad l ON p.noEstado = l.noEstado AND p.cveMuni = l.cveMuni AND p.cveLoc = l.cveLoc"
            );

            System.out.println("\n--- Datos de Personas ---");
            while (rs.next()) {
                String persona = rs.getString("Persona");
                String estado = rs.getString("Estado");
                String municipio = rs.getString("Municipio");
                String localidad = rs.getString("Localidad");
                System.out.println(persona + " | " + estado + " | " + municipio + " | " + localidad);
            }

            // Cerrar
            rs.close();
            stmt.close();
            conexion.close();
            System.out.println("\n✅ Todo ejecutado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
