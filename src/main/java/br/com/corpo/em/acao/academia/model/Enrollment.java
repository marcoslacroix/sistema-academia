package br.com.corpo.em.acao.academia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start")
    private LocalDateTime start;

    @Column(name = "end")
    private LocalDateTime end;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "enrollment_locked")
    @Builder.Default
    private boolean enrollmentLocked = false;

    @Column(name = "start_locked")
    private LocalDateTime startLocked;

    @Column(name = "end_locked")
    private LocalDateTime endLocked;

    @Column(name = "days_locked")
    private Integer daysLocked;

    @Column(name = "start_return")
    private LocalDateTime startReturn;

    @Column(name = "end_return")
    private LocalDateTime endReturn;

    @Column(name = "description")
    private String description;

    @Column(name = "student_id")
    private Long studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;
}
