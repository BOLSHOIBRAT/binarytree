package org.itstep;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      BinaryTree tree = new BinaryTree();
        Scanner tmp = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            tree.add(tmp.nextInt(),null);
            System.out.println(tree.getRoot());
        }
    }
}