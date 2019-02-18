![alt text](https://github.com/devgrigor/java_search_platform_aua/blob/master/logo.png?raw=true)
# GALASearch
Search platform for a group project.

1. Crawler using java. Small script that we will run manually
2. Saving to more than 1 db-s. Do we need this ?
3. Cahcing (Redis caching system https://redis.io/clients#java ?)
4. Collecting statistics (just API endpoint, including search results)
5. Serving queries from cache and db (another API endpoint)
6. Serve autocomplete(another API endpoin. Based on previus searches only)
7. Show sponsored links (front end, easy)
8. Remove search results that contain bad words (In 4-th point remove if has a bad word)
9. Another service for ads (can be included in 4-th point)

**Framework: Spring**

**Caching: Redis**

**DB: MySQL (Elastic search for fuzzy search ?)**

**Front: React or Angular**

**Tests: jUnit ?**

**Manual API tests: Postman**
