package Service;

import Modelo.Usuario;
import Persistencia.UsuarioDAO;
import java.util.List;
import java.util.Optional;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario registrarUsuario(String nombre, String email, String password) {
        Usuario nuevoUsuario = Usuario.crearNuevo(nombre, email, password);
        return usuarioDAO.save(nuevoUsuario);
    }

    public Optional<Usuario> obtenerUsuario(Long id) {
        return usuarioDAO.findById(id);
    }

    public List<Usuario> listarTodos() {
        return usuarioDAO.findAll();
    }

    public List<Usuario> buscarPorNombre(String nombre) {
        return usuarioDAO.findByNombre(nombre);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }

    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioDAO.update(usuario);
    }

    public boolean eliminarUsuario(Long id) {
        return usuarioDAO.delete(id);
    }

    public boolean desactivarUsuario(Long id) {
        return usuarioDAO.desactivar(id);
    }

    public boolean existeEmail(String email) {
        return usuarioDAO.existsByEmail(email);
    }

    public long contarUsuariosActivos() {
        return usuarioDAO.countActivos();
    }
}
