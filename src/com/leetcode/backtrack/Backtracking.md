Backtracking
====



General algorithm for finding all or some solutions to some computational problems i.e. constrain satisfaction problem CSP, which incrementally builds candidates to the solution and abandons a candidate i.e. backtracks as soon as it determines that the candidate cannot lead to a valid solution.



## Common patterns / Template:

```
public backtrack(candidate) {

	// iterate over the candidates, and for each candidate
	for (next candidate from all candidates)
		// verify if the solution works
		if (next candidate is valid)
			place(next candidate)
			backtrack(next candidate);
			remove(next candidate);
}
```



Example 1: Given a binary tree that forms a word from all the nodes in its path from the root to the leaf. 

[a, n, i, t, d, m, r], find if the word aim exist.

Steps:

1. Navigate A. Is A the first letter in the target AIM? Yes
2. Navigate its child, N and I. Is N the second letter? No
3. Backtrack and go with I. Is I the second letter? Yes
4. Navigate its child M and R. Is M the third letter? Yes. Found. Return.



Example 2: N-queens 

Given an NxN matrix representing a chessboard, place N queens such that no 2 queens can attach each other. Count the number of possible solutions. A queen can move diagonal, reverse diagonal, up/down, left/right.

Steps:

1. Iterate over each row of the board. Once we reach the last row, we have explored all possible solutions.
2. at each row,  iterate over each column to explore possibility of placing a queen
3. before we place a queen, we check if there are queens attacking that row, column 
4. if there's none, place a queen and mark it 
5. however, if everything does not come in place, we must have the ability to remove the queen we placed



### Notes

- Overall, the enumeration of candidates are done in 2 levels: the function is implemented as recursion, and at each occurrence of recursion , the function is one step further to the final solution.  The second level, it is within the recursion, we have an iteration that allows us to explore all the candidates that are of the same progress to the final solution.
- The backtracking should happen at the level of the iteration within the recursion
- Unlike brute-force, backtracking algos are often able to determine if a partial solution candidate is worth exploring further i.e. isValid(next candidate), which allows us to prine the search zones.  This is also knowsn as the constraint i.e. attacking zone of queen in the N-queen game.
- There are 2 symmetric function that allow us to mark the decision - place(candidate) and revert the decision - remove(candidate)





Example 3: Robot Room Cleaner

Given a room that is represented as a grid of cells, where each cell contains a value that indicates whether it is an obstacle or not, we are asked to clean the room with a robot cleaner which can turn in four directions and move one step at a time. 

Steps:

1. Model each step of the robot as a recursive function that can be backtracked - backtrack()
2. Each step, the robot would have 4 directions to go. UP, DOWN, LEFT, RIGHT. Not all direction is available to explore, so we need to check the direction if it has obstacle or has been cleaned before. This is the isValid((candidate)
3. Once the robot decides to explore in certain direction, the robot should mark its decision by place(candidate). The robot should also be able to use this mark and revert the previous decision via remove(candidate) by going back to the cell and restore its previous direction
4. The robot conducts the cleaning step by step in the form of recursion of the backtrack()



Example 4: Sudoku Solver

Sudoku is a popular game that many of you are familiar with. The main idea of the game is to fill a grid with only the numbers from 1 to 9, while ensuring that each row and each column as well as each sub-grid of 9 elements does not contain duplicate numbers.

Steps:

1. Given a grid with pre-filled numbers, the task is to fill empty cells with the numbers that meet the constraint of Sudoku game. We model each step to fill an empty cell as a recursion function backtrack()
2. Each step, we have 9 candidates to fill the empty cell. We could filter out the candidates by examining if they meet the rules of Sudoku  - isValid(candidate)
3. Among all the suitable candidates, we try out one by one filling the cell - place(candidate) and later we can revert them - remove(candidate) so we could try out other candidates
4. The solver would carry on one step after another in the form of recursion by the backtrack function. The backtracking would be triggered at the points where either the solver cannot find any suitable candidate or the solver finds a solution to the problem. At the end of the backtracking, we would enumerate all the possible solutions to the game.