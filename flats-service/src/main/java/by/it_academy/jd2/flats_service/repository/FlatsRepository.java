package by.it_academy.jd2.flats_service.repository;

import by.it_academy.jd2.flats_service.core.entity.FlatEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface FlatsRepository extends JpaRepository<FlatEntity, UUID> {
    Page<FlatEntity> findAll(Specification<FlatEntity> filter, Pageable pageable);
}
