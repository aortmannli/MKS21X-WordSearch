import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception
public class WordSearch{
  private char[][]data;
  private int seed;
  private Random randgen;
  private ArrayList<String> wordsToAdd;
  private ArrayList<String> wordsAdded;

  public WordSearch(int rows,int cols){
    data = new char[rows][cols];
    clear();
    wordsToAdd = new ArrayList<>();
    wordsAdded = new ArrayList<>();
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
  }


  public WordSearch(int rows, int cols, String fileName, int randSeed) throws FileNotFoundException {
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
  }

    /**Set all values in the WordSearch to underscores'_'*/
  private void clear(){
    for (int i = 0; i < data.length; i++) {
      for (int x = 0; x < data[0].length; x++) {
        data[i][x] = '_';
      }
    }
  }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
  public String toString(){
    String out = "";
    for (int i = 0; i < data.length; i++) {
      out += "|";
      for (int x = 0; x < data[0].length; x++) {
        out += data[i][x];
        if (x < data[0].length - 1) out += " ";
      }
      out += "|\n";
    }
    out += "Words: ";
    for (int i = 0; i < wordsAdded.size(); i++){
      out += wordsAdded.get(i);
    }
    return out;
  }


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */

  public boolean addWordHorizontal(String word,int row, int col){
    if (data[row].length < col + word.length()) return false;
    for (int i = 0; i < word.length(); i++) {
      if ((data[row][i] != word.charAt(i)) && (data[row][col + i] != '_')) return false;
    }
    for (int x = 0; x < word.length(); x++) {
      data[row][col+x] = word.charAt(x);
    }
    return true;
  }

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */

  public boolean addWordVertical(String word,int row, int col){
    if (data.length < row + word.length()) return false;
    for (int i = 0; i < word.length(); i++) {
      if (data[row + i][col] != word.charAt(i) && data[row + i][col] != '_') return false;
    }
    for (int i = 0; i < word.length(); i++) {
      data[row + i][col] = word.charAt(i);
    }
    return true;
  }

    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top left to bottom right, must fit on the WordGrid,
     *and must have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     */

  public boolean addWordDiagonal(String word,int row, int col){
    if (row > data.length || col > data[row].length || word.length() > data.length - row || word.length() > data[row].length - col) return false;
    int c= 0;
    for (int i = 0; i < word.length(); i++) {
      if (data[row + i][col + c] != '_' && data[row + i][col + c] != word.charAt(i)) return false;
      c++;
    }
    c = 0;
    for (int x = 0; x < word.length(); x++) {
      data[row + x][col + c] = word.charAt(x);
      c++;
    }
    return true;
  }

    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added in the direction rowIncrement,colIncrement
     *Words must have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@param rowIncrement is -1,0, or 1 and represents the displacement of each letter in the row direction
     *@param colIncrement is -1,0, or 1 and represents the displacement of each letter in the col direction
     *@return true when: the word is added successfully.
     *        false when: the word doesn't fit, OR  rowchange and colchange are both 0,
     *        OR there are overlapping letters that do not match
     */
  public boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
    if (rowIncrement == 0 && colIncrement == 0) return false;
    if (row + rowIncrement * word.length() > data.length || col + colIncrement * word.length() > data[row].length) return false;
    for (int i = 0; i < word.length(); i++) {
      int a = i*rowIncrement;
      int b = i*colIncrement;
      if ((data[row + a][col + b] != word.charAt(i)) && (data[row + a][col + b] != '_')) return false;
    }
    for (int i = 0; i < word.length(); i++){
      data[row + (i*rowIncrement)][col + (i*colIncrement)] = word.charAt(i);
    }
    return true;
  }

    /*[rowIncrement,colIncrement] examples:
     *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)
     *[ 1,0] would add downwards because (row+1), with no col change
     *[ 0,-1] would add towards the left because (col - 1), with no row change
     */

     private void addAllWords() {

     }
}
