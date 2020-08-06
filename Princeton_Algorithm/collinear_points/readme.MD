1. Merging with smaller auxiliary array. Suppose that the subarray \mathtt{a[0]}a[0] to \mathtt{a[n-1]}a[n−1] is sorted and the subarray \mathtt{a[n]}a[n] to \mathtt{a[2*n-1]}a[2∗n−1] is sorted. How can you merge the two subarrays so that \mathtt{a[0]}a[0] to \mathtt{a[2*n-1]}a[2∗n−1] is sorted using an auxiliary array of length nn (instead of 2n2n)?

copy only the left half into the auxiliary array then add elements from aux the right half back into the original array

2. count inversions in nlogn time.

count in the merge step of merge sort

3. shuffle linked list with log memory and nlogn time

because of linked list, no need to create aux. generate 0/1 when merging.