package by.shershen.parser_service.repository;

import by.shershen.parser_service.core.entity.FlatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FlatsRepository extends JpaRepository<FlatEntity, UUID> {
}
