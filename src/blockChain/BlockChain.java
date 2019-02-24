package blockChain;

import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

public class BlockChain {

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  Node first;
  Node last;


  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+
  
  public BlockChain(int initial) throws NoSuchAlgorithmException {
    this.first = new Node(new Block(initial));
    this.last = this.first;
  }


  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  public Block mine(int amount) throws NoSuchAlgorithmException {
    if (!isAmountValid(amount)) {
      return null;
    }
    return new Node(new Block(last.data.getNum(), amount, last.data.getPrevHash())).data;
  }

  public int getSize() {
    return last.data.getNum() + 1;
  }

  public void append(Block blk) throws IllegalArgumentException {
    if (!blk.getPrevHash().equals(last.data.getHash()) || blk.getNum() != getSize()
        || isAmountValid(blk.getAmount())) {
      throw new IllegalArgumentException();
    }
    last.next = new Node(blk);
    last = last.next;
  }

  boolean removeLast() {
    if (getSize() <= 1) {
      return false;
    }
    Node temp = first;
    while (!temp.next.equals(last)) {
      temp = temp.next;
    }
    temp.next = null;
    last = temp;
    return true;
  }

  public Hash getHash() {
    return last.data.getHash();
  }

  public boolean isValidBlockChain() {
    Hash prevHash = first.data.getHash();
    int prevNum = first.data.getNum();

    if (!first.data.getHash().isValid()) {
      return false;
    }

    Node temp = first.next;
    int balance = currentBalance();
    while (!temp.equals(null)) {
      balance = +temp.data.getAmount();
      if (!temp.data.getPrevHash().equals(prevHash) || temp.data.getNum() != (prevNum--)
          || !temp.data.getHash().isValid() || balance < 0) {
        return false;
      } else {
        prevHash = temp.data.getHash();
        temp = temp.next;
      }
    }
    return true;
  }

  public void printBalances() {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.println("Anna: " + currentBalance() + ", Bob: "
        + (first.data.getAmount() - currentBalance()) + ".");
    pen.flush();
  }

  public String toString() {
    String result = first.data.toString();
    Node temp = first;
    while (!temp.equals(null)) {
      result.concat(temp.data.toString());
      temp = temp.next;
    }
    return result;
  }

  private int currentBalance() {
    int balance = first.data.getAmount();
    Node temp = first;
    while (!temp.equals(null)) {
      balance += temp.data.getAmount();
      temp = temp.next;
    }
    return balance;
  }

  private boolean isAmountValid(int amount) {
    if (currentBalance() + amount < 0) {
      return false;
    }
    return true;
  }
}


