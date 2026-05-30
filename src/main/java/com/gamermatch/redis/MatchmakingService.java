package com.gamermatch.redis;

import com.gamermatch.common.BadRequestException;
import com.gamermatch.common.PagedList;
import org.springframework.stereotype.Service;

@Service
public class MatchmakingService {

    private final MatchQueueRepository queueRepository;
    private final IMatchStrategy matchStrategy;

    public MatchmakingService(MatchQueueRepository queueRepository, IMatchStrategy matchStrategy) {
        this.queueRepository = queueRepository;
        this.matchStrategy = matchStrategy;
    }

    public QueuePlayer joinQueue(Long playerId, String game, String rank) {
        QueuePlayer player = new QueuePlayer(playerId, game, rank);
        queueRepository.addToQueue(player);
        return player;
    }

    public void leaveQueue(Long playerId, String game) {
        queueRepository.removePlayer(game, playerId);
    }

    public PagedList<QueuePlayer> getQueue(String game) {
        return new PagedList<>(queueRepository.getQueue(game));
    }

    public LobbyRoom tryMatch(String game) {
        QueuePlayer p1 = queueRepository.popFromQueue(game);
        QueuePlayer p2 = queueRepository.popFromQueue(game);
        if (p1 == null || p2 == null) {
            if (p1 != null) queueRepository.addToQueue(p1);
            if (p2 != null) queueRepository.addToQueue(p2);
            throw new BadRequestException("Eslesme icin yeterli oyuncu yok");
        }
        return matchStrategy.createMatch(game, queueRepository, p1, p2);
    }
}
