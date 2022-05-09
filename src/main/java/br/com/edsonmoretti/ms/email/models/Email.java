package br.com.edsonmoretti.ms.email.models;

import br.com.edsonmoretti.ms.email.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Email {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime createdAt;
    private Status status;

    @Column(columnDefinition = "TEXT")
    private String error;

    public void setSent(boolean b) {
        this.status = b ? Status.SENT : Status.ERROR;
    }
}
