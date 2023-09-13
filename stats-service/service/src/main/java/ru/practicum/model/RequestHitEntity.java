package ru.practicum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "stats")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestHitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String uri;
    @Column
    private LocalDateTime timestamp;
    @Column
    private String ip;
    @Column
    private String app;
}