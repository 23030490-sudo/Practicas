package Persistencia;

public class UsuarioDAOException extends RuntimeException {
    public UsuarioDAOException(String message) {
        super(message);
    }
    public UsuarioDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
