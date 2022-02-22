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

   Dynamic Order Statistics

   After completing the algorithm, one can see that both the Select and the Delete function have a O(n*logn) time complexity, where
   we repeat the log n, which coresponds to the height h of the tree, and we repeat the operation n times, thus it being in the end 
   n*logn. We can also see when comparing them, that the selects has a smaller number of operations when compared to the delete, probably
   due to the fact that the delete also counts the finding of the succesor operation, whose key will be replaced by the node to be deleted.
   On the other hand, the Build will have a clear O(n) complexity, coresponding to the number of nodes to be put in the tree.

*/

Profiler profiler("DynamicOrderStatistics");

using namespace std;

typedef struct NodeT
{
	int key;
	int size;
	struct NodeT* right;
	struct NodeT* left;
	struct NodeT* parent;

}NodeT;


void PrettyPrint(NodeT* r3, int level)
{
	if (r3 != NULL)
	{
		for (int i = 0; i <= level; i++)cout << "    ";
		cout << r3->key << "\n";
		PrettyPrint(r3->right, level + 1);
		PrettyPrint(r3->left, level + 1);
	}
}

NodeT* BuildTree(int first, int last, NodeT* parent, int aux)
{
	Operation o = profiler.createOperation("Build", aux);

    if (first > last)
		return NULL;

	int mid = (first + last) / 2;
	o.count(6);

	NodeT* n = (NodeT*)malloc(sizeof(NodeT));
	n->key = mid;
	n->left = NULL;
	n->right = NULL;
	n->size = 1;
	n->parent = parent;

	n->left = BuildTree(first, mid-1, n, aux);
	n->right = BuildTree( mid+1, last, n, aux);

	if (n->left != NULL)
	{
		o.count(2);
		n->size = n->size + n->left->size;
	}
	if (n->right != NULL)
	{
		o.count(2);
		n->size = n->size + n->right->size;
	}

	return n;
}


NodeT* OS_Select(NodeT* x, int i, int n)
{
    int r=0;

	if (x->left != NULL)
    {
       r = x->left->size + 1;
    }
	else
		r = 1;

	if (i == r)
		return x;
	else if (i < r)
		return OS_Select(x->left, i, n);
	else
		return OS_Select(x->right, i - r, n);
}

NodeT* OS_Select2(NodeT* x, int i, int n)
{
    Operation o1 = profiler.createOperation("Select", n);
    Operation o2 = profiler.createOperation("Delete", n);

    int r = 0;
	x->size--;
	o2.count();
	o1.count();

	if (x->left != NULL)
    {
        r = x->left->size + 1;
        o1.count();
    }
	else
		r = 1;

	if (i == r)
	{
		return x;
	}
	else if (i < r)
	{
		return OS_Select2(x->left, i, n);
	}
	else
	{
		return OS_Select2(x->right, i - r, n);
	}
}

NodeT* FindTreeMin(NodeT* z, int n)
{
    Operation o = profiler.createOperation("Delete", n);

	o.count();
	z->size--;
	while (z->left != NULL)
    {
        o.count(3);
        z = z->left;
        z->size--;
    }
	return z;
}

NodeT* TreeSuccesor(NodeT* z, int n)
{
    Operation o = profiler.createOperation("Delete", n);

    o.count();
	if (z->right != NULL)
		return FindTreeMin(z->right,n);

	NodeT* y;
	o.count();
	y = z->parent;

	while (y != NULL && z == y->right)
	{
	    o.count(2);
		z = y;
		z->size--;
		y = y->parent;
	}

	return y;
}

NodeT* OS_DeleteProcedure(NodeT* T, NodeT* z, int n)
{
	Operation o = profiler.createOperation("Delete", n);

	NodeT* y;
	NodeT* x = NULL;

	o.count();
	if (z->left == NULL || z->right == NULL)
	{
		y = z;
		o.count();
	}
	else
	{
		y = TreeSuccesor(z,n);
		o.count();
	}

  
	o.count();
	if (y->left != NULL)
	{
		x = y->left;
		o.count();
	}
	else
	{
		x = y->right;
		o.count(2);
	}

	
	o.count();
	if (x != NULL)
	{
		x->parent = y->parent;
		o.count();
	}

	o.count();
	if (y->parent == NULL)
	{
	    T = x;
		o.count();
	}
	else if (y == y->parent->left)
	{
		y->parent->left = x;
		o.count(2);
	}
	else if (y == y->parent->right)
	{
		y->parent->right = x;
		o.count(2);
	}

	return y;

}


NodeT* OS_Delete(NodeT* T, int i, int n)
{
	Operation o = profiler.createOperation("Delete", n);

	int auxil = T->key;

	NodeT* aux = OS_Select2(T, i, n);

	NodeT* aux2 = OS_DeleteProcedure(T, aux, n);

	o.count();
    aux->key = aux2->key;
	delete(aux2);

	if (auxil  == T->key && aux->key == T->key)
	{
		if (T->left != NULL)
			T = T->left;
		else if (T->right != NULL)
			T = T->right;
		else
		{
			T = NULL;
			return NULL;
		}
	}

	return T;
}


void Demo()
{
	int n, p, z, t, w1 = 1, w2 = 1;
	int f = 0;
	cout << "Write size of tree: ";
	cin >> n;
	z = n;

	NodeT* r = BuildTree(1, n, NULL, f);
	cout << "The Tree is:\n";
	PrettyPrint(r, 0);

	while (w1 != 0)
	{
		cout << "\nWrite ith smallest node to be selected ";
		cin >> p;
		NodeT* aux = OS_Select(r, p, f);
		cout << "\nThe selected node is " << aux->key;
		cout << "\nDo you want to continue? If yes press 1, else press 0. ";
		cin >> w1;
	}

	while (w2 != 0)
	{
		if (z == 0)
			break;
		else
		{
			z--;
			cout << "\nWrite ith smallest node to be deleted ";
			cin >> t;
			NodeT* a = OS_Delete(r, t, f);
			if (a != r)  r = a;
			cout << "\nAfter deletion the tree is:\n";
			PrettyPrint(r, 0);
			cout << "\nDo you want to continue? If yes press 1, else press 0. ";
			cin >> w2;
		}
	}

}


void Prof()
{
    for(int n=100;n<=10000;n=n+100)
	{
		for(int j=1;j<=5;j++)
		{
			int aux = n;
			NodeT* r = BuildTree(1, n, NULL, aux);
			int k = n;

			while(k > 1)
			{
				int b[2];
				FillRandomArray(b, 1, 0, k, false, 0);
				int max = k;
				if (b[0] <= max && b[0]!=0)
				{
					NodeT* a = OS_Delete(r, b[0], aux);
					if (a != r) r = a;
					k--;
				}
			}

		}
	}

	profiler.divideValues("Build", 5);
	profiler.divideValues("Delete", 5);
	profiler.divideValues("Select", 5);
    profiler.showReport();
}


int main()
{
	//Demo();
	//Prof();

	return 0;
}
