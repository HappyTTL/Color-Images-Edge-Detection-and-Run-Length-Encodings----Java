/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */

import java.util.Iterator;

public class RunLengthEncoding implements Iterable {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */

    private int width;
    private int height;
    private DList runLengthList;


  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with two parameters) constructs a run-length
   *  encoding of a black PixImage of the specified width and height, in which
   *  every pixel has red, green, and blue intensities of zero.
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   */

  public RunLengthEncoding(int width, int height) {
    // Your solution here.
      this.width = width;
      this.height = height;
      runLengthList = new DList();
      runLengthList.insertEnd(0, 0, 0, width*height);
  }

  /**
   *  RunLengthEncoding() (with six parameters) constructs a run-length
   *  encoding of a PixImage of the specified width and height.  The runs of
   *  the run-length encoding are taken from four input arrays of equal length.
   *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
   *  blue[i].
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   *  @param red is an array that specifies the red intensity of each run.
   *  @param green is an array that specifies the green intensity of each run.
   *  @param blue is an array that specifies the blue intensity of each run.
   *  @param runLengths is an array that specifies the length of each run.
   *
   *  NOTE:  All four input arrays should have the same length (not zero).
   *  All pixel intensities in the first three arrays should be in the range
   *  0...255.  The sum of all the elements of the runLengths array should be
   *  width * height.  (Feel free to quit with an error message if any of these
   *  conditions are not met--though we won't be testing that.)
   */

  public RunLengthEncoding(int width, int height, int[] red, int[] green,
                           int[] blue, int[] runLengths) {
    // Your solution here.
      this.width = width;
      this.height = height;
      runLengthList = new DList();
      for (int i = 0; i < red.length; i++) {
          runLengthList.insertEnd(red[i], green[i], blue[i], runLengths[i]);
      }

  }

  /**
   *  getWidth() returns the width of the image that this run-length encoding
   *  represents.
   *
   *  @return the width of the image that this run-length encoding represents.
   */

  public int getWidth() {
    // Replace the following line with your solution.
    return width;
  }

  /**
   *  getHeight() returns the height of the image that this run-length encoding
   *  represents.
   *
   *  @return the height of the image that this run-length encoding represents.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return height;
  }

  /**
   *  iterator() returns a newly created RunIterator that can iterate through
   *  the runs of this RunLengthEncoding.
   *
   *  @return a newly created RunIterator object set to the first run of this
   *  RunLengthEncoding.
   */
  public RunIterator iterator() {
    // Replace the following line with your solution.
      //if (runLengthList.size == 0){
        //  return new RunIterator(new DListNode(0,0,0,0));
      //} else {
      
      System.out.println("I am here:");
      System.out.println(runLengthList.size);
      System.out.println(runLengthList.head.next.runItem.red);
      System.out.println(runLengthList.head.next.runItem.runLengths);
      if (runLengthList.head.next == null){
          return new RunIterator(new DListNode(), new DList());
      } else {
          return new RunIterator(runLengthList.head, runLengthList);
      }
      //}
    // You'll want to construct a new RunIterator, but first you'll need to
    // write a constructor in the RunIterator class.
  }

  /**
   *  toPixImage() converts a run-length encoding of an image into a PixImage
   *  object.
   *
   *  @return the PixImage that this RunLengthEncoding encodes.
   */
  public PixImage toPixImage() {
    // Replace the following line with your solution.
      PixImage decodedPixImage = new PixImage(width, height);
      RunIterator currentIterator = iterator();
//      int[] temp2 = currentIterator.next();
//      System.out.println(temp2);
//      System.out.println("I am here 2");
//      System.out.println(runLengthList.head.next.runItem.red);
      int W = 0;
      int H = 0;
      //          System.out.print(this.iterator().next());
      while(currentIterator.hasNext()){
          int[] currentArray = currentIterator.next();
          int L = currentArray[3];
          while(L > 0){
              decodedPixImage.setPixel(W,H,(short)currentArray[0],(short)currentArray[1],(short)currentArray[2]);
              L--;
              H++;
              if (H == height){
                  H = 0;
                  W++;
              }
          }
          
          
      }
      return decodedPixImage;
  }

  /**
   *  toString() returns a String representation of this RunLengthEncoding.
   *
   *  This method isn't required, but it should be very useful to you when
   *  you're debugging your code.  It's up to you how you represent
   *  a RunLengthEncoding as a String.
   *
   *  @return a String representation of this RunLengthEncoding.
   */
  public String toString() {
    // Replace the following line with your solution.
      RunIterator currentIterator = iterator();
      String rleString = "";
      
      while(currentIterator.hasNext()){
          int[] currentArray = currentIterator.next();
          rleString += "RGB: " + currentArray[0] + currentArray[1] + currentArray[2] + "Length: " + currentArray[3] + "\n";
      }
    return rleString;
  }


  /**
   *  The following methods are required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of a specified PixImage.
   * 
   *  Note that you must encode the image in row-major format, i.e., the second
   *  pixel should be (1, 0) and not (0, 1).
   *
   *  @param image is the PixImage to run-length encode.
   */
  public RunLengthEncoding(PixImage image) {
    // Your solution here, but you should probably leave the following line
    // at the end.
      this.width = image.getWidth();
      this.height = image.getHeight();
      runLengthList = new DList();
      int i = 0;
      int j = 0;
      int[] current = {image.getRed(0,0), image.getGreen(0,0), image.getBlue(0,0)};
      int length = 0;
      while(i < width){
          while(j < height){
              if (image.getRed(i,j) == current[0] && image.getGreen(i,j) == current[1] && image.getBlue(i,j) == current[2]) {
                  length++;
              } else {
                  runLengthList.insertEnd(current[0], current[1], current[2], length);
                  current[0] = image.getRed(i,j);
                  current[1] = image.getGreen(i,j);
                  current[2] = image.getBlue(i,j);
                  length = 1;
//                  System.out.println(current[0]);
//                  System.out.println(current[1]);
//                  System.out.println(current[2]);
              }
              j++;
          }
          j = 0;
          i++;
      }
      runLengthList.insertEnd(current[0], current[1], current[2], length);
    check();
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same RGB intensities, or if the sum of
   *  all run lengths does not equal the number of pixels in the image.
   */
  public void check() {
    // Your solution here.
      DListNode currentNode = runLengthList.head.next;
      int count = 0;
      while (currentNode.next != runLengthList.tail) {
          if (currentNode.runItem.equals(currentNode.next.runItem)){
              System.out.println("same consecutive same RGB runs!! Encoding Failed!!");
          }
          count += currentNode.runItem.runLengths;
          currentNode = currentNode.next;
      }
      count += currentNode.runItem.runLengths;
      
      if (count != width*height){
          System.out.println("size mismatch!! Encoding Error!!");
          System.out.println("count is:" + count);
          System.out.println("size is:" + (width+1)*(height+1));
      }

      
  }


  /**
   *  The following method is required for Part IV.
   */

  /**
   *  setPixel() modifies this run-length encoding so that the specified color
   *  is stored at the given (x, y) coordinates.  The old pixel value at that
   *  coordinate should be overwritten and all others should remain the same.
   *  The updated run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs with exactly the same RGB color.
   *
   *  @param x the x-coordinate of the pixel to modify.
   *  @param y the y-coordinate of the pixel to modify.
   *  @param red the new red intensity to store at coordinate (x, y).
   *  @param green the new green intensity to store at coordinate (x, y).
   *  @param blue the new blue intensity to store at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
      int setPosition = x * height + y + 1;
      System.out.println("setPosition: " + setPosition + "\n");
      DListNode currentNode = runLengthList.head.next;
      if (setPosition > height*width){
          System.out.println("pixel exceeds the image size!!!");
          return;
      }
      while (currentNode != runLengthList.tail) {
          setPosition -= currentNode.runItem.runLengths;
          System.out.println("setPosition: " + setPosition + "\n");
          if (setPosition == 0) {
              DListNode newPrev = new DListNode(currentNode.runItem.red, currentNode.runItem.green, currentNode.runItem.blue, currentNode.runItem.runLengths - 1);
              DListNode newCurrent = new DListNode(red, green, blue, 1);
              newPrev.prev = currentNode.prev;
              currentNode.prev.next = newPrev;
              newPrev.next = newCurrent;
              newCurrent.prev = newPrev;
              newCurrent.next = currentNode.next;
              currentNode.next.prev = newCurrent;
              runLengthList.size++;
              if (newPrev.runItem.runLengths == 0){
                  newCurrent.prev = newPrev.prev;
                  newPrev.prev.next = newCurrent;
                  runLengthList.size--;
//                  System.out.println("prev red: " + newPrev.prev.runItem.red + "\n");
//                  System.out.println("prev red: " + newPrev.prev.runItem.green + "\n");
//                  System.out.println("prev red: " + newPrev.prev.runItem.blue + "\n");
//                  
                  nodeMerge(newPrev.prev, newCurrent, newCurrent.next);
              }
              break;
          } else if (setPosition < 0){
              DListNode newPrev = new DListNode(currentNode.runItem.red, currentNode.runItem.green, currentNode.runItem.blue, currentNode.runItem.runLengths + setPosition - 1);
              DListNode newCurrent = new DListNode(red, green, blue, 1);
              DListNode newNext = new DListNode(currentNode.runItem.red, currentNode.runItem.green, currentNode.runItem.blue, -setPosition);
              newPrev.prev = currentNode.prev;
              currentNode.prev.next = newPrev;
              newPrev.next = newCurrent;
              newCurrent.prev = newPrev;
              newCurrent.next = newNext;
              newNext.prev = newCurrent;
              newNext.next = currentNode.next;
              currentNode.next.prev = newNext;
              runLengthList.size += 2;
              if (newPrev.runItem.runLengths == 0){
                  newCurrent.prev = newPrev.prev;
                  newPrev.prev.next = newCurrent;
                  runLengthList.size--;
                  nodeMerge(newPrev.prev, newCurrent, newNext);
              } else if (newNext.runItem.runLengths == 0){
                  newCurrent.next = newNext.next;
                  newNext.next.prev = newCurrent;
                  runLengthList.size--;
                  nodeMerge(newPrev, newCurrent, newNext.next);
              }
              
              nodeMerge(newPrev, newCurrent, newNext);
              break;
          } else {
              currentNode = currentNode.next;
          }
      }

      
      check();
  }

    
    public void nodeMerge(DListNode nPrev, DListNode nCurrent, DListNode nNext){
        if (nPrev.prev == runLengthList.head) {
            if (nNext.runItem.red == nCurrent.runItem.red && nNext.runItem.green == nCurrent.runItem.green && nNext.runItem.blue == nCurrent.runItem.blue){
                DListNode newCurrent = new DListNode(nNext.runItem.red, nNext.runItem.green, nNext.runItem.blue, nNext.runItem.runLengths + nCurrent.runItem.runLengths);
                newCurrent.prev = nPrev;
                newCurrent.next = nNext.next;
                nNext.next.prev = newCurrent;
                nPrev.next = newCurrent;
                runLengthList.size--;
                return;
            } else if (nNext.next == runLengthList.tail){
                if (nPrev.runItem.red == nCurrent.runItem.red && nPrev.runItem.green == nCurrent.runItem.green && nPrev.runItem.blue == nCurrent.runItem.blue){
                    DListNode newCurrent = new DListNode(nPrev.runItem.red, nPrev.runItem.green, nPrev.runItem.blue, nPrev.runItem.runLengths + nCurrent.runItem.runLengths);
                    newCurrent.prev = nPrev.prev;
                    newCurrent.next = nNext;
                    nNext.prev = newCurrent;
                    nPrev.prev.next = newCurrent;
                    runLengthList.size--;
                    System.out.println("length: " + newCurrent.runItem.runLengths + "\n");
                    return;
                }
            } else if (nPrev.runItem.red == nCurrent.runItem.red && nPrev.runItem.green == nCurrent.runItem.green && nPrev.runItem.blue == nCurrent.runItem.blue && nNext.runItem.red == nCurrent.runItem.red && nNext.runItem.green == nCurrent.runItem.green && nNext.runItem.blue == nCurrent.runItem.blue){
                DListNode newCurrent = new DListNode(nPrev.runItem.red, nPrev.runItem.green, nPrev.runItem.blue, nPrev.runItem.runLengths + nCurrent.runItem.runLengths + nNext.runItem.runLengths);
                newCurrent.prev = nPrev.prev;
                newCurrent.next = nNext.next;
                nNext.next.prev = newCurrent;
                nPrev.prev.next = newCurrent;
                runLengthList.size -= 2;
                return;
            } else if (nPrev.runItem.red == nCurrent.runItem.red && nPrev.runItem.green == nCurrent.runItem.green && nPrev.runItem.blue == nCurrent.runItem.blue){
                DListNode newCurrent = new DListNode(nPrev.runItem.red, nPrev.runItem.green, nPrev.runItem.blue, nPrev.runItem.runLengths + nCurrent.runItem.runLengths);
                newCurrent.prev = nPrev.prev;
                newCurrent.next = nNext;
                nNext.prev = newCurrent;
                nPrev.prev.next = newCurrent;
                runLengthList.size--;
                System.out.println("length: " + newCurrent.runItem.runLengths + "\n");
                return;
            } else if (nNext.runItem.red == nCurrent.runItem.red && nNext.runItem.green == nCurrent.runItem.green && nNext.runItem.blue == nCurrent.runItem.blue){
                DListNode newCurrent = new DListNode(nNext.runItem.red, nNext.runItem.green, nNext.runItem.blue, nNext.runItem.runLengths + nCurrent.runItem.runLengths);
                newCurrent.prev = nPrev;
                newCurrent.next = nNext.next;
                nNext.next.prev = newCurrent;
                nPrev.next = newCurrent;
                runLengthList.size--;
                return;
            } else {
                return;
            }
        }

    }
//    public void clearNode(DList extraList){
//        DListNode currentNode = extraList.head.next;
//        while (currentNode != extraList.tail) {
//            if (currentNode.runItem.runLengths == 0){
//                currentNode.prev.next = currentNode.next;
//                currentNode.next.prev = currentNode.prev;
//                extraList.size--;
//            }
//            currentNode = currentNode.next;
//        }
//    }
  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * setAndCheckRLE() sets the given coordinate in the given run-length
   * encoding to the given value and then checks whether the resulting
   * run-length encoding is correct.
   *
   * @param rle the run-length encoding to modify.
   * @param x the x-coordinate to set.
   * @param y the y-coordinate to set.
   * @param intensity the grayscale intensity to assign to pixel (x, y).
   */
  private static void setAndCheckRLE(RunLengthEncoding rle,
                                     int x, int y, int intensity) {
    rle.setPixel(x, y,
                 (short) intensity, (short) intensity, (short) intensity);
    rle.check();
  }

  /**
   * main() runs a series of tests of the run-length encoding code.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 3, 6 },
                                                   { 1, 4, 7 },
                                                   { 2, 5, 8 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x3 image.  Input image:");
    System.out.print(image1);
    RunLengthEncoding rle1 = new RunLengthEncoding(image1);
      System.out.println(rle1.runLengthList.head.next.runItem.red);
      System.out.println(rle1.runLengthList.head.next.runItem.runLengths);
      System.out.println(rle1.toString());
    rle1.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3,
           "RLE1 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(image1.equals(rle1.toPixImage()),
           "image1 -> RLE1 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 42);
    image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           /*
                       array2PixImage(new int[][] { { 42, 3, 6 },
                                                    { 1, 4, 7 },
                                                    { 2, 5, 8 } })),
           */
           "Setting RLE1[0][0] = 42 fails.");
//
    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 0, 42);
    image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][0] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 1, 2);
    image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][1] = 2 fails.");
//
    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 0);
    image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 7);
    image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 7 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 42);
    image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 2, 42);
    image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][2] = 42 fails.");

//
    PixImage image2 = array2PixImage(new int[][] { { 2, 3, 5 },
                                                   { 2, 4, 5 },
                                                   { 3, 4, 6 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on another 3x3 image.  Input image:");
    System.out.print(image2);
    RunLengthEncoding rle2 = new RunLengthEncoding(image2);
    rle2.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
           "RLE2 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(rle2.toPixImage().equals(image2),
           "image2 -> RLE2 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 0, 1, 2);
    image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 2, 0, 2);
    image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[2][0] = 2 fails.");


    PixImage image3 = array2PixImage(new int[][] { { 0, 5 },
                                                   { 1, 6 },
                                                   { 2, 7 },
                                                   { 3, 8 },
                                                   { 4, 9 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 5x2 image.  Input image:");
    System.out.print(image3);
    RunLengthEncoding rle3 = new RunLengthEncoding(image3);
    rle3.check();
    System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
    doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
           "RLE3 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 5x2 encoding.");
    doTest(rle3.toPixImage().equals(image3),
           "image3 -> RLE3 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 4, 0, 6);
    image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[4][0] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 1, 6);
    image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][1] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 0, 1);
    image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][0] = 1 fails.");


    PixImage image4 = array2PixImage(new int[][] { { 0, 3 },
                                                   { 1, 4 },
                                                   { 2, 5 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x2 image.  Input image:");
    System.out.print(image4);
    RunLengthEncoding rle4 = new RunLengthEncoding(image4);
    rle4.check();
    System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
    doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
           "RLE4 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x2 encoding.");
    doTest(rle4.toPixImage().equals(image4),
           "image4 -> RLE4 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 2, 0, 0);
    image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[2][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 0);
    image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 1);
    image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 1 fails.");
  }
}
