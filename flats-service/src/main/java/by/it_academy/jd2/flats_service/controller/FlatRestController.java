package by.it_academy.jd2.flats_service.controller;

import by.it_academy.jd2.flats_service.core.dto.FlatDTO;
import by.it_academy.jd2.flats_service.core.dto.FlatsFilter;
import by.it_academy.jd2.flats_service.service.api.IFlatsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/flats")
public class FlatRestController {

    private final IFlatsService service;


    public FlatRestController(IFlatsService service) {
        this.service = service;
    }

    @GetMapping
    public Page<FlatDTO> getAllFlatByFilter(@RequestParam(name = "price_from", required = false) Integer priceFrom,
                                            @RequestParam(name = "price_to", required = false) Integer priceTo,
                                            @RequestParam(name = "bedrooms_from", required = false) Integer bedroomsFrom,
                                            @RequestParam(name = "bedrooms_to", required = false) Integer bedroomsTo,
                                            @RequestParam(name = "area_from", required = false) Integer areaFrom,
                                            @RequestParam(name = "area_to", required = false) Integer areaTo,
                                            @RequestParam(name = "floors", required = false) Integer[] floors,
                                            @RequestParam(name = "photo", required = false) Boolean photo,
                                            @RequestParam(name = "page", defaultValue = "1") Integer page,
                                            @RequestParam(name = "size", defaultValue = "20") Integer size
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);

        FlatsFilter flatsFilter = new FlatsFilter()
                .setPriceFrom(priceFrom)
                .setPriceTo(priceTo)
                .setBedroomsFrom(bedroomsFrom)
                .setBedroomsTo(bedroomsTo)
                .setAreaFrom(areaFrom)
                .setAreaTo(areaTo)
                .setFloors(floors)
                .setPhoto(photo);
        return this.service.getPage(flatsFilter, pageable);
    }

}
