/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googlesearch;

import java.util.*;
import java.net.*;

import org.jsoup.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleSearch {

    static int count = 0;
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter String to search: ");
        
        String google = "http://www.google.com/search?q=";
        String search = sc.nextLine();
        String charset = "UTF-8";
        String userAgent = "Mozilla";
        
        System.out.println("Searching ................ ");
        //System.out.println(search+"-->"+URLEncoder.encode(search,charset));
        
        Elements links = Jsoup.connect(google+ URLEncoder.encode(search,charset)+"&num=1000").timeout(0).userAgent(userAgent).get().select(".g>.r>a");
        
        for( Element link : links )
        {
            String title = link.text();
            String url = link.absUrl("href");
            
            //decode link from link of google.
            String urlLink = URLDecoder.decode(url.substring(url.indexOf("=")+1 , url.indexOf("&")) , charset);
            
            if( ! urlLink.startsWith("http") )
            {
                System.out.println("Not url: "+urlLink);
                continue;    //ads/news etc
            }
            
            System.out.println("Title: "+title);     //title of page
            //System.out.println("URL: "+url);      //complete link including url of google
            System.out.println("    URL : "+urlLink);   //link of website
            
            count++;
        }
        
        System.out.println("**  --> Done : "+ count +" results");
    }
    
}
