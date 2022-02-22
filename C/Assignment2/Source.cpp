#include <stdio.h>
#include <iostream>
#include "Profiler.h"

Profiler profiler("Heapsort");

using namespace std;

/**
 * @author Rusu Horia Gabriel
 * @group 30421
 *
 * Heapcreation and Heapsorting

 * Demo function shows that the input works, by reading a number of elements and performing either Bottom-Up or
   Top-Down heap-building and sorts the obtained Max-Heap afterwards, then it shows the Heap and the sorted array.
   After comparing the 2 algorithms in the average case, one can clearly see that the Bottom-Up approach is superior
   when compared to the Top-Down one, both as far as comparisons are concerned, as well as assignments. While Top-Down
   grows with a O(n*lgn) slope, Bottom-Up does the same with a O(n) slope showing that it is better than the other. On 
   the other hand, in the worst-case scenario, despite the fact that both have a O(n) slope, Bottom-Up is still the better
   algorithm, overwhelmingly so, as it performs less assignments and comparissons than Top-Down. 
   One advantage of Top-Down, when compared to  Bottom-Up is that unlike Bottom-Up, where the initial Heap 
   is already built and we just transform it to a Max-Heap with the help of Heapify,
   thus making the dimension fixed, Top-Down has a variable dimension, because the Max-Heap is built
   progressively, by starting with the first two elements of the array and expending it progressively.
 * 
 */

#define MAX_SIZE 10000

void Show(int array[MAX_SIZE], int n)
{
	cout << '\n';
	cout << "Sorted the array is: ";
	
	for (int i = 1; i <= n; i++)
	{
		cout << array[i]<< " ";
	}
}

void ShowTree(int array[MAX_SIZE], int n)
{
    int i = 1;
	int aux = i;
	cout << "The Max Heap is: ";
	cout << "\n";
	while (aux <= n)
	{
		for (int k = 1; k <= n - aux; k++)
	         cout << " ";
        if (aux == 1)
			cout << " ";

		for (int j = 1; j <= aux; j++)
		{
			if (i <= n)
			{
				cout << array[i] << "  ";
				i++;
			}
		}

		cout << "\n";
		cout << "\n";
		aux = i;
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

int parent(int i) 
{
	return i / 2;
}

void Heapify(int array[MAX_SIZE], int i, int n)
{
	
	Operation assign = profiler.createOperation("BottomUp-assign", n);
	Operation comp = profiler.createOperation("BottomUp-compare", n);

	int l = left(i);
	int r = right(i);
	int largest=i;

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

		Heapify(array, largest, n);
	}
}

void Sort(int array[MAX_SIZE], int n)
{

	int aux = n;

	for (int j = n; j >= 2; j--)
	{
		int helper = array[1];
		array[1] = array[j];
		array[j] = helper;
		n--;
		Heapify(array, 1, n);

	}

	Show(array, aux);
}


void BottomUp(int array[MAX_SIZE], int n)
{
	
	for (int i = n / 2; i >= 1; i--)
	{
		Heapify(array, i, n);
	}

	ShowTree(array, n);

	Sort(array, n);
}


void TopDown(int array[MAX_SIZE], int n)
{

	Operation assign = profiler.createOperation("TopDown-assign", n);
	Operation comp = profiler.createOperation("TopDown-compare", n);

	for (int i = 2; i <= n; i++)
	{
        while (i > 1 && array[parent(i)] < array[i])
		{
			comp.count();
			assign.count(3);

			int helper = array[i];
			array[i] = array[parent(i)];
			array[parent(i)] = helper;
			i = parent(i);
		}
	}

	ShowTree(array, n);

	Sort(array, n);
}


void Demo()
{
	int array[MAX_SIZE];
	int n;
	cout << "The length of the array is: ";
	cin >> n;

	for (int i = 1; i <= n; i++)
		cin >> array[i];

    //BottomUp(array, n);
	//TopDown(array,n);

}


void Prof()
{
	int a[MAX_SIZE], b[MAX_SIZE];

	int i, j;

	for (i = 100; i <= MAX_SIZE; i = i + 100)
	{

		for (j = 0; j < 5; j++)
		{
			FillRandomArray(a, i);
			BottomUp(a, i);
		}
	}

	for (i = 100; i <= MAX_SIZE; i = i + 100)
	{

		for (j = 0; j < 5; j++)
		{
			FillRandomArray(b, i);
			TopDown(b, i);
		}
	}

	profiler.divideValues("TopDown-assign", 5);
	profiler.divideValues("TopDown-compare", 5);
	profiler.addSeries("TopDown", "TopDown-assign", "TopDown-compare");

    profiler.divideValues("BottomUp-assign", 5);
	profiler.divideValues("BottomUp-compare", 5);
	profiler.addSeries("BottomUp", "BottomUp-assign", "BottomUp-compare");

	profiler.createGroup("assign", "BottomUp-assign", "TopDown-assign");
	profiler.createGroup("compare", "BottomUp-compare", "TopDown-compare");
    profiler.createGroup("total", "BottomUp", "TopDown");

	profiler.showReport();

}


void ProfWorst(int order)
{
	int a[MAX_SIZE], b[MAX_SIZE];

	int i;

	for (i = 100; i <= MAX_SIZE; i = i + 100)
	{
			FillRandomArray(a, i, 10, 10000, false, order);
			BottomUp(a, i);                                
	}

	for (i = 100; i <= MAX_SIZE; i = i + 100)
	{

			FillRandomArray(b, i, 10, 10000, false, order);
			TopDown(b, i);                                 
	}

	
	profiler.addSeries("TopDown", "TopDown-assign", "TopDown-compare");

	profiler.addSeries("BottomUp", "BottomUp-assign", "BottomUp-compare");

	profiler.createGroup("assign", "BottomUp-assign", "TopDown-assign");
	profiler.createGroup("compare", "BottomUp-compare", "TopDown-compare");
	profiler.createGroup("total", "BottomUp", "TopDown");

	profiler.showReport();

}

int main()
{
	Demo();
	//Prof();
	//ProfWorst(ASCENDING);

	return 0;
}