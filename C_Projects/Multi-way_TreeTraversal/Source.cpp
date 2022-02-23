#include <stdio.h>
#include <stdlib.h>
#include <cstdlib>
#include <list>
#include <iterator> 
#include <iostream>

/**
 * @author Rusu Horia Gabriel
 * @group 30421

   Multi-way tree traversal

   For the 3 representations we used a struct for all of them, as additional memory. 
   The first has the node key and the parent. The second structure, Tree2 has the key, the count, to see how many children each node has,
   and array of pointers for the children nodes. The third structure, Tree3 holds the key, and node pointers for the node to the right of 
   the current node, as well as to the first child of the node. Lists have been used to store the elements from the r1 represenatation,
   before inserting them in the r2 tree. For the print of both R2 and R3 a slightly changed preoreder traversal was used.
   Overall the time for the changes in both cases seems to be O(n) because in both cases a single while/for structure is being used along with
   recursivity, to traverse the nodes and put them in the appropriate order.
*/

using namespace std;

typedef struct 
{
    int key;
	int parent;

}Tree1;


typedef struct  MultiTreeNode
{
    int key;
	int count;
    struct MultiTreeNode* children[20];

}Tree2;


typedef struct BinaryTreeNode
{
	int key;
	struct BinaryTreeNode* child, *right;

}Tree3;


Tree2* T1ParentMultiWay(Tree2* r2, list <int> l[], int root)
{
	int i = 0;
	r2->count = 0;
	while (l[root].size() != 0)
	{
		r2->children[i] = (Tree2*)malloc(sizeof(Tree2));
	    r2->children[i]->key = l[root].front();
		r2->count++;
		T1ParentMultiWay(r2->children[i], l, r2->children[i]->key);
		l[root].pop_front();
		i++;
	}

	return 0;
}


Tree3* T2MultiWayBinary(Tree3* r3, Tree2* r2)
{
	r3->child = NULL;
	r3->right = NULL;
	
	for(int i=0; i < r2->count; i++)
	{
		if (i == 0)
		{
			r3->child = (Tree3*)malloc(sizeof(Tree3));
			r3->child->key = r2->children[i]->key;
			r3 = r3->child;
			T2MultiWayBinary(r3, r2->children[i]);
		}
        else
		{
			r3->right = (Tree3*)malloc(sizeof(Tree3));
			r3->right->key = r2->children[i]->key;
			r3 = r3->right;
			T2MultiWayBinary(r3, r2->children[i]);
		}

	}

	return 0;
}


void PrettyPrintR2(Tree2* r2, int level)
{

	for (int i = 0; i <= level; i++)cout << "     ";
	cout << r2->key;
	cout << "\n";

    for( int i=0; i<r2->count; i++)
	{
		PrettyPrintR2(r2->children[i],level+1);
	}
}


void PrettyPrintR3(Tree3* r3, int level)
{
	if (r3 != NULL)
	{
		for (int i = 0; i <= level; i++)cout << "    ";
        cout << r3->key << "\n";
		PrettyPrintR3(r3->child, level+1);
		PrettyPrintR3(r3->right, level);
	}
}

void Demo()
{
	int b;
	int root = 0;
	list <int> l[100];
	cout << "Press 0 for first already prepared example, 1 for second already prepared example and 2 for manual input: ";
	cin >> b;
	if (b == 0)
	{
		int v[] = { 0, 2, 7, 5, 2, 7, 7, -1, 5, 2 };

		cout << "R1: ";
		for (int h = 1; h <= 9; h++)
			cout << v[h] << " ";

		Tree1 r1[100];

		for (int i = 1; i <= 9; i++)
			r1[i].key = 0;


		for (int i = 1; i <= 9; i++)
		{
			if (v[i] == -1)
			{
				root = i;
				r1[i].key = root;
			}
			else
			{
				r1[i].key = i;
				r1[i].parent = v[i];
			}
		}

		for (int i = 1; i <= 9; i++)
		{
			if (r1[i].parent == v[i])
			{
				l[v[i]].push_back(i);
			}
		}
	}

	else if (b == 1)
	{
		int v[] = { 0,2,4,2,-1,4,4,2 };   

		cout << "R1: ";
		for (int h = 1; h <= 7; h++)
			cout << v[h] << " ";

		Tree1 r1[100];

		for (int i = 1; i <= 7; i++)
			r1[i].key = 0;


		for (int i = 1; i <= 7; i++)
		{
			if (v[i] == -1)
			{
				root = i;
				r1[i].key = root;
			}
			else
			{
				r1[i].key = i;
				r1[i].parent = v[i];
			}
		}

		for (int i = 1; i <= 7; i++)
		{
			if (r1[i].parent == v[i])
			{
				l[v[i]].push_back(i);
			}
		}
	}
	else
	{
		int v[100];
		int n;
		cout << "Read number of elements to be put in tree: ";
		cin >> n;
		cout << "Input elements, be careful first element will not be added to tree, because it's on position 0.\n";
		for (int h = 0; h <= n; h++)
		{
			cout << "Element " << h << " is: ";
			cin >> v[h];
		}

		cout << "\nR1: ";
		for (int h = 1; h <= n; h++)
			cout << v[h] << " ";

		Tree1 r1[100];

		for (int i = 1; i <= n; i++)
			r1[i].key = 0;


		for (int i = 1; i <= n; i++)
		{
			if (v[i] == -1)
			{
				root = i;
				r1[i].key = root;
			}
			else
			{
				r1[i].key = i;
				r1[i].parent = v[i];
			}
		}

		for (int i = 1; i <= n; i++)
		{
			if (r1[i].parent == v[i])
			{
				l[v[i]].push_back(i);
			}
		}

	}

	cout << "\n\n";

	Tree2* r2 = (Tree2*)malloc(sizeof(Tree2));
	r2->key = root;
	T1ParentMultiWay(r2, l, root);
	cout << "R2:\n";
	PrettyPrintR2(r2, 0);

	cout << "\n";

	Tree3* r3 = (Tree3*)malloc(sizeof(Tree3));
	r3->key = root;
	T2MultiWayBinary(r3, r2);
	cout << "R3:\n";
	PrettyPrintR3(r3, 0);
}


int main()
{
	//Demo();

    return 0;
}