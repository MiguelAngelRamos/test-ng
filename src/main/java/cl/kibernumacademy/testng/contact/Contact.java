package cl.kibernumacademy.testng.contact;

public class Contact {
    private String nombre;
    private String correo;
    private Estado estado;

    public enum Estado {
        ACTIVO, INACTIVO
    }

    public Contact(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        this.estado = Estado.ACTIVO;
    }

    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public Estado getEstado() { return estado; }
    public void desactivar() { this.estado = Estado.INACTIVO; }
}
