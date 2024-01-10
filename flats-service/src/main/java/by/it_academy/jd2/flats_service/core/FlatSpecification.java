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

                if(filter.getAreaFrom() != null){
                    predicates.add(cb.equal(root.get("areaFrom"), filter.getAreaFrom()));
                }
                if(filter.getAreaTo() != null){
                    predicates.add(cb.equal(root.get("areaTo"), filter.getAreaTo()));
                }
                if(filter.getBedroomsFrom() != null){
                    predicates.add(cb.equal(root.get("bedroomsFrom"), filter.getBedroomsFrom()));
                }
                if(filter.getBedroomsTo() != null){
                    predicates.add(cb.equal(root.get("bedroomsTo"), filter.getBedroomsTo()));
                }
                if(filter.getFloors() != null){
                    predicates.add(cb.equal(root.get("floors"), filter.getFloors()));
                }
                if(filter.getPriceFrom() != null){
                    predicates.add(cb.equal(root.get("priceFrom"), filter.getPriceFrom()));
                }
                if(filter.getPriceTo() != null){
                    predicates.add(cb.equal(root.get("priceTo"), filter.getPriceTo()));
                }
                if(filter.getPhoto() != null){
                    predicates.add(cb.equal(root.get("photo"), filter.getPhoto()));
                }
                return cb.and(predicates.toArray(Predicate[]::new));
            }
        };
    }
}
