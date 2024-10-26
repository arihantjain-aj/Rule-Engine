package com.ruleEngine.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    private String type; // "operator" or "operand"
    private Node left;
    private Node right;
    private String value; // e.g., "AND", "OR", "age > 30"

    public Node(String type, Node left, Node right, String value) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.value = value;
    }
}


