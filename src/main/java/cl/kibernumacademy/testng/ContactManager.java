package cl.kibernumacademy.testng;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cl.kibernumacademy.testng.contact.Contact;

public class ContactManager {
    private List<Contact> contactos = new ArrayList<>();

    public void agregarContacto(Contact contacto) {
        contactos.add(contacto);
    }

    public boolean desactivarContacto(String nombre) {
        for (Contact c : contactos) {
            if (c.getNombre().equals(nombre)) {
                c.desactivar();
                return true;
            }
        }
        return false;
    }

    public List<Contact> filtrarPorEstado(Contact.Estado estado) {
        return contactos.stream()
                .filter(c -> c.getEstado() == estado)
                .collect(Collectors.toList());
    }

    public List<Contact> getContactos() {
        return contactos;
    }
}
