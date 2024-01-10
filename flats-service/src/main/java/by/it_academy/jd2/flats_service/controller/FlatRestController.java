package by.it_academy.jd2.flats_service.controller;

import by.it_academy.jd2.flats_service.core.dto.FlatsFilter;
import by.it_academy.jd2.flats_service.core.dto.PageOfFlatDTO;
import by.it_academy.jd2.flats_service.core.entity.FlatEntity;
import by.it_academy.jd2.flats_service.service.api.IFlatsService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/flats")
public class FlatRestController {

    private final IFlatsService service;


    public FlatRestController(IFlatsService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public PageOfFlatDTO getAllFlatByFilter(@RequestParam(name = "price_from", required = false) String priceFrom,
                                            @RequestParam(name = "price_to", required = false) String priceTo,
                                            @RequestParam(name = "bedrooms_from", required = false) String bedroomsFrom,
                                            @RequestParam(name = "bedrooms_to", required = false) String bedroomsTo,
                                            @RequestParam(name = "area_from", required = false) String areaFrom,
                                            @RequestParam(name = "area_to", required = false) String areaTo,
                                            @RequestParam(name = "floors", required = false) String floors,
                                            @RequestParam(name = "photo", required = false) String photo,
                                            @RequestParam(name = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "size", defaultValue = "20") Integer size
    ) {
        PageOfFlatDTO pageable = new PageOfFlatDTO(page, size);

        FlatsFilter flatsFilter = new FlatsFilter().setPriceFrom(priceFrom)
                .setPriceFrom(priceTo)
                .setBedroomsFrom(bedroomsFrom)
                .setBedroomsTo(bedroomsTo)
                .setAreaFrom(areaFrom)
                .setAreaTo(areaTo)
                .setFloors(floors)
                .setPhoto(photo);

        Page<FlatEntity> servicePage = this.service.getPage(flatsFilter, pageable);

        return new PageOfFlatDTO(servicePage.getNumber(), servicePage.getSize(),
                servicePage.getTotalPages(), servicePage.getTotalElements(), servicePage.isFirst(),
                servicePage.getNumberOfElements(), servicePage.isLast(), servicePage.getContent());
    }

}
