#include <stdio.h>
#include <iostream>
#include "Profiler.h"

Profiler profiler("Sorting");

using namespace std;

/**
 * @author Rusu Horia Gabriel
 * @group 30421
 *
 * Comparison sorting methods Bubblesort,Selection sort,Insertion sort (sorting is done for ascending order)
 *
 * Personal interpretation: In the best case scenario, when the numbers are already sorted, Bubblesort 
   Selection sort have the lowest number of assignments, 0, so O(1), in both cases, unlike Insertion sort
   where the slope is O(n), because of the aux=array[i] count. At comparisons on the other hand, Selection
   is the worst with a O(n^2) slope while Bubble and Insert have simple O(n) slopes. Overall the best one is
   still Bubble in best case with a lower O(n) slope than Insertion's O(n) slope and both smaller than Selection
   with an O(n^2) slope. In the worst case scenario, when the numbers are descending, Bubble has O(n^2) slopes
   for both comparisons and assignments, just like Insertion. With Select the only difference is that the number
   of assignments grows with a O(n) slope. Overall, in the worst case scenario, selection has the smallest number
   of assignments, comparisons are almost the same for select and insert, but better than bubble, thus when added,
   Selection is the best of them. In the average case, when it comes to comparisons, all of them have an O(n^2) slope. 
   At assignments the only one with a better slope is Insert with an O(n) slope,while the other 2 have O(n^2) slopes,
   because of the 3 assignments counted when exchanging values. Overall, obviously Insert has the best number and 
   lowest number of assignments, while Bubble the biggest one. Same can be said for comparisons, as Insert's slope,
   though also O(n^2) is still smaller than the other 2. So overall Insertion sort and Selection sort are almost as good, 
   while Bubble Sort is last, as the worst one.
 */

#define MAX_SIZE 10000

void show(int array[MAX_SIZE], int n)
{
	int i;
	cout << "\t";

	for (i = 0; i < n; i++)
		cout << array[i] << " ";
}


void bubble(int array[MAX_SIZE], int n)
{
	Operation comp = profiler.createOperation("bubble-comp", n);

	Operation assign = profiler.createOperation("bubble-assign", n);

	int i, j;
	int aux=0;
	bool test=true;

	while(test==true)
	{
		test = false;
		for (j = 0; j < n-1 ; j++)
		{
			comp.count();
			if (array[j] > array[j + 1])
			{
				assign.count(3);
				aux = array[j];
				array[j] = array[j + 1];
				array[j + 1] = aux;
				test = true;
			}
		}

	}

	//show(array, n);
}


void insert(int array[MAX_SIZE], int n)
{
	Operation comp = profiler.createOperation("insert-comp", n);

	Operation assign = profiler.createOperation("insert-assign", n);

	int i, j;
	int aux = 0;
	for (i = 1; i < n; i++)
	{
		assign.count();
		aux = array[i];
		j = i - 1;
		while (j >= 0 && array[j] > aux)
		{
			comp.count();
			assign.count();
			array[j + 1] = array[j];
			j--;
		}

		if (j > 0)
			comp.count();

		if (j + 1 != i)
		{
			array[j+1] = aux;
			assign.count();
		}

    }

	//show(array, n);
}

void select(int array[MAX_SIZE], int n)
{
	Operation comp = profiler.createOperation("select-comp", n);

	Operation assign = profiler.createOperation("select-assign", n);

	int i, j;
	int helper = 0;
	int min = 0;
	for (i = 0; i < n; i++)
	{
		min = i;
		for (j = i + 1; j < n; j++)
		{
			comp.count();
			if (array[min] > array[j])
				min = j;
		}

		if (min != i)
		{
			assign.count(3);

			helper = array[i];
			array[i] = array[min];
			array[min] = helper;
		}

	}

	//show(array, n);
}

void demo()
{
	int array[MAX_SIZE];
	int i, n;
	cin >> n;

	for (i = 0; i < n; i++)
		cin >> array[i];

	//bubble(array, n);
	//insert(array, n);
	//select(array, n);

}

void prof(int order)
{
	int a[MAX_SIZE], b[MAX_SIZE], c[MAX_SIZE];
	int i, j;

	for (i = 100; i <= MAX_SIZE; i = i + 100)
	{

		for (j = 0; j < 5; j++)
		{
			FillRandomArray(a, i, 10, 10000, false, order);
			select(a, i);
		}
	}

	for (i = 100; i <= MAX_SIZE; i = i + 100)
	{

		for (j = 0; j < 5; j++)
		{
			FillRandomArray(b, i, 10, 10000, false, order);
			insert(b, i);
		}
	}

	for (i = 100; i <= MAX_SIZE; i = i + 100)
	{

		for (j = 0; j < 5; j++)
		{
			FillRandomArray(c, i, 10, 10000, false, order);
			bubble(c, i);
		}
	}

	profiler.divideValues("bubble-comp", 5);
	profiler.divideValues("bubble-assign", 5);
	profiler.addSeries("bubble", "bubble-comp", "bubble-assign");

	profiler.divideValues("insert-comp", 5);
	profiler.divideValues("insert-assign", 5);
	profiler.addSeries("insert", "insert-comp", "insert-assign");


	profiler.divideValues("select-comp", 5);
	profiler.divideValues("select-assign", 5);
	profiler.addSeries("select", "select-comp", "select-assign");

	profiler.createGroup("comp", "select-comp", "insert-comp", "bubble-comp");
	profiler.createGroup("assign", "select-assign","insert-assign", "bubble-assign");
	profiler.createGroup("total", "select", "insert", "bubble");

}



void prof_comp()
{
	prof(UNSORTED);
	profiler.reset("best");
	prof(ASCENDING);
	profiler.reset("worst");
	prof(DESCENDING);

	profiler.showReport();
}


int main()
{
	//demo();

	prof_comp();
}