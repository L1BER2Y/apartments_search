package by.it_academy.jd2.flats_service.service;

import by.it_academy.jd2.flats_service.core.FlatSpecification;
import by.it_academy.jd2.flats_service.core.dto.FlatDTO;
import by.it_academy.jd2.flats_service.core.dto.FlatsFilter;
import by.it_academy.jd2.flats_service.core.entity.FlatEntity;
import by.it_academy.jd2.flats_service.repository.FlatsRepository;
import by.it_academy.jd2.flats_service.service.api.IFlatsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FlatsService implements IFlatsService {

    private final FlatsRepository repository;

    public FlatsService(FlatsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<FlatDTO> getPage(FlatsFilter filter, Pageable pageable) {
        Page<FlatEntity> entityPage = this.repository.findAll(FlatSpecification.byFlatsFilter(filter), pageable);
        return entityPage.map(FlatsService::apply);
    }

    private static FlatDTO apply(FlatEntity entity) {
        FlatDTO flatDTO = new FlatDTO();
        flatDTO.setUuid(entity.getUuid());
        flatDTO.setDtCreate(entity.getDtCreate());
        flatDTO.setDtUpdate(entity.getDtUpdate());
        flatDTO.setOfferType(entity.getOfferType());
        flatDTO.setDescription(entity.getDescription());
        flatDTO.setBedrooms(entity.getBedrooms());
        flatDTO.setArea(entity.getArea());
        flatDTO.setPrice(entity.getPrice());
        flatDTO.setFloor(entity.getFloor());
        flatDTO.setPhotoUrls(entity.getPhotoUrls());
        flatDTO.setOriginalUrl(entity.getOriginalUrl());
        return flatDTO;
    }
}
