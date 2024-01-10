package by.it_academy.jd2.flats_service.service;

import by.it_academy.jd2.flats_service.core.FlatSpecification;
import by.it_academy.jd2.flats_service.core.dto.FlatsFilter;
import by.it_academy.jd2.flats_service.core.dto.PageOfFlatDTO;
import by.it_academy.jd2.flats_service.core.entity.FlatEntity;
import by.it_academy.jd2.flats_service.repository.FlatsRepository;
import by.it_academy.jd2.flats_service.service.api.IFlatsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class FlatsService implements IFlatsService {

    private final FlatsRepository repository;

    public FlatsService(FlatsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<FlatEntity> getPage(FlatsFilter filter, PageOfFlatDTO page) {
        return this.repository.findAll(FlatSpecification.byFlatsFilter(filter), PageRequest.of(page.getNumber(), page.getSize()));
    }

}
