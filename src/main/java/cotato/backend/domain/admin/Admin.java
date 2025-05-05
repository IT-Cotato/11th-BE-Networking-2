package cotato.backend.domain.admin;

import cotato.backend.domain.application.enums.Part;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="admin")
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    @Column(name = "admin_name")
    private String name;

    @Column(name = "admin_phoneNumber")
    private String phoneNumber;

    @Column(name = "admin_generation")
    private int generation;

    @Column(name = "admin_part")
    private Part part;
}
