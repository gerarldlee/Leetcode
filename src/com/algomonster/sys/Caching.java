package com.algomonster.sys;

public class Caching {

    public void databaseCaching() {
        // redis or memcached - reduces the frequency of DB reads

        /*
        Types:

        Cache-aside - web server will read from cache, if data exist. If not, server will fetch data from DB,
        and save to cache. Cannot handle data updates

        Read-through - sits between application and database (does not interact with database directly).
        When there is a cache miss, cache fetches DB itself. ex: Amazon DynamoDB Accelerator

        Write-through - sacrifices write performance by synchronously updating database and cache.

        Write-back - similar to Write-through but do it asynchronously. May have data loss when server crashes before
        write done
         */
    }

    /*
    HTTP Cache of static files

    2-kinds:
    shared cache - header: Cache-Control: public, cache used by CDN and more than one user

    private cache - header: Cache-Control: private, cache used by users browser used with only that user
     */
    public void cdnCache() {

    }
}
