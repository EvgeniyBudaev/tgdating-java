package com.tgdating.aggregation.repository;

import com.tgdating.aggregation.dto.request.*;
import com.tgdating.aggregation.model.*;

import java.util.List;

public interface ProfileRepository {
    ProfileEntity create(RequestProfileCreateDto requestProfileCreateDto);

    void updateLastOnline(String sessionId);

    ProfileImageEntity addImage(RequestProfileImageAddDto requestProfileImageAddDto);

    ProfileNavigatorEntity addNavigator(RequestProfileNavigatorAddDto requestProfileNavigatorAddDto);

    ProfileNavigatorEntity updateNavigator(RequestProfileNavigatorUpdateDto requestProfileNavigatorUpdateDto);

    ProfileFilterEntity addFilter(RequestProfileFilterAddDto requestProfileFilterAddDto);

    ProfileTelegramEntity addTelegram(RequestProfileTelegramAddDto requestProfileTelegramAddDto);

    PaginationEntity<List<ProfileListEntity>> findProfileList(RequestProfileListGetDto requestProfileListGetDto);

    ProfileEntity findBySessionID(String sessionId);

    List<ProfileImageEntity> findImageListBySessionID(String sessionId);

    List<ProfileImageEntity> findImagePublicListBySessionID(String sessionId);

    ProfileNavigatorEntity findNavigatorBySessionID(String sessionId);

    ProfileFilterEntity findFilterBySessionID(String sessionId);

    ProfileTelegramEntity findTelegramBySessionID(String sessionId);
}
