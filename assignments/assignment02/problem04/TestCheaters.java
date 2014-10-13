import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class TestCheaters 
{
	public static Student studentA;
	public static Student studentB;
	public static void main(String args[])
	{
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader("input5.txt"));
			String line = br.readLine();
			ArrayList<Student> students = new ArrayList<Student>();
			while(line != null)
			{
				String[] arr = line.split(" ");
				students.add(new Student(arr[0], Double.parseDouble(arr[1]), Double.parseDouble(arr[2])));
				line = br.readLine();
			}
			br.close();
			ArrayList<Student> smallest =  new ArrayList<Student>();
			smallest = GetPossibleCheaters(students, 0, students.size() - 1, smallest);
			
			for(Student s : smallest)
			{
				System.out.println(s.studentId);
			}
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static ArrayList<Student> GetPossibleCheaters(ArrayList<Student> students, int left, int right, ArrayList<Student> twoSmallest)
	{
		//if there are two students to be compared.
		if(right - left == 1)
		{
			Student leftStudent;
			Student rightStudent;
			if(twoSmallest.size() == 0)
			{
				leftStudent = students.get(left);
				rightStudent = students.get(right);
				twoSmallest.add(leftStudent);
				twoSmallest.add(rightStudent);
			}
			else
			{
				leftStudent = twoSmallest.get(0);
				rightStudent = twoSmallest.get(1);
			}
			//we set the left and right alpha values at max.
			
			double firstAlphaF = Math.sqrt(Math.pow(leftStudent.exam1Grade - rightStudent.exam1Grade, 2) + Math.pow(leftStudent.exam2Grade - rightStudent.exam2Grade, 2));
			//we compare all of the students to students.get(left).
			//loop through all students from left to end of the collection to get the smallest alpha value.
			for(int i = left + 1; i < students.size(); i++)
			{
				Student A = students.get(left);
				Student B = students.get(i);
				//calculate the alpha factor between the two, and take the minimum.
				double temp = Math.sqrt(Math.pow(A.exam1Grade - B.exam1Grade, 2) + Math.pow(A.exam2Grade - B.exam2Grade, 2));
				if(temp < firstAlphaF && !twoSmallest.get(0).studentId.equals(B.studentId))
				{
					twoSmallest.remove(1);
					twoSmallest.add(B);
					firstAlphaF = temp;

				}
			}
			//firstAlphaF = Math.sqrt(Math.pow(leftStudent.exam1Grade - rightStudent.exam1Grade, 2) + Math.pow(leftStudent.exam2Grade - rightStudent.exam2Grade, 2));
			//do the same thing for the right side.
			for(int i = right + 1; i < students.size(); i++)
			{
				Student A = students.get(right);
				Student B = students.get(i);
				double temp = Math.sqrt(Math.pow(A.exam1Grade - B.exam1Grade, 2) + Math.pow(A.exam2Grade - B.exam2Grade, 2));
				
				if(temp < firstAlphaF && !twoSmallest.get(0).studentId.equals(A.studentId))
				{
					twoSmallest.remove(0);
					twoSmallest.add(B);
					firstAlphaF = temp;
				}
				
			}
			//return the minimum.
			return twoSmallest;
		}
		else
		{
			//get the midpoint.
			int midpoint = (right + left) / 2;
			//returns the left lowest alpha score.
			ArrayList<Student> leftList = GetPossibleCheaters(students, left, midpoint, twoSmallest);
			//returns the right lowest alpha score.
			ArrayList<Student> rightList = GetPossibleCheaters(students, midpoint, right, twoSmallest);
			
			//rest of comparisons in constant time.
			Student leftListOne = leftList.get(0);
			Student leftListTwo = leftList.get(1);
			
			Student rightListOne = rightList.get(0);
			Student rightListTwo = rightList.get(1);
			
			
			double afLeftOne = Math.sqrt(Math.pow(leftListOne.exam1Grade - leftListTwo.exam1Grade, 2) + Math.pow(leftListOne.exam2Grade - leftListTwo.exam2Grade, 2));
			double afRightTwo = Math.sqrt(Math.pow(rightListOne.exam1Grade - rightListTwo.exam1Grade, 2) + Math.pow(rightListOne.exam2Grade - rightListTwo.exam2Grade, 2));
			if(afLeftOne < afRightTwo)
			{
				return leftList;
			}
			else
			{
				return rightList;
			}
		}
	}
}

class Student
{
	public String studentId;
	public double exam1Grade;
	public double exam2Grade;
	public double alphaFactor;
	public Student(String studentId, double ex1, double ex2)
	{
		this.studentId = studentId;
		exam1Grade = ex1;
		exam2Grade = ex2;
	}
}
