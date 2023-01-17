package com.example.generationuniquecode.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="codes_table")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CodesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String code;
}
