package com.tgdating.aggregation.controller;

import com.tgdating.aggregation.dto.request.*;
import com.tgdating.aggregation.dto.response.*;
import com.tgdating.aggregation.model.PaginationEntity;
import com.tgdating.aggregation.model.ProfileBlockEntity;
import com.tgdating.aggregation.model.ProfileComplaintEntity;
import com.tgdating.aggregation.service.ProfileService;
import com.tgdating.aggregation.shared.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    private final ProfileService profileService;
    private static final Path BASE_PROJECT_PATH = Paths.get(System.getProperty("user.dir"));

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ResponseProfileCreateDto> createProfile(
            @ModelAttribute RequestProfileCreateDto requestProfileCreateDto) {
        System.out.println("controller createProfile: " + requestProfileCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.create(requestProfileCreateDto));
    }

    @PutMapping
    public ResponseEntity<ResponseProfileUpdateDto> updateProfile(
            @ModelAttribute RequestProfileUpdateDto requestProfileUpdateDto) {
        System.out.println("controller updateProfile: " + requestProfileUpdateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.update(requestProfileUpdateDto));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteProfile(
            @RequestBody RequestProfileDeleteDto requestProfileDeleteDto) {
        System.out.println("controller deleteProfile: " + requestProfileDeleteDto);
        profileService.delete(requestProfileDeleteDto.getSessionId());
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.builder().success(true).build());
    }

    @GetMapping("/list")
    public ResponseEntity<PaginationEntity<List<ResponseProfileListGetDto>>> getProfileList(
            @RequestParam String sessionId,
            @RequestParam(defaultValue = Constants.DEFAULT_SEARCH_GENDER) String searchGender,
            @RequestParam(defaultValue = Constants.DEFAULT_LOOKING_FOR) String lookingFor,
            @RequestParam(defaultValue = Constants.DEFAULT_AGE_FROM) Integer ageFrom,
            @RequestParam(defaultValue = Constants.DEFAULT_AGE_TO) Integer ageTo,
            @RequestParam(defaultValue = Constants.DEFAULT_DISTANCE) Double distance,
            @RequestParam(defaultValue = Constants.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = Constants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude
    ) {
        System.out.println("controller getProfileList sessionId: " + sessionId);
        RequestProfileListGetDto requestProfileListGetDto = RequestProfileListGetDto.builder()
                .sessionId(sessionId)
                .searchGender(searchGender)
                .lookingFor(lookingFor)
                .ageFrom(ageFrom)
                .ageTo(ageTo)
                .distance(distance)
                .page(page)
                .size(size)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfileList(requestProfileListGetDto));
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<ResponseProfileBySessionIdGetDto> getProfileBySessionID(
            @PathVariable String sessionId,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude
    ) {
        System.out.println("controller getProfileBySessionID sessionId: " + sessionId);
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getBySessionID(sessionId, latitude, longitude));
    }

    @GetMapping("/detail/{sessionId}")
    public ResponseEntity<ResponseProfileDetailGetDto> getProfileDetail(
            @PathVariable String sessionId,
            @RequestParam String viewedSessionId,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude
    ) {
        System.out.println("controller getProfileDetail sessionId: " + sessionId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(profileService.getProfileDetail(sessionId, viewedSessionId, latitude, longitude));
    }

    @GetMapping("/short/{sessionId}")
    public ResponseEntity<ResponseProfileShortInfoGetDto> getProfileShortInfo(
            @PathVariable String sessionId,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude
    ) {
        System.out.println("controller getProfileShortInfo sessionId: " + sessionId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(profileService.getProfileShortInfo(sessionId, latitude, longitude));
    }

    @DeleteMapping("/images/{id}")
    public ResponseEntity<ResponseDto> deleteImage(
            @PathVariable Long id
    ) {
        System.out.println("controller deleteImage id: " + id);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.builder().success(true).build());
    }

    @GetMapping("/filter/{sessionId}")
    public ResponseEntity<ResponseProfileFilterDto> getFilterBySessionID(
            @PathVariable String sessionId,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude
    ) {
        System.out.println("controller getFilterBySessionID sessionId: " + sessionId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(profileService.getFilterBySessionID(sessionId, latitude, longitude));
    }

    @GetMapping(value = "/{sessionId}/images/{fileName}", produces = "image/webp")
    public byte[] getImageBySessionID(@PathVariable String sessionId, @PathVariable String fileName) throws IOException {
        Path staticFolderPath = BASE_PROJECT_PATH.resolve("src/main/resources/static/images");
        String filePath = String.format("%s/%s/%s", staticFolderPath, sessionId, fileName);
        System.out.println("getImage filePath: " + filePath);
        return Files.readAllBytes(Paths.get(filePath));
    }

    @PutMapping("/navigators")
    public ResponseEntity<ResponseProfileNavigatorDto> updateCoordinates(
            @RequestBody RequestProfileNavigatorUpdateDto requestProfileNavigatorUpdateDto
    ) {
        System.out.println("controller updateNavigator: " + requestProfileNavigatorUpdateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(profileService.updateCoordinates(requestProfileNavigatorUpdateDto));
    }

    @PutMapping("/filters")
    public ResponseEntity<ResponseProfileFilterDto> updateFilter(
            @RequestBody RequestProfileFilterUpdateDto requestProfileFilterUpdateDto
    ) {
        System.out.println("controller updateFilter: " + requestProfileFilterUpdateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(profileService.updateFilter(requestProfileFilterUpdateDto));
    }

    @PostMapping("/likes")
    public ResponseEntity<ResponseProfileLikeDto> addLike(
            @RequestBody RequestProfileLikeAddDto requestProfileLikeAddDto
    ) {
        System.out.println("controller addLike: " + requestProfileLikeAddDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(profileService.addLike(requestProfileLikeAddDto));
    }

    @PostMapping("/blocks")
    public ResponseEntity<ProfileBlockEntity> addBlock(
            @RequestBody RequestProfileBlockAddDto requestProfileBlockAddDto
    ) {
        System.out.println("controller addBlock: " + requestProfileBlockAddDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(profileService.addBlock(requestProfileBlockAddDto));
    }

    @PostMapping("/complaints")
    public ResponseEntity<ProfileComplaintEntity> addComplaint(
            @RequestBody RequestProfileComplaintAddDto requestProfileComplaintAddDto
    ) {
        System.out.println("controller addComplaint: " + requestProfileComplaintAddDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(profileService.addComplaint(requestProfileComplaintAddDto));
    }
}
