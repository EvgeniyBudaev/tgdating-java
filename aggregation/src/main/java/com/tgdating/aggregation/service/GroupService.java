package com.tgdating.aggregation.service;

public interface GroupService {
    void assignGroup(String userId, String groupId);

    void deleteGroup(String userId, String groupId);
}
