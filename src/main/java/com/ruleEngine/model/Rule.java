package com.ruleEngine.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String ruleString;

    // Constructors, getters, and setters
    public Rule() {}

    public Rule(String ruleString) {
        this.ruleString = ruleString;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRuleString(String ruleString) {
        this.ruleString = ruleString;
    }
}
