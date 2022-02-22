#include <stdio.h>
#include <stdlib.h> 
#include <iostream>
#include <list> 
#include <iterator> 
#include "Profiler.h"

Profiler profiler("Lab4");

using namespace std;

/**
 * @author Rusu Horia Gabriel
 * @group 30421
 *  Merge k Ordered Lists 

 * After running the problem we observe that overall the graphs have a O(n*log k) complexity. In the case of k=5,
   where there are 5 lists and a different number of total values, k is a static value, so the logaritm is static and the
   differences are brought by the changing number of total elements, thus the graphs looks like they have a O(n) aspect. 
   The same can be said for the situations where k is 10 and 100, where the graph gets more stream-lined as the number of
   lists increases. Thus, when it comes to changing the number of lists, comparing them, you can see that the number
   of operations increases along with the number of operations. On the other hand, when we change the number of lists k,
   while keeping the total number n at 10000, we see that now n is the constant so now the graph is more dependent 
   on the logarithm of k, thus the graph has a O(log k) aspect to itself.
*/

# define MAX_SIZE 10001

void Show(list <int> finalList)
{
	list <int> ::iterator it2;

	cout << "\n";
	cout << "The merged list is: ";

	for (it2 = finalList.begin(); it2 != finalList.end(); ++it2)
	{
		cout << *it2 << " ";
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

void BubbleDown(int arrayHeap[MAX_SIZE], int arrayIndex[MAX_SIZE], int i, int n, int aux, int b)
{
	Operation o1 = profiler.createOperation("Merge_k_5", aux);
	Operation o2 = profiler.createOperation("Merge_k_10", aux);
	Operation o3 = profiler.createOperation("Merge_k_100", aux);
	Operation o4 = profiler.createOperation("Merge_n_10000", aux);

    int l = left(i);
	int r = right(i);
	int smallest = i;

	if (l <= n && arrayHeap[l] < arrayHeap[smallest])
	{
		smallest = l;
		if (b == 0)
			o1.count();
		else if (b == 1)
			o2.count();
		else if (b == 2)
			o3.count();
		else if (b == 3)
			o4.count();
	}


	if (r <= n && arrayHeap[r] < arrayHeap[smallest])
	{
		smallest = r;
		if (b == 0)
			o1.count();
		else if (b == 1)
			o2.count();
		else if (b == 2)
			o3.count();
		else if (b == 3)
			o4.count();
	}

	if (smallest != i)
	{
		if (b == 0)
			o1.count(6);
		else if (b == 1)
			o2.count(6);
		else if (b == 2)
			o3.count(6);
		else if (b == 3)
			o4.count(6);

		int helper = arrayHeap[i];
		arrayHeap[i] = arrayHeap[smallest];
		arrayHeap[smallest] = helper;

		int j = smallest;

	    int helper2 = arrayIndex[i];
	    arrayIndex[i] = arrayIndex[j];
		arrayIndex[j] = helper2;

		BubbleDown(arrayHeap, arrayIndex, smallest, n, aux, b);
	}
}


void BottomUp(int arrayHeap[MAX_SIZE],int arrayIndex[MAX_SIZE], int n, int aux, int b)
{
	Operation o1 = profiler.createOperation("Merge_k_5", aux);
	Operation o2 = profiler.createOperation("Merge_k_10", aux);
	Operation o3 = profiler.createOperation("Merge_k_100", aux);
	Operation o4 = profiler.createOperation("Merge_n_10000", aux);

	for (int i = n / 2; i >= 1; i--)
	{
		BubbleDown(arrayHeap, arrayIndex, i, n, aux, b);
	}
}

void Introduce(list <int> finalList, int arrayHeap[MAX_SIZE], int arrayIndex[MAX_SIZE], int arrayLengths[MAX_SIZE], 
	           int k, int n, int i, list <int> ::iterator it[MAX_SIZE], int aux, int b)
{
	Operation o1 = profiler.createOperation("Merge_k_5", aux);
	Operation o2 = profiler.createOperation("Merge_k_10", aux);
	Operation o3 = profiler.createOperation("Merge_k_100", aux);
	Operation o4 = profiler.createOperation("Merge_n_10000", aux);

    while (i <= n )
	{
		if(b==0)
		  o1.count();
		else if(b==1)
		  o2.count();
		else if(b==2)
          o3.count();
		else if (b==3)
		  o4.count();

		finalList.push_back(arrayHeap[1]);
		i++;

		for (int j = 1; j <= k; j++)
		{
			if (j == arrayIndex[1])
			{
				if (arrayLengths[j] > 1)
				{
					if (b == 0)
						o1.count(3);
					else if (b == 1)
						o2.count(3);
					else if (b == 2)
						o3.count(3);
					else if (b == 3)
						o4.count(3);

					it[j]++;

					arrayHeap[1] = *it[j];
					arrayLengths[j]--;
				}
				else
				{
                   arrayHeap[1] = INT_MAX;
				}
			}
		}

		BubbleDown(arrayHeap, arrayIndex, 1, k, aux, b);
	}

	Show(finalList);
}


void Merge(list <int> list1[MAX_SIZE], int arrayLengths[MAX_SIZE], int k, int n, int aux, int b)
{
	Operation o1 = profiler.createOperation("Merge_k_5", aux);
	Operation o2 = profiler.createOperation("Merge_k_10", aux);
	Operation o3 = profiler.createOperation("Merge_k_100", aux);
	Operation o4 = profiler.createOperation("Merge_n_10000", aux);

	list <int> finalList;
	int arrayHeap[MAX_SIZE];
	int arrayIndex[MAX_SIZE];
    list <int> ::iterator it[MAX_SIZE];

	for (int i = 1; i <= k; i++)
	{

		if (b == 0)
			o1.count(2);
		else if (b == 1)
			o2.count(2);
		else if (b == 2)
			o3.count(2);
		else if (b == 3)
			o4.count(2);

		it[i] = list1[i].begin();
		arrayHeap[i] = *it[i];
	    arrayIndex[i] = i;
	}

    BottomUp(arrayHeap, arrayIndex, k, aux, b);

	Introduce(finalList, arrayHeap, arrayIndex, arrayLengths, k, n, 1, it, aux, b);
}


void Demo()
{
	list <int> list1[100];
	int arrayLengths[100];
	int k,n,b=3;
	cout << "Number of lists is: ";
	cin >> k;

	cout << "Number of total elements in lists is: ";
	cin >> n;

	int aux = n;
	int aux2 = n;

	for (int i = 1; i <= k; i++)
	{
		cout << "How many elements does this list have?\n ";
		cin >> arrayLengths[i];
		aux2 = aux2 - arrayLengths[i];
        
		for (int j = 1; j <= arrayLengths[i]; j++)
		{
			int aux;
			cout << "Element " << j << " is : ";
			cin >> aux;
			list1[i].push_back(aux);
		}
		cout << "\n";
	}

	if (aux2 < 0)
	{
		cout << "Your input is greater than the total number of elements. Please try again.";
		return;
	}
	else if (aux2 > 0)
	{
		cout << "Your input is smaller than the total number of elements. Please try again.";
		return;
	}

	Merge(list1, arrayLengths, k, n, aux, b);
}


void Prof(int order)
{
	list <int> list1[MAX_SIZE];
	int k = 5;
	int arrayLengths[MAX_SIZE];
	int a[MAX_SIZE];
	int cnt = 0;
	int b = 0;

	while (cnt != 3)
	{
		for (int n = 100; n <= 10000; n = n + 100)
		{
			for (int j = 1; j <= 5; j++)
			{
				int aux = n;
				for (int i = 1; i <= k; i++)
				{
					arrayLengths[i] = n / k;
					FillRandomArray(a, n / k, 0, 10000, false, ASCENDING);
					list1[i].assign(a, a + n / k);
				}
				Merge(list1, arrayLengths, k, n, aux, b);
			}
		}
		b++;
		cnt++;
		if (cnt == 1)
			k = 10;
		else if (cnt == 2)
			k = 100;
	}
	
    profiler.divideValues("Merge_k_5", 5);
	profiler.divideValues("Merge_k_10", 5);
	profiler.divideValues("Merge_k_100", 5);
	profiler.createGroup("Merge", "Merge_k_5", "Merge_k_10", "Merge_k_100");
	profiler.showReport();

  /* int n = 10000;

    for (int k = 10; k <= 500; k = k + 10)
	{
		for (int j = 1; j <= 5; j++)
		{
			int aux = k;
			for (int i = 1; i <= k; i++)
			{
				arrayLengths[i] = n / k;
				FillRandomArray(a, n / k, 0, 10000, false, ASCENDING);
				list1[i].assign(a, a + n / k);
			}

			Merge(list1, arrayLengths, k, n, aux, b);
		}
	}
	profiler.divideValues("Merge_n_10000", 5);

	profiler.showReport(); */

}

int main()
{
	//Demo();
	//Prof(ASCENDING);

	return 0;
}