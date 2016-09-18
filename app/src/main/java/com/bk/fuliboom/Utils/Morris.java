package com.bk.fuliboom.Utils;


/**
 * Created by Bk on 2016/9/16.
 */

public class Morris {
    private class Node{
        public int value;
        Node left;
        Node right;

        public Node(int data){
            value = data;
        }
    }

    public void morrisIn(Node head){
        if (head == null) return;
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 !=  null){
            cur2 = cur1.left;
            if (cur2 != null){
                while (cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if (cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            System.out.print(cur1.value + " ");
            cur1 = cur1.right;
        }
        System.out.println();
    }


}
