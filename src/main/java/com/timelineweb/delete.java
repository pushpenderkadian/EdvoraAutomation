package com.timelineweb;

import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class delete {

    public static void main(String[] args) throws InterruptedException, IOException {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9000");
        WebDriverManager.chromedriver().setup();
        // setting up driver with dynamic chrome driver
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://timeline.edvora.me/");
        Thread.sleep(3000);

        System.out.println("Creating Event");
        if(driver.getCurrentUrl().equals("https://main.edvora.me/l")){
        driver.findElement(By.id("username")).sendKeys("delete_test");
        driver.findElement(By.id("password")).sendKeys("Hello@123");
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/button")).click();
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/button")).click();
        Thread.sleep(5000);
        }
        if(driver.getCurrentUrl().equals("https://classrooms.edvora.me/")){
        driver.get("https://timeline.edvora.me/");
        }

        File file = new File("./eventnames.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linee = br.readLine();
        String[] eves = linee.split(",");
        for (String j : eves) {
            try {
                List<WebElement> eventsweek = driver.findElements(By.xpath("//*[text()='" + j + "']"));
                List<Integer> idex = new ArrayList<>();
                List<WebElement> clickable = new ArrayList<>();
                int c = 0;
                for (WebElement i : eventsweek) {
                    try {
                        i.click();
                        Thread.sleep(1000);
                        driver.findElement(
                                By.xpath("/html/body/div[2]/div/div[3]/div/section/div/div/div/div/div[3]/button"))
                                .click();
                        Thread.sleep(2000);
                        idex.add(c);
                    } catch (Exception e) {
                    }
                    c++;
                }
                for (Integer i : idex) {
                    clickable.add(eventsweek.get(i));
                }

                int s = (int) (Math.random() * (clickable.size() - 0 + 1) + 0);
                String naam=clickable.get(s).getText();
                clickable.get(s).click();
                try {
                    Thread.sleep(1000);
                    try {
                        driver.findElement(
                            By.xpath("/html/body/div[2]/div/div[3]/div/section/div/div/footer/div/button[2]")).click();
                    
                    } catch (Exception e) {
                        driver.findElement(
                            By.xpath("/html/body/div[2]/div/div[3]/div/section/div/div/footer/div/button")).click();
                    
                    }
                    try {
                        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[3]/div/section/footer/button[2]"))
                                .click();
                    } catch (Exception e) {
                        int l = (int) (Math.random() * (3 - 1 + 1) + 1);
                        driver.findElement(By
                                .xpath("/html/body/div[2]/div/div[3]/div/section/div/div/div/div[1]/label[" + l + "]"))
                                .click();
                        driver.findElement(
                                By.xpath("/html/body/div[2]/div/div[3]/div/section/div/div/div/div[2]/button[2]"))
                                .click();
                        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[3]/div/section/footer/button[2]")).click();
                        if(l==1)
                            System.out.println("Event "+naam+" is deleted with This Event selection");
                        if(l==2)
                            System.out.println("Event "+naam+" is deleted with This and following Events selection");
                        if(l==3)
                            System.out.println("Event "+naam+" is deleted with All Events selection");
                    }
                } catch (Exception e) {
                    System.out.println("Event "+naam+" is currently live");
                    driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/section/div/div/div/div/div[3]/button")).click();
                }
            } catch (Exception e) {
                continue;
            }

        }
    }
}
