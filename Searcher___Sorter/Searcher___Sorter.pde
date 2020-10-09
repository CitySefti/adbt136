generate generator;
sorter mySorter;
searcher mySearcher;

void setup()
{
  size(600, 600);
  generator = new generate();
  generator.arrayFill();
  println(generator.randomArray);
  println(" ");
  
  mySorter = new sorter();
  mySearcher = new searcher();
}

void draw()
{
  background(0, 0, 0);
  textAlign(CENTER);
  fill(255, 255, 255);
  textSize(42);
  text("s = Bubble Sort", 200, 100);
  //text("p = Permutation Sort", 255, 200);
  text("b = Binary Search", 220, 300);
  text("l = Linear Search", 225, 400);
  text("r = New Array", 195, 500);
}

void keyPressed()
{
  if (key == 's')
  {
    mySorter.bubbleSort(generator.randomArray);
    println(generator.randomArray);
    println(" ");
    mySorter.comparisons = 0;
    mySorter.passes = 0;
  }
  //else if (key == 'p')
  //{
  //  mySorter.bogoSort(generator.randomArray);
  //  println(generator.randomArray);
  //  println(" ");
  //}
  else if (key == 'b')
  {
    int x = int(random(0, 9));    
    mySearcher.binarySearch(generator.randomArray, generator.randomArray[x]);
    mySearcher.comparisons = 0;
  }
  else if (key == 'l')
  {
    mySearcher.linearSearch(generator.randomArray);
    mySearcher.comparisons = 0;
  }
  else if (key == 'r')
  {
    generator.arrayFill();
    println(generator.randomArray);
    println(" ");
  }
}
