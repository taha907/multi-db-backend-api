package com.gamermatch.jdbc;

import com.gamermatch.common.PagedList;

public interface ITournamentService {
    Tournament create(TournamentRequest request);
    Tournament getById(Long id);
    PagedList<Tournament> getAll();
}
