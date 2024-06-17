package by.shershen.flats_service.service;

import by.shershen.flats_service.core.FlatSpecification;
import by.shershen.flats_service.core.converters.api.IFlatConverter;
import by.shershen.flats_service.core.dto.FlatDTO;
import by.shershen.flats_service.core.dto.FlatsFilter;
import by.shershen.flats_service.core.entity.FlatEntity;
import by.shershen.flats_service.repository.FlatsRepository;
import by.shershen.flats_service.service.api.IFlatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlatsService implements IFlatsService {

    private final FlatsRepository repository;
    private final IFlatConverter flatConverter;

    @Override
    public Page<FlatDTO> getPage(FlatsFilter filter, Pageable pageable) {
        Page<FlatEntity> entityPage = this.repository.findAll(FlatSpecification.byFlatsFilter(filter), pageable);
        List<FlatDTO> flatDTOList = entityPage.stream()
                .map(flatConverter::convertFlatEntityToDTO)
                .toList();
        return new PageImpl<>(flatDTOList, entityPage.getPageable(), entityPage.getTotalElements());
    }

}
