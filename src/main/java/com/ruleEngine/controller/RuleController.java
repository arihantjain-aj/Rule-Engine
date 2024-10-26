package com.ruleEngine.controller;

import com.ruleEngine.model.Node;
import com.ruleEngine.model.Rule;
import com.ruleEngine.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rules")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @PostMapping("/create")
    public Rule createRule(@RequestBody String ruleString) {
        return ruleService.createRule(ruleString);
    }

    @GetMapping("/{id}")
    public Rule getRule(@PathVariable Long id) {
        return ruleService.getRuleById(id);
    }

    @PostMapping("/parse")
    public Node parseRule(@RequestBody String ruleString) {
        return ruleService.parseRuleToAST(ruleString);
    }
}
