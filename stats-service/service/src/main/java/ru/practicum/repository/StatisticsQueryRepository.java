package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.model.RequestHitEntity;
import ru.practicum.model.RequestStatsView;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsQueryRepository extends JpaRepository<RequestHitEntity, Long> {

    @Query("select new ru.practicum.model.RequestStatsView(e.app, e.uri, count(e.ip)) " +
            "from RequestHitEntity e " +
            "where e.timestamp between :start and :end " +
            "group by e.app, e.uri " +
            "order by count(e.ip) desc"
    )
    List<RequestStatsView> getViewStatsByDateRange(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("select new ru.practicum.model.RequestStatsView(e.app, e.uri, count(e.ip)) " +
            "from RequestHitEntity e " +
            "where e.timestamp between :start and :end " +
            "and e.uri in :uris " +
            "group by e.app, e.uri " +
            "order by count(e.ip) desc"
    )
    List<RequestStatsView> getViewStatsByDateRange(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("uris") List<String> uri
    );

    @Query("select new ru.practicum.model.RequestStatsView(e.app, e.uri, count(distinct e.ip)) " +
            "from RequestHitEntity e " +
            "where e.timestamp between :start and :end " +
            "group by e.app, e.uri " +
            "order by count(e.ip) desc"
    )
    List<RequestStatsView> getViewStatsByDateRangeWithUniqueIP(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("select new ru.practicum.model.RequestStatsView(e.app, e.uri, count(distinct e.ip)) " +
            "from RequestHitEntity e " +
            "where e.timestamp between :start and :end " +
            "and e.uri in :uris " +
            "group by e.app, e.uri " +
            "order by count(e.ip) desc"
    )
    List<RequestStatsView> getViewStatsByDateRangeWithUniqueIP(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("uris") List<String> uri
    );
}