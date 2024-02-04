package by.it_academy.jd2.parser_service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class ParserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParserServiceApplication.class, args);
        try {
        Document doc = Jsoup.connect("https://realt.by/rent-flat-for-long/object/1838066/").get();
            String h1 = doc.select("h1").text();
            System.out.println(h1);
            Element elements = doc.select("div[class=w-1/2]").first();
            String attr = elements.attr("p");
            System.out.println(attr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
