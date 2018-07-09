package btree;

import java.util.Scanner;

/*
 * Write a code (i.e., implementing a class) that sorts “String” objects in alphabetic
order. You can name it as “StringTree.” Each node in the tree should be represented
by a “Node” class, which stores the string value and pointers to the right and left
child nodes. For any node value in the tree, the value of its left child should come
before that value, and the value of its right child should come after that value. The
“StringTree” class should contain both a method for adding strings to the tree and a
method for printing the tree’s value in alphabetic order. Write a test program that
receives strings from the user (or reads from a file (attached)) and adds them to the
tree. Also, your test program should print out the tree values after each such
reception. You may use the class Node shown below.

compare -1 string1 is less than string2
compare  0 string1 is equal to string2
compare +1 string1 is greater than string2

 LinkedList<String>list = new LinkedList<String>();
    list.add("node1");
    list.add("node2");

    list.sort( new Comparator<String>(){
    @Override 
        public int compare(String string1, String string2) {
            return Collator.getInstance().compare(string1, string2);
        }
    });
 */
public class StringTrees {
	Node left;
	int size = 0;

	private Node add(String value) {

		if (left == null) {
			Node newNode = new Node(value);
			left = newNode;
			size++;
			return left;
		}

		Node temp = left;

		while (temp.getRight() != null) {
			temp = temp.getRight();
		}

		Node newNode = new Node(value);
		temp.setRight(newNode);
		newNode.setRight(null);
		size++;
		return left;
	}

	private Node sort(Node sortedNode) {

		for (int i = size - 1; i >= 1; i--) {
			Node newValue = sortedNode;
			Node tempNode = sortedNode;

			for (int j = 0; j < i; j++) {

				String node1 = sortedNode.value;
				Node nextnode = sortedNode.getRight();
				String node2 = nextnode.value;
				if (node1.compareTo(node2) > 0) {

					if (sortedNode.getRight().getRight() != null) {
						Node CurrentNext = sortedNode.getRight().getRight();
						nextnode.setRight(sortedNode);
						nextnode.getRight().setRight(CurrentNext);

						if (j == 0) {
							newValue = nextnode;
						} else
							sortedNode = nextnode;

						for (int l = 1; l < j; l++) {
							tempNode = tempNode.getRight();
						}

						if (j != 0) {
							tempNode.setRight(nextnode);

							sortedNode = tempNode;
						}

					} else if (sortedNode.getRight().getRight() == null) {
						nextnode.setRight(sortedNode);
						nextnode.getRight().setRight(null);

						for (int l = 1; l < j; l++) {
							tempNode = tempNode.getRight();
						}

						tempNode.setRight(nextnode);
						nextnode = tempNode;
						sortedNode = tempNode;
					}

				} else {
					sortedNode = tempNode;
				}

				sortedNode = newValue;
				tempNode = sortedNode;

				for (int k = 0; k <= j && j < i - 1; k++) {
					sortedNode = sortedNode.getRight();
				}
			}
		}

		return sortedNode;
	}

	public static void main(String[] args) {

		StringTrees stringTree = new StringTrees();
		Node node = null;
		String input = "";

		// get input from keyboard
		Scanner scanner = new Scanner(System.in);

		// get input until ~~~ is entered
		do {
			System.out.println("Enter a String and press enter, enter ~~~ to exit");
			input = scanner.next();

			if (input.equals("~~~"))
				break;

			// add nodes
			node = stringTree.add(input);

		} while (!input.equals("~~~"));

		// sort nodes
		Node sortedNode = stringTree.sort(node);

		// print nodes
		while (sortedNode != null) {
			System.out.println(sortedNode.getValue());
			sortedNode = sortedNode.getRight();
		}

		scanner.close();
	}
}
