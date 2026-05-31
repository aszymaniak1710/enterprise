package lab.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import lab.dto.ComplaintDTO;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        String baseUri = "http://localhost:8080/Server-1.0-SNAPSHOT/api/complaints";

        String idZasobu = "153";

        String status = client.target(baseUri + "/" + idZasobu + "/status")
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);
        System.out.println("--- Punkt 4 ---");
        System.out.println("Status skargi o ID " + idZasobu + ": " + status);

        List<ComplaintDTO> allComplaints = client.target(baseUri)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<ComplaintDTO>>() {});

        System.out.println("\n--- Punkt 7.a: Wszystkie skargi ---");
        for (ComplaintDTO c : allComplaints) {
            System.out.println("[" + c.getId() + "] Autor: " + c.getAuthor() + ", Tekst: " + c.getComplaintText() + ", Status: " + c.getStatus());
        }

        ComplaintDTO oneComplaint = client.target(baseUri + "/" + idZasobu)
                .request(MediaType.APPLICATION_JSON)
                .get(ComplaintDTO.class);

        System.out.println("\n--- Punkt 7.b: Pobranie pojedynczej skargi ---");
        System.out.println("Pobrana skarga ID " + idZasobu + " -> " + oneComplaint.getComplaintText() + " [" + oneComplaint.getStatus() + "]");

        oneComplaint.setStatus("closed");
        client.target(baseUri + "/" + idZasobu)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(oneComplaint, MediaType.APPLICATION_JSON));
        System.out.println("\n--- Punkt 7.c ---");
        System.out.println("Wysłano żądanie PUT w celu zamknięcia skargi o ID " + idZasobu);

        List<ComplaintDTO> openComplaints = client.target(baseUri)
                .queryParam("status", "open")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<ComplaintDTO>>() {});

        System.out.println("\n--- Punkt 7.d: Wszystkie OTWARTE skargi po modyfikacji ---");
        for (ComplaintDTO c : openComplaints) {
            System.out.println("[" + c.getId() + "] Autor: " + c.getAuthor() + ", Status: " + c.getStatus());
        }

        client.close();
    }
}