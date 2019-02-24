package blockChain;


public class Node {

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  public Block data;
  public Node next;


  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  public Node(Block data) {
    this.data = data;
    this.next = null;
  }


  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  public boolean equals(Object other) {
    return this == other;
  }
}
