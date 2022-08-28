package com.timelineweb;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
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

public class s6 {
    static String tit;
    static String dat;
    static String stim;
    static String ftim;
    static String cdate_str, endate_str, modates,typp;
    static int dd;
    static String wd, nthday;
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
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[1]/input"))
                    .sendKeys(Integer.toString(stime));
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
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[1]/input"))
                    .sendKeys(Integer.toString(stime));
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[3]/button[2]")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[2]/button[2]")).click();
            // end timing

            driver.findElement(By.xpath("//*[@id='menu-button-41']")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[1]/div[1]/span")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[1]/div[1]/input"))
                    .sendKeys(Integer.toString(ftime));
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[1]/div[3]/button[2]")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-41']/div/div[2]/button[2]")).click();
        }
        stim = Integer.toString(stime);
        ftim = Integer.toString(ftime);
    }

    static void save_event_dt() {
        JSONParser jsonParser = new JSONParser();

        try {
            Object obj = jsonParser.parse(new FileReader("./cstmevents.json"));
            JSONArray jsonArray = (JSONArray) obj;

            JSONObject event1 = new JSONObject();
            event1.put("title", tit);
            event1.put("start_d", cdate_str);
            event1.put("end_d", endate_str);
            event1.put("start_t", stim);
            event1.put("end_t", ftim);
            event1.put("repeat", re);
            event1.put("seqw", nthday);
            event1.put("weekday", wd);
            event1.put("dates", modates);
            event1.put("type",typp);
            JSONObject eventobj = new JSONObject();
            eventobj.put("event", event1);

            jsonArray.add(event1);

            FileWriter file = new FileWriter("./cstmevents.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void set_monthdate(WebDriver driver) throws InterruptedException {
        typp="m";
        driver.findElement(By.xpath("//*[@id='chakra-modal--body-33']/div/div[1]/div[2]/div/div[1]/label")).click();
        Thread.sleep(1000);
        int ndates = (int) (Math.random() * (30 - 1 + 1) + 1);
        String dts = "0";
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < 31; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i = 0; i < ndates; i++) {
            int dom = list.get(i);
            driver.findElement(
                    By.xpath("//*[@id='chakra-modal--body-33']/div/div[1]/div[2]/div/div[1]/div/div/div/div/label["
                            + Integer.toString(dom) + "]"))
                    .click();
            dts = dts + "," + dom;
        }
        modates = dts;

    }

    public static void set_monthday(WebDriver driver) throws InterruptedException {
        typp="w";
        driver.findElement(By.xpath("//*[@id='chakra-modal--body-33']/div/div[1]/div[2]/div/div[2]/label")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='menu-button-132']")).click();
        int nth = (int) (Math.random() * (4 - 0 + 1) + 0);
        Thread.sleep(1000);
        driver.findElement(By.id("menu-list-132-menuitem-" + Integer.toString(126 + nth) + "")).click();
        nthday = Integer.toString(nth);
        driver.findElement(By.id("menu-button-142")).click();
        int weed = (int) (Math.random() * (6 - 0 + 1) + 0);
        Thread.sleep(1000);
        driver.findElement(By.id("menu-list-142-menuitem-" + Integer.toString(133 + weed) + "")).click();
        wd = Integer.toString(weed);

    }

    public static void verify_event(WebDriver driver)
            throws FileNotFoundException, IOException, ParseException, InterruptedException {
        JSONParser parser = new JSONParser();
        String etitle = "", estim = "", eetim = "", esdat = "", eedat = "", rpt = "", weekd = "",typo="", seq = "", dats = "";
        JSONArray a = (JSONArray) parser.parse(new FileReader("./cstmevents.json"));
        for (Object o : a) {
            JSONObject event = (JSONObject) o;
            etitle = (String) event.get("title");
            estim = (String) event.get("start_t");
            eetim = (String) event.get("end_t");
            esdat = (String) event.get("start_d");
            eedat = (String) event.get("end_d");
            rpt = (String) event.get("repeat");
            weekd = (String) event.get("weekday");
            seq = (String) event.get("seqw");
            dats = (String) event.get("dates");
            typo=(String) event.get("typp");
            driver.get("https://timeline.edvora.me/");
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[1]/div[2]/div/div[1]/div[1]/div/div[3]/div[1]/button[1]")).click();
            
            if (typo.equals("w")) {
                weekrepeat(driver,etitle,esdat,eedat,Integer.parseInt(rpt),weekd,seq);
            } else {
                monthyrepeat(driver, etitle, esdat, eedat, Integer.parseInt(rpt), dats);
            }
        }
    }

    private static void weekrepeat(WebDriver driver, String etitle, String esdat, String eedat, int rpt,
            String weekd, String seq) {
                LocalDate[] l1=new LocalDate[20];
                LocalDate start = LocalDate.parse(esdat);
                LocalDate end= LocalDate.parse(eedat);
                int q=0;
                while(start.isBefore(end)){
                    start=start.plusMonths(rpt);
                    if(weekd.equals("0")){
                        LocalDate firstMonday = start.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
                        l1[q]=firstMonday;
                    }
                    if(weekd.equals("1")){
                        LocalDate firstMonday = start.with(TemporalAdjusters.firstInMonth(DayOfWeek.TUESDAY));
                        l1[q]=firstMonday;
                    }
                    if(weekd.equals("2")){
                        LocalDate firstMonday = start.with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY));
                        l1[q]=firstMonday;
                    }
                    if(weekd.equals("3")){
                        LocalDate firstMonday = start.with(TemporalAdjusters.firstInMonth(DayOfWeek.THURSDAY));
                        l1[q]=firstMonday;
                    }
                    if(weekd.equals("4")){
                        LocalDate firstMonday = start.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
                        l1[q]=firstMonday;
                    }
                    if(weekd.equals("5")){
                        LocalDate firstMonday = start.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
                        l1[q]=firstMonday;
                    }
                    if(weekd.equals("6")){
                        LocalDate firstMonday = start.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
                        l1[q]=firstMonday;
                    }
                    q++;
                }


    }

    public static void monthyrepeat(WebDriver driver, String etitle, String esdat, String eedat, int rpt,
            String dats) throws InterruptedException {
        String[] a = esdat.split("-");
        int f = Integer.parseInt(a[1])+rpt;
        String finald = "";
        String[] p = dats.split(",");
        for (int v = 0; v < f + 12; v = v + rpt) {
            for (int u = 1; u < p.length; u = u + 1) {

                if(f<10)
                finald = a[0] + "-0" + Integer.toString(f) + "-" + p[u];
                if(Integer.parseInt(p[u])<10)
                finald = a[0] + "-" + Integer.toString(f) + "-0" + p[u];
                if(f<10&&Integer.parseInt(p[u])<10)
                finald = a[0] + "-0" + Integer.toString(f) + "-0" + p[u];
                if(f>9&&Integer.parseInt(p[u])>9)
                finald = a[0] + "-" + Integer.toString(f) + "-" + p[u];
                driver.findElement(By.xpath("//*[@id='menu-button-48']")).click();
                WebElement date = driver.findElement(By.xpath("//*[@id='menu-list-48']/div/div[3]"));
                List<WebElement> dates = date.findElements(By.xpath("//*[@class='css-1r65cup']"));
                List<WebElement> lbel = date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
                int x = 0;
                boolean flag = true;
                for (WebElement i : lbel) {
                    String d = i.getAttribute("value");
                    if (d.equals(finald)) {
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
                            if (d.equals(finald)) {
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
                dates.get(x).click();
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
                        WebElement dets = driver
                                .findElement(
                                        By.xpath("/html/body/div[2]/div/div[3]/div/section/div/div/div/div/div[2]"));
                        List<WebElement> disc = dets.findElements(By.tagName("p"));
                        String etit = disc.get(0).getText();
                        String etim = disc.get(3).getText();
                        lner = true;
                        Thread.sleep(1000);
                        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/section/div/div/div/div/div[3]/button")).click();
                        break;
                    } else {
                        lner = false;
                    }


                }
                if (lner == false) {
                    System.out.println("Event not found");
                }
            }
            if(f+rpt<=12)
            f=f+rpt;
            else{
                f=rpt-(12-f);
                a[0]=Integer.toString(Integer.parseInt(a[0])+1);
            }
            if(finald.equals(eedat))
                break;
        }
    }

   

    public static void main(String[] args)
            throws InterruptedException, AWTException, FileNotFoundException, IOException, ParseException {
        App1();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9000");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://timeline.edvora.me/");
        Thread.sleep(3000);
        System.out.println("Creating events");
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
        driver.findElement(By.id("menu-list-75-menuitem-72")).click();
        int xyz=(int) (Math.random() * (1 - 0 + 1) + 0);
        if(xyz==1)
        set_monthdate(driver);
        if(xyz==0)
        set_monthday(driver);
        driver.findElement(By.xpath("//*[@id='chakra-modal--body-33']/div/div[1]/div[1]/div[1]/div/input")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//*[@id='chakra-modal--body-33']/div/div[1]/div[1]/div[1]/div/input")).sendKeys(re);
        driver.findElement(By.xpath("//*[@id='chakra-modal--body-33']/div/div[2]/button[2]")).click();
        // time section :

        set_time(driver);

        // date:-

        set_date(driver);
        save_event_dt();
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[4]/div[2]/div[2]/div/button[2]")).click();
        System.out.println("Event Created successfully\n\nVerifing created event");
        verify_event(driver);
    }

}
