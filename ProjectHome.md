# Project overview #
TSQLParser is a Java based SQL parser with support for SQL-Temporal-Extension syntax. It is based on [JSQLParser](http://jsqlparser.sourceforge.net/) project,with the added syntax support. It also provides
query rewrite capability from the temporal T-SQL form to standard SQL.

## Temporal SQL Extension ##
The current supported SQL time extensions of the parser are:
> _AS OF SYSTEM TIME_ returns data as it was at specific point in time:
```sql
select * from foo as of system time 2011-08-12 13:02:00;```
> _VERSIONS BETWEEN_ returns all versions of data between two timestamps:
```sql
select * from foo versions between system time 2011-08-12 13:02:00 and system time 2011-08-12 13:02:00;```
_VERSIONS AFTER/Before SYSTEM TIME_ returns all versions of data before or after a point in time:
```sql
select * from foo versions before system time 2011-08-12 13:02:00;```

## Rewrite capability ##
Beside parsing the TSQL, the the parser can rewrite the TSQL queries to a standard SQL
form with added timestamps information.
Example:

From: ```sql
 INSERT INTO tab VALUES(1,"hello world",'this is me') ```

Into:
```sql
 INSERT INTO tab VALUES (1, "hello world",'this is me', now(), '2037-12-31 23:59:59.0') ```

From: ```sql
 DELETE FROM tab WHERE b="7" ```
Into: ```sql
 UPDATE tab SET tend=now() WHERE b = '7' ```

From: ```sql
 SELECT * FROM tab t1, tab t2 WHERE c=7 AND t1.a=t2.a ```
Into: ```sql
 SELECT * FROM tab AS t1 , tab AS t2 WHERE (c = 7 AND t1.a = t2.a)
AND t1.tend= '2037-12-31 23:59:59.0' AND t2.tend= '2037-12-31 23:59:59.0' ```

## Limitations ##

  * Current supported DBMS are MySQL and PostgreSQL
  * Depending on the DBMS, TSQL-Parser generates different formats of timestamps in order to have a common granularity of microseconds
    * MySQL: Timestamps are in a Decimal(20,6) form, where a date 2011-12-31 23:59:59.123456 is translated into 201112312359.123456
    * Postgres supports natively microseconds storage.

### Contact ###
Carlo Curino

### License and Restrictions ###
The TSQLParser is a free software released under the LGPL v.3 license.