public class WordSearch{

    private char[][]data;
    private char[][]data;
    private int seed;
    private Random randgen;
    private ArrayList<String> wordsToAdd;
    private ArrayList<String> wordsAdded;

    public static void main(String[] args){

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
     addAllWords();
}
    private void clear(){
      for (int i = 0; i < data.length; i++) {
        for (int x = 0; x < data[0].length; x++) {
          data[i][x] = '_';
        }
      }
    }

    public String toString(){
      String out = "|";
        for (int i = 0; i < data.length; i ++){
          for (int x = 0;  < data[i].length; x ++){
            out += data[i][x] + " ";
          }
          out += "|\n|";
        }
      return out.substring(0, out.length() - 1);
    }

    private boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
      if (rowIncrement == 0 && colIncrement == 0) return false;
      if (row > data.length || col > data[row].length || word.length() > data.length - row || word.length() > data[row].length - col) return false;
      for (int i = 0; i < word.length(); i++) {
        int a = i*rowIncrement;
        int b = i*colIncrement;
       if (data[row + a][col + b] != word.charAt(i) && data[row + a][col + b] != '_') return false;
      }
      for (int i = 0; i < word.length(); i++){
        data[row + (i*rowIncrement)][col + (i*colIncrement)] = word.charAt(i);
      }
      return true;
    }

    private void addAllWords(){
      // was stuck and Grace Mao explained the algorithm to me so code might have similarities
      int size = wordsToAdd.size();

      for(int i = 0 ;(wordsToAdd.size() > 0)&& (i + 100 < size) ; i++){
        String str = "";
        if(wordsToAdd.size() == 1) str = wordsToAdd.get(0);
        else  w = wordsToAdd.get(Math.abs(randgen.nextInt() % (wordsToAdd.size())));

        rowIncrement = 0;
        colIncrement = 0;
        while (rowIncrement == 0 && colIncrement == 0){
          int rowIncrement = randgen.nextInt(3)-1;
          int colIncrement = randgen.nextInt(3)-1;
        }
        rowIncrement = data.length;
        colIncrement = 0;
        rows = Math.abs(randgen.nextInt() % (rowIncrement + 1));
        cols = Math.abs(randgen.nextInt() % (colIncrement + 1));
        if (rowIncrement > 0) colIncrement = data[0].length;
        boolean add = false;
        if (addWord(str,rows,cols,rowIncrement, colIncrement)){
          add = true;
          i += 100;
        }

      }
    }

    private void fill(){
      for (int i = 0; i < data.length; i++) {
        for (int x = 0; x < data[0].length; x++) {
          if (data[i][x] == " "){
            data[i][x] = "A" + Random(26);
          }
        }
      }
    }


}
