package com.baloksangha.website.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "club_members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(length = 100)
    private String role;

    @Column(name = "display_order")
    private Integer displayOrder = 0;

    public Member() {}

    public Member(String name, String role, Integer displayOrder) {
        this.name = name;
        this.role = role;
        this.displayOrder = displayOrder;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public Integer getDisplayOrder() { return displayOrder; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
}
