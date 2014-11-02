package CS321.assignments.assignment03.problem01;

/**
 * Created by John on 10/26/2014.
 */
public class Node
{
    public int Id;
    public int TopChildId;
    public Node TopChild;
    public int TopChildWeight;
    public int TopChildSolution;

    public int MiddleChildId;
    public Node MiddleChild;
    public int MiddleChildWeight;
    public int MiddleChildSolution;

    public int BottomChildId;
    public Node BottomChild;
    public int BottomChildWeight;
    public int BottomChildSolution;

    public boolean ChildSolutionsFound;
    public Path BestPath;

    public Node(int id)
    {
        this.Id = id;
        this.BestPath = Path.None;
        this.TopChildSolution = this.MiddleChildSolution = this.BottomChildSolution = Integer.MIN_VALUE;
        this.ChildSolutionsFound = false;
    }

    public int BestWeightComingFrom (Path path, int penalty)
    {
        int topPenalty = 0, middlePenalty = 0, bottomPenalty = 0;
        if (path != Path.None)
        {
            topPenalty = (this.HasTopChild() && path != Path.Top) ? penalty : 0;
            middlePenalty = (this.HasMiddleChild() && path != Path.Middle) ? penalty : 0;
            bottomPenalty = (this.HasBottomChild() && path != Path.Bottom) ? penalty : 0;
        }

        int top = this.TopChildSolution + topPenalty;
        int middle = this.MiddleChildSolution + middlePenalty;
        int bottom = this.BottomChildSolution + bottomPenalty;

        return Math.max(Math.max(top, middle),  bottom);
    }

    public Path BestPathComingFrom (Path path, int penalty)
    {
        int bestWeight = this.BestWeightComingFrom(path, penalty);
        if (bestWeight == this.TopChildSolution)
        {
            return Path.Top;
        }

        if (bestWeight == this.MiddleChildSolution)
        {
            return Path.Middle;
        }

        if (bestWeight == this.BottomChildSolution)
        {
            return Path.Bottom;
        }

        // If we haven't found it yet there must have been a penalty
        // There is a potential bug here if two children have the same solution
        if (bestWeight == this.TopChildSolution + penalty)
        {
            return Path.Top;
        }

        if (bestWeight == this.MiddleChildSolution + penalty)
        {
            return Path.Middle;
        }

        if (bestWeight == this.BottomChildSolution + penalty)
        {
            return Path.Bottom;
        }

        // This shouldn't happen
        return Path.None;
    }

    public Node GetChild(Path path)
    {
        if (path == Path.Top)
        {
            return this.TopChild;
        }

        if (path == Path.Middle)
        {
           return this.MiddleChild;
        }

        if (path == Path.Bottom)
        {
            return this.BottomChild;
        }

        return null;
    }

    public boolean HasTopChild()
    {
        return this.TopChildId != 0;
    }

    public boolean HasMiddleChild()
    {
        return this.MiddleChildId != 0;
    }

    public boolean HasBottomChild()
    {
        return this.BottomChildId != 0;
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
