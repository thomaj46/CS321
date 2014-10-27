package CS321.assignments.assignment03.Problem01;

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
        Node[] graph = new Node[this.numberOfNodes];
        int index = 0;
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
                    tempNode.TopChild = nodeId + nodesInColumn - 1;
                }
                if (i < nodesInColumn - 1)
                {
                    tempNode.BottomChild = nodeId + nodesInColumn;
                }

                if (i != 0 && i != nodesInColumn - 1)
                {
                    tempNode.MiddleChild = nodeId + middleChildOffset;
                }

            }
            else
            {
                tempNode.TopChild = nodeId + nodesInColumn;
                tempNode.BottomChild = nodeId + nodesInColumn + 1;

                if (currentColumn + 1 >= square)
                {
                    tempNode.MiddleChild = nodeId + middleChildOffset - 1;
                }
                else
                {
                    tempNode.MiddleChild = nodeId + middleChildOffset;
                }

            }
        }

        currentColumn += 1;
        decreasing = currentColumn >= square;
        middleChildOffset = decreasing ? middleChildOffset - 2 : middleChildOffset + 2;
        return nodes;
    }
}
