Project Members: Muhanad Yennes, Marco Garcia, and Kaden Casey

For this project we used AI to create the list for all of the identifiers and operators to include in order to be 
able to identify them in the lexer. Comments, strings, and white spaces are skipped during parsing.

Handling:

    Decimal Literals:
    - Hex: If a number starts with 0x or 0X we treat it as base-16
    - Octal: If a number starts with just 0 and not a following 'x' we treat as base-8
    - Decimal: If no leading 0 and just numbers '123' we treat as base-10

    Char and String Literals:
    - chars: a single character inside single quotes gets picked up as a char literal. Also allowed escaped characters '\n' '\\'
    - Strings: Anything inside double quotes is counted as a string literal "hello"

    Whitespaces: 
    - Newlines: When we see '\n' we call newline() function
    - Spaces, tabs, etc.: ignored individually 
    
    Comments:
    - if '//' is detected, everything after it is skipped
    - anything between /* and */ are skipped
