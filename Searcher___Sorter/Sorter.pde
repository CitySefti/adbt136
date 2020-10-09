public class sorter
{
  int passes, swap, comparisons, count;
  
  sorter()
  {
    passes = 0;
    swap = 0;
    comparisons = 0;
    count = 0;
  }
  
  void bubbleSort(int array[])
  {
    for (int i = 0; i < array.length; i++)
    {
      for (int j = 0; j < array.length - 1; j++)
      {
        if (array[j] > array[j + 1])
        {
          swap = array[j];
          array[j] = array[j + 1];
          array[j + 1] = swap;
          comparisons++;
        }
      }
      passes++;
    }
    println("no. Of Comparisons:" + comparisons);
    println("no. Of Passes:" + passes);
    println(" ");
  }
  
  //void bogoSort(int[] array)
  //{
  //  while (count <= 2)
  //  {
  //    myShuffle(array);
  //  }
    
  //  if (isSorted(array) == false)
  //  {
  //    println("Bogo Sort kekw");
  //    println(" ");
  //  }
  //  else
  //  {
  //    println("Bogo Sort best sort!!!");
  //    println(" ");
  //  }
  //}
  
  //void myShuffle(int[] array)
  //{
  //  for (int i = 0; i <= array.length - 1; i++)
  //  {
  //    swap(array, i, (int)(Math.random()*1));
  //  }
  //}
  
  //void swap(int[] array, int i, int j)
  //{
  //  int temporaryHolder = array[i];
  //  array[i] = array[j];
  //  array[j] = temporaryHolder;
  //}
  
  //boolean isSorted(int[] array)
  //{
  //  for (int i = 0; i < array.length; i++)
  //  {
  //    if (array[i] < array[i + 1])
  //    {
  //      return false;
  //    }
  //  }
  //  return true;
  //}
}
