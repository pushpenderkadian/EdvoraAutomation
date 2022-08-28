package com.timelineweb;
// doubt in day selection
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;


import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class s5 {
    static String tit;
    static String dat;
    static String stim;
    static String ftim;
    static String cdate_str, endate_str;
    static int dd;
    static String wd;
    static String re = Integer.toString((int) (Math.random() * (5 - 1 + 1) + 1));

    static void App1() {
        Date cdate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        cdate_str = formatter.format(cdate);
        formatter = new SimpleDateFormat("dd");
        dd = Integer.parseInt(formatter.format(cdate));

    }

    static void set_title(WebDriver driver) {
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
        tit = title;
    }

    static void set_date(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath(
                "//*[@id='__next']/div/main/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[4]/div[1]/div[3]/div[2]/div[1]/p/div/div"))
                .click();
        Thread.sleep(3000);
        WebElement date = driver.findElement(By.xpath("//*[@id='chakra-modal-42']/div/div[3]"));
        List<WebElement> dates = date.findElements(By.xpath("//*[@class='css-1r65cup']"));
        List<WebElement> lbel = date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
        int c = 0;
        boolean flag = true;
        for (WebElement i : lbel) {
            String d = i.getAttribute("value");
            if (d.equals(cdate_str)) {
                flag = true;
                break;
            } else {
                flag = false;
            }
            
            c++;
        }
        if (flag == false) {
            while (true) {
                c = 0;
                driver.findElement(By.xpath("//*[@id='chakra-modal-42']/div/div[1]/button[2]")).click();
                date = driver.findElement(By.xpath("//*[@id='chakra-modal-42']/div/div[3]"));
                dates = date.findElements(By.xpath("//*[@class='css-1r65cup']"));
                lbel = date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
                for (WebElement i : lbel) {
                    String d = i.getAttribute("value");
                    if (d.equals(cdate_str)) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                    
                    c++;
                }
                if (flag == true) {
                    break;
                }
            }
        }
        dates.get(c).click();
        driver.findElement(By.xpath("//*[@id='chakra-modal-42']/div/div[4]/button[2]")).click();
        String edate_str = cdate_str;
        Date cdate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String Year = formatter.format(cdate);
        Year = Integer.toString((Integer.valueOf(Year)) + 1);
        StringBuilder string = new StringBuilder(edate_str);
        string.setCharAt(3, Year.charAt(3));
        edate_str = string.toString();
        driver.findElement(By.xpath(
                "//*[@id='__next']/div/main/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[4]/div[1]/div[3]/div[2]/div[3]/p/div/div"))
                .click();
        Thread.sleep(3000);
        date = driver.findElement(By.xpath("//*[@id='chakra-modal-44']/div/div[3]"));
        dates = date.findElements(By.xpath("//*[@class='css-1r65cup']"));
        lbel = date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
        c = 0;
        flag = true;
        for (WebElement i : lbel) {
            String d = i.getAttribute("value");
            if (d.equals(edate_str)) {
                flag = true;
                break;
            } else {
                flag = false;
            }
            c++;
        }
        if (flag == false) {
            while (true) {
                c = 0;
                driver.findElement(By.xpath("//*[@id='chakra-modal-44']/div/div[1]/button[2]")).click();
                date = driver.findElement(By.xpath("//*[@id='chakra-modal-44']/div/div[3]"));
                dates = date.findElements(By.xpath("//*[@class='css-1r65cup']"));
                lbel = date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
                for (WebElement i : lbel) {
                    String d = i.getAttribute("value");
                    if (d.equals(edate_str)) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                    c++;
                }
                if (flag == true) {
                    break;
                }
            }
        }
        endate_str = edate_str;
        dates.get(c).click();
        driver.findElement(By.xpath("//*[@id='chakra-modal-44']/div/div[4]/button[2]")).click();

    }

    static void set_time(WebDriver driver) throws InterruptedException, AWTException {
        int stime = (int) (Math.random() * (12 - 1 + 1) + 1);
        int ftime = 0;
        driver.findElement(By.id("menu-button-39")).click();
        Thread.sleep(3000);
        if (stime == 12) {
            ftime = 1;
            
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[1]/span")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[1]/input")).sendKeys(Integer.toString(stime));
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[3]/button[2]")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[2]/button[2]")).click();
            // end timing

            driver.findElement(By.xpath("//*[@id='menu-button-41']")).click();
            
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[1]/div[1]/span")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[1]/div[1]/input")).sendKeys("1");
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[1]/div[3]/button[2]")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[2]/button[2]")).click();

        } else {
            ftime = stime + 1;
            
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[1]/span")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[1]/input")).sendKeys(Integer.toString(stime));
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[3]/button[2]")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[2]/button[2]")).click();
            // end timing

            driver.findElement(By.xpath("//*[@id='menu-button-41']")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[1]/div[1]/span")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[1]/div[1]/input")).sendKeys(Integer.toString(ftime));
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[1]/div[3]/button[2]")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[2]/button[2]")).click();
        }
        stim = Integer.toString(stime);
        ftim = Integer.toString(ftime);
    }

    static void save_event_dt() {
        JSONParser jsonParser = new JSONParser();

        try {
            Object obj = jsonParser.parse(new FileReader("./cstwevents.json"));
            JSONArray jsonArray = (JSONArray) obj;

            JSONObject event1 = new JSONObject();
            event1.put("title", tit);
            event1.put("start_d", cdate_str);
            event1.put("end_d", endate_str);
            event1.put("start_t", stim);
            event1.put("end_t", ftim);
            event1.put("repeat", re);
            event1.put("Weekday", wd);
            JSONObject eventobj = new JSONObject();
            eventobj.put("event", event1);

            jsonArray.add(event1);

            FileWriter file = new FileWriter("./cstwevents.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
    public static void set_weekday(WebDriver driver) throws InterruptedException{
        int de = (int) (Math.random() * (7 - 0 + 1) + 0);
        Thread.sleep(3000);              
        driver.findElement(By.xpath("//*[@id='chakra-modal--body-33']/div/div[1]/div[2]/div/label[1]")).click();     
        WebElement days = driver.findElement(By.xpath("//*[@id='chakra-modal--body-33']/div/div[1]/div[2]/div"));
        List<WebElement> lbel = days.findElements(By.xpath("//label[@class='css-4g6ai3']"));
        List<WebElement> inpt = days.findElements(By.xpath("//*[@class='css-4g6ai3']/input"));
        int c = 0;
        for (WebElement i : inpt) {
            int d = Integer.valueOf(i.getAttribute("value"));
            if (d == de) {
                break;
            }
            c++;
        }
        lbel.get(c).click();
        wd = Integer.toString(de);
    }
    public static void verify_event(WebDriver driver) throws InterruptedException, FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();
        String etitle = "", estim = "", eetim = "", esdat = "", eedat = "", rpt = "",ewd="";
        JSONArray a = (JSONArray) parser.parse(new FileReader("./cstwevents.json"));
        for (Object o : a) {
            JSONObject event = (JSONObject) o;
            etitle = (String) event.get("title");
            estim = (String) event.get("start_t");
            eetim = (String) event.get("end_t");
            esdat = (String) event.get("start_d");
            eedat = (String) event.get("end_d");
            rpt = (String) event.get("repeat");
            ewd= (String) event.get("Weekday");
            driver.get("https://timeline.edvora.me/");
            Thread.sleep(3000);
            driver.findElement(By.xpath(
                    "//*[@id='__next']/div/main/div/div/div[1]/div[2]/div/div[1]/div[1]/div/div[3]/div[1]/button[1]"))
                    .click();
                LocalDate start = LocalDate.parse(esdat);
            LocalDate end = LocalDate.parse(eedat);
            List<LocalDate> totalDates = new ArrayList<>();
            LocalDate nextMonday = start;
            int daysToAdvance = 1;
            if (ewd.equals("0")) {
                while (!nextMonday.isAfter(end)) {
                    if (nextMonday.getDayOfWeek() == DayOfWeek.MONDAY) {
                        daysToAdvance = 7;
                        totalDates.add(nextMonday);
                    }
                    nextMonday = nextMonday.plusDays(daysToAdvance);
                }
            }
            if (ewd.equals("1")) {
                while (!nextMonday.isAfter(end)) {
                    if (nextMonday.getDayOfWeek() == DayOfWeek.TUESDAY) {
                        daysToAdvance = 7;
                        totalDates.add(nextMonday);
                    }
                    nextMonday = nextMonday.plusDays(daysToAdvance);
                }
            }
            if (ewd.equals("2")) {
                while (!nextMonday.isAfter(end)) {
                    if (nextMonday.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                        daysToAdvance = 7;
                        totalDates.add(nextMonday);
                    }
                    nextMonday = nextMonday.plusDays(daysToAdvance);
                }
            }
            if (ewd.equals("3")) {
                while (!nextMonday.isAfter(end)) {
                    if (nextMonday.getDayOfWeek() == DayOfWeek.THURSDAY) {
                        daysToAdvance = 7;
                        totalDates.add(nextMonday);
                    }
                    nextMonday = nextMonday.plusDays(daysToAdvance);
                }
            }
            if (ewd.equals("4")) {
                while (!nextMonday.isAfter(end)) {
                    if (nextMonday.getDayOfWeek() == DayOfWeek.FRIDAY) {
                        daysToAdvance = 7;
                        totalDates.add(nextMonday);
                    }
                    nextMonday = nextMonday.plusDays(daysToAdvance);
                }
            }
            if (ewd.equals("5")) {
                while (!nextMonday.isAfter(end)) {
                    if (nextMonday.getDayOfWeek() == DayOfWeek.SATURDAY) {
                        daysToAdvance = 7;
                        totalDates.add(nextMonday);
                    }
                    nextMonday = nextMonday.plusDays(daysToAdvance);
                }
            }
            if (ewd.equals("6")) {
                while (!nextMonday.isAfter(end)) {
                    if (nextMonday.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        daysToAdvance = 7;
                        totalDates.add(nextMonday);
                    }
                    nextMonday = nextMonday.plusDays(daysToAdvance);
                }
            }
            for (int f = 0; f < totalDates.size(); f++) {

                Thread.sleep(3000);
                driver.findElement(By.xpath(
                        "//*[@id='__next']/div/main/div/div/div[1]/div[2]/div/div[1]/div[1]/div/div[3]/div[1]/button[1]"))
                        .click();
                driver.findElement(By.xpath("//*[@id='menu-button-48']")).click();
                WebElement date = driver.findElement(By.xpath("//*[@id='menu-list-48']/div/div[3]"));
                List<WebElement> dates = date.findElements(By.xpath("//*[@class='css-1r65cup']"));
                List<WebElement> lbel = date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
                int x = 0;
                boolean flag = true;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                for (WebElement i : lbel) {
                    String d = i.getAttribute("value");
                    if (d.equals(totalDates.get(f).format(formatter))) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }

                    x++;
                }
                if (flag == false) {
                    while (true) {
                        x = 0;
                        driver.findElement(By.xpath("//*[@id='menu-list-48']/div/div[1]/button[2]")).click();
                        date = driver.findElement(By.xpath("//*[@id='menu-list-48']/div/div[3]"));
                        dates = date.findElements(By.xpath("//*[@class='css-1r65cup']"));
                        lbel = date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
                        for (WebElement i : lbel) {
                            String d = i.getAttribute("value");
                            if (d.equals(totalDates.get(f).format(formatter))) {
                                flag = true;
                                break;
                            } else {
                                flag = false;
                            }
                            x++;
                        }
                        if (flag == true) {
                            break;
                        }
                    }
                }
                
                    try {

                        dates.get(x).click();
                    } catch (Exception e) {
                        x = 0;
                        Thread.sleep(2000);
                        driver.findElement(By.xpath("//*[@id='menu-list-48']/div/div[1]/button[2]")).click();
                        dates.get(x).click();
                    }
                    if (lbel.get(x).getAttribute("value").equals(eedat)) {
                        break;

                    }
                    driver.findElement(By.xpath("//*[@id='menu-list-48']/div/div[4]/button[2]")).click();
                    Thread.sleep(3000);
                    WebElement all_eve = driver
                            .findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div/div[2]"));
                    List<WebElement> eves = all_eve.findElements(By.xpath("//div[@class='css-1g2x4nl']"));
                    boolean lner = false;
                    for (WebElement i : eves) {
                        List<WebElement> ptags = i.findElements(By.tagName("p"));
                        String eveName = ptags.get(0).getText();
                        if (eveName.equals(etitle)) {
                            System.out.println("Event found");
                            i.findElement(By.cssSelector("div:first-child")).click();
                            Thread.sleep(5000);
                            WebElement dets = driver.findElement(
                                    By.xpath("/html/body/div[2]/div/div[3]/div/section/div/div/div/div/div[2]"));
                            List<WebElement> disc = dets.findElements(By.tagName("p"));
                            String etit = disc.get(0).getText();
                            String etim = disc.get(3).getText();
                            lner = true;
                            driver.findElement(
                                    By.xpath("/html/body/div[2]/div/div[3]/div/section/div/div/div/div/div[3]/button"))
                                    .click();
                            break;
                        } else {
                            lner = false;
                        }

                    }
                    if (lner == false) {
                        System.out.println("Event not found");
                        break;
                    }
                    x++;
                    driver.findElement(By.xpath(
                            "//*[@id='__next']/div/main/div/div/div[1]/div[2]/div/div[1]/div[1]/div/div[3]/div[1]/button[1]"))
                            .click();
                    driver.findElement(By.xpath("//*[@id='menu-button-48']")).click();
                }
                break;
            }
    }

    public static void main(String[] args) throws InterruptedException, AWTException, FileNotFoundException, IOException, ParseException {
        App1();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9000");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://timeline.edvora.me/");
        Thread.sleep(3000);
        
        System.out.println("Creating Event");
        if (driver.getCurrentUrl().equals("https://main.edvora.me/l")) {
        driver.findElement(By.id("username")).sendKeys("automation");
        driver.findElement(By.id("password")).sendKeys("Hello@123");
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/button")).click();
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/button")).click();
        Thread.sleep(5000);
        }
        if (driver.getCurrentUrl().equals("https://classrooms.edvora.me/")) {
        driver.get("https://timeline.edvora.me/");
        }
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[1]/div[2]/div/div[1]/div[2]/a/button"))
        .click();
        Thread.sleep(5000);
        set_title(driver);

        // set classroom
        driver.findElement(By.id("menu-button-15")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("menu-list-15-menuitem-13")).click();

        // type of event
        // weekly event
        Robot r = new Robot();
        r.mouseMove(driver.manage().window().getPosition().getX(),
        driver.manage().window().getPosition().getY());
        r.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        r.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);

        driver.findElement(By.id("menu-button-37")).click();
        driver.findElement(By.id("menu-list-37-menuitem-32")).click();
        driver.findElement(By.id("menu-button-75")).click();
        driver.findElement(By.id("menu-list-75-menuitem-71")).click();
        set_weekday(driver);
        driver.findElement(By.xpath("//*[@id='chakra-modal--body-33']/div/div[1]/div[1]/div[1]/div/input")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//*[@id='chakra-modal--body-33']/div/div[1]/div[1]/div[1]/div/input")).sendKeys(re);
        driver.findElement(By.xpath("//*[@id='chakra-modal--body-33']/div/div[2]/button[2]")).click();
        // time section :

        set_time(driver);

        // date:-

        set_date(driver);
        save_event_dt();
        driver.findElement(By.xpath(
        "//*[@id='__next']/div/main/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[3]/div[2]/div[2]/div/button[2]"))
        .click();
        System.out.println("Event Created successfully\n\nVerifing created event");
        verify_event(driver);
    }
}
