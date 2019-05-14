/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package crawler;

import java.util.Set;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.http.Header;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Controller;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hello.Metadata;
import hello.MetadataRepository;



@Service
public class BasicCrawler extends WebCrawler {

    private static SessionFactory factory; 
    // TODO: make sure that repository is created correctly
    @Autowired
    private MetadataRepository metadataRepository;
    private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");

    /**
     * You should implement this function to specify whether the given url
     * should be crawled or not (based on your crawling logic).
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        // Ignore the url if it has an extension that matches our defined set of image extensions.
        if (IMAGE_EXTENSIONS.matcher(href).matches()) {
            return false;
        }

        // Only accept the url if it is in the "www.ics.uci.edu" domain and protocol is "http".
        return href.startsWith("https://www.list.am/item");
    }

    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */



    @Override
    public void visit(Page page) {
        int docid = page.getWebURL().getDocid();
        String url = page.getWebURL().getURL();
        String domain = page.getWebURL().getDomain();
        String path = page.getWebURL().getPath();
        String subDomain = page.getWebURL().getSubDomain();
        String parentUrl = page.getWebURL().getParentUrl();
        String anchor = page.getWebURL().getAnchor();
/*
        logger.debug("Docid: {}", docid);
        logger.info("URL: {}", url);
        logger.debug("Domain: '{}'", domain);
        logger.debug("Sub-domain: '{}'", subDomain);
        logger.debug("Path: '{}'", path);
        logger.debug("Parent page: {}", parentUrl);
        logger.debug("Anchor text: {}", anchor);
*/
        // TODO: fix the fonts from the incoming data
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            String title = htmlParseData.getTitle();
            String description = htmlParseData.getMetaTagValue("description");
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
/*
            logger.debug("Text length: {}", text.length());
            logger.debug("Html length: {}", html.length());
            logger.debug("Number of outgoing links: {}", links.size());
            // TODO: put title, description and url into the database
*/
            logger.debug("Title is " + title);

            logger.debug("description is " + description);

            logger.info("URL: {}", url);

           // Metadata md = new Metadata(title, description, url);
            
            //this.metadataRepository.save(md);

        try {
         factory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }


        
        Session session = factory.openSession();
        Transaction tx = null;
        Integer mdID = null;
        
        try {
           tx = session.beginTransaction();
           Metadata md = new Metadata(title, description, url);
           mdID = (Integer) session.save(md); 
           tx.commit();
        } catch (HibernateException e) {
           if (tx!=null) tx.rollback(); 
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
    

            // TODO: if the url exists then update it
            // TODO: consider a case where there is no description okay

        }

        Header[] responseHeaders = page.getFetchResponseHeaders();
        if (responseHeaders != null) {
            //logger.debug("Response headers:");
            for (Header header : responseHeaders) {
               // logger.debug("\t{}: {}", header.getName(), header.getValue());
            }
        }

        logger.debug("=====================================================");
    }
}