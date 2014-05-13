SQL-Builder
==========

Plain sql statements are very annoying to write in java. We used this SQL-Builder in one of our student-projects at zhaw.ch. It allows one to build SQL queries and run them with a very straightforward syntax. One example for a select:

```java
RowSet processResult = select().fields("id", "title").tables("process").build().executeQuery();
```

Feel free to use or copy.
Note: please *don't use this builder in production*, the project is not very well tested.

Usage
-----
### Select

```java
RowSet rs = select().fields("id", "title").tables("users").where("name", "Bob").limit(10).build().executeQuery();
```

### Update

```java
update().tables("process")
                    .values("title", "My Title")
                    .where("owner_id", "42")
                    .where("id", id).build().executeUpdate();
```

### Insert

```java
insert().tables("idea")
        .values("title", "My Title", "owner_id", "52").build().executeUpdate();
```

### Delete

```java
delete().tables("idea").where("id", String.valueOf(ideaId))
                    .limit(1).build().executeUpdate();
```

Please check the interface ISqlBuilder.java for all methods and javadoc.


Requirements
------------

* Log4j
* Apache Commons Lang3


License
-------

Copyright (C) 2014 Simon Aebersold [@saebersold](https://twitter.com/saebersold), Yanick Lukic (@yaluki)

SQL-Builder is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

SQL-Builder is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
