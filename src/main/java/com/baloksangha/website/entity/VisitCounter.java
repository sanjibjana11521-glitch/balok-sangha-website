package com.baloksangha.website.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "site_visit_counter")
public class VisitCounter {

    @Id
    private Long id;

    @Column(name = "total_visits", nullable = false)
    private Long totalVisits;

    public VisitCounter() {}

    public VisitCounter(Long id, Long totalVisits) {
        this.id = id;
        this.totalVisits = totalVisits;
    }

    public Long getId() { return id; }
    public Long getTotalVisits() { return totalVisits; }

    public void setId(Long id) { this.id = id; }
    public void setTotalVisits(Long totalVisits) { this.totalVisits = totalVisits; }
}
