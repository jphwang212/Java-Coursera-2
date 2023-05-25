
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
        la.readFile("weblog2_log");
        int answer = la.countUniqueIPs();
        System.out.println(answer);
    }

    public void testHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> ips = la.uniqueIPVisitsOnDay("Mar 24");
        for(String s : ips){
            System.out.println(s);
        }
    }

    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int numIps = la.countUniqueIPsInRange(200, 299);
        System.out.println(numIps);
    }

    public void testMostNumberVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> visits = la.countVisitsPerIP();
        int answer = la.mostNumberVisitsByIP(visits);
        System.out.println("Number = " + answer);
    }

//    public void testDayWithMostVisits(){
//        LogAnalyzer la = new LogAnalyzer();
//        la.readFile("weblog1_log");
//        HashMap<String, Integer> visits = la.countVisitsPerIP();
//        ArrayList<String> answer = la.dayWithMostIPVisits(visits);
//    }

    public void testDayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> test = la.iPsForDays();
        String answer = la.dayWithMostIPVisits(test);
        System.out.println("Date: " + answer);
    }

    public void testiPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> test = la.iPsForDays();
        ArrayList<String> answer = la.iPsWithMostVisitsOnDay(test, "Mar 17");
        System.out.println(test.get("Sep 30"));
    }

    public static void main(String[] args) {
        Tester test = new Tester();
        test.testiPsWithMostVisitsOnDay();
    }
}

