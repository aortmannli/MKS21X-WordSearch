public class TheoDriver{
	public static void main(String[] args){
		WordSearch one = new WordSearch(10,10);
		System.out.println("Initializing a 11 by 11, WordSearch");
		System.out.println("Should print out a 10 X 10 array of underscores: ");
		System.out.println(one);
		System.out.println("*******************************");
		System.out.println("attempting to add 'hello' horizontally at (1,4)");
		System.out.println("should print True: ");
		System.out.println(one.addWordHorizontal("hello",1,4));
		System.out.println("should have the second row as: _ _ _ _ h e l l o _");
		System.out.println(one);
		System.out.println("*******************************");
		System.out.println("attempting to add overlapping word 'oS' horizonally at (1,7) where it should not be able to be placed");
		System.out.println("should print false: ");
		String old = one + "";
		System.out.println(one.addWordHorizontal("oS",1,7));
		if (old.equals(""+one)){
			System.out.println("Yay you did not modify the WordSearch as you were supposed to!");
		}else{
			System.out.println("uh Oh you modified the WordSearch: ");
			System.out.println(one);
		}

		System.out.println("*******************************");
		System.out.println("attempting to add overlapping word 'oS' at (1,8)");
		System.out.println("should print true: ");
		System.out.println(one.addWordHorizontal("oS",1,8));
		System.out.println("should have the second row as: _ _ _ _ h e l l o S");
		System.out.println(one);
		System.out.println("*******************************");
		System.out.println("attempting to add 'elloHay' vertically at (1,5)");
		System.out.println("should print true: ");
		System.out.println(one.addWordVertical("elloHay",1,5));
		System.out.println("should have the sixth column as: _ e l l o H a y _ _");
		System.out.println(one);
		System.out.println("*******************************");
		System.out.println("attempting to add 'LocCabos' vertically at (1,5)");
		System.out.println("should print false: ");
		old = one + "";
		System.out.println(one.addWordVertical("LosCabos", 0, 7));
		if (old.equals(""+one)){
			System.out.println("Yay you did not modify the WordSearch as you were supposed to!");
		}else{
			System.out.println("uh Oh you modified the WordSearch: ");
			System.out.println(one);
		}

    System.out.println();

   WordSearch diag = new WordSearch(10,10);
   System.out.println("Testing addWordDiagonal (should print true)");
   System.out.println(diag.addWordDiagonal("puppies",0,0));
   System.out.println(diag);

   System.out.println();

   System.out.println("Testing addWordDiagonal (should print false)");
   System.out.println(diag.addWordDiagonal("dog",8,2));
   System.out.println(diag);

   System.out.println();

   System.out.println("Testing addWordDiagonal with overlaps");
   System.out.println(diag.addWordDiagonal("esmeh",5,5));
   System.out.println(diag);
   System.out.println(diag.addWordDiagonal("nooo",4,4));
   System.out.println(diag);

   System.out.println();

   WordSearch yay = new WordSearch(10,10);
   System.out.println("Testing all three add methods together");
   yay.addWordDiagonal("pieeee",0,0);
   yay.addWordVertical("piiiii",0,0);
   yay.addWordHorizontal("peeeee",0,0);
   System.out.println(yay);
	}
}
