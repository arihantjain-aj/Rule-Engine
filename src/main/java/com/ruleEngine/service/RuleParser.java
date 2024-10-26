package com.ruleEngine.service;


import com.ruleEngine.model.Node;
import java.util.Stack;

public class RuleParser {

    public Node createAST(String ruleString) {
        Stack<Node> operatorStack = new Stack<>();
        Stack<Node> operandStack = new Stack<>();

        String[] tokens = ruleString.split(" ");
        for (String token : tokens) {
            if (token.equals("(")) {
                operatorStack.push(new Node("operator", null, null, token));
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().getValue().equals("(")) {
                    processOperator(operatorStack, operandStack);
                }
                operatorStack.pop(); // Remove the "(" from the stack
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek().getValue()) >= precedence(token)) {
                    processOperator(operatorStack, operandStack);
                }
                operatorStack.push(new Node("operator", null, null, token));
            } else {
                // If the token is an operand, create a node and push it to the operand stack
                operandStack.push(new Node("operand", null, null, token));
            }
        }

        // Pop all the remaining operators in the stack and process them
        while (!operatorStack.isEmpty()) {
            processOperator(operatorStack, operandStack);
        }

        // The final node on the operand stack should be the root of the AST
        return operandStack.pop();
    }

    private void processOperator(Stack<Node> operatorStack, Stack<Node> operandStack) {
        Node operatorNode = operatorStack.pop();
        Node rightOperand = operandStack.pop();
        Node leftOperand = operandStack.pop();

        // Attach operands to the operator node
        operatorNode.setRight(rightOperand);
        operatorNode.setLeft(leftOperand);

        // Push the newly formed subtree back to the operand stack
        operandStack.push(operatorNode);
    }

    private boolean isOperator(String token) {
        return token.equals("AND") || token.equals("OR");
    }

    private int precedence(String operator) {
        return switch (operator) {
            case "AND" -> 2;
            case "OR" -> 1;
            default -> 0;
        };
    }
}
