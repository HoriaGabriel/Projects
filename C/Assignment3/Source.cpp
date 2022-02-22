#include <stdio.h>
#include <stdlib.h> 
#include <iostream>
#include <time.h>
#include "Profiler.h"

/**
 * @author Rusu Horia Gabriel
 * @group 30421
 *
 * QuickSort and QuickSelect

 * The Function compares the slops of QuickSort, Heapsort and QuickSort when the pivot is chosen randomely. First
   when the QuickSort is done when the pivot is the rightmost element, one can see that the slope is O(n*lgn), same
   as the slope of the HeapSort, also O(n*lgn), but in the average case, the one shown in the graph, QuickSort is 
   better than HeapSort, both when it comes to Assignments and Comparissons, as well as overall. When comparing
   QuickSort with the randomized QuickSort, one can see that their values are very similar, with Randomized QuickSort
   also having a O(n*lgn) slope, and being only slightly better overall than the normal QuickSort, at least in the 
   average case. When it comes to the Worst case however, the Randomized QuickSort is very clearly much better, having
   a small O(n*lgn) curve, as opposed to normal QuickSort's O(n^2) curve. The Best case Scenario for QuickSort is 
   generated when the pivot is chosen as the middle of the array and as the graph indicates, it has a O(n*lgn) slope,
   like in the average case.
 *
 */

Profiler profiler("Quicksort");

using namespace std;

#define MAX_SIZE 10000

void Show(int array[100], int n)
{
	cout << '\n';
	cout << "Sorted the array is: ";

	for (int i = 1; i <= n; i++)
	{
		cout << array[i] << " ";
	}
}

int left(int i)
{
	return 2 * i;
}

int right(int i)
{
	return 2 * i + 1;
}

void Heapify(int array[MAX_SIZE], int i, int n, int aux)
{
	Operation assign = profiler.createOperation("HeapSort-assign", aux);
	Operation comp = profiler.createOperation("HeapSort-compare", aux);

    int l = left(i);
	int r = right(i);
	int largest = i;

	if (l <= n && array[l] > array[i])
	{
		comp.count();
		largest = l;
	}

    if (r <= n && array[r] > array[largest])
	{
		comp.count();
		largest = r;
	}

	if (largest != i)
	{
		assign.count(3);
		int helper = array[i];
		array[i] = array[largest];
		array[largest] = helper;

		Heapify(array, largest, n, aux);
	}
}

void BottomUp(int array[MAX_SIZE], int n, int aux)
{
    for (int i = n / 2; i >= 1; i--)
	{
		Heapify(array, i, n, aux);
	}
}

void HeapSort(int array[MAX_SIZE], int n)
{
	int aux = n;

	Operation assign = profiler.createOperation("HeapSort-assign", aux);
	Operation comp = profiler.createOperation("HeapSort-compare", aux);

	BottomUp(array, n, aux);

	for (int j = n; j >= 2; j--)
	{
		assign.count(4);
        int helper = array[1];
		array[1] = array[j];
		array[j] = helper;
		n--;
		Heapify(array, 1, n, aux);
    }

	Show(array, aux);
}

int Partition(int array[MAX_SIZE], int p, int r, int aux)
{
    Operation assign = profiler.createOperation("QuickSort-assign", aux);
	Operation comp = profiler.createOperation("QuickSort-compare", aux);


	int pivot = array[r];
	assign.count();
	

    int i = p - 1;
	for (int j = p; j <= r - 1; j++)
	{
		if (array[j] <= pivot)
		{
			comp.count();
			assign.count(3);
			i++;
			int helper = array[i];
			array[i] = array[j];
			array[j] = helper;
		}
	}

	assign.count(3);

	int helper2 = array[i + 1];
	array[i + 1] = array[r];
	array[r] = helper2;

	return i + 1;

}


int PartitionRandom(int array[MAX_SIZE], int p, int r, int aux)
{
	Operation assign = profiler.createOperation("QuickSortRandom-assign", aux);
	Operation comp = profiler.createOperation("QuickSortRandom-compare", aux);

	int pivot, random;

	srand(time(NULL));
	random = p + rand() % (r - p);

	int helper3 = array[r];
	array[r] = array[random];
	array[random] = helper3;

	pivot = array[r];
	assign.count();

    int i = p - 1;
	for (int j = p; j <= r - 1; j++)
	{
		if (array[j] <= pivot)
		{
			comp.count();
			assign.count(3);
			i++;
			int helper = array[i];
			array[i] = array[j];
			array[j] = helper;
		}
	}

	assign.count(3);

	int helper2 = array[i + 1];
	array[i + 1] = array[r];
	array[r] = helper2;

	return i + 1;

}

void Quick(int array[MAX_SIZE], int p, int r, int aux, int t)
{
	int q;
    if (p<r)
	{
		if (t == 0)
			q = Partition(array, p, r, aux);
		else
			q = PartitionRandom(array, p, r, aux);

		Quick(array, p, q-1, aux, t);
		Quick(array, q + 1, r, aux, t);
    }
}


void BestQuick(int array[MAX_SIZE], int p, int r, int aux) 
{
	Operation assign = profiler.createOperation("BestQuick-assign", aux);
	Operation comp = profiler.createOperation("BestQuick-compare", aux);

    int i = p, j = r;
	int helper;

    assign.count();
    int pivot = array[(p + r) / 2];

	while (i <= j)
	{
		while (array[i] < pivot)
		{
			comp.count();
			i++;
	    }

		while (array[j] > pivot)
		{
			comp.count();
			j--;
		}
          
       if (i <= j)
	   {
		   assign.count(3);

          helper = array[i];
          array[i] = array[j];
          array[j] = helper;
	      i++;
		  j--;
	   }
    }

	if (p < j)
        BestQuick(array, p, j, aux);
    if (i < r)
        BestQuick(array, i, r, aux);
}

int QuickSelect(int array[MAX_SIZE], int p, int r, int i, int aux)
{
	if (p == r)
		return array[p];
	int q = Partition(array, p, r, aux);
	int k = q - p + 1;

	if (i == k)
	{
		return array[q];
	}
	else if (i < k)
		return QuickSelect(array, p, q - 1, i, aux);
	else
		return QuickSelect(array, q + 1, r, i - k, aux);
}

void Demo()
{
	int array[100];
	int n,aux;
	cout << "Number of elements is: ";
	cin >> n;
	aux = n;

	for (int i = 1; i <= n; i++)
	{
		cin >> array[i];
	}

   /*int t;
	cout << "Press 0 if you want rightmost element as pivot, 1 otherwise: ";
	cin >> t;

	Quick(array, 1, n, aux, t);
	Show(array,n);*/ 

	//--------------------

	/*BestQuick(array, 1, n, aux);
	Show(array, n);*/

	//---------------------

	//HeapSort(array, n);

	//----------------------

	/*int k,number;
	cout << "Which number do you want to have: ";
	cin >> k;

	number=QuickSelect(array, 1, n, k, aux);

	cout << number;*/
}

void Prof(int order)
{
	int a[MAX_SIZE],b[MAX_SIZE], c[MAX_SIZE];

	int i, j, aux;


	for (i = 100; i <= MAX_SIZE; i = i + 100)
	{

		for (j = 0; j < 5; j++)
		{
			FillRandomArray(b, i,10,10000,false,order);
			aux = i;
			int f = 1, g = 1;
			for (int k = 1; k <= i; k++)
			{
				c[k] = b[f];
				f++;
			}

			for (int h = 1; h <= i; h++)
			{
				a[h] = b[g];
				g++;
			}

			Quick(b, 1, i, aux, 1);
			Quick(a, 1, i, aux, 0);
			HeapSort(c, i);

		}
	}

	profiler.divideValues("HeapSort-assign", 5);
	profiler.divideValues("HeapSort-compare", 5);
	profiler.addSeries("HeapSort", "HeapSort-assign", "HeapSort-compare"); 

	profiler.divideValues("QuickSortRandom-assign", 5);
	profiler.divideValues("QuickSortRandom-compare", 5);
	profiler.addSeries("QuickSortRandom", "QuickSortRandom-assign", "QuickSortRandom-compare");

	profiler.divideValues("QuickSort-assign", 5);
	profiler.divideValues("QuickSort-compare", 5);
	profiler.addSeries("QuickSortNormal", "QuickSort-assign", "QuickSort-compare");

	profiler.createGroup("Comparisons", "HeapSort-compare", "QuickSortRandom-compare","QuickSort-compare");
	profiler.createGroup("Assignments", "HeapSort-assign", "QuickSortRandom-assign","QuickSort-assign");
	profiler.createGroup("Overall", "HeapSort", "QuickSortRandom","QuickSortNormal");

}


void ProfComplete()
{
    Prof(UNSORTED);
    profiler.reset("Worst");
	Prof(DESCENDING);

	profiler.showReport();
}

int main()
{
	Demo();
	//ProfComplete();

	return 0;
}