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
    return (data[0] == 0 && data[1] == 0 && data[2] == 0);
  }


  public String toString() {
    String result = String.format("%x", (Byte.toUnsignedInt(data[0])));
    for (int i = 0; i < data.length; i++) {
      result += (String.format("%x", (Byte.toUnsignedInt(data[i]))));
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
