//daily event creation and check

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
import io.github.bonigarcia.wdm.WebDriverManager;
public final class s2 {

    static String tit;
    static String dat;
    static String stim;
    static String ftim;
    String clsr;
    static String cdate_str;
    static int dd;
    static void App1() {
        Date cdate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        cdate_str = formatter.format(cdate);
        formatter = new SimpleDateFormat("dd");
        dd=Integer.parseInt(formatter.format(cdate));
        System.out.println(cdate_str);
        
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
        System.out.println(cdate_str);
        for ( WebElement i : lbel ) {
            String d=i.getAttribute("value");
            c++;
            if(d.equals(cdate_str)){
                flag=true;
                break;
            }
            else{
                flag=false;
            }
         }
         System.out.println(flag);
         if(flag==false){
            while(true){
                c=0;
                driver.findElement(By.xpath("//*[@id='chakra-modal-42']/div/div[1]/button[2]")).click();
                date=driver.findElement(By.xpath("//*[@id='chakra-modal-42']/div/div[3]"));
                dates=date.findElements(By.xpath("//*[@class='css-1r65cup']"));
                lbel=date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
                for ( WebElement i : lbel ) {
                    String d=i.getAttribute("value");
                    c++;
                    if(d.equals(cdate_str)){
                        flag=true;
                        break;
                    }
                    else{
                        flag=false;
                    }
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
         System.out.println(Month);
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
        System.out.println(edate_str);
        for ( WebElement i : lbel ) {
            String d=i.getAttribute("value");
            c++;
            if(d.equals(edate_str)){
                flag=true;
                break;
            }
            else{
                flag=false;
            }
         }
         if(flag==false){
            while(true){
                c=0;
                driver.findElement(By.xpath("//*[@id='chakra-modal-44']/div/div[1]/button[2]")).click();
                date=driver.findElement(By.xpath("//*[@id='chakra-modal-44']/div/div[3]"));
                dates=date.findElements(By.xpath("//*[@class='css-1r65cup']"));
                lbel=date.findElements(By.xpath("//*[@class='css-1r65cup']/input"));
                for ( WebElement i : lbel ) {
                    String d=i.getAttribute("value");
                    c++;
                    if(d.equals(edate_str)){
                        flag=true;
                        break;
                    }
                    else{
                        flag=false;
                    }
                 }
                 if(flag==true){
                    break;
                 }
            }
         }
         dates.get(c).click();
         driver.findElement(By.xpath("//*[@id='chakra-modal-44']/div/div[4]/button[2]")).click();
         
    }

    static void set_time(WebDriver driver) throws InterruptedException, AWTException{
        int stime = (int)(Math.random()*(12-0+1)+0); 
        stime=12;
        int ftime;
        driver.findElement(By.id("menu-button-40")).click();
        Thread.sleep(3000);
        System.out.println(stime);
        if(stime==12){
            ftime=1;
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id='menu-list-40']/div/div[1]/div[1]")).click();
            Robot r=new Robot();
            r.keyPress(KeyEvent.VK_1);
            // driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[1]")).sendKeys(Keys.BACK_SPACE);
            // driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[1]")).sendKeys(Keys.BACK_SPACE);
            // driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[1]")).sendKeys(Integer.toString(stime));
            // driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[3]/button[1]")).click();
            // driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[2]/button[2]")).click();
        
        }
        else{
            ftime=stime+1;
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[1]/input")).sendKeys(Keys.BACK_SPACE);
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[1]/input")).sendKeys(Keys.BACK_SPACE);
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[1]/input")).sendKeys(Integer.toString(stime));
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[1]/div[3]/button[1]")).click();
            driver.findElement(By.xpath("//*[@id='menu-list-39']/div/div[2]/button[2]")).click();
        }
        stim=Integer.toString(stime);
        ftim=Integer.toString(ftime);
    }
    static void verify_event(WebDriver driver){
        
    }
    public static void main(String[] args) throws InterruptedException, AWTException {
        App1();
        ChromeOptions options = new ChromeOptions(); 
        options.setExperimentalOption("debuggerAddress","localhost:9000");
		WebDriverManager.chromedriver().setup();
        //setting up driver with dynamic chrome driver
		WebDriver driver = new ChromeDriver(options);
        // driver.get("https://timeline.edvora.me/");
        // Thread.sleep(3000);
        // if(driver.getCurrentUrl().equals("https://main.edvora.me/l")){
        //     driver.findElement(By.id("username")).sendKeys("automation");
        //     driver.findElement(By.id("password")).sendKeys("Hello@123");
        //     driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/button")).click();
        //     driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/button")).click();
        //     Thread.sleep(5000);
        // }
        // if(driver.getCurrentUrl().equals("https://classrooms.edvora.me/")){
        //     driver.get("https://timeline.edvora.me/");
        // }
        // driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[1]/div[2]/div/div[1]/div[2]/a/button")).click();
        // Thread.sleep(5000);
        // set_title(driver);

        // //set classroom
        // driver.findElement(By.id("menu-button-15")).click();
        // Thread.sleep(1000);
        // driver.findElement(By.id("menu-list-15-menuitem-13")).click();

        // //type of event
        // //daily event

        // driver.findElement(By.id("menu-button-37")).click();
        // driver.findElement(By.id("menu-list-37-menuitem-30")).click();

        // time section : 

        set_time(driver);
        
        //date:-
         
        // set_date(driver);
       
        // driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[3]/div[2]/div[2]/div/button[2]")).click();

    }
}
