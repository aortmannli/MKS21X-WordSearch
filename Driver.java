public class Driver{
  public static void main(String[] args){
    WordSearch WSe = new WordSearch(6,10);

    System.out.println("WordSearch WSe = new WordSearch(6,10)");
    System.out.println(WSe);
    System.out.println(WSe.addWord("BOOP",4,4,-1,-1));
      System.out.println(WSe);
  }
}
