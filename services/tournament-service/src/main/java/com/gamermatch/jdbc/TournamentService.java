package com.gamermatch.jdbc;

import com.gamermatch.common.PagedList;
import com.gamermatch.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TournamentService implements ITournamentService {

    private final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public Tournament create(TournamentRequest request) {
        Tournament t = new Tournament();
        t.setName(request.getName());
        t.setGame(request.getGame());
        t.setStatus("KAYIT");
        return tournamentRepository.insert(t);
    }

    @Override
    public Tournament getById(Long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turnuva bulunamadi: " + id));
    }

    @Override
    public PagedList<Tournament> getAll() {
        return new PagedList<>(tournamentRepository.findAll());
    }
}
