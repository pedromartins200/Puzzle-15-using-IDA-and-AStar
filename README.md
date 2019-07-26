# Puzzle 15 using IDA and AStar
 An implementation of the IDA Star and A Star algorithms applied on the 4x4 puzzle

 View [The 15 puzzle problem](https://en.wikipedia.org/wiki/15_puzzle).

This was a project I did just to understand this two types of algorithms. In this specific problem, IDA Star was shown to be very superior, solving all possible instances of the board, while A Star was not able to solve it, since A Star runs out of memory really fast.

# My implementation

This implementation was done in a generic way, so you can easily apply this to any kind of problem, as long as you implement Ilayout.java, and implement the methods of that interface.

I used the manhattan distance heuristic. I know there is a better one: pattern database, but it seemed really complex to implement. 

# Example Usage

**Input**

Check Main.java to get all sample input and outputs

The input should be two lines each one representing the state of the board.
The 0 element represents the blank space.

123456789ABCDEF0 represents

1 2 3 4
5 6 7 8
9 A B C 
D E F 

**Output**
The lowest number of steps from board 1 to board 2.


