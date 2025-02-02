# Text Glossary
This is a Java-based project that generates a glossary of terms and their definitions from a text file. The program creates an HTML file for each term, complete with cross-referencing and an index page linking all terms. This project is ideal for creating an organized and navigable glossary from plain text input. Try the `.../lib/terms.txt` file as an input for testing.

## Features
- Text Parsing:
    - Reads terms and definitions from a text file where terms are separated by empty lines.
    - Each term and its corresponding definition are stored as key-value pairs in a HashMap.

- HTML Generation:
    - Generates an index.html file with links to individual term pages.
    - Creates separate HTML pages for each term, including its definition and links back to the index.

- Cross-Referencing:    
    - Automatically hyperlinks terms within definitions to their respective HTML pages, if they exist in the glossary.

## Requirements
- Java Development Kit (JDK): Version 8 or higher.
- A text file containing terms and their definitions.
- A directory for storing the generated HTML files.

## Input File Format
The input text file should follow this structure:
``` 
Term 1
Definition for term 1 goes here.

Term 2
Definition for term 2 goes here.
```
Each term is separated from its definition by a newline, and each term-definition pair is separated by an empty line.

## File Structure
```
TextGlossary
├── TextGlossary.java
├── data/
│   └── index.html
│   └── [term-name].html
└── README.md
```

## Example
Input File:
```
Java
A high-level, class-based, object-oriented programming language.

Glossary
A list of terms and their definitions.
```

## Output:
- ### index.html:
```
<ul>
    <li><a href="java.html">Java</a></li>
    <li><a href="glossary.html">Glossary</a></li>
</ul>
```

- ### java.html:
```
<html>
<head>
<title>Java</title>
</head>
<body>
<h2><font color="red">Java</font></h2>
<p>A high-level, class-based, object-oriented programming language.</p>
<p><a href="index.html">Return to index</a></p>
</body>
</html>
```

- ### glossary.html:
```
<html>
<head>
<title>Glossary</title>
</head>
<body>
<h2><font color="red">Glossary</font></h2>
<p>A list of terms and their definitions.</p>
<p><a href="index.html">Return to index</a></p>
</body>
</html>
```