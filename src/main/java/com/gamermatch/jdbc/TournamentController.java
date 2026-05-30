package com.gamermatch.jdbc;

import com.gamermatch.common.ApiResponse;
import com.gamermatch.common.PagedList;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final ITournamentService tournamentService;

    public TournamentController(ITournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @PostMapping
    public ApiResponse<Tournament> create(@Valid @RequestBody TournamentRequest request) {
        return ApiResponse.ok(tournamentService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<Tournament> getById(@PathVariable Long id) {
        return ApiResponse.ok(tournamentService.getById(id));
    }

    @GetMapping
    public ApiResponse<PagedList<Tournament>> getAll() {
        return ApiResponse.ok(tournamentService.getAll());
    }
}
