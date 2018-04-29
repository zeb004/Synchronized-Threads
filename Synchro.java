import java.util.*;
import java.util.concurrent.TimeUnit;
public class Synchro {
public static void main(String[] args) throws InterruptedException {
int Fac=5;
int Bowl=25;
int i=1;
while (i==1) {
while(Bowl>0) {
System.out.println("Nom Nom");
Bowl=Bowl-1;
TimeUnit.SECONDS.sleep(1);
}
if (Bowl==0) {
System.out.println("Refilling...");
Bowl=25;
TimeUnit.SECONDS.sleep(5);
}

}







}
} 
