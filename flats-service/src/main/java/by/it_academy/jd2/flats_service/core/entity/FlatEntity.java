package by.it_academy.jd2.flats_service.core.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "users", name = "flats")
public class FlatEntity {

    @Id
    private UUID uuid;

    private LocalDateTime dtDate;

    private LocalDateTime dtUpdate;

    private OfferType offerType;

    private String description;

    private int bedrooms;

    private int area;

    private int price;

    private int floor;

    @Type(StringArrayType.class)
    @Column(name = "photo_urls", columnDefinition = "text[]")
    private String[] photoUrls;

    private String originalUrl;

}
