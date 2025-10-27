package central_controle_fogo.com.backend_central_controle_fogo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "update_date",nullable = false)
    private OffsetDateTime updatedAt;

    @Setter
    @Column(nullable = false)
    private boolean active;

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
        this.active = true;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}
