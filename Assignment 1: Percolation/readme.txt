Programming Assignment 1: Percolation

Answer these questions after you implement your solution.

/* *****************************************************************************
 *  Describe the data structures (i.e., instance variables) you used to
 *  implement the Percolation API.
 **************************************************************************** */
I used a 2d boolean array, 2 integer values to help track of specific attributes
of the grid, and a weighted quick union find object that I named set to keep track
of all the connected sites in the set and if they were filled or not.
The 2d boolean array kept track of if a site was blocked or not.

/* *****************************************************************************
 *  Briefly describe the algorithms you used to implement each method in
 *  the Percolation API.
 **************************************************************************** */
open(): I used a bunch of if statements that first opened the site and determined if
the sites around could be accessed (in the scope of the array), and then connected
them in the weighted quick union find object.
isOpen(): This just used the boolean 2d array method to determine if a site was
blocked
isFull(): I used the WeightedQUF method find() to see if the site was connected
to the site of the top, and if it was this means that it had to be filled
numberOfOpenSites(): In the open function there is a counter that went up every
single time a site was opened, I simply returned this counter for this method
percolates(): I used the WeightedQUF method find() to determine if the top and bottom
sites were connected together and had the same parent, if they did then the system
percolates.

/* *****************************************************************************
 *  First, implement Percolation using QuickFindUF.
 *  What is the largest value of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100

 n          time (seconds)
--------------------------
200          0.52
400          3.807
800          29.742
1000         58.528
1050         67.622

/* *****************************************************************************
 *  Describe the strategy you used for selecting the values of n.
 **************************************************************************** */
I saw that doubling of n wwas roughly 8x a little less, so I did that until I
saw 1000 was very close to a minute, and I just increased it by a little more.

/* *****************************************************************************
 *  Next, implement Percolation using WeightedQuickUnionUF.
 *  What is the largest value of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100

 n          time (seconds)
--------------------------
500         0.922
1000        3.416
2000        26.181
2200        44.895
2700        63.756


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
 There is backwash that I couldn't quite figure out how to handle. This occurs
 when spots connected to the bottom of the grid become filled when the grid
 percolates even if they are not connected to the top of the grid.




/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
none



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
none
