package by.it_academy.jd2.flats_service.controller.resolver;

import by.it_academy.jd2.flats_service.core.dto.FlatsFilter;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class FlatsFilterResolver implements HandlerMethodArgumentResolver {

    private static final String AREA_FROM_PARAM = "area_from";

    private static final String AREA_TO_PARAM = "area_to";

    private static final String BEDROOMS_FROM_PARAM = "bedrooms_from";

    private static final String BEDROOMS_TO_PARAM = "bedrooms_to";

    private static final String FLOORS_PARAM = "floors";

    private static final String PRICE_FROM_PARAM = "price_from";

    private static final String PRICE_TO_PARAM = "price_to";

    private static final String PHOTO_PARAM = "photo";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return FlatsFilter.class.isAssignableFrom(parameter.getNestedParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest req,
                                  WebDataBinderFactory binderFactory) throws Exception {

        Integer areaFrom = Integer.valueOf(req.getParameter(AREA_FROM_PARAM));
        Integer areaTo = Integer.valueOf(req.getParameter(AREA_TO_PARAM));
        Integer bedroomsFrom = Integer.valueOf(req.getParameter(BEDROOMS_FROM_PARAM));
        Integer bedroomsTo = Integer.valueOf(req.getParameter(BEDROOMS_TO_PARAM));
        Integer floors = Integer.valueOf(req.getParameter(FLOORS_PARAM));
        Integer priceFrom = Integer.valueOf(req.getParameter(PRICE_FROM_PARAM));
        Integer priceTo = Integer.valueOf(req.getParameter(PRICE_TO_PARAM));
        Boolean photo = Boolean.valueOf(req.getParameter(PHOTO_PARAM));

        return new FlatsFilter(priceFrom, priceTo, bedroomsFrom, bedroomsTo, areaFrom, areaTo, floors, photo);
    }
}
