# Tic-Tac-Toe
A simple command-line Tic-Tac-Toe game built with Java. Players take turns as `X` and `O` to mark cells in a 3x3 grid. The goal is to align three symbols horizontally, vertically, or diagonally to win the game. If all cells are filled and no player has achieved this, the game ends in a draw.

## Features
- Interactive Gameplay: Players enter moves using coordinates (e.g., `A1`, `B3`).
- Dynamic Board Updates: The board is cleared and redrawn after every move.
- Input Validation: Ensures valid moves and prevents overwriting occupied cells.
- Win/Draw Detection: Automatically checks for winning conditions or a draw after each turn.

## Rules
1. The game alternates turns between Player X and Player O.
2. A player wins by aligning three symbols in:
    - A row
    - A column
    - A diagonal
3. The game ends in a draw if all cells are filled with no winner.

## Example Gameplay

```
   1   2   3
  -------------
A |   |   |   |
  -------------
B |   |   |   |
  -------------
C |   |   |   |
  -------------

Player X's turn.
Enter your move (e.g., A1, B3): A1

    1   2   3
  -------------
A | X |   |   |
  -------------
B |   |   |   |
  -------------
C |   |   |   |
  -------------

Player O's turn.
Enter your move (e.g., A1, B3): B2
  
   1   2   3
  -------------
A | X |   |   |
  -------------
B |   | O |   |
  -------------
C |   |   |   |
  -------------

Player X's turn.
Enter your move (e.g., A1, B3): A1
```
