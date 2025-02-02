# Word Counter

This is a Java application that reads a text file, counts the occurrences of each word, and generates an HTML report displaying the word frequencies in a tabular format. This project is helpful for analyzing text files and visualizing word frequency data. Try the `.../lib/shakespeare.txt` file as an input for testing.

## Features
- Word Counting:
    - Reads text from a file and counts the frequency of each word.
    - Handles punctuation, hyphens, and case sensitivity.
- HTML Report Generation:
    - Creates an HTML file displaying word counts in a table.
    - Includes sortable data organized alphabetically.
- Error Handling:
    - Checks if the input file exists and is readable.

## equirements
- Java Development Kit (JDK): Version 8 or higher.
- A text file to analyze.
- A directory for storing the generated HTML report.

## Input File Format
The input file should be a plain text file with the content you want to analyze. For example:
```
Hello world! This is a simple file.
Hello again, world.
```

## File Structure
```
WordCounter
├── WordCounter.java
├── data/
│   └── index.html
└── README.md
```

## Example: 
- ### Input File:
```
Hello world! This is a simple file.
Hello again, world.
```

- ### Output HTML:
```
<html>
<head>
<title>addy</title>
</head>
<body>
<h2>Words Counted in: /path/to/input.txt</h2>
<hr>
<table border="1">
<tbody>
<tr>
<th>Words</th>
<th>Counts</th>
</tr>
<tr>
<td>again</td>
<td>1</td>
</tr>
<tr>
<td>file</td>
<td>1</td>
</tr>
<tr>
<td>hello</td>
<td>2</td>
</tr>
<tr>
<td>is</td>
<td>1</td>
</tr>
<tr>
<td>simple</td>
<td>1</td>
</tr>
<tr>
<td>this</td>
<td>1</td>
</tr>
<tr>
<td>world</td>
<td>2</td>
</tr>
</tbody>
</table>
</body>
</html>
```