package blockChain;

import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BlockChainDriver {
  public static void main(String[] args)
      throws NumberFormatException, NoSuchAlgorithmException, IOException {
    BlockChain chain = new BlockChain(Integer.valueOf(args[0]));
    Scanner scanner = new Scanner(System.in);
    PrintWriter pen = new PrintWriter(System.out, true);

    String command = new String();
    do {
      pen.println(chain.toString());
      pen.println();
      pen.println("Please enter your command here: ");
      command = scanner.next();
      if (command.equals("mine")) {
        int amount = 0;
        pen.println("Please enter the amount of transaction here: ");
        amount = Integer.valueOf(scanner.next());

        Block temp = chain.mine(amount);
        if (temp == null) {
          pen.println("Mining failed: The amount you entered is not transferable.");
          continue;
        }
        pen.println("Nonce mined: " + temp.getNonce());
        continue;
      } else if (command.equals("append")) {
        int amount = 0;
        pen.println("Please enter the amount of transaction here: ");
        amount = Integer.valueOf(scanner.next());
        long nonce = 0;
        pen.println("Please enter the nonce here: ");
        nonce = Long.parseLong(scanner.next());
        chain.append(new Block(chain.getSize(), amount, chain.last.data.getHash(), nonce));
        continue;
      } else if (command.equals("remove")) {
        if (chain.removeLast()) {
          continue;
        } else {
          pen.println("Removal failed: only one block left on the chain.");
        }
        continue;
      } else if (command.equals("check")) {
        if (chain.isValidBlockChain()) {
          pen.println("The block chain is valid.");
        } else {
          pen.println("The block chain is not valid.");
        }
        continue;
      } else if (command.equals("report")) {
        chain.printBalances();
        continue;
      } else if (command.equals("help")) {
        printListOfCommands(pen);
        continue;
      } else if (command.equals("quit")) {
        continue;
      } else {
        pen.println("The command entered is invalid.");
        pen.println("Please enter 'help' to view the list of commands.");
        continue;
      }
    } while (!command.equals("quit"));

    scanner.close();
  }

  public static void printListOfCommands(PrintWriter pen) {
    pen.println("Please choose from the following list of valid commands:");
    pen.println("mine: discovers the nonce for a given transaction.");
    pen.println("append: appends a new block onto the end of the chain.");
    pen.println("remove: removes the last block from the end of the chain.");
    pen.println("check: checks that the block chain is valid.");
    pen.println("report: reports the balances of Alice and Bob.");
    pen.println("help: prints this list of commands.");
    pen.println("quit: quits the program.");
  }
}

// https://alvinalexander.com/java/edu/pj/pj010005
