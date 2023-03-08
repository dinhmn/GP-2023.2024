FROM mysql/mysql-server:8.0

EXPOSE 3306

ADD my.cnf /etc/mysql/conf.d/my.cnf

RUN chmod 644 /etc/mysql/conf.d/my.cnf
CMD ["mysqld", "--character-set-server=utf8mb4", "--collation-server=utf8mb4_bin"]
