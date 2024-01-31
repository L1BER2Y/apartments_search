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
    @ResponseBody
    public Page<FlatDTO> getAllFlatByFilter(FlatsFilter flatsFilter,
                                            @RequestParam(name = "page", defaultValue = "1") Integer page,
                                            @RequestParam(name = "size", defaultValue = "20") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return this.service.getPage(flatsFilter, pageable);
    }

}
