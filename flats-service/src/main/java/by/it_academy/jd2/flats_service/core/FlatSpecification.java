package by.it_academy.jd2.flats_service.core;

import by.it_academy.jd2.flats_service.core.dto.FlatsFilter;
import by.it_academy.jd2.flats_service.core.entity.FlatEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class FlatSpecification {

    public static Specification<FlatEntity> byFlatsFilter(FlatsFilter filter){
        return new Specification<FlatEntity>() {
            @Override
            public Predicate toPredicate(Root<FlatEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if(filter.getPriceFrom() != null){
                    predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getPriceFrom()));
                }
                if(filter.getPriceTo() != null){
                    predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getPriceTo()));
                }
                if(filter.getBedroomsFrom() != null){
                    predicates.add(cb.greaterThanOrEqualTo(root.get("bedrooms"), filter.getBedroomsFrom()));
                }
                if(filter.getBedroomsTo() != null){
                    predicates.add(cb.lessThanOrEqualTo(root.get("bedrooms"), filter.getBedroomsTo()));
                }
                if(filter.getAreaFrom() != null){
                    predicates.add(cb.greaterThanOrEqualTo(root.get("area"), filter.getAreaFrom()));
                }
                if(filter.getAreaTo() != null){
                    predicates.add(cb.lessThanOrEqualTo(root.get("area"), filter.getAreaTo()));
                }
                if(filter.getFloors() != null && filter.getFloors().length != 0){
                    predicates.add(root.<Integer>get("floor").in(List.of(filter.getFloors())));
                }
                if(filter.getPhoto() != null && filter.getPhoto()){
                    predicates.add(root.get("photoUrls").isNotNull());
                }
                return cb.and(predicates.toArray(Predicate[]::new));
            }
        };
    }
}
