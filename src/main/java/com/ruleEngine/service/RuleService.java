package com.ruleEngine.service;

import com.ruleEngine.model.Node;
import com.ruleEngine.model.Rule;
import com.ruleEngine.repository.RuleRepository;
import com.ruleEngine.exception.RuleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService {
    @Autowired
    private RuleRepository ruleRepository;

    public Rule createRule(String ruleString) {
        Rule rule = new Rule(ruleString);
        return ruleRepository.save(rule);
    }

    public Node parseRuleToAST(String ruleString) {
        RuleParser parser = new RuleParser();
        return parser.createAST(ruleString);
    }

    public Rule getRuleById(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new RuleNotFoundException("Rule not found with ID: " + id));
    }
}
