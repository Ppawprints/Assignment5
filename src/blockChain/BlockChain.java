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
		if (first == last) {
			return new Node(new Block(amount)).data;
		}
		return new Node(new Block(last.data.getNum(), amount, last.data.getPrevHash())).data;
	}

	public int getSize() {
		return last.data.getNum() + 1;
	}

	public void append(Block blk) throws IllegalArgumentException {
		if (first == last) {
			if (blk.getNum() != getSize() || !isAmountValid(blk.getAmount())) {
				throw new IllegalArgumentException();
			} else {
				last = new Node(blk);
				first.next = last;
				return;
			}
		}
		if (!blk.getPrevHash().equals(last.data.getHash()) || blk.getNum() != getSize()
				|| !isAmountValid(blk.getAmount())) {
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
		while (temp != null) {
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
		pen.println("Anna: " + currentBalance() + ", Bob: " + (first.data.getAmount() - currentBalance()) + ".");
		pen.flush();
	}

	public String toString() {
		String result = first.data.toString();
		Node temp = first;
		while (temp != null) {
			result.concat(temp.data.toString());
			temp = temp.next;
		}
		return result;
	}

	private int currentBalance() {
		int balance = 0;
		Node temp = first;
		while (temp != null) {
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
