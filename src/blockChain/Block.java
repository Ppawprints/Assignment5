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


  /**
   * Used to construct Block when num, amount and prevHash is known. Mine for nonce generate valid
   * hash
   * 
   * @param num
   * @param amount
   * @param prevHash
   * @throws NoSuchAlgorithmException
   */
  public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
    this.num = 0;
    this.amount = amount;
    this.prevHash = prevHash;

    long currentNonce = -1;
    do {
      currentNonce++;

      byte temp[];

      if (prevHash == null) {
        temp = ByteBuffer.allocate(16).putInt(this.num).putInt(this.amount).putLong(currentNonce)
            .array();
      } else {
        temp = ByteBuffer.allocate(16 + prevHash.getData().length).putInt(this.num)
            .putInt(this.amount).put(prevHash.getData()).putLong(currentNonce).array();
      }
      MessageDigest md = MessageDigest.getInstance("sha-256");
      md.update(temp);
      this.hash = new Hash(md.digest());
    } while (!this.hash.isValid()); // end do-while
    this.nonce = currentNonce;

  }

  /**
   * Used to construct the rest of the Blocks when num, amount, prevHash and nonce is known
   * 
   * @param num
   * @param amount
   * @param prevHash
   * @param nonce
   * @throws NoSuchAlgorithmException
   */
  public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
    this.num = num;
    this.amount = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;
    byte[] temp = ByteBuffer.allocate(16 + prevHash.getData().length).putInt(this.num)
        .putInt(this.amount).put(this.prevHash.getData()).putLong(this.nonce).array();
    MessageDigest md = MessageDigest.getInstance("sha-256");
    md.update(temp);
    this.hash = new Hash(md.digest());
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
