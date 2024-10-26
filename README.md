Task 1 Rule Engine with AST
Github Url: arihantjain-aj/Rule-Engine
Step 1: Start the Spring Boot Application
1.	Run the Application: Make sure your Spring Boot application is up and running. You can do this by executing the main method in RuleEngineApplication.java or using a command in your terminal: mvn spring-boot:run
2.	Confirm the Server is Running: By default, the application should be running on http://localhost:8080. You can confirm this by visiting this URL in your web browser. You should see a basic Spring Boot welcome page or a 404 error.
Step 2: Use Postman to Test API Endpoints
1. Create a Rule
•	Endpoint: POST /api/rules/create
•	Method: POST
•	URL: http://localhost:8080/api/rules/create
•	Headers:
o	Content-Type: application/json
•	Body (Raw JSON):
{
    "ruleString": "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)"
}

Expected Response:
{
    "id": 1,
    "ruleString": "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)"
}
2. 
2. Parse the Rule to AST
•	Endpoint: POST /api/rules/parse
•	Method: POST
•	URL: http://localhost:8080/api/rules/parse
•	Headers:
o	Content-Type: application/json
•	Body (Raw JSON):
{
    "ruleString": "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)"
}

Expected Response (AST representation):
{
    "type": "operator",
    "left": {
        "type": "operator",
        "left": {
            "type": "operand",
            "value": "age > 30"
        },
        "right": {
            "type": "operand",
            "value": "department = 'Sales'"
        },
        "value": "AND"
    },
    "right": {
        "type": "operator",
        "left": {
            "type": "operand",
            "value": "age < 25"
        },
        "right": {
            "type": "operand",
            "value": "department = 'Marketing'"
        },
        "value": "AND"
    },
    "value": "OR"
}

3. Evaluate the Rule against User Data
•	Endpoint: POST /api/rules/evaluate
•	Method: POST
•	URL: http://localhost:8080/api/rules/evaluate
•	Headers:
o	Content-Type: application/json
•	Body (Raw JSON):
{
    "ruleId": 1,
    "data": {
        "age": 32,
        "department": "Sales",
        "salary": 60000,
        "experience": 6
    }
}

Expected Response:
{
    "result": true
}
4. Evaluate with Different User Data
You can also test the evaluation with different user data:
•	Example 1 (User below required experience):
o	Body:
•	{
    "ruleId": 1,
    "data": {
        "age": 22,
        "department": "Sales",
        "salary": 30000,
        "experience": 4
    }
}
Expected Response:
{
    "result": false
}

