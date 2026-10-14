package by.shershen.parser_service.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "users", name = "flats")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    @Column(name = "offer_type")
    @Enumerated(EnumType.STRING)
    private OfferType offerType;

    private String description;

    private Integer bedrooms;

    private Integer area;

    private Integer price;

    private Integer floor;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "photo_urls", columnDefinition = "text array")
    private String[] photoUrls;

    @Column(name = "original_url", unique = true)
    private String originalUrl;
}
