package by.shershen.parser_service.repository;

import by.shershen.parser_service.core.entity.FlatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface FlatsRepository extends JpaRepository<FlatEntity, UUID> {

    @Query("select f.originalUrl from FlatEntity f where f.originalUrl in :originalUrls")
    Set<String> findExistingOriginalUrls(@Param("originalUrls") Collection<String> originalUrls);
}
