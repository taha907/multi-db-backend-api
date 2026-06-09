package com.gamermatch.jdbc;

import com.gamermatch.common.PagedList;
import com.gamermatch.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TeamService implements ITeamService {

    private final TeamRepository teamRepository;
    private final TeamMemberRepository memberRepository;
    private final UserRepository userRepository;

    public TeamService(TeamRepository teamRepository, TeamMemberRepository memberRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Team create(TeamRequest request) {
        Team team = new Team();
        team.setName(request.getName());
        team.setGame(request.getGame());
        return teamRepository.insert(team);
    }

    @Override
    public Team getById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Takim bulunamadi: " + id));
    }

    @Override
    public PagedList<Team> getAll() {
        return new PagedList<>(teamRepository.findAll());
    }

    @Override
    public TeamMember addMember(Long teamId, Long userId) {
        if (!teamRepository.existsById(teamId)) {
            throw new ResourceNotFoundException("Takim bulunamadi: " + teamId);
        }
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Kullanici bulunamadi: " + userId);
        }
        TeamMember m = new TeamMember();
        m.setTeamId(teamId);
        m.setUserId(userId);
        return memberRepository.insert(m);
    }

    @Override
    public PagedList<TeamMember> getMembers(Long teamId) {
        return new PagedList<>(memberRepository.findByTeamId(teamId));
    }
}
