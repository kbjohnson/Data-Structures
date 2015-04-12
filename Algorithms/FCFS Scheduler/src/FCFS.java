
import java.util.Scanner;

/**
 *
 * @author Kevin Johnson
 */
public class FCFS
{

   //array of all generated run times
   private static float runTime[];
   //arry of all generated arrive times
   private static float arriveTime[];
   //array of each processes id
   private static int procID[];
   //the number of processes to create
   private static int numProc;
   //current time of the program
   private static float time;
   //total waiting time sum
   private static float waitTimeSum;
   //total turnaround time sum
   private static float turnTimeSum;
   //total response time sum
   private static float responseTimeSum;
   /**
    * @param args the command line arguments
    */
   public static void main(String[] args)
   {
     FCFS myProcess = new FCFS();
     myProcess.FCFS();
   }
   //gets next process to schedule and calculates statistics
   private static int firstIndex() {
      int first = 0;
      for(int i=0;i<numProc;i++) {
         if(arriveTime[i]<=arriveTime[first]) {
            first = i;
         }
      }
      time += runTime[first];
      waitTimeSum += time - arriveTime[first] - runTime[first];
      turnTimeSum += time - arriveTime[first];
      responseTimeSum += time - arriveTime[first];
      System.out.println("id = " + procID[first] + "   Arrival Time = " 
         + arriveTime[first] + "    Est. Run Time = " + runTime[first]);
      return first;
   }
   
   public static void FCFS() {
      getInput();
      
      int count = 0;
      int first = 0;
      time = 0;
      
      while(count < numProc) {
        first = firstIndex();
        arriveTime[first] = (float) 201.0;//set above max allow to not read again     
        count++;
      }

      System.out.println("Average wating time is : " + waitTimeSum/numProc);
      System.out.println("Average turnaround time is : " + turnTimeSum/numProc);
      System.out.println("Average response time is : " + responseTimeSum/numProc);   
      System.out.println("Throughput is : " + time/numProc);

   }
   //gets input from user for number of processes and time quantum
   private static void getInput() {
      numProc = 0;
      Boolean valid = false;
      while(valid == false) {
         System.out.println("Enter the number of processes(50 or 100): ");
         Scanner scan = new Scanner(System.in);
         numProc = scan.nextInt();
         if(numProc == 50 || numProc == 100) {
            valid = true;
         }
      }
      runTime = new float[numProc];
      arriveTime = new float[numProc];
      procID = new int[numProc];
      for(int i=0;i<numProc;i++) {
         float randArival = (float) (Math.random()*200);
         float randRunTime = (float) (Math.random()*10);
         arriveTime[i] = randArival;
         runTime[i] = randRunTime;
         procID[i] = i+1;
      }
   }
}
