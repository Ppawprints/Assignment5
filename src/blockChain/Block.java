package blockChain;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  private int num;
  private int amount;
  private Hash prevHash;
  private long nonce;
  private Hash hash;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  public Block(int amount) throws NoSuchAlgorithmException {
    this.num = 0;
    this.amount = amount;
    this.prevHash = null;

    // this.nonce = (long) (Math.random() * Long.MAX_VALUE);
    // byte[] temp =
    // ByteBuffer.allocate(16).putInt(this.num).putInt(this.amount).putLong(this.nonce).array();
    // MessageDigest md = MessageDigest.getInstance("sha-256");
    // md.update(temp);
    // this.hash = new Hash((byte[]) md.digest());
    //
    // while (!this.hash.isValid()) {
    // this.nonce = (long) (Math.random() * Long.MAX_VALUE);
    //
    // temp =
    // ByteBuffer.allocate(16).putInt(this.num).putInt(this.amount).putLong(this.nonce).array();
    // md = MessageDigest.getInstance("sha-256");
    // md.update(temp);
    //
    // this.hash = new Hash((byte[]) md.digest());
    // }

      do {
        this.nonce = (long) (Math.random() * Long.MAX_VALUE);
        byte[] temp = ByteBuffer.allocate(16).putInt(this.num).putInt(this.amount)
            .putLong(this.nonce).array();
        MessageDigest md = MessageDigest.getInstance("sha-256");
        md.update(temp);
        this.hash = new Hash((byte[]) md.digest());
      } while (!this.hash.isValid());
  }

  public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
    this.num = num;
    this.amount = amount;
    this.prevHash = prevHash;

    do {
      this.nonce = (long) (Math.random() * Long.MAX_VALUE);
      byte[] temp = ByteBuffer.allocate(16 + prevHash.getData().length).putInt(this.num)
          .putInt(this.amount).put(this.prevHash.getData()).putLong(this.nonce).array();
      MessageDigest md = MessageDigest.getInstance("sha-256");
      md.update(temp);
      this.hash = new Hash((byte[]) md.digest());
    } while (!hash.isValid());
  }

  //need to check whether hash is valid?
  public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
    this.num = num;
    this.amount = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;
    byte[] temp = ByteBuffer.allocate(16 + prevHash.getData().length).putInt(this.num)
        .putInt(this.amount).put(this.prevHash.getData()).putLong(this.nonce).array();
    MessageDigest md = MessageDigest.getInstance("sha-256");
    md.update(temp);
    this.hash = new Hash((byte[]) md.digest());
  }

  public Block(int num, int amount, long nonce) throws NoSuchAlgorithmException {
    this.num = num;
    this.amount = amount;
    this.prevHash = null;
    this.nonce = nonce;
    byte[] temp =
        ByteBuffer.allocate(16).putInt(this.num).putInt(this.amount).putLong(this.nonce).array();
    MessageDigest md = MessageDigest.getInstance("sha-256");
    md.update(temp);
    this.hash = new Hash((byte[]) md.digest());
  }

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  public int getNum() {
    return this.num;
  }

  public int getAmount() {
    return this.amount;
  }

  public long getNonce() {
    return this.nonce;
  }

  public Hash getPrevHash() {
    return this.prevHash;
  }

  Hash getHash() {
    return this.hash;
  }

  public String toString() {
    if (num != 0) {
      return "Block " + num + "(Amount: " + amount + ", Nonce: " + nonce + ", prevHash: "
          + prevHash.toString() + ", hash: " + hash.toString() + ")";
    }
    return "Block " + num + "(Amount: " + amount + ", Nonce: " + nonce + ", prevHash: null"
        + ", hash: " + hash.toString() + ")";
  }
}
