import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Cheaters {

	public static void main(String args[]) {
		try {
			// Read in from a file
			BufferedReader br = new BufferedReader(new FileReader("input5.txt"));
			String line = br.readLine();
			ArrayList<Student> students = new ArrayList<Student>();
			while (line != null) {
				String[] arr = line.split(" ");
				students.add(new Student(arr[0], Double.parseDouble(arr[1]),
						Double.parseDouble(arr[2])));
				line = br.readLine();
			}
			br.close();
			ArrayList<Student> lowest = new ArrayList<Student>();
			lowest = FindCheaters(students, 0, students.size() - 1, lowest);

			System.out.println("The following two students have the lowest alpha score and may have cheated:");
			for (Student s : lowest) {
				System.out.println(s.studentId);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static ArrayList<Student> FindCheaters(ArrayList<Student> students, int left, int right, ArrayList<Student> twolowest) {
		// If there is more than one student.
		if (right - left == 1) {
			Student studentOne;
			Student studentTwo;
			if (twolowest.size() == 0) {
				studentOne = students.get(left);
				studentTwo = students.get(right);
				twolowest.add(studentOne);
				twolowest.add(studentTwo);
			} else {
				studentOne = twolowest.get(0);
				studentTwo = twolowest.get(1);
			}
			// Set Alpha values to max levels
			double alphaFact = Math.sqrt(Math.pow(studentOne.exam1Grade - studentTwo.exam1Grade, 2) + Math.pow(studentOne.exam2Grade - studentTwo.exam2Grade, 2));

			for (int i = left + 1; i < students.size(); i++) {
				Student One = students.get(left);
				Student Two = students.get(i);
				// Calculate Alphas between two, and take the minimum.
				double temp = Math.sqrt(Math.pow(One.exam1Grade - Two.exam1Grade, 2) + Math.pow(One.exam2Grade - Two.exam2Grade, 2));
				if (temp < alphaFact && !twolowest.get(0).studentId.equals(Two.studentId)) {
					twolowest.remove(1);
					twolowest.add(Two);
					alphaFact = temp;

				}
			}
			// Repeat above for the right.
			for (int i = right + 1; i < students.size(); i++) {
				Student One = students.get(right);
				Student Two = students.get(i);
				double temp = Math.sqrt(Math.pow(One.exam1Grade - Two.exam1Grade, 2) + Math.pow(One.exam2Grade - Two.exam2Grade, 2));

				if (temp < alphaFact && !twolowest.get(0).studentId.equals(One.studentId)) {
					twolowest.remove(0);
					twolowest.add(Two);
					alphaFact = temp;
				}

			}
			// return the minimum.
			return twolowest;
		} else {
			// get the midpoint.
			int mid = (right + left) / 2;
			// Returns alpha scores for sides
			ArrayList<Student> leftsList = FindCheaters(students, left, mid, twolowest);
			ArrayList<Student> rightsList = FindCheaters(students, mid, right, twolowest);

			// Comparisons
			Student leftsListOne = leftsList.get(0);
			Student leftsListTwo = leftsList.get(1);
			Student rightsListOne = rightsList.get(0);
			Student rightsListTwo = rightsList.get(1);

			double alphaLeftOne = Math.sqrt(Math.pow(leftsListOne.exam1Grade - leftsListTwo.exam1Grade, 2) + Math.pow(leftsListOne.exam2Grade - leftsListTwo.exam2Grade, 2));
			double alphaRightTwo = Math.sqrt(Math.pow(rightsListOne.exam1Grade - rightsListTwo.exam1Grade, 2) + Math.pow(rightsListOne.exam2Grade - rightsListTwo.exam2Grade, 2));
			if (alphaLeftOne < alphaRightTwo) {
				return leftsList;
			} else {
				return rightsList;
			}
		}
	}
}

class Student {

	public String studentId;
	public double exam1Grade;
	public double exam2Grade;

	public Student(String studentId, double ex1, double ex2) {
		this.studentId = studentId;
		exam1Grade = ex1;
		exam2Grade = ex2;
	}
}
