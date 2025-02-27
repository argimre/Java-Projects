# WordGuesser
A simple word-guessing game inspired by Wordle. The game provides users with multiple attempts to guess a randomly selected word, providing color-coded feedback for each letter:

- Green: Correct letter in the correct position.
- Yellow: Correct letter in the wrong position.
- Red: Incorrect letter.

## Features
- A 6x5 grid for word guessing.
- Interactive input with automatic cursor movement.
- Backspace functionality to delete and move back.
- Disabled editing of previous rows once a guess is made.
- Random word selection from a file (words.txt).

## How to play
1. Type a 5-letter word in the first row.
2. Press Enter to submit your guess.
3. Check the color-coded feedback.
4. If incorrect, continue guessing in the next row.
5. Win by guessing the word correctly or lose after 6 failed attempts.

## File Structure
```
WordGuesser/
│── ControlPanel.java       # Handles UI components (grid setup & key events)
│── WordGuesser.java        # Manages game logic (word selection & guess handling)
│── lib/
│   ├── words.txt           # Word list for random selection
│── README.md               # Project documentation
```