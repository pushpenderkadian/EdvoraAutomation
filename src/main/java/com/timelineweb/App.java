package com.timelineweb;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.*;
import java.util.*;
import com.google.gson.JsonParser;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class App {

    static String tit;
    static String dat;
    static String stim;
    static String ftim;
    String clsr;
    static String cdate_str,endate_str;
    static int dd;
    static void App1() {
        Date cdate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        cdate_str = formatter.format(cdate);
        formatter = new SimpleDateFormat("dd");
        dd=Integer.parseInt(formatter.format(cdate));
        
    }
    static void set_title(WebDriver driver){
        int leftLimit = 48;
        int rightLimit = 122; 
        int targetStringLength = 10;
        Random random = new Random();

        String title = random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div[2]/div/input")).sendKeys(title);
        tit=title;
    }

    static void set_date(WebDriver driver) throws InterruptedException{
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div[3]/div[2]/div[1]")).click();
        Thread.sleep(3000);
        WebElement date=driver.findElement(By.xpath("//*[@id='chakra-modal-42']/div/div[3]"));
        List<WebElement> dates=date.findElements(By.xpath("//*[@class='css-1r65cup']"));
        List<WebElement> lbel=date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
        int c=0;
        boolean flag=true;
        for ( WebElement i : lbel ) {
            String d=i.getAttribute("value");
            if(d.equals(cdate_str)){
                flag=true;
                break;
            }
            else{
                flag=false;
            }
            c++;
         }
         if(flag==false){
            while(true){
                c=0;
                driver.findElement(By.xpath("//*[@id='chakra-modal-42']/div/div[1]/button[2]")).click();
                date=driver.findElement(By.xpath("//*[@id='chakra-modal-42']/div/div[3]"));
                dates=date.findElements(By.xpath("//*[@class='css-1r65cup']"));
                lbel=date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
                for ( WebElement i : lbel ) {
                    String d=i.getAttribute("value");
                    if(d.equals(cdate_str)){
                        flag=true;
                        break;
                    }
                    else{
                        flag=false;
                    }
                    c++;
                 }
                 if(flag==true){
                    break;
                 }
            }
         }
         dates.get(c).click();
         driver.findElement(By.xpath("//*[@id='chakra-modal-42']/div/div[4]/button[2]")).click();
         String edate_str=cdate_str;
         Date cdate = new Date();
         SimpleDateFormat formatter = new SimpleDateFormat("MM");
         String Month = formatter.format(cdate);
         Month=Integer.toString((Integer.valueOf(Month))+1);
         if(Integer.parseInt(Month)==12){
            StringBuilder string = new StringBuilder(edate_str);
            string.setCharAt(5,'0');
            string.setCharAt(6, '1');
         }
         else{
            StringBuilder string = new StringBuilder(edate_str);
            if(Integer.valueOf(Month)<10){
                string.setCharAt(6, Month.charAt(0));
            }
            else{
                string.setCharAt(5,Month.charAt(0));
                string.setCharAt(6, Month.charAt(1));
            }
            edate_str=string.toString();
         }
         driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div[3]/div[2]/div[3]")).click();
        Thread.sleep(3000);
        date=driver.findElement(By.xpath("//*[@id='chakra-modal-44']/div/div[3]"));
        dates=date.findElements(By.xpath("//*[@class='css-1r65cup']"));
        lbel=date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
        c=0;
        flag=true;
        for ( WebElement i : lbel ) {
            String d=i.getAttribute("value");
            if(d.equals(edate_str)){
                flag=true;
                break;
            }
            else{
                flag=false;
            }
            c++;
         }
         if(flag==false){
            while(true){
                c=0;
                date=driver.findElement(By.xpath("//*[@id='chakra-modal-44']/div/div[3]"));
                dates=date.findElements(By.xpath("//*[@class='css-1r65cup']"));
                lbel=date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
                for ( WebElement i : lbel ) {
                    String d=i.getAttribute("value");
                    if(d.equals(cdate_str)){
                        flag=true;
                        break;
                    }
                    else{
                        flag=false;
                    }
                    
                    c++;
                 }
                 if(flag==true){
                    break;
                 }
                 
                driver.findElement(By.xpath("//*[@id='chakra-modal-44']/div/div[1]/button[2]")).click();
            }
         }
         endate_str=cdate_str;
         dates.get(c).click();
         driver.findElement(By.xpath("//*[@id='chakra-modal-44']/div/div[4]/button[2]")).click();
         
    }

    static void set_time(WebDriver driver) throws InterruptedException, AWTException{
        int stime = (int)(Math.random()*(12-1+1)+1); 
        int ftime=0;
        driver.findElement(By.id("menu-button-39")).click();
        Thread.sleep(3000);
        if(stime==12){
            ftime=1;
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[1]")).click();
            Robot r=new Robot();
            r.mouseMove(driver.manage().window().getPosition().getX(),driver.manage().window().getPosition().getY());
            r.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
            r.keyPress(KeyEvent.VK_1);
            r.keyPress(KeyEvent.VK_2);
            r.keyPress(KeyEvent.VK_ENTER);
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[3]/button[2]")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[2]/button[2]")).click();
            //end timing

            driver.findElement(By.xpath("//*[@id='menu-button-41']")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[1]/div[1]")).click();
            r.mouseMove(driver.manage().window().getPosition().getX(),driver.manage().window().getPosition().getY());
            r.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
            r.keyPress(KeyEvent.VK_1);
            r.keyPress(KeyEvent.VK_ENTER);
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[1]/div[3]/button[2]")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[2]/button[2]")).click();
        
        }
        else{
            ftime=stime+1;
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[1]")).click();
            Robot r=new Robot();
            r.mouseMove(driver.manage().window().getPosition().getX(),driver.manage().window().getPosition().getY());
            r.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
            if(stime==1)
                r.keyPress(KeyEvent.VK_1);
            if(stime==2)
                r.keyPress(KeyEvent.VK_2);
            if(stime==3)
                r.keyPress(KeyEvent.VK_3);
            if(stime==4)
                r.keyPress(KeyEvent.VK_4);
            if(stime==5)
                r.keyPress(KeyEvent.VK_5);
            if(stime==6)
                r.keyPress(KeyEvent.VK_6);
            if(stime==7)
                r.keyPress(KeyEvent.VK_7);
            if(stime==8)
                r.keyPress(KeyEvent.VK_8);
            if(stime==9)
                r.keyPress(KeyEvent.VK_9);
            if(stime==10){
                r.keyPress(KeyEvent.VK_1);
                r.keyPress(KeyEvent.VK_0);
            }
            if(stime==11){
                r.keyPress(KeyEvent.VK_1);
                r.keyPress(KeyEvent.VK_1);
                r.keyPress(KeyEvent.VK_1);
            }    
            
            r.keyPress(KeyEvent.VK_ENTER);
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[3]/button[2]")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[2]/button[2]")).click();
            //end timing

            driver.findElement(By.xpath("//*[@id='menu-button-41']")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[1]/div[1]")).click();
            r.mouseMove(driver.manage().window().getPosition().getX(),driver.manage().window().getPosition().getY());
            r.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
            if(ftime==11){
                r.keyPress(KeyEvent.VK_1);
                r.keyPress(KeyEvent.VK_1);
            }   
            if(ftime==2)
                r.keyPress(KeyEvent.VK_2);
            if(ftime==3)
                r.keyPress(KeyEvent.VK_3);
            if(ftime==4)
                r.keyPress(KeyEvent.VK_4);
            if(ftime==5)
                r.keyPress(KeyEvent.VK_5);
            if(ftime==6)
                r.keyPress(KeyEvent.VK_6);
            if(ftime==7)
                r.keyPress(KeyEvent.VK_7);
            if(ftime==8)
                r.keyPress(KeyEvent.VK_8);
            if(ftime==9)
                r.keyPress(KeyEvent.VK_9);
            if(ftime==10){
                r.keyPress(KeyEvent.VK_1);
                r.keyPress(KeyEvent.VK_0);
            }
            if(ftime==12){
                r.keyPress(KeyEvent.VK_1);
                r.keyPress(KeyEvent.VK_2);
            }   
            r.keyPress(KeyEvent.VK_ENTER);
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[1]/div[3]/button[2]")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[2]/button[2]")).click();
        }
        stim=Integer.toString(stime);
        ftim=Integer.toString(ftime);
    }
    
    static void save_event_dt(){
        JSONParser jsonParser = new JSONParser();

        try {
            Object obj = jsonParser.parse(new FileReader("./dnrevents.json"));
            JSONArray jsonArray = (JSONArray)obj;


            JSONObject event1 = new JSONObject();
            event1.put("title", tit);
            event1.put("start_d", cdate_str);
            event1.put("end_d", endate_str);
            event1.put("start_t",stim);
            event1.put("end_t",ftim);
            JSONObject eventobj = new JSONObject(); 
            eventobj.put("event", event1);

            jsonArray.add(event1);

            FileWriter file = new FileWriter("./dnrevents.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        

    }
    static void verify_event(WebDriver driver) throws InterruptedException, FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();
        String etitle="",estim="",eetim="",esdat="";
        JSONArray a = (JSONArray) parser.parse(new FileReader("./dnrevents.json"));
        for (Object o : a)
        {
            JSONObject event = (JSONObject) o;
            etitle = (String) event.get("title");
            estim=(String) event.get("start_t");
            eetim=(String) event.get("end_t");
            esdat=(String) event.get("start_d");
        driver.get("https://timeline.edvora.me/");
        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[1]/div[2]/div/div[1]/div[1]/div/div[3]/div[1]/button[1]")).click();
        driver.findElement(By.xpath("//*[@id='menu-button-48']")).click();
        WebElement date=driver.findElement(By.xpath("//*[@id='menu-list-48']/div/div[3]"));
        List<WebElement> dates=date.findElements(By.xpath("//*[@class='css-1r65cup']"));
        List<WebElement> lbel=date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
        int x=0;
        boolean flag=true;
        for ( WebElement i : lbel ) {
            String d=i.getAttribute("value");
            if(d.equals(esdat)){
                flag=true;
                break;
            }
            else{
                flag=false;
            }
            
            x++;
         }
         if(flag==false){
            while(true){
                x=0;
                driver.findElement(By.xpath("//*[@id='chakra-modal-42']/div/div[1]/button[2]")).click();
                date=driver.findElement(By.xpath("//*[@id='chakra-modal-42']/div/div[3]"));
                dates=date.findElements(By.xpath("//*[@class='css-1r65cup']"));
                lbel=date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
                for ( WebElement i : lbel ) {
                    String d=i.getAttribute("value");
                    if(d.equals(esdat)){
                        flag=true;
                        break;
                    }
                    else{
                        flag=false;
                    }
                    x++;
                 }
                 if(flag==true){
                    break;
                 }
            }
         }
         dates.get(x).click();
         driver.findElement(By.xpath("//*[@id='menu-list-48']/div/div[4]/button[2]")).click();
        Thread.sleep(3000);
         WebElement all_eve= driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div/div[2]"));
        List<WebElement> eves=all_eve.findElements(By.xpath("//div[@class='css-1g2x4nl']"));
        boolean lner=false;
        for ( WebElement i : eves ) {
            List<WebElement> ptags=i.findElements(By.tagName("p"));
            String eveName=ptags.get(0).getText();
            if(eveName.equals(etitle)){
                System.out.println("Event found");
                i.findElement(By.cssSelector("div:first-child")).click();
                Thread.sleep(5000);                 
                WebElement dets=driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/section/div/div/div/div/div[2]"));
                List<WebElement> disc=dets.findElements(By.tagName("p"));
                String etit=disc.get(0).getText();
                String etim=disc.get(3).getText();
                lner=true;
                Thread.sleep(6000);
                break;
            }
            else{
                lner=false;
            }
            
         }
         if(lner==false){
            System.out.println("Event not found");
         }
    }
}

    public static void main(String[] args) throws InterruptedException, AWTException, FileNotFoundException, IOException, ParseException {
        App1();
        ChromeOptions options = new ChromeOptions(); 
        options.setExperimentalOption("debuggerAddress","localhost:9000");
		WebDriverManager.chromedriver().setup();
        //setting up driver with dynamic chrome driver
		WebDriver driver = new ChromeDriver(options);
        driver.get("https://timeline.edvora.me/");
        Thread.sleep(3000);
        System.out.println("Creating Event");
        if(driver.getCurrentUrl().equals("https://main.edvora.me/l")){
            driver.findElement(By.id("username")).sendKeys("test");
            driver.findElement(By.id("password")).sendKeys("Hello@123");
            driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/button")).click();
            driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/button")).click();
            Thread.sleep(5000);
        }
        if(driver.getCurrentUrl().equals("https://classrooms.edvora.me/")){
            driver.get("https://timeline.edvora.me/");
        }
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[1]/div[2]/div/div[1]/div[2]/a/button")).click();
        Thread.sleep(5000);
        set_title(driver);

        //set classroom
        driver.findElement(By.id("menu-button-15")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("menu-list-15-menuitem-13")).click();

        // time section : 

        set_time(driver);
        
        //date:-
         
        set_date(driver);
       save_event_dt();
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[3]/div[2]/div[2]/div/button[2]")).click();

        System.out.println("Event Created successfully\n\nVerifing created event");
        verify_event(driver);
    }
}
