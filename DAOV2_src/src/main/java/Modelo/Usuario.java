
package Modelo;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class Usuario {
    private final Long id;
    private String nombre;
    private final String email;
    private String password;
    private final LocalDate fechaRegistro;
    private boolean activo;

    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PASSWORD_PATTERN = 
        Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,100}$");

    private Usuario(Long id, String nombre, String email, String password, 
                   LocalDate fechaRegistro, boolean activo) {
        this.id = id;
        this.nombre = validarNombre(nombre);
        this.email = validarEmail(email);
        this.password = validarPassword(password);
        this.fechaRegistro = Objects.requireNonNull(fechaRegistro, "Fecha de registro no puede ser nula");
        this.activo = activo;
    }

    public static class Builder {
        private String nombre;
        private String email;
        private String password;
        
        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        
        public Usuario build() {
            return new Usuario(null, nombre, email, password, LocalDate.now(), true);
        }
    }
    
    public static Usuario fromDatabase(Long id, String nombre, String email, 
                                     String password, LocalDate fechaRegistro, boolean activo) {
        return new Usuario(id, nombre, email, password, fechaRegistro, activo);
    }
    
    public static Usuario crearNuevo(String nombre, String email, String password) {
        return new Usuario(null, nombre, email, password, LocalDate.now(), true);
    }
    
    private static String validarNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        String nombreTrimmed = nombre.trim();
        if (nombreTrimmed.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (nombreTrimmed.length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder 100 caracteres");
        }
        return nombreTrimmed;
    }
    
    private static String validarEmail(String email) {
        Objects.requireNonNull(email, "El email no puede ser nulo");
        String emailLower = email.toLowerCase().trim();
        if (emailLower.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (!EMAIL_PATTERN.matcher(emailLower).matches()) {
            throw new IllegalArgumentException("Formato de email inválido: " + email);
        }
        if (emailLower.length() > 100) {
            throw new IllegalArgumentException("El email no puede exceder 100 caracteres");
        }
        return emailLower;
    }
    
    private static String validarPassword(String password) {
        Objects.requireNonNull(password, "La contraseña no puede ser nula");
        if (password.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }
        if (password.length() > 255) {
            throw new IllegalArgumentException("La contraseña no puede exceder 255 caracteres");
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException(
                "La contraseña debe contener al menos una mayúscula, una minúscula y un número");
        }
        return password;
    }
    
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public boolean isActivo() { return activo; }
    
    public void setNombre(String nombre) {
        this.nombre = validarNombre(nombre);
    }
    
    public void setPassword(String password) {
        this.password = validarPassword(password);
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public boolean esReciente() {
        return fechaRegistro.isAfter(LocalDate.now().minusMonths(1));
    }
    
    public boolean esEmailCorporativo() {
        return email.endsWith("@empresa.com") || email.endsWith("@corporacion.com");
    }
    
    public int antiguedadEnMeses() {
        return (int) java.time.temporal.ChronoUnit.MONTHS.between(fechaRegistro, LocalDate.now());
    }
    
    public boolean puedeCambiarPassword() {
        return antiguedadEnMeses() >= 1;
    }
    
    public boolean esValido() {
        return activo && fechaRegistro != null && email != null && !email.isEmpty();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
    
    @Override
    public String toString() {
        return String.format(
            "Usuario{id=%d, nombre='%s', email='%s', activo=%s, antiguedad=%d meses}",
            id, nombre, email, activo, antiguedadEnMeses()
        );
    }
    
    public Usuario withNombre(String nuevoNombre) {
        return new Usuario(this.id, nuevoNombre, this.email, this.password, this.fechaRegistro, this.activo);
    }
    
    public Usuario asInactivo() {
        return new Usuario(this.id, this.nombre, this.email, this.password, this.fechaRegistro, false);
    }
}
