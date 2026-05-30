package com.gamermatch.jdbc;

import com.gamermatch.common.PagedList;

public interface ITeamService {
    Team create(TeamRequest request);
    Team getById(Long id);
    PagedList<Team> getAll();
    TeamMember addMember(Long teamId, Long userId);
    PagedList<TeamMember> getMembers(Long teamId);
}
