public class January   {
public int[][] mat;// mat is square
	public static void main ( String [] args )   {
		January ace = new January ( );
		ace.setup();
		ace.mark(3,1);
		ace.print();
	}
public void setup
	/** Precondition: x >= 0
	*/
	public void run()
	{
int num1 = 4, num2 = 5;
Integer int1 = new Integer(12);
Integer int2 = new Integer(63);
swapper(num1, num2, int1, int2);
System.out.print(num1 + "   " + num2 + "   " + int1 + "   " + int2 + "   ");
	}

	public void swapper (int n1, int n2, Integer i1, Integer i2)
{
int temp = n1;
n1 = n2;
n2 = temp;
Integer tempint = i1;
i1 = i2;
i2 = tempint;
System.out.print(n1 + "   " + n2 + "   " + i1 + "   " + i2 + "   ");
}


public void mark(int rowMark, int colMark)
{
for(int k = 0; k < mat.length; k++)
{
int row = (rowMark + k) % mat.length;
int col = (colMark + mat.length - k) % mat.length;
mat[row][col] = 1;
}
}

public void print()
{
	for (int i = 0;i<mat.length;i++) {
		for (int j = 0;j<mat[i].length;j++) {
			System.out.print(mat[i][j]+" ");
		}
		System.out.println("");
	}
}






}