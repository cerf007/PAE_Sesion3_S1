package ni.edu.uam.ejercicio15.services;

import ni.edu.uam.ejercicio15.model.Documento;

import java.util.LinkedList;
import java.util.Queue;

public class ImpresoraService {
    private final Queue<Documento> colaImpresion = new LinkedList<>();

    public void agregarDocumento(Documento doc) {
        colaImpresion.offer(doc);
    }

    public Documento imprimirDocumento() {
        return colaImpresion.poll();
    }

    public Documento obtenerSiguiente() {
        return colaImpresion.peek();
    }

    public int getCantidadPendientes() {
        return colaImpresion.size();
    }

    public String getPendientesFormateado() {
        if (colaImpresion.isEmpty()) {
            return "No hay documentos pendientes de impresión.";
        }
        StringBuilder sb = new StringBuilder();
        int posicion = 1;
        for (Documento doc : colaImpresion) {
            sb.append(posicion++).append(". ").append(doc.toString()).append("\n");
        }
        return sb.toString();
    }
}
