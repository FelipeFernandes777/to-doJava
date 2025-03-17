package br.com.to_do.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity()
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String fullerName;
    @Column(nullable = false)
    private String email;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Boolean active = true;
    private LocalDateTime created_at;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updated_at;

    public User create(CreateUserDTO data) {
        this.fullerName = data.fullerName();
        this.email = data.email();

        return this;
    }

    public User update(UpdateUserDTO data) {
        this.fullerName = data.fullerName();
        this.email = data.email();

        return this;
    }

    public void disableUser() {
        this.active = !this.active;
    }
}
