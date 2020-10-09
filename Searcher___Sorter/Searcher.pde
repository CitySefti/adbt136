class searcher
{
  boolean found;
  int head, tail, mid, item, comparisons;
  
  searcher()
  {
    found = false;
    head = 0;  
    comparisons = 0;
  }
  
  void binarySearch(int array[], int j)
  {
    item = j;
    tail = array.length;
    mid = (head + tail) / 2;
    found = false;
    
    println("Searching for: " + item);
    
    while (found == false)
    {
      if (item > array[mid])
      {
        head = mid;
        mid = (head + tail) / 2;
        comparisons++;
      }
      else if (item < array[mid])
      {
        tail = mid;
        mid = (head + tail) / 2;
        comparisons++;
      }
      else if (item == array[mid])
      {
        println(item + " is at index " + mid + " of array ");
        println("no. Of Comparisons:" + comparisons);
        println(" ");
        found = true;
      }      
    } 
  }
  
  void linearSearch(int[] array)
  {
    item = int(random(0, 100));
    found = 
    false;
    println("Searching for: " + item);
    
    for (int i = 0; i < array.length - 1; i++)
    {
      comparisons++;
      if (array[i] == item)
      {
        println(item + " is at index " + i + " of array ");
        println("no. Of Comparisons:" + comparisons);
        println(" ");
        found = true;
      }
    }
    
    if (found == false)
    {
      println(item + " not in list ");
      println("no. Of Comparisons:" + comparisons);
      println(" ");
    }
  }
}
