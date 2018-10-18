# Lexer

This is a Java implementation of a Lexer explained in the book **Principles of Compiler Design** written by **Alfred Aho and Jeffrey D. Ullman**.  
What I will go to implement are the transaction diagram to recognize the relationship operations, identificators, numbers, separator and blank spaces.

### Relop diagram
---
<div style="text-aling:center">
 <img src="https://github.com/CiccioTecchio/Lexer/blob/master/img/relop.png"/>
 </div>  
 
 ### Identificator diagram
 ---
 <div style="text-aling:center">
 <img src="https://github.com/CiccioTecchio/Lexer/blob/master/img/id.png"/>
 </div>  
 
 ### Number diagram
 ---
  <div style="text-aling:center">
 <img src="https://github.com/CiccioTecchio/Lexer/blob/master/img/number.png"/>
 </div>  

  ### Separator diagram
 ---
 The separator are the symbol:
 -  {} [\] ()
 - , ; 
  <div style="text-aling:center">
 <img src="https://github.com/CiccioTecchio/Lexer/blob/master/img/separator.png"/>
 </div>  

 ### Blank space diagram
 ---
The blanck spaces are recognized but not returned.  
They are:
- empty space character
- new line character
- tabulation character
  <div style="text-aling:center">
 <img src="https://github.com/CiccioTecchio/Lexer/blob/master/img/blank.png"/>
 </div>  

## How to run

You can import this project in Eclipse and run it normally.
- lang.txt: contains your language
- keywords.txt: contains your keywords
