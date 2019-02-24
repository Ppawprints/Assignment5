package blockChain;

public class StringFormatterTester {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(String.format("%02X", (int) Math.exp(i)));
		}
	}
}
