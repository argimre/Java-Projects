# Core Tokenizer
A simple tokenizer that reads one line at a time from an input file, tokenizes that line, and categorizes it
as a reserved word, special symbol, integer, or an identifier. The syntax of this is based on the made-up language
called "core". This categorization is based on predefined mappings.

## Features
- Reads and tokenizes input files line by line.
- Categorizes tokens as reserved words, special symbols, integers, or identifiers.
- Detects invalid tokens and prints appropriate error messages.
- Supports a predefined set of reserved words and symbols.

## Files
- `CoreTokenizer.java` : The main driver program that reads input from a file and processes tokens using the Tokenizer class.
- `Tokenizer.java` : Implements a tokenizer that reads an input file, tokenizes its contents, and provides tokenized outputs.
- `TokenMapSetup.java` - Defines mappings for reserved words and symbols used in the tokenizer.

## Compilation Instructions:
Ensure you have Java installed and are in the `src` directory. To compile the program, use the following command:
```
javac CoreTokenizer.java Tokenizer.java TokenMapSetup.java
```
## Execution Instructions:
To run the tokenizer, provide an input file path as an argument:
```
java CoreTokenizer <input_file>
```
You need to do something like this:
```
java CoreTokenizer sample.txt
```
## Example:
If you use a txt file with the following content:
```
program int X2B1; 
begin X2B1 = 252; 
end !=
```
You will get the following result:
```
PS D:\Workspace\VS Code Workspace\Java-Projects\CoreTOkenizer\src> java CoreTokenizer "D:\Workspace\VS Code Workspace\Java-Projects\CoreTokenizer\test\test1.txt"
[program, int, X2B1, ;, begin, X2B1, =, 252, ;, end, !=]
Token: 1
Token: 4
Token: 32 IdName: X2B1
Token: 12
Token: 2
Token: 32 IdName: X2B1
Token: 14
Token: 31 Integer: 252
Token: 12
Token: 3
Token: 25
Token: 33
End of file reached.
```
