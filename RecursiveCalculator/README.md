# Recursive Calculator
A command-line-based calculator that evaluates simple arithmetic expressions using recursion. The program handles basic operations such as addition, subtraction, multiplication, division, and parentheses. It supports integer values and ensures protection against division by zero.

## Features:
- Supports addition, subtraction, multiplication, division, and parentheses.
- Handles multi-step operations by respecting operator precedence.
- Strips white spaces in the input for easier handling.
- Displays an error message for invalid expressions or characters.

## Error Handling:
- Division by zero: The program will throw an error message if a division by zero is attempted.
- Invalid characters: If the expression contains invalid characters, an error message will be displayed.

## Example:
```
Enter an expression: 10 / 2
Result: 5

Enter an expression: 10 / 0
Error in expression: Division by zero is not allowed

Enter an expression: 3 + 5 * (2 - 1)
Result: 8
```
