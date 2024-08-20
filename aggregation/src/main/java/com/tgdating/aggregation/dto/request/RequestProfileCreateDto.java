package com.tgdating.aggregation.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class RequestProfileCreateDto {
    @NotNull
    private String sessionId;
    @NotNull
    private String displayName;
    @NotNull
    private LocalDate birthday;
    @NotNull
    private String gender;
    @NotNull
    private String searchGender;
    private String location;
    private String description;
    private Double height;
    private Double weight;
    private String lookingFor;
    private String telegramId;
    private String telegramFirstName;
    private String telegramLastName;
    private String telegramUsername;
    private String telegramLanguageCode;
    private Boolean telegramAllowsWriteToPm;
    private String telegramQueryId;
    private Long telegramChatId;
    private String latitude;
    private String longitude;
    private Byte ageFrom;
    private Byte ageTo;
    private Double distance;
    private Integer page;
    private Integer size;
    private MultipartFile[] image;
}
