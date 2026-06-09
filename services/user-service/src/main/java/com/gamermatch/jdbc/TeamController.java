package com.gamermatch.jdbc;

import com.gamermatch.common.ApiResponse;
import com.gamermatch.common.PagedList;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final ITeamService teamService;

    public TeamController(ITeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ApiResponse<Team> create(@Valid @RequestBody TeamRequest request) {
        return ApiResponse.ok(teamService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<Team> getById(@PathVariable Long id) {
        return ApiResponse.ok(teamService.getById(id));
    }

    @GetMapping
    public ApiResponse<PagedList<Team>> getAll() {
        return ApiResponse.ok(teamService.getAll());
    }

    @PostMapping("/{teamId}/members/{userId}")
    public ApiResponse<TeamMember> addMember(@PathVariable Long teamId, @PathVariable Long userId) {
        return ApiResponse.ok(teamService.addMember(teamId, userId));
    }

    @GetMapping("/{teamId}/members")
    public ApiResponse<PagedList<TeamMember>> getMembers(@PathVariable Long teamId) {
        return ApiResponse.ok(teamService.getMembers(teamId));
    }
}
