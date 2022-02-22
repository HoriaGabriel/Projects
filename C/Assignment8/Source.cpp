#include <cstdlib>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <iostream>
#include <fstream>
#include "Profiler.h"


/**
 * @author Rusu Horia Gabriel
 * @group 30421

   Disjoint Sets
   When analyzing the graph, one can see that all three disjoint set operations have a O(n) time complexity. In case of MakeSet
   the complexity is O(1) but the function is called n times, according to the nunber of nodes. In case of FindSet, it takes 
   O(n) to run trough all the edges and find the parents of the nodes at both ends, as to be able to see whether or not they
   have the same parent. When it comes to Union, the FindSet is performed again for the nodes, after which the link is made.
   When comparing the three, we can see that the smallest one is MakeSet, since it only consists of two assignments, followed 
   by Union and finally The most time is taken by FindSet because we search for the parents twice, both when comparing the
   two ends, as well as when making the Union.
*/

Profiler profiler ("DisjointSets");

using namespace std;

#define MAX_SIZE 40001

typedef struct Node
{
	int key;
	int rank;
	struct Node* parent;

}NodeT;

typedef struct edge
{
	NodeT* begin;
	NodeT* end;
	int weight;

}Edge;


void PrintMST(Edge* e[MAX_SIZE], int A[MAX_SIZE], int k)
{
	cout << "Following are the edges in the constructed MST\n";

	for (int i = 1; i <= k; i++)
	{
		for (int j = 1; j <= k; j++)
		{
			if (A[j] == e[i]->weight)
			{
				cout << e[i]->begin->key << " -- " << e[i]->end->key << " == " << e[i]->weight;
				cout << "\n";
			}
		}
	}
}

void PrintGraph(Edge* e[MAX_SIZE], int k)
{
	cout << "Following is the Graph:\n";

	for (int i = 1; i <= k; i++)
	{
	  cout << e[i]->begin->key << " -- " << e[i]->end->key << " == " << e[i]->weight;
	  cout << "\n";
	}
}


void MAKE_SET(NodeT* r, int aux)
{
	Operation o1 = profiler.createOperation("MakeSet", aux);
	o1.count(2);
	r->parent = r;
	r->rank = 0;
}

NodeT* FIND_SET(NodeT* r, int aux)
{
	Operation o3 = profiler.createOperation("FindSet", aux);

	o3.count();
	if (r != r->parent)
	{
		o3.count();
		r->parent = FIND_SET(r->parent, aux);
	}
	return r->parent;
}

void LINK(NodeT* x, NodeT* y, int aux)
{
	Operation o2 = profiler.createOperation("Union", aux);

	if (x->rank > y->rank)
	{
		y->parent = x;
		o2.count(2);
	}
	else
	{
		x->parent = y;
		o2.count(2);
		if (x->rank == y->rank)
		{
			o2.count(2);
			y->rank++;
		}
	}
}

void UNION(NodeT* x, NodeT* y, int aux)
{
	Operation o2 = profiler.createOperation("Union", aux);

	LINK(FIND_SET(x, aux), FIND_SET(y, aux), aux);
}

NodeT* FIND_SET2(NodeT* r, int aux)
{
	Operation o3 = profiler.createOperation("FindSet", aux);
	Operation o2 = profiler.createOperation("Union", aux);

	o3.count();
	o2.count();
	if (r != r->parent)
	{
		o3.count();
		o2.count();
		r->parent = FIND_SET(r->parent, aux);
	}
	return r->parent;
}

void UNION2(NodeT* x, NodeT* y, int aux)
{
	Operation o2 = profiler.createOperation("Union", aux);

	LINK(FIND_SET2(x, aux), FIND_SET2(y, aux), aux);
}


void MergeSort(Edge* e[MAX_SIZE], int n)
{
	int i, j;
	int helper = 0;
	int min = 0;
	for (i = 1; i <= n; i++)
	{
		min = i;
		for (j = i + 1; j <= n; j++)
		{
			if (e[min]->weight > e[j]->weight)
				min = j;
		}

		if (min != i)
		{
            helper = e[i]->weight;
			e[i]->weight = e[min]->weight;
			e[min]->weight = helper;

			NodeT* helper2 = e[i]->begin;
			e[i]->begin = e[min]->begin;
			e[min]->begin = helper2;

			NodeT* helper3 = e[i]->end;
			e[i]->end = e[min]->end;
			e[min]->end = helper3;
		}
	}

}

void MST_KRUSKAL(NodeT* v[MAX_SIZE], Edge* e[MAX_SIZE], int n, int k, int aux)
{
	Operation o1 = profiler.createOperation("MakeSet", aux);
	Operation o3 = profiler.createOperation("FindSet", aux);
	Operation o2 = profiler.createOperation("Union", aux);

    int A[MAX_SIZE];
	int t = 1;

	for (int i = 1; i <= n; i++)
	{
		MAKE_SET(v[i],aux);
		o1.count();
	}

	MergeSort(e, k);

	for (int j = 1; j <= k; j++)
	{
		o3.count();
		if (FIND_SET(e[j]->begin, aux) != FIND_SET(e[j]->end, aux))
		{
			o2.count();
			A[t] = e[j]->weight;
			UNION2(e[j]->begin, e[j]->end, aux);
			t++;
		}
	}

    //PrintMST(e, A, k); 

}


void BuildGraph(NodeT* v[100], Edge* e[100], int n, int k)
{
	for (int i = 1; i <= n; i++)
	{
		v[i] = (NodeT*)malloc(sizeof(NodeT));
		v[i]->key = i;
	}

	for (int j = 1; j <= k; j++)
	{
		e[j] = (Edge*)malloc(sizeof(Edge));
		int h, c;
		cout << "The edge " << j << " starts at: ";
		cin >> h;
		e[j]->begin = v[h];
		cout << "The edge " << j << " ends at: ";
		cin >> c;
		e[j]->end = v[c];
		cout << "The edge " << j << " has the weight: ";
		cin >> e[j]->weight;
	}

	//PrintGraph(e, k);
}

void BuildGraph2(NodeT* v[MAX_SIZE], Edge* e[MAX_SIZE], int n, int k)
{
	for (int i = 1; i <= n; i++)
	{
		v[i] = (NodeT*)malloc(sizeof(NodeT));
		v[i]->key = i;
	}

	int j = 1;

	while(j<=k)
	{
		int b1[10];
		int b2[10];
		int b3[10];
		FillRandomArray(b1, 2, 1, n, false, 0);
		FillRandomArray(b2, 2, 1, n, false, 0);
		FillRandomArray(b3, 2, 1, 50000, false, 0);
		if (b1[0] != b2[0])
		{
			e[j] = (Edge*)malloc(sizeof(Edge));

			for (int g = 1; g <= n; g++)
			{
				if (v[g]->key == b1[0])
				{
					e[j]->begin = v[g];
				}
			}
			
			for (int h = 1; h <= n; h++)
			{
				if (v[h]->key == b2[0])
				{
					e[j]->end = v[h];
				}
			}

			e[j]->weight = b3[0];
			j++;
        }
	}
}


void Demo()
{
	NodeT* v[100];
	int n,l,t=0;
	cout << "How many nodes do you want? ";
	cin >> n;
	for (int i = 1; i <= n; i++)
	{
		v[i] = (NodeT*)malloc(sizeof(NodeT));
		v[i]->key = i;
	}
	for (int j = 1; j <= n; j++)
		MAKE_SET(v[j],0);

	for (int j = 1; j < n/2; j++)
	{
		cout << "The element " << j << " with parent " << v[j]->parent->key << " and ";
		cout << " element " << j + 1 << " with parent " << v[j + 1]->parent->key << " results in them having parent ";
		UNION(v[j], v[j + 1], t);
		cout << v[j]->parent->key << "\n";
	}

	for (int j = n/2 + 1; j < n; j++)
	{
		cout << "The element "<<j<<" with parent " << v[j]->parent->key << " and ";
		cout << " element " << j + 1 << " with parent " << v[j + 1]->parent->key << " results in them having parent ";
		UNION(v[j], v[j + 1], t);
		cout << v[j]->parent->key << "\n";
	}

	cout << "The element 1" << " with parent " << v[1]->parent->key << " and ";
	cout << " element " << n << " with parent " << v[n]->parent->key << " results in them having parent ";
	UNION(v[1], v[n], t);
	cout << v[n]->parent->key << "\n\n";
	cout << "\nAfter the union of the first and last element\n";

	for (int j = 1; j <= n; j++)
	{
		cout << "The element " << j;
		NodeT* aux = (NodeT*)malloc(sizeof(NodeT));
		aux = FIND_SET(v[j], t);
		cout << " has parent " << aux->key << "\n";
	}

}

void DemoKruskal()
{
	NodeT* v[100];
	Edge* e[100];
	int n, k;
	int aux = 0;
	cout << "The number of nodes is: ";
	cin >> n;
	cout << "The number of edges is: ";
	cin >> k;

	BuildGraph(v, e, n, k);
    MST_KRUSKAL(v, e, n, k, aux);
}


void Prof()
{
	for (int j = 1; j <= 5; j++)
	{
		for (int n = 100; n <= 10000; n = n + 100)
		{
			//cout << n << " ";
			int aux = n;
			NodeT* v[MAX_SIZE];
			Edge* e[MAX_SIZE];
			BuildGraph2(v, e, n, n * 4);
			MST_KRUSKAL(v, e, n, n * 4, aux);
		}
	}

	profiler.divideValues("MakeSet", 5);
	profiler.divideValues("FindSet", 5);
	profiler.divideValues("Union", 5);
	profiler.createGroup("BaseOperations", "MakeSet", "Union", "FindSet");
	profiler.showReport();
}

int main()
{
	//Demo();
	//DemoKruskal();
	//Prof();

	return 0;
}
