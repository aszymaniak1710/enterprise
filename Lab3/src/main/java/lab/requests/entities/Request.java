package lab.requests.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate requestData;
    private String requestText;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRequestData() {
        return requestData;
    }

    public void setRequestData(LocalDate requestData) {
        this.requestData = requestData;
    }

    public String getRequestText() {
        return requestText;
    }

    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }
}