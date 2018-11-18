import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class WordSearch{

  private char[][]data;
  private int seed;
  private Random randgen;
  private ArrayList<String> wordsToAdd;
  private ArrayList<String> wordsAdded;

  public static void main(String[] args)throws FileNotFoundException {
    int rows = Integer.parseInt(args[0]);
    int cols = Integer.parseInt(args[1]);
    String fileN = args[2];
    Random s = new Random();
    int seed = Math.abs(s.nextInt() % 10000);
    boolean answer = false;
    seed = Integer.parseInt(args[3]);

    if (args.length == 5 && args[4].equals("key")) {
      answer = true;
    }


    WordSearch result = new WordSearch(rows, cols, fileN, seed, answer);

    System.out.println(result);

  }

  public WordSearch(int rows, int cols, String fileName) throws FileNotFoundException {
    randgen = new Random();
    seed = randgen.nextInt();
    randgen = new Random(seed);
    data = new char[rows][cols];
    clear();
    File f = new File(fileName);
    Scanner in = new Scanner(f);
    wordsToAdd = new ArrayList<>();
    wordsAdded = new ArrayList<>();
    while (in.hasNext()) {
      wordsToAdd.add(in.nextLine().toUpperCase());
    }

    addAllWords();
  }

  public WordSearch(int rows, int cols, String fileName, int randSeed, boolean ans) throws FileNotFoundException {
    seed = randSeed;
    randgen = new Random(seed);
    data = new char[rows][cols];
    clear();
    File f = new File(fileName);
    Scanner in = new Scanner(f);
    wordsToAdd = new ArrayList<>();
    wordsAdded = new ArrayList<>();
    while (in.hasNext()) {
      wordsToAdd.add(in.nextLine().toUpperCase());
    }
    System.out.println(wordsToAdd);
    addWord("YEET",1,5,1,0);
    //addAllWords();
    if (!ans) {
    fill();
    }
  }

  private void clear(){
    for (int i = 0; i < data.length; i++) {
      for (int x = 0; x < data[0].length; x++) {
      data[i][x] = '_';
      }
    }
  }

  public String toString(){
    String out = "";
    for (int x = 0; x < data.length; x++) {
      out += "|";
      for (int y = 0; y < data[0].length; y++) {
          out += data[x][y];
          if (y < data[0].length - 1) out += " ";
      }
      out += "|\n";
    }
    out += "Words: ";
    for (int i = 0; i < wordsAdded.size(); i++){
      out += wordsAdded.get(i);
    }
    return out;
  }

  private boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
    if (rowIncrement == 0 && colIncrement == 0) return false;
    if (row + rowIncrement * word.length() > data.length || col + colIncrement * word.length() > data[row].length) return false;
    if (row + rowIncrement * word.length() < 0 || col + colIncrement * word.length() < 0) return false;

    for (int i = 0; i < word.length(); i++) {
      int a = i*rowIncrement;
      int b = i*colIncrement;
      if (data[row + a][col + b] != word.charAt(i) && data[row + a][col + b] != ' ') return false;
    }

    for (int i = 0; i < word.length(); i++){
      data[row + (i*rowIncrement)][col + (i*colIncrement)] = word.charAt(i);
    }
    wordsToAdd.remove(word);
    wordsAdded.add(word);
    return true;
  }

  private void addAllWords(){
  // was stuck and Grace Mao explained the algorithm to me so code might have similarities
    int x = 0;
    while (!wordsToAdd.isEmpty() && x < 5000){
      boolean add = false;
      int colIncrement = randgen.nextInt() % 2;
      int rowIncrement = randgen.nextInt() % 2;
      /*while (rowIncrement == 0 && colIncrement == 0){
        colIncrement = randgen.nextInt() % 2;
        rowIncrement = randgen.nextInt() % 2;
      }*/

      String str = wordsToAdd.get(randgen.nextInt(wordsToAdd.size()));
      for (int i = 0; i < 100 & !add; i ++){
        int row = randgen.nextInt(data.length);
        int col = randgen.nextInt(data[0].length);
        if (addWord(str, row, col, rowIncrement, colIncrement)){
          add = true;
          x = 0;
        }
      }
      if (!add) x++;
    }
    System.out.println(seed);
    System.out.println(this);

  }

  private void fill(){
    int letter = 0;
    for (int i = 0; i < data.length; i++) {
      for (int x = 0; x < data[0].length; x++) {
        if (data[i][x] == ' ') {
          data[i][x] = (char)('A' + randgen.nextInt(26));
        }
      }
    }
  }
}
