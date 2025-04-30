package cotato.backend.domain;

import cotato.backend.domain.attribute.Age;
import cotato.backend.domain.attribute.Engagement;
import cotato.backend.domain.attribute.Generation;
import cotato.backend.domain.attribute.GrowthDesire;
import cotato.backend.domain.attribute.Name;
import cotato.backend.domain.attribute.Part;
import cotato.backend.domain.attribute.PhoneNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
    @Column(name = "id")
    private UUID id;

    @Embedded
    @Column(name = "name")
    private Name name;

    @Embedded
    @Column(name = "generation")
    private Generation generation;

    @Embedded
    @Column(name = "age")
    private Age age;

    @Column(name = "part")
    private Part part;

    @Embedded
    @Column(name = "engagement")
    private Engagement engagement;

    @Embedded
    @Column(name = "growth_desire")
    private GrowthDesire growthDesire;

    @Embedded
    @Column(name = "phone_number")
    private PhoneNumber phoneNumber;

    @CreationTimestamp
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

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
