#include <stdlib.h>
#include <string.h>
#include <iostream>
#include "Profiler.h"
#include "bfs.h"
#include <queue>

using std::queue;

/**
 * @author Rusu Horia Gabriel
 * @group 30421

   BFS
   After running the algorithm, one can see that the total running time of BFS is O(V+E). In our two cases, when we vary
   the number of vertices and keep the edges constant, the algorithm has a O(n) complexity, where n is the number of vertices.
   The same can be said for the case where we vary the number of edges and keep the vertices constant, the complexity is
   O(n) where n represents the number of edges.
  
*/

int get_neighbors(const Grid *grid, Point p, Point neighb[])
{
    // TODO: fill the array neighb with the neighbors of the point p and return the number of neighbors
    // the point p will have at most 4 neighbors (up, down, left, right)
    // avoid the neighbors that are outside the grid limits or fall into a wall
    // note: the size of the array neighb is guaranteed to be at least 4

	int count=0;
	int i = 0;

    if (grid->mat[p.row][p.col] == 0)
	{
		if (grid->mat[p.row + 1][p.col] == 0)
		{

			neighb[i].row = p.row + 1;
			neighb[i].col = p.col;
			count++;
			i++;
		}

		if (grid->mat[p.row - 1][p.col] == 0)
		{

			neighb[i].row = p.row - 1;
			neighb[i].col = p.col;
			count++;
			i++;
		}

		if (grid->mat[p.row][p.col + 1] == 0)
		{

			neighb[i].row = p.row;
			neighb[i].col = p.col + 1;
			count++;
			i++;
		}


		if (grid->mat[p.row][p.col - 1] == 0)
		{
			neighb[i].row = p.row;
			neighb[i].col = p.col - 1;
			count++;
			i++;
		}

		return count;
	}

	return 0;
}

void grid_to_graph(const Grid *grid, Graph *graph)
{
    //we need to keep the nodes in a matrix, so we can easily refer to a position in the grid
    Node *nodes[MAX_ROWS][MAX_COLS];
    int i, j, k;
    Point neighb[4];

    //compute how many nodes we have and allocate each node
    graph->nrNodes = 0;
    for(i=0; i<grid->rows; ++i){
        for(j=0; j<grid->cols; ++j){
            if(grid->mat[i][j] == 0){
                nodes[i][j] = (Node*)malloc(sizeof(Node));
                memset(nodes[i][j], 0, sizeof(Node)); //initialize all fields with 0/NULL
                nodes[i][j]->position.row = i;
                nodes[i][j]->position.col = j;
                ++graph->nrNodes;
            }else{
                nodes[i][j] = NULL;
            }
        }
    }
    graph->v = (Node**)malloc(graph->nrNodes * sizeof(Node*));
    k = 0;
    for(i=0; i<grid->rows; ++i){
        for(j=0; j<grid->cols; ++j){
            if(nodes[i][j] != NULL){
                graph->v[k++] = nodes[i][j];
            }
        }
    }

    //compute the adjacency list for each node
    for(i=0; i<graph->nrNodes; ++i){
        graph->v[i]->adjSize = get_neighbors(grid, graph->v[i]->position, neighb);
        if(graph->v[i]->adjSize != 0){
            graph->v[i]->adj = (Node**)malloc(graph->v[i]->adjSize * sizeof(Node*));
            k = 0;
            for(j=0; j<graph->v[i]->adjSize; ++j){
                if( neighb[j].row >= 0 && neighb[j].row < grid->rows &&
                    neighb[j].col >= 0 && neighb[j].col < grid->cols &&
                    grid->mat[neighb[j].row][neighb[j].col] == 0){
                        graph->v[i]->adj[k++] = nodes[neighb[j].row][neighb[j].col];
                }
            }
            if(k < graph->v[i]->adjSize){
                //get_neighbors returned some invalid neighbors
                graph->v[i]->adjSize = k;
                graph->v[i]->adj = (Node**)realloc(graph->v[i]->adj, k * sizeof(Node*));
            }
        }
    }
}

void free_graph(Graph *graph)
{
    if(graph->v != NULL){
        for(int i=0; i<graph->nrNodes; ++i){
            if(graph->v[i] != NULL){
                if(graph->v[i]->adj != NULL){
                    free(graph->v[i]->adj);
                    graph->v[i]->adj = NULL;
                }
                graph->v[i]->adjSize = 0;
                free(graph->v[i]);
                graph->v[i] = NULL;
            }
        }
        free(graph->v);
        graph->v = NULL;
    }
    graph->nrNodes = 0;
}


int search(Graph* graph, Node* aux)
{
	for (int i = 0; i < graph->nrNodes; i++)
	{
		if (aux == graph->v[i])
			return i;
	}

	return 0;
}

void bfs(Graph *graph, Node *s, Operation *op)
{
    // TOOD: implement the BFS algorithm on the graph, starting from the node s
    // at the end of the algorithm, every node reachable from s should have the color BLACK
    // for all the visited nodes, the minimum distance from s (dist) and the parent in the BFS tree should be set
    // for counting the number of operations, the optional op parameter is received
    // since op can be NULL (when we are calling the bfs for display purposes), you should check it before counting:
    // if(op != NULL) op->count();

	queue <Node*> Q;
	int cnt1 = 0;

	for (int i = 0; i < graph->nrNodes; i++)
	{
		if (op != NULL)
		    op->count(3);

		graph->v[i]->color = COLOR_WHITE;
		graph->v[i]->dist = INT_MAX;
		graph->v[i]->parent = NULL;
	}

	int p = search(graph, s);

	if (op != NULL)
	    op->count(4);

	graph->v[p]->color = COLOR_GRAY;
	graph->v[p]->dist = 0;
	graph->v[p]->parent = NULL;
	Q.push(graph->v[p]);

    while (Q.size() != 0)
	{
		if (op != NULL)
			op->count(1);
		
		Node* aux = Q.front();
		Q.pop();

		int i = search(graph, aux);

		for (int j=0; j<graph->v[i]->adjSize; j++) 
		{
			if (op != NULL)
				op->count(1);

			if (graph->v[i]->adj[j]->color == COLOR_WHITE)
			{
				if (op != NULL)
					op->count(4);

			    graph->v[i]->adj[j]->color = COLOR_GRAY;
				graph->v[i]->adj[j]->dist = graph->v[i]->dist + 1;
				graph->v[i]->adj[j]->parent = graph->v[i];
				Q.push(graph->v[i]->adj[j]);
			}
		}

		   if (op != NULL)
			op->count(1);
	
			graph->v[i]->color = COLOR_BLACK;
    }

}

void print_bfs_tree(Graph *graph)
{
    //first, we will represent the BFS tree as a parent array
    int n = 0; //the number of nodes
    int *p = NULL; //the parent array
    Point *repr = NULL; //the representation for each element in p

    //some of the nodes in graph->v may not have been reached by BFS
    //p and repr will contain only the reachable nodes
    int *transf = (int*)malloc(graph->nrNodes * sizeof(int));
    for(int i=0; i<graph->nrNodes; ++i){
        if(graph->v[i]->color == COLOR_BLACK){
            transf[i] = n;
            ++n;
        }else{
            transf[i] = -1;
        }
    }
    if(n == 0){
        //no BFS tree
        free(transf);
        return;
    }

    int err = 0;
    p = (int*)malloc(n * sizeof(int));
    repr = (Point*)malloc(n * sizeof(Node));
    for(int i=0; i<graph->nrNodes && !err; ++i){
        if(graph->v[i]->color == COLOR_BLACK){
            if(transf[i] < 0 || transf[i] >= n){
                err = 1;
            }else{
                repr[transf[i]] = graph->v[i]->position;
                if(graph->v[i]->parent == NULL){
                    p[transf[i]] = -1;
                }else{
                    err = 1;
                    for(int j=0; j<graph->nrNodes; ++j){
                        if(graph->v[i]->parent == graph->v[j]){
                            if(transf[j] >= 0 && transf[j] < n){
                                p[transf[i]] = transf[j];
                                err = 0;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    free(transf);
    transf = NULL;

    if(!err){
        // TODO: pretty print the BFS tree
        // the parrent array is p (p[k] is the parent for node k or -1 if k is the root)
        // when printing the node k, print repr[k] (it contains the row and column for that point)
        // you can adapt the code for transforming and printing multi-way trees from the previous labs

		int l = -1;
		int level = -1;
		int q[100];
		for (int i = 0; i < n; i++) q[i] = 0;

		for (int i = 0; i < n; i++)
		{
			int count = 0;

			for (int k = 0; k < n; k++)
			{
				if (p[k] == l && q[k] == 0)
				{
					for (int i = 0; i <= level; i++) printf("          ");
					printf(" ( %d, %d )\n ", repr[k]);
					q[k] = 1;
					l = k;
					level++;
					break;
				}
				else
					count++;

				if (count == n)
				{
					l = p[l];
					i--;
					level--;
				}

			}

		}

    }

    if(p != NULL){
        free(p);
        p = NULL;
    }
    if(repr != NULL){
        free(repr);
        repr = NULL;
    }
}


bool otherbfs(Graph* graph, Node* source, Node* dest)
{
	queue <Node*> Q;
	
	for (int i = 0; i < graph->nrNodes; i++)
	{
		graph->v[i]->color = COLOR_WHITE;
		graph->v[i]->dist = INT_MAX;
		graph->v[i]->parent = NULL;
	}

	int p = search(graph, source);
	int m = search(graph, dest);


	graph->v[p]->color = COLOR_GRAY;
	graph->v[p]->dist = 0;
	graph->v[p]->parent = NULL;
	Q.push(graph->v[p]);

	while (Q.size() != 0)
	{
		Node* aux = Q.front();
		Q.pop();

		int i = search(graph, aux);

		for (int j = 0; j < graph->v[i]->adjSize; j++)
		{

			if (graph->v[i]->adj[j]->color == COLOR_WHITE)
			{
				graph->v[i]->adj[j]->color = COLOR_GRAY;
				graph->v[i]->adj[j]->dist = graph->v[i]->dist + 1;
				graph->v[i]->adj[j]->parent = graph->v[i];
				Q.push(graph->v[i]->adj[j]);

				if (graph->v[i]->adj[j] == graph->v[m])
					return true;
			}
		}

		graph->v[i]->color = COLOR_BLACK;
	}

	return false;
}

int shortest_path(Graph *graph, Node *start, Node *end, Node *path[])
{
    // TODO: compute the shortest path between the nodes start and end in the given graph
    // the nodes from the path, should be filled, in order, in the array path
    // the number of nodes filled in the path array should be returned
    // if end is not reachable from start, return -1
    // note: the size of the array path is guaranteed to be at least 1000

	if(otherbfs(graph,start,end)==false)
	   return -1;
	else
	{
		Node* crawl = end;
		int t = 0;
		path[t] = crawl;
		int z = search(graph, crawl);

		while (graph->v[z]->parent != NULL)
		{
			path[t] = graph->v[z]->parent;
			graph->v[z]->parent= graph->v[z]->parent->parent;
			t++;
		}

		int d = search(graph, end);
		return graph->v[d]->dist;
	}
}


void performance()
{
    int n, i;
    Profiler p("bfs");

        // vary the number of edges

		for (n = 1000; n <= 4500; n += 100) {
			Operation op = p.createOperation("bfs-edges", n);
			Graph graph;
			graph.nrNodes = 100;
			//initialize the nodes of the graph
			graph.v = (Node **)malloc(graph.nrNodes * sizeof(Node*));
			for (i = 0; i < graph.nrNodes; ++i) {
				graph.v[i] = (Node*)malloc(sizeof(Node));
				memset(graph.v[i], 0, sizeof(Node));
			}
			// TODO: generate n random edges
			// make sure the generated graph is connected

            for (i = 0; i < graph.nrNodes; i++) 
			{
               graph.v[i]->adjSize = 0;
			   graph.v[i]->adj = (Node**)malloc(graph.nrNodes * sizeof(Node));

			   for (int k = 0; k < graph.nrNodes; k++)
			   {
				   graph.v[i]->adj[k] = (Node*)malloc(sizeof(Node));
				   graph.v[i]->adj[k] = NULL;
			   }
			}

			int u = 1;

			while(u<n)
			{
				int w[100];
				FillRandomArray(w, 3, 0, graph.nrNodes - 1, false, 0);

				if(u<graph.nrNodes)
				{
					FillRandomArray(w, 3, 0, graph.nrNodes - 1, false, 0);
					w[0] = u;
					graph.v[w[0]]->adj[graph.v[w[0]]->adjSize] = graph.v[w[1]];
					graph.v[w[0]]->adjSize++;
					graph.v[w[1]]->adj[graph.v[w[1]]->adjSize] = graph.v[w[0]];
					graph.v[w[1]]->adjSize++;
					u++;
				}
				else if(u>=graph.nrNodes && graph.v[w[0]]->adj[graph.v[w[0]]->adjSize]==NULL && 
				     graph.v[w[1]]->adj[graph.v[w[1]]->adjSize]==NULL && w[0] != w[1])
				{
					graph.v[w[0]]->adj[graph.v[w[0]]->adjSize] = graph.v[w[1]];
					graph.v[w[0]]->adjSize++;
					graph.v[w[1]]->adj[graph.v[w[1]]->adjSize] = graph.v[w[0]];
					graph.v[w[1]]->adjSize++;
					u++;
				}
			}

			bfs(&graph, graph.v[0], &op);
			free_graph(&graph);
		}
	

	    // vary the number of vertices
        for (n = 100; n <= 200; n += 10) {
			Operation op = p.createOperation("bfs-vertices", n);
			Graph graph;
			graph.nrNodes = n;
			//initialize the nodes of the graph
			graph.v = (Node * *)malloc(graph.nrNodes * sizeof(Node*));
			for (i = 0; i < graph.nrNodes; ++i) {
				graph.v[i] = (Node*)malloc(sizeof(Node));
				memset(graph.v[i], 0, sizeof(Node));
			}
			// TODO: generate 4500 random edges
			// make sure the generated graph is connected

			for (i = 0; i < graph.nrNodes; i++)
			{
				graph.v[i]->adjSize = 0;
				graph.v[i]->adj = (Node * *)malloc(graph.nrNodes * sizeof(Node));

				for (int k = 0; k < graph.nrNodes; k++)
				{
					graph.v[i]->adj[k] = (Node*)malloc(sizeof(Node));
					graph.v[i]->adj[k] = NULL;
				}
			}

			int u = 1;

			while (u < 4500)
			{
				int w[100];
				FillRandomArray(w, 3, 0, graph.nrNodes - 1, false, 0);

				if (u < graph.nrNodes)
				{
					FillRandomArray(w, 3, 0, graph.nrNodes - 1, false, 0);
					w[0] = u;
					graph.v[w[0]]->adj[graph.v[w[0]]->adjSize] = graph.v[w[1]];
					graph.v[w[0]]->adjSize++;
					graph.v[w[1]]->adj[graph.v[w[1]]->adjSize] = graph.v[w[0]];
					graph.v[w[1]]->adjSize++;
					u++;
				}
				else if (u >= graph.nrNodes && graph.v[w[0]]->adj[graph.v[w[0]]->adjSize] == NULL &&
					     graph.v[w[1]]->adj[graph.v[w[1]]->adjSize] == NULL && w[0] != w[1])
				{
					graph.v[w[0]]->adj[graph.v[w[0]]->adjSize] = graph.v[w[1]];
					graph.v[w[0]]->adjSize++;
					graph.v[w[1]]->adj[graph.v[w[1]]->adjSize] = graph.v[w[0]];
					graph.v[w[1]]->adjSize++;
					u++;
				}
			}

			bfs(&graph, graph.v[0], &op);
			free_graph(&graph);
		}
	
    p.showReport();
}
