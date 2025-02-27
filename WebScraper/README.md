# Web Scraper
A Java application that retrieves content from a user-specified URL, counts the frequency of each word (ignoring case), and extracts all hyperlinks found within the page. The results are then written into an HTML file that displays two tables: a Word Count Table listing each word alongside its count, and a Links Table with clickable hyperlinks extracted from the web page.

## Features
- URL Content Fetching: Reads data directly from the URL provided by the user.
- Word Counting: Counts all words (ignoring punctuation and case) found in the HTML content.
- Link Extraction: Uses regular expressions to find and list hyperlinks present in the content.
- HTML Output: Generates a user-friendly HTML file with tables that display the word counts and links.

## Code Structure
- `buildHeader`: Writes the opening HTML tags and header content (title and heading) into the output file.
- `buildWordsTable`: Creates an HTML table that displays each word and its frequency, sorted in a case-insensitive manner.
- `buildLinksTable`: Generates an HTML table containing hyperlinks extracted from the web page. Each link is clickable.
- `buildFooter`: Closes the HTML document.
- `countWordsAndExtractLinks`: Reads the web page content, counts the words, and extracts hyperlinks using regular expressions.
- `main`: Manages user input, orchestrates the counting/extraction process, and writes the results to an output HTML file.

## View the Results
Open the generated HTML file `index.html` in your web browser to review the word counts and the list of extracted hyperlinks.

