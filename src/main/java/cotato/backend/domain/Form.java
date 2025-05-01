package cotato.backend.domain;

import cotato.backend.domain.attribute.Age;
import cotato.backend.domain.attribute.Engagement;
import cotato.backend.domain.attribute.Generation;
import cotato.backend.domain.attribute.GrowthDesire;
import cotato.backend.domain.attribute.Name;
import cotato.backend.domain.attribute.Part;
import cotato.backend.domain.attribute.PhoneNumber;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "forms")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Embedded
    @Column(name = "name", nullable = false)
    private Name name;

    @Embedded
    @Column(name = "generation", nullable = false)
    private Generation generation;

    @Embedded
    @Column(name = "age", nullable = false)
    private Age age;

    @Enumerated(EnumType.STRING)
    @Column(name = "part", nullable = false)
    private Part part;

    @Embedded
    @Column(name = "engagement", nullable = false)
    private Engagement engagement;

    @Embedded
    @Column(name = "growth_desire", nullable = false)
    private GrowthDesire growthDesire;

    @Embedded
    @Column(name = "phone_number", nullable = false)
    private PhoneNumber phoneNumber;

    @CreationTimestamp
    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;

    @OneToMany(mappedBy = "form", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private final List<Like> likes = new ArrayList<>();

    public Form(String name, int generation, int age, Part part, int engagement, int growthDesire,
                String phoneNumber) {
        this.name = new Name(name);
        this.generation = new Generation(generation);
        this.age = new Age(age);
        this.part = part;
        this.engagement = new Engagement(engagement);
        this.growthDesire = new GrowthDesire(growthDesire);
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }
}
