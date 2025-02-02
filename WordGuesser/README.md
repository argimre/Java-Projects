# Word Guesser

Word Guesser is a console-based word-guessing game implemented in Java. Players have six attempts to guess a randomly selected 5-letter word from a predefined word list. After each guess, feedback is provided in the form of a 6x5 grid, with hints about correct and misplaced letters.

## Features
- Reads a list of 5-letter words from a text file.
- Randomly selects a word for each game.
- Allows players six attempts to guess the word.
- Displays a feedback grid to indicate:
    - Correct letters in the correct positions.
    - Letters that are in the word but in the wrong positions.
    - Provides helpful hints after each guess.

## How to Play
1. The game will display a 6x5 grid initially filled with -.
2. Players enter a 5-letter word as a guess.
3. After each guess:
    - The grid updates with feedback:
        - Correct letters appear in their correct positions.
        -  A `-` is used for incorrect letters.
    - A hint is displayed for misplaced letters.

4. The game ends when the player:
    - Guesses the correct word.
    - Runs out of attempts.

## Requirements
- Java Development Kit (JDK) 8 or higher.
- A text file named words.txt containing 5-letter words. The file must be located at `...\WordGuesser\lib\`.
- Sample words.txt
    ```
    apple
    trace
    react
    reach
    crate
    place
    ```