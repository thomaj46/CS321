package CS321.assignments.assignment03.problem01;

/**
 * Created by John on 10/26/2014.
 */
public class GraphMaker
{
    private int square;
    private int numberOfNodes;
    private int currentColumn;
    private int lastNodeId;
    private int middleChildOffset;
    private boolean decreasing;

    public GraphMaker(int numberOfNodes)
    {
        this.numberOfNodes = numberOfNodes;
        this.square = (int)Math.sqrt(numberOfNodes);
        this.currentColumn = 1;
        this.middleChildOffset = 4;
    }

    public Node[] makeGraph()
    {
        Node[] graph = new Node[this.numberOfNodes + 1];
        graph[0] = new Node(0);
        int index = 1;
        Node[] columnNodes = this.nextColumnNodes();
        while(columnNodes.length > 0)
        {
            for(int i = 0; i < columnNodes.length; i += 1)
            {
                Node node = columnNodes[i];
                graph[index] = node;
                index += 1;
            }

            columnNodes = this.nextColumnNodes();
        }

        for (Node node : graph)
        {
            node.TopChild = graph[node.TopChildId];
            node.MiddleChild = graph[node.MiddleChildId];
            node.BottomChild = graph[node.BottomChildId];
        }

        return graph;
    }

    private Node[] nextColumnNodes()
    {
        int nodesInColumn;
        if (currentColumn > square)
        {
            nodesInColumn = square - (currentColumn - square);
        }
        else
        {
            nodesInColumn = currentColumn;
        }

        Node[] nodes = new Node[nodesInColumn];
        Node tempNode;
        int nodeId;
        for (int i = 0; i < nodes.length; i += 1)
        {
            nodeId = lastNodeId += 1;
            tempNode = new Node(nodeId);
            nodes[i] = tempNode;
            if (decreasing)
            {
                if (i > 0)
                {
                    tempNode.TopChildId = nodeId + nodesInColumn - 1;
                }
                if (i < nodesInColumn - 1)
                {
                    tempNode.BottomChildId = nodeId + nodesInColumn;
                }

                if (i != 0 && i != nodesInColumn - 1)
                {
                    tempNode.MiddleChildId = nodeId + middleChildOffset;
                }

            }
            else
            {
                tempNode.TopChildId = nodeId + nodesInColumn;
                tempNode.BottomChildId = nodeId + nodesInColumn + 1;

                if (currentColumn + 1 >= square)
                {
                    tempNode.MiddleChildId = nodeId + middleChildOffset - 1;
                }
                else
                {
                    tempNode.MiddleChildId = nodeId + middleChildOffset;
                }

            }
        }

        currentColumn += 1;
        decreasing = currentColumn >= square;
        middleChildOffset = decreasing ? middleChildOffset - 2 : middleChildOffset + 2;
        return nodes;
    }
}
