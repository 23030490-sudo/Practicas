package Persistencia;

import Modelo.Usuario;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UsuarioDAO extends GenericDAO<Usuario, Long> {

    public UsuarioDAO(Connection connection) {
        super(connection);
    }

    private static final String SQL_INSERT =
            "INSERT INTO usuarios (nombre, email, password, fecha_registro, activo) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE usuarios SET nombre = ?, password = ?, activo = ?, fecha_actualizacion = CURRENT_TIMESTAMP WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id = ?";
    private static final String SQL_FIND_BY_ID =
            "SELECT id, nombre, email, password, fecha_registro, activo FROM usuarios WHERE id = ?";
    private static final String SQL_FIND_ALL =
            "SELECT id, nombre, email, password, fecha_registro, activo FROM usuarios ORDER BY nombre";
    private static final String SQL_FIND_BY_EMAIL =
            "SELECT id, nombre, email, password, fecha_registro, activo FROM usuarios WHERE email = ?";
    private static final String SQL_FIND_BY_NOMBRE =
            "SELECT id, nombre, email, password, fecha_registro, activo FROM usuarios WHERE nombre LIKE ? ORDER BY nombre";
    private static final String SQL_EXISTS_BY_EMAIL =
            "SELECT 1 FROM usuarios WHERE email = ?";
    private static final String SQL_DESACTIVAR =
            "UPDATE usuarios SET activo = FALSE, fecha_actualizacion = CURRENT_TIMESTAMP WHERE id = ?";
    private static final String SQL_COUNT_ACTIVOS =
            "SELECT COUNT(*) FROM usuarios WHERE activo = TRUE";

    @Override
    public Usuario save(Usuario usuario) {
        if (existsByEmail(usuario.getEmail())) {
            throw new UsuarioDAOException("El email ya está registrado: " + usuario.getEmail());
        }
        Long generatedId = executeInsert(SQL_INSERT,
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                Date.valueOf(usuario.getFechaRegistro()),
                usuario.isActivo()
        );
        return Usuario.fromDatabase(
                generatedId,
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getFechaRegistro(),
                usuario.isActivo()
        );
    }

    @Override
    public Usuario update(Usuario usuario) {
        if (usuario.getId() == null) {
            throw new UsuarioDAOException("El usuario debe tener un ID para ser actualizado");
        }
        int affectedRows = executeUpdate(SQL_UPDATE,
                usuario.getNombre(),
                usuario.getPassword(),
                usuario.isActivo(),
                usuario.getId()
        );
        if (affectedRows == 0) {
            throw new UsuarioDAOException("Usuario no encontrado para actualizar: " + usuario.getId());
        }
        return usuario;
    }

    @Override
    public boolean delete(Long id) {
        return executeUpdate(SQL_DELETE, id) > 0;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return executeQuerySingleResult(SQL_FIND_BY_ID, id);
    }

    @Override
    public List<Usuario> findAll() {
        return executeQueryMultipleResults(SQL_FIND_ALL);
    }

    public Optional<Usuario> findByEmail(String email) {
        return executeQuerySingleResult(SQL_FIND_BY_EMAIL, email);
    }

    public List<Usuario> findByNombre(String nombre) {
        return executeQueryMultipleResults(SQL_FIND_BY_NOMBRE, "%" + nombre + "%");
    }

    public boolean existsByEmail(String email) {
        return exists(SQL_EXISTS_BY_EMAIL, email);
    }

    public boolean desactivar(Long id) {
        return executeUpdate(SQL_DESACTIVAR, id) > 0;
    }

    public long countActivos() {
        return count(SQL_COUNT_ACTIVOS);
    }

    @Override
    protected Usuario mapResultSetToEntity(ResultSet rs) throws SQLException {
        return Usuario.fromDatabase(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getDate("fecha_registro").toLocalDate(),
                rs.getBoolean("activo")
        );
    }
}
