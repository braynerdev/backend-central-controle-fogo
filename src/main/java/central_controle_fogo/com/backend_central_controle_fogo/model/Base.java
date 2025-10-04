package central_controle_fogo.com.backend_central_controle_fogo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.Instant;
import java.util.Date;


@Getter
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public abstract class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "update_date",nullable = false)
    private Date updatedAt;

    @Setter()
    @Column(nullable = false)
    private boolean active;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Date.from(Instant.now());
        this.updatedAt = Date.from(Instant.now());
        this.active = true;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Date.from(Instant.now());
    }
}
