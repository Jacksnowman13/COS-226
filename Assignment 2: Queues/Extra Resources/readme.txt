Programming Assignment 2: Deques and Randomized Queues


/* *****************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 **************************************************************************** */

For the deque, I created a linked last that you could enter and remove entries
from both sides. I chose a linked list for this because it has efficient entry of
both sides using two pointers, which would be inefficient using an array. I used a
two pointer system in order to do this efficiently, one pointing at the front
and one pointing at the back.

For the randomized queue I used an array to create a queue that would have randomized
dequeue functions and a resized function to be able to increase or decrease the
size of the array in accordance to how an queue would be. The queue needs to use
an array because the linked list would be inefficient since removing a random
entry would require traversing the entire array from the end or the back.




/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
