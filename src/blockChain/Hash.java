package blockChain;
import java.util.Arrays;

public class Hash {

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  private byte[] data;


  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  public Hash(byte[] data) {
    this.data = data;
  }


  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  public byte[] getData() {
    return data;
  }

  public boolean isValid() {
    if (data[0] - 0 == 0 && data[1] - 0 == 0 && data[2] - 0 == 0) {
      return true;
    }
    return false;
  }

  public String toString() {
    String result = new String();
    for (int i = 0; i < data.length; i++) {
      result.concat(String.format("X", (Byte.toUnsignedInt(data[i]))));
    }
    return result;
  }

  public boolean equals(Object other) {
    if (other instanceof Hash) {
      return Arrays.equals(this.data, ((Hash) other).getData());
    }
    return false;
  }

}
