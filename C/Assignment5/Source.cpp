#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <time.h>
#include <cstdlib>
#include "Profiler.h"

Profiler profiler("HashTable");

using namespace std;

/**
 * @author Rusu Horia Gabriel
 * @group 30421

   HashTable:Search and Insert

   After creating the table we notice that both the average effort, as well as the max effort is much smaller in the cases
   where the element can be found in the table than when he isn't there, no matter the filling factor. When comparing the 
   numbers for the average effort for found, we see that there is a slight increase in the results. Same can be said for the
   max effort found. This is due to the fact that the input increases, such that it would be harder to find an element, reason
   why the effort grows. Same can be said for elements that are not in the table, the more the table is filled, the more it will
   have to go through the entire hashtable in order to realise that the element is not there. 
*/

#define _CRT_SECURE_NO_WARNINGS
#define N 9973

typedef struct {
	int id;
	char name[30];
} Entry;


Entry* CreateEntry(int key, char* str)
{
	Entry* p;
	p = (Entry*)malloc(sizeof(Entry));
	if (p)
	{
		p->id = key;
		strcpy_s(p->name, str);
    }
	return p;
}

Entry* CreateEntry2(int key)
{
	Entry* p;
	p = (Entry*)malloc(sizeof(Entry));
	if (p)
	{
		p->id = key;
	}
	return p;
}


int h(int key)
{
	int c1 = 11;
	int c2 = 23;

	return ((c1 * key + c2) % N);
}


int hprim(int key, unsigned long long i)
{
	int c3 = 43;
	int c4 = 17;

	return ((h(key) + c3 * i + c4 * i * i) % N);
}


int HashInsert(Entry* HashTable[N], Entry* a)
{
	  unsigned long long i=0;
	  int j;
      do {
		  j = hprim(a->id, i);
		  if (HashTable[j] == NULL)
		  {
			  HashTable[j] = a;
			  return j;
		  }
		  else
			 i++;
	  } while (i != N);

	  cout << "Overflow";
}

int HashInsert2(Entry* HashTable[N], int key)
{
	unsigned long long i = 0;
	int j;
	do {
		j = hprim(key, i);
		if (HashTable[j] == NULL)
		{
			Entry* a;
			a = CreateEntry2(key);
			HashTable[j] = a;
			return j;
		}
		else
			i++;
	} while (i != N);

	cout << "Overflow";

}

int HashSearch (Entry* HashTable[N], int key)
{
	int j;
	unsigned long long i = 0;
	do
	{
		j = hprim(key, i);

		if (HashTable[j] == NULL)
			return -1;

		if (HashTable[j]->id == key)
		{
			return j;
		}
		i++;
	} while (HashTable[j] != NULL || i != N);

	return -1;
}


int HashSearch2(Entry* HashTable[N], int key)
{
	int j = 0;
	int count = 0;
	unsigned long long i = 0;
	do
	{
		int j = hprim(key, i);
		count++;

		if (HashTable[j] == NULL)
			return count;

		if (HashTable[j]->id == key)
		{
			return count;
		}
		i++;
	} while (HashTable[j] != NULL || i != N);

	return count;
}

void Show(Entry* HashTable[N])
{
	cout << "\n";
	cout << "The HashTable is:";
	cout << "\n";

	for (int j = 0; j < N; j++)
	{
		if (HashTable[j] != NULL)
			cout << j << " " << HashTable[j]->id << " " << HashTable[j]->name << "\n";
	}
}

void Demo()
{
	Entry* HashTable[N];
	int n,l,p;
	int array1[100];
	char str[100];

	for (int i = 0; i < N; i++)
		HashTable[i] = NULL;


	cout << "Number of elements to be put in the Hash Table: ";
	cin >> n;

	for (int i = 0; i < n; i++)
	{
		cout << "Id " << i << " to be put is: ";
		cin >> array1[i];

		cout << "Name " << i << " to be put is: ";
		scanf_s("%s", str);

		Entry* a;
		a = CreateEntry(array1[i], str);

		int j = HashInsert(HashTable, a);
	}

	Show(HashTable);

	cout << "\n";
	cout << "Write Id to be searched for: ";
	cout << "\n";
	cin >> l;
	p = HashSearch(HashTable, l);
	if (p == -1)
		cout << "The Number isn't here. Try again.";
	else
		cout << "The Number is at position " << p << " and the name is " << HashTable[p]->name;
}

void TableGenerator()
{
	double alpha = 0.8;
	int beta = 0;
	cout << "The Table is:\n";
	cout << "Filling Factor  " << "Avg. Effort Found  " << "Max. Effort Found  " << "Avg. Effort Not-Found  " << "Max. Effort Not-Found  ";
	cout << "\n";

	while (alpha != 1)
	{
		Entry* HashTable[N];
		int a[N];
		int a1[N];
		int n = alpha * N;

		int total1[100];
		int total2[100];
		int max1[100];
		int max2[100];

		for (int i = 0; i < 5; i++)
		{
			total1[i] = 0;
			total2[i] = 0;
			max1[i] = 0;
			max2[i] = 0;
		}

		float finaltotal1[100];
		float finaltotal2[100];
		for (int i = 0; i < 5; i++)
		{
			finaltotal1[i] = 0;
			finaltotal2[i] = 0;
		}

		for (int q = 0; q < 5; q++)
		{
			int cnt1 = 1500;
			int cnt2 = 1503;
			int auxil1 = cnt1;
			int auxil2 = cnt2;

			for (int i = 0; i < N; i++)
				HashTable[i] = NULL;

			FillRandomArray(a, n, 0, 100000, false, 0);
			for (int i = 0; i < n; i++)
			{
				a1[i] = a[i];
				int j = HashInsert2(HashTable, a[i]);
			}

			while (cnt1 != 0)
			{
				int b[100];
				FillRandomArray(b, 1, 0, n, false, 0);
				int l = HashSearch2(HashTable, a[b[0]]);
				total1[q] = total1[q] + l;
				if (l > max1[q])
					max1[q] = l;
				cnt1--;
			}

			finaltotal1[q] = (float)total1[q] / auxil1;


			while (cnt2 != 0)
			{
				int count = 0;
				int b[100];
				FillRandomArray(b, 1, 0, 2000000, false, 0);
				if (b[0] < 100000)
				{
					b[0] = b[0] + 100000;
				}

				int s = HashSearch2(HashTable, b[0]);
				total2[q] = total2[q] + s;
				if (s > max2[q])
					max2[q] = s;
				cnt2--;
			}

			finaltotal2[q] = (float)total2[q] / auxil2;
		}

		float aux1 = 0;
		int aux2 = 0;
		int aux3 = 0;
		float aux4 = 0;

		for (int k = 0; k < 5; k++)
		{
			aux1 = aux1 + finaltotal1[k];
			aux2 = aux2 + max1[k];
			aux3 = aux3 + max2[k];
			aux4 = aux4 + finaltotal2[k];
		}

		float final1;
		float final2;
		int finalmax;
		int finalmax2;
		final1 = aux1 / 5;
		finalmax = aux2 / 5;
		final2 = aux4 / 5;
		finalmax2 = aux3 / 5;
		printf("%.2f                  %.2f               %d                   %.2f                  %d ", alpha, final1, finalmax, final2, finalmax2);
		cout << "\n";
		beta++;
		if (beta == 1)
			alpha = 0.85;
		if (beta == 2)
			alpha = 0.9;
		if (beta == 3)
			alpha = 0.95;
		if (beta == 4)
			alpha = 0.99;
		if (beta == 5)
			alpha = 1;
	}

}

int main()
{
	//Demo();
	//TableGenerator();

    return 0;
}
