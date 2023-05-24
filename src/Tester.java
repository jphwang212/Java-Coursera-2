
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("short-test_log");
        logAnalyzer.printAll();
    }

    public void testUniqueIp(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.countUniqueIPs();
    }

    public void testHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        ArrayList<String> ips = la.uniqueIPVisitsOnDay("Mar 24");
        for(String s : ips){
            System.out.println(s);
        }
    }

    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int numIps = la.countUniqueIPsInRange(200, 299);
        System.out.println(numIps);
    }

    public static void main(String[] args) {
        Tester test = new Tester();
        test.testCountUniqueIPsInRange();
    }
}
