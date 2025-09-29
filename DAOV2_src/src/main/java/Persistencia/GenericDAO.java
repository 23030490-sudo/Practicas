package Persistencia;

import java.sql.*;
import java.util.*;

public abstract class GenericDAO<T, ID> {
    protected final Connection connection;

    protected GenericDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract T save(T entity);
    public abstract T update(T entity);
    public abstract boolean delete(ID id);
    public abstract Optional<T> findById(ID id);
    public abstract List<T> findAll();
    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;

    // Métodos utilitarios
    protected int executeUpdate(String sql, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            setParameters(stmt, params);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error en update: " + sql, e);
        }
    }

    protected Long executeInsert(String sql, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setParameters(stmt, params);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("Insert fallido, no se generó ID");
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
                throw new RuntimeException("No se generó clave primaria");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en insert: " + sql, e);
        }
    }

    protected Optional<T> executeQuerySingleResult(String sql, Object... params) {
        List<T> results = executeQueryMultipleResults(sql, params);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    protected List<T> executeQueryMultipleResults(String sql, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            setParameters(stmt, params);
            try (ResultSet rs = stmt.executeQuery()) {
                List<T> results = new ArrayList<>();
                while (rs.next()) {
                    results.add(mapResultSetToEntity(rs));
                }
                return results;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en query: " + sql, e);
        }
    }

    protected boolean exists(String sql, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            setParameters(stmt, params);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en exists: " + sql, e);
        }
    }

    protected long count(String sql, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            setParameters(stmt, params);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getLong(1);
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en count: " + sql, e);
        }
    }

    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }
}
