package by.it_academy.jd2.report_service.repository;

import by.it_academy.jd2.report_service.core.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, UUID> {

    @Query("SELECT r.status FROM ReportEntity AS r WHERE r.id = :id")
    String getStatusById(UUID id);
}
