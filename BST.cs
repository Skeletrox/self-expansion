using System;

namespace BST
{
	class Node
	{
		private Node parent, lchild, rchild;
		private int value;
		public void setParent(Node n)
		{
			parent = n;
		}
		public void setLchild(Node n)
		{
			lchild = n;
		}
		public void setRchild(Node n)
		{
			rchild = n;
		}
		public void setValue(int x)
		{
			value = x;
		}
		public int getValue()
		{
			return value;
		}
		public Node getParent()
		{
			return parent;
		}
		public Node getLchild()
		{
			return lchild;
		}
		public Node getRchild()
		{
			return rchild;
		}

		public Node(int x)
		{
			value = x;
		}
	}

	class MainClass
	{
		static void BSTify(Node[] nodes, int low, int high, int[] array)
		{

			/*Inspect each element in the node and keep assigning them to the BST. How does one do this
			 * without having to depend on the size of the list (assigning from bottom up may seem easier
			 * after sorting but heavily depends on the size of the list)?
			 * Maybe take all middle values and assign them to root nodes? That seems fine.
			 * Recursively, can cause a stack overflow, but for basic implementation, shouldn't have issues
			 * Select list, take middle, split into left and right sublists, use middle of left and right
			 * sublists as left child and right child, continue recursively until the length list is 1
			 */

			int mid = (low + high) / 2;
			nodes[mid].setValue(array[mid]);

			/* Split from 0 to mid-1 (mid elements) and mid+1 to n (n-mid elements)
			 * Repeat for left and right
			 */
			if (low < high)
			{
				BSTify(nodes, low, mid - 1, array);
				BSTify(nodes, mid + 1, high, array);

				/* Now is the task of asssigning the parents and children
				 * Care needs to be taken to ensure that no node refers to itself as its parent
				 * Or goes out of bounds [this can happen if the left tree ends up referencing
				 * the right tree for parent or vice-versa]
				 */ 

				if (((low + mid - 1) / 2 >= low) && ((low + mid -1)/2 != mid))	//Refers to left tree
				{
					nodes[(low + mid - 1) / 2].setParent(nodes[mid]);
					nodes[mid].setLchild(nodes[(low + mid - 1) / 2]);
				}
				if (((high + mid + 1) / 2 != mid) && ((high+mid+1)/2 != mid))	//Refers to right tree
				{
					nodes[(high + mid + 1) / 2].setParent(nodes[mid]);
					nodes[mid].setRchild(nodes[(high + mid + 1) / 2]);
				}
			}
		}

		public static void Main(string[] args)
		{
			int n, i = 0, j = 0, k = 0;
			Console.WriteLine("Enter the number of elements to perform binary search");
			n = int.Parse(Console.ReadLine());
			string seqstr;
			int[] seq;
			seq = new int[n];
			Node[] bst;
			bst = new Node[n];
			Console.WriteLine("Enter the sequence of values to manufacture BST");
			seqstr = Console.ReadLine() + " "; //Adding the space to circumvent a bug

			/*Set the initial seq pointer to the first number and the next one to where the space is
			 * Parse the number into an int and then set the initial pointer to the char after space
			 */

			while ((i < seqstr.Length) && (k < n))
			{
				for (j = i; seqstr[j] >= '0' && seqstr[j] <= '9'; j++) { }
				string number = seqstr.Substring(i, j - i);
				seq[k++] = int.Parse(number);
				i = j + 1;
			}
			//Sort the array

			for (i = 0; i < n; i++)
			{
				for (j = 0; j < n - i - 1; j++)
				{
					if (seq[j] > seq[j + 1])
					{
						seq[j] = seq[j] + seq[j + 1];
						seq[j + 1] = seq[j] - seq[j + 1];
						seq[j] = seq[j] - seq[j + 1];
					}
				}
			}

			//Assign values to nodes

			for (i = 0; i < n; i++)
				bst[i] = new Node(seq[i]);



			BSTify(bst, 0, n-1, seq);
			Console.WriteLine("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			Console.WriteLine("The BST with nodes is: ");
			for (i = 0; i < n; i++)
			{
				Console.WriteLine("----------------------------------");
				Console.WriteLine("Value at node: " + bst[i].getValue());
				if (bst[i].getParent() == null)
					Console.WriteLine("Value of Parent is NULL");
				else
					Console.WriteLine("Value of Parent is " + bst[i].getParent().getValue());
				if (bst[i].getLchild() == null)
					Console.WriteLine("Value of Left Child is NULL");
				else
					Console.WriteLine("Value of left child is " + bst[i].getLchild().getValue());
				if (bst[i].getRchild() == null)
					Console.WriteLine("Value of Right Child is NULL");
				else
					Console.WriteLine("Value of right child is " + bst[i].getRchild().getValue());
			}
		}
	}
}
