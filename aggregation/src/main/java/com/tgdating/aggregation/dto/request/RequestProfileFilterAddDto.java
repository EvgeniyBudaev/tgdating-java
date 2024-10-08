package com.tgdating.aggregation.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestProfileFilterAddDto {
    private String sessionId;
    private String searchGender;
    private String lookingFor;
    private Integer ageFrom;
    private Integer ageTo;
    private Double distance;
    private Integer page;
    private Integer size;
}
