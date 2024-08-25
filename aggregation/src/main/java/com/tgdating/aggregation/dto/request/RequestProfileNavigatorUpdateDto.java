package com.tgdating.aggregation.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestProfileNavigatorUpdateDto {
    private String sessionId;
    private Double latitude;
    private Double longitude;
}
