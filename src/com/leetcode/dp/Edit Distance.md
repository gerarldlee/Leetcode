Edit Distance Problem
====

- quantify how different 2 strings or words are to one another
- and know the minimum number of operations required to transform one string to another
- use cases:
  - spell correction
  - natural language processing
  - bioinformatics
- types:
  - Levenshtein distance - allows deletion, insertion and substitution
  - Longest common subsequence (LCS)  distance - allows only insertion and deletion, not substitution



## Levenshtein Distance

- string metric for measuring difference between 2 sequences
- the minimum number of single character edits (insertion, deletion, or substitutions) required to change one word to another

Operations:

- Situation 1: Equivalent characters - no operation
- Situation 2: Mismatch, do a replacement operation
- Situation 3: Mismatch, do an insert operation
- Situation 4: Mismatch, do a delete operation

