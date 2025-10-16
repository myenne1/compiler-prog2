Project Members: Muhanad Yennes, Marco Garcia, and Kaden Casey

For this project we used AI to split the grammar rules needed to be implemented into sections.
We also used it to create the nonterminals needed for each grammar rule.

Muhanad: Worked on creating the structure for Grm.cup with necessary grammar rules and made Absyn files.
Kaden: Worked on Absyn files for expressions, and assignments.
Marco: Worked on Absyn files for statement grammar and constructed the Print.java.

We implemented all grammar rules up to the unary section, including support for pointer types like int*** 
and arrays like int[][]. We ended up using a wrapping method to build up types in the correct ordering. For unary expressions, 
we made sure that ++ and -- only work on variables so that things like ++5 don’t cause problems. We don't have any reduce/reduce conflicts, and the 
shift/reduce conflicts that did show up were expected as the limit is still 3.

We couldn't get the Print.java absyn file working correctly and couldn't get the output to show a parse tree with all of the correct indentations.
Instead, the output shows a list of tokens registered from the test.c file along with some nonterminals it comes across when reading an ID token.