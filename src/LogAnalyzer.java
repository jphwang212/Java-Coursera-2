
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
     public int countUniqueIPs(){
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for(LogEntry le : records){
             String ipAddr = le.getIpAddress();
             if(!uniqueIps.contains(ipAddr)){
                 uniqueIps.add(ipAddr);
             }
         }
         return uniqueIps.size();
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num){
         ArrayList<LogEntry> higherStatusCodes = new ArrayList<LogEntry>();
         for(LogEntry le : records){
             if(le.getStatusCode() > num){
                 higherStatusCodes.add(le);
             }
         }
         for(LogEntry le : higherStatusCodes){
             System.out.println(le);
         }
     }

     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for(LogEntry le : records){
             String accessTime = le.getAccessTime().toString();
             String[] dateComponents = accessTime.split(" ");
             String date = dateComponents[1] + " " + dateComponents[2];
             String ipAddress = le.getIpAddress();
             if(date.equals(someday) && !uniqueIps.contains(ipAddress)){
                 uniqueIps.add(ipAddress);
             }
         }
         System.out.println(uniqueIps.size());
         return uniqueIps;
     }

     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records){
             int status = le.getStatusCode();
             if(status >= low && status <= high){
                 String ipAddress = le.getIpAddress();
                 if(!uniqueIPs.contains(ipAddress)){
                     uniqueIPs.add(ipAddress);
                 }
             }
         }
         return uniqueIPs.size();
     }

     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> visitPerIP = new HashMap<String, Integer>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!visitPerIP.containsKey(ip)){
                 visitPerIP.put(ip, 1);
             } else {
                 visitPerIP.put(ip, visitPerIP.get(ip) + 1);
             }
         }
         return visitPerIP;
     }
     public int mostNumberVisitsByIP(HashMap<String, Integer> visitsPerIp){
        int mostVisits = 0;
        for(String ip : visitsPerIp.keySet()){
            int visits = visitsPerIp.get(ip);
            if(visits > mostVisits){
                mostVisits = visits;
            }
        }
        return mostVisits;
     }

     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> ipsForDays = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records){
             String accessTime = le.getAccessTime().toString();
             String[] dateComponents = accessTime.split(" ");
             String date = dateComponents[1] + " " + dateComponents[2];
             String ipAddress = le.getIpAddress();
             if(!ipsForDays.containsKey(ipAddress)){
                 ArrayList<String> firstIp = new ArrayList<String>();
                 firstIp.add(ipAddress);
                 ipsForDays.put(date, firstIp);
             } else {
                 ArrayList<String> ips = ipsForDays.get(date);
                 ips.add(ipAddress);
                 ipsForDays.replace(date, ips);
             }
         }
         return ipsForDays;
     }
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> visitsByDate){
         int largestSize = 0;
         String dateWithMostVisits = "";;
         for(String date : visitsByDate.keySet()){
             int currSize = visitsByDate.get(date).size();
             if(currSize > largestSize){
                dateWithMostVisits = date;
                largestSize = currSize;
             }
         }
         return dateWithMostVisits;
     }

     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> visitsByDate, String day){
         String dayWithMostVisits = dayWithMostIPVisits(visitsByDate);

     }
}
