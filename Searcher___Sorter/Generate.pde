public class generate
{
  int[] randomArray;
  
  generate()
  {
    randomArray = new int[10];
  }
  
  void arrayFill()
  {
    for (int i = 0; i < 10; i++)
    {
      randomArray[i] = int(random(0, 100));
    }
  }
}
