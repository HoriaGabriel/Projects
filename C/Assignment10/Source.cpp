#include <cstdlib>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <iostream>
#include <fstream>
#include <list>
#include <iterator>
#include <stack>
#include <string.h>
#include "Profiler.h"


/**
 * @author Rusu Horia Gabriel
 * @group 30421

   DFS
   After performing the DFS algorithm, one can see that the overall complexity of DFS is O(V+E). In the case where the
   number of edges is constant and we vary the number of vertices, one can see that the complexity is O(n), where n is the
   number of vertices, varying from 100 to 200. On the other hand, when the number of vertices is constant and we vary the
   number of edges, the complexity is also O(n), but now n represents the number of edges, varying from 1000 to 5000.

*/

using namespace std;

enum {
	COLOR_WHITE = 0,
	COLOR_GRAY,
	COLOR_BLACK
};

typedef struct _Node {
	int adjSize;
	struct _Node** adj;

	int color;
	int dist;
	int discoveryTime;
	int finalTime;
	int low;
	struct _Node* parent;

}Node;

typedef struct {
	int nrNodes;
	Node** v;
}Graph;

int t = 0;
std::list<int>q;
std::stack<int>st;

int search(Graph* graph, Node* aux)
{
	for (int i = 0; i < graph->nrNodes; i++)
	{
		if (aux == graph->v[i])
			return i;
	}

	return 0;
}


void StronglyConnected(Graph* graph, int g, bool stackMember[])
{
	int w = 0;
	if (graph->v[g]->discoveryTime == graph->v[g]->low)
	{
		cout << "Strongely connected components are: ";
		while (st.top() != g)
		{
			w = st.top();
			cout << w << " ";
			stackMember[w] = false;
			st.pop();
		}
		w = (int)st.top();
		cout << w << "\n";
		stackMember[w] = false;
		st.pop();
	}
}

void topological(Graph* graph)
{
	cout << "Topological sort:\n";
	while (q.size() > 0)
	{
		cout <<"Node "<< q.front() << " with final time:  ";
		cout << graph->v[q.front()]->finalTime << " ";
		q.pop_front();
		cout << "\n";
	}
}

void AdjacencyList(Graph* graph)
{
	cout << "Adjacency list:\n";
	for (int i = 0; i < graph->nrNodes; i++)
	{
		for (int j = 0; j < graph->v[i]->adjSize; j++)
		{
			cout << "Node " << i << " has adjacent node " << search(graph, graph->v[i]->adj[j]);
			cout << "\n";
		}
	}
}

void dfs_visit(Graph* graph, Node* s, int time, bool stackMember[], Operation* op)
{
	t++;
	int g = search(graph, s);

	if (op != NULL)
		op->count(5);

	graph->v[g]->color = COLOR_GRAY;
	graph->v[g]->discoveryTime = graph->v[g]->low = t;
	graph->v[g]->low = t;
	stackMember[g] = true;
	st.push(g);

	for (int k = 0; k < graph->v[g]->adjSize; k++)
	{
		if (op != NULL)
			op->count();

		if (graph->v[g]->adj[k]->color == COLOR_WHITE)
		{
			if (op != NULL)
				op->count(2);

			graph->v[g]->adj[k]->parent = graph->v[g];

			dfs_visit(graph, graph->v[g]->adj[k], t, stackMember, op);

			graph->v[g]->low = min(graph->v[g]->low, graph->v[g]->adj[k]->low);
		}
		else if (stackMember[search(graph, graph->v[g]->adj[k])] == true)
		{
            graph->v[g]->low = min(graph->v[g]->low, graph->v[g]->adj[k]->discoveryTime);
		}

	}


	if (op != NULL)
		op->count(4);

	graph->v[g]->color = COLOR_BLACK;
	t++;
	graph->v[g]->finalTime = t;
	q.push_front(g);

	if(op==NULL)
	   StronglyConnected(graph, g, stackMember);

}

void dfs(Graph* graph, Operation* op)
{
	bool stackMember[5000];
	for (int i = 0; i < graph->nrNodes; i++)
	{
		if (op != NULL)
			op->count(3);

		graph->v[i]->color = COLOR_WHITE;
		graph->v[i]->parent = NULL;
		graph->v[i]->low = NULL;
	}

	for (int i = 0; i < graph->nrNodes; i++)
	{
		if (op != NULL)
			op->count();

		stackMember[i] = false;
	}

	for (int j = 0; j < graph->nrNodes; j++)
	{
		if (op != NULL)
			op->count();

		if (graph->v[j]->color == COLOR_WHITE)
		{
			dfs_visit(graph, graph->v[j], t, stackMember, op);
		}
	}
}

void Demo()
{
	int q;
	cout << "Press 0 for the already prepared example and 1 for self-input: ";
	cin >> q;

	if (q == 0)
	{
		Graph graph;

		cout << "The number of nodes is: 6";
		graph.nrNodes = 6;

		graph.v = (Node **)malloc(graph.nrNodes * sizeof(Node*));
		for (int i = 0; i < graph.nrNodes; ++i) {
			graph.v[i] = (Node*)malloc(sizeof(Node));
		}

		for (int i = 0; i < graph.nrNodes; i++)
		{
			graph.v[i]->adjSize = 0;
			graph.v[i]->adj = (Node * *)malloc(graph.nrNodes * sizeof(Node));

			for (int k = 0; k < graph.nrNodes; k++)
			{
				graph.v[i]->adj[k] = (Node*)malloc(sizeof(Node));
				graph.v[i]->adj[k] = NULL;
			}
		}

		graph.v[0]->adj[0] = graph.v[1];
		graph.v[0]->adj[1] = graph.v[3];
		graph.v[0]->adjSize = 2;

		graph.v[1]->adj[0] = graph.v[2];
		graph.v[1]->adjSize = 1;

		graph.v[2]->adj[0] = graph.v[3];
		graph.v[2]->adjSize = 1;

		graph.v[3]->adj[0] = graph.v[1];
		graph.v[3]->adjSize = 1;

		graph.v[4]->adj[0] = graph.v[3];
		graph.v[4]->adj[1] = graph.v[5];
		graph.v[4]->adjSize = 2;

		graph.v[5]->adj[0] = graph.v[4];
		graph.v[5]->adjSize = 1;

		cout << "\n\n";
		AdjacencyList(&graph);
		cout << "\n\n";
		dfs(&graph, NULL);
		cout << "\n\n";
		topological(&graph);

	}
	else
	{
		Graph graph;

		cout << "The number of nodes is: ";
		cin >> graph.nrNodes;

		graph.v = (Node * *)malloc(graph.nrNodes * sizeof(Node*));
		for (int i = 0; i < graph.nrNodes; ++i) {
			graph.v[i] = (Node*)malloc(sizeof(Node));
		}

		for (int i = 0; i < graph.nrNodes; i++)
		{
			graph.v[i]->adjSize = 0;
			graph.v[i]->adj = (Node * *)malloc(graph.nrNodes * sizeof(Node));

			for (int k = 0; k < graph.nrNodes; k++)
			{
				graph.v[i]->adj[k] = (Node*)malloc(sizeof(Node));
				graph.v[i]->adj[k] = NULL;
			}
		}

		for (int i = 0; i < graph.nrNodes; i++)
		{
			cout << "The adjacency size of node " << i << " is: ";
			cin >> graph.v[i]->adjSize;
			for (int j = 0; j < graph.v[i]->adjSize; j++)
			{
				int k;
				cout << "Write node to be adjacent: ";
				cin >> k;
				graph.v[i]->adj[j] = graph.v[k];
			}
		}

		cout << "\n\n";
		AdjacencyList(&graph);
		cout << "\n\n";
		dfs(&graph, NULL);
		cout << "\n\n";
		topological(&graph);
	}
}

void Prof()
{
	int n, i;
	Profiler p("dfs");


	for (n = 1000; n <= 5000; n += 100) {
		Operation op = p.createOperation("dfs-edges", n);
		Graph graph;
		graph.nrNodes = 100;
		graph.v = (Node **)malloc(graph.nrNodes * sizeof(Node*));
		for (i = 0; i < graph.nrNodes; ++i) {
			graph.v[i] = (Node*)malloc(sizeof(Node));
		}

		for (i = 0; i < graph.nrNodes; i++)
		{
			graph.v[i]->adjSize = 0;
			graph.v[i]->adj = (Node **)malloc(graph.nrNodes * sizeof(Node));

			for (int k = 0; k < graph.nrNodes; k++)
			{
				graph.v[i]->adj[k] = (Node*)malloc(sizeof(Node));
				graph.v[i]->adj[k] = NULL;
			}
		}

		int u = 1;

		while (u < n)
		{
			int w[100];
			FillRandomArray(w, 3, 0, graph.nrNodes - 1, false, 0);

			if (u < graph.nrNodes)
			{
				FillRandomArray(w, 3, 0, graph.nrNodes - 1, false, 0);
				w[0] = u;
				graph.v[w[0]]->adj[graph.v[w[0]]->adjSize] = graph.v[w[1]];
				graph.v[w[0]]->adjSize++;
				u++;
			}
			else if (u >= graph.nrNodes && graph.v[w[0]]->adj[graph.v[w[0]]->adjSize] == NULL)
			{
				graph.v[w[0]]->adj[graph.v[w[0]]->adjSize] = graph.v[w[1]];
				graph.v[w[0]]->adjSize++;
				u++;
			}
		}

		dfs(&graph,&op);
	}


	for (n = 100; n <= 200; n += 10) {
		Operation op = p.createOperation("dfs-vertices", n);
		Graph graph;
		graph.nrNodes = n;
		
		graph.v = (Node **)malloc(graph.nrNodes * sizeof(Node*));
		for (i = 0; i < graph.nrNodes; ++i) {
			graph.v[i] = (Node*)malloc(sizeof(Node));
		}
	
		for (i = 0; i < graph.nrNodes; i++)
		{
			graph.v[i]->adjSize = 0;
			graph.v[i]->adj = (Node **)malloc(graph.nrNodes * sizeof(Node));

			for (int k = 0; k < graph.nrNodes; k++)
			{
				graph.v[i]->adj[k] = (Node*)malloc(sizeof(Node));
				graph.v[i]->adj[k] = NULL;
			}
		}

		int h = 1;

        while (h < 9000)
		{
			int w[100];
			FillRandomArray(w, 3, 0, graph.nrNodes - 1, false, 0);

			if (h < graph.nrNodes)
			{
				FillRandomArray(w, 3, 0, graph.nrNodes - 1, false, 0);
				w[0] = h;
				graph.v[w[0]]->adj[graph.v[w[0]]->adjSize] = graph.v[w[1]];
				graph.v[w[0]]->adjSize++;
				h++;
			}
			else if (h >= graph.nrNodes && graph.v[w[0]]->adj[graph.v[w[0]]->adjSize] == NULL)
			{
				graph.v[w[0]]->adj[graph.v[w[0]]->adjSize] = graph.v[w[1]];
				graph.v[w[0]]->adjSize++;
				h++;
			}

		}

		dfs(&graph, &op);
	}

	p.showReport();
}


int main()
{
	//Demo();
	//Prof();

	return 0;
}
