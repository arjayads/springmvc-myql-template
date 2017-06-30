# its-a-map

## Config
`\src\main\resources\env.properties` - ignored by `git`, needs to be added

```

message.source.basename=i18n/messages

#DB properties:
db.datasource=com.mysql.jdbc.jdbc2.optional.MysqlDataSource
db.driver=com.mysql.jdbc.Driver
db.url=jdbc:mysql://127.0.0.1:3306/{db_name}
db.username={username}
db.password={password}

#Hibernate Configuration:
hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
hibernate.dialect.ms=org.hibernate.dialect.SQLServerDialect


# base url
url.base=map.dev:8080

# gzip compression
server.compression.enabled=true
server.compression.mime-types=application/json,application/xml,text/html,text/xml,text/plain,application/pdf

```
