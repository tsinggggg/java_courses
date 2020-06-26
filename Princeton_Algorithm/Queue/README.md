1. use two stacks to build a queue:
enQueue(q,  x)
  1) Push x to stack1 (assuming size of stacks is unlimited).
Here time complexity will be O(1)

deQueue(q)
  1) If both stacks are empty then error.
  2) If stack2 is empty
       While stack1 is not empty, push everything from stack1 to stack2.
  3) Pop the element from stack2 and return it.
Here time complexity will be O(n), amortized complexity of the dequeue operation becomes  \Theta (1) .

2. stack with max: maintain another stack for max

3. Explain why Java prohibits generic array creation: don't know what's the memory size of each item?
