package CS321.assignments.assignment03.Problem01;

/**
 * Created by John on 10/26/2014.
 */
public class Node
{
    public int Id;
    public int TopChild;
    public int TopChildCost;
    public int MiddleChild;
    public int MiddleChildCost;
    public int BottomChild;
    public int BottomChildCost;

    public Node(int id)
    {
        this.Id = id;
    }

    public boolean HasTopChild()
    {
        return this.TopChild != 0;
    }

    public boolean HasMiddleChild()
    {
        return this.MiddleChild != 0;
    }

    public boolean HasBottomChild()
    {
        return this.BottomChild != 0;
    }

    public boolean HasChildren()
    {
        return this.HasTopChild() || this.HasMiddleChild() || this.HasBottomChild();
    }

    public boolean HasNoChildren()
    {
        return !this.HasChildren();
    }
}
